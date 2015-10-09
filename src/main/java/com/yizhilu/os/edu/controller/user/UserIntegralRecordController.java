package com.yizhilu.os.edu.controller.user;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.service.user.UserIntegralRecordService;
import com.yizhilu.os.edu.service.user.UserIntegralTemplateService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.IntegralKeyword;
import com.yizhilu.os.edu.entity.user.UserIntegralRecord;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;
/**
 * UserIntegralRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
@RequestMapping("/uc")
public class UserIntegralRecordController extends EduBaseController{

 	@Autowired
    private UserIntegralRecordService userIntegralRecordService;
	@Autowired
 	private UserIntegralTemplateService userIntegralTemplateService;
    
    
 	
 	private static final String userUpIntegralRecord = getViewPath("/ucenter/u-upIntegralRecord");	//用户上下线积分反馈信息
 	
    
    /**
     * 修改UserIntegralRecord
     * @param userIntegralRecord 要修改的UserIntegralRecord
     */
    public void updateUserIntegralRecord(UserIntegralRecord userIntegralRecord){
     	userIntegralRecordService.updateUserIntegralRecord(userIntegralRecord);
    }
    
    /**
     * 获取学员上线记录信息
     * @param request
     * @param response
     * @param page
     * @return
     */
    @RequestMapping("/getUserIntegralRecord")
    public ModelAndView getUserIntegralRecord(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("page") PageEntity page){
    	ModelAndView modelAndView  = new ModelAndView(userUpIntegralRecord);
    	try {
    		UserIntegralRecord userIntegralRecord = new UserIntegralRecord();
    		userIntegralRecord.setUserId(EduBaseController.getLoginUserId(request));
    		userIntegralRecord.setIntegralType(13L);
    		UserIntegralTemplate userIntegralTemplate=userIntegralTemplateService.getUserIntegralTemplateByKeyword(IntegralKeyword.rebate.toString());
    		List<UserIntegralRecord>  userIntergralRecordList=  userIntegralRecordService.getUserDownIntegralRecordListPage(userIntegralRecord, page);
    		modelAndView.addObject("userIntergralRecordList", userIntergralRecordList);
    		modelAndView.addObject("page", page);
    		modelAndView.addObject("score", userIntegralTemplate.getScore());
    		modelAndView.addObject("userId", EduBaseController.getLoginUserId(request));
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
    	return modelAndView;
    }
}