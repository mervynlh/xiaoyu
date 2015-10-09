package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单查询条件类
 * 
 * @author guozhenzhou
 * @Data 2014-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryTrxorder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1996291355323321464L;
	private Long id;
	private Long userId;// 用户id
	private java.util.Date createTime;//
	private java.util.Date payTime;
	private java.math.BigDecimal orderAmount;// 订单原始金额
	private java.math.BigDecimal couponAmount;// 优惠券金额
	private java.math.BigDecimal amount;// 实际支付金额
	private java.math.BigDecimal cashAmount;// 实际支付的cash金额
	private java.math.BigDecimal minusAmount;//课时折扣金额
	private java.math.BigDecimal vmAmount;// 实际支付的vm金额
	private Long couponCodeId = 0L;// 优惠券id
	private String requestId;// 交易请求号
	private String trxStatus;// 交易装态 INIT SUCCESS REFUND CACULE
	private String payType;// 支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
	private String reqChannel;// 请求渠道(WEB,APP)
	private Long version = 0L;// 乐观锁版本号
	private String description;
	private String reqIp;// 客户端IP
	private String userName;// 用户名
	private String email;// 用户邮箱
	private java.util.Date startCreateTime;// 下单时间
	private java.util.Date endCreateTime;// 下单时间
	private java.util.Date startPayTime;// 支付时间
	private java.util.Date endPayTime;//
	private String couponCode;//优惠券编码
	private String mobile;//手机号码
	private String studentName;//学生姓名
    private String remarks;//备注
    private Long lessionNum;//总课时
    private Long lessionOver;//已完成
    private Long courseId;//课程id
    private String courseName;//课程名称
    private Date loseTime;//过期时间
    private Long teacherId;//教师id
    private Long evaNum;//评价数
    private String courseModel;// 上课方式
    private String studyAddress;// 上课地址
    
    private Long subjectId;// 阶段id
    private Long gradeId;// 年级id
    private Long majorId;// 科目id
}
