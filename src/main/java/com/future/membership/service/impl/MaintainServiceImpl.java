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
import com.future.membership.bean.EquipmentDto;
import com.future.membership.bean.MaintainDto;
import com.future.membership.bean.MaintainDtoExample;
import com.future.membership.bean.bo.MaintainBo;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.MaintainPage;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.EquipmentDtoMapper;
import com.future.membership.mapper.EquipmentTypeDtoMapper;
import com.future.membership.mapper.MaintainDtoMapper;
import com.future.membership.service.MaintainService;
import com.future.membership.util.DateUtil;

@Service
@Transactional
public class MaintainServiceImpl implements MaintainService{

	private Logger logger = LoggerFactory.getLogger(MaintainServiceImpl.class);
	
	@Autowired
	private MaintainDtoMapper maintainMapper;
	
	@Autowired
	private EquipmentDtoMapper equipmentMapper;
	
	@Autowired
	private EquipmentTypeDtoMapper equipmentTypeMapper;
	
	@Override
	public MaintainPage queryList(int currentPage) {
		MaintainDtoExample example = new MaintainDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		int count = maintainMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<MaintainDto> maintainDtos = maintainMapper.selectByExample(example);
		List<MaintainBo> bos = new ArrayList<MaintainBo>();
		for (MaintainDto maintainDto : maintainDtos) {
			MaintainBo maintainBo = new MaintainBo();
			BeanUtils.copyProperties(maintainDto, maintainBo);
			maintainBo.setMaintenance_time(DateUtil.formatYYMMDDHH(maintainDto.getMaintenance_time()));
			EquipmentDto equipment = equipmentMapper.selectByPrimaryKey(maintainDto.getEquipment_id());
			maintainBo.setEquipment_code(equipment.getCode());
			maintainBo.setEquipment_name(equipment.getName());
			bos.add(maintainBo);
		}
		MaintainPage maintainPage = new MaintainPage();
		maintainPage.setList(bos);
		maintainPage.setPage(page);
		return maintainPage;
	}
	@Override
	public int saveOrUpdateMaintain(MaintainBo maintainBo, boolean update) {
		int i = -1;
		if(maintainBo!=null){
			MaintainDto dto = new MaintainDto();
			EquipmentDto equipment = equipmentMapper.selectByPrimaryKey(maintainBo.getEquipment_id());
			dto.setEquipment_id(maintainBo.getEquipment_id());
			dto.setMauntauners(maintainBo.getMauntauners());
			dto.setQu_type(equipmentTypeMapper.selectByPrimaryKey(equipment.getType_id()).getName());
			dto.setQu_create(equipment.getCreateTime());
			dto.setMaintenance_time(DateUtil.toDate(maintainBo.getMaintenance_time()));
			dto.setValid(ValidHelper.YES.toInt());
			logger.info("miantainDto={}",JSON.toJSONString(dto));
			if(update){
				i = maintainMapper.updateByPrimaryKey(dto);
			}else{
				i = maintainMapper.insertSelective(dto);
			}
		}
		return i;
	}
	@Override
	public MaintainBo selectById(int id) {
		MaintainBo maintainBo = new MaintainBo();
		MaintainDto dto = maintainMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(dto, maintainBo);
		return maintainBo;
	}
	@Override
	public int delete(int id) {
		MaintainDto maintain = maintainMapper.selectByPrimaryKey(id);
		maintain.setValid(ValidHelper.NO.toInt());
		return maintainMapper.updateByPrimaryKey(maintain);
	}

}
