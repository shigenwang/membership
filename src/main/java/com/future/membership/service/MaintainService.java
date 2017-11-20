package com.future.membership.service;

import com.future.membership.bean.bo.MaintainBo;
import com.future.membership.bean.reqsponse.MaintainPage;

public interface MaintainService {

	MaintainPage queryList(int currentPage);

	int saveOrUpdateMaintain(MaintainBo maintainBo, boolean update);

	MaintainBo selectById(int id);

	int delete(int id);

}
