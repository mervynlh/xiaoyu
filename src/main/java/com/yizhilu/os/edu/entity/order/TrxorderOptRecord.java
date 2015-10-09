package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单操作记录表
 * @author guozhenzhou
 * @version 2014-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderOptRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2736648970905791755L;
	private Long id;
    private Long orderId;
    private String type;
    private Long optuser;
    private String optusername;
    private String desc;
    private java.util.Date createtime;
    private Long userType;// 申请人类型
    private String applicant;// 申请人
}
