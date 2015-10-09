package com.yizhilu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangzhuang
 * 教师星级评定实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherRating {

	private static final long serialVersionUID = 1L; 
	private Long id;//主键自增
	private float score;//定价系数 
	private String rating;//星级 
	private String description;//描述 0.8是一星0.9是二星1.0是三星1.2是四星1.4是五星
	
	
}
