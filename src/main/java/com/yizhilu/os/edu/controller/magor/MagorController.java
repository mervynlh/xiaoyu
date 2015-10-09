package com.yizhilu.os.edu.controller.magor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tools.ant.types.resources.selectors.Majority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;

import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.service.major.MajorService;

@Controller
@RequestMapping("/admin")
public class MagorController extends EduBaseController {
	
	private static final Logger logger=LoggerFactory.getLogger(MagorController.class);
	private  static  final String  queryMagorByPgae=getViewPath("/admin/major/major_list");
	private static final String  tzAddMagor=getViewPath("/admin/major/major_add");
	@Autowired
	private  MajorService majorService;
	
	@InitBinder("major")
	public void initBinderArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("major.");
	}
	/**
	 * 查询科目分页列表
	 */
	@RequestMapping("/magor/page")
	public  String  queryMagorListByPgae(HttpServletRequest request,Major major){
		try {
			List<Major> maList=	majorService.queryMagorListByPgae(major);
			request.setAttribute("maList", maList);
		} catch (Exception e) {
			logger.info("MagorController------queryMagorListByPgae",e);
		}
		return queryMagorByPgae;
		 
		
	}
	
	
	@RequestMapping("/mgor/update")
	@ResponseBody
	public Map<String, Object>  updateMagorById(HttpServletRequest request){
		try {
		  Long id= Long.parseLong(request.getParameter("id"));
		  int status= Integer.parseInt(request.getParameter("status"));
		  Major magor=new Major();
		  magor.setId(id);
		  magor.setStatus(status);
		  majorService.updateMagorById(magor);
		  this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.info("MagorController-----updateMagorById",e);
			this.setJson(false, "error", null);
		
		}
		
		return json;
	}
		
	@RequestMapping("/mgor/add/tz")
	public  String tzAddMagor(){
		
		return  tzAddMagor;
		
	}
	
	@RequestMapping("/magor/tj")
	public  String  addMagor(HttpServletRequest request,@ModelAttribute("major") Major major){
		
		try {
			major.setSort(0);
			major.setStatus(0);
			majorService.addMagor(major);
		
		} catch (Exception e) {
			logger.info("MagorController-----addMagor",e);
		}
        return "redirect:/admin/magor/page";
	}
}
