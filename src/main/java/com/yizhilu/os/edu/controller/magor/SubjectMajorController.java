package com.yizhilu.os.edu.controller.magor;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.major.Major;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.edu.service.major.SubjectMajorService;
import com.yizhilu.os.sysuser.entity.QuerySubject;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;
@Controller
@RequestMapping("/admin")
public class SubjectMajorController extends EduBaseController {
	
	private static final Logger logger=LoggerFactory.getLogger(SubjectMajorController.class);
	private static final String querySubjectMajorByPage=getViewPath("/admin/major/SubjectMajor_list");
	private static final String addSubjectMajorByPage=getViewPath("/admin/major/SubjectMajor_add_list");
	@Autowired
	private  SubjectMajorService subjectMajorService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private  MajorService majorService;
	
	
	@InitBinder("subjectMajor")
	public void initBinderSubjectMajor(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("subjectMajor.");
	}
	@InitBinder("major")
	public void initBinderArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("major.");
	}
	@InitBinder("page")
	public void initBinderPage(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("page.");
	}

	@RequestMapping("/SubjectMajor/page")
	public String querySubjectMajorByPage(HttpServletRequest request,@ModelAttribute SubjectMajor subjectMajor,@ModelAttribute("page") PageEntity page){
		
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
			QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
    		List<SubjectMajor> subjectMajorlist=subjectMajorService.querySubjectMajorByPage(subjectMajor, page);
          
            request.setAttribute("subjectMajorlist",subjectMajorlist );
            request.setAttribute("subjectList", gson.toJson(subjectList));
            request.setAttribute("subjectMajor", subjectMajor);
            request.setAttribute("page", getPage());
		} catch (Exception e) {
			logger.info("SubjectMajorController---------querySubjectMajorByPage",e);
		}
		
		return querySubjectMajorByPage;
	} 
	
	@RequestMapping("/Subjectmajor/delete")
	@ResponseBody
	public Map<String, Object> deleteSubjectmajorByIds(HttpServletRequest request){
		try {
			String ids="";
			String id=request.getParameter("ids");
			ids+=id.replace(",", " ").trim().replace(" ", ",");
			subjectMajorService.deleteSubjectMajorByIds(ids);
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.info("SubjectMajorController------deleteSubjectmajorByIds",e);
			this.setJson(false, "error", null);
		}
		
		return json;
	}
	
	@RequestMapping("/Subjectmajor/add/tz")
	public  String  addqueryMagorListByPgae(HttpServletRequest request,@ModelAttribute Major major){
		try {
			List<Major> maList=	majorService.queryMagorListByPgae(major);
			QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
			request.setAttribute("maList", maList);
		
		} catch (Exception e) {
			logger.info("SubjectMajorController------addqueryMagorListByPgae",e);
		}
		return addSubjectMajorByPage;
		 
		
	}
	
	@RequestMapping("/Subjectmajor/addlist")
	@ResponseBody
	public  Map<String, Object> addSubjectMajorByids(HttpServletRequest request){
		try {
 			String ids=request.getParameter("ids");
			Long subjectId=Long.parseLong(request.getParameter("subjectId"));
			Subject subject = new Subject();
			subject.setSubjectId(subjectId);
			subject = subjectService.getSubjectBySubjectId(subject);
			if (ObjectUtils.isNull(subject.getParentId()) || subject.getParentId().intValue() == 0) {
				this.setJson(false, "subjectIdError", null);
				return json;
			}
			ids=ids.replace(","," ").trim().replace(" ", ",");
			if(ObjectUtils.isNotNull(ids)){
				String[] idsArray=ids.split(",");
				List<SubjectMajor> subList=new ArrayList<SubjectMajor>();
				for (int i = 0; i < idsArray.length; i++) {
				 SubjectMajor subjectMajor=new SubjectMajor();
					
					subjectMajor.setMajorid(Long.parseLong(idsArray[i]));
					subjectMajor.setSubjectid(subjectId);
					subList.add(subjectMajor);
				}
				subjectMajorService.addSubjectMajorBatch(subList);
				
				this.setJson(true, "success", null);
			}
			
		} catch (Exception e) {
			this.setJson(false, "error", null);
			logger.info("MagorController------addSubjectMajorByids");
		}
		return json;
		
	}

	
	@RequestMapping("/Subjectmajor/majorlist/{subjectId}")
	@ResponseBody
	public Map<String, Object> querySubejctMajorById(HttpServletRequest request,@PathVariable("subjectId") Long subjectId){
		
			try {
				List<SubjectMajor> suList=subjectMajorService.querySubjectMajorBySubjectId(subjectId);
				request.setAttribute("suList", suList);
				this.setJson(true, "success", suList);
			} catch (Exception e) {
				logger.info("SubjectMajorController------querySubjectMajorBySubjectId",e);
				this.setJson(false, "error", null);
			}
		
			return json;
	}

	
	/**
	 * 获取的专业下的科目
	 * @param request
	 * @return
	 */
	@RequestMapping("/subjectmajor/getmajor")
	@ResponseBody
	public Map<String,Object> getMajor(HttpServletRequest request){
		try {
			String subjectId=request.getParameter("subjectId");
			if(StringUtils.isNotEmpty(subjectId)){
				SubjectMajor query=new SubjectMajor();
				query.setSubjectid(Long.parseLong(subjectId));
				List<SubjectMajor> list=subjectMajorService.querySubjectMajorList(query);
				this.setJson(true, null, list);
			}else{
				this.setJson(false, null, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("SubjectMajorController.getMajor---error",e);
			this.setJson(false, null, null);
		}
		return json;
	}

}
	
