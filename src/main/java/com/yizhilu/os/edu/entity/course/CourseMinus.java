package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课时优惠
 * @author Administrator
 * 2015-8-5
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseMinus implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private long teacherId;//教师id
	private long courseId;//课程id
	private int minusNum;//所达优惠需购买课时数
	private double discount;//折扣
	private Date createTime;//创建时间
	private String details;//说明
}
