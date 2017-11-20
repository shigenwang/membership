package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.TeacherDto;
import com.future.membership.bean.TeacherDtoExample;
import com.future.membership.bean.UserDto;
import com.future.membership.bean.bo.TeacherBo;
import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.TeacherPage;
import com.future.membership.bean.request.TeacherPs;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.TeacherDtoMapper;
import com.future.membership.mapper.UserDtoMapper;
import com.future.membership.service.TeacherService;
import com.future.membership.util.PasswordHelper;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{

	private Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
	
	@Autowired
	private TeacherDtoMapper teacherMapper;
	
	@Autowired
	private UserDtoMapper userMapper;
	
	@Override
	public TeacherPage queryList(Integer teacher_id,int currentPage) {
		TeacherDtoExample example = new TeacherDtoExample();
		if(teacher_id == null){
			example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		}else{
			example.createCriteria().andIdEqualTo(teacher_id).andValidEqualTo(ValidHelper.YES.toInt());
		}
		int count = teacherMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<TeacherDto> teacherDtos = teacherMapper.selectByExample(example);
		List<TeacherBo> bos = new ArrayList<TeacherBo>();
		for (TeacherDto teacherDto : teacherDtos) {
			TeacherBo teacherBo = new TeacherBo();
			BeanUtils.copyProperties(teacherDto, teacherBo);
			UserDto user = userMapper.selectByPrimaryKey(teacherDto.getUser_id());
			BeanUtils.copyProperties(user, teacherBo);
			teacherBo.setId(teacherDto.getId());
			teacherBo.setUser_id(user.getId());
			switch (user.getGender()) {
			case "1":
				teacherBo.setGender("男");
				break;
			case "2":
				teacherBo.setGender("女");
				break;

			default:
				break;
			}
			logger.info("teacherBo={}",JSON.toJSONString(teacherBo));
			bos.add(teacherBo);
		}
		TeacherPage teacherPage = new TeacherPage();
		teacherPage.setList(bos);
		teacherPage.setPage(page);
		return teacherPage;
	}

	@Override
	public int saveOrUpdateTeacher(TeacherBo teacherBo, boolean update) {
		int i = -1;
		if(teacherBo!=null){
			TeacherDto dto = new TeacherDto();
			teacherBo.setValid(ValidHelper.YES.toInt());
			BeanUtils.copyProperties(teacherBo, dto);
			logger.info("teacherBo={},dto={}",JSON.toJSONString(teacherBo),JSON.toJSONString(dto));
			if(update){
				UserDto user = userMapper.selectByPrimaryKey(dto.getUser_id());
				user.setUsername(teacherBo.getUsername());
				userMapper.updateByPrimaryKey(user);
				i = teacherMapper.updateByPrimaryKey(dto);
			}else{
				UserDto user = new UserDto();
				BeanUtils.copyProperties(teacherBo, user);
				user.setLocked(1);    //默认正常
				//user.setPassword("123456");    //默认     回来加密
				UserBo bo = new UserBo();
				bo.setUsername(user.getUsername());
				bo.setPassword("123456");
				PasswordHelper ps = new PasswordHelper();
				ps.encryptPassword(bo);
				user.setPassword(bo.getPassword());
				user.setType(3);    //教练
				userMapper.insertSelective(user);
				dto.setUser_id(user.getId());
				i = teacherMapper.insertSelective(dto);
			}
		}
		return i;
	}

	@Override
	public TeacherBo selectById(int id) {
		TeacherBo teacherBo = new TeacherBo();
		TeacherDto dto = teacherMapper.selectByPrimaryKey(id);
		logger.info("teacherDto={},id={}",JSON.toJSONString(dto),id);
		BeanUtils.copyProperties(dto, teacherBo);
		UserDto user = userMapper.selectByPrimaryKey(dto.getUser_id());
		BeanUtils.copyProperties(user, teacherBo);
		teacherBo.setId(dto.getId());
		return teacherBo;
	}

	@Override
	public void delete(int id) {
		TeacherDto teacher = teacherMapper.selectByPrimaryKey(id);
		UserDto user = userMapper.selectByPrimaryKey(teacher.getUser_id());
		teacher.setValid(ValidHelper.NO.toInt());
		user.setValid(ValidHelper.NO.toInt());
		userMapper.updateByPrimaryKey(user);
		teacherMapper.updateByPrimaryKey(teacher);
	}

	@Override
	public TeacherDto selectByExample(TeacherDtoExample example) {
		return teacherMapper.selectByExample(example).get(0);
	}

	@Override
	public List<TeacherBo> queryAllTeacher() {
		TeacherDtoExample example = new TeacherDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		List<TeacherDto> dtos = teacherMapper.selectByExample(example);
		List<TeacherBo> list = new ArrayList<TeacherBo>();
		for (TeacherDto teacherDto : dtos) {
			TeacherBo bo = new TeacherBo();
			bo.setId(teacherDto.getId());
			UserDto user = userMapper.selectByPrimaryKey(teacherDto.getUser_id());
			bo.setUser_id(user.getId());
			bo.setUsername(user.getUsername());
			list.add(bo);
		}
		return list;
	}

	@Override
	public void updatePassword(TeacherPs teacherPs) {
		UserBo user = new UserBo();
		TeacherDto dto = teacherMapper.selectByPrimaryKey(teacherPs.getId());
		UserDto userDto = userMapper.selectByPrimaryKey(dto.getUser_id());
		user.setUsername(userDto.getUsername());
		user.setPassword(teacherPs.getPassword());
		PasswordHelper ps = new PasswordHelper();
		ps.encryptPassword(user);   //加密
		userDto.setPassword(user.getPassword());
		userMapper.updateByPrimaryKey(userDto);
	}

}
 