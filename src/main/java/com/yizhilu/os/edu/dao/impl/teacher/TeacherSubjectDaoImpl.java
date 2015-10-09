package com.yizhilu.os.edu.dao.impl.teacher;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.teacher.TeacherSubjectDao;
import com.yizhilu.os.edu.entity.teacher.TeacherSubject;

/**
 * TeacherSubject管理类
 * @author WangKaiping
 *
 */
@Repository("teacherSubjectDao")
public class TeacherSubjectDaoImpl extends GenericDaoImpl implements TeacherSubjectDao {

	/**
	 * 添加教师科目
	 */
	public void addTeacherSubjectBatch(List<TeacherSubject> teacherSubject) {
		this.insert("TeacherSubjectMapper.addTeacherSubjectBatch", teacherSubject);
	}

	/**
	 * 根据教师ID查询所教授科目
	 */
	public List<TeacherSubject> queryTeacherSubjectByTeacherId(Long teacherId) {
		return this.selectList("TeacherSubjectMapper.queryTeacherSubjectByTeacherId", teacherId);
	}

	/**
	 * 根据教师编号删除科目
	 */
	public void delTeacherSubjectByTeacherId(Long teacherId) {
		this.delete("TeacherSubjectMapper.delTeacherSubjectByTeacherId", teacherId);
	}

	@Override
	public List<TeacherSubject> queryTeacherParentSubject(Long teacherId) {
		// TODO Auto-generated method stub
		return selectList("TeacherSubjectMapper.queryTeacherParentSubject", teacherId);
	}

	@Override
	public List<TeacherSubject> queryTeacherSubjectByOK(Long teacherId) {
		// TODO Auto-generated method stub
		return selectList("TeacherSubjectMapper.queryTeacherSubjectByOK",teacherId);
	}

}
