package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 流水查询条件类
 * @author guozhenzhou
 * @Data   2014-09-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class QueryTrxorderDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8823831006166279064L;

	  private Long id;
	    private Long userId;//用户id
	    private Long courseId;//相关联的课程id（前台快照）
	    private Long trxorderId;//交易订单ID
	    private java.util.Date authTime;//上课后的流水过期时间
	    private java.util.Date createTime;//下单时间
	    private java.util.Date payTime;//支付成功时间
	    private java.math.BigDecimal sourcePrice;//原价格（前台快照）
	    private java.math.BigDecimal currentPrice;//销售价格（前台快照）
	    private String courseName;//课程名称（前台goods快照）
	    private String trxStatus;//订单状态（前台goods快照）
	    private String authStatus;//课程状态（INIT，SUCCESS，REFUND，CLOSED，LOSED）
	    private String requestId;//订单请求号
	    private String description;//描述
	    private Long version=1L;//乐观锁版本号
	    private java.util.Date lastUpdateTime;//最后更新时间
	    private java.util.Date refundTime;// 退课时间
	    private String userName;
	    private String email;
	    private String teacherSummary;// 教师小结
	    private String userSummary;// 学生小结
	    
	    private String ifConfirm;//是否确认时间 ALREADYCONFIRM 已确认 TEAUNCONFIRM 教师未确认 STUUNCONFIRM 学生未确认
	    private java.util.Date startAuthTime;
	    private java.util.Date endAuthTime;
	    private java.util.Date startCreateTime;
	    private java.util.Date endCreateTime;
	    private java.util.Date startPayTime;
	    private java.util.Date endPayTime;
	    private Date startTime;//上课开始时间
	    private Date endTime;//上课结束时间
	    private Long currentLession;//当前课时
	    private Long status;//状态 1.待确认约课，2待上课，3待确认课酬4.待评价5.已评价，5已取消
	    private Long teacherConfirm;//教师是否确认 0未确认，1已确认
	    private Long studentConfirm;//学生是否确认 0未确认，1已确认
	    private Long timetableId;//教师课表id
	    private Long courseType;//课程类型 1.一对一，2大小班
	    private long teacherId;//教师id
	    private String teacherName;//教师名称
	    private int num;//显示条数
	    private String mobile;//家长手机
}
