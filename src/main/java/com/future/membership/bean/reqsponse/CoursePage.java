package com.future.membership.bean.reqsponse;

import java.util.List;

import com.future.membership.bean.CourseDto;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.request.CourseReq;

public class CoursePage {

	private Page page;
	
	private List<CourseReq> list;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<CourseReq> getList() {
		return list;
	}

	public void setList(List<CourseReq> list) {
		this.list = list;
	}
}
