package com.yizhilu.os.edu.service.uploadVideo;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.uploadVideo.QueryUploadVideo;
import com.yizhilu.os.edu.entity.uploadVideo.UploadVideo;



public interface UploadVideoService {
	/**
	 * 获取上传视频分页
	 * @param queryUploadVideo
	 * @param page
	 * @return
	 */
	public List<UploadVideo> getVideoUploadPage(QueryUploadVideo queryUploadVideo,PageEntity page);
	/**
	 * 根据ID获取上传视频
	 * @return UploadVideo
	 */
	public UploadVideo getVideoUploadByid(Long id);
	/**
	 * 根据ID更新上传视频
	 */
	public void updateVideoUploadById(UploadVideo uploadVideo);
	/**
	 * 创建上传视频并返回ID
	 * @return id
	 * @param uploadVideo
	 */
	public Long createVideoUpload(UploadVideo uploadVideo);
	/**
	 * 删除上传视频  改状态0正常，1删除
	 */
	public void delVideoUpload(String ids);
	
}

