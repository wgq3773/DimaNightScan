package com.dima.nightscan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DimaNightScanApplication {

	private static Log log = LogFactory.getLog(DimaNightScanApplication.class);

	public static void main(String[] args) {
		log.info("开始启动DimaNightScanApplication......");
		SpringApplication.run(DimaNightScanApplication.class, args);
		log.info("DimaNightScanApplication启动完成!!!!!!");
	}
}