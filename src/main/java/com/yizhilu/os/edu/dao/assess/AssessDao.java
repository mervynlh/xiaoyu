package com.yizhilu.os.edu.dao.assess;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.assess.Assess;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.assess.AssessType;
import com.yizhilu.os.edu.entity.assess.QueryAssess;

/**
 * @author wangzhuang
 */
public interface AssessDao {

	/**
	 * 添加评论  学生对教师
	 * 
	 */
	public java.lang.Long addAssess(Assess assess);
	
	/**
	 * 根据评论类型查询评论
	 * 
	 */
	public List<Assess> getAssessListByType(Long type);
	/**
	 * 根据edu_assess表中的id删除评论数据 待定 用于/admin中的 删除评论
	 * @param ids
	 */
	public void deleteAssessById(String ids);
	
	/**
	 * 根据订单id获取学生对老师的评价
	 * @param orderId 订单id
	 * @return
	 */
	public Assess getAssessByOrderId(Long orderId);
	/**
	 * 根据条件查询教师评价（前台）
	 * @param query
	 * @param page
	 * @return
	 */
	public List<AssessDto> queryAssessListByCondition(QueryAssess query,PageEntity page);
	/**
	 * 获取类型数量
	 * @param map userId 用户类型 status状态
	 * @return
	 */
	public List<AssessType> getCountByType(Map<String,Object> map);
	
	/**
	 *  根据用户Id教师id类型id状态id查询评论表 
	 * @param Assess 
	 * @param page
	 * @return
	 */
	public List<AssessDto> getAssessListByTypeStatus(Assess Assess,PageEntity page);
}
