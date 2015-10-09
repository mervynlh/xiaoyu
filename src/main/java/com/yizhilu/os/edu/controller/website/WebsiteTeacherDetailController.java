package com.yizhilu.os.edu.controller.website;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetail;
import com.yizhilu.os.edu.entity.website.WebsiteTeacherDetailDTO;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.teacher.TeacherMajorService;
import com.yizhilu.os.edu.service.teacher.TeacherSubjectService;
import com.yizhilu.os.edu.service.website.WebsiteTeacherDetailService;
/**
 * 
 * @ClassName  com.yizhilu.os.edu.controller.website.WebsiteTeacherDetailController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月11日 下午3:16:26
 */
@Controller
public class WebsiteTeacherDetailController extends EduBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(WebsiteTeacherDetailController.class);

 	@Autowired
    private WebsiteTeacherDetailService websiteTeacherDetailService;
 	@Autowired
 	private TeacherService teacherService;
 	@Autowired
 	private TeacherSubjectService teacherSubjectService;
 	@Autowired
 	private TeacherMajorService teacherMajorService;
 	
 	
    
    
    
    /**
     * 修改WebsiteTeacherDetail
     * @param websiteTeacherDetail 要修改的WebsiteTeacherDetail
     */
    public void updateWebsiteTeacherDetail(WebsiteTeacherDetail websiteTeacherDetail){
     	websiteTeacherDetailService.updateWebsiteTeacherDetail(websiteTeacherDetail);
    }
    
   
}