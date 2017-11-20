package com.future.membership.service;

import com.future.membership.bean.CardDto;
import com.future.membership.bean.reqsponse.CardPage;

public interface CardService {

	CardDto selectById(int id);

	int saveOrUpdateCard(CardDto card, boolean update);

	CardPage queryList(int currentPage);

}
