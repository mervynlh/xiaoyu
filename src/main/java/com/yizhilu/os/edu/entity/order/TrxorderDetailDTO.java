package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderDetailDTO extends TrxorderDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1124545121543651212L;
	
	private String courseName; // 课程课时名称
	private String courseModel; // 上课方式
	private String studyAddress; // 上课地址
	private Date studyDate; // 学习时间
	private String subjectMajorName; // 年级科目名称  年级,科目
	private String year; // 年份
	private String titleName; // 课程名
	
	private String studyTime; // 学习时间段
}
