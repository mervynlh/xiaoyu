package com.yizhilu.os.edu.dao.uploadVideo;

import java.util.List;


import com.yizhilu.os.edu.entity.uploadVideo.VideoClassify;



public interface VideoClassifyDao {
	
	/**
	 * 查询所有一级分类 
	 * @return VideoClassify
	 */
    public List<VideoClassify> getVideoClassifyOne();
    /**
	 * 根据一级分类ID查询二级分类 
	 * @return VideoClassify
	 */
	public List<VideoClassify> getVideoClassifyTwoByOne(Long id);
    /**
     * 删除分类
     * @param id
     */
    public void delClassifyById(Long id);
    /**
     * 更新分类
     * @param videoClassify
     */
    public void updateClassifyById(VideoClassify videoClassify);
    /**
     *  添加分类
     * @param videoClassify
     * @return id
     */
    public Long createClassify(VideoClassify videoClassify);
    /**
     * 根绝ID查找分类
     * @param id
     * @return
     */
    public VideoClassify getVideoClassifyById(Long id);
    /**
     * 查找该二级分类下的视频数
     * @param id
     * @return count
     */
    public int getUploadVideoCountByClassify(Long id);
}

