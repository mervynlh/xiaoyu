package com.yizhilu.os.edu.entity.coupon;

import java.io.Serializable;
import java.util.List;

import com.yizhilu.os.edu.entity.course.Course;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponDTO implements Serializable{
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
     * 指的是该优惠券的适用范围_项目ID
     */
    private String subjectId;
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
     * 限制课程集合
     */
    private List<Course> courses;
    /**
     * 优惠券编码集合
     */
    private List<String> couponCodes;
    /**
     * 限制项目名称
     */
    private String subjectName;
    /**
     * 默认0未生成优惠券编码，1已生成
     */
    private int isCouponCode;
    /**
     * 创建者类型
     */
    private int createType;
    
    /**
     * 教师id
     */
    private Long teacherId;
    
    /**
     * 优惠券领取数量
     */
    private Integer receiveNum;
}
