package com.future.membership.bean.reqsponse;


public class GymResponse {
	private Integer id;

    private String name;
    
    private String description;

    private Integer roomNumber;
    
    private Integer valid;

    private EquipmentPage page;   //本健身房所对应的器材列表
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EquipmentPage getPage() {
		return page;
	}

	public void setPage(EquipmentPage page) {
		this.page = page;
	}

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
