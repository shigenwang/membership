package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.CardDto;
import com.future.membership.bean.page.Page;

public class CardPage {
	private Page page;
	
	private List<CardDto> list;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<CardDto> getList() {
		return list;
	}

	public void setList(List<CardDto> list) {
		this.list = list;
	}
	
}
