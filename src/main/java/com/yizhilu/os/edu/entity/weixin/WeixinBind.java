package com.yizhilu.os.edu.entity.weixin;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinBind implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 **/
	private Long id;
	/**
	 * 绑定微信Id
	 **/
	private String openId;
	/**
	 * 绑定用户Id
	 **/
	private Long userId;
	/**
	 * 绑定时间
	 **/
	private Date createTime;
	
	
	
}
