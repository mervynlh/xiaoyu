package com.yizhilu.os.edu.entity.uploadVideo;


import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class QueryUploadVideo{

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 视频名称
	 */
	private String title;
	/**
	 * 视频地址
	 */
	private String videoUrl;
	/**
	 * 一级分类ID
	 */
	private Long classifyOne;
	/**
	 * 二级分类ID
	 */
	private Long classifyTwo;
	/**
	 * 视频简介
	 */
	private String info;
	/**
	 * 上传时间
	 */
	private String startTime;
	/**
	 * 更新时间
	 */
	private String endTime;
	/**
	 * 状态;默认0正常，1冻结，2删除
	 */
	private int status;
	/**
	 * id数组
	 * @return
	 */
	private int[] UploadVideoIds;
	/**
	 * 查询上传起始时间
	 */
	private Date createTime;
	/**
	 * 查询上传结束时间
	 */
	private Date updateTime;
	/**
	 * 查询版本
	 */
	private int versionId;
	
	private Long userId;
	private String type;
	
}
