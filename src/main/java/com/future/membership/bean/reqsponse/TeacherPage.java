package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.bo.TeacherBo;
import com.future.membership.bean.page.Page;

public class TeacherPage {

	private Page page;
	private List<TeacherBo> list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<TeacherBo> getList() {
		return list;
	}
	public void setList(List<TeacherBo> list) {
		this.list = list;
	}
	
}
