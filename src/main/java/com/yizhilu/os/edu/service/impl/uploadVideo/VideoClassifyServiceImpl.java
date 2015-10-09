package com.yizhilu.os.edu.service.impl.uploadVideo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.uploadVideo.VideoClassifyDao;
import com.yizhilu.os.edu.entity.uploadVideo.VideoClassify;
import com.yizhilu.os.edu.service.uploadVideo.VideoClassifyService;


@Service("videoClassifyService")
public class VideoClassifyServiceImpl implements VideoClassifyService{
	@Autowired
	private VideoClassifyDao videoClassifyDao;
	
	/**
	 * 查询所有一级分类 
	 * @return VideoClassify
	 */
    public List<VideoClassify> getVideoClassifyOne(){
    	return videoClassifyDao.getVideoClassifyOne();
    }
    /**
	 * 根据一级分类ID查询二级分类 
	 * @return VideoClassify
	 */
	public List<VideoClassify> getVideoClassifyTwoByOne(Long id){
		return videoClassifyDao.getVideoClassifyTwoByOne(id);
	}
    /**
     * 删除分类
     * @param id
     */
    public void delClassifyById(Long id){
    	videoClassifyDao.delClassifyById(id);
    }
    /**
     * 更新分类
     * @param videoClassify
     */
    public void updateClassifyById(VideoClassify videoClassify){
    	videoClassifyDao.updateClassifyById(videoClassify);
    }
    /**
     *  添加分类
     * @param videoClassify
     * @return id
     */
    public Long createClassify(VideoClassify videoClassify){
    	return videoClassifyDao.createClassify(videoClassify);
    }
    /**
     * 根绝ID查找分类
     * @param id
     * @return
     */
    public VideoClassify getVideoClassifyById(Long id){
    	return videoClassifyDao.getVideoClassifyById(id);
    }
    /**
     * 查找该二级分类下的视频数
     * @param id
     * @return count
     */
    public int getUploadVideoCountByClassify(Long id){
    	return videoClassifyDao.getUploadVideoCountByClassify(id);
    }
}

