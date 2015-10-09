package com.yizhilu.os.edu.service.weixin;


import com.yizhilu.os.edu.entity.weixin.WeixinSetReply;
/**
 * 微信常规回复设置service
 * @author Administrator
 *
 */
public interface WeixinSetService {
	/**
	 * 根据回复类型（如默认、关注时回复）获取常规回复设置
	 * @param type
	 * @return
	 */
	public WeixinSetReply getWeixinSetReply(String type);
	/**
	 * 添加常规回复
	 * @param weixinSetReply
	 */
	public void addSetWeixinReply(WeixinSetReply weixinSetReply);
	/**
	 * 更新常规回复
	 * @param weixinSetReply
	 */
	public void updateSetWeixinReply(WeixinSetReply weixinSetReply);
	/**
	 * 根据回复素材ID删除常规回复（回复素材被删除时，同时删除引用者中的回复）
	 * @param id
	 */
	public void delWeixinSetReply(Long id);
	/**
	 * 获取微信access_token
	 * @return
	 */
	public String getWeixinAccessToken();
}


