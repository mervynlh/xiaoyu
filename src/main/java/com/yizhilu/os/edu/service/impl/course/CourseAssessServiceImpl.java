package com.yizhilu.os.edu.service.impl.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.enums.CourseProfileType;
import com.yizhilu.os.edu.dao.course.CourseAssessDao;
import com.yizhilu.os.edu.entity.course.CourseAssess;
import com.yizhilu.os.edu.entity.course.CourseProfile;
import com.yizhilu.os.edu.entity.course.QueryCourseAssess;
import com.yizhilu.os.edu.service.course.CourseAssessService;
import com.yizhilu.os.edu.service.course.CourseProfileService;

/**
 * CourseAssess管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("courseAssessService")
public class CourseAssessServiceImpl implements CourseAssessService {

	@Autowired
	private CourseAssessDao courseAssessDao;
	@Autowired
	private CourseProfileService courseProfileService;

	MemCache memCache = MemCache.getInstance();
	
	/**
	 * 添加CourseAssess
	 * 
	 * @param courseAssess
	 *            要添加的CourseAssess
	 * @return id
	 */
	public java.lang.Long addCourseAssess(CourseAssess courseAssess) {
		//更新课程的笔记数量
        courseProfileService.updateCourseProfile(CourseProfileType.commentcount.toString(), courseAssess.getCourseId(), 1L,CourseProfile.ADD);
		return courseAssessDao.addCourseAssess(courseAssess);
	}

	/**
	 * 根据id删除一个CourseAssess
	 * 
	 * @param id
	 *  要删除的id
	 */
	public void deleteCourseAssessById(Long id) {
		courseAssessDao.deleteCourseAssessById(id);
	}

	/**
	 * 修改CourseAssess
	 * 
	 * @param courseAssess
	 *            要修改的CourseAssess
	 */
	public void updateCourseAssess(CourseAssess courseAssess) {
		courseAssessDao.updateCourseAssess(courseAssess);
	}

	/**
	 * 根据id获取单个CourseAssess对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CourseAssess
	 */
	public QueryCourseAssess getCourseAssessById(Long id) {
		return courseAssessDao.getCourseAssessById(id);
	}

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<CourseAssess> getCourseAssessList(CourseAssess courseAssess) {
		return courseAssessDao.getCourseAssessList(courseAssess);
	}

	/**
	 * 根据条件获取CourseAssess列表
	 * 
	 * @param courseAssess
	 *            查询条件
	 * @return List<CourseAssess>
	 */
	public List<QueryCourseAssess> getCourseAssessListPage(CourseAssess courseAssess, PageEntity page) {
		return courseAssessDao.getCourseAssessListPage(courseAssess, page);
	}
	/**
	 * 后台查询课程评论列表
	 * 
	 * @param queryCourseAssess
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page) throws Exception {
		List<QueryCourseAssess> queryCourseAssessList=courseAssessDao.getAdminCourseAssessList(queryCourseAssess, page);
		return queryCourseAssessList;
	}
	/**
	 * 删除课程评论
	 * 
	 * @param courseAssessIds
	 */
	public void delCourseAssessBatch(String courseAssessIds) {
		courseAssessDao.delCourseAssessBatch(courseAssessIds);
	}

	@Override
	public List<QueryCourseAssess> indexAssess(int showNum) {
		// TODO Auto-generated method stub
		List<QueryCourseAssess> assessList=null;//(List<QueryCourseAssess>)memCache.get(MemConstans.ASSESS_INDEX);
		if(ObjectUtils.isNull(assessList)||assessList.size()==0){
			CourseAssess courseAssess=new CourseAssess();
			PageEntity page=new PageEntity();
			page.setPageSize(showNum);
			assessList= courseAssessDao.getCourseAssessListPage(courseAssess, page);
			
			if (ObjectUtils.isNotNull(assessList)) {
	            memCache.set(MemConstans.ASSESS_INDEX, assessList,MemConstans.ASSESS_INDEX_TIME);
	        } 
		}
		return assessList;
	}
}