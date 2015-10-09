package com.yizhilu.os.edu.service.impl.order;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.constants.enums.AccountBizType;
import com.yizhilu.os.edu.constants.enums.AccountHistoryType;
import com.yizhilu.os.edu.constants.enums.AccountType;
import com.yizhilu.os.edu.constants.enums.CourseStatus;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.enums.TrxdetailOptRecordEnum;
import com.yizhilu.os.edu.constants.web.LetterConstans;
import com.yizhilu.os.edu.constants.web.SendMsgConstans;
import com.yizhilu.os.edu.dao.course.CourseDao;
import com.yizhilu.os.edu.dao.order.TrxorderDao;
import com.yizhilu.os.edu.dao.order.TrxorderDetailDao;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetail;
import com.yizhilu.os.edu.entity.order.QueryTrxorderDetailCourse;
import com.yizhilu.os.edu.entity.order.TrxdetailOptRecord;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderDetailDTO;
import com.yizhilu.os.edu.entity.order.TrxorderDetailStatus;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.cashOut.CashOutService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.major.MajorService;
import com.yizhilu.os.edu.service.order.TrxdetailOptRecordService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;
import com.yizhilu.os.sysuser.entity.Subject;
import com.yizhilu.os.sysuser.service.SubjectService;

/**
 * TrxorderDetail管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("trxorderDetailService")
public class TrxorderDetailServiceImpl implements TrxorderDetailService {

	private static final Logger logger = LoggerFactory.getLogger(TrxorderDetailServiceImpl.class);

	MemCache memCache = MemCache.getInstance();

	@Autowired
	private TrxorderDao trxorderDao;
	@Autowired
	private TrxorderDetailDao trxorderDetailDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CashOutService cashOutService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private TrxdetailOptRecordService trxdetailOptRecordService;
	@Autowired
	private UserMobileMsgService userMobileMsgService;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	/**
	 * 添加TrxorderDetail
	 * 
	 * @param trxorderDetail
	 *            要添加的TrxorderDetail
	 * @return id
	 */
	public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail) {
		return trxorderDetailDao.addTrxorderDetail(trxorderDetail);
	}

	/**
	 * 批量添加TrxorderDetail
	 * 
	 * @param trxorderDetail
	 *            要添加的TrxorderDetail
	 * @return id
	 */
	public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetail) {
		trxorderDetailDao.addBatchTrxorderDetail(trxorderDetail);
	}

	/**
	 * 根据id删除一个TrxorderDetail
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTrxorderDetailById(Long id) {
		trxorderDetailDao.deleteTrxorderDetailById(id);
	}

	/**
	 * 修改TrxorderDetail
	 * 
	 * @param trxorderDetail
	 *            要修改的TrxorderDetail
	 */
	public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
		trxorderDetailDao.updateTrxorderDetail(trxorderDetail);
	}
	@Override
	 public void updateTrxorderDetailStatusById(Long detailId,Long status){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("detailId",detailId);
		map.put("status",status);
		trxorderDetailDao.updateTrxorderDetailStatusById(map);
	}
	
	/**
	 * 根据id获取单个TrxorderDetail对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return TrxorderDetail
	 */
	public TrxorderDetail getTrxorderDetailById(Long id) {
		TrxorderDetail trxorderDetail = trxorderDetailDao.getTrxorderDetailById(id);
		if (ObjectUtils.isNotNull(trxorderDetail.getSubjectId()) && ObjectUtils.isNotNull(trxorderDetail.getGradeId())
				&& ObjectUtils.isNotNull(trxorderDetail.getMajorId())) {
			Subject query = new Subject();
			query.setSubjectId(trxorderDetail.getSubjectId());
			trxorderDetail.setSubjectName(subjectService.getSubjectBySubjectId(query).getSubjectName());
			query.setSubjectId(trxorderDetail.getGradeId());
			trxorderDetail.setGradeName(subjectService.getSubjectBySubjectId(query).getSubjectName());
			trxorderDetail.setMajorName(majorService.queryMajorById(trxorderDetail.getMajorId()).getName());
		}
		return trxorderDetail;
	}

	/**
	 * 根据条件获取TrxorderDetail列表
	 * 
	 * @param trxorderDetail
	 *            查询条件
	 * @return List<TrxorderDetail>
	 */
	public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail) {
		List<TrxorderDetail> orderList = trxorderDetailDao.getTrxorderDetailList(trxorderDetail);
		return orderList;
	}

	public List<TrxorderDetail> getTrxorderDetailListByTrxorderId(Long trxorderId) {
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		trxorderDetail.setTrxorderId(trxorderId);
		return this.trxorderDetailDao.getTrxorderDetailList(trxorderDetail);
	}

	/**
	 * 查询该用户购买过的课程
	 *
	 * @return List<TrxorderDetail>
	 */
	public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId) {
		return trxorderDetailDao.getTrxorderDetailListBuy(userId);
	}

	/**
	 * 后台根据 条件查询 分页
	 * 
	 * @param trxorderDetail
	 * @return List<QueryTrxorderDetail>
	 */
	public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page) {

		return trxorderDetailDao.queryTrxorderDetailByOrder(trxorderDetail, page);
	}

	@Override
	public void updateTrxorderDetailCancel(Long orderId,Long userType) throws Exception{
		// 取消订单
		Trxorder trxorder = trxorderService.getTrxorderById(orderId);
		trxorder.setTrxStatus("CANCEL");
		trxorderService.updateTrxorder(trxorder);
		// 取消订单流水
		this.trxorderDetailDao.updateTrxorderDetailCancel(orderId);
		// 发送消息
		MsgReceive msgReceive = new MsgReceive();
		UserExpandDto user = userExpandService.getUserExpandByUids(trxorder.getUserId());
		Teacher teacher = this.teacherService.getTeacherById(trxorder.getTeacherId());
		if(userType==0){
			String msgContent = "用户 "+user.getRealname()+" 已取消订单！";
			msgReceive.setContent(msgContent);
			msgReceive.setReceivingCusId(teacher.getUserId());
			msgReceive.setShowname(user.getRealname());
		}else if(userType==1){
			String msgContent = "教师 "+teacher.getUserExpand().getRealname()+" 已取消订单！";
			msgReceive.setContent(msgContent);
			msgReceive.setReceivingCusId(user.getCusId());
			msgReceive.setShowname(teacher.getUserExpand().getRealname());
		}
		msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			//userMobileMsgService.sendEx(msgContent,teacher.getUserExpand().getMobile(), null, null, null);
		} catch (Exception e) {
			logger.info("send updateTrxorderDetailCancel error");
		}
	}
	
	/**
	 * 根据流水id关联用户表查询流水详情
	 * 
	 * @param id
	 *            return QueryTrxorderDetail
	 * @Date 2014-09-28
	 */
	public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id) {
		return trxorderDetailDao.queryQueryTrxorderDetailById(id);
	}

	/**
	 * 根据用户id和状态查询课程列表(学生)
	 */
	@Override
	public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusStu(TrxorderDetail detail, PageEntity page) {
		QueryTrxorderDetailCourse detailCourse = new QueryTrxorderDetailCourse();
		detailCourse.setUserId(detail.getUserId());
		detailCourse.setStatus(detail.getStatus());
		detailCourse.setSubjectId(detail.getSubjectId());
		detailCourse.setGradeId(detail.getGradeId());
		detailCourse.setMajorId(detail.getMajorId());
		detailCourse.setStartTime(detail.getStartTime());
		detailCourse.setEndTime(detail.getEndTime());
		return this.trxorderDetailDao.getTrxorderDetailByStatusStu(detailCourse, page);
	}

	/**
	 * 根据用户id和状态查询课程列表(老师)
	 */
	@Override
	public List<QueryTrxorderDetailCourse> getTrxorderDetailByStatusTea(TrxorderDetail detail, PageEntity page) {
		QueryTrxorderDetailCourse detailCourse = new QueryTrxorderDetailCourse();
		detailCourse.setTeacherId(detail.getTeacherId());
		detailCourse.setStatus(detail.getStatus());
		detailCourse.setSubjectId(detail.getSubjectId());
		detailCourse.setGradeId(detail.getGradeId());
		detailCourse.setMajorId(detail.getMajorId());
		detailCourse.setStartTime(detail.getStartTime());
		detailCourse.setEndTime(detail.getEndTime());
		return this.trxorderDetailDao.getTrxorderDetailByStatusTea(detailCourse, page);
	}
	
	/**
	 * 查询流水列表(定时自动确认付款任务使用)
	 * 
	 * @param detail
	 * @return
	 */
	public List<TrxorderDetail> queryTrxorderDetailByStatus(TrxorderDetail detail) {
		return trxorderDetailDao.queryTrxorderDetailByStatus(detail);
	}

	/**
	 * 定时自动确认订单流水，满足条件为用户账户充值
	 * 
	 * @throws StaleObjectStateException
	 * @throws AccountException
	 */
	public void updateTrxorderAutomaticConfirm() {
		try {
			// 获得结课12小时后学生未确认的已支付的订单流水
			TrxorderDetail detail = new TrxorderDetail();
			detail.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 流水支付成功
			detail.setStatus(CourseStatus.REWARD);// 待确认课酬
			List<TrxorderDetail> detailList = queryTrxorderDetailByStatus(detail);
			if (ObjectUtils.isNotNull(detailList) && detailList.size() > 0) {
				// 用户比较的订单编号
				String compareRequestId = "";
				// 订单编号字符串集合
				List<String> requestIdList = new ArrayList<String>();
				// 遍历集合
				for (TrxorderDetail trxorderDetail : detailList) {
					cashOutService.updateteacherAccountRecharge(trxorderDetail);
					detail = getTrxorderDetailById(trxorderDetail.getId());
					// 获得乐观锁版本，是否执行更新流水信息
					if (trxorderDetail.getVersion().intValue() == detail.getVersion().intValue()) {
						detail.setStatus(CourseStatus.NOTEVALUTE); //修改为未评价
						updateTrxorderDetail(detail);
						// 课时+1
						updateTrxorderLessionOverAdd(trxorderDetail.getTrxorderId());
						logger.info("-------修改订单------请求号：" + detail.getRequestId());
					}else{
						throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_LOCK_ERROR);
					}
					// 如果当前的订单编号与之前的订单编号不同，将当前订单编号放入字符串集合中，并重新给用作比较是否相同的订单编号字符串赋值
					if (!compareRequestId.equalsIgnoreCase(trxorderDetail.getRequestId())) {
						requestIdList.add(trxorderDetail.getRequestId());
						compareRequestId = trxorderDetail.getRequestId();
					}
				}
				// 遍历订单编号字符串集合
				for (String _requestId : requestIdList) {
					// 根据订单请求号查询全部流水集合
					TrxorderDetail queryDetail = new TrxorderDetail();
					queryDetail.setRequestId(_requestId);
					List<TrxorderDetail> allDetails = getTrxorderDetailList(queryDetail);
					int num = 0; // 流水中（1.待确认时间，2待上课，3待确认课酬）的数量
					for (TrxorderDetail allDetail : allDetails) {
						if (allDetail.getStatus() != CourseStatus.NOTEVALUTE
								&& allDetail.getStatus() != CourseStatus.EVALUTE
								&& allDetail.getStatus() != CourseStatus.OVER
								&& allDetail.getTrxStatus().equalsIgnoreCase(TrxOrderStatus.SUCCESS.toString())) {
							num++; //　数量+1
						}
					}
					// 遍历流水结束，若数量为0，则更新该订单的状态为“已完成”
					if (num == 0) {
						trxorderService.updateTrxorderTrxStatus(allDetails.get(0).getTrxorderId(), TrxOrderStatus.FINISH.toString());
					}
				}
			}
		} catch (Exception e) {
			logger.error("TrxorderDetailServiceImpl.updateTrxorderAutomaticConfirm-----error", e);
		}

	}

	@Override
	public void updateTrxorderDetailByTrxStatusAndStatus(){
		TrxorderDetail detail = new TrxorderDetail();
		detail.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
		detail.setStatus(CourseStatus.CLASS);
		this.trxorderDetailDao.updateTrxorderDetailByTrxStatusAndStatus(detail);
	}
	
	@Override
	public List<TrxorderDetail> queryMySchedulePage(QueryTrxorderDetail query, PageEntity page) {
		List<TrxorderDetail> list = trxorderDetailDao.queryMySchedulePage(query, page);
		if (ObjectUtils.isNotNull(list) && list.size() > 0) {
			for (TrxorderDetail trx : list) {
				Course course = courseDao.getCourseById(trx.getCourseId());
				if (ObjectUtils.isNotNull(course)) {
					trx.setCourse(course);
				}
			}
		}
		return list;
	}

	/**
	 * 取消约课(弃用)
	 */
	@Override
	public void updateStatusCancelClass(Long id, Long teacherId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updateTime", new Date());
		this.trxorderDetailDao.updateStatusCancelClass(map);
		String content = "用户已取消约课";// TODO 发送通知，消息给老师(待完善)
		msgReceiveService.addSystemMessageByCusId(content, teacherId);
	}

	/**
	 * 学生确认约课
	 */
	@Override
	public void updateConfirmCourseStudent(Long id, Long userId,Long teacherId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updateTime", new Date());
		this.trxorderDetailDao.updateConfirmCourse(map);
		
		QueryTrxorderDetail trxorderDetail = this.queryQueryTrxorderDetailById(id);
		// 修改课表
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		String hour = hourFormat.format(trxorderDetail.getStartTime()) + "-"
				+ hourFormat.format(trxorderDetail.getEndTime());
		String classHour = dateFormat.format(trxorderDetail.getStartTime()) + " " + hour + "DAY";
		Date date = dateFormat.parse(dateFormat.format(trxorderDetail.getStartTime()));
		Map<String, Object> map1 = teacherClassHourService.checkIfSelect(classHour,
				trxorderDetail.getTeacherId());
		// 课时未发布
		if (!map1.get("msg").equals("true")) {
			this.teacherClassHourService.addTeacherClassHour(classHour, trxorderDetail.getTeacherId());
		}
		TeacherClassHour teacherClassHour = new TeacherClassHour();
		teacherClassHour.setTeacherId(trxorderDetail.getTeacherId());
		teacherClassHour.setDateDay(date);
		teacherClassHour.setTime(hour);
		teacherClassHour.setUserId(trxorderDetail.getUserId());
		teacherClassHour.setStatus(3);// 预约状态
		this.teacherClassHourService.updateTeacherClassHourStatus(teacherClassHour);
		// 发送消息
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy年MM月dd日");
		UserExpandDto user = userExpandService.getUserExpandByUids(userId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "用户 "+user.getRealname()+" 已确认约课时间为"+dateSdf.format(date)+" "+hour+"，请您按时上课！";
		msgReceive.setContent(msgContent);
		msgReceive.setReceivingCusId(teacher.getUserId());
		msgReceive.setType(LetterConstans.LETTER_TYPE_COURSE_NOTICE);
		msgReceive.setShowname(user.getRealname());
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			userMobileMsgService.sendEx(SendMsgConstans.SEND_CONFIRM_TIME,teacher.getUserExpand().getMobile(), "用户"+user.getRealname(), null, null,null);
		} catch (Exception e) {
			logger.info("send updateConfirmCourseStudent error");
		}
	}

	/**
	 * 老师确认约课
	 */
	@Override
	public void updateConfirmCourseTeacher(Long id, Long userId,Long teacherId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		map.put("id", id);
		map.put("updateTime", date);
		this.trxorderDetailDao.updateConfirmCourse(map);
		
		QueryTrxorderDetail trxorderDetail = this.queryQueryTrxorderDetailById(id);
		// 修改课表
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		String hour = hourFormat.format(trxorderDetail.getStartTime()) + "-"
				+ hourFormat.format(trxorderDetail.getEndTime());
		String classHour = dateFormat.format(trxorderDetail.getStartTime()) + " " + hour + "DAY";
		Date date1 = dateFormat.parse(dateFormat.format(trxorderDetail.getStartTime()));
		Map<String, Object> map1 = teacherClassHourService.checkIfSelect(classHour,
				trxorderDetail.getTeacherId());
		// 课时未发布
		if (!map1.get("msg").equals("true")) {
			this.teacherClassHourService.addTeacherClassHour(classHour, trxorderDetail.getTeacherId());
		}
		TeacherClassHour teacherClassHour = new TeacherClassHour();
		teacherClassHour.setTeacherId(trxorderDetail.getTeacherId());
		teacherClassHour.setDateDay(date1);
		teacherClassHour.setTime(hour);
		teacherClassHour.setUserId(userId);
		teacherClassHour.setStatus(3);// 预约状态
		this.teacherClassHourService.updateTeacherClassHourStatus(teacherClassHour);
		
		// 发送消息
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy年MM月dd日");
		UserExpandDto user = userExpandService.getUserExpandByUids(userId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "教师 "+teacher.getUserExpand().getRealname()+" 已确认约课时间为"+dateSdf.format(date)+" "+hour+"，请您按时上课！";
		msgReceive.setContent(msgContent);
		msgReceive.setReceivingCusId(userId);
		msgReceive.setType(LetterConstans.LETTER_TYPE_COURSE_NOTICE);
		msgReceive.setShowname(teacher.getUserExpand().getRealname());
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			userMobileMsgService.sendEx(SendMsgConstans.SEND_CONFIRM_TIME,user.getMobile(), "教师"+teacher.getUserExpand().getRealname(), null, null,null);
		} catch (Exception e) {
			logger.info("send updateConfirmCourseTeacher error");
		}
	}

	
	@Override
	public void updateTimeStuById(Long id, String startTime, String endTime, Long userId,Long teacherId) throws Exception {
		QueryTrxorderDetail trxorderDetail = this.queryQueryTrxorderDetailById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("updateTime", new Date());
		this.trxorderDetailDao.updateTimeStuById(map);
		
		// 重置课时到发布状态
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		String hour = hourFormat.format(trxorderDetail.getStartTime()) + "-"
				+ hourFormat.format(trxorderDetail.getEndTime());
		Date date = dateFormat.parse(dateFormat.format(trxorderDetail.getStartTime()));
		TeacherClassHour teacherClassHour = new TeacherClassHour();
		teacherClassHour.setTeacherId(trxorderDetail.getTeacherId());
		teacherClassHour.setDateDay(date);
		teacherClassHour.setTime(hour);
		teacherClassHour.setUserId(0L);
		teacherClassHour.setStatus(2);// 发布状态
		this.teacherClassHourService.updateTeacherClassHourStatus(teacherClassHour);
		
		// 发送消息
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserExpandDto user = userExpandService.getUserExpandByUids(userId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "用户 "+user.getRealname()+" 已修改上课时间为 "+dateSdf.format(dateFormat.parse(startTime))+" "+hourFormat.format(timeSdf.parse(startTime))+"-"+hourFormat.format(timeSdf.parse(endTime))+" 请您及时确认修改！";
		msgReceive.setContent(msgContent);
		msgReceive.setReceivingCusId(teacher.getUserId());
		msgReceive.setType(LetterConstans.LETTER_TYPE_COURSE_NOTICE);
		msgReceive.setShowname(user.getRealname());
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			userMobileMsgService.sendEx(SendMsgConstans.SEND_UPDATE_TIME,teacher.getUserExpand().getMobile(), "用户"+user.getRealname(), dateSdf.format(dateFormat.parse(startTime))+" "+hourFormat.format(timeSdf.parse(startTime))+"-"+hourFormat.format(timeSdf.parse(endTime)), null,null);
		} catch (Exception e) {
			logger.info("send updateTimeStuById error");
		}
	}
	@Override
	public void updateTimeTeaById(Long id, String startTime, String endTime,Long userId,Long teacherId) throws Exception {
		QueryTrxorderDetail trxorderDetail = this.queryQueryTrxorderDetailById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
 		map.put("updateTime", new Date());
		this.trxorderDetailDao.updateTimeTeaById(map);
		// 重置课时到发布状态
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
		String hour = hourFormat.format(trxorderDetail.getStartTime()) + "-"
				+ hourFormat.format(trxorderDetail.getEndTime());
		Date date = dateFormat.parse(dateFormat.format(trxorderDetail.getStartTime()));
		TeacherClassHour teacherClassHour = new TeacherClassHour();
		teacherClassHour.setTeacherId(trxorderDetail.getTeacherId());
		teacherClassHour.setDateDay(date);
		teacherClassHour.setTime(hour);
		teacherClassHour.setUserId(0L);
		teacherClassHour.setStatus(2);// 发布状态
		this.teacherClassHourService.updateTeacherClassHourStatus(teacherClassHour);
		
		// 发送消息
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserExpandDto user = userExpandService.getUserExpandByUids(userId);
		Teacher teacher = teacherService.getTeacherById(teacherId);
		MsgReceive msgReceive = new MsgReceive();
		
		String msgContent = "教师 "+teacher.getUserExpand().getRealname()+" 已修改约课时间为 "+dateSdf.format(dateFormat.parse(startTime))+" "+hourFormat.format(timeSdf.parse(startTime))+"-"+hourFormat.format(timeSdf.parse(endTime))+"  请您及时确认修改 !";
		msgReceive.setContent(msgContent);
		msgReceive.setReceivingCusId(userId);
		msgReceive.setType(LetterConstans.LETTER_TYPE_COURSE_NOTICE);
		msgReceive.setShowname(teacher.getUserExpand().getRealname());
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			userMobileMsgService.sendEx(SendMsgConstans.SEND_UPDATE_TIME,user.getMobile(), "教师"+teacher.getUserExpand().getRealname(), dateSdf.format(dateFormat.parse(startTime))+" "+hourFormat.format(timeSdf.parse(startTime))+"-"+hourFormat.format(timeSdf.parse(endTime)), null,null);
		} catch (Exception e) {
			logger.info("send updateTimeTeaById error");
		}
	}
	
	public void updateTrxorderDetailStatusRefund(TrxorderDetail trxorderDetail){
		this.trxorderDetailDao.updateTrxorderDetailStatus(trxorderDetail);
	}
	
	/**
	 * 课时退款审核
	 */
	@Override
	public void updateTrxorderDetailStatusAudit(TrxorderDetail trxorderDetail,String description,Long userType){
		// 修改流水状态
		trxorderDetail.setTrxStatus(TrxOrderStatus.AUDIT.toString()); // 审核
		Date date = new Date();
		trxorderDetail.setLastUpdateTime(date);
		trxorderDetail.setRefundTime(date);
		trxorderDetailDao.updateTrxorderDetailStatusAudit(trxorderDetail);
		// 添加退课操作记录
		UserExpandDto user = this.userExpandService.getUserExpandByUids(trxorderDetail.getUserId());
		Teacher teacher = this.teacherService.getTeacherById(trxorderDetail.getTeacherId());
		TrxdetailOptRecord trxdetailOptRecord = new TrxdetailOptRecord();
		trxdetailOptRecord.setCreateTime(new Date());
		trxdetailOptRecord.setTrxorderDetailId(trxorderDetail.getId());
		trxdetailOptRecord.setTrxorderId(trxorderDetail.getTrxorderId());
		trxdetailOptRecord.setOptuser(0L);
		trxdetailOptRecord.setOptusername("");
		trxdetailOptRecord.setUserType(userType);
		if(userType.intValue()==0){// 学生
			trxdetailOptRecord.setApplicant(user.getRealname());
		}else if(userType.intValue()==1){// 教师
			trxdetailOptRecord.setApplicant(teacher.getUserExpand().getRealname());
		}
		trxdetailOptRecord.setType(TrxdetailOptRecordEnum.COURSEAUDIT.toString());
		trxdetailOptRecord.setDesc(description);
		this.trxdetailOptRecordService.addTrxdetailOptRecord(trxdetailOptRecord);
		
		// 发送消息
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "您的退课申请已经提交，请等待后台管理员审核！";
		msgReceive.setContent(msgContent);
		if(userType==0){
			msgReceive.setReceivingCusId(trxorderDetail.getUserId());
		}else if(userType==1){
			msgReceive.setReceivingCusId(teacher.getUserId());
		}
		msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
		msgReceive.setShowname("系统管理员");
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			// userMobileMsgService.sendEx(msgContent, user.getMobile(), null, null, null);
		} catch (Exception e) {
			logger.info("send updateTrxorderDetailStatusAudit error");
		}
	}
	
	/**
	 * 课时退款通过
	 * 
	 * @throws AccountException
	 */
	@Override
	public void updateTrxorderDetailStatusRefundSuccess(TrxorderDetail trxorderDetail,Long userType)
			throws AccountException, StaleObjectStateException {
		TrxorderDetail detail = getTrxorderDetailById(trxorderDetail.getId());
		BigDecimal money = new BigDecimal(0);
		if (trxorderDetail.getVersion().intValue() == detail.getVersion().intValue()) {
			// 退款金额
			money = money.add(detail.getCurrentPrice());
			// 修改流水状态
			detail.setTrxStatus(TrxOrderStatus.REFUND.toString()); // 退款
			detail.setLastUpdateTime(new Date());
			logger.info("-------订单退款------请求号：" + detail.getRequestId());
			trxorderDetailDao.updateTrxorderDetailStatusRefundSuccess(detail);
			logger.info("----------------订单退款金额：", money);
			// 获得用户账号
			UserAccount account = userAccountService.getUserAccountByUserId(detail.getUserId());
			logger.info("----------------用户账号信息：", account.getId());
			logger.info("----------------用户账号余额----------：", account.getBalance());
			userAccountService.credit(account, money.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH,
					AccountHistoryType.REFUND, detail.getUserId(), detail.getTrxorderId(), detail.getRequestId(), "",
					new Date(), "", true, AccountBizType.COURSE);// 课程订单
			
			// 判断是否是订单中唯一流水
			int count = this.getTrxorderDetailListCount(detail.getTrxorderId());
			if(count<=0){
				this.trxorderService.updateTrxorderTrxStatus(detail.getTrxorderId(),TrxOrderStatus.REFUND.toString());
			}
		}
	}

	/**
	 * 课时退款取消
	 */
	@Override
	public void updateTrxorderDetailStatusRefundCancel(TrxorderDetail trxorderDetail,Long userType){
		// 修改流水状态
		trxorderDetail.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
		trxorderDetail.setLastUpdateTime(new Date());
		trxorderDetailDao.updateTrxorderDetailStatusRefundCancel(trxorderDetail);
	}
	
	/**
	 * 课时付款
	 */
	@Override
	public void updateTrxorderDetailStatusPay(TrxorderDetail trxorderDetail)
			throws AccountException, StaleObjectStateException{
		TrxorderDetail detail = getTrxorderDetailById(trxorderDetail.getId());
		Teacher teacher = this.teacherService.getTeacherById(detail.getTeacherId());
		BigDecimal money = new BigDecimal(0);
		if (trxorderDetail.getVersion().intValue() == detail.getVersion().intValue()) {
			// 支付金额
			money = money.add(detail.getCurrentPrice());
			// 修改流水状态
			detail.setTrxStatus(TrxOrderStatus.FINISH.toString()); // 支付成功
			detail.setLastUpdateTime(new Date());
			logger.info("-------订单付款------请求号：" + detail.getRequestId());
			trxorderDetailDao.updateTrxorderDetailStatusPay(detail);
			logger.info("----------------订单支付金额：", money);
			// 获得用户账号
			UserAccount account = userAccountService.getUserAccountByUserId(teacher.getUserId());
			logger.info("----------------用户账号信息：", account.getId());
			logger.info("----------------用户账号余额----------：", account.getBalance());
			userAccountService.credit(account, money.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH,
					AccountHistoryType.SALES, teacher.getUserId(), detail.getTrxorderId(), detail.getRequestId(), "",
					new Date(), "", true, AccountBizType.COURSE);// 课程订单
			// 判断是否是订单中唯一流水
			int count = this.getTrxorderDetailListCount(detail.getTrxorderId());
			if(count<=0){
				this.trxorderService.updateTrxorderTrxStatus(detail.getTrxorderId(),TrxOrderStatus.FINISH.toString());
			}
			// 课时+1
			updateTrxorderLessionOverAdd(trxorderDetail.getTrxorderId());
			// 发送消息
			UserExpandDto user = userExpandService.getUserExpandByUids(detail.getUserId());
			MsgReceive msgReceive = new MsgReceive();
			String msgContent = "用户 "+user.getRealname()+" 已向您支付 "+detail.getCourseName()+" 课时的费用,请您及时查收！";
			msgReceive.setContent(msgContent);
			msgReceive.setReceivingCusId(detail.getTeacherId());
			msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
			msgReceive.setShowname(user.getRealname());
			try {
				//发送消息
				msgReceiveService.addOtherMessageByCusId(msgReceive);
				//发送短信
				userMobileMsgService.sendEx(SendMsgConstans.SEND_CONFIRM_COURSE,teacher.getUserExpand().getMobile(), "用户"+user.getRealname(), detail.getCourseName(), null,null);
			} catch (Exception e) {
				logger.info("send updateTrxorderDetailStatusPay error ");
			}
		}
	}
	
	@Override
	public void updateTrxorderLessionOverAdd(Long orderId){
		trxorderDao.updateTrxorderLessionOverAdd(orderId);
	}

	/**
	 * 订单下所有流水
	 */
	public List<TrxorderDetail> queryTrxorderDetailRefund(String requestId) {
		return trxorderDetailDao.queryTrxorderDetailRefund(requestId);
	}

	@Override
	public Map<String, Object> getTrxorderDetailCountById(Long userId, Long teacherId) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		m.put("teacherId", teacherId);
		List<TrxorderDetailStatus> list = this.trxorderDetailDao.getTrxorderDetailCountById(m);
		Map<String, Object> map = new HashMap<String, Object>();
		if (ObjectUtils.isNull(list)) {
			map.put("TIME", 0);// 待确认时间的课
			map.put("CLASS", 0);// 将要上的课
			map.put("REWARD", 0);// 待确认课酬
			map.put("NOTEVALUTE", 0);// 未评价
			map.put("EVALUTE", 0);// 已评价
			map.put("OVER", 0);// 已上的课
		} else {
			for (int i = 0; i < list.size(); i++) {
				map.put(list.get(i).getStatus(), list.get(i).getCount());
			}
			map.put("TIME", map.containsKey(CourseStatus.TIME.toString()) ? map.get(CourseStatus.TIME.toString()) : 0);// 待确认时间的课
			map.put("CLASS", map.containsKey(CourseStatus.CLASS.toString()) ? map.get(CourseStatus.CLASS.toString()) : 0);// 将要上的课
			map.put("REWARD", map.containsKey(CourseStatus.REWARD.toString()) ? map.get(CourseStatus.REWARD.toString()) : 0);// 待确认课酬
			map.put("NOTEVALUTE", map.containsKey(CourseStatus.NOTEVALUTE.toString()) ? map.get(CourseStatus.NOTEVALUTE.toString()) : 0);// 未评价
			map.put("EVALUTE", map.containsKey(CourseStatus.EVALUTE.toString()) ? map.get(CourseStatus.EVALUTE.toString()) : 0);// 已评价
			map.put("OVER", map.containsKey(CourseStatus.OVER.toString()) ? map.get(CourseStatus.OVER.toString()) : 0);// 已上的课
		}
		return map;
	}

	@Override
	public Set<Integer> getTrxorderDetailCountByTimeAndId(Long userId, Long teacherId, Date startTime, Date endTime,
			Long status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("teacherId", teacherId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("status", status);
		List<Integer> list = this.trxorderDetailDao.getTrxorderDetaiCountByTimeAndId(map);
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < list.size(); i++) {
			set.add(list.get(i));
		}
		return set;
	}

	@Override
	public List<TrxorderDetail> queryTimeStudentConflict(QueryTrxorderDetail query) {
		// TODO Auto-generated method stub
		return trxorderDetailDao.queryTimeStudentConflict(query);
	}

	@Override
	public List<TrxorderDetail> queryTimeTeacherConflict(QueryTrxorderDetail query) {
		// TODO Auto-generated method stub
		return trxorderDetailDao.queryTimeTeacherConflict(query);
	}

	/**
	 * 查询我的学习记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<List<TrxorderDetailDTO>> queryMyStudyHistoryByTrxorderDetail(Long userId, PageEntity page) {
		List<List<TrxorderDetailDTO>> resultList = new ArrayList<List<TrxorderDetailDTO>>();
		// 获得查询结果
		List<TrxorderDetailDTO> studyHistoryList = trxorderDetailDao.queryMyStudyHistoryByTrxorderDetail(userId, page);
		if (ObjectUtils.isNotNull(studyHistoryList) && studyHistoryList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf_0 = new SimpleDateFormat("HH:mm");
			// 获得第一条数据的年份
			String year = sdf.format(studyHistoryList.get(0).getLastUpdateTime());
			// 新的里层List集合(同一年的数据)
			List<TrxorderDetailDTO> trxorder = new ArrayList<TrxorderDetailDTO>();
			// 遍历结果集合
			for (TrxorderDetailDTO detailDTO : studyHistoryList) {
				// 设置年份
				detailDTO.setYear(sdf.format(detailDTO.getLastUpdateTime()));
				// 设置学习时间段： 07:00-08:00
				detailDTO.setStudyTime(
						sdf_0.format(detailDTO.getStartTime()) + "-" + sdf_0.format(detailDTO.getEndTime()));
				if (!detailDTO.getYear().equals(year)) {
					resultList.add(trxorder);
					trxorder = new ArrayList<TrxorderDetailDTO>();
					year = detailDTO.getYear();
					trxorder.add(detailDTO);
				} else {
					trxorder.add(detailDTO);
				}
			}
			resultList.add(trxorder);
		}
		return resultList;
	}
	@Override
	public List<List<TrxorderDetailDTO>> queryMyStudyHistoryList(Long userId) {
		// TODO Auto-generated method stub
		List<List<TrxorderDetailDTO>> resultList = new ArrayList<List<TrxorderDetailDTO>>();
		// 获得查询结果
		List<TrxorderDetailDTO> studyHistoryList = trxorderDetailDao.queryMyStudyHistoryList(userId);
		if (ObjectUtils.isNotNull(studyHistoryList) && studyHistoryList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf_0 = new SimpleDateFormat("HH:mm");
			// 获得第一条数据的年份
			String year = sdf.format(studyHistoryList.get(0).getLastUpdateTime());
			// 新的里层List集合(同一年的数据)
			List<TrxorderDetailDTO> trxorder = new ArrayList<TrxorderDetailDTO>();
			// 遍历结果集合
			for (TrxorderDetailDTO detailDTO : studyHistoryList) {
				// 设置年份
				detailDTO.setYear(sdf.format(detailDTO.getLastUpdateTime()));
				// 设置学习时间段： 07:00-08:00
				detailDTO.setStudyTime(
						sdf_0.format(detailDTO.getStartTime()) + "-" + sdf_0.format(detailDTO.getEndTime()));
				if (!detailDTO.getYear().equals(year)) {
					resultList.add(trxorder);
					trxorder = new ArrayList<TrxorderDetailDTO>();
					year = detailDTO.getYear();
					trxorder.add(detailDTO);
				} else {
					trxorder.add(detailDTO);
				}
			}
			resultList.add(trxorder);
		}
		return resultList;
	}

	@Override
	public int getTrxorderDetailListCount(Long trxorderId) {
		return this.trxorderDetailDao.getTrxorderDetailListCount(trxorderId);
	}

	@Override
	public void updateTrxorderDetailSummaryById(TrxorderDetail trxorderDetail) {
		this.trxorderDetailDao.updateTrxorderDetailSummaryById(trxorderDetail);
	}
}