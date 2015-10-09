package com.yizhilu.os.edu.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.media.sound.ModelOscillator;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.dao.user.UserDao;
import com.yizhilu.os.edu.entity.user.UserFeedback;
import com.yizhilu.os.edu.service.user.UserFeedbackService;

/**
 * UserFeedback管理接口 User: qinggang.liu Date: 2014-10-15
 */
@Controller
public class UserFeedbackController extends EduBaseController {

	@Autowired
	private UserFeedbackService userFeedbackService;

	@Autowired
	private UserDao userDao;
	
	private static final String free_back = getViewPath("/front/feed_back");
	//后台
	private static final String admin_back_list = getViewPath("/admin/feed/feed_list");
	
	private static final String toFeedbackInfo = getViewPath("/admin/feed/feed_info");

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("userFeedback")
	public void userFeedback(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userFeedback.");
	}

	/**
	 * 修改UserFeedback
	 * 
	 * @param userFeedback
	 *            要修改的UserFeedback
	 */
	public void updateUserFeedback(UserFeedback userFeedback) {
		userFeedbackService.updateUserFeedback(userFeedback);
	}

	/**
	 * 用户反馈添加
	 * @param userFeedback
	 * @return
	 */
	@RequestMapping("/front/addfreeback")
	@ResponseBody
	public Map<String,Object> addUserFeedback(HttpServletRequest request, @ModelAttribute UserFeedback userFeedback) {
		try {
			userFeedback.setCreateTime(new Date());
			userFeedbackService.addUserFeedback(userFeedback);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			e.printStackTrace();
			this.setJson(true, "false", null);
		}
		return json;
	}
	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/front/to_free_back")
	public String toAddFreeBack() {
		return free_back;
	}
	/**
	 * 用户反馈页面  这是用于后台的查看
	 * @param request
	 * @param page
	 * @param userFeedback
	 * @return
	 */
	@RequestMapping("/admin/feed/feedList")
	public ModelAndView getFeedListByCondition(HttpServletRequest request, @ModelAttribute("page") PageEntity page,@ModelAttribute UserFeedback userFeedback) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(admin_back_list);
		try {
			this.setPage(page);
			List<UserFeedback> userFeedbackList = userFeedbackService.getUserFeedbackListCondtion(userFeedback, page);
			modelAndView.addObject("userFeedbackList", userFeedbackList);//相当于setAttribute
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			setExceptionRequest(request, e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	/**
	 * 进入详情页面
	 * @Author wangzhuang
	 * @param request
	 * @return
	 */
	   @RequestMapping("/admin/feed/feedInfo/{id}")
	   public String getFeedById(Model model,@PathVariable("id") Long id){
		   try {
		
			   UserFeedback queryUserFeedback = userFeedbackService.getUserFeedbackById(id);
		       model.addAttribute("queryUserFeedback",queryUserFeedback);	   
			   
		} catch (Exception e) {
		}
		   return toFeedbackInfo;
	   }
}

   

