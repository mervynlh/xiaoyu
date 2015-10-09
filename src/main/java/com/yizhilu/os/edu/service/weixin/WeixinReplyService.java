package com.yizhilu.os.edu.service.weixin;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.weixin.QueryWeixinReply;
import com.yizhilu.os.edu.entity.weixin.WeixinManyImageDTO;
import com.yizhilu.os.edu.entity.weixin.WeixinReply;



public interface WeixinReplyService {
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
	public void addWeixinManyImageText(Long mangImageId,String ids);
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
	/**
	 *关键字回复
	 */
	public WeixinReply WeixinExit(String keyWords,String fromUserName,String toUserName);
	/**
	 * 查询常规微信回复
	 * @return
	 */
	public WeixinReply getDefaultWeixin(String type);
	/**
	 * 根据关键字精确查找回复
	 * @param eventKey
	 * @return
	 */
	public WeixinReply getWeixinByEventKey(String eventKey);
	/**
	 * 判断微信用户是否已绑定,未绑定则进行绑定
	 */
	public String checkWeixinBindCus(String fromUserName,User user);
	/**
	 * 微信绑定用户回复
	 * @param bindMsg 绑定信息
	 * @param fromUserName  绑定微信号
	 * @param toUserName
	 * @return
	 */
	public WeixinReply WeixinBindCus(String bindMsg,String fromUserName,String toUserName);
	/**
	 * 微信解绑用户回复
	 * @param bindMsg 绑定信息
	 * @param fromUserName  绑定微信号
	 * @param toUserName
	 * @return
	 */
	public WeixinReply WeixinBindCusExit(String fromUserName,String toUserName);
	/**
	 * 微信用户学习记录
	 * @param fromUserName
	 * @param toUserName
	 * @return
	 */
	public WeixinReply WeixinStudyRecord(String fromUserName,String toUserName);
}


