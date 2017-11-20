package com.future.membership.controller;

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
import com.future.membership.bean.bo.TeacherBo;
import com.future.membership.bean.reqsponse.TeacherPage;
import com.future.membership.bean.request.TeacherPs;
import com.future.membership.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	private Logger logger = LoggerFactory.getLogger(TeacherController.class);
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		return "teacher/save_UI";
	}
	
	
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		TeacherBo teacherBo = teacherService.selectById(id);
		logger.info("edit === teacherBo={}",JSON.toJSONString(teacherBo));
		map.addAttribute("teacher",teacherBo);
		map.addAttribute("currentPage",currentPage);
		return "teacher/update_UI";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String save(ModelMap map,@ModelAttribute("teacher") TeacherBo teacherBo,@PathVariable("currentPage") int currentPage){
		logger.info("save teacherBo={}",JSON.toJSONString(teacherBo));
		teacherService.saveOrUpdateTeacher(teacherBo,false);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/teacher/list/"+currentPage;
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String update(ModelMap map,@ModelAttribute("teacher") TeacherBo teacherBo,@PathVariable("currentPage") int currentPage){
		logger.info("update teacherBo={}",JSON.toJSONString(teacherBo));
		teacherService.saveOrUpdateTeacher(teacherBo,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("update teacherBo={}",teacherBo);
		return "redirect:/teacher/list/"+currentPage;
	}
	@RequestMapping(value="/update/{teacher_id}/{currentPage}",method=RequestMethod.POST)
	public String update2(ModelMap map,@ModelAttribute("teacher") TeacherBo teacherBo,@PathVariable("currentPage") int currentPage,@PathVariable("teacher_id") int teacher_id){
		logger.info("update teacherBo={}",JSON.toJSONString(teacherBo));
		teacherService.saveOrUpdateTeacher(teacherBo,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("update teacherBo={}",teacherBo);
		return "redirect:/teacher/list/"+teacher_id+"/"+currentPage;
	}
	
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(ModelMap map,@PathVariable("currentPage") int currentPage){
		TeacherPage teacherPage = teacherService.queryList(null,currentPage);
		logger.info("teacherPage={}",JSON.toJSONString(teacherPage));
		map.addAttribute("teacherPage",teacherPage);
		map.addAttribute("currentPage",currentPage);
		return "teacher/teacher_list";
	}
	@RequestMapping(value="/list/{teacher_id}/{currentPage}",method=RequestMethod.GET)
	public String list2(ModelMap map,@PathVariable("currentPage") int currentPage,@PathVariable("teacher_id") int teacher_id){
		TeacherPage teacherPage = teacherService.queryList(teacher_id,currentPage);
		logger.info("teacherPage={}",JSON.toJSONString(teacherPage));
		map.addAttribute("teacherPage",teacherPage);
		map.addAttribute("currentPage",currentPage);
		return "teacher/teacher_list";
	}
	@RequestMapping(value="/getOne/{teacher_id}",method=RequestMethod.GET)
	public String getOne(ModelMap map,@PathVariable("teacher_id") int teacher_id){
		TeacherBo teacher = teacherService.selectById(teacher_id);
		logger.info("teacher={]",JSON.toJSONString(teacher));
		map.addAttribute("teacher",teacher);
		map.addAttribute("currentPage",1);
		return "teacher/update_UI";
	}
	@RequestMapping(value="/upPassword/{teacher_id}",method=RequestMethod.GET)
	public String upPasswordUI(ModelMap map,@PathVariable("teacher_id") int teacher_id){
		TeacherBo teacher = teacherService.selectById(teacher_id);
		map.addAttribute("teacher",teacher);
		return "teacher/upPassword_UI";
	}
	@RequestMapping(value="/upPassword",method=RequestMethod.POST)
	public String upPassword(@ModelAttribute("teacherPs") TeacherPs teacherPs,ModelMap map){
		logger.info("teacher={}",JSON.toJSONString(teacherPs));
		teacherService.updatePassword(teacherPs);
		map.addAttribute("message","修改成功");
		return "message";
	}
	
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String delete(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		teacherService.delete(id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/teacher/list/"+currentPage;
	}
}
