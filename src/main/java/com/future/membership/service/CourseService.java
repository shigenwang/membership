package com.future.membership.service;

import java.util.List;

import com.future.membership.bean.CourseDto;
import com.future.membership.bean.reqsponse.CoursePage;
import com.future.membership.bean.request.CourseReq;

public interface CourseService {

	int saveOrUpdateCourse(CourseReq course, boolean update);

	CourseReq selectById(int id);

	boolean delete(int id);

	CoursePage queryList(int user_id, int currentPage);

	List<CourseDto> queryAll(int membership_id);

}
