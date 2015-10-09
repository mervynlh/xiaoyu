package com.yizhilu.os.edu.service.order;

import com.yizhilu.os.edu.entity.order.TrxorderDetailAssess;

/**
 * 流水评价Service接口
 * @author dingkai
 * @date 2015年8月24日
 */
public interface TrxorderDetailAssessService {
	/**
	 * 添加流水评价
	 * @param detailId 流水id
	 * @param content 评价内容
	 * @param teacherId 教师id
	 * @param userId 学生id
	 */
	public void addDetailAssess(Long detailId,String content,Long teacherId,Long userId);
	
	
	/**
	 * 根据订单id获取订单评价列表
	 * @param detailId
	 */
	public TrxorderDetailAssess getDetailAssessInfoByDetailId(Long detailId);
	
}
