package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Trxorder implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 5113068126680550371L;
    private Long id;
    private Long userId;//用户id
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功
    private java.math.BigDecimal orderAmount;//订单原始金额
    private java.math.BigDecimal minusAmount;//课时折扣金额
    private java.math.BigDecimal couponAmount;//优惠券金额
    private java.math.BigDecimal amount;//实际支付金额
    private java.math.BigDecimal cashAmount;//实际支付的cash金额
    private java.math.BigDecimal vmAmount;//实际支付的vm金额
    private Long couponCodeId=0L;//优惠券编码id
    private String requestId;//交易请求号
    private String trxStatus;//交易装态 INIT SUCCESS REFUND CACULE
    private String payType;//支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
    private String reqChannel;//请求渠道(WEB,APP)
    private String description;//备用描述
    private Long version=0L;//乐观锁版本号
    private String reqIp;//客户端IP
    private String studentName;//学生姓名
    private String mobile;//手机号
    private String remarks;//备注
    private Long lessionNum;//总课时
    private Long courseId;//课程id
    private String courseName;//课程名称
    private Date loseTime;//过期时间
    private Long teacherId;//教师id
    private Long lessionOver;//完成课时
    private String courseModel; // 上课方式
    private String teacherName; // 教师姓名
    private String studyAddress; // 上课地址
    private Long evaNum;//评价数
    private Long assessTeatostu=-1L;//是否已评价(老师给学生) -1初始化 0未评价 1已评价
    private Long assessStutotea=-1L;//是否已评价(学生给老师) -1初始化 0未评价 1已评价
}
