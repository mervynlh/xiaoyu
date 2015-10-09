package com.yizhilu.os.edu.entity.teacher;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeacherSubject implements Serializable {/**
	 * 
	 * 教师教授阶段表
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; //主键
	private Long teacherId;//教师id	
	private Long subjectId; // 教授阶段ID
	private String subjectName;//专业名
}
