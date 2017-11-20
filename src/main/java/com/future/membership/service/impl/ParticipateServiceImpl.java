package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.membership.bean.CourseDto;
import com.future.membership.bean.ParticipateDto;
import com.future.membership.bean.ParticipateDtoExample;
import com.future.membership.bean.bo.ParticipateBo;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.ParticipatePage;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.CourseDtoMapper;
import com.future.membership.mapper.MembershipDtoMapper;
import com.future.membership.mapper.ParticipateDtoMapper;
import com.future.membership.service.ParticipateService;

@Service
@Transactional
public class ParticipateServiceImpl implements ParticipateService{

	@Autowired
	private ParticipateDtoMapper participateMapper;
	
	@Autowired
	private CourseDtoMapper courseMapper;
	
	@Autowired
	private MembershipDtoMapper membershipMapper;
	
	@Override
	public ParticipatePage queryList(int membership_id,int currentPage) {
		ParticipateDtoExample example = new ParticipateDtoExample();
		example.createCriteria().andMembership_idEqualTo(membership_id).andValidEqualTo(ValidHelper.YES.toInt());
		int count = participateMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<ParticipateDto> dtos = participateMapper.selectByExample(example);
		List<ParticipateBo> bos = new ArrayList<ParticipateBo>();
		for (ParticipateDto dto : dtos) {
			ParticipateBo bo = new ParticipateBo();
			BeanUtils.copyProperties(dto, bo);
			bos.add(bo);
		}
		ParticipatePage participatePage = new ParticipatePage();
		participatePage.setList(bos);
		participatePage.setPage(page);
		return participatePage;
	}

	@Override
	public ParticipateBo selectById(int id) {
		ParticipateDto dto = participateMapper.selectByPrimaryKey(id);
		ParticipateBo bo = new ParticipateBo();
		BeanUtils.copyProperties(dto, bo);
		return bo;
	}

	@Override
	public int saveOrUpdate(ParticipateBo participate, boolean update) {
		int i = -1;
		if(participate!=null){
			ParticipateDto dto = new ParticipateDto();
			CourseDto course = courseMapper.selectByPrimaryKey(participate.getCourse_id());
//			MembershipDto membership = membershipMapper.selectByPrimaryKey(participate.getMembership_id());
			participate.setCourse_name(course.getName());
			participate.setCourse_type(course.getType());
			BeanUtils.copyProperties(participate, dto);
			dto.setValid(ValidHelper.YES.toInt());
			if(update){
				i = participateMapper.updateByPrimaryKey(dto);
			}else{
				i = participateMapper.insertSelective(dto);
			}
		}
		return i;
	}

}
