package com.yizhilu.os.edu.controller.uploadVideo;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.yizhilu.os.edu.service.uploadVideo.UploadVideoService;
import com.yizhilu.os.edu.service.uploadVideo.VideoClassifyService;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.uploadVideo.QueryUploadVideo;
import com.yizhilu.os.edu.entity.uploadVideo.UploadVideo;
import com.yizhilu.os.edu.entity.uploadVideo.VideoClassify;

/**
 * AdminUploadVideoController管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Controller
@RequestMapping("/admin")
public class AdminUploadVideoController extends EduBaseController{
	private static final Logger logger = LoggerFactory.getLogger(AdminUploadVideoController.class);
 	@Autowired
    private UploadVideoService uploadVideoService;
 	@Autowired
    private VideoClassifyService videoClassifyService;
 	
	private static final String getVideoClassify = getViewPath("/admin/uploadVideo/video_classify_list");
	private static final String addVideoClassify = getViewPath("/admin/uploadVideo/video_classify_add");
	private static final String updateVideoClassify = getViewPath("/admin/uploadVideo/video_classify_update");
	private static final String getUploadVideos = getViewPath("/admin/uploadVideo/upload_video_list");
	private static final String addUploadVideo = getViewPath("/admin/uploadVideo/upload_video_add");
	private static final String updateUploadVideo = getViewPath("/admin/uploadVideo/upload_video_update");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("uploadVideo")
	public void initBinderUploadVideo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("uploadVideo.");
	}
	@InitBinder("queryUploadVideo")
	public void initBinderQueryUploadVideo(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryUploadVideo.");
	}
	@InitBinder("videoClassify")
	public void initBinderVideoClassify(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("videoClassify.");
	}

	/**
	 * 视频分类表
	 * @param request
	 * @return
	 */
	@RequestMapping("/videoclassify/list")
	public ModelAndView getVideoClassify(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getVideoClassify);
		try{
			List<List<VideoClassify>> videoClassifys=new ArrayList<List<VideoClassify>>();//用于前台显示的分类集合
			List<VideoClassify> videoClassifyOnes=videoClassifyService.getVideoClassifyOne();//一级分类集合
			for(VideoClassify videoClassifyOne:videoClassifyOnes)
			{
				List<VideoClassify> videoClassifyOneAndTwo=new ArrayList<VideoClassify>();//一级分类+该分类的二级分类集合
				List<VideoClassify> videoClassifyTwos=videoClassifyService.getVideoClassifyTwoByOne(videoClassifyOne.getId());//二级分类集合
				videoClassifyOneAndTwo.add(videoClassifyOne);
				for(VideoClassify videoClassifyTwo:videoClassifyTwos)//组装一级分类和二级分类
				{
					videoClassifyTwo.setVideoNum(videoClassifyService.getUploadVideoCountByClassify(videoClassifyTwo.getId()));
					videoClassifyOneAndTwo.add(videoClassifyTwo);
				}
				videoClassifys.add(videoClassifyOneAndTwo);
			}
			modelAndView.addObject("videoClassifys",videoClassifys);
		}catch (Exception e) {
			logger.error("AdminUploadVideoController.getVideoClassify--视频分类表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 跳转添加分类
	 * @return
	 */
	@RequestMapping("/videoclassify/doadd")
	public ModelAndView doAddVideoClassify(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addVideoClassify);
		try{
			//一级分类
			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyOne();
			modelAndView.addObject("videoClassifys",videoClassifys);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.doAddVideoClassify", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 添加分类
	 * @return
	 */
	@RequestMapping("/videoclassify/add")
	public String addVideoClassify(VideoClassify videoClassify,HttpServletRequest request)
	{
		try{
			if(videoClassify!=null){
				videoClassify.setCreateTime(new Date());
				videoClassifyService.createClassify(videoClassify);
			}
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.addVideoClassify", e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/videoclassify/list";
	}
	/**
	 * 跳转更新分类
	 * @return
	 */
	@RequestMapping("/videoclassify/doupdate/{id}")
	public ModelAndView doUpdateVideoClassify(@PathVariable Long id,HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateVideoClassify);
		try{
			//一级分类
			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyOne();
			modelAndView.addObject("videoClassifys",videoClassifys);
			VideoClassify videoClassify=videoClassifyService.getVideoClassifyById(id);
			modelAndView.addObject("videoClassify",	videoClassify);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.doUpdateVideoClassify", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 更新分类
	 * @return
	 */
	@RequestMapping("/videoclassify/update")
	public String updateVideoClassify(VideoClassify videoClassify,HttpServletRequest request)
	{
		try{
			videoClassifyService.updateClassifyById(videoClassify);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.updateVideoClassify", e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/videoclassify/list";
	}
	
	/**
	 * 删除分类
	 * @return
	 */
	@RequestMapping("/videoclassify/del/{id}")
	@ResponseBody
	public Map<String,Object> delVideoClassify(@PathVariable Long id,HttpServletRequest request)
	{
		try{
			videoClassifyService.delClassifyById(id);
			this.setJson(true, "true", null);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.delVideoClassify", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	/**
	 * 获取全部一级分类
	 * @return
	 */
	@RequestMapping("/videoclassify/one")
	@ResponseBody
	public Map<String,Object> getClassifyOne()
	{
		try{
			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyOne();
			this.setJson(true, "true", videoClassifys);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.getClassifyOne", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 父Id获取二级分类
	 * @return
	 */
	@RequestMapping("/videoclassify/two/{id}")
	@ResponseBody
	public Map<String,Object> getClassifyTwoByOne(@PathVariable Long id,HttpServletRequest request)
	{
		try{
			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyTwoByOne(id);
			this.setJson(true, "true", videoClassifys);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.getClassifyOne", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	
	/**
	 * 视频分页
	 * @return 
	 */
	@RequestMapping("/uploadVideo/list")
	public ModelAndView showVideoUploadList(QueryUploadVideo queryUploadVideo,@ModelAttribute("page")PageEntity page,HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getUploadVideos);
		try{
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<UploadVideo> uploadVideos=uploadVideoService.getVideoUploadPage(queryUploadVideo, page);
			modelAndView.addObject("uploadVideos",uploadVideos);
			modelAndView.addObject("page",this.getPage());
			//一级分类
//			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyOne();
//			modelAndView.addObject("videoClassifys",videoClassifys);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.showVideoUploadList", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 删除视频
	 * @return
	 */
	@RequestMapping("/uploadVideo/del/{ids}")
	@ResponseBody
	public Map<String,Object> delUploadVideo(HttpServletRequest request,@PathVariable String ids)
	{
		try{
			if(ids!=null&&ids!=""){
				uploadVideoService.delVideoUpload(ids);
			}
			this.setJson(true, "true", null);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.videoUploadStatus", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 跳转更新视频
	 * @return
	 */
	@RequestMapping("/uploadVideo/doUpdate/{id}")
	public ModelAndView doUpdateUploadVideo(@PathVariable Long id,HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateUploadVideo);
		try{
			UploadVideo uploadVideo=uploadVideoService.getVideoUploadByid(id);
			modelAndView.addObject("uploadVideo",uploadVideo);
			//一级分类
			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyOne();
			modelAndView.addObject("videoClassifys",videoClassifys);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.doUpdateUploadVideo", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 更新上传视频
	 * @return
	 */
	@RequestMapping("/uploadVideo/update")
	public String updateUploadVideo(UploadVideo uploadVideo,HttpServletRequest request)
	{
		try{
			uploadVideoService.updateVideoUploadById(uploadVideo);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.UpdateUploadVideo", e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/uploadVideo/list";
	}
	/**
	 * 跳转添加视频
	 * @return
	 */
	@RequestMapping("/uploadVideo/doadd")
	public ModelAndView doAddUploadVideo(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addUploadVideo);
		try{
			//一级分类
			List<VideoClassify> videoClassifys=videoClassifyService.getVideoClassifyOne();
			modelAndView.addObject("videoClassifys",videoClassifys);
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.showVideoUploadList", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 添加上传视频
	 * @return
	 */
	@RequestMapping("/uploadVideo/add")
	public String addUploadVideo(UploadVideo uploadVideo,HttpServletRequest request)
	{
		try{
			uploadVideo.setUpdateTime(new Date());//添加时间
			uploadVideo.setCreateTime(new Date());//更新时间
			uploadVideo.setStatus(0);//状态正常
			DecimalFormat df = new DecimalFormat("#.00");//只保留小数点后两位位
			double size=Double.parseDouble(df.format(uploadVideo.getSize()/(1024*1024)));
			if(Double.toString(size).equals("0.0"))
			{
				size=0.1;
			}
			uploadVideo.setSize(size);//视频大小，至少为0.1，单位M
			uploadVideoService.createVideoUpload(uploadVideo);//创建视频
		}catch(Exception e)
		{
			logger.error("AdminUploadVideoController.addUploadVideo", e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/uploadVideo/list";
	}
	
	
}