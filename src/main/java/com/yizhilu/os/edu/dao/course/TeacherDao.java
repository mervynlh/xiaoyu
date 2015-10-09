package com.yizhilu.os.edu.dao.course;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;

/**
 * Teacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface TeacherDao {

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
     * 修改Teacher
     * 
     * @param teacher
     *            要修改的Teacher
     */
    public void updateTeacher(Teacher teacher);

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
	public List<QueryTeacher> queryTeacherListPage(QueryTeacher QueryTeacher,PageEntity page);
    /**
     * 根据id获取Teacher列表
     */
    public List<QueryTeacher> queryTeacherInIds(String ids);

    /**
     * 修改教师状态(韩教授认证)
     * @param teacher
     */
    public void updateTeacherStatus(Teacher teacher);
    

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
     * 前台查询推荐教师
     * @param recommendId
     * @return
     * @throws Exception
     */
    public List<QueryTeacher> getTeacherListByHomePage(Long recommendId) throws Exception;
    
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
     * 修改城市城镇id
     * @param teacher
     */
    public void updateCityTown(Teacher teacher);


    /**
     * 修改教师直播接口返回的用户ID
     * @param map
     */
    public void updateZoomMeetingUserId(Map<String, Object> map);

    /**
     * 更新教师表的最低价格
     * @param map
     */
    public void updateLowPrice(Map<String,Object> map);

    /**
     * 前台修改教师个人中心-账户设置-基本信息性别
     * @param teacher
     */
    public void updateTeacherSex(Teacher teacher);

}