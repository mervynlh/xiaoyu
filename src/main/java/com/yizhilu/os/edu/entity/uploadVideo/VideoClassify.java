package com.yizhilu.os.edu.entity.uploadVideo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VideoClassify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 父级分类ID,默认0无父级ID
	 */
	private Long parentId;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 区别分类和默认分类，为程序预置的一级分类和二级分类，不可删除，只能编辑
	 */
	private String keyword;
	/**
	 * 每个二级分类下的视频数
	 */
	private int videoNum;
	
	/**
	 * 用户ID
	 */
	private Long userId;
	
}
