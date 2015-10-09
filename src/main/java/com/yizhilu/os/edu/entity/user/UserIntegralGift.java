package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * @ClassName  com.yizhilu.os.edu.entity.user.UserIntegralGift
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月28日 下午4:30:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegralGift implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 556308335578768967L;
	
	private Long id;//id
    private String name;//礼品名称
    private Long score;//所需分数
    private String logo;//图片
    private String content;//内容
    private java.util.Date createTime;//创建时间
    private java.util.Date updateTime;//更新时间
    private Long courseId;//课程id
    private Long status;//状态1为正常，2为删除
    //课程礼品所需属性
    private String courseName;//课程名
    private String courseTitle;//简介
    private String courseLogo;//课程图片
}
