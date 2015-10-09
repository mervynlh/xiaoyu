package com.yizhilu.os.edu.dao.impl.uploadVideo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.uploadVideo.VideoClassifyDao;
import com.yizhilu.os.edu.entity.uploadVideo.VideoClassify;


@Repository("videoClassifyDao")
public class VideoClassifyDaoImpl extends GenericDaoImpl implements VideoClassifyDao {
	
	/**
	 * 查询所有一级分类 
	 * @return VideoClassify
	 */
    public List<VideoClassify> getVideoClassifyOne(){
    	return this.selectList("VideoClassifyMapper.getClassifyOneAll", 0);
    }
    /**
	 * 根据一级分类ID查询二级分类 
	 * @return VideoClassify
	 */
	public List<VideoClassify> getVideoClassifyTwoByOne(Long id){
		return this.selectList("VideoClassifyMapper.getClassifyTwoByOneId", id);
	}
    /**
     * 删除分类
     * @param id
     */
    public void delClassifyById(Long id){
    	this.delete("VideoClassifyMapper.delClassifyById", id);
    }
    /**
     * 更新分类
     * @param videoClassify
     */
    public void updateClassifyById(VideoClassify videoClassify){
    	this.update("VideoClassifyMapper.updateClassifyById", videoClassify);
    }
    /**
     *  添加分类
     * @param videoClassify
     * @return id
     */
    public Long createClassify(VideoClassify videoClassify){
    	return this.insert("VideoClassifyMapper.createClassify", videoClassify);
    }
    /**
     * ID查找分类
     * @param id
     * @return
     */
    public VideoClassify getVideoClassifyById(Long id){
    	return this.selectOne("VideoClassifyMapper.getClassifyById", id);
    }
    /**
     * 查找该二级分类下的视频数
     * @param id
     * @return count
     */
    public int getUploadVideoCountByClassify(Long id){
    	return this.selectOne("VideoClassifyMapper.getClassifyTwoVideoCount", id);
    }
}

