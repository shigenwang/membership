package com.future.membership.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.future.membership.bean.reqsponse.CoursePage;
import com.future.membership.bean.request.CourseReq;
import com.future.membership.service.CourseService;
import com.future.membership.service.TeacherService;

@Controller
@RequestMapping("/course")
public class CourseController {

	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequiresPermissions("course:view")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		List<TeacherBo> teacherList = teacherService.queryAllTeacher();
		logger.info("teacherList={}",JSON.toJSONString(teacherList));
		map.addAttribute("teacherList",teacherList);
		return "course/save_UI";
	}
	
	@RequiresPermissions("course:update")
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		CourseReq course = courseService.selectById(id);
		List<TeacherBo> teacherList = teacherService.queryAllTeacher();
		logger.info("teacherList={}",JSON.toJSONString(teacherList));
		map.addAttribute("teacherList",teacherList);
		map.addAttribute("course",course);
		map.addAttribute("currentPage",currentPage);
		return "course/update_UI";
	}
	@RequiresPermissions("course:save")
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String saveCourse(ModelMap map,@ModelAttribute("course") CourseReq course,@PathVariable("currentPage") int currentPage){
		logger.info("save course={}",JSON.toJSONString(course));
		courseService.saveOrUpdateCourse(course,false);
		map.addAttribute("currentPage",currentPage);
		logger.info("save course={}",course);
		return "redirect:/course/list/"+currentPage;
	}
	
	@RequestMapping(value="/save/{teacher_id}/{currentPage}",method=RequestMethod.POST)
	public String saveCourse2(ModelMap map,@ModelAttribute("course") CourseReq course,@PathVariable("currentPage") int currentPage,@PathVariable("teacher_id") int teacher_id){
		logger.info("save course={}",JSON.toJSONString(course));
		courseService.saveOrUpdateCourse(course,false);
		map.addAttribute("currentPage",currentPage);
		logger.info("save course={}",course);
		return "redirect:/course/list/"+teacher_id+"/"+currentPage;
	}
	
	@RequiresPermissions("course:update")
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String updateCourse(ModelMap map,@ModelAttribute("course") CourseReq course,@PathVariable("currentPage") int currentPage){
		logger.info("update course={}",JSON.toJSONString(course));
		courseService.saveOrUpdateCourse(course,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("save course={}",course);
		return "redirect:/course/list/"+currentPage;
	}
	
	@RequestMapping(value="/update/{teacher_id}/{currentPage}",method=RequestMethod.POST)
	public String updateCourse2(ModelMap map,@ModelAttribute("course") CourseReq course,@PathVariable("currentPage") int currentPage,@PathVariable("teacher_id") int teacher_id){
		logger.info("update course={}",JSON.toJSONString(course));
		courseService.saveOrUpdateCourse(course,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("save course={}",course);
		return "redirect:/course/list/"+teacher_id+"/"+currentPage;
	}
	
	@RequiresPermissions("course:view")
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(ModelMap map,@PathVariable("currentPage") int currentPage){
		CoursePage coursePage = courseService.queryList(-1,currentPage);
		logger.info("coursePage={}",JSON.toJSONString(coursePage));
		map.addAttribute("coursePage",coursePage);
		map.addAttribute("currentPage",currentPage);
		return "course/course_list";
	}
	
	@RequestMapping(value="/list/{user_id}/{currentPage}",method=RequestMethod.GET)
	public String listCourse(ModelMap map,@PathVariable("currentPage") int currentPage,@PathVariable("user_id")int user_id){
		CoursePage coursePage = courseService.queryList(user_id,currentPage);
		logger.info("coursePage={}",JSON.toJSONString(coursePage));
		map.addAttribute("coursePage",coursePage);
		map.addAttribute("currentPage",currentPage);
		return "course/course_list";
	}
	
	@RequiresPermissions("course:delete")
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String delete(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		courseService.delete(id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/course/list/"+currentPage;
	}
}
