package com.dima.nightscan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/testtest")
	@ResponseBody
	public String test(){
		return "TestDimaApplication TestController get started......";
	}
}
