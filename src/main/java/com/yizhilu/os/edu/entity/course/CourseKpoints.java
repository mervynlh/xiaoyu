package com.yizhilu.os.edu.entity.course;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 大小班课节表
 * @author Administrator
 * 2015-8-5
 */
@Data
public class CourseKpoints implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long courseId;//课程id
	private Long teacherId;//教师id
	private String name;//课节名称
	private Date addTime;//创建时间
	private Date updateTime;//更新时间
	private int status;//课节状态 0正常，1删除
	private int sort;//排序
	private Date beginTime;//开始时间
	private Date endTime;//结束时间
	private String details;//课节内容

	private String meetingId; // 直播视频ID
	private String meetingUrl; // 直播地址
	private int meetingStatus; // 直播状态  0未开始  1开始  2已结束
}
