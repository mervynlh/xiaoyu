package com.yizhilu.os.edu.entity.weixin;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 微信菜单类
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 父级菜单ID
	 */
	private Long parentId;
	/**
	 * 菜单名称，一级菜单最多4个汉字，二级菜单最多7个汉字
	 */
	private String menuName;
	/**
	 * 关键字或链接，根据此请求相应的回复或链接
	 */
	private String keywordUrl;
	/**
	 * 菜单排序，数字越小，顺序越前
	 */
	private int soft;
	/**
	 *是否启用，默认0未启用，1已启用
	 */
	private int status;
	
	public WeixinMenu(){}

	public WeixinMenu(Long id, Long parentId, String menuName,
			String keywordUrl, int soft, int status) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.menuName = menuName;
		this.keywordUrl = keywordUrl;
		this.soft = soft;
		this.status = status;
	}
	
	
}
