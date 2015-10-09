package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 网站配置实体
 * 
 * @ClassName com.yizhilu.os.edu.entity.website.WebsiteProfile
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月11日 下午3:36:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebsiteProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6689726203603217717L;
	private Long id;
	private String type;//类型
	private String desciption;//描述内容JSON格式
	private String explain;//说明
}
