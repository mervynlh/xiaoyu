package com.yizhilu.os.edu.dao.impl.order;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.dao.order.TrxorderDetailDao;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetail;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetailCourse;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderDetailDTO;
import com.yizhilu.os.edu.entity.order.TrxorderDetailStatus;

/**
 *
 * TrxorderDetail
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("trxorderDetailDao")
public class TrxorderDetailDaoImpl extends GenericDaoImpl implements TrxorderDetailDao{

    public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail) {
        return this.insert("TrxorderDetailMapper.createTrxorderDetail",trxorderDetail);
    }
    /**
     * 批量添加TrxorderDetail
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetailList){
        this.insert("TrxorderDetailMapper.addBatchTrxorderDetail",trxorderDetailList);
    }

    public void deleteTrxorderDetailById(Long id){
        this.delete("TrxorderDetailMapper.deleteTrxorderDetailById",id);
    }

    public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
        this.update("TrxorderDetailMapper.updateTrxorderDetail",trxorderDetail);
    }
    public void updateTrxorderDetailStatusById(Map<String,Object> map){
    	this.update("TrxorderDetailMapper.updateTrxorderDetailStatusById",map);
    }
    
    public TrxorderDetail getTrxorderDetailById(Long id) {
        return this.selectOne("TrxorderDetailMapper.getTrxorderDetailById",id);
    }

    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail) {
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailList",trxorderDetail);
    }
    /**
     * 查询该用户购买过的课程
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId){
        return this.selectList("TrxorderDetailMapper.getTrxorderDetailListBuy",userId);
    }
    
    /**
     * 更新流水状态为成功，网银回调用
     * @param trxorderDetail
     */
    public Long updateTrxorderDetailStatusSuccess(TrxorderDetail trxorderDetail){
        return this.update("TrxorderDetailMapper.updateTrxorderDetailStatusSuccess", trxorderDetail);
    }
    
    
    /**
     *根据 订单条件查询 流水分页
     *@param trxorderDetail,page
     *@return List<TrxorderDetail>
     *@Date 2014-09-28
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail,PageEntity page){
    	return this.queryForListPage("TrxorderDetailMapper.getTrxorderDetailListByCondition",trxorderDetail, page);
    	
    }
    
    @Override
    public void updateTrxorderDetailCancel(Long orderId){
    	this.update("TrxorderDetailMapper.updateTrxorderDetailCancel", orderId);
    }
    
    /**
     * 根据流水id关联用户表查询流水详情
     * @param id
     * return QueryTrxorderDetail
     * @Date 2014-09-28
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id){
    	
    	return this.selectOne("TrxorderDetailMapper.getTrxorderDetailInfoById", id);
    }
    /**
     * 修改订单详情的状态
     * @param detail
     */
	public void updateTrxorderDetailStatus(TrxorderDetail detail){
		if((detail.getRequestId()!=null&&detail.getRequestId()!="")||(detail.getId()!=null&&detail.getId()>0)){
			this.update("TrxorderDetailMapper.updateTrxorderDetailStatus", detail);
		}
	}
	/**
	 * 根据用户id和状态查询课程列表(学生)
	 */
	@Override
	public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusStu(QueryTrxorderDetailCourse detailCourse,PageEntity page) {
		return this.queryForListPage("TrxorderDetailMapper.getTrxorderDetailByStatusStu", detailCourse,page);
	}
	/**
	 * 根据用户id和状态查询课程列表(老师)
	 */
	@Override
	public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusTea(QueryTrxorderDetailCourse detailCourse,PageEntity page) {
		return this.queryForListPage("TrxorderDetailMapper.getTrxorderDetailByStatusTea", detailCourse,page);
	}
	/**
	 * 查询流水列表(定时自动确认付款任务使用) 
	 * @param detail
	 * @return
	 */
	public List<TrxorderDetail> queryTrxorderDetailByStatus(TrxorderDetail detail){
		return this.selectList("TrxorderDetailMapper.queryTrxorderDetailByStatus", detail);
	}
    
	public void updateTrxorderDetailByTrxStatusAndStatus(TrxorderDetail detail){
		this.update("TrxorderDetailMapper.updateTrxorderDetailByTrxStatusAndStatus", detail);
	}
	
	@Override
	public List<TrxorderDetail> queryMySchedulePage(QueryTrxorderDetail query,PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("TrxorderDetailMapper.queryMySchedulePage", query,page);
	}
	@Override
	public void updateStatusCancelClass(Map<String, Object> map) {
		this.update("TrxorderDetailMapper.updateStatusCancelClass", map);
	}

	@Override
	public void updateTimeStuById(Map<String, Object> map) {
		this.update("TrxorderDetailMapper.updateTimeStuById", map);
	}
	@Override
	public void updateTimeTeaById(Map<String, Object> map) {
		this.update("TrxorderDetailMapper.updateTimeTeaById", map);
	}
	@Override
	public void updateConfirmCourse(Map<String, Object> map) {
		this.update("TrxorderDetailMapper.updateConfirmCourse", map);
	}
	
	@Override
	public void updateTrxorderDetailStatusAudit(TrxorderDetail trxorderDetail){
		this.update("TrxorderDetailMapper.updateTrxorderDetailStatusAudit", trxorderDetail);
	}
	
	@Override
	public void updateTrxorderDetailStatusRefundSuccess(TrxorderDetail trxorderDetail) {
		this.update("TrxorderDetailMapper.updateTrxorderDetailStatusRefundSuccess", trxorderDetail);
	}
	@Override
	public void updateTrxorderDetailStatusRefundCancel(TrxorderDetail trxorderDetail) {
		this.update("TrxorderDetailMapper.updateTrxorderDetailStatusRefundCancel", trxorderDetail);
	}
	@Override
	public void updateTrxorderDetailStatusPay(TrxorderDetail trxorderDetail) {
		this.update("TrxorderDetailMapper.updateTrxorderDetailStatusPay", trxorderDetail);
	}
	@Override
	public List<TrxorderDetail> queryTrxorderDetailRefund(String requestId) {
		return this.selectList("TrxorderDetailMapper.queryTrxorderDetailRefund", requestId);
	}
	@Override
	public List<TrxorderDetailStatus> getTrxorderDetailCountById(Map<String,Object> map) {
		return this.selectList("TrxorderDetailMapper.getTrxorderDetailCountById", map);
	}
	@Override
	public List<Integer> getTrxorderDetaiCountByTimeAndId(Map<String, Object> map) {
		return this.selectList("TrxorderDetailMapper.getTrxorderDetaiCountByTime", map);
	}
	@Override
	public List<TrxorderDetail> queryTimeStudentConflict(
			QueryTrxorderDetail query) {
		// TODO Auto-generated method stub
		return selectList("TrxorderDetailMapper.queryTimeStudentConflict", query);
	}
	@Override
	public List<TrxorderDetail> queryTimeTeacherConflict(
			QueryTrxorderDetail query) {
		// TODO Auto-generated method stub
		return selectList("TrxorderDetailMapper.queryTimeTeacherConflict", query);
	}
	@Override
	public List<QueryTrxorderDetailCourse> getTrxorderDetaiListByTime(Map<String, Object> map) {
		return this.selectList("TrxorderDetailMapper.getTrxorderDetaiListByTime", map);
	}
	
	/**
     * 查询我的学习记录
     * @param userId
     * @return
     */
    public List<TrxorderDetailDTO> queryMyStudyHistoryByTrxorderDetail(Long userId, PageEntity page){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
    	return this.queryForListPage("TrxorderDetailMapper.queryMyStudyHistoryByTrxorderDetail", map, page);
    }
	@Override
	public int getTrxorderDetailListCount(Long trxorderId) {
		return this.selectOne("TrxorderDetailMapper.getTrxorderDetailListCount", trxorderId);
	}
	@Override
	public void updateTrxorderDetailSummaryById(TrxorderDetail trxorderDetail) {
		this.update("TrxorderDetailMapper.updateTrxorderDetailSummaryById", trxorderDetail);
	}
	@Override
	public List<TrxorderDetailDTO> queryMyStudyHistoryList(Long userId) {
		// TODO Auto-generated method stub
		return selectList("TrxorderDetailMapper.queryMyStudyHistoryList", userId);
	}
}
