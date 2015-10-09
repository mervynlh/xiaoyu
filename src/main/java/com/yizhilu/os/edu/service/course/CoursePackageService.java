package com.yizhilu.os.edu.service.course;

import com.yizhilu.os.edu.entity.course.CoursePackage;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface CoursePackageService {

	/**
	 * 添加CoursePackage
	 */
	public void addCoursePackageBatch(String ids, Long courseId);

	/**
	 * 删除CoursePackage
	 */
	public void delCoursePackage(CoursePackage coursePackage);
	
	/**
    * 修改套餐课程下的课程排序
    * @param course
    */
    public void updateCoursePackageOrderNum(CoursePackage coursePackage);
}