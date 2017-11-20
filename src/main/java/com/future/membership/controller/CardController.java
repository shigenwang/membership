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
import com.future.membership.bean.CardDto;
import com.future.membership.bean.reqsponse.CardPage;
import com.future.membership.service.CardService;
import com.future.membership.service.MembershipService;

@Controller
@RequestMapping("/card")
public class CardController {

	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private CardService cardService;
	
	@Autowired
	private MembershipService membershipService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(ModelMap map){
		return "card/save_UI";
	}
	
	
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		CardDto card = cardService.selectById(id);
		map.addAttribute("card",card);
		map.addAttribute("currentPage",currentPage);
		return "card/update_UI";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String save(ModelMap map,@ModelAttribute("card") CardDto card,@PathVariable("currentPage") int currentPage){
		logger.info("save card={}",JSON.toJSONString(card));
		cardService.saveOrUpdateCard(card,false);
		map.addAttribute("membershipList",membershipService.queryAll());
		map.addAttribute("currentPage",currentPage);
		logger.info("save card={}",card);
		return "";
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String updateCourse(ModelMap map,@ModelAttribute("card") CardDto card,@PathVariable("currentPage") int currentPage){
		logger.info("update card={}",JSON.toJSONString(card));
		cardService.saveOrUpdateCard(card,true);
		map.addAttribute("membershipList",membershipService.queryAll());
		map.addAttribute("currentPage",currentPage);
		logger.info("save card={}",card);
		return "";
	}
	@RequestMapping(value="/list/{currentPage}",method=RequestMethod.GET)
	public String list(ModelMap map,@PathVariable("currentPage") int currentPage){
		CardPage coursePage = cardService.queryList(currentPage);
		map.addAttribute("coursePage",coursePage);
		map.addAttribute("membershipList",membershipService.queryAll());
		map.addAttribute("currentPage",currentPage);
		return "card/card_list";
	}
}
