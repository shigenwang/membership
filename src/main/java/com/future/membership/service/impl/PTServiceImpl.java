package com.future.membership.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.membership.bean.PtDto;
import com.future.membership.bean.PtDtoExample;
import com.future.membership.bean.bo.ParticipateBo;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.ParticipatePage;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.PtDtoMapper;
import com.future.membership.service.PTService;

@Service
@Transactional
public class PTServiceImpl implements PTService{

	@Autowired
	private PtDtoMapper ptMapper;
	@Override
	public ParticipatePage queryList(int currentPage) {
		PtDtoExample example = new PtDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		int count = ptMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<PtDto> ptDtos = ptMapper.selectByExample(example);
		List<ParticipateBo> bos = new ArrayList<ParticipateBo>();
		for (PtDto ptDto : ptDtos) {
			ParticipateBo ptBo = new ParticipateBo();
			BeanUtils.copyProperties(ptDto, ptBo);
			bos.add(ptBo);
		}
		ParticipatePage ptPage = new ParticipatePage();
		ptPage.setList(bos);
		ptPage.setPage(page);
		return ptPage;
	}
	@Override
	public int saveOrUpdatePT(ParticipateBo ptBo, boolean update) {
		int i = -1;
		if(ptBo!=null){
			PtDto dto = new PtDto();
			BeanUtils.copyProperties(ptBo, dto);
			if(update){
				i = ptMapper.updateByPrimaryKey(dto);
			}else{
				i = ptMapper.insertSelective(dto);
			}
		}
		return i;
	}
	@Override
	public ParticipateBo selectById(int id) {
		ParticipateBo ptBo = new ParticipateBo();
		PtDto dto = ptMapper.selectByPrimaryKey(id);
		BeanUtils.copyProperties(dto, ptBo);
		return ptBo;
	}

}
