package com.future.membership.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.future.membership.bean.bo.excel.EquipmentExcel;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.EquipmentPage;
import com.future.membership.bean.reqsponse.EquipmentReqs;
import com.future.membership.bean.request.EquipmentReq;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.EquipmentDtoMapper;
import com.future.membership.mapper.EquipmentTypeDtoMapper;
import com.future.membership.service.EquipmentDtoService;
import com.future.membership.util.ExcelUtils;

@Service
@Transactional
public class EquipmentDtoServiceImpl implements EquipmentDtoService {

	private Logger logger = LoggerFactory.getLogger(EquipmentDtoServiceImpl.class);

	@Autowired
	private EquipmentDtoMapper equipmentDtoMapper;

	@Autowired
	private EquipmentTypeDtoMapper typeDtoMapper;

	public int saveOrUpdateEquipment(EquipmentReq equipmentReq, String tid, boolean update) {

		if (equipmentReq != null) {
			EquipmentDto newEquipmentDto = new EquipmentDto();
			newEquipmentDto.setName(equipmentReq.getName());
			newEquipmentDto.setType_id(equipmentReq.getType_id());

			if (equipmentReq.getId() != null) {
				newEquipmentDto.setId(equipmentReq.getId());
			}
			if (equipmentReq.getDescription() != null) {
				newEquipmentDto.setDescription(equipmentReq.getDescription());
			}
			if (equipmentReq.getCode() != null) {
				newEquipmentDto.setCode(equipmentReq.getCode());
			}
			if (equipmentReq.getValid() != null) {
				newEquipmentDto.setValid(equipmentReq.getValid());
			} else {
				newEquipmentDto.setValid(ValidHelper.YES.toInt()); // 默认可用
			}
			int i = 0;
			if (update) { // 修改
				i = equipmentDtoMapper.updateByPrimaryKeySelective(newEquipmentDto);
			} else {
				i = equipmentDtoMapper.insertSelective(newEquipmentDto);
			}
			logger.info("saveorUpdateEquipment tid={},id={}", tid, i);
			return i;
		}
		return -1;
	}

	public boolean delete(String tid, int id) {
		EquipmentDtoExample example = new EquipmentDtoExample();
		example.createCriteria().andIdEqualTo(id).andValidEqualTo(ValidHelper.YES.toInt());
		List<EquipmentDto> equipmentDtos = equipmentDtoMapper.selectByExample(example);

		EquipmentDto equipmentDto = null;
		int i = 0;
		if (equipmentDtos != null) {
			equipmentDto = equipmentDtos.get(0);
			equipmentDto.setValid(ValidHelper.NO.toInt()); // 逻辑删除
			i = equipmentDtoMapper.updateByPrimaryKeySelective(equipmentDto);
		}

		return i > 0 ? true : false;
	}

	public EquipmentPage queryList(int currentPage, boolean vacant) {
		EquipmentDtoExample example = new EquipmentDtoExample();
		if (vacant) {
			example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt()).andGym_idIsNull();
		} else {
			example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		}
		int count = equipmentDtoMapper.countByExample(example);
		int pageSize = 10; // 默认10页
		String orderKeyStr = "id desc"; // 默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize, count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<EquipmentDto> dtoList = equipmentDtoMapper.selectByExample(example);
		List<EquipmentReqs> eqList = new ArrayList<EquipmentReqs>();
		if (!CollectionUtils.isEmpty(dtoList)) {
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
		return equipmentPage;
	}

	public EquipmentReq selectById(int id) {
		EquipmentReq equipmentReq = new EquipmentReq();
		EquipmentDto equipmentDto = equipmentDtoMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(equipmentDto, equipmentReq);
		return equipmentReq;
	}

	public void download(OutputStream outputStream) {
		// 自定义
		List<EquipmentExcel> list = new ArrayList<EquipmentExcel>();
		EquipmentDtoExample example = new EquipmentDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		List<EquipmentDto> dtos = equipmentDtoMapper.selectByExample(example);
		for (EquipmentDto equipmentDto : dtos) {
			EquipmentExcel excel = new EquipmentExcel();
			BeanUtils.copyProperties(equipmentDto, excel);
			EquipmentTypeDto type = typeDtoMapper.selectByPrimaryKey(equipmentDto.getType_id());
			if (type != null) {
				excel.setTypeId(type.getId());
				excel.setType(type.getName());
			}
			list.add(excel);
		}

		HSSFWorkbook workBook = ExcelUtils.createWorkBook();
		HSSFSheet sheet = ExcelUtils.createSheet(workBook, "test");
		String[] headers = new String[] { "ID", "器材名", "编号", "描述", "类型ID", "类型名称" };
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
	public List<EquipmentDto> selectByExample(EquipmentDtoExample example2) {
		return equipmentDtoMapper.selectByExample(example2);
	}

	@Override
	public void insertSelective(EquipmentDto dto) {
		equipmentDtoMapper.insertSelective(dto);
	}

	@Override
	public void updateByPrimaryKey(EquipmentDto dto) {
		equipmentDtoMapper.updateByPrimaryKey(dto);
	}
}
