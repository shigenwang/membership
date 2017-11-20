package com.future.membership.service;

import com.future.membership.bean.reqsponse.GymPage;
import com.future.membership.bean.reqsponse.GymResponse;
import com.future.membership.bean.request.GymReq;

public interface GymService {

	int saveOrUpdateGym(GymReq gym, String tid, boolean update);

	boolean delete(String tid, int id);
	GymResponse getGym(String tid, int id);

	GymPage queryList(int currentPage);

	GymReq findById(int id);

	GymResponse getGymResp(int id,int currentPage);

}
