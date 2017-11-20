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
import com.future.membership.bean.reqsponse.EquipmentPage;
import com.future.membership.bean.reqsponse.GymPage;
import com.future.membership.bean.reqsponse.GymResponse;
import com.future.membership.bean.request.GymReq;
import com.future.membership.service.EquipmentDtoService;
import com.future.membership.service.GymService;
/**
 * 健身房controller
 * @author tend
 *
 */
@Controller
@RequestMapping(value="gym")
public class GymController {

	private Logger logger = LoggerFactory.getLogger(GymController.class);
	
	@Autowired
	private GymService gymService;
	
	private EquipmentDtoService equipmentDtoService;
	
	@RequestMapping(value="/addEquipment/{id}/{currentPage}")
	public String addEquipment(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		EquipmentPage equipmentPage = equipmentDtoService.queryList(currentPage,false);
		map.addAttribute("equipmentPage", equipmentPage);
		map.addAttribute("currentPage",currentPage);
		logger.info("equipmentPage={}",JSON.toJSONString(equipmentPage));
		return "";
	}
	
	@RequestMapping(value="/equipmentList/{id}/{currentPage}")
	public String equipmentList(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		GymResponse response = gymService.getGymResp(id,currentPage);
		map.addAttribute("gymResponse",response);
		map.addAttribute("currentPage",currentPage);
		logger.info("gymResponse={}",JSON.toJSONString(response));
		return "redirect:/gym/details/"+id+"/"+currentPage;
	}
	
	
	
	@RequestMapping(value="/details/{id}/{currentPage}")
	public String details(ModelMap map,@PathVariable("id") int id, @PathVariable("currentPage") int currentPage){
		GymResponse response = gymService.getGymResp(id,currentPage);
		map.addAttribute("gymResponse",response);
		map.addAttribute("currentPage",currentPage);
		logger.info("gymResponse={}",JSON.toJSONString(response));
		return "gym/detail";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		
		return "/gym/save_UI";
	}
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		map.addAttribute("currentPage",currentPage);
		GymReq gym = gymService.findById(id);
		map.addAttribute("gym",gym);
		return "/gym/update_UI";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String saveGym(ModelMap map,@ModelAttribute("gym")GymReq gym,@PathVariable("currentPage") int currentPage){
		logger.info("saveGym gym={}",gym);
		String tid = UUID.randomUUID().toString();
		map.addAttribute("currentPage",currentPage);
		gymService.saveOrUpdateGym(gym,tid,false);
		logger.info("savegym tid={},gym={}",tid,gym);
		return "redirect:/gym/list/"+currentPage;
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String updateGym(ModelMap map,@ModelAttribute("gym")GymReq gym,@PathVariable("currentPage") int currentPage){
		logger.info("updateGym gym={}",gym);
		String tid = UUID.randomUUID().toString();
		map.addAttribute("currentPage",currentPage);
		gymService.saveOrUpdateGym(gym,tid,true);
		logger.info("updategym tid={},gym={}",tid,gym);
		return "redirect:/gym/list/"+currentPage;
	}
	
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String deleteGym(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		String tid = UUID.randomUUID().toString();
		gymService.delete(tid,id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/gym/list/"+currentPage;
	}
	
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String queryGymList(@PathVariable("currentPage") int currentPage,ModelMap map){
		GymPage gymPage = gymService.queryList(currentPage);
		map.addAttribute("gymPage", gymPage);
		map.addAttribute("currentPage",currentPage);
		logger.info("gymPage={}",JSON.toJSONString(gymPage));
		return "/gym/gym_list";
	}
	
}
