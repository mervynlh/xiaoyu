package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FavouriteCourseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; // 课程名字
	private String title; // 课程标题
	private java.util.Date loseAbsTime; // 失效时间
	private int courseId; // 课程id
	private String logo;// 课程图片
	private int favouriteId; // 收藏课程id
}
