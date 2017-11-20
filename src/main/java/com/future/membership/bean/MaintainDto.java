package com.future.membership.bean;

import java.util.Date;

public class MaintainDto {
    private Integer id;

    private Integer equipment_id;

    private Date qu_create;

    private String qu_type;

    private String mauntauners;

    private Date maintenance_time;

    private Integer valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Date getQu_create() {
        return qu_create;
    }

    public void setQu_create(Date qu_create) {
        this.qu_create = qu_create;
    }

    public String getQu_type() {
        return qu_type;
    }

    public void setQu_type(String qu_type) {
        this.qu_type = qu_type == null ? null : qu_type.trim();
    }

    public String getMauntauners() {
        return mauntauners;
    }

    public void setMauntauners(String mauntauners) {
        this.mauntauners = mauntauners == null ? null : mauntauners.trim();
    }

    public Date getMaintenance_time() {
        return maintenance_time;
    }

    public void setMaintenance_time(Date maintenance_time) {
        this.maintenance_time = maintenance_time;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}