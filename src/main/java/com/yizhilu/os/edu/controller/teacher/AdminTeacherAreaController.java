package com.yizhilu.os.edu.controller.teacher;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.service.user.UserAreaService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.controller.teacher.AdminTeacherAreaController
 * @description 讲师地域管理
 * @author :WangKaiping
 * @Create Date : 2015年08月04日 16:40:25
 */
@Controller
@RequestMapping("/admin")
public class AdminTeacherAreaController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminTeacherAreaController.class);
	
	// 讲师地域列表返回路径
	private static final String areaList = getViewPath("/admin/teacher/teacher_select_area_list");
	private static final String teacherAreaList = getViewPath("/admin/teacher/teacher_area_list");
	
	@Autowired
	private UserAreaService userAreaService;

	// 绑定变量参数
	@InitBinder("userArea")
	public void initBinderUserArea(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userArea.");
	}
	
	/**
	 * 查询城市区域列表
	 * @param request
	 * @param userArea
	 * @return
	 */
	@RequestMapping("/teacher/area/list")
	public String teacherAreaList(HttpServletRequest request, @ModelAttribute UserArea userArea){
		try {
			// 查询省份列表
			List<UserArea> provinceList = userAreaService.getProvinceList();
			request.setAttribute("provinceList", provinceList);
			// 获得城市区域列表
			userArea.setCheckShow(2);
			List<UserArea> cityList = userAreaService.getUserAreaList(userArea);
			request.setAttribute("cityList", cityList);
			request.setAttribute("userArea", userArea);
		} catch (Exception e) {
			logger.error("AdminTeacherAreaController.queryAreaList", e);
			return setExceptionRequest(request, e);
		}
		return teacherAreaList;
	}
	
	/**
	 * 查询城市区域列表(选择显示)
	 * @param request
	 * @param userArea
	 * @return
	 */
	@RequestMapping("/teacher/select/area/list")
	public String queryAreaList(HttpServletRequest request, @ModelAttribute UserArea userArea){
		try {
			// 查询省份列表
			List<UserArea> provinceList = userAreaService.getProvinceList();
			request.setAttribute("provinceList", provinceList);
			// 获得城市区域列表
			userArea.setCheckShow(1);// 查询未显示的
			userArea.setAreaType(2);
			List<UserArea> cityList = userAreaService.getUserAreaList(userArea);
			request.setAttribute("cityList", cityList);
			request.setAttribute("userArea", userArea);
		} catch (Exception e) {
			logger.error("AdminTeacherAreaController.queryAreaList", e);
			return setExceptionRequest(request, e);
		}
		return areaList;
	}
	
	/**
	 * 修改城市区域显示状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacher/area/updateShow")
	@ResponseBody
	public Map<String, Object> updateAreaShow(HttpServletRequest request){
		try {
			// 获得要修改显示状态的地域ID集合字符串
			String ids = request.getParameter("ids");
			// 修改显示状态
			userAreaService.updateUserAreaShow(ids);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminTeacherAreaController.updateAreaShow", e);
			this.setJson(false, "系统错误", null);
		}
		return json;
	}
	
	/**
	 * 修改城市区域显示状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacher/area/updateHide")
	@ResponseBody
	public Map<String, Object> delAreaShow(HttpServletRequest request){
		try {
			// 获得要修改显示状态的地域ID集合字符串
			String ids = request.getParameter("ids");
			// 修改显示状态
			userAreaService.delUserAreaShow(ids);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminTeacherAreaController.delAreaShow", e);
			this.setJson(false, "系统错误", null);
		}
		return json;
	}

}
