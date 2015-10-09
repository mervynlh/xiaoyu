package com.yizhilu.os.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.QueryUserAccounthistory;
import com.yizhilu.os.edu.entity.user.UserAccounthistory;
import com.yizhilu.os.edu.service.user.UserAccounthistoryService;
/**
 * UserAccounthistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
public class UserAccounthistoryController extends EduBaseController{

 	@Autowired
    private UserAccounthistoryService userAccounthistoryService;
    
 	//账单历史信息
 	private static  final String account_detailed_list = getViewPath("/admin/accout/admin_account_history_list");
 	
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryUserAccounthistory")
	public void initBinderUserAccounthistory(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryUserAccounthistory.");
	}
 	
 	
 	/**
 	 * 获取用户历史信息
 	 * @param request
 	 * @param userAccounthistory
 	 * @param page
 	 * @return
 	 */
 	@RequestMapping("/admin/accout/detailed_list")
 	public ModelAndView getAccountHistory(HttpServletRequest request,@ModelAttribute("queryUserAccounthistory") QueryUserAccounthistory queryUserAccounthistory,@ModelAttribute("page") PageEntity page){
 		ModelAndView modelAndView = new ModelAndView();
 		modelAndView.setViewName(account_detailed_list);
 		try {
 			this.setPage(page);
 			List<UserAccounthistory> userHisoty = userAccounthistoryService.getUserAccountHistroyListByCondition(queryUserAccounthistory, page);
 			modelAndView.addObject("userHisoty", userHisoty);
 			modelAndView.addObject("page", page);
		} catch (Exception e) {
			setExceptionRequest(request, e);
		}
 		return modelAndView;
 	}
}