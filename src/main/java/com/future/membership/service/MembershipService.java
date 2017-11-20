package com.future.membership.service;

import java.io.OutputStream;
import java.util.List;

import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.MembershipDtoExample;
import com.future.membership.bean.reqsponse.MembershipPage;
import com.future.membership.bean.reqsponse.MembershipResp;
import com.future.membership.bean.request.MembershipPassWord;
import com.future.membership.bean.request.MembershipReq;
import com.future.membership.util.MembershipSend;

public interface MembershipService {

	int saveOrUpdateMembership(MembershipReq membershipReq, String tid, boolean b);

	boolean delete(String tid, int id);

	MembershipPage queryList(int membership_id,int currentPage);

	MembershipResp getById(int id);

	void downLoad(OutputStream outputStream);

	List<MembershipDto> queryAll();

	MembershipDto selectByExample(MembershipDtoExample example);

	void updatePassword(MembershipPassWord membership);

	MembershipSend getSendById(int id);

	boolean sendEmail(int id);

}
