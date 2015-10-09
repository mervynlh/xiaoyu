package com.yizhilu.os.edu.entity.user;

import com.yizhilu.os.core.util.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserExpand implements Serializable {
	
	public static String ADD = "+";
    public static String SUBTRACTION = "-";	
	    
	/**
	 * 
	 */
	private static final long serialVersionUID = 6702212021177973240L;
	private Long id;
	private String realname;// 真实姓名
	private int gender;// 性别：0男 1女
	private String avatar;// 头像地址
	private String bannerUrl;//自定图片
	private Long studysubject;// 最后一次选择的专业
	private String email;// 邮箱
    private Long cusId;// 用户id
    private String mobile;// 手机号
    private Long cusAttentionId;// 是否关注过这个用户的 关注表id
    private String showname;//网页显示,优先显示nickname,然后显示邮箱
    private int msgNum;//站内信未读消息数
    private Date lastSystemTime;//上传统计系统消息时间
    private String nickname;//用户名
    private String userInfo="";// 用户简介
    private String registerFrom;//注册来源
    private Long courseid;//课程ID
    private int hideStatus;//隐藏模式 0未启动，1启动
	private String headPhoto;//显示头像
	private Long extendCord;// 推广号
	
    private int contSignin;// 连续签到天数
    private Date lastSignTime;// 上次签到日期
    private String signDate="";// 当月签到天数
	
	private Date birthday; // 出生日期
    private Long age; // 年龄
	private String myName;
	
	
	private String assessTeatostu;// 老师评价学生
	private String orderId;// 订单id
    //隐藏模式显示名称
    public String getShowname() {
    	if(hideStatus==1){
    		 return getNickname();
    	}
    	return getRealname();
    }
    //隐藏模式返回显示头像
    public String getHeadPhoto(){
    	if(hideStatus==1){
	   		 return "";
	   	}
	   	return getAvatar();
    }
    public String getMyName() {
    	if (StringUtils.isNotEmpty(realname)) {
            return getRealname();
        }
        if (StringUtils.isNotEmpty(nickname)) {
            return getNickname();
        }
        return getMobile();
    }

    private int unreadFansNum;//未读粉丝数
}
