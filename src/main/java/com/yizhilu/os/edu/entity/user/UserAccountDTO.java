package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccountDTO implements Serializable{	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;// 用户id
    private java.util.Date createTime;// 创建时间
    private java.util.Date lastUpdateTime;// 最后更新时间
    private java.math.BigDecimal balance;// 账户余额
    private java.math.BigDecimal forzenAmount;// 冻结金额
    private java.math.BigDecimal cashAmount;// 银行入账
    private java.math.BigDecimal vmAmount;// 课程卡入账
    private String accountStatus;// 账户状态
    private String email;		//电子邮箱
    private String nickName;	//昵称
    private String mobile;      //手机号

}
