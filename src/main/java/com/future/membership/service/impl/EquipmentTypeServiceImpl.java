package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.future.membership.bean.EquipmentTypeDto;
import com.future.membership.bean.EquipmentTypeDtoExample;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.EquipmentTypePage;
import com.future.membership.bean.reqsponse.EquipmentTypeReqs;
import com.future.membership.bean.request.EquipmentTypeBo;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.EquipmentTypeDtoMapper;
import com.future.membership.service.EquipmentTypeService;

@Service
@Transactional
public class EquipmentTypeServiceImpl implements EquipmentTypeService{

	private Logger logger = LoggerFactory.getLogger(EquipmentTypeServiceImpl.class);
	
	@Autowired
	private EquipmentTypeDtoMapper equipmentTypeDtoMapper;
	
	@Override
	public int saveOrUpdateEquipmentType(EquipmentTypeBo equipmentType, String tid, boolean update) {
		if(equipmentType != null){
			EquipmentTypeDto newEquipmentTypeDto = new EquipmentTypeDto();
			newEquipmentTypeDto.setName(equipmentType.getName());
			if(equipmentType.getId()!=null){
				newEquipmentTypeDto.setId(equipmentType.getId());
			}
			if(equipmentType.getDescription()!=null)
				newEquipmentTypeDto.setDescription(equipmentType.getDescription());
			if(equipmentType.getValid()!=null){
				newEquipmentTypeDto.setValid(equipmentType.getValid());  
			}else{
				newEquipmentTypeDto.setValid(ValidHelper.YES.toInt()); //默认可用
			}
			int i = -1;
			if(update){  //修改
				i = equipmentTypeDtoMapper.updateByPrimaryKeySelective(newEquipmentTypeDto);
			}else{
				i = equipmentTypeDtoMapper.insertSelective(newEquipmentTypeDto);
			}
			logger.info("saveorUpdateEquipment tid={},id={}",tid,i);
			return i;
		}
		return -1;
	}

	@Override
	public boolean delete(String tid, int id) {
		EquipmentTypeDtoExample example = new EquipmentTypeDtoExample();
		example.createCriteria().andIdEqualTo(id).andValidEqualTo(ValidHelper.YES.toInt());
		List<EquipmentTypeDto> equipmentTypeDtos = equipmentTypeDtoMapper.selectByExample(example);
		
		EquipmentTypeDto equipmentTypeDto = null;
		int i = 0;
		if(equipmentTypeDtos!=null){
			equipmentTypeDto = equipmentTypeDtos.get(0);
			equipmentTypeDto.setValid(ValidHelper.NO.toInt());  //逻辑删除
			i = equipmentTypeDtoMapper.updateByPrimaryKeySelective(equipmentTypeDto);
		}
		
		return i>0?true:false;
	}

	@Override
	public EquipmentTypePage queryList(int currentPage) {
		EquipmentTypeDtoExample example = new EquipmentTypeDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		int count = equipmentTypeDtoMapper.countByExample(example);
		EquipmentTypePage equipmentTypePage = new EquipmentTypePage();
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<EquipmentTypeDto> dtoList = equipmentTypeDtoMapper.selectByExample(example);
		List<EquipmentTypeReqs> eqTypes = new ArrayList<EquipmentTypeReqs>();
		if(!CollectionUtils.isEmpty(dtoList)){
			for (EquipmentTypeDto equipmentTypeDto : dtoList) {
				EquipmentTypeReqs eqTypeReqs = new EquipmentTypeReqs();
				eqTypeReqs.setId(equipmentTypeDto.getId());
				eqTypeReqs.setName(equipmentTypeDto.getName());
				eqTypeReqs.setValid(equipmentTypeDto.getValid());
				eqTypeReqs.setDescription(equipmentTypeDto.getDescription());
				eqTypes.add(eqTypeReqs);
			}
		}
		equipmentTypePage.setPage(page);
		equipmentTypePage.setList(eqTypes);
		return equipmentTypePage;
	}

	@Override
	public List<EquipmentTypeDto> selectByExample(EquipmentTypeDtoExample example) {
		return equipmentTypeDtoMapper.selectByExample(example);
	}

	@Override
	public EquipmentTypeReqs selectById(int id) {
		EquipmentTypeReqs typeReqs = new EquipmentTypeReqs();
		EquipmentTypeDto dto = equipmentTypeDtoMapper.selectByPrimaryKey(id);
		typeReqs.setDescription(dto.getDescription());
		typeReqs.setId(dto.getId());
		typeReqs.setName(dto.getName());
		typeReqs.setValid(dto.getValid());
		return typeReqs;
	}

}
