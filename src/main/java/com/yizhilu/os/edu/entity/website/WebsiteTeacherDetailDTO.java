package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;

import com.yizhilu.os.edu.entity.course.Teacher;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.website.WebsiteCourseDetail
 * @description 推荐课程DTO
 * @author :xujunbao
 * @Create Date : 2014年6月7日 下午1:52:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteTeacherDetailDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7475674095165175841L;

	private Long id;// 主键
	private Long recommendId;// 分类id
	private Long teacherId;// 教师id
	private int orderNum;// 排序值
	private String recommendName;//推荐名称
	private String teacherName;//教师名称
	private int teacherStatus;//教师状态
	private Long cityId;//城市id
	
	private Teacher teacher;//教师详情
}
