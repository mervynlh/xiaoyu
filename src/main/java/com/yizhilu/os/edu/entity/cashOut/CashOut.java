package com.yizhilu.os.edu.entity.cashOut;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提现
 * @author WangKaiping
 * @version 2015-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CashOut implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2736648970905791155L;
	private Long id; // 主键
    private Long userId; // 提现用户ID
    private String userMobile; // 提现用户手机号
    private String cashType; // 提现方式：BANK 银行卡  ALIPAY支付宝
    private String bankName; // 银行名
    private String bankCard; // 银行卡卡号
    private String openBankName; // 开户行名称
    private String openBankPerson; // 开户人姓名
    private String alipayAccount; // 支付宝账号
    private BigDecimal cashoutMoney; // 提现金额
    private int status; // 提现状态  1正常  2确认(后台管理)  3取消(用户)
    private Date createtime; // 提现创建时间
    
    private String optusername; // 后台操作用户名
    private String realname; // 用户真实姓名
    
    private Date startTime; // 查询开始时间
    private Date endTime; // 查询结束时间
}
