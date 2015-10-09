package com.yizhilu.os.edu.entity.card;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author  xinYing.shan
 * @version Sep 25, 2014 5:25:55 PM
 * 获取线下卡信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCardCode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;	
    private String name;
    private java.math.BigDecimal money;
    private int type;	//卡类型(1课程卡2充值卡)  
    private Long num;					//申城个数
    private String status;				//状态
    private Long cardNode;				//课程编码
    private java.util.Date beginTime;	//开始时间
    private java.util.Date endTime;		//结束时间
    private String createUser;			//创建人
    private java.util.Date createTime;	//创建时间
    private String cardCodePassword;	//充值卡密码
    private Long  userId;	//学员id
    private String email;	//邮件
    private String remark;	//备注信息
    private int cardId;	//课程主卡id
    
    
}
