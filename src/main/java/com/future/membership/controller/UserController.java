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
import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.reqsponse.UserPage;
import com.future.membership.service.UserService;
import com.future.membership.util.PasswordHelper;

@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		return "user/save_UI";
	}
	
	
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		UserBo user = userService.selectById(id);
		map.addAttribute("user",user);
		map.addAttribute("currentPage",currentPage);
		return "user/update_UI";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String save(ModelMap map,@PathVariable("currentPage") int currentPage,@ModelAttribute("user")UserBo user){
		logger.info("save user={}",JSON.toJSONString(user));
		userService.saveOrUpdateUser(user,false);
		map.addAttribute("currentPage",currentPage);
		logger.info("save user={}",user);
		return "";
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String update(ModelMap map,@PathVariable("currentPage") int currentPage,@ModelAttribute("user")UserBo user){
		logger.info("update user={}",JSON.toJSONString(user));
		userService.saveOrUpdateUser(user,true);
		map.addAttribute("currentPage",currentPage);
		logger.info("save user={}",user);
		return "";
	}
	
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(ModelMap map,@PathVariable("currentPage") int currentPage){
		UserPage userPage = userService.queryList(currentPage);
		logger.info("userPage={}",JSON.toJSONString(userPage));
		map.addAttribute("userPage",userPage);
		map.addAttribute("currentPage",currentPage);
		return "user/user_list";
	}
	
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String delete(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		userService.delete(id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/user/list/"+currentPage;
	}
	
	public static void main(String[] args) {
		UserBo user = new UserBo();
		user.setPassword("123456");
		String salt = "b15759287d8eba77ebfe6bcd44f68953";
		user.setSalt(salt);
		user.setUsername("admin");
		PasswordHelper pa = new PasswordHelper();
		pa.encryptPassword(user);
		System.out.println(user.getPassword());
	}
}
