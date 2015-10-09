package com.yizhilu.os.edu.entity.teacher;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeacherMajor implements Serializable {/**
	 * 
	 * 教师科目表
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; //主键
	private Long teacherId;//教师id	
	private Long majorId; // 科目ID
	private String majorName;//科目名
}
