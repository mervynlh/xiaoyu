package com.yizhilu.os.edu.entity.cashOut;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提现操作记录表
 * @author WangKaiping
 * @version 2015-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CashOutOptRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2736648970905791755L;
	private Long id; // 主键
    private Long cashoutId; // 提现申请ID
    private String type; // 操作类型
    private Long optuser; // 操作用户ID
    private String optusername; // 操作用户名
    private String desc; // 操作描述
    private java.util.Date createtime; // 操作时间
}
