package com.future.membership.bean.bo;

import java.util.Date;

public class MaintainBo {
	private Integer id;

    private Integer equipment_id;

    private String equipment_name;
    
    private String equipment_code;
    
    private Date qu_create;

    private String qu_type;

    private String mauntauners;

    private String maintenance_time;

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

    public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getEquipment_code() {
		return equipment_code;
	}

	public void setEquipment_code(String equipment_code) {
		this.equipment_code = equipment_code;
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

    public String getMaintenance_time() {
		return maintenance_time;
	}

	public void setMaintenance_time(String maintenance_time) {
		this.maintenance_time = maintenance_time;
	}

	public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
