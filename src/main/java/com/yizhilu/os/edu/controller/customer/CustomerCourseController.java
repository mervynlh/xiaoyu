package com.yizhilu.os.edu.controller.customer;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.common.util.DateEditor;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.customer.CustomerCourse;
import com.yizhilu.os.edu.entity.customer.CustomerCourseRecord;
import com.yizhilu.os.edu.entity.customer.QueryCusCourseRecord;
import com.yizhilu.os.edu.entity.customer.QueryCustomerCourse;
import com.yizhilu.os.edu.service.customer.CustomerCourseService;
/**
 * CustomerCourse管理接口
 * User: qinggang.liu
 * Date: 2014-09-24
 */
@Controller
public class CustomerCourseController extends EduBaseController{
	private static final Logger logger = LoggerFactory.getLogger(CustomerCourseController.class);
	
	private static final String frontCustomerCourseList=getViewPath("/front/customerCourse_frontlist");

 	@Autowired
    private CustomerCourseService customerCourseService;
    
 	/**
 	 * 绑定属性参数
 	 * @param binder
 	 */
 	@InitBinder("queryCustomerCourse")
    public void initBinderqueryCustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder) {
 			binder.setFieldDefaultPrefix("queryCustomerCourse.");
 			binder.registerCustomEditor(Date.class, new DateEditor());
 		}
 	  
    /**
     * 绑定 customerCourse
     * 
     */
 	@InitBinder("customerCourse")
 	public void initBindercustomerCourse(HttpServletRequest request, ServletRequestDataBinder binder){
 		   binder.setFieldDefaultPrefix("customerCourse.");
 	}
 	
 	/**
 	 * 绑定queryCusCourseRecord 封装参数
 	 * @param queryCusCourseRecord
 	 */
 	@InitBinder("queryCusCourseRecord")
 	public void  initBinderqueryCusCourseRecord(HttpServletRequest request, ServletRequestDataBinder binder){
 		
 		binder.setFieldDefaultPrefix("queryCusCourseRecord.");
 	}
 	
    /**
     * 修改CustomerCourse
     * @param customerCourse 要修改的CustomerCourse
     */
    public void updateCustomerCourse(CustomerCourse customerCourse){
     	customerCourseService.updateCustomerCourse(customerCourse);
    }
     /**
      * 前台分页查询所有自定义课程列表
      * @param queryCustomerCourse
      * @return model
      */
   
    @RequestMapping("/front/customerCourse")
    @ResponseBody
    public ModelAndView getFrontCustomerCourseList(HttpServletRequest request,@ModelAttribute("queryCustomerCourse")QueryCustomerCourse queryCustomerCourse,@ModelAttribute("page")PageEntity page){
    	ModelAndView model=new ModelAndView();
    	try{
    		model.setViewName(frontCustomerCourseList);
    		this.setPage(page);
    		this.getPage().setPageSize(6);
    		String title=URLDecoder.decode(queryCustomerCourse.getTitle()!=null?queryCustomerCourse.getTitle():"","utf-8").replace(" ", "");
    		if(title!=null){
    		queryCustomerCourse.setTitle(title);
    		}
    		queryCustomerCourse.setStatus(0L);
    		List<QueryCustomerCourse> courseList=new ArrayList<QueryCustomerCourse>();
    		courseList=customerCourseService.getCustomerCourseList(queryCustomerCourse, page);
    		//查询加入自定义课程的人数
			int cusCourseJoinNum=customerCourseService.queryJoinNum();
			model.addObject("cusCourseJoinNum", cusCourseJoinNum);
    		model.addObject("courseList", courseList);
    		model.addObject("page", this.getPage());
    		model.addObject("queryCustomerCourse", queryCustomerCourse);
    	}catch(Exception e){
    		logger.error("getFrontCustomerCourseList-----------",e);
    	}
    	return model;
    }
    
    /**
     * 前台学员创建自定义课程
     * @param CustomerCourse
     * @return json
     */
    @RequestMapping("/front/tocommitCusCourse")
    @ResponseBody
    public Map<String,Object> customerCommitCourse(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("customerCourse")CustomerCourse customerCourse){
    	try{
    		if(customerCourse!=null){
    			customerCourse.setCreateTime(new Date());
    			customerCourse.setStatus(1L);
    			customerCourse.setCreateuserId(getLoginUserId(request));
    			customerCourse.setJoinNum(0L);
    			customerCourseService.addCustomerCourse(customerCourse);
    			this.setJson(true, "提交成功", customerCourse);
    		}
    		else{
    			this.setJson(false, "参数为空", null);
    		}
    		
    	}catch(Exception e){
    		logger.error("customerCommitCourse.error------",e);
    		this.setJson(false, "请求错误", null);
    	}
    	return json;
    }
    
    /**
     * 增加加入自定义课程的人数 投票记录
     * @param 
     */
    @RequestMapping("/front/userjoinCourse")
    @ResponseBody
    public Map<String,Object> addUserforCusCourse(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("queryCusCourseRecord")QueryCusCourseRecord queryCusCourseRecord){
    	try{  
    		if(queryCusCourseRecord!=null){
    		   queryCusCourseRecord.setUserId(getLoginUserId(request));
    		   boolean joinStatus=customerCourseService.queryuserIsjoinCourse(queryCusCourseRecord);
    			if(joinStatus){
    				this.setJson(false, "alreadyJoin", null);
    			}else{
    				CustomerCourseRecord customerCourseRecord=new CustomerCourseRecord();
    				customerCourseRecord.setCustomerCourseId(queryCusCourseRecord.getCusCourseId());
    				customerCourseRecord.setUserId(queryCusCourseRecord.getUserId());
    				customerCourseRecord.setJoinTime(new Date());
    				customerCourseService.createCustomerCourseRecord(customerCourseRecord);
    				CustomerCourse course=customerCourseService.getCustomerCourseById(queryCusCourseRecord.getCusCourseId());
    				if(course.getJoinNum()==null){
    					course.setJoinNum(1L);
    				}else{
    				course.setJoinNum(course.getJoinNum()+1L);
    				}
    				customerCourseService.updateCustomerCourse(course);
    				this.setJson(true, "success", null);
    			}
    		}
    	}catch(Exception e){
    		logger.error("addUserforCusCourse.error-------",e);
    		this.setJson(false, "请求出错", null);
    	}
    	return json;
    }
    
 
}