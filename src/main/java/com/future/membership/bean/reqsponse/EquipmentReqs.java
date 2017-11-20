package com.future.membership.bean.reqsponse;

import com.future.membership.bean.EquipmentTypeDto;

public class EquipmentReqs {
	private Integer id;

    private String name;

    private String code;
    
    private String description;

    private EquipmentTypeDto type;
    
    private Integer valid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	public EquipmentTypeDto getType() {
		return type;
	}

	public void setType(EquipmentTypeDto type) {
		this.type = type;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

}
