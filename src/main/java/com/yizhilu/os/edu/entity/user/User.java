package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6849058590636336328L;
	private Long id;// 主键 id
	private String nickname="";//用户名
	private String email="";// 邮件
	private int emailIsavalible=0;// 邮件是否验证
	private String mobile="";// 手机号
	private int mobileIsavalible=0;// 手机号是否验证
	private String password;
	private int isavalible;// 是否激活(0正常 1冻结)
	private String customerkey;
	private Date createdate;
	private String userip="";
	private int userType;// 用户类型
	private Long extendCord;// 推广号
	private String startDate;//开始时间
	private String endDate;//结束时间
	private String courseName;//课程名称 用于查询
    private String registerFrom;//账号来源
    private List<LoginOnline> listnew;
    
    private String realname;//真实姓名
    private int gender;//性别
}
