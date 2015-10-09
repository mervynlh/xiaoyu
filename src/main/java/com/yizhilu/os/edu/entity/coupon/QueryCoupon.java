package com.yizhilu.os.edu.entity.coupon;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCoupon implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 5800304649691278968L;
    private Long id;
    /**
     * 优惠卷名称
     */
    private String title;
    /**
     * 使用说明
     */
    private String info;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
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
     * 优惠金额:打折存折扣，定额存优惠金额
     */
    private java.math.BigDecimal amount;
    /**
     * 生成数量
     */
    private Long totalNum;
    /**
     * 类型1为打折2定额3会员优惠券（定额）
     */
    private int type;
    /**
     * 已使用数量
     */
    private int userNum;
    /**
     * 订单支付成功数量
     */
    private int payNum;
    /**
     * 操作者
     */
    private String optuserName;
    /**
     * 生成开始时间
     */
    private String startCreateTime; 
    /**
     * 生成结束时间
     */
    private String endCreateTime; 
    /**
     * 关键字类型
     */
    private int keyWordType;
    /**
     * 关键字
     */
    private String keyWord;
    /**
     * 默认0未生成优惠券编码，1已生成
     */
    private int isCouponCode;
    /**
     * 创建者类型 0 后台管理员 1前台教师
     */
    private Long createType;
}
