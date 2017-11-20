package com.future.membership.service;

import java.io.OutputStream;
import java.util.List;

import com.future.membership.bean.EquipmentDto;
import com.future.membership.bean.EquipmentDtoExample;
import com.future.membership.bean.reqsponse.EquipmentPage;
import com.future.membership.bean.request.EquipmentReq;

public interface EquipmentDtoService {

	int saveOrUpdateEquipment(EquipmentReq equipmentReq,String tid,boolean update);

	boolean delete(String tid, int id);

	EquipmentPage queryList(int currentPage,boolean vacant);

	EquipmentReq selectById(int id);

	void download(OutputStream outputStream);

	List<EquipmentDto> selectByExample(EquipmentDtoExample example2);

	void insertSelective(EquipmentDto dto);

	void updateByPrimaryKey(EquipmentDto dto);

}
