package com.dima.nightscan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dima.commons.utils.RequestUtils;
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

	/**
	 * <p>Title: portalNightScan</p>
	 * <p>Description: 入口controller</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(RequestUrl.NIGTH_SCAN_PORTAL_URL)
	public String portalNightScan(HttpServletRequest request, HttpServletResponse response) {
		log.info("portalNightScan入口参数打印：" + RequestUtils.getRequestParamts(request));
		boolean wechat = RequestUtils.isWechat(request);
		if (!wechat) {
			return "isNotFromWechat";
		}
		return "index";
	}

}