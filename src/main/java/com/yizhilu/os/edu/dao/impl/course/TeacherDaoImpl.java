package com.yizhilu.os.edu.dao.impl.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.course.TeacherDao;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;

/**
 * 
 * Teacher User: qinggang.liu voo@163.com Date: 2014-05-27
 */
@Repository("teacherDao")
public class TeacherDaoImpl extends GenericDaoImpl implements TeacherDao {

    /**
     * 添加Teacher
     * 
     * @param teacher
     *            要添加的Teacher
     * @return id
     */
    public java.lang.Long addTeacher(Teacher teacher) {
        return this.insert("TeacherMapper.createTeacher", teacher);
    }

    /**
     * 根据id删除一个Teacher
     * 
     * @param id
     *            要删除的id
     */
    public void deleteTeacherById(Long id) {
        this.delete("TeacherMapper.deleteTeacherById", id);
    }

    /**
     * 修改Teacher
     * 
     * @param teacher
     *            要修改的Teacher
     */
    public void updateTeacher(Teacher teacher) {
        this.update("TeacherMapper.updateTeacher", teacher);
    }

    /**
     * 根据id获取单个Teacher对象
     * 
     * @param id
     *            要查询的id
     * @return Teacher
     */
    public Teacher getTeacherById(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teacherId", id);
        map.put("serial", id.toString().substring(id.toString().length()-1));
        return this.selectOne("TeacherMapper.getTeacherById", map);
    }

    /**
     * 根据条件获取Teacher列表
     * 
     * @param teacher
     *            查询条件
     * @return List<Teacher>
     */
    public List<Teacher> getTeacherList(QueryTeacher teacher) {
        return this.selectList("TeacherMapper.getTeacherList", teacher);
    }
    /**
	 * 后台查询Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
   	public List<QueryTeacher> queryTeacherListPage(QueryTeacher teacher,PageEntity page){
   		return this.queryForListPage("TeacherMapper.queryTeacherListPage", teacher, page);
	}
    /**
     * 根据id获取Teacher列表
     */
    public List<QueryTeacher> queryTeacherInIds(String ids) {
        return selectList("TeacherMapper.queryTeacherInIds", ids);
    }

    /**
     * 修改教师状态
     * @param teacher
     */
    public void updateTeacherStatus(Teacher teacher){
    	this.update("TeacherMapper.updateTeacherStatus", teacher);
    }

	@Override
	public Teacher getTeacherByUserId(Long userId) {
		return this.selectOne("TeacherMapper.getTeacherByUserId", userId);
	}

	@Override
	public List<Teacher> queryTeacherBySubjectMajor(Map<String,Object> query) {
		// TODO Auto-generated method stub
		return selectList("TeacherMapper.queryTeacherBySubjectMajor", query);
	}

	@Override
	public int queryAllTeacherCount() {
		// TODO Auto-generated method stub
		return selectOne("TeacherMapper.queryAllTeacherCount", null);
	}
	/**
     * 前台查询推荐教师
     * @param recommendId
     * @return
     * @throws Exception
     */
    public List<QueryTeacher> getTeacherListByHomePage(Long recommendId) throws Exception{
    	return this.selectList("TeacherMapper.getTeacherListByHomePage", recommendId);
    }

    /**
     * 前台分页查询教师列表
     */
	public List<Teacher> queryTeacherList(QueryTeacher queryTeacher, PageEntity page) {
		return this.queryForListPage("TeacherMapper.queryTeacherList", queryTeacher, page);
	}

	/**
     * 根据用户ID查询教师列表
     * @param userId
     * @param page
     * @return
     */
    public List<QueryTeacher> queryTeacherListByUserId(Long userId, PageEntity page){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", userId);
    	return this.queryForListPage("TeacherMapper.queryTeacherListByUserId", map, page);
    }

	@Override
	public void updateCityTown(Teacher teacher) {
		// TODO Auto-generated method stub
		update("TeacherMapper.updateCityTown", teacher);
	}


    /**
     * 修改教师直播接口返回的用户ID
     * @param map
     */
    public void updateZoomMeetingUserId(Map<String, Object> map){
        this.update("TeacherMapper.updateZoomMeetingUserId", map);
    }


	@Override
	public void updateLowPrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("TeacherMapper.updateLowPrice", map);
	}

    /**
     * 前台修改教师个人中心-账户设置-基本信息性别
     * @param teacher
     */
    public void updateTeacherSex(Teacher teacher){
        update("TeacherMapper.updateTeacherSex", teacher);
    }

}
