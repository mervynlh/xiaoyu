package com.yizhilu.os.edu.service.impl.uploadVideo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.uploadVideo.UploadVideoDao;
import com.yizhilu.os.edu.entity.uploadVideo.QueryUploadVideo;
import com.yizhilu.os.edu.entity.uploadVideo.UploadVideo;
import com.yizhilu.os.edu.service.uploadVideo.UploadVideoService;


@Service("uploadVideoService")
public class UploadVideoServiceImpl implements UploadVideoService{
	@Autowired
	private UploadVideoDao uploadVideoDao;
	
	/**
	 * 获取上传视频分页
	 * @param queryUploadVideo
	 * @param page
	 * @return
	 */
	public List<UploadVideo> getVideoUploadPage(QueryUploadVideo queryUploadVideo,PageEntity page){
		return uploadVideoDao.getVideoUploadPage(queryUploadVideo, page);
	}
	/**
	 * 根据ID获取上传视频
	 * @return UploadVideo
	 */
	public UploadVideo getVideoUploadByid(Long id){
		return uploadVideoDao.getVideoUploadByid(id);
	}
	/**
	 * 根据ID更新上传视频
	 */
	public void updateVideoUploadById(UploadVideo uploadVideo){
		uploadVideoDao.updateVideoUploadById(uploadVideo);
	}
	/**
	 * 创建上传视频并返回ID
	 * @return id
	 * @param uploadVideo
	 */
	public Long createVideoUpload(UploadVideo uploadVideo){
		return uploadVideoDao.createVideoUpload(uploadVideo);
	}
	/**
	 * 删除上传视频  改状态0正常，1删除
	 */
	public void delVideoUpload(String ids){
		uploadVideoDao.delVideoUpload(ids);
	}
	
}

