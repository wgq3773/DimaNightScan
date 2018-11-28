package com.dima.nightscan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dima.commons.utils.PropertiesUtils;
import com.dima.commons.utils.RequestUtils;
import com.dima.nightscan.pojo.ContentPOJO;
import com.dima.nightscan.urlConstant.RequestUrl;

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
	
	private static Log log = LogFactory.getLog(WeiXinNightScanPortalController.class);
	
	private static String CONFIG_PATH = "commonProperties/uuid.properties";
	
	@RequestMapping(value = RequestUrl.CONTENT_LIST_URL)
	public ModelAndView contentList(HttpServletRequest request, HttpServletResponse response){
		log.info("内容列表入口参数打印：" + RequestUtils.getIpAddress(request) + "---" +RequestUtils.getRequestParamts(request));
		ModelAndView mv = new ModelAndView();
		Boolean fromMobile = RequestUtils.isFromMobile(request);
		if (!fromMobile) {
			mv.setViewName("isNotFromMobile");
			return mv;
		}
		
		// contentList
        List<ContentPOJO> contentList = new ArrayList<ContentPOJO>();
        for (int i = 0; i < 23; i++) {
        	ContentPOJO content = new ContentPOJO();
        	String uuidString= PropertiesUtils.getProperty(CONFIG_PATH, String.valueOf(i % 10));
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