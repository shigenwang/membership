package com.future.membership.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.future.membership.bean.MembershipLevelDto;
import com.future.membership.bean.MembershipLevelDtoExample;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.MembershipLevelDtoMapper;
import com.future.membership.service.MembershipLevelService;

@Service
@Transactional
public class MembershipLevelServiceImpl implements MembershipLevelService {

	@Autowired
	private MembershipLevelDtoMapper levelMapper;
	
	@Override
	public List<MembershipLevelDto> getAll() {
		MembershipLevelDtoExample example = new MembershipLevelDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		List<MembershipLevelDto> levelDtos = levelMapper.selectByExample(example);
		return levelDtos;
	}

}
