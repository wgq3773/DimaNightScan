package com.dima.nightscan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dima.commons.constant.PropertiesFilePath;
import com.dima.commons.constant.RequestUrl;
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
	
	private static Logger log = LoggerFactory.getLogger(WeiXinNightScanPortalController.class);
	
	@RequestMapping(value = RequestUrl.CONTENT_LIST_URL)
	public ModelAndView contentList(HttpServletRequest request, HttpServletResponse response){
		//内容列表入口参数打印：0:0:0:0:0:0:0:1---{accept-language=zh-CN,zh;q=0.9, host=localhost:8087, upgrade-insecure-requests=1, connection=keep-alive, accept-encoding=gzip, deflate, br, user-agent=Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36, accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8}
		log.info("内容列表入口参数打印：" + RequestUtils.getIpAddress(request) + "---" +RequestUtils.getRequestParamts(request));
		ModelAndView mv = new ModelAndView();
//		Boolean fromMobile = RequestUtils.isFromMobile(request);
//		if (!fromMobile) {
//			mv.setViewName("isNotFromMobile");
//			return mv;
//		}
		
		// contentList
        List<ContentPOJO> contentList = new ArrayList<ContentPOJO>();
        for (int i = 0; i < 23; i++) {
        	ContentPOJO content = new ContentPOJO();
        	String uuidString= PropertiesUtils.getProperty(PropertiesFilePath.UUID_FILE_PATH, String.valueOf(i % 10));
        	content.setTxtString(uuidString);
        	content.setImgString("/images/" + uuidString + ".gif");
        	content.setVideoString("/images/" + uuidString + ".gif");
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