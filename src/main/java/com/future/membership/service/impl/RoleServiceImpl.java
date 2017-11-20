package com.future.membership.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.membership.bean.RoleDto;
import com.future.membership.bean.RoleDtoExample;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.request.RoleReq;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.RoleDtoMapper;
import com.future.membership.service.ResourceService;
import com.future.membership.service.RoleService;
import com.future.membership.util.CommonUtil;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDtoMapper roleDtoMapper;
	
	@Autowired
	private ResourceService resourceService;
	
	@Override
	public int saveOrUpdateRole(RoleReq role, String tid, boolean update) {
		if(role != null){
			RoleDto newRoleDto = new RoleDto();
			if(role.getId()!=null){
				newRoleDto.setId(role.getId());
			}
			newRoleDto.setName(role.getName());
			newRoleDto.setDescription(role.getDescription());
			newRoleDto.setName(role.getName());
			//此处关联resource表
			newRoleDto.setResource_ids(role.getResource_ids());
			
			if(role.getValid()!=null){
				newRoleDto.setValid(role.getValid());  
			}else{
				newRoleDto.setValid(ValidHelper.YES.toInt()); //默认可用
			}
			int i = -1;
			if(update){  //修改
				i = roleDtoMapper.updateByPrimaryKeySelective(newRoleDto);
			}else{
				i = roleDtoMapper.insertSelective(newRoleDto);
			}
			logger.info("saveorUpdateGym tid={},id={}",tid,i);
			return i;
		}
		return -1;
	}

	@Override
	public boolean delete(String tid, int id) {
		RoleDtoExample example = new RoleDtoExample();
		example.createCriteria().andIdEqualTo(id).andValidEqualTo(ValidHelper.YES.toInt());
		List<RoleDto> roleDtos = roleDtoMapper.selectByExample(example);
		
		RoleDto roleDto = null;
		int i = 0;
		if(roleDtos!=null){
			roleDto = roleDtos.get(0);
			roleDto.setValid(ValidHelper.NO.toInt());  //逻辑删除
			i = roleDtoMapper.updateByPrimaryKeySelective(roleDto);
		}
		
		return i>0?true:false;
	}

	@Override
	public List<RoleDto> queryList(int currentPage) {
		RoleDtoExample example = new RoleDtoExample();
		example.createCriteria();
		int count = roleDtoMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize);
		page.setCount(count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<RoleDto> dtos = roleDtoMapper.selectByExample(example);
		return dtos;
	}

	@Override
	public Set<String> findRoles(Integer... roleIds) {
		 Set<String> roles = new HashSet<String>();
        for(Integer roleId : roleIds) {
            RoleDto role = findOne(roleId);
            if(role != null) {
                roles.add(role.getName());
            }
        }
        return roles;
	}

	public RoleDto findOne(Integer roleId) {
		return roleDtoMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public Set<String> findPermissions(Integer... roleIds) {
		Set<Integer> resourceIds = new HashSet<Integer>();
        for(Integer roleId : roleIds) {
            RoleDto role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(CommonUtil.CommaStringCovertToListInteger(role.getResource_ids()));
            }
        }
        return resourceService.findPermissions(resourceIds);
	}

}
