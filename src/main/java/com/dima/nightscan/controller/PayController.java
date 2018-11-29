package com.dima.nightscan.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dima.api.bean.Order;
import com.dima.api.bean.PayResponse;
import com.dima.api.service.PayService;
import com.dima.commons.constant.RequestUrl;
import com.dima.commons.utils.CommonUtils;

@Controller
public class PayController {
	
	private static Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private PayService payService;

	@RequestMapping(value = RequestUrl.PAY_URL)
	public String pay(Model model){
		Order order = new Order();
		order.setOrderId(CommonUtils.getUUID());
		order.setOrderGenData(new Date());
		order.setOrderStatus("0");
		order.setOrderMoney("0.02");
		order.setOrderUserId(order.getOrderId());
		PayResponse payResponse = payService.pay(order);
		log.info(payResponse.toString());
		model.addAttribute("paramsMap", payResponse.getParam());
		return "pay";
	}
}
