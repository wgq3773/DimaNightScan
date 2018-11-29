package com.dima.nightscan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dima.commons.constant.CommonConstant;
import com.dima.commons.constant.PropertiesFilePath;
import com.dima.commons.constant.RedisKey;
import com.dima.commons.constant.RequestUrl;
import com.dima.commons.redis.impl.JedisClientPool;
import com.dima.commons.utils.PropertiesUtils;
import com.dima.commons.utils.RequestUtils;
import com.dima.nightscan.pojo.ContentPOJO;

/**
 * <p>
 * Title: WeiXinNightScanPortalController
 * </p>
 * <p>
 * Description: 微信扫码入口
 * </p>
 * 
 * @author Dima
 * @date 2018年11月24日
 */
@Controller
public class WeiXinNightScanPortalController {
	
	@Autowired
	private JedisClientPool jedisClientPool;
	
	private static Logger log = LoggerFactory.getLogger(WeiXinNightScanPortalController.class);
	
	@RequestMapping(value = RequestUrl.CONTENT_LIST_URL)
	public ModelAndView contentList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		// 获取请求的IP地址
		String ipAddress = RequestUtils.getIpAddress(request);
		// 获取请求头参数信息
		Map<String, String> requestParamts = RequestUtils.getRequestParamts(request);
		// 根据用户请求的IP地址和user-agent生成用户ID
		String userId = RequestUtils.generateUserIdByIpUserAgent(request);
		String logStr = new StringBuilder("内容列表入口参数打印:userId:").append(userId).append(",ipAddress:").append(ipAddress)
				.append(",requestParamts:").append(requestParamts).toString();
		log.info(logStr);
		
		String user_visit_count = jedisClientPool.hget(userId, RedisKey.USER_VISIT_COUNT);
		// 首次访问
		if (StringUtils.isBlank(user_visit_count)) {
			user_visit_count = "0";
		}
		Integer count = Integer.valueOf(user_visit_count) + 1;
		jedisClientPool.hset(userId, RedisKey.USER_VISIT_COUNT, String.valueOf(count));
		
		log.info(userId + ",用户第" + count + "次访问！");
		
		if (count > 10) {
			log.info(userId + ",用户访问次数大于10次");
			String if_user_payed = jedisClientPool.hget(userId, RedisKey.IF_USER_PAYED);
			if (!StringUtils.equals(CommonConstant.YES, if_user_payed)) {
				log.info(userId + ",用户访问次数大于10次,且未支付");
				mv.setViewName("forward:/pay/" + userId);
				return mv;
			}
		}
		
//		Boolean fromMobile = RequestUtils.isFromMobile(request);
//		if (!fromMobile) {
//			mv.setViewName("isNotFromMobile");
//			return mv;
//		}
		
        List<ContentPOJO> contentList = new ArrayList<ContentPOJO>();
        for (int i = 0; i < 23; i++) {
        	ContentPOJO content = new ContentPOJO();
        	String uuidString= PropertiesUtils.getProperty(PropertiesFilePath.UUID_FILE_PATH, String.valueOf(i % 10));
        	content.setTxtString(uuidString);
        	content.setImgString("/images/jpg/" + uuidString + ".jpg");
        	content.setVideoString("/images/gif/" + uuidString + ".gif");
        	contentList.add(content);
		}
        mv.addObject("contentList", contentList);
        mv.setViewName("contentList");
		return mv;
	}

	/**
	 * <p>Title: portalNightScan</p>
	 * <p>Description: 入口controller</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {RequestUrl.EMPTY_URL})
	public String portalNightScan(HttpServletRequest request, HttpServletResponse response) {
		log.info("主页入口参数打印：" + RequestUtils.getRequestParamts(request));
		return "index";
	}

}