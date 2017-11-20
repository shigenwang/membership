
package com.future.membership.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.future.membership.bean.bo.UserBo;
import com.future.membership.service.UserService;

/**
 * 
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	private Logger logger = LoggerFactory.getLogger(SystemAuthorizingRealm.class);
	/**
	 * 认证回调函数
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {

		System.out.println("**********************开始验证*****************");
		
		String userName = (String)authcToken.getPrincipal();
		UserBo user = userService.getUserByUserName(userName);
		logger.info("shiro 认证 user={}",JSON.toJSONString(user));
		if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
		 if(user.getLocked()==0) {
	            throw new LockedAccountException(); //帐号锁定
	      }
		 //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //登录名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=userName+salt
                getName()  //realm name
        );
		return authenticationInfo;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String userName = (String) principals.getPrimaryPrincipal();
		System.out.println("userName  :  " + userName);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		 authorizationInfo.setRoles(userService.findRoles(userName));
		 System.out.println("userService.findPermissions(loginName) : " + userService.findPermissions(userName));
		 authorizationInfo.setStringPermissions(userService.findPermissions(userName));
		return authorizationInfo;
	}

	 @Override
	    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
	        super.clearCachedAuthorizationInfo(principals);
	    }

	    @Override
	    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
	        super.clearCachedAuthenticationInfo(principals);
	    }

	    @Override
	    public void clearCache(PrincipalCollection principals) {
	        super.clearCache(principals);
	    }

	    public void clearAllCachedAuthorizationInfo() {
	        getAuthorizationCache().clear();
	    }

	    public void clearAllCachedAuthenticationInfo() {
	        getAuthenticationCache().clear();
	    }

	    public void clearAllCache() {
	        clearAllCachedAuthenticationInfo();
	        clearAllCachedAuthorizationInfo();
	    }
}
