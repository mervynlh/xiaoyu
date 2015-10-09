package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单拓展
 * @author dingkai
 * @date 2015年8月24日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderExpand extends Trxorder implements  Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sellType;// 销售形式
	private String subjectMajorStr;// 一对一课程名称
	
}
