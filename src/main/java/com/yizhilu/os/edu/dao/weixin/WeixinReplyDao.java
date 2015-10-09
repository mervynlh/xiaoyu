package com.yizhilu.os.edu.dao.weixin;



import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.weixin.QueryWeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImageDTO;
import com.yizhilu.os.edu.entity.weixin.WeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImage;



public interface WeixinReplyDao {
	/**
	 * 添加微信文本或图文回复素材
	 * @param weixin
	 * @return
	 */
    public Long addWeixin(WeixinReply weixin);
    
   /**
    * 更新微信回复信息（文本或图文）素材
    * @param weixin
    */
    public void updateWeixinReply(WeixinReply weixin);
   
    /**
     * 根据id删除微信素材
     * @param id
     */
    public void deleteWeixin(Long id);
    /**
     * 根据id查询一条记录
     * @param id
     * @return
     */
    public WeixinReply getWeixinById(Long id);
    
	/**
	 * 根据类型查询
	 * @param msgType
	 * @return
	 */
    public List<WeixinReply> getWeixinByMsgType(String msgType);

    /**
     * 微信回复分页
     * @param queryWeixinReply
     * @return
     */
    public List<WeixinReply> getWeixinReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page);
    /**
     * 微信图文回复分页
     * @param queryWeixinReply
     * @return
     */
    public List<WeixinReply> getImageTextReplyPageList(QueryWeixinReply queryWeixinReply,PageEntity page);
    /**
     * 微信多图文和图文关联
     * @param imageTextList
     */
	public void addWeixinManyImageText(List<WeixinManyImage> imageTextList);
	/**
	 * 微信根据多图文ID删除多图文和图文关联
	 * @param id
	 */
	public void delWeixinManyImageTextByManyId(Long id);
	/**
	 * 微信根据图文ID删除多图文和图文关联
	 * @param id
	 */
	public void delWeixinManyImageTextById(Long id);
	/**
	 * 微信根据多图文ID查询多图文和图文关联
	 * @param id
	 * @return
	 */
	public List<WeixinManyImageDTO> getWeixinManyImageTextByManyId(Long id);
	/**
	 * 根据根据关键字模糊查询微信回复
	 * @param eventKey
	 * @return
	 */
	public List<WeixinReply> getWeixinBywxKeywords(String eventKey);
	/**
	 * 查询子图文
	 * @param id
	 * @return
	 */
	public List<WeixinReply> getWeixinReplyByManyId(Long id);
}


