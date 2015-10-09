package com.yizhilu.os.edu.service.impl.course;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.enums.SellType;
import com.yizhilu.os.edu.dao.course.CourseDao;
import com.yizhilu.os.edu.dao.course.CourseFavoritesDao;
import com.yizhilu.os.edu.dao.course.CourseProfileDao;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseDto;
import com.yizhilu.os.edu.entity.course.CourseProfile;
import com.yizhilu.os.edu.entity.course.CourseSubject;
import com.yizhilu.os.edu.entity.course.QueryCourse;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.course.CourseSubjectService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.major.SubjectMajorService;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    private MemCache memcache = MemCache.getInstance();
    @Autowired
    private CourseSubjectService courseSubjectService;
    @Autowired
    private CourseFavoritesDao courseFavoritesDao;
    @Autowired
    private CourseProfileDao courseProfileDao;
    @Autowired
    private SubjectMajorService subjectMajorService;
    @Autowired
    private CourseKpointsService courseKpointsService;
    @Autowired
    private TeacherService teacherService;
    
    /**
     * 添加Course
     *
     * @param course 要添加的Course
     * @return id
     */
    public java.lang.Long addCourse(Course course) {
        course.setAddtime(new Date());
        course.setUpdateTime(new Date());
        //查询专业科目名称存入字段
        SubjectMajor subjectMajor=subjectMajorService.querySubjectMajorById(course.getSubjectMajorId());
        course.setSubjectMajorStr(subjectMajor.getSubjectName()+","+subjectMajor.getMajorName());
        if(course.getSellType().equals("1")){
        	course.setCurrentprice(getMinMoney(course));
        	course.setName(subjectMajor.getSubjectName()+","+subjectMajor.getMajorName());
        }
        Long num = courseDao.addCourse(course);
        
        //创建课程专业关联
        CourseSubject courseSubject=new CourseSubject();
        courseSubject.setCourseId(course.getId());
        courseSubject.setSubjectId(course.getSubjectMajorId());
        courseSubjectService.addCourseSubject(courseSubject);
        
        //添加课程配置数据
        CourseProfile courseProfile = new CourseProfile();
        courseProfile.setCourseId(course.getId());
        courseProfileDao.addCourseProfile(courseProfile);
        return num;
    }
    /**
     * 获取上课方式中价钱最低的金额
     * @param course
     * @return
     */
    private BigDecimal getMinMoney(Course course){
    	Gson gson = new Gson();
    	//将上课方式转换为map
		Map<String,String> modelMap = gson.fromJson(course.getCourseModel(), new TypeToken<Map<String,String>>(){}.getType());
		List<Double> doubleArray=new ArrayList<>();
		for(String dataKey : modelMap.keySet()){
			doubleArray.add(new Double(modelMap.get(dataKey)));
		}
		//最低价格
		BigDecimal lowPrice= new BigDecimal(Collections.min(doubleArray));
		//更新教师表的最低金额
		Map<String,Object> map=new HashMap<>();
		
    	map.put("lowPrice",  lowPrice);
    	map.put("id", course.getTeacherId());
    	teacherService.updateLowPrice(map);
		return lowPrice;
    }

    /**
     * 根据id删除一个Course
     *
     * @param id 要删除的id
     */
    public void deleteCourseById(Long id) {
        courseDao.deleteCourseById(id);
        // 删除该课程关联的专业
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourseId(id);
        courseSubjectService.deleteCourseSubject(courseSubject);
        //删除课程记录表
        courseProfileDao.delByCourse(id);
        //删除课节信息
        courseKpointsService.delByCourseId(id);
    }

    /**
     * 根据id获取单个Course对象
     *
     * @param id 要查询的id
     * @return Course
     */
    public Course getCourseById(Long id) {
    	Course course=courseDao.getCourseById(id);
    	CourseSubject courseSubject = new CourseSubject();
		courseSubject.setCourseId(id);
    	List<CourseSubject> courseSubjectList = courseSubjectService.getCourseSubjectList(courseSubject);
		if(ObjectUtils.isNotNull(courseSubjectList)){
			SubjectMajor subjectMajor=subjectMajorService.querySubjectMajorById(courseSubjectList.get(0).getSubjectId());
			course.setSubjectMajor(subjectMajor);
		}
		Gson gson = new Gson();
		//将上课方式转换为map
		Map<String,String> modelMap = gson.fromJson(course.getCourseModel(), new TypeToken<Map<String,String>>(){}.getType());
		course.setCourseModelMap(modelMap);
        return course;
    }


    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<Course> getCourseList(Course course) {
        return courseDao.getCourseList(course);
    }
    
    @Override
    public List<Course> getCourseListByBro() {
    	// TODO Auto-generated method stub
    	return courseDao.getCourseListByBro();
    }
    
    

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page) {

        List<CourseDto> courseDtos = courseDao.getCourseListPage(course, page);
        
        if(ObjectUtils.isNotNull(courseDtos)){
		    //专业科目id
		    List<Long> list = new ArrayList<Long>();
		    for(CourseDto courseDto:courseDtos){
		        list.add(courseDto.getId());
		    }
		    Map<Long,SubjectMajor> subjectMajors=subjectMajorService.querySubjectMajorByIds(list);
		    if(ObjectUtils.isNotNull(subjectMajors)){
		    	for(CourseDto courseDto:courseDtos){
		    		courseDto.setSubjectMajor(subjectMajors.get(courseDto.getId()));
		        }
		    }
		    }
        return courseDtos;
    }
    
    /**
     * 首页获取推荐的课程 封装为map key:index_course_+recommendId ,List<Course>
     *
     * @param recommendId
     * @throws Exception
     * @returnmap key:index_course_1 ,List<Course>
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<CourseDto>> getCourseListByHomePage(Long recommendId) throws Exception {// 首页课程存缓存
        Map<String, List<CourseDto>> map = new HashMap<String, List<CourseDto>>();
        List<CourseDto> courseList = (List<CourseDto>) memcache.get(MemConstans.RECOMMEND_COURSE);
        if (ObjectUtils.isNull(courseList)) {
            courseList = courseDao.getCourseListByHomePage(recommendId);// 查询db
            if (ObjectUtils.isNotNull(courseList)) {
                memcache.set(MemConstans.RECOMMEND_COURSE, courseList, MemConstans.RECOMMEND_COURSE_TIME);// 存缓存一小时
            }
        }
//        if (ObjectUtils.isNotNull(courseList)) {
//            List<CourseDto> courseHotList = new ArrayList<CourseDto>();
//            Long recommend = courseList.get(0).getRecommendId();
//            for (CourseDto course : courseList) {
//                if (recommend.longValue() != course.getRecommendId().longValue()) {
//                    map.put("index_course_" + recommend, courseHotList);
//                    courseHotList = new ArrayList<CourseDto>();
//                    courseHotList.add(course);
//                    recommend = course.getRecommendId();
//                } else {
//                    courseHotList.add(course);
//                }
//            }
//            if (ObjectUtils.isNotNull(courseHotList)) {
//                map.put("index_course_" + recommend, courseHotList);
//            }
//        }
        return map;
    }

    /**
     * 获得项目专业限制的所有课程
     *
     * @param subjectId
     * @param courseIds
     * @return
     */
    public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
        return courseDao.getCouponSubjectCourse(subjectId, courseIds);
    }

    //过滤直播的课程
    public List<CourseDto> filtrationLive(List<CourseDto> courseDtoList) {
        List<CourseDto> newCourseDtoList = new ArrayList<CourseDto>();
        if (ObjectUtils.isNotNull(courseDtoList)) {
            for (CourseDto courseDto : courseDtoList) {
                if (!SellType.LIVE.toString().equals(courseDto.getSellType())) {
                    newCourseDtoList.add(courseDto);
                }
            }
        }
        return newCourseDtoList;
    }

    /**
     * 删除收藏课程
     *
     * @return
     */
    @Override
    public void delFavouriteCourseByIds(String courseIds) {
        courseFavoritesDao.deleteCourseFavoritesById(courseIds);
    }

    @Override
    public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map,
                                                       PageEntity page) {
        return courseDao.queryAppAllCourse(map, page);
    }

	@Override
	public List<Course> queryCourseListByIds(String courseIds) {
		return courseDao.queryCourseListByIds(courseIds);
	}
	/**
    * 根据课程ID，查询用户列表信息
    * @param courseIds
    * @return
    */
	public List<UserExpand> queryUserExpandListByCourseId(Long courseId) {
		return courseDao.queryUserExpandListByCourseId(courseId);
	}

	@Override
	public void verifyCourse(Long isavaliable,String ids) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		map.put("isavaliable", isavaliable);
		map.put("ids", ids);
		courseDao.verifyCourse(map);
	}

	@Override
	public List<Map<String,Object>> queryCourseToTeacher(QueryCourse course) {
		// TODO Auto-generated method stub
		return courseDao.queryCourseToTeacher(course);
	}

	@Override
	public List<Course> queryOneToOneByTeacherId(Long teacherId) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		List<Course> courseList=courseDao.queryOneToOneByTeacherId(teacherId);
		if(ObjectUtils.isNotNull(courseList)){
			//将上课方式转换为map
			for(Course course:courseList){
				Map<String,String> modelMap = gson.fromJson(course.getCourseModel(), new TypeToken<Map<String,String>>(){}.getType());
				course.setCourseModelMap(modelMap);
			}
		}
		return courseList;
	}

	@Override
	public void updateCourseTeacher(Course course) {
		// TODO Auto-generated method stub
		Course course1=courseDao.getCourseById(course.getId());
		//判断课程是否有修改  有修改将重新审核
		boolean isChange= compareCourse(course,course1);
		//改变后
		if(isChange){
			course.setIsavaliable(0l);
			//查询专业科目名称存入字段
			course.setUpdateTime(new Date());
			//查询专业科目名称存入字段
	        SubjectMajor subjectMajor=subjectMajorService.querySubjectMajorById(course.getSubjectMajorId());
	        course.setSubjectMajorStr(subjectMajor.getSubjectName()+","+subjectMajor.getMajorName());
	        if(course.getSellType().equals("1")){
				course.setCurrentprice(getMinMoney(course));
				course.setName(subjectMajor.getSubjectName()+","+subjectMajor.getMajorName());
			}
		    courseDao.updateCourseTeacher(course);

	        // 添加课程关联专业
	        CourseSubject courseSubject = new CourseSubject();
	        courseSubject.setCourseId(course.getId());
	        courseSubject.setSubjectId(course.getSubjectMajorId());
	        courseSubjectService.deleteCourseSubject(courseSubject);
	        courseSubjectService.addCourseSubject(courseSubject);
		}
	}
	//比较课程是否有修改
	private boolean compareCourse(Course course1,Course course2){
		if(course1.getSubjectMajorId().intValue()!=course2.getSubjectMajorId().intValue()){
			return true;
		}
		if(!course1.getCourseModel().equals(course2.getCourseModel())){
			return true;
		}
		return false;
	}
	//比较班课课程是否有修改
	private boolean compareClassCourse(Course course1,Course course2){
		if(!course1.getName().equals(course2.getName())){
			return true;
		}
		if(!course1.getClassImgs().equals(course2.getClassImgs())){
			return true;
		}
		if(course1.getSubjectMajorId().intValue()!=course2.getSubjectMajorId().intValue()){
			return true;
		}
		if(course1.getCurrentprice().compareTo(course2.getCurrentprice())>0){
			return true;
		}
		if(course1.getPeopleNum()!=course2.getPeopleNum()){
			return true;
		}
		if(!course1.getCourseModel().equals(course2.getCourseModel())){
			return true;
		}
		if(!course1.getIsJoinClass().equals(course2.getIsJoinClass())){
			return true;
		}
		return false;
	}
	@Override
	public List<Course> queryClassCourseByTeacherPage(QueryCourse queryCourse,
			PageEntity page) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		List<Course> courseList=courseDao.queryClassCourseByTeacherPage(queryCourse, page);
		if(ObjectUtils.isNotNull(courseList)){
			//将上课方式转换为map
			for(Course course:courseList){
				Map<String,String> modelMap = gson.fromJson(course.getCourseModel(), new TypeToken<Map<String,String>>(){}.getType());
				course.setCourseModelMap(modelMap);
			}
		}
		return courseList;
	}
	@Override
	public void updateClassCourseTeacher(Course course) {
		// TODO Auto-generated method stub
		//判断课程是否有修改  有修改将重新审核
		Course course1=courseDao.getCourseById(course.getId());
		boolean isChange= compareClassCourse(course,course1);
		//修改后
		if(isChange){
			course.setIsavaliable(0l);
			course.setUpdateTime(new Date());
			//查询专业科目名称存入字段
	        SubjectMajor subjectMajor=subjectMajorService.querySubjectMajorById(course.getSubjectMajorId());
	        course.setSubjectMajorStr(subjectMajor.getSubjectName()+","+subjectMajor.getMajorName());
		    courseDao.updateClassCourseTeacher(course);

	        // 添加课程关联专业
	        CourseSubject courseSubject = new CourseSubject();
	        courseSubject.setCourseId(course.getId());
	        courseSubject.setSubjectId(course.getSubjectMajorId());
	        courseSubjectService.deleteCourseSubject(courseSubject);
	        courseSubjectService.addCourseSubject(courseSubject);
		}
	}
	@Override
	public Course getClassCourseById(Long courseId) {
		Course course=courseDao.getClassCourseById(courseId);
		if(ObjectUtils.isNotNull(course)){
			//将上课方式转换为map
			Gson gson = new Gson();
			Map<String,String> modelMap = gson.fromJson(course.getCourseModel(), new TypeToken<Map<String,String>>(){}.getType());
			course.setCourseModelMap(modelMap);
		}
		return course;
	}
	@Override
	public void updateLessionnum(Long courseId) {
		// TODO Auto-generated method stub
		courseDao.updateLessionnum(courseId);
	}
	@Override
	public int quertyCourseNumByAddress(Long addressId) {
		// TODO Auto-generated method stub
		return courseDao.quertyCourseNumByAddress(addressId);
	}
	@Override
	public void updateJoinNum(Long courseId) {
		// TODO Auto-generated method stub
		courseDao.updateJoinNum(courseId);
	}
	@Override
	public int querySubjectMajorConflict(Long subjectMajorId, Long teacherId,
			String sellType,Long courseId) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		map.put("subjectMajorId", subjectMajorId);
		map.put("teacherId", teacherId);
		map.put("sellType", sellType);
		map.put("courseId", courseId);
		return courseDao.querySubjectMajorConflict(map);
	}
	@Override
	public void updateFinshStatu(String isFinsh, Long courseId) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		map.put("isFinsh", isFinsh);
		map.put("courseId", courseId);
		courseDao.updateFinshStatu(map);
	}
	@Override
	public void verifyAllCourse() {
		// TODO Auto-generated method stub
		courseDao.verifyAllCourse();
	}
}