package com.yizhilu.os.edu.controller.audition;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.audition.Audition;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.audition.AuditionService;
import com.yizhilu.os.edu.service.user.UserExpandService;

/**
 * 约课记录controller
 * @author dingkai
 * @date 2015年8月12日
 */
@Controller
@RequestMapping("/audition")
public class AuditionController extends EduBaseController{

	private static final Logger logger = LoggerFactory.getLogger(AuditionController.class);
	
	private static final String getAuditionList = getViewPath("/teacher/teacher_audition");//TODO 约课列表
	@Autowired
	private AuditionService auditionService;
	@Autowired
	private UserExpandService userExpandService;
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("audition")
	public void initBinderArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("audition.");
	}
	
	/**
	 * 添加约课记录
	 * @param audition
	 * @return
	 */
	@RequestMapping("/addAudition")
	@ResponseBody
	public Map<String,Object> addAudition(HttpServletRequest request,@ModelAttribute("audition") Audition audition){
		try{
			if(audition.getType()==0){//为我制定
				auditionService.addAudition(audition);
				this.setJson(true, "约课成功！", null);
			}else if(audition.getType()==1){//我要约课
				Long firstTime = audition.getStartTime().getTime();// 开始时间
				Long lastTime = new Date().getTime();// 当前时间
				if(firstTime.intValue()<lastTime.intValue()){// 开始时间不能小于当前时间
					this.setJson(false, "不能选择当前时间", null);
				}else {
					Long userId = getLoginUserId(request);
					UserExpandDto user = userExpandService.getUserExpandByUids(userId);
					audition.setStudentName(user.getRealname());
					audition.setStudentMobile(user.getMobile());
					auditionService.addAudition(audition);
					this.setJson(true, "约课成功！", null);
				}	
			}
		}catch(Exception e){
			logger.error("AuditionController.addAudition", e);
			this.setJson(false, "系统异常", null);
		}
		return json;
	}
}
