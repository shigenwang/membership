package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.bo.ParticipateBo;
import com.future.membership.bean.page.Page;

public class ParticipatePage {
	private Page page;
	private List<ParticipateBo> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<ParticipateBo> getList() {
		return list;
	}
	public void setList(List<ParticipateBo> list) {
		this.list = list;
	}
	
}
