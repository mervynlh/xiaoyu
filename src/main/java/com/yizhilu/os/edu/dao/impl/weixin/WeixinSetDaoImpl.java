package com.yizhilu.os.edu.dao.impl.weixin;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.weixin.WeixinSetDao;
import com.yizhilu.os.edu.entity.weixin.WeixinSetReply;
/**
 * 微信常规回复设置
 * @author Administrator
 *
 */

@Repository("weixinSetDao")
public class WeixinSetDaoImpl extends GenericDaoImpl implements WeixinSetDao{
	/**
	 * 根据回复类型（如默认、关注时回复）获取常规回复设置
	 * @param type
	 * @return
	 */
	public WeixinSetReply getWeixinSetReply(String type){
		return this.selectOne("WeixinSetMapper.getWeixinSetReply", type);
	}
	/**
	 * 添加常规回复
	 * @param weixinSetReply
	 */
	public void addSetWeixinReply(WeixinSetReply weixinSetReply){
		this.insert("WeixinSetMapper.addWeixinSetReply", weixinSetReply);
	}
	/**
	 * 更新常规回复
	 * @param weixinSetReply
	 */
	public void updateSetWeixinReply(WeixinSetReply weixinSetReply){
		this.update("WeixinSetMapper.updateWeixinSetReply", weixinSetReply);
	}
	/**
	 * 根据回复素材ID删除常规回复（回复素材被删除时，同时删除引用者中的回复）
	 * @param id
	 */
	public void delWeixinSetReply(Long id){
		this.delete("WeixinSetMapper.delWeixinSetReply", id);
	}
	
}


