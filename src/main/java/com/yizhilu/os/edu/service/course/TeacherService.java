package com.yizhilu.os.edu.service.course;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.major.SubjectMajor;

/**
 * Teacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface TeacherService {

	/**
	 * 添加Teacher
	 * 
	 * @param teacher
	 *            要添加的Teacher
	 * @return id
	 */
	public java.lang.Long addTeacher(Teacher teacher);

	/**
	 * 根据id删除一个Teacher
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTeacherById(Long id);

	/**
	 * 后台修改Teacher
	 * 
	 * @param teacher
	 *            要修改的Teacher
	 */
	public void updateTeacherAdmin(Teacher teacher);

    /**
     * 前台修改Teacher基本信息
     *
     * @param teacher
     *            要修改的Teacher
     */
    public void updateTeacherBaseInfo(Teacher teacher) throws Exception;

    /**
     * 前台修改Teacher高级信息
     *
     * @param teacher
     *            要修改的Teacher
     */
    public void updateTeacherSeniorInfo(Teacher teacher);

    /**
     * 前台修改Teacher认证信息
     *
     * @param teacher
     *            要修改的Teacher
     */
    public String updateTeacherAttestationInfo(Teacher teacher, String type);

    /**
     * 修改教师信息
     * @param teacher
     */
    public void updateTeacher(Teacher teacher) throws Exception;

	/**
	 * 根据id获取单个Teacher对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Teacher
	 */
	public Teacher getTeacherById(Long id);
	
	/**
     * 根据用户ID查询教师
     * @param userId
     * @return
     */
    public Teacher getTeacherByUserId(Long userId);

	/**
	 * 根据条件获取Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<Teacher> getTeacherList(QueryTeacher teacher);

	/**
	 * Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<QueryTeacher> queryTeacherListPage(QueryTeacher teacher,PageEntity page);
	/**
	 * 根据id获取Teacher列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<QueryTeacher> queryTeacherInIds(String ids);
	
	/**
     * 修改教师状态
     * @param teacher
     */
    public void updateTeacherStatus(Teacher teacher);

    /**
     * 根据专业科目查询教师(缓存)
     * @param query
     * @param page
     * @return
     */
    public Map<String,Object> queryTeacherBySubjectMajorIndex(List<SubjectMajor> query, int num,Long cityId);
    /**
     * 根据专业科目查询教师
     * @param query
     * @param page
     * @return
     */
    public List<Teacher> queryTeacherBySubjectMajor(Map<String,Object> query);
    /**
     * 查询入住老师人数
     * @return
     */
    public int queryAllTeacherCount();
    
    /**
     * 前台页面获得推荐教师
     * @return
     * @throws Exception
     */
    public Map<String, List<QueryTeacher>> getTeacherListByHomePage() throws Exception;
    
    /**
     * 前台分页查询教师列表
     * @param queryTeacher
     * @return
     */
    public List<Teacher> queryTeacherList(QueryTeacher queryTeacher, PageEntity page);
    
    /**
     * 根据用户ID查询教师列表
     * @param userId
     * @param page
     * @return
     */
    public List<QueryTeacher> queryTeacherListByUserId(Long userId, PageEntity page);
    /**
     * 查询教师详细信息
     * @param id
     * @return
     */
    public Teacher getTeacherInfoById(Long id);
    /**
     * 修改城市城镇id
     * @param teacher
     */
    public void updateCityTown(Teacher teacher);


    /**
     * 修改教师直播接口返回的用户ID
     * @param teacherId
     *               教师ID
     */
    public void updateZoomMeetingUserId(Long teacherId, String zoomUserId);

    /**
     * 更新教师表的最低价格
     * @param map
     */
    public void updateLowPrice(Map<String,Object> map);

    /**
     * 判断是否可以审核教师
     * @param teacher
     * @return
     */
    public String checkTeaCourseNumAndAddress(Long teacher);

    /**
     * 前台修改教师个人中心-账户设置-基本信息性别
     * @param teacher
     */
    public void updateTeacherSex(Teacher teacher);

}