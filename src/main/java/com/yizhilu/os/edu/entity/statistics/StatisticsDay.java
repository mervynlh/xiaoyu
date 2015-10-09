package com.yizhilu.os.edu.entity.statistics;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Administrator
 * 2015-8-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StatisticsDay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 统计日期
	 */
    private java.util.Date statisticsTime;
    /**
     * 登录人数（活跃人数 ）
     */
    private Long loginNum;
    /**
     * 新注册人数
     */
    private Long registerNum;
    /**
     * 支付成功人数（会员）
     */
    private Long userPayMemberNum;
    /**
     * 支付成功人数（课程）
     */
    private Long userPayCourseNum;
    /**
     * 支付成功人数（课程+会员的合计）
     */
    private Long userPayNum;
    /**
     * 课程下单总数量
     */
    private Long courseNum;
    /**
     * 课程支付成功数量
     */
    private Long coursePayNum;
    /**
     * 课程支付成功金额
     */
    private Long coursePayAmount;
    /**
     * 课程退费订单量
     */
    private Long courseRefundNum;
    /**
     * 会员订单下单总数量
     */
    private Long memberNum;
    /**
     * 会员支付成功数量
     */
    private Long memberPayNum;
    /**
     *  会员支付成功金额
     */
    private Long memberPayAmount;
    /**
     * 发表观点数
     */
    private Long weiboNum;
    /**
     *  博文数
     */
    private Long blogNum;
    /**
     * 课程评论数
     */
    private Long assesNum;
    /**
     *  问题数
     */
    private Long quesNum;
    /**
     *  生成时间
     */
    private java.util.Date createTime;
    /**
     * 发布课程
     */
    private int coursePubNum;
    /**
     * 入驻教师
     */
    private int teacherNum;
    /**
     * 用户提现数量
     */
    private int userCashOutNum;
    /**
     * 提现金额
     */
    private Long userCashOutAmount;
}
