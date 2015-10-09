package com.yizhilu.os.edu.service.impl.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.teacher.TeacherSubjectDao;
import com.yizhilu.os.edu.entity.teacher.TeacherSubject;
import com.yizhilu.os.edu.service.teacher.TeacherSubjectService;

/**
 * TeacherSubject管理类
 * @author WangKaiping
 *
 */
@Service("teacherSubjectService")
public class TeacherSubjectServiceImpl implements TeacherSubjectService {

	@Autowired
	private TeacherSubjectDao teacherSubjectDao;
	
	/**
	 * 添加教师科目
	 */
	public void addTeacherSubjectBatch(List<TeacherSubject> teacherSubject) {
		// 先删除该教师所教授的全部科目
		delTeacherSubjectByTeacherId(teacherSubject.get(0).getTeacherId());
		teacherSubjectDao.addTeacherSubjectBatch(teacherSubject);
	}

	/**
	 * 根据教师ID编号查询科目列表
	 */
	public List<TeacherSubject> queryTeacherSubjectByTeacherId(Long teacherId) {
		return teacherSubjectDao.queryTeacherSubjectByTeacherId(teacherId);
	}

	/**
	 * 根据教师ID编号删除科目
	 */
	public void delTeacherSubjectByTeacherId(Long teacherId) {
		teacherSubjectDao.delTeacherSubjectByTeacherId(teacherId);
	}

	@Override
	public List<TeacherSubject> queryTeacherParentSubject(Long teacherId) {
		// TODO Auto-generated method stub
		return teacherSubjectDao.queryTeacherParentSubject(teacherId);
	}

	@Override
	public List<TeacherSubject> queryTeacherSubjectByOK(Long teacherId) {
		// TODO Auto-generated method stub
		return teacherSubjectDao.queryTeacherSubjectByOK(teacherId);
	}

}
