package com.future.membership.bean.bo;

import java.util.Date;

public class UserBo {
	 private Integer id;

	    private String username;

	    private String password;

	    private String salt;

	    private Integer age;

	    private String gender;

	    private String rolesids;

	    private Integer locked;

	    private Integer valid;

	    private Integer type;
	    
	    private String tel;
	    
	    private Integer teacher_id;
	    
	    private Integer membership_id;
	    private String company;
	    
	    private String address;
	    
	    private String email;

	    private Date createTime;

	    private Integer rank;
	    
	    private Date lastloginTime;

	    public Integer getTeacher_id() {
			return teacher_id;
		}

		public void setTeacher_id(Integer teacher_id) {
			this.teacher_id = teacher_id;
		}

		public Integer getMembership_id() {
			return membership_id;
		}

		public void setMembership_id(Integer membership_id) {
			this.membership_id = membership_id;
		}

		public Integer getRank() {
			return rank;
		}

		public void setRank(Integer rank) {
			this.rank = rank;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

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

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public String getSalt() {
	        return salt;
	    }

	    public void setSalt(String salt) {
	        this.salt = salt == null ? null : salt.trim();
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
	        this.gender = gender == null ? null : gender.trim();
	    }

	    public String getRolesids() {
	        return rolesids;
	    }

	    public void setRolesids(String rolesids) {
	        this.rolesids = rolesids == null ? null : rolesids.trim();
	    }

	    public Integer getLocked() {
	        return locked;
	    }

	    public void setLocked(Integer locked) {
	        this.locked = locked;
	    }

	    public Integer getValid() {
	        return valid;
	    }

	    public void setValid(Integer valid) {
	        this.valid = valid;
	    }

	    public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
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

	    public String getCredentialsSalt() {
	        return this.username + salt;
	    }
}
