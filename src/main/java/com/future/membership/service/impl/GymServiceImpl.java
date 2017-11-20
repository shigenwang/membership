package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.future.membership.bean.EquipmentDto;
import com.future.membership.bean.EquipmentDtoExample;
import com.future.membership.bean.EquipmentTypeDto;
import com.future.membership.bean.GymDto;
import com.future.membership.bean.GymDtoExample;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.EquipmentPage;
import com.future.membership.bean.reqsponse.EquipmentReqs;
import com.future.membership.bean.reqsponse.GymPage;
import com.future.membership.bean.reqsponse.GymResponse;
import com.future.membership.bean.request.GymReq;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.EquipmentDtoMapper;
import com.future.membership.mapper.EquipmentTypeDtoMapper;
import com.future.membership.mapper.GymDtoMapper;
import com.future.membership.service.GymService;
@Service
@Transactional
public class GymServiceImpl implements GymService{

	private Logger logger = LoggerFactory.getLogger(GymServiceImpl.class);
	
	@Autowired
	private GymDtoMapper gymDtoMapper;
	
	@Autowired
	private EquipmentDtoMapper equipmentDtoMapper;
	
	@Autowired
	private EquipmentTypeDtoMapper typeDtoMapper;
	
	@Override
	public int saveOrUpdateGym(GymReq gym, String tid, boolean update) {
		if(gym != null){
			GymDto newGymDto = new GymDto();
			newGymDto.setName(gym.getName());
			if(gym.getId()!=null){
				newGymDto.setId(gym.getId());
			}
			if(gym.getRoomNumber()!=null){
				newGymDto.setRoomNumber(gym.getRoomNumber());
			}
			if(gym.getDescription()!=null)
				newGymDto.setDescription(gym.getDescription());
			if(gym.getValid()!=null){
				newGymDto.setValid(gym.getValid());  
			}else{
				newGymDto.setValid(ValidHelper.YES.toInt()); //默认可用
			}
			int i = -1;
			if(update){  //修改
				i = gymDtoMapper.updateByPrimaryKeySelective(newGymDto);
			}else{
				i = gymDtoMapper.insertSelective(newGymDto);
			}
			logger.info("saveorUpdateGym tid={},id={}",tid,i);
			return i;
		}
		return -1;
	}

	@Override
	public boolean delete(String tid, int id) {
		GymDtoExample example = new GymDtoExample();
		example.createCriteria().andIdEqualTo(id).andValidEqualTo(ValidHelper.YES.toInt());
		List<GymDto> gymDtos = gymDtoMapper.selectByExample(example);
		
		GymDto gymDto = null;
		int i = 0;
		if(gymDtos!=null){
			gymDto = gymDtos.get(0);
			gymDto.setValid(ValidHelper.NO.toInt());  //逻辑删除
			i = gymDtoMapper.updateByPrimaryKeySelective(gymDto);
		}
		
		return i>0?true:false;
	}

	@Override
	public GymResponse getGym(String tid, int id) {
		GymDtoExample example = new GymDtoExample();
		example.createCriteria().andIdEqualTo(id);
		List<GymDto> list = gymDtoMapper.selectByExample(example);
		GymDto gymDto = null;
		if(!list.isEmpty()){
			gymDto = list.get(0);
		}
		GymResponse gymResponse = new GymResponse();
		//此处复制from GymDto to GymResponse
		gymResponse.setId(gymDto.getId());
		gymResponse.setName(gymDto.getName());
		
		return gymResponse;
	}

	@Override
	public GymPage queryList(int currentPage) {
		GymDtoExample example = new GymDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		int count = gymDtoMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<GymDto> dtoList = gymDtoMapper.selectByExample(example);
		GymPage gymPage = new GymPage();
		List<GymReq> gymList = new ArrayList<GymReq>();
		if(!CollectionUtils.isEmpty(dtoList)){
			for (GymDto gymDto : dtoList) {
				GymReq gymReq = new GymReq();
				gymReq.setDescription(gymDto.getDescription());
				gymReq.setId(gymDto.getId());
				gymReq.setName(gymDto.getName());
				gymReq.setRoomNumber(gymDto.getRoomNumber());
				gymReq.setValid(gymDto.getValid());
				gymList.add(gymReq);
			}
		}
		gymPage.setList(gymList);
		gymPage.setPage(page);
		return gymPage;
	}

	@Override
	public GymReq findById(int id) {
		GymReq gymReq = new GymReq();
		EquipmentDtoExample example = new EquipmentDtoExample();
		example.createCriteria().andGym_idEqualTo(id);
		int count = equipmentDtoMapper.countByExample(example);
		gymReq.setEquipmentSize(count);
		GymDto gym = gymDtoMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(gym, gymReq);
		return gymReq;
	}

	@Override
	public GymResponse getGymResp(int id,int currentPage) {
		GymResponse gymResp = new GymResponse();
		GymDto gym = gymDtoMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(gym, gymResp);
		EquipmentDtoExample example = new EquipmentDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt()).andGym_idEqualTo(id);
		int count = equipmentDtoMapper.countByExample(example);
		int pageSize = 1;   //默认10页
		String orderKeyStr = "id";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<EquipmentDto> dtoList = equipmentDtoMapper.selectByExample(example);
		List<EquipmentReqs> eqList = new ArrayList<EquipmentReqs>();
		if(!CollectionUtils.isEmpty(dtoList)){
			for (EquipmentDto equipmentDto : dtoList) {
				EquipmentReqs eqreqs = new EquipmentReqs();
				eqreqs.setCode(equipmentDto.getCode());
				eqreqs.setId(equipmentDto.getId());
				EquipmentTypeDto eqType = typeDtoMapper.selectByPrimaryKey(equipmentDto.getType_id());
				eqreqs.setType(eqType);
				eqreqs.setName(equipmentDto.getName());
				eqreqs.setValid(equipmentDto.getValid());
				eqreqs.setDescription(equipmentDto.getDescription());
				eqList.add(eqreqs);
			}
		}
		EquipmentPage equipmentPage = new EquipmentPage();
		equipmentPage.setList(eqList);
		equipmentPage.setPage(page);
		gymResp.setPage(equipmentPage);
		return gymResp;
	}
}
