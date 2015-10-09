package com.yizhilu.os.edu.dao.impl.teacher;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.teacher.TeacherStyleDao;
import com.yizhilu.os.edu.entity.teacher.TeacherStyle;

@Repository("teacherStyleDao")
public class TeacherStyleDaoImpl extends GenericDaoImpl implements TeacherStyleDao{

	@Override
	public void addTeacherStyleBatch(List<TeacherStyle> styles) {
		// TODO Auto-generated method stub
		insert("TeacherStyleMapper.addTeacherStyleBatch", styles);
	}

	@Override
	public List<TeacherStyle> queryTeacherStyleByCondition(TeacherStyle query) {
		// TODO Auto-generated method stub
		return selectList("TeacherStyleMapper.queryTeacherStyleByCondition", query);
	}

	@Override
	public void delTeacherStyle(String ids) {
		// TODO Auto-generated method stub
		delete("TeacherStyleMapper.delTeacherStyle", ids);
	}

	/**
	 * 添加单个教师风采信息
	 * @param teacherStyle
	 * @return
	 */
	public Long addTeacherStyle(TeacherStyle teacherStyle){
		return this.insert("TeacherStyleMapper.addTeacherStyle", teacherStyle);
	}

	@Override
	public TeacherStyle queryStyleById(Long styleId) {
		// TODO Auto-generated method stub
		return selectOne("TeacherStyleMapper.queryStyleById", styleId);
	}
}
