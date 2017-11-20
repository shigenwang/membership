package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.membership.bean.CourseDto;
import com.future.membership.bean.CourseDtoExample;
import com.future.membership.bean.ParticipateDto;
import com.future.membership.bean.ParticipateDtoExample;
import com.future.membership.bean.TeacherDto;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.CoursePage;
import com.future.membership.bean.request.CourseReq;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.CourseDtoMapper;
import com.future.membership.mapper.ParticipateDtoMapper;
import com.future.membership.mapper.TeacherDtoMapper;
import com.future.membership.service.CourseService;
import com.future.membership.util.DateUtil;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDtoMapper courseMapper;
	
	@Autowired
	private TeacherDtoMapper teacherMapper;
	
	@Autowired
	private ParticipateDtoMapper participateMapper;
	public CoursePage queryList(int user_id,int currentPage) {
		CourseDtoExample example = new CourseDtoExample();
		if(user_id ==-1){
			example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		}else{
			TeacherDto teacher = teacherMapper.selectByPrimaryKey(user_id);
			example.createCriteria().andTeacher_idEqualTo(teacher.getId()).andValidEqualTo(ValidHelper.YES.toInt());
		}
		int count = courseMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<CourseDto> courseDtos = courseMapper.selectByExample(example);
		CoursePage coursePage = new CoursePage();
		List<CourseReq> list = new ArrayList<CourseReq>();
		for (CourseDto courseDto : courseDtos) {
			CourseReq course = new CourseReq();
			BeanUtils.copyProperties(courseDto, course);
			course.setStartTime(DateUtil.formatYYMMDDHH(courseDto.getStartTime()));
			course.setEndTime(DateUtil.formatYYMMDDHH(courseDto.getEndTime()));
			list.add(course);
		}
		coursePage.setList(list);
		coursePage.setPage(page);
		return coursePage;
	}

	public int saveOrUpdateCourse(CourseReq course, boolean update) {
		int i = -1;
		if(course!=null){
			CourseDto dto = new CourseDto();
			BeanUtils.copyProperties(course, dto);
			dto.setStartTime(DateUtil.toDate(course.getStartTime()));
			dto.setEndTime(DateUtil.toDate(course.getEndTime()));
			dto.setTeacher_id(course.getTeacher_id());
			dto.setValid(ValidHelper.YES.toInt());
			if(update){
				i = courseMapper.updateByPrimaryKey(dto);
			}else{
				i = courseMapper.insertSelective(dto);
			}
		}
		return i;
	}

	public CourseReq selectById(int id) {
		CourseReq course = new CourseReq();
		CourseDto dto = courseMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(dto, course);
		course.setStartTime(DateUtil.formatYYMMDDHHmmss(dto.getStartTime()));
		course.setEndTime(DateUtil.formatYYMMDDHHmmss(dto.getEndTime()));
		return course;
	}

	@Override
	public boolean delete(int id) {
		CourseDtoExample example = new CourseDtoExample();
		example.createCriteria().andIdEqualTo(id).andValidEqualTo(ValidHelper.YES.toInt());
		List<CourseDto> courseDtos = courseMapper.selectByExample(example);

		CourseDto courseDto = null;
		int i = 0;
		if (courseDtos != null) {
			courseDto = courseDtos.get(0);
			courseDto.setValid(ValidHelper.NO.toInt()); // 逻辑删除
			i = courseMapper.updateByPrimaryKeySelective(courseDto);
		}

		return i > 0 ? true : false;
	}

	@Override
	public List<CourseDto> queryAll(int membership_id) {
		ParticipateDtoExample example2 = new ParticipateDtoExample();
		example2.createCriteria().andMembership_idEqualTo(membership_id);
		List<ParticipateDto> list = participateMapper.selectByExample(example2);
		List<Integer> coursIids = new ArrayList<Integer>();
		for (ParticipateDto dto : list) {
			coursIids.add(dto.getCourse_id());
		}
		CourseDtoExample example = new CourseDtoExample();
		example.createCriteria().andIdNotIn(coursIids).andValidEqualTo(ValidHelper.YES.toInt());
		return courseMapper.selectByExample(example);
	}

}
