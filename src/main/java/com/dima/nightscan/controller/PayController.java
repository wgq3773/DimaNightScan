package com.dima.nightscan.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dima.api.bean.Order;
import com.dima.api.bean.PayResponse;
import com.dima.api.enums.OrderStatus;
import com.dima.api.service.PayService;
import com.dima.commons.constant.RedisKey;
import com.dima.commons.constant.RequestUrl;
import com.dima.commons.redis.impl.JedisClientPool;

@Controller
public class PayController {
	
	private static Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private JedisClientPool jedisClientPool;

	@RequestMapping(value = RequestUrl.PAY_URL + "/{userId}")
	public String pay(Model model, @PathVariable("userId") String userId){
		Order order = new Order();
		String orderId = jedisClientPool.hget(userId, RedisKey.USER_ORDER_ID);
		if (StringUtils.isBlank(orderId)) {
			orderId = userId + "-0";
		}
		orderId = userId + "-" + String.valueOf(Integer.valueOf(orderId.split("-")[1]) + 1);
		jedisClientPool.hset(userId, orderId + RedisKey.USER_ORDER_ID, orderId);
		jedisClientPool.hset(userId, RedisKey.USER_ORDER_STATUS + orderId, OrderStatus.PAYING.getStatus());
		
		order.setOrderId(orderId);
		order.setOrderGenData(new Date());
		order.setOrderStatus("0");
		order.setOrderMoney("0.02");
		order.setOrderUserId(userId);
		PayResponse payResponse = payService.pay(order);
		log.info(payResponse.toString());
		model.addAttribute("paramsMap", payResponse.getParam());
		return "pay";
	}
}
