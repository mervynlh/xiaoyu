package com.yizhilu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 教师dto
 * @author dingkai
 * @date 2015年8月18日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherDto extends Teacher{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String major;// 主讲科目
	private String subject;// 年级
	private int age;// 年龄
	private Long favoritesId;// 收藏id


}
