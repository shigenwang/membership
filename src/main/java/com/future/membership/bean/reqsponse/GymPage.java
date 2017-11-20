package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.page.Page;
import com.future.membership.bean.request.GymReq;

public class GymPage {

	private Page page;
	private List<GymReq> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<GymReq> getList() {
		return list;
	}
	public void setList(List<GymReq> list) {
		this.list = list;
	}
	
}
