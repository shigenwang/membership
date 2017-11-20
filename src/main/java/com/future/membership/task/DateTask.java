package com.future.membership.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateTask {

private Logger logger = LoggerFactory.getLogger(DateTask.class);
	
	public void execute(){
		logger.info("DateTask execute。。。。。");
	}
}
