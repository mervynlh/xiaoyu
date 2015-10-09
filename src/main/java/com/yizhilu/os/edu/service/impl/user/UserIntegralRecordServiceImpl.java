package com.yizhilu.os.edu.service.impl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.entity.course.Course;
import com.yizhilu.os.edu.entity.user.UserIntegral;
import com.yizhilu.os.edu.entity.user.UserIntegralRecord;
import com.yizhilu.os.edu.constants.enums.PayType;
import com.yizhilu.os.edu.constants.enums.ReqChanle;
import com.yizhilu.os.edu.dao.user.UserIntegralRecordDao;
import com.yizhilu.os.edu.service.course.CourseService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.user.UserIntegralRecordService;
import com.yizhilu.os.edu.service.user.UserIntegralService;

/**
 * UserIntegralRecord管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("userIntegralRecordService")
public class UserIntegralRecordServiceImpl implements UserIntegralRecordService {

	@Autowired
	private UserIntegralRecordDao userIntegralRecordDao;
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TrxorderService trxorderService;

	/**
	 * 添加UserIntegralRecord
	 * 
	 * @param userIntegralRecord
	 *            要添加的UserIntegralRecord
	 * @return id
	 */
	public java.lang.Long addUserIntegralRecord(UserIntegralRecord userIntegralRecord) {
		return userIntegralRecordDao.addUserIntegralRecord(userIntegralRecord);
	}

	/**
	 * 根据id删除一个UserIntegralRecord
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralRecordById(Long id) {
		userIntegralRecordDao.deleteUserIntegralRecordById(id);
	}

	/**
	 * 修改UserIntegralRecord
	 * 
	 * @param userIntegralRecord
	 *            要修改的UserIntegralRecord
	 */
	public void updateUserIntegralRecord(UserIntegralRecord userIntegralRecord) {
		userIntegralRecordDao.updateUserIntegralRecord(userIntegralRecord);
	}

	/**
	 * 根据id获取单个UserIntegralRecord对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralRecord
	 */
	public UserIntegralRecord getUserIntegralRecordById(Long id) {
		return userIntegralRecordDao.getUserIntegralRecordById(id);
	}

	/**
	 * 根据条件获取UserIntegralRecord列表
	 * 
	 * @param userIntegralRecord
	 *            查询条件
	 * @return List<UserIntegralRecord>
	 */
	public List<UserIntegralRecord> getUserIntegralRecordList(UserIntegralRecord userIntegralRecord) {
		return userIntegralRecordDao.getUserIntegralRecordList(userIntegralRecord);
	}

	/**
	 * 查询用户积分记录
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getUserIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page) {
		return userIntegralRecordDao.getUserIntegralRecordListPage(userIntegralRecord, page);
	}
	
	@Override
	public List<UserIntegralRecord> getUserDownIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page) {
		// TODO Auto-generated method stub
		return userIntegralRecordDao.getUserDownIntegralRecordListPage(userIntegralRecord, page);
	}
	
	
	/**
	 * 积分兑换记录
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getExchangeIntegralRecord(UserIntegralRecord userIntegralRecord, PageEntity page) {
		return userIntegralRecordDao.getExchangeIntegralRecord(userIntegralRecord, page);
	}
	/**
	 * 更改兑换记录状态
	 * 
	 * @param id
	 */
	public void updateIntegralRecordStatus(Long id) {
		userIntegralRecordDao.updateIntegralRecordStatus(id);
	}
	/**
	 * 进行兑换操作
	 * 
	 * @param userIntegralRecord
	 *            积分记录
	 * @param courseId
	 *            课程id
	 * @param userIntegral
	 *            用户积分对象
	 * @throws Exception 
	 */
	public boolean addExchangeOperate(UserIntegralRecord userIntegralRecord, Long courseId,UserIntegral userIntegral,String ipAdrr) throws Exception {
		if(courseId!=0){//判断是否兑换的是课程
			Course course = courseService.getCourseById(courseId);
			if(ObjectUtils.isNull(course)){
				return false;
			}
			//添加课程生成订单流水
			Map<String, String> sourceMap = new HashMap<String,String>();
			sourceMap.put("courseId", courseId+"");//添加课程id
			sourceMap.put("userid", userIntegralRecord.getUserId()+"");//添加用户id
			sourceMap.put("reqchanle", ReqChanle.WEB.toString());//请求渠道
			sourceMap.put("payType", PayType.INTEGRAL.toString());//支付类型
			sourceMap.put("reqIp", ipAdrr);//ip地址
			sourceMap.put("type", "1");//类型默认课程1
			trxorderService.addFreeTrxorder(sourceMap);
		}
		this.addUserIntegralRecord(userIntegralRecord);//添加积分记录
		//更新用户最新积分
		userIntegral.setCurrentScore(userIntegralRecord.getCurrentScore());
		userIntegralService.updateUserIntegral(userIntegral);
		return true;
	}

	/**
	 * 查询用户今天是否登陆积分纪录
	 * 
	 * @param userIntegralRecord
	 * @return
	 */
	public boolean getUserScoreByToday(UserIntegralRecord userIntegralRecord) {
		Long num=userIntegralRecordDao.getUserScoreByToday(userIntegralRecord);
		if(num!=0){
			return true;
		}
		return false;
	}

	/**
	 * 查询其它积分纪录
	 * 
	 * @param userIntegralRecord
	 * @return
	 */
	public boolean getUserScoreByOther(UserIntegralRecord userIntegralRecord) {
		Long num=userIntegralRecordDao.getUserScoreByOther(userIntegralRecord);
		if(num!=0){
			return true;
		}
		return false;
	}

	@Override
	public List<Integer> getSignCountByTime(Long userId, Date startTime, Date endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return this.userIntegralRecordDao.getSignCountByTime(map);
	}

	@Override
	public Integer getSignCount(Long userId, Date startTime, Date endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return this.userIntegralRecordDao.getSignCount(map);
	}
}