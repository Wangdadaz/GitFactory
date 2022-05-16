package com.wang.hm_takeout;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.wang.hm_takeout.dao.mapper")
@EnableTransactionManagement
public class HmTakeoutApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HmTakeoutApplication.class);
		logger.info("启动成功！！！！");
		SpringApplication.run(HmTakeoutApplication.class, args);
	}

}
