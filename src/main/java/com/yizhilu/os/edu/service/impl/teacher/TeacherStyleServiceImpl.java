package com.yizhilu.os.edu.service.impl.teacher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yizhilu.os.edu.dao.teacher.TeacherStyleDao;
import com.yizhilu.os.edu.entity.teacher.TeacherStyle;
import com.yizhilu.os.edu.service.teacher.TeacherStyleService;
@Service("teacherStyleService")
public class TeacherStyleServiceImpl implements TeacherStyleService{

 	@Autowired
    private TeacherStyleDao teacherStyleDao;

	@Override
	public void addTeacherStyleBatch(List<TeacherStyle> styles) {
		// TODO Auto-generated method stub
		teacherStyleDao.addTeacherStyleBatch(styles);
	}

	@Override
	public List<TeacherStyle> queryTeacherStyleByCondition(TeacherStyle query) {
		// TODO Auto-generated method stub
		return teacherStyleDao.queryTeacherStyleByCondition(query);
	}

	@Override
	public void delTeacherStyle(String ids) {
		// TODO Auto-generated method stub
		teacherStyleDao.delTeacherStyle(ids);
	}

	/**
	 * 添加单个
	 * @param teacherStyle
	 * @return
	 */
	public TeacherStyle addTeacherStyle(TeacherStyle teacherStyle){
		teacherStyleDao.addTeacherStyle(teacherStyle);
		return teacherStyle;
	}

	@Override
	public TeacherStyle queryStyleById(Long styleId) {
		// TODO Auto-generated method stub
		return teacherStyleDao.queryStyleById(styleId);
	}
}