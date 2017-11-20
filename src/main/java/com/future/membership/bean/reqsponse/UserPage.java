package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.bo.UserBo;
import com.future.membership.bean.page.Page;

public class UserPage {

	private Page page;
	private List<UserBo> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<UserBo> getList() {
		return list;
	}
	public void setList(List<UserBo> list) {
		this.list = list;
	}
	
}
