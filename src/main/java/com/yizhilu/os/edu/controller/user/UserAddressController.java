package com.yizhilu.os.edu.controller.user;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.controller.course.CourseController;
import com.yizhilu.os.edu.entity.user.UserAddress;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.user.UserAddressService;

/**
 * UserAddress管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class UserAddressController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	// 地址管理
	private String address = getViewPath("/ucenter/address");// 收货地址
	
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private CourseService courseService;

	@InitBinder("userAddress")
	public void initBinderuserAddress(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userAddress.");
	}
	
	/**
	 * 用户地址
	 */
	@RequestMapping("/uc/address")
	public String userAddress(HttpServletRequest request) {
		try {
			//查询我全部的地址
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(getLoginUserId(request));
			List<UserAddress> userAddressList = userAddressService.getUserAddressList(userAddress);
			request.setAttribute("userAddressList", userAddressList);
		} catch (Exception e) {
			logger.error("UserController.userAddress", e);
			return setExceptionRequest(request, e);
		}
		return address;
	}
	/**
	 * 添加地址
	 */
	@RequestMapping("/uc/address/add")
	public String addUserAddress(HttpServletRequest request,@ModelAttribute UserAddress userAddress) {
		try {
			//当选择设为默认地址时取消用户的默认地址
			if(userAddress.getIsFirst()==1){
				//更新该用户下全部的地址为不常用地址
				UserAddress query=new UserAddress();
				query.setIsFirst(0);
				query.setUserId(getLoginUserId(request));
		    	userAddressService.updateUserAddressisFirst(query);
			}
			if(ObjectUtils.isNull(userAddress.getId())){
				//添加送货地址
				userAddress.setUserId(getLoginUserId(request));
				userAddressService.addUserAddress(userAddress);
			}else{
				//修改送货地址
				userAddress.setUserId(getLoginUserId(request));
				userAddressService.updateUserAddress(userAddress);
			}
		} catch (Exception e) {
			logger.error("UserController.addUserAddress", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/uc/address";
	}
	/**
	 * ajax创建地址
	 * @param request
	 * @param userAddress
	 * @return
	 */
	@RequestMapping("/uc/ajax/address/add")
	@ResponseBody
	public Map<String,Object> addUserAddressAjax(HttpServletRequest request,@ModelAttribute UserAddress userAddress) {
		try {
			//添加送货地址
			userAddress.setUserId(getLoginUserId(request));
			userAddressService.addUserAddress(userAddress);
			this.setJson(true, "地址保存成功", userAddress);
		} catch (Exception e) {
			logger.error("UserController.addUserAddressAjax", e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	}
	/**
	 * 删除地址
	 */
	@RequestMapping("/uc/address/del/{id}")
	@ResponseBody
	public Map<String,Object> delUserAddress(HttpServletRequest request,@PathVariable Long id) {
		try {
			int num= courseService.quertyCourseNumByAddress(id);
			if(num>0){
				this.setJson(false, "有课程正在使用该地址，暂时不允许删除", null);
			}else{
				//删除地址
				userAddressService.deleteUserAddressById(getLoginUserId(request),id);
				this.setJson(true, "操作成功", null);
			}
		} catch (Exception e) {
			logger.error("UserController.delUserAddress", e);
			this.setJson(false, "系统繁忙", null);
		}
		return json;
	}
	/**
	 * 设为常用地址
	 */
	@RequestMapping("/uc/address/common/{id}")
	public String commonUserAddress(HttpServletRequest request,@PathVariable Long id) {
		try {
			//设为常用地址
			userAddressService.updateUserAddressById(id, getLoginUserId(request));
		} catch (Exception e) {
			logger.error("UserController.commonUserAddress", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/uc/address";
	}
}