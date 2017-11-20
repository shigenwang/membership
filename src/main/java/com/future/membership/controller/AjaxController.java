package com.future.membership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.future.membership.bean.Result;
import com.future.membership.service.UserService;

@Controller
@RequestMapping(value = "/system/ajax")
public class AjaxController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/userPassword/isExist",produces = "application/json")
	public Result isExist(Model model ,String password){
		Result result = new Result();
		boolean success = userService.isExistPassword(password);
		result.setSuccess(success);
		if (success) {
			result.setMessage("<font color='red'>登录名已存在</font>");
		}else{
			result.setMessage("<font color='green'>登录名合法</font>");
		}
		return result;
	}
}
