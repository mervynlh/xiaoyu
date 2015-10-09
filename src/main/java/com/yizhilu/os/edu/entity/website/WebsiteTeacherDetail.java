package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.website.WebsiteCourseDetail
 * @description 推荐课程
 * @author :xujunbao
 * @Create Date : 2014年6月7日 下午1:52:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteTeacherDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7475674095165175841L;

	private Long id;// 主键
	private Long recommendId;// 分类id
	private Long teacherId;// 课程id
	private int orderNum;// 排序值
}
