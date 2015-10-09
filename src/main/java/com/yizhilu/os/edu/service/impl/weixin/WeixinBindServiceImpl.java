package com.yizhilu.os.edu.service.impl.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.weixin.WeixinBindDao;
import com.yizhilu.os.edu.entity.weixin.WeixinBind;
import com.yizhilu.os.edu.service.weixin.WeixinBindService;
/**
 * 微信用户绑定service
 * @author Administrator
 *
 */
@Service("weixinBindService")
public class WeixinBindServiceImpl implements WeixinBindService{
	@Autowired
	private WeixinBindDao weixinBindDao;
	/**
	 * 查询微信用户绑定信息
	 * @param openId
	 * @param cusId
	 * @return
	 */
	public WeixinBind queryWeixinBind(WeixinBind weixinBind){
		return weixinBindDao.queryWeixinBind(weixinBind);
	}
	/**
	 * 添加绑定信息
	 * @param weixinBind
	 */
	public void createWeixinBind(WeixinBind weixinBind){
		weixinBindDao.createWeixinBind(weixinBind);
	}
	
	/**
	 * 解除绑定
	 * @param weixinBind
	 */
	public void deleteWeixinBind(WeixinBind weixinBind){
		weixinBindDao.deleteWeixinBind(weixinBind);
	}
}


