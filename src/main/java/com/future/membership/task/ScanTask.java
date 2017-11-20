package com.future.membership.task;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.future.membership.bean.MembershipDto;
import com.future.membership.bean.MembershipDtoExample;
import com.future.membership.bean.UserDto;
import com.future.membership.bean.UserDtoExample;
import com.future.membership.enums.MembershipState;
import com.future.membership.enums.ValidHelper;
import com.future.membership.mapper.MembershipDtoMapper;
import com.future.membership.mapper.UserDtoMapper;
import com.future.membership.util.DateUtil;

@Component
public class ScanTask {

	private Logger logger = LoggerFactory.getLogger(RecommendTask.class);
	
	@Autowired
	private UserDtoMapper userMapper;
	
	private MembershipDtoMapper membershipMapper;
	@Scheduled(cron = "0 0 0 1 * ?")   //每月的一号扫描
	public void execute(){
		UserDtoExample example = new UserDtoExample();
		example.createCriteria().andValidEqualTo(ValidHelper.YES.toInt()).andTypeEqualTo(2);   //会员 
		List<UserDto> users = userMapper.selectByExample(example);
		for (UserDto userDto : users) {
			Date date = userDto.getCreateTime();
			Date currentDate = new Date();
			MembershipDtoExample example2 = new MembershipDtoExample();
			example2.createCriteria().andUser_idEqualTo(userDto.getId());
			MembershipDto membership = membershipMapper.selectByExample(example2).get(0);
			isState(date, currentDate, membership);
		}
		logger.info("ScanTask execute。。。。。");
	}
	
	private int isState(Date date, Date currentDate,MembershipDto membership){
		try {
			int days = DateUtil.daysBetween(date, currentDate);
			if(days < 365*MembershipState.SILVER.toYears()){
				membership.setLevel_id(MembershipState.BRONZE.toInt());
			}else if(days< 365*MembershipState.GOLD.toYears()&& days > 365*365*MembershipState.SILVER.toYears()){
				membership.setLevel_id(MembershipState.SILVER.toInt());
			}else if(days < 365*MembershipState.PLATINUM.toYears()&&days >365*MembershipState.GOLD.toYears()){
				membership.setLevel_id(MembershipState.GOLD.toInt());
			}else{
				membership.setLevel_id(MembershipState.PLATINUM.toInt());
			}
			membershipMapper.updateByPrimaryKey(membership);
			return membership.getId();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
