package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.page.Page;

public class EquipmentPage {

	private Page page;
	private List<EquipmentReqs> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<EquipmentReqs> getList() {
		return list;
	}
	public void setList(List<EquipmentReqs> list) {
		this.list = list;
	}
	
}
