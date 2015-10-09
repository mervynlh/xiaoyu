package com.yizhilu.os.edu.dao.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.order.QueryTrxorder;
import com.yizhilu.os.edu.entity.order.TrxOrderDTO;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.order.TrxorderExpand;

/**
 * Trxorder管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface TrxorderDao {

	/**
	 * 添加Trxorder
	 * 
	 * @param trxorder
	 *            要添加的Trxorder
	 * @return id
	 */
	public java.lang.Long addTrxorder(Trxorder trxorder);

	/**
	 * 根据id删除一个Trxorder
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTrxorderById(Long id);

	/**
	 * 修改Trxorder
	 * 
	 * @param trxorder
	 *            要修改的Trxorder
	 */
	public void updateTrxorder(Trxorder trxorder);

	/**
	 * 根据id获取单个Trxorder对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Trxorder
	 */
	public Trxorder getTrxorderById(Long id);

	/**
	 * 根据条件获取Trxorder列表
	 * 
	 * @param trxorder
	 *            查询条件
	 * @return List<Trxorder>
	 */
	public List<Trxorder> getTrxorderList(Trxorder trxorder);

	/**
	 * 根据requestId获取Trxorder
	 * 
	 * @param 列表
	 * @return Trxorder
	 */
	public Trxorder getTrxorderByRequestId(String requestId);

	/**
	 * 更新订单状态为成功
	 * 
	 * @param trxorder
	 */
	public Long updateTrxorderStatusSuccess(Trxorder trxorder);

	/**
	 * 订单分页查询 ,根据条件
	 * 
	 * @param QueryTrxorder
	 * @return List
	 */
	public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page);

	/**
	 * 订单分页查询 ,根据条件
	 * 
	 * @param QueryTrxorder
	 * @return List
	 */
	public List<TrxOrderDTO> queryOrderForUc(QueryTrxorder queryTrxorder, PageEntity page);

	/**
	 * 订单id查询流水的课程集合
	 * 
	 * @param orderId
	 * @return
	 */
	public List<Course> getTrxCourseByRequestId(String requestId);

	/**
	 * 订单详情
	 * 
	 * @param id
	 * @return
	 */
	public Trxorder getOrderInfoById(Long id);

	/**
	 * 网站支付成功的订单数量和销售金额
	 * 
	 * @return orderNum(key) 订单数
	 *         salesNum(key) 销售金额
	 */
	public Map<String,Object> getOrderTotalAndSales();
	/**
	 * 获取所有未支付的订单
	 * @return
	 */
	public List<Trxorder> getTrxorderUnPay();
	/**
	 * 修改订单状态
	 * @param trxorder
	 */
	public void updateTrxorderStatus(Trxorder trxorder);
	/**
	 * 删除上个月之前的过期
	 * @param date
	 */
	public void deleteOrderOverStatus(Date date);
	
	/**
	 * 根据id查询订单详情
	 * @param id 订单id
	 * @return 订单详情
	 */
	public TrxorderExpand getTrxorderExpandById(Long id);
	
	/**
	 * 查询教师教学记录
	 * @param trxorderExpand
	 * @return
	 */
	public List<TrxorderExpand> getTrxorderExpandByTeacherId(TrxorderExpand trxorderExpand,PageEntity page);
	
	/**
	 * 获取教师的学生（根据是否有成功的订单）
	 * @param trxorder
	 * @return
	 */
	public List<Trxorder> getTeacherStudents(Trxorder trxorder);
	
	/**
	 * 根据Id修改订单交易状态
	 * @param map id trx_status
	 */
	public void updateTrxorderTrxStatus(Map<String,Object> map);
	
	/**
	 * 已完成课时+1
	 * @param orderId
	 */
	public void updateTrxorderLessionOverAdd(Long orderId);
	/**
	 * 首页订单展视
	 * @return
	 */
	public List<TrxOrderDTO> indexOrder();
	/**
	 * 查询用户是否已报名
	 * @param map
	 * @return
	 */
	public int queryUserIsJoin(Map<String,Object> map);
	
	/**
	 * 更新assess TeaToStu
	 * @param trxorder
	 */
	public void updateTrxorderAssessTeaToStu(Trxorder trxorder);
	/**
	 * 更新assess StuToTea
	 * @param trxorder
	 */
	public void updateTrxorderAssessStuToTea(Trxorder trxorder);
}