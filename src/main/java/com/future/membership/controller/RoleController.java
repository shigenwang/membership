package com.future.membership.controller;

import java.util.List;
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
import com.future.membership.bean.RoleDto;
import com.future.membership.bean.request.RoleReq;
import com.future.membership.service.RoleService;
/**
 * role
 * @author tend
 *
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/saveRole",method=RequestMethod.POST)
	public String saveRole(@ModelAttribute("role")RoleReq role){
		logger.info("saverole role={}",role);
		String tid = UUID.randomUUID().toString();
		roleService.saveOrUpdateRole(role,tid,false);
		logger.info("saverole tid={},role={}",tid,role);
		return "";
	}
	
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	public String updateRole(@ModelAttribute("role")RoleReq role){
		logger.info("updateRole role={}",role);
		String tid = UUID.randomUUID().toString();
		roleService.saveOrUpdateRole(role,tid,true);
		logger.info("updateRole tid={},role={}",tid,role);
		return "";
	}
	
	@RequestMapping(value="/deleteRole",method=RequestMethod.DELETE)
	public String deleteRole(@PathVariable("id") int id){
		String tid = UUID.randomUUID().toString();
		roleService.delete(tid,id);
		return "";
	}
	
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(@PathVariable("currentPage") int currentPage,ModelMap map){
		List<RoleDto> dtos = roleService.queryList(currentPage);
		map.addAttribute("roleList", dtos);
		logger.info("roleList={}",JSON.toJSONString(dtos));
		return "";
	}
}
