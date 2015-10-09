package com.yizhilu.os.edu.entity.assess;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :wangzhuang
 * 学生对教师的评论实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Assess implements Serializable {
	
	private static final long serialVersionUID = -1L;
	private long id;//主键自增
	private long userId;//用户id 学生id
	private long orderId;//订单id
	private long teacherId;//教师id
	private java.util.Date createTime;//评价时间
	private String content;//评价内容
	private String imgs;//上传的图片
	private long status;//状态1对学生的评价2对老师评价
	private long type;//评价类型1好评2中评3差评
	private long description;//描述相关得分
	private long attribute;//教学态度得分
	private long speed;//响应态度得分
	private String courseModel;//上课方式
	private String subjectMajorName;//阶段科目名称
	private int star;//评分
}
