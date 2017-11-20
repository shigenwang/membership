package com.future.membership.service;

import com.future.membership.bean.bo.ParticipateBo;
import com.future.membership.bean.reqsponse.ParticipatePage;

public interface ParticipateService {

	ParticipatePage queryList(int membership_id,int currentPage);

	ParticipateBo selectById(int id);

	int saveOrUpdate(ParticipateBo participate, boolean update);

}
