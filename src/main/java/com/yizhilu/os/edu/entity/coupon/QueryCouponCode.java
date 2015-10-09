package com.yizhilu.os.edu.entity.coupon;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 优惠卷编码类
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCouponCode implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 优惠卷id
	 */
    private Long couponId;
    /**
     * 优惠卷状态）1未使用2为已使用3过期4作废
     */
    private int status;
    /**
     * 订单request_id
     */
    private String requestId;
    /**
     * 订单id
     */
    private Long trxorderId;
    /**
     * 使用者id
     */
    private Long userId;
    /**
     * 优惠卷编码
     */
    private String couponCode;
    /**
     * 生成时间
     */
    private java.util.Date createTime;
    /**
     * 使用时间
     */
    private java.util.Date useTime;
    /**
     * 支付成功时间
     */
    private java.util.Date payTime;
    /**
     * 优惠卷名称
     */
    private String title;
   
    /**
     * 有效期开始时间
     */
    private java.util.Date startTime;
    /**
     * 有效期结束时间
     */
    private java.util.Date endTime;
    /**
     * 使用限额0.不限制，否则大于等于
     */
    private java.math.BigDecimal limitAmount;
    
    /**
     * 类型1为打折2定额3会员优惠券（定额）
     */
    private int type;
    /**
     * 操作者
     */
    private String optuserName;
    /**
     * 是否支付
     */
    private int isPay;
}
