package com.future.membership.service;

import java.util.Set;

import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.reqsponse.UserPage;

public interface UserService {

	int saveOrUpdateUser(UserBo user, boolean update);

	UserPage queryList(int currentPage);

	UserBo selectById(int id);

	Set<String> findRoles(String userName);

	Set<String> findPermissions(String userName);

	UserBo getUserByUserName(String userName);

	UserBo selectByUsernameAndPassword(String username, String password);

	int delete(int id);

	boolean isExistPassword(String password);

}
