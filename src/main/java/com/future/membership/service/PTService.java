package com.future.membership.service;

import com.future.membership.bean.bo.ParticipateBo;
import com.future.membership.bean.reqsponse.ParticipatePage;

public interface PTService {

	ParticipatePage queryList(int currentPage);

	int saveOrUpdatePT(ParticipateBo ptBo, boolean update);

	ParticipateBo selectById(int id);

}
