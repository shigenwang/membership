package com.future.membership.bean.request;

public class EquipmentReq {

	private Integer id;

    private String name;
    
    private String description;

    private String code;
    
    private Integer gym_id;
    
    private Integer type_id;  //健身设备类型
    
    private Integer valid;    //是否可用 1 可用 0不可用

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGym_id() {
		return gym_id;
	}

	public void setGym_id(Integer gym_id) {
		this.gym_id = gym_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
    
}
