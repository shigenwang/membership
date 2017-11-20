package com.future.membership.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 推荐定时任务
 * @author tend
 *
 */
@Component
public class RecommendTask {

	private Logger logger = LoggerFactory.getLogger(RecommendTask.class);
	@Scheduled(cron = "0 0/1 * * * ?")
	public void execute(){
		logger.info("RecommendTask execute。。。。。");
	}
}
