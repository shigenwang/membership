package com.future.membership.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.reqsponse.EquipmentTypePage;
import com.future.membership.bean.reqsponse.EquipmentTypeReqs;
import com.future.membership.bean.request.EquipmentTypeBo;
import com.future.membership.service.EquipmentTypeService;
/**
 * 健身器材类型
 * @author tend
 *
 */
@Controller
@RequestMapping("/equipmentType")
public class EquipmentTypeController {

	private Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	
	@Autowired
	private EquipmentTypeService equipmentTypeService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUI(ModelMap map){
		
		return "/equipmentType/save_UI";
	}
	
	@RequestMapping(value="/edit/{id}/{currentPage}")
	public String eidtUI(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		EquipmentTypeReqs typeReqs = equipmentTypeService.selectById(id);
		map.addAttribute("equipmentType",typeReqs);
		map.addAttribute("currentPage",currentPage);
		return "/equipmentType/update_UI";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String saveEquipmentType(ModelMap map,@ModelAttribute("equipmentType")EquipmentTypeBo equipmentType,@PathVariable("currentPage") int currentPage){
		logger.info("saveEquipmentType equipmentType={}",equipmentType);
		String tid = UUID.randomUUID().toString();
		equipmentTypeService.saveOrUpdateEquipmentType(equipmentType,tid,false);
		map.addAttribute("currentPage",currentPage);
		logger.info("saveEquipmentType tid={},equipmentType={}",tid,equipmentType);
		return "redirect:/equipmentType/list/"+currentPage;
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String updateEquipmentType(ModelMap map,@ModelAttribute("equipmentType")EquipmentTypeBo equipmentType,@PathVariable("currentPage") int currentPage){
		logger.info("updateEquipmentType equipmentType={}",equipmentType);
		String tid = UUID.randomUUID().toString();
		equipmentTypeService.saveOrUpdateEquipmentType(equipmentType,tid,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("updateEquipmentType tid={},equipmentType={}",tid,equipmentType);
		return "redirect:/equipmentType/list/"+currentPage;
	}
	
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String delete(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		String tid = UUID.randomUUID().toString();
		equipmentTypeService.delete(tid,id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/equipmentType/list/"+currentPage;
	}
	
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(@PathVariable("currentPage") int currentPage,ModelMap map){
		EquipmentTypePage etypePage = equipmentTypeService.queryList(currentPage);
		map.addAttribute("etypePage", etypePage);
		map.addAttribute("currentPage",currentPage);
		logger.info("etypePage={}",JSON.toJSONString(etypePage));
		return "/equipmentType/equipmentType_list";
	}
	
}
