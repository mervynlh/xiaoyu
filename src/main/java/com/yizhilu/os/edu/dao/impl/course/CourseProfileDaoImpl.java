package com.yizhilu.os.edu.dao.impl.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.course.CourseProfile;
import com.yizhilu.os.edu.dao.course.CourseProfileDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;

/**
 *
 * CourseProfile
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("courseProfileDao")
public class CourseProfileDaoImpl extends GenericDaoImpl implements CourseProfileDao{

    public java.lang.Long addCourseProfile(CourseProfile courseProfile) {
        return this.insert("CourseProfileMapper.createCourseProfile",courseProfile);
    }

    public void deleteCourseProfileById(Long id){
        this.delete("CourseProfileMapper.deleteCourseProfileById",id);
    }

    public void updateCourseProfile(CourseProfile courseProfile) {
        this.update("CourseProfileMapper.updateCourseProfile",courseProfile);
    }

    public CourseProfile getCourseProfileById(Long id) {
        return this.selectOne("CourseProfileMapper.getCourseProfileById",id);
    }

    public List<CourseProfile> getCourseProfileList(CourseProfile courseProfile) {
        return this.selectList("CourseProfileMapper.getCourseProfileList",courseProfile);
    }
    /**
     * 要更新的数量类型
     * 修改CourseProfile浏览次数
     * @param courseProfile 
     */
    public void updateCourseProfile(String type,Long courseId,Long count,String operation){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);// 类型
        map.put("count", count);// 要更新的数量
        map.put("courseId", courseId);// 课程id
        map.put("operation", operation);//运算符号
        this.update("CourseProfileMapper.updateCount", map);
    }

	@Override
	public void updateCourseBuyCount(String ids) {
		this.update("CourseProfileMapper.updateBuyCount", ids);
	}

	@Override
	public void delByCourse(long id) {
		// TODO Auto-generated method stub
		delete("CourseProfileMapper.delByCourse", id);
	}
}
