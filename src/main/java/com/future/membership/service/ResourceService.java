package com.future.membership.service;

import java.util.List;
import java.util.Set;

import com.future.membership.bean.ResourceDto;

public interface ResourceService {

	Set<String> findPermissions(Set<Integer> resourceIds);

	List<ResourceDto> findMenus(Set<String> permissions);

}
