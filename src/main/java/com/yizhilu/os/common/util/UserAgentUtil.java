package com.yizhilu.os.common.util;
import javax.servlet.http.HttpServletRequest;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * 
 * @ClassName  com.yizhilu.os.common.util.UserAgentUtil
 * @description 获取用户操作系统 浏览器信息
 * @author :xujunbao
 * @Create Date : 2014年11月4日 下午1:37:08
 */
public class UserAgentUtil {
    	/**
         * 获得用户浏览器ua
         * 
         * @param request
         * @return String 浏览器类型
         */
        public static String getUserAgent(HttpServletRequest request) {
        	String uabrow = request.getHeader("User-Agent");//获取浏览器信息
        	UserAgent userAgent =UserAgent.parseUserAgentString(uabrow);
        	Browser browser = userAgent.getBrowser();
            OperatingSystem os = userAgent.getOperatingSystem();
        	return browser.getName().toLowerCase()+";"+os.getName().toLowerCase();
        }
        
}
