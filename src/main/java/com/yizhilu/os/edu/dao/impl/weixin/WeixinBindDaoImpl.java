package com.yizhilu.os.edu.dao.impl.weixin;

import org.springframework.stereotype.Repository;


import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.weixin.WeixinBindDao;
import com.yizhilu.os.edu.entity.weixin.WeixinBind;
/**
 * 微信用户绑定service
 * @author Administrator
 *
 */
@Repository("weixinBindDao")
public class WeixinBindDaoImpl extends GenericDaoImpl implements WeixinBindDao{
	/**
	 * 查询微信用户绑定信息
	 * @param openId
	 * @param cusId
	 * @return
	 */
	public WeixinBind queryWeixinBind(WeixinBind weixinBind){
		return this.selectOne("queryWeixinBind", weixinBind);
	}
	/**
	 * 添加绑定信息
	 * @param weixinBind
	 */
	public void createWeixinBind(WeixinBind weixinBind){
		this.insert("createWeixinBind", weixinBind);
	}
	/**
	 * 解除绑定
	 * @param weixinBind
	 */
	public void deleteWeixinBind(WeixinBind weixinBind){
		this.delete("deleteWeixinBind", weixinBind);
	}
}


