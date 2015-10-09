package com.yizhilu.os.edu.entity.weixin;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常规回复类
 * @author Administrator
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinSetReply {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 回复类型，例如关注时、默认回复等
	 */
	private String type;
	/**
	 * 关联回复素材ID
	 */
	private Long replyId;
	
	
}
