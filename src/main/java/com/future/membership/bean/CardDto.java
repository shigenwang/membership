package com.future.membership.bean;

import java.util.Date;

public class CardDto {
    private Integer id;

    private Integer membership_id;

    private String membership_name;

    private String code;

    private Integer type;

    private Date createTime;

    private Integer remaining_days;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getRemaining_days() {
        return remaining_days;
    }

    public void setRemaining_days(Integer remaining_days) {
        this.remaining_days = remaining_days;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}