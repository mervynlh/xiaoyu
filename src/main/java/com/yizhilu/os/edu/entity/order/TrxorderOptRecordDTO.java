package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退单操作记录
 * @author dingkai
 * @date 2015年9月19日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderOptRecordDTO extends TrxorderOptRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2736648970905791755L;

	private String studentName;// 学生
	private String teacherName;// 老师
	private String mobile;// 手机号
	private String courseName;// 课程名称
	private java.math.BigDecimal orderAmount;//订单原始金额
	private java.math.BigDecimal couponAmount;//优惠券金额
	private java.math.BigDecimal amount;//实际支付金额
	private String requestId;//交易请求号
	private String trxStatus;//交易装态 INIT SUCCESS REFUND CACULE
    private String payType;//支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
    
    private Date startCreateTime;//退款开始时间
    private Date endCreateTime;//退款结束时间
}
