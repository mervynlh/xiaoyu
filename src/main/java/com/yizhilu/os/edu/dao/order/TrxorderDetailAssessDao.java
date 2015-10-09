package com.yizhilu.os.edu.dao.order;

import com.yizhilu.os.edu.entity.order.TrxorderDetailAssess;

/**
 * 流水评价DAO接口
 * @author dingkai
 * @date 2015年8月24日
 */
public interface TrxorderDetailAssessDao {

	/**
	 * 添加流水评价
	 * @param detailAssess 流水评价实体
	 */
	public void addDetailAssess(TrxorderDetailAssess detailAssess);
	
	/**
	 * 根据订单id获取订单评价列表
	 * @param detailId
	 */
	public TrxorderDetailAssess getDetailAssessInfoByDetailId(Long detailId);
}
