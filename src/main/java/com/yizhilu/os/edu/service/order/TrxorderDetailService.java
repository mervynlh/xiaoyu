package com.yizhilu.os.edu.service.order;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
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
public interface TrxorderDetailService {

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
     * 根据流水id修改课时状态
     * @param detailId 流水id
     * @param status 课时状态
     */
    public void updateTrxorderDetailStatusById(Long detailId,Long status);

    /**
     * 根据id获取单个TrxorderDetail对象
     * @param id 要查询的id
     * @return TrxorderDetail
     */
    public TrxorderDetail getTrxorderDetailById(Long id);

    /**
     * 查询该用户购买过的课程
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId);
    
    
    /**
     * 根据条件获取TrxorderDetail列表
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail);
    
    /**
     * 根据订单id获取流水列表
     * @param trxorderId 订单id
     * @return
     */
    public List<TrxorderDetail> getTrxorderDetailListByTrxorderId(Long trxorderId);
    /**
     * 根据条件获取TrxorderDetail 流水列表分页
     * @param trxorderDetail 查询条件
     * @return List<QueryTrxorderDetail>
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail,PageEntity page);
    
    /**
     * 取消订单
     * @param orderId 订单id
     */
    public void updateTrxorderDetailCancel(Long orderId,Long userType) throws Exception;
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     * @Date  2014-09-28
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id);
    
    /**
     * 根据条件订单状态(学生)
     * @param detail
     * @param page
     * @return
     */
    public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusStu(TrxorderDetail detail,PageEntity page);
    /**
     * 根据条件订单状态(教师)
     * @param detail
     * @param page
     * @return
     */
    public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusTea(TrxorderDetail detail,PageEntity page);
    /**
	 * 查询流水列表(定时自动确认付款任务使用) 
	 * @param detail
	 * @return
	 */
	public List<TrxorderDetail> queryTrxorderDetailByStatus(TrxorderDetail detail);
	
	/**
	 * 定时自动确认订单流水
	 */
	public void updateTrxorderAutomaticConfirm();
	
	/**
	 * 定时 将待上课状态修改为待确认课酬(根据结束时间)
	 */
	public void updateTrxorderDetailByTrxStatusAndStatus();
    /**
	 * 我的课表
	 * @param query
	 * @return
	 */
	public List<TrxorderDetail> queryMySchedulePage(QueryTrxorderDetail query,PageEntity page);
    /**
     * 取消约课(弃用)
     * @param map(vsersion,id)
     * @return
     */
    public void updateStatusCancelClass(Long id,Long teacherId)throws Exception;
    
    /**
     * 修改时间(学生)
     * @param map (id,startTime,endTime,updateTime)
     */
    public void updateTimeStuById(Long id,String startTime,String endTime,Long userId,Long teacherId)throws Exception;
    /**
     * 修改时间(老师)
     * @param map (id,startTime,endTime,updateTime)
     */
    public void updateTimeTeaById(Long id,String startTime,String endTime,Long userId,Long teacherId)throws Exception;
    /**
     * 学生确认约课
     * @param id 流水id
     */
    public void updateConfirmCourseStudent(Long id,Long userId,Long teacherId) throws Exception;
    /**
     * 老师确认约课
     * @param id 流水id
     */
    public void updateConfirmCourseTeacher(Long id,Long userId,Long teacherId) throws Exception;
    
    /**
     * 课时退款(操作订单时调用)
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusRefund(TrxorderDetail trxorderDetail);
    /**
     * 课时退款审核
     * @param trxorderDetail
     * @param description 退课原因
     */
    public void updateTrxorderDetailStatusAudit(TrxorderDetail trxorderDetail,String description,Long userType);
    
    /**
     * 课时退款通过
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusRefundSuccess(TrxorderDetail trxorderDetail,Long userType) throws AccountException, StaleObjectStateException;
    /**
     * 课时退款取消
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusRefundCancel(TrxorderDetail trxorderDetail,Long userType);
    /**
     * 课时付款
     * @param trxorderDetail
     */
    public void updateTrxorderDetailStatusPay(TrxorderDetail trxorderDetail) throws AccountException, StaleObjectStateException;
    
    /**
     * 已上课+1
     * @param orderId
     */
    public void  updateTrxorderLessionOverAdd(Long orderId);
    /**
     * 订单下所有流水列表
     * @param requestId
     */
    public List<TrxorderDetail> queryTrxorderDetailRefund(String requestId);

    
    /**
     * 获取课程状态数量
     * @param userId 用户id
     * @param teacherId 教师id
     * @return
     */
    public Map<String,Object> getTrxorderDetailCountById(Long userId,Long teacherId);
    
    /**
     * 获取根据开始和结束日期获取流水数量
     * @param map 开始日期，结束日期，用户id
     * @return
     */
    public Set<Integer> getTrxorderDetailCountByTimeAndId(Long userId,Long teacherId,Date startTime,Date endTime,Long status);

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
    public List<List<TrxorderDetailDTO>> queryMyStudyHistoryByTrxorderDetail(Long userId, PageEntity page);
    /**
     * 查询我的学习记录（不分页）
     * @param userId
     * @return
     */
    public List<List<TrxorderDetailDTO>> queryMyStudyHistoryList(Long userId);
    /**
     * 根据订单id获取流水数量
     * @param trxorderId 订单id
     * @return
     */
    public int getTrxorderDetailListCount(Long trxorderId);
    /**
     * 根据流水id修改课时小结
     * @param TrxorderDetail
     */
    public void updateTrxorderDetailSummaryById(TrxorderDetail trxorderDetail);
    
    
}