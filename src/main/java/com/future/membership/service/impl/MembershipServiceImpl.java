package com.future.membership.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.MembershipDtoExample;
import com.future.membership.bean.MembershipLevelDto;
import com.future.membership.bean.UserDto;
import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.bo.excel.MembershipExcel;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.MembershipPage;
import com.future.membership.bean.reqsponse.MembershipResp;
import com.future.membership.bean.request.MembershipPassWord;
import com.future.membership.bean.request.MembershipReq;
import com.future.membership.enums.MembershipState;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.MembershipDtoMapper;
import com.future.membership.mapper.MembershipLevelDtoMapper;
import com.future.membership.mapper.UserDtoMapper;
import com.future.membership.service.MembershipService;
import com.future.membership.util.ExcelUtils;
import com.future.membership.util.MembershipSend;
import com.future.membership.util.PasswordHelper;

@Service
@Transactional
public class MembershipServiceImpl implements MembershipService {

	private Logger logger = LoggerFactory.getLogger(MembershipServiceImpl.class);

	@Autowired
	private MembershipDtoMapper membershipDtoMapper;

	@Autowired
	private UserDtoMapper userMapper;
	@Autowired
	private MembershipLevelDtoMapper levelMapper;

	@Override
	public int saveOrUpdateMembership(MembershipReq membershipReq, String tid, boolean update) {
		if (membershipReq != null) {
			MembershipDto newMembershipDto = new MembershipDto();
			// 此处关联user表
			UserDto user = new UserDto();
			user.setAge(membershipReq.getAge());

			user.setGender(membershipReq.getGender());
			user.setLastloginTime(membershipReq.getLastloginTime());
			if (!update) { // 创建
				user.setLocked(1); // 刚创建 正常
				user.setCreateTime(new Date());
				user.setUsername(membershipReq.getUsername());
				//user.setPassword("123456"); // 此处需要加密 默认123456
				UserBo bo = new UserBo();
				bo.setUsername(user.getUsername());
				bo.setPassword("123456");   //默认密码为123456
				PasswordHelper ps = new PasswordHelper();
				ps.encryptPassword(bo);
				user.setPassword(bo.getPassword());
				user.setType(2); // 会员
				user.setValid(ValidHelper.YES.toInt());
				userMapper.insertSelective(user);
				newMembershipDto.setAudit(0); // 审核中
				newMembershipDto.setLevel_id(MembershipState.BRONZE.toInt());  //默认青铜会员
			} else { // 修改
				user.setLocked(membershipReq.getLocked());
				user.setPassword(membershipReq.getPassword());
				user.setValid(membershipReq.getValid());
				user.setLocked(membershipReq.getLocked());
				user.setId(membershipReq.getUser_id());
				userMapper.updateByPrimaryKey(user);
				newMembershipDto.setAudit(membershipReq.getAudit());
			}

			newMembershipDto.setLevel_id(membershipReq.getLevel_id());
			newMembershipDto.setEmail(membershipReq.getEmail());
			newMembershipDto.setUser_id(user.getId());
			newMembershipDto.setAddress(membershipReq.getAddress());
			newMembershipDto.setCompany(membershipReq.getCompany());
			newMembershipDto.setFitnessNumber(membershipReq.getFitnessNumber()); // 已经健身次数
			newMembershipDto.setLeftTimes(membershipReq.getLeftTimes()); // 会员还剩的健身次数

			newMembershipDto.setTel(membershipReq.getTel());
			if (membershipReq.getId() != null) {
				newMembershipDto.setId(membershipReq.getId());
			}
			if (membershipReq.getValid() != null) {
				newMembershipDto.setValid(membershipReq.getValid());
			} else {
				newMembershipDto.setValid(ValidHelper.YES.toInt()); // 默认可用
			}
			int i = -1;
			if (update) { // 修改
				i = membershipDtoMapper.updateByPrimaryKeySelective(newMembershipDto);
			} else {
				i = membershipDtoMapper.insertSelective(newMembershipDto);
			}
			
			logger.info("saveorUpdateMembership newMembershipDto={}", JSON.toJSONString(newMembershipDto));
			logger.info("saveorUpdateMembership tid={},id={}", tid, i);
			return i;
		}
		return -1;
	}

	@Override
	public boolean delete(String tid, int id) {
		MembershipDtoExample example = new MembershipDtoExample();
		example.createCriteria().andIdEqualTo(id).andValidEqualTo(ValidHelper.YES.toInt());
		List<MembershipDto> membershipDtos = membershipDtoMapper.selectByExample(example);

		MembershipDto membershipDto = null;
		int i = 0;
		if (membershipDtos != null) {
			membershipDto = membershipDtos.get(0);
			membershipDto.setValid(ValidHelper.NO.toInt()); // 逻辑删除
			i = membershipDtoMapper.updateByPrimaryKeySelective(membershipDto);
			UserDto user = userMapper.selectByPrimaryKey(membershipDto.getUser_id());
			user.setValid(ValidHelper.NO.toInt()); // 将user对应的表删除
			userMapper.updateByPrimaryKeySelective(user);
		}

		return i > 0 ? true : false;
	}

	@Override
	public MembershipPage queryList(int membership_id,int currentPage) {
		MembershipDtoExample example = new MembershipDtoExample();
		if(membership_id == -1){
			example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		}else{
			example.createCriteria().andIdEqualTo(membership_id).andValidEqualTo(ValidHelper.YES.toInt());
		}
		int count = membershipDtoMapper.countByExample(example);
		int pageSize = 10; // 默认10页
		String orderKeyStr = "id"; // 默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize, count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<MembershipDto> dtos = membershipDtoMapper.selectByExample(example);
		MembershipPage membershipPage = new MembershipPage();
		List<MembershipResp> resps = new ArrayList<MembershipResp>();
		for (MembershipDto membershipDto : dtos) {
			MembershipResp resp = new MembershipResp();
			BeanUtils.copyProperties(membershipDto, resp);
			MembershipState state = MembershipState.parse(resp.getLevel_id());
			switch (state) {
			case PLATINUM: // 铂金会员
				resp.setLevelName("铂金会员");
				break;
			case GOLD: // 黄金会员
				resp.setLevelName("黄金会员");
				break;
			case SILVER: // 白银会员
				resp.setLevelName("白银会员");
				break;
			case BRONZE: // 青铜会员
				resp.setLevelName("青铜会员");
				break;
			default:
				break;
			}
			UserDto user = userMapper.selectByPrimaryKey(resp.getUser_id());
			resp.setName(user.getUsername());
			resp.setAge(user.getAge());
			resp.setEmail(membershipDto.getEmail());
			switch (user.getGender()) {
			case "1":
				resp.setGender("男");
				break;
			case "2":
				resp.setGender("女");
				break;

			default:
				break;
			}
			resp.setLastloginTime(user.getLastloginTime());
			resps.add(resp);
		}
		membershipPage.setList(resps);
		membershipPage.setPage(page);
		return membershipPage;
	}

	@Override
	public MembershipResp getById(int id) {
		MembershipDto memberDto = membershipDtoMapper.selectByPrimaryKey(id);
		UserDto user = userMapper.selectByPrimaryKey(memberDto.getUser_id());
		MembershipLevelDto level = levelMapper.selectByPrimaryKey(memberDto.getLevel_id());
		MembershipResp resp = new MembershipResp();
		BeanUtils.copyProperties(memberDto, resp);
		BeanUtils.copyProperties(user, resp);
		resp.setId(memberDto.getId());
		resp.setName(user.getUsername());
		resp.setLevelName(level.getLevelName());
		return resp;
	}

	@Override
	public void downLoad(OutputStream outputStream) {
		// 自定义
		List<MembershipExcel> list = new ArrayList<MembershipExcel>();
		MembershipDtoExample example = new MembershipDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		List<MembershipDto> dtos = membershipDtoMapper.selectByExample(example);
		for (MembershipDto membershipDto : dtos) {
			MembershipExcel excel = new MembershipExcel();
			BeanUtils.copyProperties(membershipDto, excel);
			switch (membershipDto.getAudit()) {
			case 1:
				excel.setAudit("审核通过");
				break;
			case 0:
				excel.setAudit("审核中");
				break;
			default:
				break;
			}
			UserDto user = userMapper.selectByPrimaryKey(membershipDto.getUser_id());
			BeanUtils.copyProperties(user, excel);
			switch (user.getLocked()) {
			case 1:
				excel.setLocked("正常");
				break;
			case 0:
				excel.setLocked("锁定");
				break;

			default:
				break;
			}
			switch (user.getGender()) {
			case "1":
				excel.setGender("男");
				break;
			case "2":
				excel.setGender("女");
				break;

			default:
				break;
			}
			list.add(excel);
		}
		logger.info("list={}",JSON.toJSONString(list));
		HSSFWorkbook workBook = ExcelUtils.createWorkBook();
		HSSFSheet sheet = ExcelUtils.createSheet(workBook, "会员信息");
		String[] headers = new String[] { "用户名", "性别", "锁定状态", "电话", "公司", "住址", "审核状态", "email" };
		ExcelUtils.fillSheet(sheet, headers, list, "yyyy-MM-dd");

		// 需要合并的行数
		// int rowSize=list.size();

		// 合并excel单元格时候需要传进去的合并哪些行和列int[]{1,size,2,4}代表四个点{开始行,结束行,开始列,结束列}
		// List<int[]> mergeCoordinateList=new ArrayList<int[]>();
		// int[] workCoordinate1=new int[]{1,rowSize,0,0};
		//// int[] workCoordinate2=new int[]{1,rowSize,1,1};
		// mergeCoordinateList.add(workCoordinate1);
		//// mergeCoordinateList.add(workCoordinate2);
		// ExcelUtils.mergeTableCell(workBook,outputStream,mergeCoordinateList,0);

		ExcelUtils.write2OutStream(workBook, outputStream);
		try {
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MembershipDto> queryAll() {
		MembershipDtoExample example = new MembershipDtoExample();
		example.createCriteria().andIdIsNotNull().andValidEqualTo(ValidHelper.YES.toInt());
		return membershipDtoMapper.selectByExample(example);
	}

	@Override
	public MembershipDto selectByExample(MembershipDtoExample example) {
		return membershipDtoMapper.selectByExample(example).get(0);
	}

	@Override
	public void updatePassword(MembershipPassWord membership) {
		UserBo user = new UserBo();
		MembershipDto dto = membershipDtoMapper.selectByPrimaryKey(membership.getId());
		UserDto userDto = userMapper.selectByPrimaryKey(dto.getUser_id());
		user.setUsername(userDto.getUsername());
		user.setPassword(membership.getPassword());
		PasswordHelper ps = new PasswordHelper();
		ps.encryptPassword(user);
		userDto.setPassword(user.getPassword());
		userMapper.updateByPrimaryKey(userDto);
	}

	@Override
	public MembershipSend getSendById(int id) {
		MembershipDto dto = membershipDtoMapper.selectByPrimaryKey(id);
		MembershipSend send = new MembershipSend();
		send.setEmail(dto.getEmail());
		send.setId(dto.getId());
		return send;
	}

	@Override
	public boolean sendEmail(int id) {
		boolean flag = false;
		MembershipSend send = getSendById(id);
		
		return false;
	}
}
