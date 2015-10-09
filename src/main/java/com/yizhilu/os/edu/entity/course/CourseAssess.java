package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.course.CourseAssess
 * @description 课程评论
 * @author :xujunbao
 * @Create Date : 2014年9月23日 下午12:00:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseAssess implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;// 主键自增
	private Long userId;// 用户id
	private Long courseId;// 课程id
	private Long kpointId;// 节点id
	private String content;// 内容
	private int status;//0显示 1隐藏
	private java.util.Date createTime;// 创建时间
}
