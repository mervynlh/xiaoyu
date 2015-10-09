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
public class CouponCodeStatus implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status; // 状态
	private int count;// 数量
}
