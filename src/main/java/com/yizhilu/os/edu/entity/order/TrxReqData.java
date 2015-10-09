package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.entity.user.UserAccount;

import lombok.Data;

/**
 * @ClassName  com.yizhilu.os.edu.entity.order.TrxReqData
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-7-2 上午9:48:43
 */
@Data
public class TrxReqData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7415114287634426319L;
    private Long userId;//用户id
    private Long type;//购物车类型
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功
    private java.math.BigDecimal orderAmount;//订单原始金额
    private java.math.BigDecimal couponAmount;//优惠券金额
    private java.math.BigDecimal amount;//实际支付金额(订单的金额 )
    private java.math.BigDecimal bankAmount;//本次还需提交到银行的金额，账户中有余额的情况bankAmount<=amount
    private String couponCode;//优惠券编码
    private Long trxorderId;
    private String requestId;//交易请求号
    private String trxStatus;//交易装态 INIT SUCCESS REFUND CACULE
    private PayType payType;//支付类型（ALIPY,KUAIQIAN,CARD,FREE,INTEGRAL）
    private String reqChannel;//请求渠道(WEB,APP)
    private String description;//备用描述
    private Long version;//乐观锁版本号
    private String reqIp;//客户端IP
    
    private boolean isRecharge=false;//是否充值操作
    private UserAccount userAccount;
    private String out_trade_no;//提交到支付宝的请求号
    private Long subjectId;//科目id
    private Long courseId;//课程id
    private Long teacherId;//教师id
    private String studentName;//学生姓名
    private String mobile;//手机号
    private String remarks;//备注
    private Long lessionNum;//总课时
    private String courseModel; // 上课方式
    private String dateValue;//上课时间字符串
    private String studyAddress;//上课地址

}
