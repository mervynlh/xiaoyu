package com.yizhilu.os.edu.entity.weixin;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信回复素材类
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinReply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 **/
	private Long id;
	/**
	 * 多图文的图文id和标题集合字符串
	 **/
	private String manyImageIds;
	/**
	 * 回复素材创建时间
	 **/
	private Date createTime;
	/**
	 * 回复素材更新时间
	 **/
	private Date updateTime;
	/**
	 * 回复素材类型： 1.text文本2.image图文3.news多图文'
	 **/
	private int msgType;
	/**
	 * 消息标题
	 **/
	private String title;
	/**
	 * 消息内容
	 **/
	private String content;
	/**
	 * 图片的url
	 */
	private String imageUrl;
	/**
	 *关键字 
	 */
	private String keyword;
	/**
	 *文章作者 
	 */
	private String nickName;
    /**
     * 点击图文消息跳转链接
     */
    private String url;
    
  
	
}
