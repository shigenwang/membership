package com.future.membership.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.future.membership.bean.CardDto;
import com.future.membership.bean.CardDtoExample;
import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.UserDto;
import com.future.membership.bean.page.Page;
import com.future.membership.bean.reqsponse.CardPage;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.CardDtoMapper;
import com.future.membership.mapper.MembershipDtoMapper;
import com.future.membership.mapper.UserDtoMapper;
import com.future.membership.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService{

	@Autowired
	private CardDtoMapper cardMapper;
	
	@Autowired
	private MembershipDtoMapper membershipMapper;
	
	@Autowired
	private UserDtoMapper userMapper;
	@Override
	public CardDto selectById(int id) {
		return cardMapper.selectByPrimaryKey(id);
	}
	@Override
	public int saveOrUpdateCard(CardDto card, boolean update) {
		int i = -1;
		if(card!=null){
			MembershipDto membership = membershipMapper.selectByPrimaryKey(card.getMembership_id());
			UserDto user = userMapper.selectByPrimaryKey(membership.getUser_id());
			card.setMembership_name(user.getUsername());
			CardDto dto = new CardDto();
			BeanUtils.copyProperties(card, dto);
			dto.setCreateTime(new Date());
			switch (dto.getType()) {
			case 1:   //月卡
				dto.setRemaining_days(30);
				break;
			case 2:   //季卡
				dto.setRemaining_days(90);
				break;
			case 3:   //半年卡
				dto.setRemaining_days(180);
				break;
			case 4:   //年卡
				dto.setRemaining_days(365);
				break;

			default:
				break;
			}
			if(update){
				i = cardMapper.updateByPrimaryKey(dto);
			}else{
				i = cardMapper.insertSelective(dto);
			}
		}
		return i;
	}
	@Override
	public CardPage queryList(int currentPage) {
		CardDtoExample example = new CardDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt());
		int count = cardMapper.countByExample(example);
		int pageSize = 10;   //默认10页
		String orderKeyStr = "id desc";   //默认id排序
		int begin = (currentPage - 1) * pageSize;
		Page page = new Page(begin, pageSize,count);
		example.setPage(page);
		example.setOrderByClause(orderKeyStr);
		List<CardDto> cardDtos = cardMapper.selectByExample(example);
		CardPage cardPage = new CardPage();
		cardPage.setList(cardDtos);
		cardPage.setPage(page);
		return cardPage;
	}
}
