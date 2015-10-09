package com.yizhilu.os.edu.service.order;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.order.QueryTrxorder;
import com.yizhilu.os.edu.entity.order.TrxOrderDTO;
import com.yizhilu.os.edu.entity.order.TrxReqData;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.order.TrxorderExpand;

/**
 * 
 * @ClassName  com.yizhilu.os.edu.service.order.TrxorderService
 * @description 订单操作
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-7-2 上午9:31:29
 */
public interface TrxorderService {

    /**
     * 下订单操作
     * @param sourceMap 需要的参数
     * @return id
     */
    public Map<String, Object> addTrxorder(Map<String, String> sourceMap) throws Exception;
    /**
     * 班课下订单操作
     * @param sourceMap 需要的参数
     * @return id
     */
    public Map<String, Object> addClassTrxorder(Map<String, String> sourceMap) throws Exception;
    /**生成订单号
     * @return
     */
    public String getOrderNum(Long userId);
    /**
     * 根据id删除一个Trxorder
     * @param id 要删除的id
     */
    public void deleteTrxorderById(Long id);

    /**
     * 修改Trxorder
     * @param trxorder 要修改的Trxorder
     */
    public void updateTrxorder(Trxorder trxorder);

    /**
     * 根据id获取单个Trxorder对象
     * @param id 要查询的id
     * @return Trxorder
     */
    public Trxorder getTrxorderById(Long id);

    /**
     * 根据条件获取Trxorder列表
     * @param trxorder 查询条件
     * @return List<Trxorder>
     */
    public List<Trxorder> getTrxorderList(Trxorder trxorder);
    
    /**
     * 免费赠送下订单操作
     * @param sourceMap 需要的参数
     * @return id
     */
    public Map<String, Object> addFreeTrxorder(Map<String, String> sourceMap) throws Exception;
    
    
    /**
     * 根据requestId获取Trxorder
     * @param 列表
     * @return Trxorder
     */
    public Trxorder  getTrxorderByRequestId(String requestId );
    
    /**
     * 订单回调支付成功操作
     * @param
     * @return
     * @throws ParseException 
     */
    public Map<String, String> updateCompleteOrder(TrxReqData trxReqData)throws AccountException, StaleObjectStateException, ParseException;
    
    /**
     * 更新优惠券信息
     * @param trxorder
     */
    public void updateCouponCodeInfo(Trxorder trxorder);
    /**
	 * 修改教师的学生人数
	 * @param trxorder
	 */
    public void updateTeacherProfileStudentNum(Trxorder trxorder);
    /**
	 * 修改教师课表
	 * @param trxReqData
	 * @throws ParseException 
	 */
	public String updateTeacherClassHour(TrxReqData trxReqData) throws ParseException;
	/**
	 * 更新班课报名人数
	 * @param trxorder
	 */
	public void updateClassJoinNum(Trxorder trxorder);
    /**
     * 订单回调支付成功,预处理查询
     * @param
     * @return
     */
    public TrxReqData preQueryCompleteOrder(TrxReqData trxReqData)throws AccountException;
    
    /**
     * 更新订单状态为成功,网银的回调
     * @param trxorder
     */
    public void updateTrxorderStatusSuccess(Trxorder trxorder) throws StaleObjectStateException;
    /**
     * 订单分页查询 ,根据条件
     * @param QueryTrxorder
     * @return List
     */
    public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder,PageEntity page);
    
    
    /**
     * 个人中心订单查询
     * @param queryTrxorder
     * @param page
     * @return
     */
    public List<TrxOrderDTO> queryOrderForWebUc(QueryTrxorder queryTrxorder,PageEntity page);
    
    
    
    /**
     * 订单的课程集合
     * @param id
     * @return
     */
	public List<Course> getTrxCourseByRequestId(String requestId);
	/**
	 * 订单详情
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
	 * 定时修改今日订单是否有失效订单
	 */
	public void updateOrderDeadline();
	/**
	 * 定时删除一个月前之前的订单
	 */
	public void deleteOrderDeadline();
	/**
	 * 根据id查询订单详情
	 * @param id 订单id
	 * @return TrxorderExpand 订单详情
	 */
	public TrxorderExpand getTrxorderExpandById(Long id);
	
	/**
	 * 查询教师教学记录
	 * @param trxorderExpand
	 * @return
	 */
	public List<TrxorderExpand> getTrxorderExpandByTeacherId(TrxorderExpand trxorderExpand,PageEntity page);
	
	/**
	 * 订单申请退款
	 * @param orderId 订单id
	 * @param description 退款原因
 	 */
	public void updateTrxorderRefundAudit(Long orderId,String description,Long userType);
	
	/**
	 * 订单退款
	 * @param trxorder
	 */
	public void updateTrxorderRefund(Trxorder trxorder) throws AccountException, StaleObjectStateException,Exception;
	
	/**
	 * 根据订单id修改订单状态
	 * @param id 订单id
	 * @param trxStatus 订单状态
	 */
	public void updateTrxorderTrxStatus(Long id,String trxStatus);
	/**
	 * 首页订单展视（缓存）
	 * @return
	 */
	public List<TrxOrderDTO> indexOrder();
	/**
	 * 查询用户是否已报名
	 * @param map
	 * @return
	 */
	public int queryUserIsJoin(Long userId,Long courseId,String trxStatus);
	
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