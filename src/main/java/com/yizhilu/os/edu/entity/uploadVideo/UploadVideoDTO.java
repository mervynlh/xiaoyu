package com.yizhilu.os.edu.entity.uploadVideo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class UploadVideoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 状态;默认0正常，1删除
	 */
	private int status;
	/**
	 * id数组
	 * @return
	 */
	private int[] UploadVideoIds;
	/**
	 * 视频大小，单位为M，小数点后保留一位
	 */
	private double size;
	/**
	 * 一级分类名称
	 */
	private String classifyOneName;
	/**
	 * 二级分类名称
	 */
	private String classifyTwoName;
	
	
}
