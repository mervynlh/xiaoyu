package com.yizhilu.os.edu.entity.article;

import java.io.Serializable;

import com.yizhilu.os.core.util.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @ClassName com.yizhilu.os.edu.entity.article.Article
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月19日 上午9:23:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = -7794265972850284387L;
	private Long id;// 主键
	private String title;// 标题
	private String meta;// 标签
	private String description;// 简介（自动生成，前200个汉字）
	private String content;// 内容
	private String picture;// 图片
	private java.util.Date createTime;// 创建时间
	private java.util.Date updateTime;// 修改时间
	private String author;// 作者
	private Long clickTimes;// 点击数量
	private Long type;// 1资讯 2公告
}
