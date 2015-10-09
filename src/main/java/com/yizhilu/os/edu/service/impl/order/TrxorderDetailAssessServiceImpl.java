package com.yizhilu.os.edu.service.impl.order;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.constants.enums.CourseStatus;
import com.yizhilu.os.edu.constants.enums.TrxOrderDetailConfirmStatus;
import com.yizhilu.os.edu.dao.order.TrxorderDetailAssessDao;
import com.yizhilu.os.edu.entity.order.TrxorderDetailAssess;
import com.yizhilu.os.edu.entity.order.TrxorderDetailStatus;
import com.yizhilu.os.edu.service.order.TrxorderDetailAssessService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;

@Service("trxorderDetailAssessService")
public class TrxorderDetailAssessServiceImpl implements TrxorderDetailAssessService {

	@Autowired
	private  TrxorderDetailAssessDao  trxorderDetailAssessDao;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Override
	public void addDetailAssess(Long detailId, String content, Long teacherId,Long userId) {
		TrxorderDetailAssess detailAssess = new TrxorderDetailAssess();
		detailAssess.setDetailId(detailId);
		detailAssess.setContent(content);
		detailAssess.setTeacherId(teacherId);
		detailAssess.setUserId(userId);;
		detailAssess.setCreateTime(new Date());
		this.trxorderDetailAssessDao.addDetailAssess(detailAssess);
		this.trxorderDetailService.updateTrxorderDetailStatusById(detailId, CourseStatus.EVALUTE);
	}
	@Override
	public TrxorderDetailAssess getDetailAssessInfoByDetailId(Long detailId) {
		return this.trxorderDetailAssessDao.getDetailAssessInfoByDetailId(detailId);
	}
}
