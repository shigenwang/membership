package com.future.membership.bean.request;

public class RoleReq {
	 private Integer id;

	    private String name;

	    private String description;

	    private String resource_ids;

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

	    public String getResource_ids() {
	        return resource_ids;
	    }

	    public void setResource_ids(String resource_ids) {
	        this.resource_ids = resource_ids == null ? null : resource_ids.trim();
	    }

	    public Integer getValid() {
	        return valid;
	    }

	    public void setValid(Integer valid) {
	        this.valid = valid;
	    }
}
