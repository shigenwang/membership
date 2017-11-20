package com.future.membership.bean.reqsponse;

import java.util.Date;

public class MembershipResp {

	private Integer id;

    private Integer user_id;
    
    private String name;
    
    private String password;
    
    private Integer age;
    private String gender;
    private String tel;

    private String company;

    private Integer level_id;
    
    private String levelName;
    
    private Date lastloginTime;

    private String address;

    private Integer audit;

    private Integer fitnessNumber;

    private Integer leftTimes;
    
    private String email;

    private Integer valid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getLevel_id() {
		return level_id;
	}

	public void setLevel_id(Integer level_id) {
		this.level_id = level_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAudit() {
		return audit;
	}

	public void setAudit(Integer audit) {
		this.audit = audit;
	}

	public Integer getFitnessNumber() {
		return fitnessNumber;
	}

	public void setFitnessNumber(Integer fitnessNumber) {
		this.fitnessNumber = fitnessNumber;
	}

	public Integer getLeftTimes() {
		return leftTimes;
	}

	public void setLeftTimes(Integer leftTimes) {
		this.leftTimes = leftTimes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
}
