package com.yizhilu.os.edu.entity.teacher;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeacherProfile implements Serializable {/**
	 * 
	 * 教师拓展表
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; //主键
	private Long teacherId;//教师id	
	private int star; //评星
	private Long assessNum;// 评价总数
	private Long studentNum; // 学生人数
	private Long browseNum; // 浏览人数
	private Long collectNum; // 收藏数量
	private BigDecimal description;//描述相符得分
	private BigDecimal attribute;//教学态度得分
	private BigDecimal speed;//相应速度得分
	private int goodNum;//好评数
	private int middleNum;//中评数
	private int badNum;//差评数

	private int cardStatus; // 身份证认证状态  1审核中  2审核通过  3审核未通过
	private int educationStatus; // 学历认证状态 1审核中  2审核通过  3审核未通过
	private int teachingStatus; // 教师证认证状态  1审核中  2审核通过  3审核未通过
	private int specialtyStatus; // 专业资质认证状态 教师证认证状态  1审核中  2审核通过  3审核未通过
}
