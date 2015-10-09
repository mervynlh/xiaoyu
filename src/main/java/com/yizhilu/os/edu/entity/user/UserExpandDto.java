package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.yizhilu.os.core.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : xiaokun
 * @ClassName com.yizhilu.os.core.entity.customer.QueryCustomer
 * @description 用户查询
 * @Create Date : 2013-12-13 下午12:35:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserExpandDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -4181611215034299276L;

    private Long id;// 主键 id
    private String nickname = "";// 用户名
    private String email = "";// 邮件
    private int emailIsavalible = 0;// 邮件是否验证
    private String mobile = "";// 手机号
    private int mobileIsavalible = 0;// 手机号是否验证
    private String password;
    private int isavalible = 0;// 是否激活(0正常 1冻结)
    private String customerkey;
    private Date createdate;
    private String userip = "";
    private String realname;// 真实姓名
    private int gender;// 性别：0男 1女
    private String avatar;// 头像地址
    private String bannerUrl;//自定义背景的地址

    private String nowYear;
    private String nowMonth;
    private String nowTime;

    private int msgNum;// 未读消息数
    private Date lastSystemTime;// 上传统计系统消息时间

    private String showname="";// 前端显示名称
    private String userInfo="";// 用户简介
    private Long cusId;// 用户id
    private int commonFriendNum;// 共同好友数

    private Long maxId;// 最大id 后台批量添加系统消息用到
    private int cusNum;// 返回的行数
    
    private long current;//登录时的当前时间戳
    
    private String registerFrom;//注册来源 studentCard（学员卡）
    private int hideStatus;//隐藏模式 0未启动，1启动
	private String headPhoto;//显示头像
	private Long extendCord;// 推广号 
	
	private int userType;// 用户类型

    private Long integift; //用户积分

    private Date birthday;
    
    private int contSignin;// 连续签到天数
    private Date lastSignTime;// 上次签到日期
    private String signDate="";// 当月签到天数
    
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

    public void setExtendInfo(UserExpandDto userExpandDto) {
        this.id = userExpandDto.getId();
        this.nickname = userExpandDto.getNickname();
        this.mobile = userExpandDto.getMobile();
        this.email = userExpandDto.getEmail();
        this.cusId = userExpandDto.getCusId();
        this.realname = userExpandDto.getRealname();
        this.gender = userExpandDto.getGender();
        this.avatar = userExpandDto.getAvatar();
        this.commonFriendNum = userExpandDto.getCommonFriendNum();
        this.msgNum = userExpandDto.getMsgNum();
        this.lastSystemTime = userExpandDto.getLastSystemTime();
        this.showname = userExpandDto.getShowname();
        this.headPhoto = userExpandDto.getHeadPhoto();
        this.hideStatus = userExpandDto.getHideStatus();
    }

}
