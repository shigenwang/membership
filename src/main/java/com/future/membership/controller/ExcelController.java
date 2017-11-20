package com.future.membership.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ExcelController {

	@RequestMapping(value="/imExcel",method=RequestMethod.POST)
	@ResponseBody
	public String imExcel(@RequestParam(value="excelFile",required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response){
		
		return "";
	}
}
