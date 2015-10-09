package com.yizhilu.os.edu.entity.cash;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CashOrderDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 充值金额
     */
    private BigDecimal payCash;
    /**
     * 下单时间
     */
    private java.util.Date createTime;
    /**
     * 支付成功/确认时间
     */
    private java.util.Date payTime;
    /**
     * 订单原始金额
     */
    private BigDecimal orderAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal amount;
    /**
     * 交易请求号
     */
    private String requestId;
    /**
     * 交易装态
     */
    private String trxStatus;
    /**
     * 支付类型（ALIPY,KUAIQIAN,VM,FREE,INTEGRAL)
     */
    private String payType;
    /**
     * 请求渠道(WEB,APP)
     */
    private String reqChannel;
    /**
     * 备用描述
     */
    private String description;
    /**
     * 乐观锁版本号
     */
    private Long version=0L;
    /**
     * 客户端IP
     */
    private String reqIp;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 开始下单时间
     */
    private String startTime;
    /**
     * 结束下单时间
     */
    private String endTime;
    /**
     * 开始支付时间
     */
    private String startPayTime;
    /**
     * 结束支付时间
     */
    private String endPayTime;
   
}
