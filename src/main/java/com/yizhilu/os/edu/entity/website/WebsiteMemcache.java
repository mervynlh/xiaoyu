package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName  com.yizhilu.os.edu.entity.website.WebsiteMemcache
 * @description memcache管理
 * @author :xujunbao
 * @Create Date : 2014年9月23日 下午5:26:58
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WebsiteMemcache implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611745081384979974L;
	
	private Long id;//主键自增
	private String memKey;//memcache key
	private String memDesc;//memcache 描述
}
