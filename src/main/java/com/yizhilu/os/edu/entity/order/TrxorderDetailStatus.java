package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 课程状态
 * @author dingkai
 * @date 2015年8月31日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderDetailStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status; // 状态
	private int count;// 数量
}
