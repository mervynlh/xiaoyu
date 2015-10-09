package com.yizhilu.os.edu.dao.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserIntegralRecord;

/**
 * UserIntegralRecord管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface UserIntegralRecordDao {

	/**
	 * 添加UserIntegralRecord
	 * 
	 * @param userIntegralRecord
	 *            要添加的UserIntegralRecord
	 * @return id
	 */
	public java.lang.Long addUserIntegralRecord(UserIntegralRecord userIntegralRecord);

	/**
	 * 根据id删除一个UserIntegralRecord
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralRecordById(Long id);

	/**
	 * 修改UserIntegralRecord
	 * 
	 * @param userIntegralRecord
	 *            要修改的UserIntegralRecord
	 */
	public void updateUserIntegralRecord(UserIntegralRecord userIntegralRecord);

	/**
	 * 根据id获取单个UserIntegralRecord对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralRecord
	 */
	public UserIntegralRecord getUserIntegralRecordById(Long id);

	/**
	 * 根据条件获取UserIntegralRecord列表
	 * 
	 * @param userIntegralRecord
	 *            查询条件
	 * @return List<UserIntegralRecord>
	 */
	public List<UserIntegralRecord> getUserIntegralRecordList(UserIntegralRecord userIntegralRecord);

	/**
	 * 查询用户积分记录
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getUserIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page);
	
	
	/**
	 * 查询用户现在反馈积分
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getUserDownIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page);

	/**
	 * 积分兑换记录
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getExchangeIntegralRecord(UserIntegralRecord userIntegralRecord, PageEntity page);

	/**
	 * 更改兑换记录状态
	 * 
	 * @param id
	 */
	public void updateIntegralRecordStatus(Long id);

	/**
	 * 查询用户今天是否登陆积分纪录
	 * 
	 * @param userIntegralRecord
	 * @return
	 */
	public Long getUserScoreByToday(UserIntegralRecord userIntegralRecord);

	/**
	 * 查询其它积分纪录
	 * 
	 * @param userIntegralRecord
	 * @return
	 */
	public Long getUserScoreByOther(UserIntegralRecord userIntegralRecord);
	
	/**
	 * 查询当月签到天数
	 * @param map 用户id,开始时间,结束时间
	 * @return
	 */
	public List<Integer> getSignCountByTime(Map<String,Object> map);
	
	/**
	 * 获取签到数
	 * @param map 用户id 开始时间，结束时间
	 * @return
	 */
	public Integer getSignCount(Map<String,Object> map);
}