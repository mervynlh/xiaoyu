package com.yizhilu.os.edu.controller.user;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yizhilu.os.common.contoller.SingletonLoginUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.UserOptType;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserAccountDTO;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.sysuser.entity.SysUser;

/**
 * UserAccount管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class UserAccountController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserService userService;

	private static final String account_list = getViewPath("/admin/accout/admin_account_list"); // 账户订单页面
	private static final String account_recharge = getViewPath("/admin/accout/admin_account_recharge");// 账户充值页面

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("user")
	public void initBinderUserAccounthistory(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}

	/**
	 * 获取用户账户信息
	 * 
	 * @param request
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping("/account/list")
	public ModelAndView accountList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("user") User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(account_list);
		try {
			// 设置分页 ，默认每页10
			this.setPage(page);
			List<UserAccountDTO> userAccountList = userAccountService.getUserAccountListByCondition(this.getPage(), user);
			modelAndView.addObject("userAccountList", userAccountList);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			setExceptionRequest(request, e);
		}
		return modelAndView;
	}

	/**
	 * 更新账户状态
	 * 
	 * @param request
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/account/update/{userId}")
	@ResponseBody
	public Map<String, Object> updateAccountStatus(HttpServletRequest request, @PathVariable("userId") Long userId, @RequestParam("status") String status) {
		try {
			if (StringUtils.isNotEmpty(status)) {
				userAccountService.updateUserAccountStatus(userId, status);
				this.setJson(true, "修改成功", null);

				//查询账户
				UserAccount userAccount=userAccountService.getUserAccountByUserId(userId);
				// 记录系统用户操作
				Map<String, Object> descMap = new HashMap<String, Object>();
				descMap.put("optuser", "操作id_" + SingletonLoginUtils.getLoginUserId(request));
				descMap.put("optType", status.indexOf(UserOptType.FROZEN.toString())!=-1?"冻结":"解冻");
				descMap.put("accountId", "账户id_" + userAccount!=null?userAccount.getId():0);
				descMap.put("userId", "用户id_" + userId);
				userService.addUserOptRecord(userId, UserOptType.GIVECOURSE.toString(), SingletonLoginUtils.getSysUserId(request), this.getSysLoginLoginName(request), userAccount!=null?userAccount.getId():0L, gson.toJson(descMap));
			} else {
				this.setJson(false, "请求数据错误", null);
			}
		} catch (Exception e) {
			logger.error("updateAccountStatus", e);
			this.setJson(false, "系统错误", null);
		}
		return json;
	}

	/**
	 * 账户详情
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping("/account/info/{userId}/{flag}")
	public String getUserAccountByUserId(HttpServletRequest request, Model model, @PathVariable("userId") Long userId,@PathVariable("flag") String flag) {
		try {
			// 获得用户账户详情
			UserAccountDTO userAccountDTO = userAccountService.getuserAccountInfo(userId);
			model.addAttribute("userAccountDTO", userAccountDTO);
			model.addAttribute("flag", flag);
		} catch (Exception e) {
			logger.error("getUserAccountByUserId", e);
			return setExceptionRequest(request, e);
		}
		return account_recharge;
	}

	/**
	 * 后台账户充值,扣费
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/account/recharge")
	@ResponseBody
	public Map<String, Object> gainUserRechargeAmount(HttpServletRequest request) {
		try {
			String userId=request.getParameter("userId");//获得用户id
			String flag=request.getParameter("flag");//获得用户id
			String balance=request.getParameter("balance");//获得用户id
			if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(flag)||StringUtils.isEmpty(balance)){
				this.setJson(true, "请求数据错误", null);
				return json;
			}
			//后台账户充值,扣费
			SysUser sysUser= new SysUser();
			sysUser.setUserId(SingletonLoginUtils.getSysUserId(request));
			sysUser.setLoginName(this.getSysLoginLoginName(request));
			this.getSysLoginLoginName(request);
			boolean result=userAccountService.gainUserRechargeAmount(sysUser,Long.valueOf(userId), new BigDecimal(balance), flag);
			if(result){
				this.setJson(true, "操作成功", null);
			}else{
				this.setJson(false, "操作失败", null);
			}
		} catch (Exception e) {
			logger.error("gainUserRechargeAmount--recharge is failure", e);
			this.setJson(false, "recharge is failure", null);
		}
		return json;
	}
}