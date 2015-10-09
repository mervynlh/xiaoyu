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
public class CouponCode implements Serializable{
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
    
}
