package com.future.membership.bean.bo;

public class ParticipateBo {
	private Integer id;

    private Integer membership_id;

    private String membership_name;

    private Integer course_id;

    private String course_name;
    
    private String course_type;

    private Integer valid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(Integer membership_id) {
		this.membership_id = membership_id;
	}

	public String getMembership_name() {
		return membership_name;
	}

	public void setMembership_name(String membership_name) {
		this.membership_name = membership_name;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getCourse_type() {
		return course_type;
	}

	public void setCourse_type(String course_type) {
		this.course_type = course_type;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
    
}
