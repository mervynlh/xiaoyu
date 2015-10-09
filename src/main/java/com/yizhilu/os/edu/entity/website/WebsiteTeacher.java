package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推荐分类
 * 
 * @ClassName com.yizhilu.os.edu.entity.website.WebsiteCourse
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月7日 下午1:52:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteTeacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1383373953853661904L;

	private Long id;// 分类id
	private String name;//分类名称
	private String link;//更多链接
	private String description;//详细描述
	private int teacherNum;//数量限制
}
