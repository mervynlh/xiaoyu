package com.yizhilu.os.edu.service.impl.course;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.entity.course.CourseKpoint;
import com.yizhilu.os.edu.entity.course.FavouriteCourseDTO;
import com.yizhilu.os.edu.constants.enums.VideoType;
import com.yizhilu.os.edu.dao.course.CourseKpointDao;
import com.yizhilu.os.edu.service.course.CourseKpointService;
/**
 * CourseKpoint管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("courseKpointService")
public class CourseKpointServiceImpl implements CourseKpointService{

 	@Autowired
    private CourseKpointDao courseKpointDao;
    
    /**
     * 添加CourseKpoint
     * @param courseKpoint 要添加的CourseKpoint
     * @return id
     */
    public java.lang.Long addCourseKpoint(CourseKpoint courseKpoint){
    	courseKpoint.setAddTime(new Date());
    	//如果是56视频类型则把数据转成json存入字段
    	if(VideoType.FIVESIX.toString().equals(courseKpoint.getVideotype())){
    		courseKpoint.setVideojson(initVideojson(courseKpoint.getVideojson()));
    	}
    	return courseKpointDao.addCourseKpoint(courseKpoint);
    }
    
    public String initVideojson(String videojson){
    	 // 56视频的回调参数（只有56 会有）
        if (StringUtils.isNotEmpty(videojson)) {
            String[] arr = videojson.split(",");
            Map<String, String> map = new HashMap<String, String>();
            map.put("vid", arr[0]);
            map.put("subject", arr[1]);
            map.put("url", arr[2]);
            map.put("result", arr[3]);
            map.put("player", arr[4]);
            map.put("chk", arr[5]);
            map.put("cover", arr[6]);
            map.put("coop_public", arr[7]);
            map.put("forbid", arr[8]);
            map.put("coopid", arr[9]);
            map.put("sid", arr[10]);
            map.put("category", arr[11]);
            map.put("attach", arr[12]);
            map.put("tags", arr[13]);
            map.put("content", arr[14]);
            map.put("item_1", arr[15]);
            Gson gson = new Gson();
            return gson.toJson(map);
        }
		return "";
    }
    /**
     * 根据id删除一个CourseKpoint
     * @param id 要删除的id
     */
    public void deleteCourseKpointById(Long id){
    	 courseKpointDao.deleteCourseKpointById(id);
    }
    /**
     * 根据id集合删除CourseKpoint
     */
    public void deleteCourseKpointByIdBatch(String[] courseKpointIds){
    	courseKpointDao.deleteCourseKpointByIdBatch(courseKpointIds);
    }
    /**
     * 修改CourseKpoint
     * @param courseKpoint 要修改的CourseKpoint
     */
    public void updateCourseKpoint(CourseKpoint courseKpoint){
    	//如果是56视频类型则把数据转成json存入字段
    	if(StringUtils.isNotEmpty(courseKpoint.getVideojson())&&VideoType.FIVESIX.toString().equals(courseKpoint.getVideotype())){
    		courseKpoint.setVideojson(initVideojson(courseKpoint.getVideojson()));
    	}
     	courseKpointDao.updateCourseKpoint(courseKpoint);
    }
    /**
     * 修改CourseKpoint 播放数加一
     */
    public void updateCourseKpointPlaycountAdd(Long kpointId){
    	courseKpointDao.updateCourseKpointPlaycountAdd(kpointId);
    }
    /**
     * 根据id获取单个CourseKpoint对象
     * @param id 要查询的id
     * @return CourseKpoint
     */
    public CourseKpoint getCourseKpointById(Long id){
    	return courseKpointDao.getCourseKpointById( id);
    }

    /**
     * 根据条件获取CourseKpoint列表
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint){
    	return courseKpointDao.getCourseKpointList(courseKpoint);
    }
    /**
     * 根据条件获取CourseKpoint列表
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    public List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint,PageEntity page){
    	return courseKpointDao.getCourseKpointNewList(courseKpoint,page);
    }
    
    /**
	 * 根据几点ID集合字符串查询节点集合
	 * @param ids
	 * @return
	 */
	public List<CourseKpoint> getCourseKpointListByIds(String ids){
		return courseKpointDao.getCourseKpointListByIds(ids);
	}
	
	/**
	 * 批量添加子节点
	 * @param childCourseKpointList
	 */
	public void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList){
		courseKpointDao.createChildCourseKpointList(childCourseKpointList);
	}
}