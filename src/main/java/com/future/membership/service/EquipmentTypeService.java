package com.future.membership.service;

import java.util.List;

import com.future.membership.bean.EquipmentTypeDto;
import com.future.membership.bean.EquipmentTypeDtoExample;
import com.future.membership.bean.reqsponse.EquipmentTypePage;
import com.future.membership.bean.reqsponse.EquipmentTypeReqs;
import com.future.membership.bean.request.EquipmentTypeBo;

public interface EquipmentTypeService {

	int saveOrUpdateEquipmentType(EquipmentTypeBo equipmentType, String tid, boolean update);

	boolean delete(String tid, int id);

	EquipmentTypePage queryList(int currentPage);

	List<EquipmentTypeDto> selectByExample(EquipmentTypeDtoExample example);

	EquipmentTypeReqs selectById(int id);

}
