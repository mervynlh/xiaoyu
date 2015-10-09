package com.yizhilu.os.common.intercepter;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.edu.service.user.UserAreaService;
import com.yizhilu.os.edu.service.website.WebsiteNavigateService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * 
 * @ClassName  com.yizhilu.os.common.intercepter.LimitIntercepterForWebsite
 * @description 网站配置管理拦截器
 * @author :xujunbao
 * @Create Date : 2014年10月9日 下午5:50:49
 */
public class LimitIntercepterForWebsite extends HandlerInterceptorAdapter{
	 //logger
	 Logger logger = LoggerFactory.getLogger(LimitIntercepterForWebsite.class);
	 //获取memcache
     MemCache memCache = MemCache.getInstance();
     @Autowired
     private WebsiteProfileService websiteProfileService;
 	 @Autowired
 	 private WebsiteNavigateService websiteNavigateService;
 	@Autowired
	private SubjectService subjectService;
 	@Autowired
 	private UserAreaService userAreaService;
	@Autowired
	private MajorService majorService;
 	 
     @Override
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         super.afterCompletion(request, response, handler, ex);
     }

     @Override
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         super.postHandle(request, response, handler, modelAndView);
     }
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         try{
//        	 if(SingletonLoginUtils.JudgeIsMoblie(request)){
//        		 response.sendRedirect(CommonConstants.wzPath);// 手机访问跳转微站
//        		 return false;
//        	 }
        	//获得网站配置
          	Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
            //获得LOGO配置
          	Map<String,Object> logomap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.logo.toString());
          	request.setAttribute("websitemap",websitemap);
          	request.setAttribute("logomap",logomap);
          	//网站统计代码
            Map<String,Object> tongjiemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.censusCode.toString());
            request.setAttribute("tongjiemap",tongjiemap);
          	//网站导航配置
          	Map<String,Object> navigatemap=websiteNavigateService.getWebNavigate();
      		request.setAttribute("navigatemap",navigatemap);
      		//购买方式配置
          	Map<String,Object> salemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
      		request.setAttribute("salemap",salemap);
      		//网站开关配置
          	Map<String,Object> keywordmap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
      		request.setAttribute("keywordmap",keywordmap);
      		//底部菜单
//      		List<List<HelpMenu>> helpMenuList=helpMenuService.getHelpMenuAll();
//      		request.setAttribute("helpMenuList", helpMenuList);
      		// 城市列表
      		List<UserArea> areaLiat = userAreaService.queryUserAreaList();
      		request.setAttribute("areaLiat", areaLiat);

      		//导航专业
      		QuerySubject querySubject = new QuerySubject();
      		List<Subject> navSubjectList=subjectService.getSubjectListByLevel(querySubject);
      		request.setAttribute("navSubjectList",navSubjectList);
			// 城市ID和城市名
			Long cityId = SingletonLoginUtils.getCurrentCityId(request);
			for (UserArea area : areaLiat) {
				if (area.getId().intValue() == cityId.intValue()) {
					request.setAttribute("cityId", cityId);
					request.setAttribute("cityName", area.getAreaName());
					break;
				}
			}
			// 全部科目列表
			List<Major> majorList = majorService.queryMajorAllList();
			request.setAttribute("majorList", majorList);
			// 全部二级专业(年级)
			List<Subject> twoSubjectList = subjectService.getSubjectTwoList();
			request.setAttribute("twoSubjectList", twoSubjectList);
			// 全部二级专业(年级)
			List<Subject> oneSubjectList = subjectService.getSubjectOneList();
			request.setAttribute("oneSubjectList", oneSubjectList);
         }catch(Exception e){
        	 logger.error("LimitIntercepterForWebsite.preHandle 网站配置出错",e);
         }
		
    	return super.preHandle(request, response, handler);
    } 
}
