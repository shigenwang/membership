package com.future.membership.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
import com.future.membership.bean.MembershipLevelDto;
import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.reqsponse.MembershipPage;
import com.future.membership.bean.reqsponse.MembershipResp;
import com.future.membership.bean.request.MembershipPassWord;
import com.future.membership.bean.request.MembershipReq;
import com.future.membership.service.MembershipLevelService;
import com.future.membership.service.MembershipService;
import com.future.membership.util.MembershipSend;
import com.future.membership.util.SendMailUtil;
/**
 * 会员controller
 * @author tend
 *
 */
@Controller
@RequestMapping(value="/membership")
public class MembershipController {

	private Logger logger = LoggerFactory.getLogger(MembershipController.class);
	
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private MembershipLevelService levelService;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addUI(ModelMap map){
		map.addAttribute("levelList",getAllLevel());
		return "membership/save_UI";
	}
	
	@RequestMapping(value="/edit/{id}/{currentPage}",method=RequestMethod.GET)
	public String edit(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		MembershipResp resp = membershipService.getById(id);
		map.addAttribute("membership",resp);
		map.addAttribute("levelList",getAllLevel());
		map.addAttribute("currentPage",currentPage);
		return "membership/update_UI";
	}
	
	@RequestMapping(value="/sendEmail/{id}",method=RequestMethod.GET)
	public String sendEamilUI(ModelMap map,@PathVariable("id") int id){
		map.addAttribute("membership_id",id);
		return "membership/sendEmail_UI";
	}
	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	public String sendEamil(ModelMap map,@ModelAttribute("send")MembershipSend send){
		MembershipSend send2 = membershipService.getSendById(send.getId());
		send.setEmail(send2.getEmail());
		SendMailUtil.send(send);
		String message = "发送成功";
		map.addAttribute("message",message);
		return "message";
	}
	
	@RequestMapping(value="/save/{currentPage}",method=RequestMethod.POST)
	public String saveMembership(ModelMap map,@PathVariable("currentPage") int currentPage,@ModelAttribute("membership")MembershipReq membershipReq){
		logger.info("saveMembership membershipReq={}",membershipReq);
		String tid = UUID.randomUUID().toString();
		membershipService.saveOrUpdateMembership(membershipReq,tid,false);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/membership/list/"+currentPage;
	}
	
	@RequestMapping(value="/update/{currentPage}",method=RequestMethod.POST)
	public String updateMembership(ModelMap map,@PathVariable("currentPage") int currentPage,@ModelAttribute("membership")MembershipReq membershipReq,HttpSession session){
		logger.info("updatemembership membershipReq={}",membershipReq);
		String tid = UUID.randomUUID().toString();
		membershipService.saveOrUpdateMembership(membershipReq,tid,true);
		map.addAttribute("currentPage",currentPage);
		UserBo user = (UserBo) session.getAttribute("user");
		if(user.getMembership_id() ==null){
			return "redirect:/membership/list/"+currentPage;
		}else{
			return "redirect:/membership/list/"+user.getMembership_id()+"/"+currentPage;
		}
	}
	@RequestMapping(value="/upPassword/{membership_id}/{currentPage}",method=RequestMethod.GET)
	public String upPasswordUI(ModelMap map,@PathVariable("membership_id") int membership_id,@PathVariable("currentPage") int currentPage){
		MembershipResp membership = membershipService.getById(membership_id);
		map.addAttribute("membership",membership);
		map.addAttribute("currentPage",currentPage);
		return "membership/upPassword_UI";
	}
	
	@RequestMapping(value="/upPassword",method=RequestMethod.POST)
	public String upPassword(ModelMap map,@ModelAttribute("membership")MembershipPassWord membership){
		membershipService.updatePassword(membership);
		map.addAttribute("message","修改成功");
		return "message";
	}
	
	@RequestMapping(value="/delete/{id}/{currentPage}",method=RequestMethod.GET)
	public String deleteMembership(ModelMap map,@PathVariable("id") int id,@PathVariable("currentPage") int currentPage){
		String tid = UUID.randomUUID().toString();
		membershipService.delete(tid,id);
		map.addAttribute("currentPage",currentPage);
		return "redirect:/membership/list/"+currentPage;
	}
	
	@RequestMapping(value="/list/{currentPage}")
	public String list(ModelMap map,@PathVariable("currentPage") int currentPage){
		MembershipPage membershipPage = membershipService.queryList(-1,currentPage);
		map.addAttribute("membershipPage",membershipPage);
		map.addAttribute("currentPage",currentPage);
		logger.info("membershipPage={}",JSON.toJSONString(membershipPage));
		return "membership/membership_list";
	}
	@RequestMapping(value="/list/{membership_id}/{currentPage}")
	public String list2(ModelMap map,@PathVariable("currentPage") int currentPage,@PathVariable("membership_id") int membership_id){
		MembershipPage membershipPage = membershipService.queryList(membership_id,currentPage);
		map.addAttribute("membershipPage",membershipPage);
		map.addAttribute("currentPage",currentPage);
		logger.info("membershipPage={}",JSON.toJSONString(membershipPage));
		return "membership/membership_list";
	}
	
	private List<MembershipLevelDto> getAllLevel(){
		return levelService.getAll();
	}
}
