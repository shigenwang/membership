package com.future.membership.service;

import java.util.List;

import com.future.membership.bean.TeacherDto;
import com.future.membership.bean.TeacherDtoExample;
import com.future.membership.bean.bo.TeacherBo;
import com.future.membership.bean.reqsponse.TeacherPage;
import com.future.membership.bean.request.TeacherPs;

public interface TeacherService {

	TeacherPage queryList(Integer teacher_id,int currentPage);

	int saveOrUpdateTeacher(TeacherBo teacherBo, boolean update);

	TeacherBo selectById(int id);

	void delete(int id);

	TeacherDto selectByExample(TeacherDtoExample example);

	List<TeacherBo> queryAllTeacher();

	void updatePassword(TeacherPs teacherPs);

}
