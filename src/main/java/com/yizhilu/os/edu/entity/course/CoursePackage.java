package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CoursePackage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long mainCourseId;//主课程id
    private Long courseId;//课程id
    
    private Long orderNum; // 排序值
}
