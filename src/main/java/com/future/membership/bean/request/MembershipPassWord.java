package com.future.membership.bean.request;

public class MembershipPassWord {

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String oldPassword;
	
	private String uppassword;
	
	private String confirmspassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getUppassword() {
		return uppassword;
	}

	public void setUppassword(String uppassword) {
		this.uppassword = uppassword;
	}

	public String getConfirmspassword() {
		return confirmspassword;
	}

	public void setConfirmspassword(String confirmspassword) {
		this.confirmspassword = confirmspassword;
	}
	
}
