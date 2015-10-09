package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流水操作记录表
 * @author guozhenzhou
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxdetailOptRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9062964350026862182L;
	private Long id;
    private Long trxorderId;
    private Long trxorderDetailId;
    private String type;
    private Long optuser;
    private String optusername;
    private String desc;
    private java.util.Date createTime;
    private Long userType;// 申请人类型
    private String applicant;// 申请人
}
