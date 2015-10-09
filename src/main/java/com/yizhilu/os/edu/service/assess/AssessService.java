package com.yizhilu.os.edu.service.assess;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.assess.Assess;
import com.yizhilu.os.edu.entity.assess.AssessDto;
import com.yizhilu.os.edu.entity.assess.QueryAssess;

/**
 * @author wangzhuang
 * Assess管理接口
 */
public interface AssessService {

	/**
	 * 学生对教师 添加评论
	 */
	public java.lang.Long addAssess(Assess assess);
	/**
	 * 
	 * 根据评论类型查询评论
	 * @param assess
	 * @return
	 */
	public List<Assess> getAssessListByType(Long type);
	/**
	 * 根据id删除评论信息
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
	 * 首页显示评论(缓存)
	 * @param num
	 * @return
	 */
	public List<AssessDto> indexAssess(int num,HttpServletRequest request) throws Exception;
	
	/**
	 *   获取类型数量
	 * @param userId 用户id
	 * @param teacher 教师id
	 * @param status 状态
	 * @return
	 */
	public Map<String,Object> getCountByType(Long userId,Long teacherId,Long status);
	
	/**
	 * 根据条件查询评论列表
	 * @param userId 用户id
	 * @param teacherId 教师id
	 * @param type 类型1好评2中评3差评
	 * @param status 状态 1对学生的评价2对老师评价
	 * @param page 分页实体
	 * @return
	 */
	public List<AssessDto> getAssessListByTypeStatus(Long userId,Long teacherId,Long type,Long status,PageEntity page);
}
