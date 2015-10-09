package com.yizhilu.os.edu.entity.major;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.yizhilu.os.edu.entity.course.Teacher;

@Data
@EqualsAndHashCode(callSuper=false)
public class SubjectMajor implements  Serializable{
	/**
	 **
	 *专业科目表 
	 */
	private static final long serialVersionUID = 1L;
	private  Long  id;//主键id
	private  Long subjectid; //专业Id
	private  Long majorid; //科目Id
	private  String subjectName; //专业名字
	private  String majorName; //科目名字
	
	private List<Teacher> teacherList;//老师集合（主页用）
}
