package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流水评价
 * @author dingkai
 * @date 2015年8月24日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderDetailAssess implements Serializable{

	private static final long serialVersionUID = 272005430928354244L;

	private Long id; // 主键
	private Long detailId;// 流水id
	private String content;// 内容
	private Date createTime;// 创建时间
	private Long teacherId;// 教师id
	private Long userId;// 学生id
	
	private String teacherName;// 教师姓名
	private String studentName;// 学生姓名
}
