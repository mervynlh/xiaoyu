package com.yizhilu.os.edu.service.weixin;

import com.yizhilu.os.edu.entity.weixin.WeixinBind;

/**
 * 微信用户绑定service
 * @author Administrator
 *
 */
public interface WeixinBindService {
	/**
	 * 查询微信用户绑定信息
	 * @param openId
	 * @param cusId
	 * @return
	 */
	public WeixinBind queryWeixinBind(WeixinBind weixinBind);
	/**
	 * 添加绑定信息
	 * @param weixinBind
	 */
	public void createWeixinBind(WeixinBind weixinBind);
	/**
	 * 解除绑定
	 * @param weixinBind
	 */
	public void deleteWeixinBind(WeixinBind weixinBind);
}


