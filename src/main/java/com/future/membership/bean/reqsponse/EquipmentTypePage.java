package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.page.Page;

public class EquipmentTypePage {

	private Page page;
	private List<EquipmentTypeReqs> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<EquipmentTypeReqs> getList() {
		return list;
	}
	public void setList(List<EquipmentTypeReqs> list) {
		this.list = list;
	}
}
