package com.yizhilu.os.edu.entity.user;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserChart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dateTime;	//返回的时间s
	private int allStudentNum;	//注册学生个数
	private int payOrderNum;	//付款订单
	private int orderNum;		//订单数量
	private BigDecimal amountNum;		//实付金额
	private BigDecimal cashMonty;//课程卡金额
}
