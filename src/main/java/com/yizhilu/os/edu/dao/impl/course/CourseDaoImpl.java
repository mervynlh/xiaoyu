package com.yizhilu.os.edu.dao.impl.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.course.CourseDao;
import com.yizhilu.os.edu.dao.course.CourseTeacherDao;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.QueryCourse;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.user.UserExpand;

/**
 * 
 * Course User: qinggang.liu voo@163.com Date: 2014-05-27
 */
@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl implements CourseDao {

    @Autowired
    CourseTeacherDao courseTeacherDao;

    public java.lang.Long addCourse(Course course) {
        this.insert("CourseMapper.createCourse", course);
        return course.getId();
    }

    public void deleteCourseById(Long id) {
        this.update("CourseMapper.deleteCourseById", id);
    }


    public Course getCourseById(Long id) {
        return this.selectOne("CourseMapper.getCourseById", id);
    }


    public List<Course> getCourseList(Course course) {
        return this.selectList("CourseMapper.getCourseList", course);
    }
    
    
    public List<Course> getCourseListByBro(){
    	return this.selectList("CourseMapper.getCourseListByBro",null);
    }
    

    /**
     * 根据条件获取Course列表
     * 
     * @param course
     *            查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page) {
        return this.queryForListPage("CourseMapper.getCourseListPage", course, page);
    }

    /**
     * 首页获取课程 查询了2次SQL。尽量少用循环的查询
     * 
     * @param recommendId
     * @return
     */
    public List<CourseDto> getCourseListByHomePage(Long recommendId) {

        List<CourseDto> courseDtos = this.selectList("CourseMapper.getCourseListByHomePage", recommendId);
        if (ObjectUtils.isNotNull(courseDtos)) {
            List<Long> list = new ArrayList<Long>();
            for (CourseDto courseDto : courseDtos) {
                list.add(courseDto.getId());
            }
            // 获取讲师的list
            Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        }
        return courseDtos;
    }
    
	public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page) {
		return queryForListPage("CourseMapper.queryAppAllCourse", map, page);
	}

	public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("subjectId", subjectId);
		map.put("courseIds", courseIds);
		return this.selectList("CourseMapper.getCouponSubjectCourse", map);
	}


	@Override
	public List<Course> queryCourseListByIds(String courseIds) {
		return selectList("CourseMapper.queryCourseListByIds", courseIds);
	}

	 /**
     * 根据课程ID，查询用户列表信息
     * @param courseIds
     * @return
     */
	public List<UserExpand> queryUserExpandListByCourseId(Long courseId) {
		return selectList("UserExpandMapper.queryUserExpandListByCourseId", courseId);
	}
	
	@Override
	public void verifyCourse(Map<String,Object> map) {
		// TODO Auto-generated method stub
		update("CourseMapper.verifyCourse", map);
	}

	@Override
	public List<Map<String,Object>> queryCourseToTeacher(QueryCourse course) {
		// TODO Auto-generated method stub
		return selectList("CourseMapper.queryCourseToTeacher", course);
	}

	@Override
	public List<Course> queryOneToOneByTeacherId(Long teacherId) {
		// TODO Auto-generated method stub
		return selectList("CourseMapper.queryOneToOneByTeacherId", teacherId);
	}

	@Override
	public void updateCourseTeacher(Course course) {
		// TODO Auto-generated method stub
		update("CourseMapper.updateCourseTeacher", course);
	}

	@Override
	public List<Course> queryClassCourseByTeacherPage(QueryCourse queryCourse,
			PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("CourseMapper.queryClassCourseByTeacherPage", queryCourse, page);
	}

	@Override
	public void updateClassCourseTeacher(Course course) {
		// TODO Auto-generated method stub
		update("CourseMapper.updateClassCourseTeacher", course);
	}

	@Override
	public Course getClassCourseById(Long courseId) {	
		return this.selectOne("CourseMapper.getClassCourseById", courseId);
	}

	@Override
	public void updateLessionnum(Long courseId) {
		// TODO Auto-generated method stub
		update("CourseMapper.updateLessionnum",courseId);
	}

	@Override
	public int quertyCourseNumByAddress(Long addressId) {
		// TODO Auto-generated method stub
		return selectOne("CourseMapper.quertyCourseNumByAddress", addressId);
	}

	@Override
	public void updateJoinNum(Long courseId) {
		// TODO Auto-generated method stub
		update("CourseMapper.updateJoinNum",courseId);
	}

	@Override
	public int querySubjectMajorConflict(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectOne("CourseMapper.querySubjectMajorConflict", map);
	}

	@Override
	public void updateFinshStatu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("CourseMapper.updateFinshStatu",map);
	}

	@Override
	public void verifyAllCourse() {
		// TODO Auto-generated method stub
		update("CourseMapper.verifyAllCourse",null);
	}
}
