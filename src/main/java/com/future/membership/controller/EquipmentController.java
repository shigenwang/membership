package com.future.membership.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.EquipmentDto;
import com.future.membership.bean.EquipmentDtoExample;
import com.future.membership.bean.EquipmentTypeDto;
import com.future.membership.bean.EquipmentTypeDtoExample;
import com.future.membership.bean.bo.excel.EquipmentImExcel;
import com.future.membership.bean.reqsponse.EquipmentPage;
import com.future.membership.bean.request.EquipmentReq;
import com.future.membership.enums.ValidHelper;
import com.future.membership.service.EquipmentDtoService;
import com.future.membership.service.EquipmentTypeService;
import com.future.membership.util.ExcelUtils;
/**
 * 健身器材
 * @author tend
 *
 */
@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	private Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	private EquipmentDtoService equipmentDtoService;
	
	@Autowired
	private EquipmentTypeService typeService;
	
	@RequiresPermissions("equipment:save")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		map.addAttribute("eqTypeList",getAllType());
		return "equipment/save_UI";
	}
	
	@RequiresPermissions("equipment:update")
	@RequestMapping(value="/editEquipment/{id}/{currentPage}",method=RequestMethod.GET)
	public String editEquipment(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		EquipmentReq equipment = equipmentDtoService.selectById(id);
		map.addAttribute("equipment",equipment);
		map.addAttribute("eqTypeList",getAllType());
		map.addAttribute("currentPage",currentPage);
		return "equipment/update_UI";
	}
	
	@RequiresPermissions("equipment:save")
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String saveEquipment(ModelMap map,@ModelAttribute("equipment")EquipmentReq equipmentReq,@PathVariable("currentPage") int currentPage){
		logger.info("saveEquipment Equipment={}",equipmentReq);
		String tid = UUID.randomUUID().toString();
		equipmentDtoService.saveOrUpdateEquipment(equipmentReq,tid,false);
		map.addAttribute("currentPage",currentPage);
		logger.info("saveEquipment tid={},Equipment={}",tid,equipmentReq);
		return "redirect:/equipment/list/"+currentPage;
	}
	
	@RequiresPermissions("equipment:update")
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String updateEquipment(ModelMap map,@ModelAttribute("equipment")EquipmentReq equipmentReq,@PathVariable("currentPage") int currentPage){
		logger.info("updateEquipment Equipment={}",equipmentReq);
		map.addAttribute("currentPage",currentPage);
		String tid = UUID.randomUUID().toString();
		equipmentDtoService.saveOrUpdateEquipment(equipmentReq,tid,true);
		logger.info("updateEquipment tid={},Equipment={}",tid,equipmentReq);
		return "redirect:/equipment/list/"+currentPage;
	}
	
	@RequiresPermissions("equipment:delete")
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String deleteEquipment(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		String tid = UUID.randomUUID().toString();
		equipmentDtoService.delete(tid,id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/equipment/list/"+currentPage;
	}
	
	@RequiresPermissions("equipment:view")
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(@PathVariable("currentPage") int currentPage,ModelMap map){
		EquipmentPage equipmentPage = equipmentDtoService.queryList(currentPage,false);
		map.addAttribute("equipmentPage", equipmentPage);
		map.addAttribute("currentPage",currentPage);
		logger.info("equipmentPage={}",JSON.toJSONString(equipmentPage));
		return "equipment/equipment_list";
	}
	
	@RequestMapping(value="/vacant/{currentPage}",method=RequestMethod.GET)
	public String noUseList(@PathVariable("currentPage") int currentPage,ModelMap map){
		EquipmentPage equipmentPage = equipmentDtoService.queryList(currentPage,true);
		map.addAttribute("equipmentPage", equipmentPage);
		map.addAttribute("currentPage",currentPage);
		logger.info("equipmentPage={}",JSON.toJSONString(equipmentPage));
		return "equipment/equipment_list";
	}
	
	
	@RequestMapping(value="/imExcel",method=RequestMethod.POST)
	@ResponseBody
	public String imExcel(@RequestParam(value="excelFile",required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		logger.info("file.size={}",file.getSize());
		List<Object> list = ExcelUtils.excelForList(file, EquipmentImExcel.class, true);
		for (Object object : list) {
			EquipmentImExcel equipment = (EquipmentImExcel) object;
			System.out.println("equipment.getCreateTime="+ equipment.getCreateTime());
			logger.info("equipment={}",JSON.toJSONString(equipment));
			EquipmentDto dto = new EquipmentDto();
			dto.setDamage(Integer.parseInt(equipment.getDamage()));
			BeanUtils.copyProperties(equipment, dto);
			EquipmentTypeDtoExample example = new EquipmentTypeDtoExample();
			example.createCriteria().andNameEqualTo(equipment.getTypeName());
			EquipmentTypeDto typeDto = typeService.selectByExample(example).get(0);
			dto.setType_id(typeDto.getId());
			dto.setValid(ValidHelper.YES.toInt());
			EquipmentDtoExample example2 = new EquipmentDtoExample();
			example2.createCriteria().andCodeEqualTo(dto.getCode());
			List<EquipmentDto> dtos = equipmentDtoService.selectByExample(example2);
			if(CollectionUtils.isEmpty(dtos)){
				equipmentDtoService.insertSelective(dto);
			}else{
				dto.setId(dtos.get(0).getId());
				equipmentDtoService.updateByPrimaryKey(dto);
			}
			logger.info("dto={}",JSON.toJSONString(dto));
		}
		return null;
	}
	
	private List<EquipmentTypeDto> getAllType(){
		EquipmentTypeDtoExample example = new EquipmentTypeDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		return typeService.selectByExample(example);
	}
}
