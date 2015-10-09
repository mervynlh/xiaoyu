package com.yizhilu.os.edu.service.impl.teacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.teacher.TeacherMajorDao;
import com.yizhilu.os.edu.entity.teacher.TeacherMajor;
import com.yizhilu.os.edu.service.teacher.TeacherMajorService;

/**
 * TeacherMajor管理类
 * @author WangKaiping
 *
 */
@Service("teacherMajorService")
public class TeacherMajorServiceImpl implements TeacherMajorService {

	@Autowired
	private TeacherMajorDao teacherMajorDao;
	
	/**
	 * 添加教师科目
	 */
	public void addTeacherMajorBatch(List<TeacherMajor> teacherMajor) {
		// 先删除该教师所教授的全部科目
		delTeacherMajorByTeacherId(teacherMajor.get(0).getTeacherId());
		teacherMajorDao.addTeacherMajorBatch(teacherMajor);
	}

	/**
	 * 根据教师ID编号查询科目列表
	 */
	public List<TeacherMajor> queryTeacherMajorByTeacherId(Long teacherId) {
		return teacherMajorDao.queryTeacherMajorByTeacherId(teacherId);
	}

	/**
	 * 根据教师ID编号删除科目
	 */
	public void delTeacherMajorByTeacherId(Long teacherId) {
		teacherMajorDao.delTeacherMajorByTeacherId(teacherId);
	}

}
