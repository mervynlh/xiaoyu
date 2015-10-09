package com.yizhilu.os.edu.dao.impl.uploadVideo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.uploadVideo.UploadVideoDao;
import com.yizhilu.os.edu.entity.uploadVideo.QueryUploadVideo;
import com.yizhilu.os.edu.entity.uploadVideo.UploadVideo;


@Repository("uploadVideoDao")
public class UploadVideoDaoImpl extends GenericDaoImpl implements UploadVideoDao {
	/**
	 * 获取上传视频分页
	 * @param queryUploadVideo
	 * @param page
	 * @return
	 */
	public List<UploadVideo> getVideoUploadPage(QueryUploadVideo queryUploadVideo,PageEntity page){
		return this.queryForListPage("UploadVideoMapper.getUploadVideoPage", queryUploadVideo, page);
	}
	/**
	 * 根据ID获取上传视频
	 * @return UploadVideo
	 */
	public UploadVideo getVideoUploadByid(Long id){
		return this.selectOne("UploadVideoMapper.getUploadVideoById", id);
	}
	/**
	 * 根据ID更新上传视频
	 */
	public void updateVideoUploadById(UploadVideo uploadVideo){
		this.update("UploadVideoMapper.updateUploadVideoById", uploadVideo);
	}
	/**
	 * 创建上传视频并返回ID
	 * @return id
	 * @param uploadVideo
	 */
	public Long createVideoUpload(UploadVideo uploadVideo){
		return this.insert("UploadVideoMapper.createUploadVideo", uploadVideo);
	}
	/**
	 * 删除上传视频  改状态0正常，1删除
	 */
	public void delVideoUpload(String ids){
		this.delete("UploadVideoMapper.delUploadVideo", ids);
	}
	
}

