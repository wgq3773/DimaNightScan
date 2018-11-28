package com.dima.nightscan;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@ImportResource("classpath:applicationContext-NightScan.xml")
public class DimaNightScanApplication {

	private static Log log = LogFactory.getLog(DimaNightScanApplication.class);

	public static void main(String[] args) {
		log.info("DimaNightScanApplication开始启动............");
		SpringApplication.run(DimaNightScanApplication.class, args);
		log.info("DimaNightScanApplication启动完成!!!!!!!!!!!!");
	}
}