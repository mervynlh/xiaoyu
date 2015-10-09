package com.yizhilu.os.edu.entity.coupon;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponLimit implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 优惠券ID
	 */
    private Long couponId;
    /**
     * 课程id
     */
    private Long courseId;
}
