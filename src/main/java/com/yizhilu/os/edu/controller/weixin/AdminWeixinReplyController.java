package com.yizhilu.os.edu.controller.weixin;

import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.weixin.QueryWeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImageDTO;
import com.yizhilu.os.edu.entity.weixin.WeixinReply;
import com.yizhilu.os.edu.service.weixin.WeixinReplyService;
import com.yizhilu.os.edu.service.weixin.WeixinSetService;

/**
 * 微信常规回复、微信开发者配置
 * 
 * @ClassName com.yizhilu.os.edu.controller.website.AdminWeixinReplyController
 * @description
 * @author :JJL
 * @Create Date : 2014年6月7日 上午9:47:26
 */
@Controller
@RequestMapping("/admin")
public class AdminWeixinReplyController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWeixinReplyController.class);

	@Autowired
	private WeixinReplyService weixinReplyService;
	@Autowired
	private WeixinSetService weixinSetService;
	
	private static final String getWeixinReplyList = getViewPath("/admin/weixin/reply/weixinReply_list");
	private static final String getImageWeixinReplyList = getViewPath("/admin/weixin/reply/imageReply_list");
	private static final String getDefaultWeixinReplyList = getViewPath("/admin/weixin/reply/defaultReply_list");
	private static final String textReplyAdd = getViewPath("/admin/weixin/reply/textReply_add");
	private static final String imageReplyAdd = getViewPath("/admin/weixin/reply/imageReply_add");
	private static final String manyImageReplyAdd = getViewPath("/admin/weixin/reply/manyImageReply_add");
	private static final String textReplyUpdate = getViewPath("/admin/weixin/reply/textReply_update");
	private static final String imageReplyUpdate = getViewPath("/admin/weixin/reply/imageReply_update");
	private static final String manyImageReplyUpdate = getViewPath("/admin/weixin/reply/manyImageReply_update");
	
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("weixinReply")
	public void initBinderWeixinReply(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("weixinReply.");
	}
	@InitBinder("queryWeixinReply")
	public void initBinderQueryWeixinReply(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryWeixinReply.");
	}
	
    /**
     * 微信素材列表
     */
	@RequestMapping("/weixin/page")
    public ModelAndView getWeixinReplyList(HttpServletRequest request,QueryWeixinReply queryWeixinReply,@ModelAttribute("page")PageEntity page){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(getWeixinReplyList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
	   		List<WeixinReply> weixinReplys=weixinReplyService.getWeixinReplyPageList(queryWeixinReply, page);
	   		modelAndView.addObject("weixinReplys",weixinReplys);
	   		modelAndView.addObject("page",page);
		} catch (Exception e) {
			logger.error("AdminWeixinSetController.setDefaultWeixinReply", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
    }
	/**
     * 微信素材列表(常规回复专用)
     */
	@RequestMapping("/weixin/defaultpage")
    public ModelAndView getDefaultReplyList(HttpServletRequest request,QueryWeixinReply queryWeixinReply,@ModelAttribute("page")PageEntity page){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(getDefaultWeixinReplyList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
	   		List<WeixinReply> weixinReplys=weixinReplyService.getWeixinReplyPageList(queryWeixinReply, page);
	   		modelAndView.addObject("weixinReplys",weixinReplys);
	   		modelAndView.addObject("page",page);
		} catch (Exception e) {
			logger.error("AdminWeixinSetController.setDefaultWeixinReply", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
    }
    /**
    * 微信图文回复列表(多图文添加子图文专用)
    */
	@RequestMapping("/weixin/imagepage")
    public ModelAndView getImageTextReplyList(HttpServletRequest request,QueryWeixinReply queryWeixinReply,@ModelAttribute("page")PageEntity page){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(getImageWeixinReplyList);
		try {
			this.setPage(page);
			this.getPage().setPageSize(10);
	   		List<WeixinReply> weixinReplys=weixinReplyService.getImageTextReplyPageList(queryWeixinReply, page);
	   		modelAndView.addObject("weixinReplys",weixinReplys);
	   		modelAndView.addObject("page",page);
		} catch (Exception e) {
			logger.error("AdminWeixinSetController.getImageTextReplyList", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
    }
	
	/**
     * 跳转添加回复 素材
     */
	@RequestMapping("/weixin/doadd/{msgType}")
    public ModelAndView doAddReply(@PathVariable int msgType,HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		if(msgType==1){
			modelAndView.setViewName(textReplyAdd);
		}else if(msgType==2){
			modelAndView.setViewName(imageReplyAdd);
		}else if(msgType==3){
			modelAndView.setViewName(manyImageReplyAdd);
		}
		return modelAndView;
    }
	
   /**
    *添加回复素材 
    * @return
    */
	@RequestMapping("/weixin/add")
   public String addWeixinReply(HttpServletRequest request,WeixinReply weixinReply){
		try{
			Date date=new Date();
			weixinReply.setCreateTime(date);//创建时间
			weixinReply.setUpdateTime(date);//更新时间
			weixinReplyService.addWeixin(weixinReply);
			if(weixinReply.getMsgType()==3){//类型为多图文时添加多图文从属
				if(weixinReply.getManyImageIds()!=null&&!weixinReply.getManyImageIds().equals(""))//多图文从属图文不为空
		   		{
		   			weixinReplyService.addWeixinManyImageText(weixinReply.getId(),weixinReply.getManyImageIds());
		   		}
			}
		}catch(Exception e)
		{
			logger.error("AdminWeixinReplyController.addWeixinReply",e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/weixin/page";
   }
	/**
     * 跳转更新回复 素材
     */
	@RequestMapping("/weixin/doupdate/{id}")
    public ModelAndView doUpdateReply(@PathVariable Long id,HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		try{
			WeixinReply weixinReply=weixinReplyService.getWeixinById(id);
			modelAndView.addObject("weixinReply",weixinReply);
			if(weixinReply.getMsgType()==1){//文本
				modelAndView.setViewName(textReplyUpdate);
			}else if(weixinReply.getMsgType()==2){//图文
				modelAndView.setViewName(imageReplyUpdate);
			}else if(weixinReply.getMsgType()==3){//多图文
				modelAndView.setViewName(manyImageReplyUpdate);
				List<WeixinManyImageDTO> weixinManyImageDTOs=weixinReplyService.getWeixinManyImageTextByManyId(id);
				String str="";
				for(WeixinManyImageDTO weixinManyImageDTO:weixinManyImageDTOs){
					str+="|"+weixinManyImageDTO.getImageId()+"#"+weixinManyImageDTO.getTitle();
				}
				weixinReply.setManyImageIds(str);//子图文id，title集合
				modelAndView.addObject("weixinReply",weixinReply);//包含子图文
			}
		}catch(Exception e){
			logger.error("AdminWeixinReplyController.doUpdateReply",e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
    }
   /**
    * 更新回复素材
    * @return
    */
	@RequestMapping("/weixin/update")
   public String updateWeixinReply(HttpServletRequest request,WeixinReply weixinReply){
	   	try{
	   		weixinReplyService.updateWeixinReply(weixinReply);
	   		if(weixinReply.getManyImageIds()!=null&&!weixinReply.getManyImageIds().equals(""))//多图文从属图文不为空
	   		{
	   			weixinReplyService.delWeixinManyImageTextByManyId(weixinReply.getId());//先删除多图文和图文的关联   
	   			weixinReplyService.addWeixinManyImageText(weixinReply.getId(),weixinReply.getManyImageIds());//添加多图文和图文的关联   
	   		}
	   	}catch(Exception e){
	   		logger.error("AdminWeixinReplyController.updateWeixinReply",e);
			return this.setExceptionRequest(request, e);
	   	}   
	   	return "redirect:/admin/weixin/page";
   }
  
    /**
    * 根据回复类型删除回复
    * @return
    */
	@RequestMapping("/weixin/del")
	@ResponseBody
   public Map<String,Object> delWeixinReply(WeixinReply weixinReply,HttpServletRequest request){
	   	try{
	   		Long id=weixinReply.getId();
	   		int msgType=weixinReply.getMsgType();
	   		if(msgType==1)//文本回复
	   		{
	   			weixinReplyService.deleteWeixin(id);//删除回复素材
	   			weixinSetService.delWeixinSetReply(id);//删除对回复素材的引用
	   		}else if(msgType==2)//图文回复
	   		{
	   			weixinReplyService.delWeixinManyImageTextById(id);//删除关联
	   			weixinReplyService.deleteWeixin(id);//删除回复表中图文
	   			weixinSetService.delWeixinSetReply(id);//删除对回复素材的引用
	   		}else if(msgType==3)//多图文回复
	   		{
	   			weixinReplyService.delWeixinManyImageTextByManyId(id);//删除关联
	   			weixinReplyService.deleteWeixin(id);//删除回复表中图文实体
	   			weixinSetService.delWeixinSetReply(id);//删除对回复素材的引用
	   		}
	   		this.setJson(true, "true", null);
	   	}catch(Exception e){
	   		logger.error("AdminWeixinReplyController.delWeixinReply",e);
	   		this.setJson(false, "error", null);
	   	}
	   	return json;
   }
	
   
}
