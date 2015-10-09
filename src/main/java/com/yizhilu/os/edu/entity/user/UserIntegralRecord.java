package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * @ClassName  com.yizhilu.os.edu.entity.user.UserIntegralRecord
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月28日 下午1:18:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserIntegralRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4740771380199011810L;
	
	private Long id;//id
    private Long userId;//用戶id
    private Long score;//分数
    private Long integralType;//积分类型
    private java.util.Date createTime;//创建时间
    private Long currentScore;//当前积分
    private Long other;//其它辅助
    private Long fromUserId;//来源id
    private Long status;//兑换状态 0未处理1已处理
    private String description;//描述
    private String templateName;//模板名称
    private String templateScore;//模板分数
    private String nickname;//用户名
    private String email;//邮箱
    private String mobile;//用户手机
    private String realname;//用户姓名
    private String giftName;//礼品名称
    private Long courseId;//课程id
    private String startDate;//开始时间
	private String endDate;//结束时间
	private Long addressId;//收货地址Id
	private String address;//收货地址
	private int contSignIn;// 连续签到天数
}
