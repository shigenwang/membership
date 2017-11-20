package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.bo.MaintainBo;
import com.future.membership.bean.page.Page;

public class MaintainPage {

	private Page page;
	
	private List<MaintainBo> list;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<MaintainBo> getList() {
		return list;
	}

	public void setList(List<MaintainBo> list) {
		this.list = list;
	}
	
}
