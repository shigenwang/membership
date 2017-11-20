package com.future.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloExample {
	final static Logger logger = LoggerFactory.getLogger(HelloExample.class);

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		//下面的就是把当前日期加一个月
		cal.add(Calendar.MONTH, 3);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("today is:" + format.format(Calendar.getInstance().getTime()));
		System.out.println("yesterday is:" + format.format(cal.getTime()));
	}

}
