package com.yizhilu.os.edu.dao.order;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetail;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetailCourse;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderDetailDTO;
import com.yizhilu.os.edu.entity.order.TrxorderDetailStatus;

/**
 * TrxorderDetail管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface TrxorderDetailDao {

    /**
     * 添加TrxorderDetail
     * @param trxorderDetail 要添加的TrxorderDetail
     * @return id
     */
    public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail);
    /**
     * 批量添加TrxorderDetail
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList);
    /**
     * 根据id删除一个TrxorderDetail
     * @param id 要删除的id
     */
    public void deleteTrxorderDetailById(Long id);

    /**
     * 修改TrxorderDetail
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail);
    /**
     * 根据id获取单个TrxorderDetail对象
     * @param id 要查询的id
     * @return TrxorderDetail
     */
    public TrxorderDetail getTrxorderDetailById(Long id);

    /**
     * 根据条件获取TrxorderDetail列表
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail);
    /**
     * 查询该用户购买过的课程
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId);
    
    /**
     * 更新流水状态为成功，网银回调用
     * @param trxorderDetail
     */
    public Long updateTrxorderDetailStatusSuccess(TrxorderDetail trxorderDetail);
    
    /**
     *根据 订单条件查询分页
     *@param trxorderDetail
     *@return List<QueryTrxorderDetail>
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail,PageEntity page);
    
    /**
     * 取消订单
     * @param orderId 订单id
     */
    public void updateTrxorderDetailCancel(Long orderId);
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id);
    
    /**
     * 根据用户id和状态查询课程列表(学生)
     * @param detail
     * @return
     */
    public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusStu(QueryTrxorderDetailCourse detailCourse,PageEntity page);
    /**
     * 根据用户id和状态查询课程列表(老师)
     * @param detail
     * @return
     */
    public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusTea(QueryTrxorderDetailCourse detailCourse,PageEntity page);
    /**
     * 修改订单详情的状态
     * @param detail
     */
	public void updateTrxorderDetailStatus(TrxorderDetail detail);
	
	/**
	 * 根据流水id修改课时状态
	 * @param map 流水id 课时状态status
	 */
	public void updateTrxorderDetailStatusById(Map<String,Object> map);
	/**
	 * 查询流水列表(定时自动确认付款任务使用) 
	 * @param detail
	 * @return
	 */
	public List<TrxorderDetail> queryTrxorderDetailByStatus(TrxorderDetail detail);

	/**
	 * 定时 将待上课状态修改为待确认课酬(根据结束时间)
	 */
	public void updateTrxorderDetailByTrxStatusAndStatus(TrxorderDetail detail);
	
	/**
	 * 我的课表
	 * @param query
	 * @return
	 */
	public List<TrxorderDetail> queryMySchedulePage(QueryTrxorderDetail query,PageEntity page);
    /**
     * 取消约课
     * @param map(id)
     * @return
     */
    public void updateStatusCancelClass(Map<String,Object> map);
    
    /**
     * 修改时间(学生)
     * @param map(id,startTime,endTime,updateTime)
     */
    public void updateTimeStuById(Map<String,Object> map);
    /**
     * 修改时间(老师)
     * @param map(id,startTime,endTime,updateTime)
     */
    public void updateTimeTeaById(Map<String,Object> map);
    /**
     * 确认约课
     * @param map(id,updateTime)
     */
    public void updateConfirmCourse(Map<String,Object> map);
    /**
     * 课时退款审核
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusAudit(TrxorderDetail trxorderDetail);
    
    /**
     * 课时退款通过
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusRefundSuccess(TrxorderDetail trxorderDetail);
    /**
     * 课时退款取消
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusRefundCancel(TrxorderDetail trxorderDetail);
    /**
     * 课时付款
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusPay(TrxorderDetail trxorderDetail);
    /**
     * 订单下所有流水列表
     * @param requestId
     */
    public List<TrxorderDetail> queryTrxorderDetailRefund(String requestId);
    
    /**
     * 根据id获取课表课程数量
     * @param map 教师id或用户id
     * @return
     */
    public List<TrxorderDetailStatus> getTrxorderDetailCountById(Map<String,Object> map);
    
    /**
     * 获取根据开始和结束日期获取流水数量
     * @param map 开始日期，结束日期，用户id
     * @return
     */
    public List<Integer> getTrxorderDetaiCountByTimeAndId(Map<String,Object> map);
    /**
     * 获取根据开始和结束日期获取流水列表
     * @param map 开始日期，结束日期，用户id
     * @return
     */
    public List<QueryTrxorderDetailCourse> getTrxorderDetaiListByTime(Map<String,Object> map);
    /**
     * 学生选课查询是否与自己其他课程时间段冲突
     * @param query
     * @return
     */
    public List<TrxorderDetail> queryTimeStudentConflict(QueryTrxorderDetail query);
    /**
     * 老师课程时间安排查询是否与自己其他课程时间段冲突
     * @param query
     * @return
     */
    public List<TrxorderDetail> queryTimeTeacherConflict(QueryTrxorderDetail query);
    
    /**
     * 查询我的学习记录
     * @param userId
     * @return
     */
    public List<TrxorderDetailDTO> queryMyStudyHistoryByTrxorderDetail(Long userId, PageEntity page);
    /**
     * 查询我的学习记录（不分页）
     * @param userId
     * @return
     */
    public List<TrxorderDetailDTO> queryMyStudyHistoryList(Long userId);
    /**
     * 根据订单id查询流水数量
     * @param trxorderId 订单id
     * @return 流水数量
     */
    public int getTrxorderDetailListCount(Long trxorderId);
    
    /**
     * 根据流水id修改课时小结
     * @param TrxorderDetail
     */
    public void updateTrxorderDetailSummaryById(TrxorderDetail trxorderDetail);
}