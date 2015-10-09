package com.yizhilu.os.edu.dao.impl.teacher;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.teacher.TeacherMajorDao;
import com.yizhilu.os.edu.entity.teacher.TeacherMajor;

/**
 * TeacherMajor管理类
 * @author WangKaiping
 *
 */
@Repository("teacherMajorDao")
public class TeacherMajorDaoImpl extends GenericDaoImpl implements TeacherMajorDao {

	/**
	 * 添加教师科目
	 */
	public void addTeacherMajorBatch(List<TeacherMajor> teacherMajor) {
		this.insert("TeacherMajorMapper.addTeacherMajorBatch", teacherMajor);
	}

	/**
	 * 根据教师ID查询所教授科目
	 */
	public List<TeacherMajor> queryTeacherMajorByTeacherId(Long teacherId) {
		return this.selectList("TeacherMajorMapper.queryTeacherMajorByTeacherId", teacherId);
	}

	/**
	 * 根据教师编号删除科目
	 */
	public void delTeacherMajorByTeacherId(Long teacherId) {
		this.delete("TeacherMajorMapper.delTeacherMajorByTeacherId", teacherId);
	}

}
