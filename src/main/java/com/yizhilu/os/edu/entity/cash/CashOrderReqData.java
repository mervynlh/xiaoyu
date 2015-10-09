package com.yizhilu.os.edu.entity.cash;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.entity.user.UserAccount;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CashOrderReqData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单id
	 */
	private Long cashOrderId;
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
    private BigDecimal bankAmount;//本次还需提交到银行的金额
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
    private PayType payType;
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
    private Long version;
    /**
     * 客户端IP
     */
    private String reqIp;
    private UserAccount userAccount;
    private String out_trade_no;//提交到支付宝的请求号
}
