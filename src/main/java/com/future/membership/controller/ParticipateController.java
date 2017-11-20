package com.future.membership.controller;

import java.util.List;

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
import com.future.membership.bean.CourseDto;
import com.future.membership.bean.bo.ParticipateBo;
import com.future.membership.bean.reqsponse.ParticipatePage;
import com.future.membership.service.CourseService;
import com.future.membership.service.ParticipateService;
/**
 * 私教controller
 * @author tend
 *
 */
@Controller
@RequestMapping("/participate")
public class ParticipateController {

	
	private Logger logger = LoggerFactory.getLogger(ParticipateController.class);
	
	@Autowired
	private ParticipateService participateService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/add/{membership_id}",method=RequestMethod.GET)
	public String add(ModelMap map,@PathVariable("membership_id") int membership_id){
		List<CourseDto> courseList = courseService.queryAll(membership_id);
		logger.info("courseList={}",JSON.toJSONString(courseList));
		map.addAttribute("courseList",courseList);
		return "participate/save_UI";
	}
	
	
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		ParticipateBo participateBo = participateService.selectById(id);
		map.addAttribute("participateBo",participateBo);
		map.addAttribute("currentPage",currentPage);
		return "participate/update_UI";
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String update(ModelMap map,@ModelAttribute("participate") ParticipateBo participate,@PathVariable("currentPage") int currentPage){
		logger.info("update participate={}",JSON.toJSONString(participate));
		participateService.saveOrUpdate(participate,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("update ptBo={}",participate);
		return "";
	} 
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String save(ModelMap map,@ModelAttribute("participate") ParticipateBo participate,@PathVariable("currentPage") int currentPage){
		logger.info("save participate={}",JSON.toJSONString(participate));
		participateService.saveOrUpdate(participate,false);
		map.addAttribute("currentPage",currentPage);
		logger.info("save participate={}",participate);
		return "redirect:/participate/list/"+participate.getMembership_id()+"/"+currentPage;
	}
	
	@RequestMapping(value="/list/{membership_id}/{currentPage}",method=RequestMethod.GET)
	public String list(ModelMap map,@PathVariable("membership_id") int membership_id,@PathVariable("currentPage") int currentPage){
		ParticipatePage participatePage = participateService.queryList(membership_id,currentPage);
		map.addAttribute("participatePage",participatePage);
		map.addAttribute("currentPage",currentPage);
		return "participate/participate_list";
	}
}
