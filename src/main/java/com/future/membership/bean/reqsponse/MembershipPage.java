package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.page.Page;

public class MembershipPage {

	private Page page;
	private List<MembershipResp> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<MembershipResp> getList() {
		return list;
	}
	public void setList(List<MembershipResp> list) {
		this.list = list;
	}
	
}
