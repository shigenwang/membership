package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.future.membership.bean.UserDto;
import com.future.membership.bean.UserDtoExample;
import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.UserPage;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.UserDtoMapper;
import com.future.membership.service.RoleService;
import com.future.membership.service.UserService;
import com.future.membership.util.CommonUtil;
import com.future.membership.util.PasswordHelper;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDtoMapper userMapper;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public int saveOrUpdateUser(UserBo user, boolean update) {
		
		int i = -1;
		if(user!=null){
			if(!update){
				PasswordHelper pas = new PasswordHelper();
				user.setSalt("b15759287d8eba77ebfe6bcd44f68953");
				pas.encryptPassword(user);   //对用户的密码进行加密
			}
			UserDto dto = new UserDto();
			BeanUtils.copyProperties(user, dto);
			if(update){
				i = userMapper.updateByPrimaryKey(dto);
			}else{
				i = userMapper.insertSelective(dto);
			}
		}
		return i;
	}
	@Override
	public UserPage queryList(int currentPage) {
		UserDtoExample example = new UserDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		int count = userMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<UserDto> courseDtos = userMapper.selectByExample(example);
		List<UserBo> userBos = new ArrayList<UserBo>();
		UserPage userPage = new UserPage();
		for (UserDto userDto : courseDtos) {
			UserBo bo = new UserBo();
			BeanUtils.copyProperties(userDto, bo);
			userBos.add(bo);
		}
		userPage.setList(userBos);
		userPage.setPage(page);
		return userPage;
	}
	@Override
	public UserBo selectById(int id) {
		UserBo user = new UserBo();
		UserDto dto = userMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(dto, user);
		return user;
	}
	
	public UserDto findByUsername(String username) {
		UserDtoExample example = new UserDtoExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UserDto> list =  userMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
    }
	
	@Override
	public Set<String> findRoles(String userName) {
		UserDto user =findByUsername(userName);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        List<Integer> roleIds = new ArrayList<Integer>();
        String[] str = user.getRolesids().split(",");
        for (String string : str) {
			roleIds.add(Integer.parseInt(string));
		}
		return roleService.findRoles(roleIds.toArray(new Integer[0]));
	}
	@Override
	public Set<String> findPermissions(String userName) {
		UserDto user =findByUsername(userName);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        List<Integer> roleIds = new ArrayList<Integer>();
        roleIds = CommonUtil.CommaStringCovertToListInteger(user.getRolesids());
        return roleService.findPermissions(roleIds.toArray(new Integer[0]));
	}
	@Override
	public UserBo getUserByUserName(String userName) {
		UserDto dto = findByUsername(userName);
		UserBo user = new UserBo();
		if(dto!=null){
			BeanUtils.copyProperties(dto, user);
			return user;
		}else{
			return null;
		}
	}
	@Override
	public UserBo selectByUsernameAndPassword(String username, String password) {
		UserDtoExample example = new UserDtoExample();
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<UserDto> list = userMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			UserDto dto = list.get(0);
			UserBo bo = new UserBo();
			BeanUtils.copyProperties(dto, bo);
			return bo;
		}
	}
	@Override
	public int delete(int id) {
		UserDto user = userMapper.selectByPrimaryKey(id);
		user.setValid(ValidHelper.NO.toInt());
		int i = userMapper.updateByPrimaryKey(user);
		return i;
	}
	@Override
	public boolean isExistPassword(String password) {
		
		return false;
	}

}
