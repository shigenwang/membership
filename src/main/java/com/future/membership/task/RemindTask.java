package com.future.membership.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.future.membership.service.EquipmentDtoService;
import com.future.membership.service.MembershipService;

/**
 * 提醒任务
 * @author tend
 *
 */
@Component
public class RemindTask {
	private Logger logger = LoggerFactory.getLogger(RemindTask.class);
	
	@Autowired
	private EquipmentDtoService equipmentService;
	
	@Autowired
	private MembershipService membershipService;
	public void execute(){
		
		logger.info("RecommendTask execute。。。。。");
	}
}
