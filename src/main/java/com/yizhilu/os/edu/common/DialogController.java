package com.yizhilu.os.edu.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.entity.common.Dialog;
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.entity.course.CourseMinus;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderDetailAssess;
import com.yizhilu.os.edu.entity.teacher.TeacherStyle;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseMinusService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.TrxorderDetailAssessService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.teacher.TeacherStyleService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.common.DialogController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年11月6日 上午10:51:15
 */
@Controller
public class DialogController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(DialogController.class);

	private static final String getDialogHtml = "/common/dialog";// 弹出窗页面
	private static final String getSNSDialogHtml = "/common/sns_dialog";// 弹出窗页面
	private static final String getUcDialogHtml= "/common/u_dialog";// 个人中心弹出窗新页面
	// 绑定变量参数
	@InitBinder("dialog")
	public void initBinderDialog(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("dialog.");
	}
	
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseMinusService courseMinusService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseKpointsService courseKpointsService; 
	@Autowired
	private TeacherStyleService teacherStyleService;
	@Autowired
	private TrxorderDetailAssessService trxorderDetailAssessService;
	
	/**
	 * 获取弹出窗页面
	 * 
	 * @param request
	 * @param model
	 * @param dialog
	 * @return
	 */
	@RequestMapping("/common/snsdialog")
	public String getSNSDialog(HttpServletRequest request, Model model, @ModelAttribute Dialog dialog) {
		try {
			model.addAttribute("dialog", dialog);
		} catch (Exception e) {
			logger.error("getSNSDialog", e);
			return setExceptionRequest(request, e);
		}
		return getSNSDialogHtml;
	}
	
	/**
	 * 弹窗页面
	 * @param request
	 * @param model
	 * @param dialog
	 * @return
	 */
	@RequestMapping("/common/dialog")
	public String getDialogs(HttpServletRequest request,@ModelAttribute Dialog dialog) {
		try {
			// 获取教师课程信息
			if(dialog.getIndex().intValue()==7){
				Teacher teacher=teacherService.getTeacherById(Long.parseLong(dialog.getParams()));
				request.setAttribute("teacher", teacher);
			}
			//弹窗查看视频 需要的数据
			if(dialog.getIndex().intValue()==14){
				if(StringUtils.isNotEmpty(dialog.getParams())){
					TeacherStyle teacherStyle= teacherStyleService.queryStyleById(Long.parseLong(dialog.getParams()));
					request.setAttribute("teacherStyle", teacherStyle);
				}
			}
			//弹窗查看图片 需要的数据
			if(dialog.getIndex().intValue()==15){
				if(StringUtils.isNotEmpty(dialog.getParams())){
					TeacherStyle teacherStyle= teacherStyleService.queryStyleById(Long.parseLong(dialog.getParams()));
					request.setAttribute("teacherStyle", teacherStyle);
				}
			}
			request.setAttribute("dialog", dialog);
		} catch (Exception e) {
			logger.error("getDialogs", e);
			return setExceptionRequest(request, e);
		}
		return getDialogHtml;
	}
	/**
	 * 个人中心弹窗新页面
	 * @param request
	 * @param model
	 * @param dialog
	 * @return
	 */
	@RequestMapping("/common/uc/dialog")
	public String getUcDialog(HttpServletRequest request,@ModelAttribute Dialog dialog) {
		try {
			Long userId = getLoginUserId(request);
			//弹窗重新选择上课时间 需要的数据
			if(dialog.getIndex().intValue()==2){
				User user = this.userService.getUserById(userId);
				TrxorderDetail trxorderDetail=trxorderDetailService.getTrxorderDetailById(Long.parseLong(dialog.getParams()));
				if(user.getUserType()==0){
					Teacher teacher=teacherService.getTeacherById(trxorderDetail.getTeacherId());
					request.setAttribute("teacher", teacher);
					request.setAttribute("userType", 0);
				}else {
					UserExpandDto userDto = this.userService.queryUserExpand(trxorderDetail.getUserId());
					request.setAttribute("user", userDto);
					request.setAttribute("userType", 1);
				}
				request.setAttribute("trxorderDetail", trxorderDetail);
			}
			//弹窗编辑优惠 需要的数据
			if(dialog.getIndex().intValue()==15){
				if(StringUtils.isNotEmpty(dialog.getParams())){
					CourseMinus minus= courseMinusService.queryCourseMinusById(Long.parseLong(dialog.getParams()));
					request.setAttribute("minus", minus);
				}
			}
			//弹窗教师修改课时 需要的数据
			if(dialog.getIndex().intValue()==19){
				if(StringUtils.isNotEmpty( dialog.getParams())){
				    CourseKpoints courseKpoints=courseKpointsService.queryKpointsById(Long.parseLong(dialog.getParams()));
					request.setAttribute("kpoint", courseKpoints);
				}
			}
			// 查看课时小结
			if(dialog.getIndex().intValue()==21){
				User user = this.userService.getUserById(userId);
				TrxorderDetail trxorderDetail=trxorderDetailService.getTrxorderDetailById(Long.parseLong(dialog.getParams()));
				if(user.getUserType()==0){//学生
					dialog.setContent(trxorderDetail.getUserSummary());
				}else if(user.getUserType()==1){//老师
					dialog.setContent(trxorderDetail.getTeacherSummary());
				}
			}
			// 查看课时评价
			if(dialog.getIndex().intValue()==22){
				TrxorderDetailAssess trxorderDetailAssess = this.trxorderDetailAssessService.getDetailAssessInfoByDetailId(Long.parseLong(dialog.getParams()));
				dialog.setContent(trxorderDetailAssess.getContent());
			}
			request.setAttribute("dialog", dialog);
		} catch (Exception e) {
			logger.error("getDialogs", e);
			return setExceptionRequest(request, e);
		}
		return getUcDialogHtml;
	}
}
