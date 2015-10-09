package com.yizhilu.os.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserIntegralGift;
import com.yizhilu.os.edu.service.user.UserIntegralGiftService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.controller.user.UserIntegralGiftController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月30日 下午4:52:15
 */
@Controller
@RequestMapping("/uc")
public class UserIntegralGiftController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserIntegralGiftController.class);

	private static final String toGiftList = getViewPath("/ucenter/integralGift");// 礼品页面列表
	private static final String toMyGiftList = getViewPath("/ucenter/myGift");// 我的礼品页面列表
	@Autowired
	private UserIntegralGiftService userIntegralGiftService;

	/**
	 * 查询礼品列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/integift")
	public String getGiftList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页参数
			this.setPage(page);
			this.getPage().setPageSize(9);
			// 查询礼品列表
			UserIntegralGift userIntegralGift = new UserIntegralGift();
			List<UserIntegralGift> userIntegralGiftList = userIntegralGiftService.getUserIntegralGiftListPage(userIntegralGift, this.getPage());
			model.addAttribute("userIntegralGiftList", userIntegralGiftList);// 礼品列表数据
			model.addAttribute("page", this.getPage());// 分页参数
		} catch (Exception e) {
			logger.error("UserIntegralGiftController.getGiftList", e);
			return setExceptionRequest(request, e);
		}
		return toGiftList;
	}

	/**
	 * 获得我的礼品列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/mygift")
	public String getMyGiftList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
		try {
			// 设置分页参数
			this.setPage(page);
			this.getPage().setPageSize(9);
			List<UserIntegralGift> userIntegralGiftList = userIntegralGiftService.getIntegralGiftListByUserId(getLoginUserId(request), this.getPage());
			model.addAttribute("userIntegralGiftList", userIntegralGiftList);// 我的礼品列表数据
			model.addAttribute("page", this.getPage());// 分页参数
		} catch (Exception e) {
			logger.error("UserIntegralGiftController.getMyGiftList", e);
			return setExceptionRequest(request, e);
		}
		return toMyGiftList;
	}
}