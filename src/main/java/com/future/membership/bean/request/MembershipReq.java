package com.future.membership.bean.request;

import java.util.Date;

public class MembershipReq {
	private Integer id;

    private Integer user_id;

    private String username;
    
    private String password;
    
    private String salt;
    
    private Integer type;  //身份 1员工 2会员
    
    private Integer age;
    private String gender;
    private Integer locked;  //账号是否锁定 1正常 0锁定
    private String tel;

    private Date createTime;
    private Date lastloginTime;
    private String company;

    private Integer level_id;
    
    private String levelName;

    private String address;

    private Integer audit;

    private Integer fitnessNumber;

    private Integer leftTimes;
    private String email;

    private Integer valid;   //是否删除 0删除 1未删

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(Date lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
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
        this.address = address == null ? null : address.trim();
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

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
