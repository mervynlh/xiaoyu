package com.yizhilu.os.edu.dao.impl.weixin;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.weixin.WeixinReplyDao;
import com.yizhilu.os.edu.entity.weixin.QueryWeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImageDTO;
import com.yizhilu.os.edu.entity.weixin.WeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImage;


@Repository("weixinReplyDao")
public class WeixinReplyDaoImpl extends GenericDaoImpl implements WeixinReplyDao{
	/**
	 * 添加微信文本或图文回复素材
	 * @param weixin
	 * @return
	 */
    public Long addWeixin(WeixinReply weixin){
    	return this.insert("WeixinReplyMapper.addWeixin", weixin);
    }
    
   /**
    * 更新微信回复信息（文本或图文）素材
    * @param weixin
    */
    public void updateWeixinReply(WeixinReply weixin){
    	this.update("WeixinReplyMapper.updateWeixinReply",  weixin);
    }
   
    /**
     * 根据id删除微信素材
     * @param id
     */
    public void deleteWeixin(Long id){
    	this.delete("WeixinReplyMapper.delWeixinText", id);
    }
    /**
     * 根据id查询一条记录
     * @param id
     * @return
     */
    public WeixinReply getWeixinById(Long id){
    	return this.selectOne("WeixinReplyMapper.getWeixinByReplyId", id);
    }
    
	/**
	 * 根据类型查询
	 * @param msgType
	 * @return
	 */
    public List<WeixinReply> getWeixinByMsgType(String msgType){
    	return this.selectList("WeixinReplyMapper.getWeixinByMsgType", msgType);
    }

    /**
     * 微信回复分页
     * @param queryWeixinReply
     * @return
     */
    public List<WeixinReply> getWeixinReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page){
    	return this.queryForListPage("WeixinReplyMapper.getWeixinReplyPage", queryWeixinReply, page);
    }
    /**
     * 微信图文回复分页
     * @param queryWeixinReply
     * @return
     */
    public List<WeixinReply> getImageTextReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page){
    	return this.queryForListPage("WeixinReplyMapper.getImageTextReplyPage", queryWeixinReply, page);
    }
    /**
     * 微信多图文和图文关联
     * @param imageTextList
     */
	public void addWeixinManyImageText(List<WeixinManyImage> imageTextList){
		this.insert("WeixinReplyMapper.addWeixinManyImageText", imageTextList);
	}
	/**
	 * 微信根据多图文ID删除多图文和图文关联
	 * @param id
	 */
	public void delWeixinManyImageTextByManyId(Long id){
		this.delete("WeixinReplyMapper.deleteWeixinManyImageTextByManyId", id);
	}
	/**
	 * 微信根据图文ID删除多图文和图文关联
	 * @param id
	 */
	public void delWeixinManyImageTextById(Long id){
		this.delete("WeixinReplyMapper.deleteWeixinManyImageTextById", id);
	}
	/**
	 * 微信根据多图文ID查询多图文和图文关联
	 * @param id
	 * @return
	 */
	public List<WeixinManyImageDTO> getWeixinManyImageTextByManyId(Long id){
		return this.selectList("WeixinReplyMapper.getWeixinManyImageTextById", id);
	}
	
	/**
	 * 根据根据关键字模糊查询微信回复
	 * @param eventKey
	 * @return
	 */
	public List<WeixinReply> getWeixinBywxKeywords(String eventKey){
		return this.selectList("WeixinReplyMapper.getWeixinBywxKeywords", eventKey);
	}
	/**
	 * 查询子图文
	 * @param id
	 * @return
	 */
	public List<WeixinReply> getWeixinReplyByManyId(Long id){
		return this.selectList("WeixinReplyMapper.getWeixinReplyByManyId", id);
	}
}


