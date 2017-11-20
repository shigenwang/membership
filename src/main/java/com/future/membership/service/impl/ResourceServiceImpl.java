package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.future.membership.bean.ResourceDto;
import com.future.membership.bean.ResourceDtoExample;
import com.future.membership.enums.ResourceType;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.ResourceDtoMapper;
import com.future.membership.service.ResourceService;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDtoMapper resourceMapper;
	
	@Override
	public Set<String> findPermissions(Set<Integer> resourceIds) {
		Set<String> permissions = new HashSet<String>();
        for(Integer resourceId : resourceIds) {
            ResourceDto resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
	}

	public ResourceDto findOne(Integer resourceId) {
		return resourceMapper.selectByPrimaryKey(resourceId);
	}

	@Override
	public List<ResourceDto> findMenus(Set<String> permissions) {
		List<ResourceDto> allResource = getAllResource();
        List<ResourceDto> menus = new ArrayList<ResourceDto>();
        System.out.println("allResource : " + allResource);
        for(ResourceDto resource : allResource) {
            if(resource.getType().equals(ResourceType.menu)) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
	}
	
	public List<ResourceDto> getAllResource() {
		ResourceDtoExample example = new ResourceDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		return resourceMapper.selectByExample(example);
	}

	private boolean hasPermission(Set<String> permissions, ResourceDto resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
	}

}
