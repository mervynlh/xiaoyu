package com.yizhilu.os.edu.entity.audition;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 约课记录实体
 * @author dingkai
 * @date 2015年8月14日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Audition {
	private Long id;// 主键
	private int type;// 0为后台1为老师
	private String teacherName;//老师姓名
	private String teacherMobile;// 教师手机号
	private String studentName;// 学生姓名
	private String studentMobile;// 学生手机好哦啊
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Date createTime;// 创建时间
	private int isRecommend;// 是否允许推荐其他老师 0允许 1不允许
	private String description;// 备注
	private Long gradeId;// 年级id
	private String gradeName;// 年级名称
	private Long subjectId;//阶段id
	private String subjectName;// 阶段名称
	private Long majorId;// 科目id
	private String majorName;// 科目名称
	private Long status;// 状态 0未处理 1处理
}
