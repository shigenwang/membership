package com.future.membership.bean.bo.excel;

import java.util.Date;

public class EquipmentImExcel {
private String name;
    
    private String description;

    private String code;
    
    private String typeName;
    
    private String damage;
    
    private String manufacturer;
    
    private String manufacturer_tel;
    
    private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getManufacturer_tel() {
		return manufacturer_tel;
	}

	public void setManufacturer_tel(String manufacturer_tel) {
		this.manufacturer_tel = manufacturer_tel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
