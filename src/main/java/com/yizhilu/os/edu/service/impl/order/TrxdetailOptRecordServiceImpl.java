package com.yizhilu.os.edu.service.impl.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.constants.web.LetterConstans;
import com.yizhilu.os.edu.constants.web.SendMsgConstans;
import com.yizhilu.os.edu.dao.order.TrxdetailOptRecordDao;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.order.TrxdetailOptRecord;
import com.yizhilu.os.edu.entity.order.TrxdetailOptRecordDTO;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.order.TrxdetailOptRecordService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;
/**
 * TrxdetailOptRecord管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("trxdetailOptRecordService")
public class TrxdetailOptRecordServiceImpl implements TrxdetailOptRecordService{

	private static final Logger logger = LoggerFactory.getLogger(TrxdetailOptRecordServiceImpl.class);
	
 	@Autowired
    private TrxdetailOptRecordDao trxdetailOptRecordDao;
 	@Autowired
    private TrxorderDetailService trxorderDetailService;
 	@Autowired
 	private MsgReceiveService msgReceiveService;
 	@Autowired
 	private TeacherClassHourService teacherClassHourService;
 	@Autowired
 	private TeacherService teacherService;
 	@Autowired
 	private UserExpandService userExpandService;
 	@Autowired
 	private UserMobileMsgService userMobileMsgService;
    /**
     * 添加TrxdetailOptRecord
     * @param trxdetailOptRecord 要添加的TrxdetailOptRecord
     * @return id
     */
    public java.lang.Long addTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord){
    	return trxdetailOptRecordDao.addTrxdetailOptRecord(trxdetailOptRecord);
    }

    /**
     * 根据id删除一个TrxdetailOptRecord
     * @param id 要删除的id
     */
    public void deleteTrxdetailOptRecordById(Long id){
    	 trxdetailOptRecordDao.deleteTrxdetailOptRecordById(id);
    }
    /**
     * 修改TrxdetailOptRecord
     * @param trxdetailOptRecord 要修改的TrxdetailOptRecord
     */
    public void updateTrxdetailOptRecord(TrxdetailOptRecord trxdetailOptRecord){
     	trxdetailOptRecordDao.updateTrxdetailOptRecord(trxdetailOptRecord);
    }
    
    @Override
    public void updateTrxdetailOptRecordSuccess(TrxdetailOptRecord trxdetailOptRecord) throws Exception{
    	// 获取订单流水
		TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(trxdetailOptRecord.getTrxorderDetailId());
		// 修改订单流水状态(退款成功)
		trxorderDetailService.updateTrxorderDetailStatusRefundSuccess(trxorderDetail,trxdetailOptRecord.getUserType());
		// 课时+1
		trxorderDetailService.updateTrxorderLessionOverAdd(trxorderDetail.getTrxorderId());
		// 获取时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		String hour = hourFormat.format(trxorderDetail.getStartTime()) + "-" + hourFormat.format(trxorderDetail.getEndTime());
		Date date = dateFormat.parse(dateFormat.format(trxorderDetail.getStartTime()));
		// 重置课时到发布状态
		TeacherClassHour teacherClassHour = new TeacherClassHour();
		teacherClassHour.setTeacherId(trxorderDetail.getTeacherId());
		teacherClassHour.setDateDay(date);
		teacherClassHour.setTime(hour);
		teacherClassHour.setUserId(0L);
		teacherClassHour.setStatus(2);// 发布状态
		this.teacherClassHourService.updateTeacherClassHourStatus(teacherClassHour);
    	// 修改操作记录
     	trxdetailOptRecordDao.updateTrxdetailOptRecord(trxdetailOptRecord);
     	// 发送系统消息
 		UserExpandDto user = this.userExpandService.getUserExpandByUids(trxorderDetail.getUserId());
 		Teacher teacher = this.teacherService.getTeacherById(trxorderDetail.getTeacherId());
 		MsgReceive msgReceive = new MsgReceive();
 		msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
     	if(trxdetailOptRecord.getUserType()==0){// 学生
     		String msgContent = "您的退课申请已通过,请您查收退款！";
     		msgReceive.setContent(msgContent);
     		// 学生用户id
     		msgReceive.setReceivingCusId(trxorderDetail.getUserId());
     		// 学生姓名
     		msgReceive.setShowname(user.getRealname());
     	}else if(trxdetailOptRecord.getUserType()==1){// 教师
     		String msgContent = "您的退课申请已通过！";
     		msgReceive.setContent(msgContent);
     		// 教师用户id
     		msgReceive.setReceivingCusId(teacher.getUserId());
     		// 教师姓名
     		msgReceive.setShowname(teacher.getUserExpand().getRealname());
     	}
     	msgReceiveService.addOtherMessageByCusId(msgReceive);
     	//发送短信
		try {
			if(trxdetailOptRecord.getUserType()==0){
				userMobileMsgService.sendEx(SendMsgConstans.SEND_REFUND_COURSE, teacher.getUserExpand().getMobile(), user.getRealname(), trxorderDetail.getCourseName(), null,null); 
			}else if(trxdetailOptRecord.getUserType()==1){
				userMobileMsgService.sendEx(SendMsgConstans.SEND_REFUND_COURSE, user.getMobile(), teacher.getUserExpand().getRealname(), trxorderDetail.getCourseName(), null,null); 
			}
		} catch (Exception e) {
			logger.info("send updateTrxorderDetailStatusRefundSuccess error ");
		}
    }
    @Override
    public void updateTrxdetailOptRecordCancel(TrxdetailOptRecord trxdetailOptRecord) throws Exception{
    	// 获取订单流水
		TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(trxdetailOptRecord.getTrxorderDetailId());
		// 修改订单流水状态(退款失败)
		trxorderDetailService.updateTrxorderDetailStatusRefundCancel(trxorderDetail,trxdetailOptRecord.getUserType());
    	// 修改操作记录
		trxdetailOptRecordDao.updateTrxdetailOptRecord(trxdetailOptRecord);
		// 发送系统消息
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "您的退款申请未通过,请您按时上课！";
		msgReceive.setContent(msgContent);
		msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
		if(trxdetailOptRecord.getUserType()==0){// 学生
			// 学生用户id
			msgReceive.setReceivingCusId(trxorderDetail.getUserId());
			// 学生姓名
			UserExpandDto user = this.userExpandService.getUserExpandByUids(trxorderDetail.getUserId());
			msgReceive.setShowname(user.getRealname());
		}else if(trxdetailOptRecord.getUserType()==1){// 教师
			Teacher teacher = this.teacherService.getTeacherById(trxorderDetail.getTeacherId());
			// 教师用户id
			msgReceive.setReceivingCusId(teacher.getUserId());
			// 教师姓名
			msgReceive.setShowname(teacher.getUserExpand().getRealname());
		}
    }
    /**
     * 根据id获取单个TrxdetailOptRecord对象
     * @param id 要查询的id
     * @return TrxdetailOptRecord
     */
    public TrxdetailOptRecordDTO getTrxdetailOptRecordById(Long id){
    	return trxdetailOptRecordDao.getTrxdetailOptRecordById( id);
    }

    /**
     * 根据条件获取TrxdetailOptRecord列表
     * @param trxdetailOptRecord 查询条件
     * @return List<TrxdetailOptRecord>
     */
    public List<TrxdetailOptRecordDTO> getTrxdetailOptRecordList(TrxdetailOptRecord trxdetailOptRecord,PageEntity page){
    	return trxdetailOptRecordDao.getTrxdetailOptRecordList(trxdetailOptRecord,page);
    }
}