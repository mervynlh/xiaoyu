package com.yizhilu.os.edu.dao.impl.course;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.course.CourseKpointDao;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.CourseKpoint;

/**
 *
 * CourseKpoint
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("courseKpointDao")
public class CourseKpointDaoImpl extends GenericDaoImpl implements CourseKpointDao{

    public java.lang.Long addCourseKpoint(CourseKpoint courseKpoint) {
        return this.insert("CourseKpointMapper.createCourseKpoint",courseKpoint);
    }

    public void deleteCourseKpointById(Long id){
        this.delete("CourseKpointMapper.deleteCourseKpointById",id);
    }
    /**
     * 根据id集合删除CourseKpoint
     */
    public void deleteCourseKpointByIdBatch(String[] courseKpointIds){
    	this.update("CourseKpointMapper.deleteCourseKpointByIdBatch",courseKpointIds);
    }

    public void updateCourseKpoint(CourseKpoint courseKpoint) {
        this.update("CourseKpointMapper.updateCourseKpoint",courseKpoint);
    }
    /**
     * 修改CourseKpoint 播放数加一
     */
    public void updateCourseKpointPlaycountAdd(Long kpointId){
    	 this.update("CourseKpointMapper.updateCourseKpointPlaycountAdd",kpointId);
    }
    public CourseKpoint getCourseKpointById(Long id) {
        return this.selectOne("CourseKpointMapper.getCourseKpointById",id);
    }

    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint) {
        return this.selectList("CourseKpointMapper.getCourseKpointList",courseKpoint);
    }
    public List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint,PageEntity page) {
    	return this.queryForListPage("CourseKpointMapper.getCourseKpointNewList",courseKpoint,page);
    }
    /**
     * 根据课程集合获取节点集合
     * @param list
     * @return
     */
	public Long getCourseKpointNumByCourseList(List<CourseDto> list){
		 return this.selectOne("CourseKpointMapper.getCourseKpointNumByCourseList",list);
	}
	
	/**
	 * 根据几点ID集合字符串查询节点集合
	 * @param ids
	 * @return
	 */
	public List<CourseKpoint> getCourseKpointListByIds(String ids){
		return this.selectList("CourseKpointMapper.getCourseKpointByIds", ids);
	}
	
	/**
	 * 批量添加子节点
	 * @param childCourseKpointList
	 */
	public void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList){
		this.insert("CourseKpointMapper.createChildCourseKpointList", childCourseKpointList);
	}
}
