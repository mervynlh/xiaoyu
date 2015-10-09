package com.yizhilu.os.edu.service.impl.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.web.LetterConstans;
import com.yizhilu.os.edu.dao.order.TrxorderOptRecordDao;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecord;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecordDTO;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.order.TrxorderOptRecordService;
import com.yizhilu.os.edu.service.order.TrxorderService;
/**
 * TrxorderOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("trxorderOptRecordService")
public class TrxorderOptRecordServiceImpl implements TrxorderOptRecordService{

 	@Autowired
    private TrxorderOptRecordDao trxorderOptRecordDao;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private TeacherService teacherService;
    /**
     * 添加TrxorderOptRecord
     * @param trxorderOptRecord 要添加的TrxorderOptRecord
     * @return id
     */
    public java.lang.Long addTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord){
    	return trxorderOptRecordDao.addTrxorderOptRecord(trxorderOptRecord);
    }

    /**
     * 根据id删除一个TrxorderOptRecord
     * @param id 要删除的id
     */
    public void deleteTrxorderOptRecordById(Long id){
    	 trxorderOptRecordDao.deleteTrxorderOptRecordById(id);
    }

    /**
     * 修改TrxorderOptRecord
     * @param trxorderOptRecord 要修改的TrxorderOptRecord
     */
    public void updateTrxorderOptRecord(TrxorderOptRecord trxorderOptRecord){
     	trxorderOptRecordDao.updateTrxorderOptRecord(trxorderOptRecord);
    }

    @Override
    public void updateTrxorderOptRecordSuccess(TrxorderOptRecord trxorderOptRecord) throws Exception{
    	// 获取订单
    	Trxorder trxorder = this.trxorderService.getTrxorderById(trxorderOptRecord.getOrderId());
    	// 订单退款
    	this.trxorderService.updateTrxorderRefund(trxorder);
    	// 修改订单状态(退款成功)
    	this.trxorderService.updateTrxorderTrxStatus(trxorderOptRecord.getOrderId(), TrxOrderStatus.REFUND.toString());
    	// 发送系统消息
    	MsgReceive msgReceive = new MsgReceive();
		String msgContent = "您的退款申请已通过,请您查收退款！";
		msgReceive.setContent(msgContent);
		msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
    	if(trxorderOptRecord.getUserType()==0){// 学生
    		// 学生用户id
    		msgReceive.setReceivingCusId(trxorder.getUserId());
    		// 学生姓名
    		msgReceive.setShowname(trxorder.getStudentName());
    	}else if(trxorderOptRecord.getUserType()==1){// 教师
    		Teacher teacher = this.teacherService.getTeacherById(trxorder.getTeacherId());
    		// 教师用户id
    		msgReceive.setReceivingCusId(teacher.getUserId());
    		// 教师姓名
    		msgReceive.setShowname(teacher.getUserExpand().getRealname());
    	}
    	msgReceiveService.addOtherMessageByCusId(msgReceive);
    	// 修改订单操作记录
    	trxorderOptRecordDao.updateTrxorderOptRecord(trxorderOptRecord);
    }
    @Override
    public void updateTrxorderOptRecordCancel(TrxorderOptRecord trxorderOptRecord) throws Exception{
    	// 获取订单
    	Trxorder trxorder = this.trxorderService.getTrxorderById(trxorderOptRecord.getOrderId());
    	// 修改订单状态(退款失败)
    	this.trxorderService.updateTrxorderTrxStatus(trxorder.getId(), TrxOrderStatus.SUCCESS.toString());
    	// 发送系统消息
    	MsgReceive msgReceive = new MsgReceive();
		String msgContent = "您的退款申请未通过,请按时上课！";
		msgReceive.setContent(msgContent);
		msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
    	if(trxorderOptRecord.getUserType()==0){// 学生
    		// 学生用户id
    		msgReceive.setReceivingCusId(trxorder.getUserId());
    		// 学生姓名
    		msgReceive.setShowname(trxorder.getStudentName());
    	}else if(trxorderOptRecord.getUserType()==1){// 教师
    		Teacher teacher = this.teacherService.getTeacherById(trxorder.getTeacherId());
    		// 教师用户id
    		msgReceive.setReceivingCusId(teacher.getUserId());
    		// 教师姓名
    		msgReceive.setShowname(teacher.getUserExpand().getRealname());
    	}
    	msgReceiveService.addOtherMessageByCusId(msgReceive);
     	// 修改订单操作记录
    	trxorderOptRecordDao.updateTrxorderOptRecord(trxorderOptRecord);
    }
    
    /**
     * 根据id获取单个TrxorderOptRecord对象
     * @param id 要查询的id
     * @return TrxorderOptRecord
     */
    public TrxorderOptRecordDTO getTrxorderOptRecordById(Long id){
    	return trxorderOptRecordDao.getTrxorderOptRecordById( id);
    }

    /**
     * 根据条件获取TrxorderOptRecord列表
     * @param trxorderOptRecord 查询条件
     * @return List<TrxorderOptRecord>
     */
    public List<TrxorderOptRecordDTO> getTrxorderOptRecordList(TrxorderOptRecordDTO trxorderOptRecord,PageEntity page){
    	return trxorderOptRecordDao.getTrxorderOptRecordList(trxorderOptRecord,page);
    }
}