package com.yizhilu.os.edu.controller.assess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.common.EduBaseController;

@Controller
@RequestMapping("/admin")
public class AdminAssessController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminAssessController.class);
	
	MemCache memCache = MemCache.getInstance();
	
	private static final String assess_manage=getViewPath("/admin/assess/assess_manage");//主页评论管理

	/**
	 * 主页评论管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/index/assess")
	public String indexAssessManage(HttpServletRequest request){
		try {
			 String encoding="UTF-8";
             File file=new File(request.getSession().getServletContext().getRealPath("/")+CommonConstants.assessFile);
             if(file.isFile() && file.exists()){ //判断文件是否存在
                 InputStreamReader read = new InputStreamReader(
                 new FileInputStream(file),encoding);//考虑到编码格式
                 BufferedReader bufferedReader = new BufferedReader(read);
                 String lineTxt = "";
                 String content="";
                 while((lineTxt = bufferedReader.readLine()) != null){
                 	content+=lineTxt;
                 }
                 request.setAttribute("assessContent",content);
                 read.close();
             }
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("AdminAssessController.getAssessFile()---error()",e);
			return setExceptionRequest(request, e);
		}
		return assess_manage;
	}
	/**
	 * 保存主页评论文件
	 * @param request
	 * @return
	 */
	@RequestMapping("/index/saveAssessFile")
	@ResponseBody
	public Map<String,Object> saveAssessFile(HttpServletRequest request){
		BufferedWriter writer=null;
		OutputStreamWriter write =null;
		try {
			String content=request.getParameter("content");
			File f = new File(request.getSession().getServletContext().getRealPath("/")+CommonConstants.assessFile);      
	        write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");      
	        writer=new BufferedWriter(write);          
	        writer.write(content);   
	        writer.close();  
	        write.close();
	        memCache.remove(MemConstans.ASSESS_INDEX); 
	        this.setJson(true, "保存成功", null);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("AdminAssessController.saveAssessFile--error",e);
			this.setJson(false, "系统繁忙", null);
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return json;
	}
}