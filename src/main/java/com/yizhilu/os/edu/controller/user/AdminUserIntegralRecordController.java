package com.yizhilu.os.edu.controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.IntegralKeyword;
import com.yizhilu.os.edu.entity.user.UserIntegralRecord;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;
import com.yizhilu.os.edu.service.user.UserIntegralRecordService;
import com.yizhilu.os.edu.service.user.UserIntegralTemplateService;

/**
 * 
 * @ClassName 
 *            com.yizhilu.os.edu.controller.user.AdminUserIntegralRecordController
 * @description 用户积分记录
 * @author :xujunbao
 * @Create Date : 2014年9月28日 下午1:06:03
 */
@RequestMapping("/admin")
@Controller
public class AdminUserIntegralRecordController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserIntegralRecordController.class);

	private static final String userIntegralList = getViewPath("/admin/user/userIntegralRecord_list");// 用户积分列表
	private static final String exchageIntegralRecord = getViewPath("/admin/user/exchangeIntegral_record");// 积分兑换记录
	@Autowired
	private UserIntegralRecordService userIntegralRecordService;
	@Autowired
	private UserIntegralTemplateService userIntegralTemplateService;
	
	//绑定变量名参数
	@InitBinder("userIntegralRecord")
	public void initBinderUserIntegralRecord(WebDataBinder binder){
		binder.setFieldDefaultPrefix("userIntegralRecord.");
	}

	/**
	 * 查询用户积分列表
	 * 
	 * @param request
	 * @param model
	 * @param userIntegral
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/integralrecordlist/{userId}")
	public String getUserIntegralList(HttpServletRequest request, Model model, @PathVariable("userId") Long userId, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页参数
			this.setPage(page);
			this.getPage().setPageSize(10);
			UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
			userIntegralRecord.setUserId(userId);
			// 查询用户积分历史列表
			List<UserIntegralRecord> userIntegralRecordList = userIntegralRecordService.getUserIntegralRecordListPage(userIntegralRecord, this.getPage());
			model.addAttribute("userIntegralRecordList", userIntegralRecordList);
			model.addAttribute("page", this.getPage());
			model.addAttribute("userId", userId);
		} catch (Exception e) {
			logger.error("getUserIntegralList", e);
			setExceptionRequest(request, e);
		}
		return userIntegralList;
	}

	/**
	 * 积分兑换记录
	 * 
	 * @param request
	 * @param model
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	@RequestMapping("/user/exchange")
	public String getExchangeIntegralRecord(HttpServletRequest request, Model model, @ModelAttribute UserIntegralRecord userIntegralRecord, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页参数
			this.setPage(page);
			this.getPage().setPageSize(10);
			//查询积分模板,获得礼品的id
			UserIntegralTemplate userIntegralTemplate=userIntegralTemplateService.getUserIntegralTemplateByKeyword(IntegralKeyword.gift.toString());
			// 查询积分兑换记录
			userIntegralRecord.setIntegralType(userIntegralTemplate.getId());
			List<UserIntegralRecord> userIntegralRecordList = userIntegralRecordService.getExchangeIntegralRecord(userIntegralRecord, this.getPage());
			model.addAttribute("userIntegralRecordList", userIntegralRecordList);
			model.addAttribute("page", this.getPage());
		} catch (Exception e) {
			logger.error("getExchangeIntegralRecord", e);
		}
		return exchageIntegralRecord;
	}
	/**
	 * 根据Id修改兑换状态
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/user/editrecord/{id}")
	public String updateIntegralRecordStatus(HttpServletRequest request,@PathVariable("id") Long id){
		try{
			//修改礼品兑换状态
			userIntegralRecordService.updateIntegralRecordStatus(id);
		}catch(Exception e){
			logger.error("updateIntegralRecordStatus", e);
			this.setJson(false, "error", null);
		}
		return "redirect:/admin/user/exchange";
	}
}
