package com.future.membership.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.future.membership.annotation.CurrentUser;
import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.MembershipDtoExample;
import com.future.membership.bean.ResourceDto;
import com.future.membership.bean.TeacherDto;
import com.future.membership.bean.TeacherDtoExample;
import com.future.membership.bean.bo.UserBo;
import com.future.membership.service.MembershipService;
import com.future.membership.service.ResourceService;
import com.future.membership.service.TeacherService;
import com.future.membership.service.UserService;
import com.future.membership.util.PasswordHelper;

@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "/footing")
	public String footing() {
		return "footing";
	}

	@RequestMapping(value = "/head")
	public String heading() {
		return "head";
	}

	@RequestMapping(value = "/userLeft")
	public String userLeft() {
		return "userLeft";
	}
	@RequestMapping(value = "/membershipLeft")
	public String membershipLeft() {
		return "membershipLeft";
	}
	@RequestMapping(value = "/teacherLeft")
	public String teacherLeft() {
		return "teacherLeft";
	}
	
	/*@RequestMapping(value = "/login")
	public String login( Model model, HttpServletRequest request) throws Exception {
		// 如果用户登录失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        Subject subject = SecurityUtils.getSubject();
		Principal principal = (Principal)subject.getPrincipal();
		if(principal!=null){
			return "redirect:" + "index";
		}
        //登陆成功后shiro会自动跳转到上一个请求页面
		// 此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
		// 登陆失败还到login页面
		return "login";
	}*/
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String login() {
		logger.info("login.........");
		return "login";
	}
	@RequestMapping(value = "/register",method=RequestMethod.GET)
	public String register() {
		logger.info("register.........");
		return "register";
	}
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login1( Model model, HttpServletRequest request,HttpSession session){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserBo user = new UserBo();
		user.setUsername(username);
		user.setPassword(password);
		user.setSalt("b15759287d8eba77ebfe6bcd44f68953");
		PasswordHelper ps= new PasswordHelper();
		ps.encryptPassword(user);
		UserBo bo= null;
		bo = userService.selectByUsernameAndPassword(user.getUsername(),user.getPassword());
		if(bo==null){
			return "login";
		}
		bo.setLastloginTime(new Date());
		userService.saveOrUpdateUser(bo, true);
		String returnStr = "";
		switch (bo.getType()) {
		case 1:
			returnStr = "userIndex";
			break;
		case 2:
			MembershipDtoExample example = new MembershipDtoExample();
			example.createCriteria().andUser_idEqualTo(bo.getId());
			MembershipDto membership = membershipService.selectByExample(example);
			bo.setAddress(membership.getAddress());
			bo.setCompany(membership.getCompany());
			bo.setEmail(membership.getEmail());
			bo.setTel(membership.getTel());
			bo.setMembership_id(membership.getId());
			returnStr = "membershipIndex";
			break;
		case 3:
			TeacherDtoExample example2 = new TeacherDtoExample();
			example2.createCriteria().andUser_idEqualTo(bo.getId());
			TeacherDto teacher = teacherService.selectByExample(example2);
			bo.setRank(Integer.parseInt(teacher.getRank()));
			bo.setTeacher_id(teacher.getId());
			returnStr = "teacherIndex";
			break;
		default:
			break;
		}
		session.setAttribute("user", bo);
		return "redirect:" + returnStr;
	}
	@RequestMapping("test/login")
	public String testLogin(){
		return "login";
	}
	@RequestMapping("/userIndex")
	public String userIndex(Model map){
		return "userIndex";
	}
	@RequestMapping("/membershipIndex")
	public String membershipIndex(Model map){
		return "membershipIndex";
	}
	@RequestMapping("/teacherIndex")
	public String teacherIndex(Model map){
		return "teacherIndex";
	}
	
	@RequestMapping(value = "/index")
	public String index(@CurrentUser UserBo loginUser, Model model,HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		String loginName = (String) subject.getPrincipal();
		System.out.println("loginName : " + loginName);
		System.out.println("loginUser.getLoginName() : " + loginUser.getUsername());
        Set<String> permissions = userService.findPermissions(loginName);
        List<ResourceDto> menus = resourceService.findMenus(permissions);
        System.out.println("permissions : " + permissions);
        System.out.println("menus : " + menus);
		List<ResourceDto> parentMenu = new ArrayList<ResourceDto>();
		List<ResourceDto> sonMenu = new ArrayList<ResourceDto>();
		for (ResourceDto menu : menus) {
			if (menu.getParent_id() == 0) {
				System.out.println("father" + menu.getName());
				parentMenu.add(menu);
			} else {
				System.out.println("son" + menu.getName());
				sonMenu.add(menu);
			}
		}
		logger.info("parentMenu={}",JSON.toJSONString(parentMenu));
		logger.info("sonMenu={}",JSON.toJSONString(sonMenu));
		session.setAttribute("parentMenu", parentMenu);
		session.setAttribute("sonMenu", sonMenu);
		session.setAttribute("loginName", loginName);
        return "index";
    }
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		logger.info("session={}",JSON.toJSONString(session));
		UserBo user = (UserBo) session.getAttribute("user");
		if(user!=null){
			session.removeAttribute("user");
		}
		logger.info("session={}",JSON.toJSONString(session));
		return "redirect:/login";
	}
}
