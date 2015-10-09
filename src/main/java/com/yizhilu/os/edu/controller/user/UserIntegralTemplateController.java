package com.yizhilu.os.edu.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.yizhilu.os.edu.service.user.UserIntegralTemplateService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;
/**
 * UserIntegralTemplate管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
public class UserIntegralTemplateController extends EduBaseController{

 	@Autowired
    private UserIntegralTemplateService userIntegralTemplateService;
    
    
    
    /**
     * 修改UserIntegralTemplate
     * @param userIntegralTemplate 要修改的UserIntegralTemplate
     */
    public void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate){
     	userIntegralTemplateService.updateUserIntegralTemplate(userIntegralTemplate);
    }

   
}