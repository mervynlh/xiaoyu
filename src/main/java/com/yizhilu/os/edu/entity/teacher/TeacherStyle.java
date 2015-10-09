package com.yizhilu.os.edu.entity.teacher;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherStyle implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;//标题
	private long teacherId;//讲师id
	private String type;//分类（img 图片  video 视频）
	private String imageUrl;//图片
	private String videoUrl;//视频
	private Date createTime;//创建时间
	private int status;//0 显示  1 不显示
	
	private int num;//显示条数
}
