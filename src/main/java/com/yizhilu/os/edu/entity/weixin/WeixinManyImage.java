package com.yizhilu.os.edu.entity.weixin;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多图文素材类
 * @author Administrator
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinManyImage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 **/
	private Long id;
	/**
	 * 多图文ID
	 **/
	private Long manyImageId;
	/**
	 * 单图文ID
	 **/
	private Long imageId;
	
}
