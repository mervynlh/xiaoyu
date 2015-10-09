package com.yizhilu.os.edu.entity.order;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 流水记录操作记录实体
 * @author dingkai
 * @date 2015年9月17日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrxdetailOptRecordDTO extends TrxdetailOptRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String requestId;//订单请求号
    private String courseName;//课时名称
    private java.math.BigDecimal sourcePrice;//原价格（前台快照）
    private java.math.BigDecimal currentPrice;//销售价格（前台快照）(退款应退金额)平均算出来的金额
    private String realname;
}
