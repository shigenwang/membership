package com.future.membership.bean.request;

public class GymReq {
	private Integer id;

    private String name;

    private Integer roomNumber;
    
    private String description;

    private Integer valid;
    
    private Integer equipmentSize;

    public Integer getEquipmentSize() {
		return equipmentSize;
	}

	public void setEquipmentSize(Integer equipmentSize) {
		this.equipmentSize = equipmentSize;
	}

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
