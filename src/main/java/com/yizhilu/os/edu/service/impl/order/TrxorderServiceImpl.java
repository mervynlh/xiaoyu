package com.yizhilu.os.edu.service.impl.order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.exception.AccountException;
import com.yizhilu.os.common.exception.StaleObjectStateException;
import com.yizhilu.os.core.common.exception.BaseException;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.DateUtils;
import com.yizhilu.os.core.util.EnumUtil;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.constants.enums.AccountBizType;
import com.yizhilu.os.edu.constants.enums.AccountHistoryType;
import com.yizhilu.os.edu.constants.enums.AccountStatus;
import com.yizhilu.os.edu.constants.enums.AccountType;
import com.yizhilu.os.edu.constants.enums.AuthStatus;
import com.yizhilu.os.edu.constants.enums.CourseProfileType;
import com.yizhilu.os.edu.constants.enums.CourseStatus;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.TrxOrderDetailConfirmStatus;
import com.yizhilu.os.edu.constants.enums.TrxOrderOptRecordEnum;
import com.yizhilu.os.edu.constants.enums.TrxOrderStatus;
import com.yizhilu.os.edu.constants.web.LetterConstans;
import com.yizhilu.os.edu.constants.web.OrderConstans;
import com.yizhilu.os.edu.dao.course.CourseProfileDao;
import com.yizhilu.os.edu.dao.order.TrxorderDao;
import com.yizhilu.os.edu.dao.order.TrxorderDetailDao;
import com.yizhilu.os.edu.entity.coupon.CouponCode;
import com.yizhilu.os.edu.entity.coupon.CouponCodeDTO;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.course.CourseKpoints;
import com.yizhilu.os.edu.entity.course.CourseMinus;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.letter.MsgReceive;
import com.yizhilu.os.edu.entity.order.QueryTrxorder;
import com.yizhilu.os.edu.entity.order.TrxOrderDTO;
import com.yizhilu.os.edu.entity.order.TrxReqData;
import com.yizhilu.os.edu.entity.order.Trxorder;
import com.yizhilu.os.edu.entity.order.TrxorderDetail;
import com.yizhilu.os.edu.entity.order.TrxorderExpand;
import com.yizhilu.os.edu.entity.order.TrxorderOptRecord;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.service.coupon.CouponCodeService;
import com.yizhilu.os.edu.service.coupon.CouponService;
import com.yizhilu.os.edu.service.course.CourseKpointsService;
import com.yizhilu.os.edu.service.course.CourseMinusService;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.letter.MsgReceiveService;
import com.yizhilu.os.edu.service.order.TrxorderDetailService;
import com.yizhilu.os.edu.service.order.TrxorderOptRecordService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
import com.yizhilu.os.edu.service.teacher.TeacherProfileService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserMobileMsgService;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName com.yizhilu.os.edu.service.impl.order.TrxorderServiceImpl
 * @description
 * @author : xiaokun
 * @Create Date : 2014-6-23 上午10:40:33
 */
@Service("trxorderService")
public class TrxorderServiceImpl implements TrxorderService {

	private static Logger logger = LoggerFactory.getLogger(TrxorderServiceImpl.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@Autowired
	private TrxorderDao trxorderDao;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private TrxorderOptRecordService trxorderOptRecordService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private TrxorderDetailDao trxorderDetailDao;
	@Autowired
	private CouponCodeService couponCodeService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private CourseProfileDao courseProfileDao;
	@Autowired
	private CourseMinusService courseMinusService;
	@Autowired
	private TeacherClassHourService teacherClassHourService;
	@Autowired
	private TeacherProfileService teacherProfileService;
	@Autowired
	private CourseKpointsService courseKpointsService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserMobileMsgService userMobileMsgService;
	
	MemCache memCache = MemCache.getInstance();
	Gson gson = new Gson();
	
	
	@Getter@Setter
	private Map<String,Object> userMesg= new HashMap<String,Object>();
	/**
	 * 添加Trxorder
	 * 
	 * @param trxorder
	 *            要添加的Trxorder
	 * @return id
	 */
	@SuppressWarnings("deprecation")
	public Map<String, Object> addTrxorder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		// 检查参数
		TrxReqData trxReqData = checkorderparam(sourceMap);
		if (trxReqData == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		String dateValue = trxReqData.getDateValue();
		//判断是否可以预约
		Map<String,Object> selectMap  = teacherClassHourService.checkIfSelect(dateValue,trxReqData.getTeacherId());
		if(!selectMap.get("msg").equals("true")){
			return selectMap;
		}
		// 拼装订单数据
		Trxorder trxorder = new Trxorder();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(trxReqData.getUserId());
		trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
		trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
		trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setDescription("无");
		trxorder.setStudentName(trxReqData.getStudentName());//学生姓名
		trxorder.setMobile(trxReqData.getMobile());//手机
		trxorder.setTeacherId(trxReqData.getTeacherId());//教师id
		trxorder.setCourseId(trxReqData.getCourseId());//课程id
		trxorder.setRemarks(trxReqData.getRemarks());//备注
		trxorder.setCourseModel(trxReqData.getCourseModel());//上课方式
		trxorder.setLessionNum(trxReqData.getLessionNum());//总课时
		trxorder.setLoseTime(new Date());//订单过期时间，需要再次修改！！！
		trxorder.setStudyAddress(trxReqData.getStudyAddress());//上课地址
		trxorder.setLessionOver(0L);//完成课时
		//获取课程
		Course cou = courseService.getCourseById(trxReqData.getCourseId());
		trxorder.setCourseName(cou.getName());//课程名称
		Map<String, String> map = gson.fromJson(cou.getCourseModel(),new TypeToken<Map<String, String>>(){}.getType());
		//订单原始总价格
    	BigDecimal orderAcount = new BigDecimal(map.get(trxReqData.getCourseModel())).multiply(new BigDecimal(trxReqData.getLessionNum()));
		trxorder.setOrderAmount(orderAcount); // 原始金额
    	//折扣优惠的价格
    	BigDecimal minusAcount =  new BigDecimal(0l);
    	//课时折扣后的价格
    	BigDecimal orderAfterMinusAcount = orderAcount;
    	CourseMinus courseMinus = new CourseMinus();
    	courseMinus.setTeacherId(trxReqData.getTeacherId());
    	courseMinus.setMinusNum(trxReqData.getLessionNum().intValue());
    	//课时折扣
    	List<CourseMinus> minusList = courseMinusService.queryCourseMinusConditionForOrder(courseMinus);
    	if(minusList!=null&&minusList.size()>0){
    		CourseMinus minus = minusList.get(0);
    		BigDecimal minusBig = new BigDecimal(minus.getDiscount()*0.1);
    		//折扣后金额
    		orderAfterMinusAcount = orderAcount.multiply(minusBig).setScale(2, RoundingMode.HALF_UP);
    		//折扣优惠金额
    		minusAcount = orderAcount.subtract(orderAfterMinusAcount);
    		//折扣优惠
    		trxorder.setMinusAmount(minusAcount);
    	}
		// 是否使用优惠券，判断优惠券是否在规则内
		// 优惠金额
		BigDecimal couponAmount = new BigDecimal(0);
		// 查询优惠券编码
		if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "") {// 使用优惠券
			// 查询优惠券编码
			CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(sourceMap.get("couponcode"));
			// 验证优惠券编码
			if(couponCodeDTO!=null){//
				if(couponCodeDTO.getUserId().longValue()==trxReqData.getUserId().longValue()){//判断是否是用户的优惠券
					int compareResult = couponCodeDTO.getLimitAmount().compareTo(orderAfterMinusAcount);
					if(compareResult<=0){//，-1表示小于，0是等于，1是大于。
						if (couponCodeDTO.getType() == 1) {// 折扣券
							couponAmount = orderAfterMinusAcount.multiply(new BigDecimal(0.1).multiply(couponCodeDTO.getAmount()));
							trxorder.setCouponAmount(couponAmount);//优惠券优惠金额
							trxorder.setCouponCodeId(couponCodeDTO.getId());//优惠券编码id
						} else {// 定额券
							couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
							// 实际需要支付的金额,四舍五去取2位
							orderAfterMinusAcount = orderAfterMinusAcount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
							trxorder.setCouponAmount(couponAmount);//优惠券优惠金额
							trxorder.setCouponCodeId(couponCodeDTO.getId());//优惠券编码id
						}
					}
				}
			}
				
		}
		trxorder.setAmount(orderAfterMinusAcount);// 实际支付金额
		// 添加订单
		trxorderDao.addTrxorder(trxorder);
		//添加流水，课时detail
		addTrxorderDetail(trxReqData, trxorder, cou);
		result.put("order", trxorder);
		return result;
	}
	@Override
	public Map<String, Object> addClassTrxorder(Map<String, String> sourceMap)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		// 检查参数
		TrxReqData trxReqData = checkorderparam(sourceMap);
		if (trxReqData == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		//检查课程信息系
		Map<String,Object> selectMap  = checkCourse(Long.parseLong(sourceMap.get("courseId")));
		if(!selectMap.get("msg").equals("ok")){
			return selectMap;
		}
		// 拼装订单数据
		Trxorder trxorder = new Trxorder();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(trxReqData.getUserId());
		trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
		trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
		trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setDescription("无");
		trxorder.setStudentName(trxReqData.getStudentName());//学生姓名
		trxorder.setMobile(trxReqData.getMobile());//手机
		trxorder.setTeacherId(trxReqData.getTeacherId());//教师id
		trxorder.setCourseId(trxReqData.getCourseId());//课程id
		trxorder.setRemarks(trxReqData.getRemarks());//备注
		trxorder.setCourseModel(trxReqData.getCourseModel());//上课方式
		trxorder.setLessionNum(trxReqData.getLessionNum());//总课时
		trxorder.setLoseTime(new Date());//订单过期时间，需要再次修改！！！
		trxorder.setStudyAddress(trxReqData.getStudyAddress());//上课地址
		trxorder.setLessionOver(0L);//完成课时
		//获取课程
		Course cou = courseService.getCourseById(trxReqData.getCourseId());
		trxorder.setCourseName(cou.getName());//课程名称
		
		int openStatu=courseKpointsService.queryOpenStatu(trxReqData.getCourseId());
		BigDecimal orderAcount=new BigDecimal(0l);
		//获取订单价格
		if(openStatu==0){
			//订单插班总价格
			orderAcount =cou.getJoinPrise();
		}else{
			//订单原始总价格
			orderAcount=cou.getCurrentprice();
		}
		trxorder.setOrderAmount(orderAcount); // 原始金额
    	//课时折扣后的价格
    	BigDecimal orderAfterMinusAcount = orderAcount;
		// 优惠金额
		BigDecimal couponAmount = new BigDecimal(0);
		// 查询优惠券编码
		if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "") {// 使用优惠券
			// 查询优惠券编码
			CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(sourceMap.get("couponcode"));
			// 验证优惠券编码
			if(couponCodeDTO!=null){//
				if(couponCodeDTO.getUserId().longValue()==trxReqData.getUserId().longValue()){//判断是否是用户的优惠券
					int compareResult = couponCodeDTO.getLimitAmount().compareTo(orderAfterMinusAcount);
					if(compareResult<=0){//，-1表示小于，0是等于，1是大于。
						if (couponCodeDTO.getType() == 1) {// 折扣券
							couponAmount = orderAfterMinusAcount.multiply(new BigDecimal(0.1).multiply(couponCodeDTO.getAmount()));
							trxorder.setCouponAmount(couponAmount);//优惠券优惠金额
							trxorder.setCouponCodeId(couponCodeDTO.getId());//优惠券编码id
						} else {// 定额券
							couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
							// 实际需要支付的金额,四舍五去取2位
							orderAfterMinusAcount = orderAfterMinusAcount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
							trxorder.setCouponAmount(couponAmount);//优惠券优惠金额
							trxorder.setCouponCodeId(couponCodeDTO.getId());//优惠券编码id
						}
					}
				}
			}
				
		}
		trxorder.setAmount(orderAfterMinusAcount);// 实际支付金额
		// 添加订单
		trxorderDao.addTrxorder(trxorder);
		//添加流水，课时detail
		addTrxorderDetail(trxReqData, trxorder, cou);
		result.put("order", trxorder);
		return result;
	}
	//检查课程信息
	private Map<String,Object> checkCourse(Long courseId){
		Map<String,Object> returnMap=new HashMap<>();
		
		Course course= courseService.getCourseById(courseId);
		//检查班课是否审核
		if(course.getIsavaliable().intValue()!=1||course.getStatu()==1){
			returnMap.put("msg", "courseIsNull");
			return returnMap;
		}		
		//检查班课是否完成
		if(!course.getIsFinsh().equals("SUREJOIN")){
			returnMap.put("msg", "courseIsNoJoin");
			return returnMap;
		}
		//检查班课是否有课节
		List<CourseKpoints> kpoints=courseKpointsService.queryKpointsByCourseId(courseId);
		if(ObjectUtils.isNull(kpoints)){
			returnMap.put("msg", "kpointIsNull");
			return returnMap;
		}
		//检查班课是否可以插班
		int openStatu=courseKpointsService.queryOpenStatu(courseId);
		if(openStatu==0){
			if(course.getIsJoinClass().equals("CANNOT")){
				returnMap.put("msg", "canNotJoin");
				return returnMap;
			}
		}
		//查询班课人数是否已满
		if(course.getPeopleNum()<=course.getJoinNum()){
			returnMap.put("msg", "numIsOk");
			return returnMap;
		}
		returnMap.put("msg", "ok");
		return returnMap;
	}
	
	/**
	 * 添加订单详细
	 * @throws ParseException 
	 */
	private void addTrxorderDetail(TrxReqData trxReqData,Trxorder trxorder,Course cou) throws ParseException{
		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		//添加流水，课时detail
		//一对一
		if(cou.getSellType().equals("1")){
			List<TrxorderDetail> timeList = getClassTime(trxReqData.getDateValue());
			BigDecimal onePrice = new BigDecimal(0);
			BigDecimal lastPrice = new BigDecimal(0);
			BigDecimal lessionNum = new BigDecimal(trxorder.getLessionNum());
			if(timeList.size()>1){
				//每个课时的价格
				onePrice = trxorder.getAmount().divide(lessionNum,2,BigDecimal.ROUND_HALF_EVEN);
				//最后一个课时价格
				lastPrice = trxorder.getAmount().subtract(onePrice.multiply(lessionNum.subtract(new BigDecimal(1))));
			}else{
				//只有一个时就是该课时的价格
				onePrice = trxorder.getAmount();
			}
			
			for (int i = 0; i < timeList.size(); i++) {
				// 创建流水
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				// 用户id
				trxorderDetail.setUserId(trxReqData.getUserId());
				// 课程id
				trxorderDetail.setCourseId(cou.getId());
				// 订单id
				trxorderDetail.setTrxorderId(trxorder.getId());
				// 订单类型
				trxorderDetail.setTrxStatus(TrxOrderStatus.INIT.toString());// 流水支付状态
				// 下单时间
				trxorderDetail.setCreateTime(new Date());
				// 课时原始价格
				trxorderDetail.setSourcePrice(cou.getCurrentprice());
				if(timeList.size()>1&&timeList.size()-i==1){
					// 课时实际支付价格
					trxorderDetail.setCurrentPrice(lastPrice);
				}else{
					// 课时实际支付价格
					trxorderDetail.setCurrentPrice(onePrice);
				}
				// 课时名
				trxorderDetail.setCourseName(cou.getName()+"第"+(i+1)+"课");
				// 课程状态
				trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
				// 订单请求号
				trxorderDetail.setRequestId(trxorder.getRequestId());
				trxorderDetail.setLastUpdateTime(new Date());
				trxorderDetail.setDescription("");
				trxorderDetail.setStartTime(timeList.get(i).getStartTime());//课程开始时间
				trxorderDetail.setEndTime(timeList.get(i).getEndTime());//课程结束时间
				Calendar c = new GregorianCalendar();
				c.setTime(timeList.get(i).getEndTime());
	        	c.add(Calendar.HOUR, 12);//+12小时
	        	Date date = c.getTime();
				trxorderDetail.setAuthTime(date);//上课后的流水过期时间 默认结束时间+12小时
				trxorderDetail.setCurrentLession(Long.valueOf(i+1));//当前课时
				trxorderDetail.setStatus(CourseStatus.CLASS);//课时状态
				trxorderDetail.setIfConfirm(TrxOrderDetailConfirmStatus.ALREADYCONFIRM.toString());//已确认
				trxorderDetail.setCourseType(1l);//班课类型1对1
				
				trxorderDetail.setTeacherId(trxorder.getTeacherId());//教师id

				trxorderDetailList.add(trxorderDetail);
			}
		}else if(cou.getSellType().equals("2")){//班课
			List<CourseKpoints> kpoints=courseKpointsService.queryKpointsByCourseId(cou.getId());
			if(ObjectUtils.isNotNull(kpoints)){
				for(CourseKpoints kpoint:kpoints){
					// 创建流水
					TrxorderDetail trxorderDetail = new TrxorderDetail();
					// 用户id
					trxorderDetail.setUserId(trxReqData.getUserId());
					// 课程id
					trxorderDetail.setCourseId(cou.getId());
					// 订单id
					trxorderDetail.setTrxorderId(trxorder.getId());
					// 订单类型
					trxorderDetail.setTrxStatus(TrxOrderStatus.INIT.toString());// 流水支付状态
					// 下单时间
					trxorderDetail.setCreateTime(new Date());
					trxorderDetail.setTeacherId(cou.getTeacherId());
					// 课程实际支付价格
					trxorderDetail.setCurrentPrice(cou.getCurrentprice());
					trxorderDetail.setCourseName(cou.getName());
					// 课程状态
					trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
					// 订单请求号
					trxorderDetail.setRequestId(trxorder.getRequestId());
					trxorderDetail.setSourcePrice(trxorder.getAmount());
					trxorderDetail.setLastUpdateTime(new Date());
					trxorderDetail.setDescription("");
					trxorderDetail.setStatus(CourseStatus.CLASS);//课时状态
					trxorderDetail.setStartTime(kpoint.getBeginTime());
					trxorderDetail.setEndTime(kpoint.getEndTime());// 课程名
					trxorderDetail.setCurrentLession(0l);//当前课时
					trxorderDetail.setCourseType(2l);//班课类型1对1
					trxorderDetail.setAuthTime(kpoint.getEndTime());//上课后的流水过期时间 默认结束时间
					trxorderDetail.setIfConfirm(TrxOrderDetailConfirmStatus.ALREADYCONFIRM.toString());//已确认
					trxorderDetailList.add(trxorderDetail);
				}
			}
		}
		// 批量添加流水
		trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
	}
	/**
	 * 分割日期数据，获取开始时间和结束时间
	 * @param dateValue
	 * @return
	 * @throws ParseException
	 */
	private List<TrxorderDetail> getClassTime(String dateValue) throws ParseException{
		List<TrxorderDetail> list = new ArrayList<>();
		String[] array = dateValue.split("DAY");
    	if (array.length != 0) {
    		for (int i = 0; i < array.length; i++) {
				TrxorderDetail detail = new TrxorderDetail();
	    		String[] date = array[i].split(" ");//
	    		String[] time = date[1].split("-");//小时分钟
	    		detail.setStartTime(sdf.parse(date[0]+" "+time[0]));
	    		detail.setEndTime(sdf.parse(date[0]+" "+time[1]));
	    		list.add(detail);
			}
		}
    	return list;
	}
	/**
	 * 生成订单号 当前用户id+毫秒数
	 * 
	 * @return
	 */
	public String getOrderNum(Long userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}
	
	/**
	 * 根据id删除一个Trxorder
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTrxorderById(Long id) {
		trxorderDao.deleteTrxorderById(id);
	}

	/**
	 * 修改Trxorder
	 * 
	 * @param trxorder
	 *            要修改的Trxorder
	 */
	public void updateTrxorder(Trxorder trxorder) {
		trxorderDao.updateTrxorder(trxorder);
	}

	/**
	 * 根据id获取单个Trxorder对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Trxorder
	 */
	public Trxorder getTrxorderById(Long id) {
		return trxorderDao.getTrxorderById(id);
	}

	/**
	 * 根据条件获取Trxorder列表
	 * 
	 * @param trxorder
	 *            查询条件
	 * @return List<Trxorder>
	 */
	public List<Trxorder> getTrxorderList(Trxorder trxorder) {
		return trxorderDao.getTrxorderList(trxorder);
	}
	/**
	 * 订单检查请求参数
	 * 
	 * @param sourceMap
	 * @return
	 */
	public TrxReqData checkorderparam(Map<String, String> sourceMap) {
		TrxReqData reqData = new TrxReqData();
		String couponcode = sourceMap.get("couponcode"); // 优惠卷编码
		if (StringUtils.isNotEmpty(couponcode)) {
			reqData.setCouponCode(couponcode);
		}

		String userid = sourceMap.get("userid");// 用户id
		if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
			reqData.setUserId(Long.valueOf(userid));
		} else {
			return null;
		}
	/*	String subjectId = sourceMap.get("subjectId");// 科目id
		if (StringUtils.isNotEmpty(subjectId) && Long.valueOf(subjectId).longValue() > 0) {
			reqData.setSubjectId(Long.valueOf(subjectId));
		} else {
			return null;
		}*/
		String courseId = sourceMap.get("courseId");// 课程id
		if (StringUtils.isNotEmpty(courseId) && Long.valueOf(courseId).longValue() > 0) {
			reqData.setCourseId(Long.valueOf(courseId));
		} else {
			return null;
		}
		String teacherId = sourceMap.get("teacherId");// 教师id
		if (StringUtils.isNotEmpty(teacherId) && Long.valueOf(teacherId).longValue() > 0) {
			reqData.setTeacherId(Long.valueOf(teacherId));
		} else {
			return null;
		}
		String lessionNum = sourceMap.get("lessionNum");// 总课时
		if (StringUtils.isNotEmpty(lessionNum) && Long.valueOf(lessionNum).longValue() > 0) {
			reqData.setLessionNum(Long.valueOf(lessionNum));
		} else {
			return null;
		}

		String reqchanle = sourceMap.get("reqchanle");//请求渠道(WEB,APP)
		if (StringUtils.isNotEmpty(reqchanle)) {
			reqData.setReqChannel(reqchanle);
		} else {
			return null;
		}

		String payType = sourceMap.get("payType");// 支付类型
		if (StringUtils.isNotEmpty(payType)) {
			reqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
		} else {
			reqData.setPayType(EnumUtil.transStringToEnum(PayType.class,PayType.ALIPAY.toString()));
		}

		String reqIp = sourceMap.get("reqIp");// 用户reqIp
		if (StringUtils.isNotEmpty(reqIp)) {
			reqData.setReqIp(reqIp);
		} else {
			reqData.setReqIp("");
		}
		String studentName = sourceMap.get("studentName");// 学生姓名
		if (StringUtils.isNotEmpty(studentName)) {
			reqData.setStudentName(studentName);
		} else {
			return null;
		}
		String mobile = sourceMap.get("mobile");// 手机
		if (StringUtils.isNotEmpty(mobile)) {
			reqData.setMobile(mobile);
		} else {
			return null;
		}
		String remarks = sourceMap.get("remarks");// 备注
		if (StringUtils.isNotEmpty(remarks)) {
			reqData.setRemarks(remarks);
		} else {
			reqData.setRemarks("");
		}
		String courseModel = sourceMap.get("courseModel");// 上课类型
		if (StringUtils.isNotEmpty(courseModel)) {
			reqData.setCourseModel(courseModel);
		} else {
			return null;
		}
		String dateValue = sourceMap.get("dateValue");// 上课时间字符串
		if (StringUtils.isNotEmpty(dateValue)) {
			reqData.setDateValue(dateValue);
		} else {
			return null;
		}
		String studyAddress = sourceMap.get("studyAddress");//上课地址
		if (StringUtils.isNotEmpty(studyAddress)) {
			reqData.setStudyAddress(studyAddress);
		} else {
			reqData.setStudyAddress("");
		}
		return reqData;
	}

	/**
	 * 免费赠送订单操作
	 * 
	 * @param sourceMap
	 *            需要的参数
	 * @throws BaseException
	 */
	@Override
	public Map<String, Object> addFreeTrxorder(Map<String, String> sourceMap) throws BaseException {

		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		TrxReqData trxReqData = checkorderparam(sourceMap);
		if (trxReqData == null) {// 参数验证失败
			result.put("msg", "parame-rror");
			return result;
		}

		Course course = courseService.getCourseById(Long.valueOf(sourceMap.get("courseId")));
		if (course == null) {
			result.put("msg", "couser-is-null");
			return result;
		}
		/*
		 * // 非0元直接返回 if (course.getCurrentprice().doubleValue() > 0) {
		 * result.put("msg", "course-Currentprice-not-0"); return result; }
		 */
		// 已经添加过无需重复添加
		List<TrxorderDetail> buyDetailList = trxorderDetailService.getTrxorderDetailListBuy(trxReqData.getUserId());
		if (buyDetailList != null && buyDetailList.size() > 0) {
			for (TrxorderDetail detail : buyDetailList) {
				if (detail.getCourseId().equals(course.getId())) {
					result.put("msg", "course-already-success");
					return result;
				}
			}
		}

		// 拼装订单数据
		Trxorder trxorder = new Trxorder();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(trxReqData.getUserId());
		trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
		trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型 免费
		trxorder.setReqChannel(trxReqData.getReqChannel());
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setOrderAmount(course.getCurrentprice()); // 原始金额
		trxorder.setCouponAmount(course.getCurrentprice());// 优惠金额
		trxorder.setAmount(new BigDecimal(0));// 实际支付金额
		trxorder.setDescription(new Gson().toJson(sourceMap));
		trxorder.setPayTime(date);// 支付时间
		// 添加订单
		trxorderDao.addTrxorder(trxorder);

		// 添加流水表

		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		// 创建流水
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		// 用户id
		trxorderDetail.setUserId(trxReqData.getUserId());
		// 课程id
		trxorderDetail.setCourseId(course.getId());
		// 订单id
		trxorderDetail.setTrxorderId(trxorder.getId());
		// 订单类型
		trxorderDetail.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 流水支付状态
		// 下单时间
		trxorderDetail.setCreateTime(new Date());

		// 课程实际支付价格
		trxorderDetail.setCurrentPrice(new BigDecimal(0));
		// 课程名
		trxorderDetail.setCourseName(course.getName());
		// 课程状态（未开始）
		trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
		// 订单请求号
		trxorderDetail.setRequestId(trxorder.getRequestId());
		trxorderDetail.setLastUpdateTime(date);
		// 支付时间
		trxorderDetail.setPayTime(date);
		trxorderDetail.setDescription("");
		// 批量添加流水
		trxorderDetailList.add(trxorderDetail);
		trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);

		result.put("msg", "success");
		return result;

	}

	/**
	 * 根据requestId获取Trxorder
	 * 
	 * @param 列表
	 * @return Trxorder
	 */
	public Trxorder getTrxorderByRequestId(String requestId) {
		return trxorderDao.getTrxorderByRequestId(requestId);
	}

	/**
	 * 订单回调,余额支付订单操作 事物开启
	 * 
	 * @param
	 * @return
	 * @throws StaleObjectStateException
	 * @throws ParseException 
	 */
	public Map<String, String> updateCompleteOrder(TrxReqData trxReqData) throws AccountException, StaleObjectStateException, ParseException {
		logger.info("updateCompleteOrder trxReqData:" + trxReqData);
		Map<String, String> res = new HashMap<String, String>();
		// userAccount要重新查询一次，否则乐观锁版本号异常
		UserAccount userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
		if (!AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
			res.put(OrderConstans.RESCODE, "您的账户已冻结");
			res.put("requestId", trxReqData.getRequestId());
			logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
			return res; // 账户被冻结
		}
		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = trxReqData.getAmount();// 订单所需支付的金额
		BigDecimal balance = userAccount.getBalance();// 账户余额
		BigDecimal useCashAmoun = new BigDecimal(0);
		BigDecimal useVmAmount = new BigDecimal(0);
		if (balance.compareTo(amount) < 0) {// 账户余额不足
			res.put(OrderConstans.RESCODE, "balance");
			res.put("amount", amount.toString());
			res.put("balance", balance.toString());
			res.put("bankAmount", amount.subtract(balance).toString());
			res.put("requestId", trxReqData.getRequestId());
			logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
			return res;
		}
		if (userAccount.getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额,vm足够支付
			useVmAmount = amount;
		} else {// vm不够的时候 再扣除cash的余额
			useVmAmount = userAccount.getVmAmount();//
			useCashAmoun = amount.subtract(useVmAmount);// 需要扣除的cash的金额
		}
		Trxorder trxorder = getTrxorderByRequestId(trxReqData.getRequestId());
		trxorder.setCashAmount(useCashAmoun);
		trxorder.setVmAmount(useVmAmount);
		trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
		trxorder.setPayTime(trxReqData.getCreateTime());
		trxorder.setPayType(trxReqData.getPayType().toString());
		//验证优惠券信息
		if(trxorder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
			
			//验证优惠券编码
	        Map<String,Object> map = couponCodeService.checkCode(couponCodeDTO,trxReqData.getUserId());
	        if(map.get("msg")!="true"){//验证不通过，返回优惠券编码错误信息
	        	res.put(OrderConstans.RESCODE, map.get("msg").toString());
				res.put("amount", amount.toString());
				res.put("balance", balance.toString());
				res.put("bankAmount", amount.subtract(balance).toString());
				res.put("requestId", trxReqData.getRequestId());
				logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
				return res;
	        }
		}
		// 扣款
		userAccountService.debit(userAccount, trxReqData.getAmount(), AccountHistoryType.SALES, trxReqData.getUserId(), trxReqData.getTrxorderId(), trxReqData.getRequestId(), trxReqData.getCreateTime(), trxReqData.getDescription(), true, AccountBizType.COURSE);
		
		// 更新订单的状态
		updateTrxorderStatusSuccess(trxorder);
		// 修改教师的课表
		String msg = updateTeacherClassHour(trxReqData);
		if(msg!=null&&!msg.equals("")){
			res.put(OrderConstans.RESCODE, msg);
			return res;
		}
		// 修改课程购买数量
		courseProfileDao.updateCourseProfile(CourseProfileType.buycount.toString(), trxorder.getCourseId(), 1L, "+");
		// 修改教师的学生人数
		updateTeacherProfileStudentNum(trxorder);
		//更改优惠券信息
		updateCouponCodeInfo(trxorder);
		//更新班课报名人数
		updateClassJoinNum(trxorder);
		Teacher teacher = teacherService.getTeacherById(trxorder.getTeacherId());
		//发送订单消息
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "用户"+trxorder.getStudentName()+"向您购买了"+trxorder.getCourseName()+"具体上课信息请您去查看订单！";
		msgReceive.setContent(msgContent);
		msgReceive.setReceivingCusId(teacher.getUserId());
		msgReceive.setType(LetterConstans.LETTER_TYPE_PAY_MESSAGE);
		msgReceive.setShowname(trxorder.getStudentName());
		try {
			//发送消息
			msgReceiveService.addOtherMessageByCusId(msgReceive);
			//发送短信
			userMobileMsgService.sendEx(msgContent, trxorder.getMobile(), null, null, null);
		} catch (Exception e) {
			logger.info("send orderMsg error trxorder.id:"+trxorder.getId());
		}
		res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
		logger.info("updateCompleteOrder trxReqData success");
		return res;
	}
	//更新班课报名人数
	@Override
	public void updateClassJoinNum(Trxorder trxorder){
		Course course=courseService.getCourseById(trxorder.getCourseId());
		//班课修改课程报名人数
		if(course.getSellType().equals("2")){
			courseService.updateJoinNum(course.getId());
			//班课人数已满  修改班课状态为已完成
			if(course.getPeopleNum()<=(course.getJoinNum()+1)){
				courseService.updateFinshStatu("FINSHED", course.getId());
			}
		}
	}
	
	/**
	 * 更新优惠券信息
	 * @param trxorder
	 */
	@Override
	public void updateCouponCodeInfo(Trxorder trxorder) {
		if(trxorder.getCouponCodeId()>0){//订单使用了优惠券1
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
			couponService.updateCouponUserNum(couponCodeDTO.getCouponId());//更新优惠券使用数
			CouponCode couponCode=new CouponCode();
			couponCode.setPayTime(new Date());
			couponCode.setStatus(2);//已使用
			couponCode.setTrxorderId(trxorder.getId());//订单id
			couponCode.setRequestId(trxorder.getRequestId());//订单请求号
		    couponCode.setUserId(trxorder.getUserId());//用户id
		    couponCode.setUseTime(new Date());//使用时间
		    couponCode.setId(trxorder.getCouponCodeId());
			couponCodeService.updateCouponCode(couponCode);//更新优惠券编码使用后的信息
		}
		
	}
	
	@Override
	public void updateTeacherProfileStudentNum(Trxorder trxorder) {
		List<Trxorder> list = trxorderDao.getTeacherStudents(trxorder);
		if(list!=null&&list.size()>0){
			teacherProfileService.updateTeacherProfileStudentNum(trxorder.getTeacherId(),"+");
		}
	}
	/**
	 * 修改教师课表
	 * @param trxReqData
	 * @throws ParseException 
	 */
	@Override
	public String updateTeacherClassHour(TrxReqData trxReqData) throws ParseException {
		String message = "";
		//获取课程
		Course course = courseService.getCourseById(trxReqData.getCourseId());
		if(course.getSellType().equals("1")){//一对一执行
			List<TrxorderDetail> detailList = trxorderDetailService.getTrxorderDetailListByTrxorderId(trxReqData.getTrxorderId());
			String dateValue = "";
			if(detailList!=null&&detailList.size()>0){
				SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat timeFmt = new SimpleDateFormat("HH:mm");
				for (TrxorderDetail trxorderDetail : detailList) {
					dateValue+=dateFmt.format(trxorderDetail.getStartTime())+" "+timeFmt.format(trxorderDetail.getStartTime())+"-"+timeFmt.format(trxorderDetail.getEndTime())+"DAY";
				}
			}
			message = teacherClassHourService.updateStudentConsultClass(dateValue,trxReqData.getTeacherId() ,trxReqData.getUserId());
			logger.info("unpdate teacherClassHour error" + message);
		}
		return message;
	}
	/**
	 * 订单回调支付成功,预处理查询
	 * 
	 * @param
	 * @return
	 */
	public TrxReqData preQueryCompleteOrder(TrxReqData trxReqData) throws AccountException {
		// 查询用户账户
		UserAccount userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
		// 账户状态为正常状态
		if (AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
			BigDecimal orderAmount = trxReqData.getAmount();// 订单的金额
			BigDecimal bankAmount = trxReqData.getBankAmount();// 本次支付回调的金额
			BigDecimal balance = userAccount.getBalance().subtract(userAccount.getForzenAmount());// 账户的可用余额
			// 充值的加余额不足支付本次订单的，改为充值操作
			if (bankAmount.add(balance).compareTo(orderAmount) < 0) {
				trxReqData.setRecharge(true);
			} else {
				trxReqData.setRecharge(false);
			}
		} else {
			// 此处冻结状态不管。可以充值进去，但是不允许消费
			// 只充值操作,不够支付改订单的。
			trxReqData.setRecharge(true);
		}
		trxReqData.setUserAccount(userAccount);
		return trxReqData;
	}

	/**
	 * 更新订单状态为成功,网银的回调
	 * 
	 * @param trxorder
	 */
	public void updateTrxorderStatusSuccess(Trxorder trxorder) throws StaleObjectStateException {
		// 更新订单表状态
		Long cnt = trxorderDao.updateTrxorderStatusSuccess(trxorder);
		// 更新流水的状态
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		trxorderDetail.setPayTime(trxorder.getPayTime());
		trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
		trxorderDetail.setStatus(CourseStatus.CLASS);//修改为待上课状态
		trxorderDetail.setTrxStatus(trxorder.getTrxStatus());
		trxorderDetail.setRequestId(trxorder.getRequestId());
		Long cnt2 = trxorderDetailDao.updateTrxorderDetailStatusSuccess(trxorderDetail);
		System.out.println("wwwwwwwwwwwwwwwwwwwwwww          cnt:"+cnt);
		System.out.println("wwwwwwwwwwwwwwwwwwwwwww           cnt2:"+cnt2);
		if (cnt.longValue() == 0 || cnt2.longValue() == 0) {
			throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_UPDATE_NONE);
		}
	}

	/**
	 * 订单分页查询 ,根据条件
	 * 
	 * @param QueryTrxorder
	 * @return List
	 */
	public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page) {

		return trxorderDao.queryOrderPageResult(queryTrxorder, page);
	}

	/**
	 * 订单id查询流水的课程集合
	 * @return
	 */
	public List<Course> getTrxCourseByRequestId(String requestId){
		return trxorderDao.getTrxCourseByRequestId(requestId);
	}
	/**
	 * 个人中心订单查询
	 * */
	public List<TrxOrderDTO> queryOrderForWebUc(QueryTrxorder queryTrxorder, PageEntity page) {
		return trxorderDao.queryOrderForUc(queryTrxorder, page);
	}
	public void addDynamicForBuyCourse(Long userId,String requestId) throws Exception{
		//订单课程集合
		List<Course> courses=getTrxCourseByRequestId(requestId);
		if(ObjectUtils.isNotNull(courses)&&courses.size()>0){
			Map<String, String> map = new HashMap<String, String>();
	        map.put("userId",userId + "");//用户id
	        map.put("bizId", courses.get(0).getId() + "");// 学员活动（事件）id 商品id 试卷id
	        map.put("type", 12 + "");//11观看课程 12购买了商品 13 考试
	        map.put("desc", "购买课程");// 动态描述
	        map.put("title", courses.get(0).getName());// 辅助title
	        map.put("assistId",0L+"");// 辅助id
			String content=WebUtils.replaceTagHTML(courses.get(0).getTitle());
			 if (StringUtils.isNotEmpty(content)) {// 回复的内容
				 content=StringUtils.getLength(content, 300);
				 map.put("content", content);
			 }else{
				 map.put("content", "");
			 }
			 map.put("url", CommonConstants.contextPath+"/front/couinfo/"+courses.get(0).getId());//操作url
		}
	}
	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	public Trxorder getOrderInfoById(Long id) {
		return trxorderDao.getOrderInfoById(id);
	}
	/**
	 * 网站支付成功的订单数量和销售金额
	 * 
	 * @return orderNum(key) 订单数
	 *         salesNum(key) 销售金额
	 */
	public Map<String, Object> getOrderTotalAndSales() {
		return trxorderDao.getOrderTotalAndSales();
	}
	/**
	 * 定时修改今日订单是否有失效订单
	 */
	public void updateOrderDeadline(){
		try {
			logger.info("quartz updateOrderDeadline run...");
			//获取所有未支付的订单
			List<Trxorder> orderList = trxorderDao.getTrxorderUnPay();
			if(orderList!=null&&orderList.size()>0){
				//需要修改状态的订单
				for (Trxorder trxorder : orderList) {
					//判断是否过期
					if(new Date().getTime()-trxorder.getLoseTime().getTime()>=0){
						updateOrderDeadlineInfo(trxorder);
					}
				}
			}
		} catch (Exception e) {
			logger.info("TrxorderService.updateOrderDeadline error"+e);
		}
		
				
	}
	/**
	 * 修改过期订单的操作
	 * @param trxorder
	 */
	private void updateOrderDeadlineInfo(Trxorder trxorder) {
		//修改未支付的订单变为已过期
		trxorder.setTrxStatus(TrxOrderStatus.OVER.toString());
		trxorderDao.updateTrxorderStatus(trxorder);
		//修改未支付的详情改为已退单
		TrxorderDetail detail = new TrxorderDetail();
		detail.setRequestId(trxorder.getRequestId());
		trxorderDetailDao.updateTrxorderDetailStatus(detail);
		//修改教师课表状态为可预约
		List<TrxorderDetail> detailList = trxorderDetailDao.getTrxorderDetailList(detail);
		
	}
	/**
	 * 定时删除一个月前之前的订单
	 */
	public void deleteOrderDeadline(){
		try {
			//上个月的时间
			Date date = getLastMonthDate(new Date());
			logger.info("quartz deleteOrderDeadline run..."+date);
			//删除上个月之前的过期订单
			trxorderDao.deleteOrderOverStatus(date);
		} catch (Exception e) {
			logger.info("quartz deleteOrderDeadline run..."+e);
		}
	}
	
	/**
	 * 获取当前时间的上个月日期
	 * @param date
	 * @return
	 */
	public Date getLastMonthDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		Date lastDate = calendar.getTime();
		return lastDate;
	}

	@Override
	public TrxorderExpand getTrxorderExpandById(Long id) {
		return this.trxorderDao.getTrxorderExpandById(id);
	}

	public List<TrxorderExpand> getTrxorderExpandByTeacherId(TrxorderExpand trxorderExpand,PageEntity page){
		return this.trxorderDao.getTrxorderExpandByTeacherId(trxorderExpand, page);
	}
	
	@Override
	public void updateTrxorderRefundAudit(Long orderId,String description,Long userType){
		// 修改订单状态
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", orderId);
		map.put("trxStatus", TrxOrderStatus.AUDIT.toString());
		trxorderDao.updateTrxorderTrxStatus(map);
		// 添加退款操作记录
		Trxorder trxorder = this.getOrderInfoById(orderId);
		Teacher teacher = this.teacherService.getTeacherById(trxorder.getTeacherId());
		TrxorderOptRecord trxorderOptRecord = new TrxorderOptRecord();
		trxorderOptRecord.setCreatetime(new Date());
		trxorderOptRecord.setDesc(description);
		trxorderOptRecord.setOptuser(0L);
		trxorderOptRecord.setOptusername("");
		trxorderOptRecord.setOrderId(orderId);
		trxorderOptRecord.setUserType(userType);
		if(userType.intValue()==0){// 学生
			trxorderOptRecord.setApplicant(trxorder.getStudentName());
		}else if(userType.intValue()==1){// 老师
			trxorderOptRecord.setApplicant(teacher.getUserExpand().getRealname());
		}
		trxorderOptRecord.setType(TrxOrderOptRecordEnum.ORDERAUDIT.toString());
		this.trxorderOptRecordService.addTrxorderOptRecord(trxorderOptRecord);
		// 发送消息
		MsgReceive msgReceive = new MsgReceive();
		String msgContent = "您的退款申请已经提交，请等待后台管理员审核！";
		msgReceive.setContent(msgContent);
		
		if(userType==0){
			msgReceive.setReceivingCusId(trxorder.getUserId());
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
			logger.info("send updateTrxorderRefundAudit error");
		}
	}
	/**
	 * 订单退款
	 * @throws AccountException 
	 * @throws StaleObjectStateException 
	 */
	public void updateTrxorderRefund(Trxorder trxorder) throws AccountException, StaleObjectStateException,Exception {
		// 根据订单交易号获得流水列表
		List<TrxorderDetail> trxorderDetail = trxorderDetailService.queryTrxorderDetailRefund(trxorder.getRequestId());
		// 遍历流水列表
		BigDecimal money = new BigDecimal(0);
		for (TrxorderDetail detail : trxorderDetail) {
			TrxorderDetail _detail = trxorderDetailService.getTrxorderDetailById(detail.getId());
			
			if (detail.getVersion().intValue() == _detail.getVersion().intValue()) {
				// 退款金额
				money = money.add(_detail.getCurrentPrice());
				// 修改流水状态
				_detail.setTrxStatus(AuthStatus.REFUND.toString()); // 退款
				_detail.setStatus(CourseStatus.CANCEL);// 取消
				_detail.setLastUpdateTime(new Date());
				logger.info("-------订单退款------请求号：" + _detail.getRequestId());
				// 修改状态
				trxorderDetailService.updateTrxorderDetailStatusRefund(_detail);
				// 课时+1
				trxorderDetailService.updateTrxorderLessionOverAdd(_detail.getTrxorderId());
				// 获取时间
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
				String hour = hourFormat.format(_detail.getStartTime()) + "-" + hourFormat.format(_detail.getEndTime());
				Date date = dateFormat.parse(dateFormat.format(_detail.getStartTime()));
				// 重置课时到发布状态
				TeacherClassHour teacherClassHour = new TeacherClassHour();
				teacherClassHour.setTeacherId(_detail.getTeacherId());
				teacherClassHour.setDateDay(date);
				teacherClassHour.setTime(hour);
				teacherClassHour.setUserId(0L);
				teacherClassHour.setStatus(2);// 发布状态
				this.teacherClassHourService.updateTeacherClassHourStatus(teacherClassHour);
			}
		}
		logger.info("----------------订单退款总金额：", money);
		// 获得用户账号
		UserAccount account = userAccountService.getUserAccountByUserId(trxorder.getUserId());
		logger.info("----------------用户账号信息：", account.getId());
		logger.info("----------------用户账号余额----------：", account.getBalance());
		userAccountService.credit(account, money.setScale(2, BigDecimal.ROUND_HALF_UP), AccountType.CASH, AccountHistoryType.REFUND, trxorder.getUserId(), trxorder.getId(), trxorder.getRequestId(),
        		"",  new Date(), "", true, AccountBizType.COURSE);//课程订单
	}
	@Override
	public void updateTrxorderTrxStatus(Long id, String trxStatus) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("trxStatus", trxStatus);
		this.trxorderDao.updateTrxorderTrxStatus(map);
	}
	@Override
	public List<TrxOrderDTO> indexOrder() {
		// TODO Auto-generated method stub
		List<TrxOrderDTO> orderList =(List<TrxOrderDTO>) memCache.get(MemConstans.ORDER_INDEX);
		if (ObjectUtils.isNull(orderList) || orderList.size() == 0) {
			orderList = trxorderDao.indexOrder();
			if (ObjectUtils.isNotNull(orderList)) {
				//手机号码处理
				for(TrxOrderDTO order:orderList){
					if(StringUtils.isNotEmpty(order.getMobile())){
						order.setMobile(phoneHandler(order.getMobile()));
					}
				}
				memCache.set(MemConstans.ORDER_INDEX, orderList, MemConstans.ORDER_INDEX_TIME);
			}
		}
		return orderList;
	}
	//手机号码处理
	public static String phoneHandler(String phone){
		phone=phone.substring(0,3)+"****"+phone.substring(phone.length()-4,phone.length());
		return phone;
	}
	@Override
	public int queryUserIsJoin(Long userId, Long courseId,String trxStatus) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		map.put("userId",userId);
		map.put("courseId", courseId);
		map.put("trxStatus", trxStatus);
		return trxorderDao.queryUserIsJoin(map);
	}
	@Override
	public void updateTrxorderAssessTeaToStu(Trxorder trxorder){
		this.trxorderDao.updateTrxorderAssessTeaToStu(trxorder);
	}
	@Override
	public void updateTrxorderAssessStuToTea(Trxorder trxorder){
		this.trxorderDao.updateTrxorderAssessStuToTea(trxorder);
	}
}