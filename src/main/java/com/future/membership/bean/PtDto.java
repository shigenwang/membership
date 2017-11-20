package com.future.membership.bean;

public class PtDto {
    private Integer id;

    private Integer membership_id;

    private String membership_name;

    private Integer teacher_id;

    private String teacher_name;

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
        this.membership_name = membership_name == null ? null : membership_name.trim();
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name == null ? null : teacher_name.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}