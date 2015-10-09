package com.yizhilu.os.edu.entity.card;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CourseCardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; //
	private String cardCode;
	private int type;
	private Long cardNode; // 课程编码
	private java.util.Date beginTime; // 开始时间
	private java.util.Date endTime; // 结束时间
	private String createUser; // 创建人
	private java.util.Date createTime; // 创建时间
	private String cardCodePassword; // 充值卡密码
	private Long userId; // 学员id
	private String email; // 邮件
	private String remark; // 备注信息
	private Long id;
	private BigDecimal money;
	private java.util.Date useTime; // 使用时间
	private String courseName;	//关联课程名字
	private String status;	//状态
	private String requestId;//请求Id(订单号)
	private String orderId;	//订单id
	
}
