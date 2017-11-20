package com.future.membership.bean;

import java.util.Date;

public class EquipmentDto {
    private Integer id;

    private String name;

    private String description;

    private String code;

    private Integer gym_id;

    private Integer type_id;

    private Integer damage;

    private String manufacturer;

    private String manufacturer_tel;

    private Date createTime;

    private Integer valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getGym_id() {
        return gym_id;
    }

    public void setGym_id(Integer gym_id) {
        this.gym_id = gym_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getManufacturer_tel() {
        return manufacturer_tel;
    }

    public void setManufacturer_tel(String manufacturer_tel) {
        this.manufacturer_tel = manufacturer_tel == null ? null : manufacturer_tel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}