package com.future.membership.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.EquipmentDtoExample;
import com.future.membership.bean.bo.MaintainBo;
import com.future.membership.bean.reqsponse.MaintainPage;
import com.future.membership.enums.ValidHelper;
import com.future.membership.service.EquipmentDtoService;
import com.future.membership.service.MaintainService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**
 * 维护controller
 * @author tend
 *
 */
@Controller
@RequestMapping("/maintain")
public class MaintainController {

	private Logger logger = LoggerFactory.getLogger(MaintainController.class);
	
	@Autowired
	private MaintainService maintainService;
	
	@Autowired
	private EquipmentDtoService equipmentService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		EquipmentDtoExample example = new EquipmentDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		map.addAttribute("equipmentList",equipmentService.selectByExample(example));
		return "maintain/save_UI";
	}
	
	
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		MaintainBo maintainBo = maintainService.selectById(id);
		map.addAttribute("maintainBo",maintainBo);
		map.addAttribute("currentPage",currentPage);
		return "maintain/update_UI";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String save(ModelMap map,@ModelAttribute("maintain") MaintainBo maintainBo,@PathVariable("currentPage") int currentPage){
		logger.info("save maintainBo={}",JSON.toJSONString(maintainBo));
		maintainService.saveOrUpdateMaintain(maintainBo,false);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/maintain/list/"+currentPage;
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String update(ModelMap map,@ModelAttribute("maintain") MaintainBo maintainBo,@PathVariable("currentPage") int currentPage){
		logger.info("update maintainBo={}",JSON.toJSONString(maintainBo));
		maintainService.saveOrUpdateMaintain(maintainBo,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("update maintainBo={}",maintainBo);
		return "redirect:/maintain/list/"+currentPage;
	}
	
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(ModelMap map,@PathVariable("currentPage") int currentPage){
		MaintainPage maintainPage = maintainService.queryList(currentPage);
		map.addAttribute("maintainPage",maintainPage);
		map.addAttribute("currentPage",currentPage);
		return "maintain/maintain_list";
	}
	
	@RequestMapping(value="/delete/{id}/{currentPage}")
	public String delete(ModelMap map,@PathVariable("currentPage") int currentPage,@PathVariable("id") int id){
		maintainService.delete(id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/maintain/list/"+currentPage;
	}
}
