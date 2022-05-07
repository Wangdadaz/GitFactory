package com.wang.hm_takeout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HmTakeoutApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HmTakeoutApplication.class);
		logger.info("启动成功！！！！");
		SpringApplication.run(HmTakeoutApplication.class, args);
	}

}
