package com.yizhilu.os.edu.entity.card;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @author Administrator
 * 主卡信息返回实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MainCardDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;	
    private String name;
    private java.math.BigDecimal money;
    private int type;	//卡类型(1课程卡2充值卡)  
    private Long num;
    private java.util.Date beginTime;
    private java.util.Date endTime;
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String courseName;
}
