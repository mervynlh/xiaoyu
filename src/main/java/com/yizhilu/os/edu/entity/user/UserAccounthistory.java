package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccounthistory implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8822827743580244283L;
    private Long id;
    private Long userId;// 用户id
    private Long trxorderId;// 订单id
    private String requestId ;//外部请求订单号
    private String outTradeNo;//支付宝订单号
    private Long accountId;// 相关账户ID
    private java.util.Date createTime;
    private int isDisplay;// 是否显示账户历史记录:0显示;1不显示
    private java.math.BigDecimal balance;// 当前余额
    private java.math.BigDecimal cashAmount;// cash余额
    private java.math.BigDecimal vmAmount;// vm余额
    private java.math.BigDecimal trxAmount;// 交易金额
    private String description;// 账户历史内容描述
    private String actHistoryType;// 帐务历史类型.充值。消费等
    private String bizType;// 业务类型(课程订单、会员订单、图书订单)
    private Long version;// 乐观锁版本号
    private String createUser;//操作人
}
