package com.future.membership.service;

import java.util.List;
import java.util.Set;

import com.future.membership.bean.RoleDto;
import com.future.membership.bean.request.RoleReq;

public interface RoleService {

	int saveOrUpdateRole(RoleReq role, String tid, boolean update);

	boolean delete(String tid, int id);

	List<RoleDto> queryList(int currentPage);

	Set<String> findRoles(Integer... roleIds);

	Set<String> findPermissions(Integer... roleIds);

}
