package com.yizhilu.os.edu.dao.impl.user;

import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.user.UserIntegralRecord;
import com.yizhilu.os.edu.dao.user.UserIntegralRecordDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;

/**
 * 
 * UserIntegralRecord User: qinggang.liu voo@163.com Date: 2014-05-27
 */
@Repository("userIntegralRecordDao")
public class UserIntegralRecordDaoImpl extends GenericDaoImpl implements UserIntegralRecordDao {
	/**
	 * 添加UserIntegralRecord
	 * 
	 * @param userIntegralRecord
	 *            要添加的UserIntegralRecord
	 * @return id
	 */
	public java.lang.Long addUserIntegralRecord(UserIntegralRecord userIntegralRecord) {
		return this.insert("UserIntegralRecordMapper.createUserIntegralRecord", userIntegralRecord);
	}

	/**
	 * 根据id删除一个UserIntegralRecord
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralRecordById(Long id) {
		this.update("UserIntegralRecordMapper.deleteUserIntegralRecordById", id);
	}

	/**
	 * 修改UserIntegralRecord
	 * 
	 * @param userIntegralRecord
	 *            要修改的UserIntegralRecord
	 */
	public void updateUserIntegralRecord(UserIntegralRecord userIntegralRecord) {
		this.update("UserIntegralRecordMapper.updateUserIntegralRecord", userIntegralRecord);
	}

	/**
	 * 根据id获取单个UserIntegralRecord对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralRecord
	 */
	public UserIntegralRecord getUserIntegralRecordById(Long id) {
		return this.selectOne("UserIntegralRecordMapper.getUserIntegralRecordById", id);
	}

	/**
	 * 根据条件获取UserIntegralRecord列表
	 * 
	 * @param userIntegralRecord
	 *            查询条件
	 * @return List<UserIntegralRecord>
	 */
	public List<UserIntegralRecord> getUserIntegralRecordList(UserIntegralRecord userIntegralRecord) {
		return this.selectList("UserIntegralRecordMapper.getUserIntegralRecordList", userIntegralRecord);
	}

	/**
	 * 查询用户积分记录
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getUserIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page) {
		return this.queryForListPage("UserIntegralRecordMapper.getUserIntegralRecordListPage", userIntegralRecord, page);
	}
	
	@Override
	public List<UserIntegralRecord> getUserDownIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page) {
		// TODO Auto-generated method stub
		return this.queryForListPage("UserIntegralRecordMapper.getUserDownIntegralRecordListPage", userIntegralRecord, page);
	}
	
	/**
	 * 积分兑换记录
	 * 
	 * @param userIntegralRecord
	 * @param page
	 * @return
	 */
	public List<UserIntegralRecord> getExchangeIntegralRecord(UserIntegralRecord userIntegralRecord, PageEntity page) {
		return this.queryForListPage("UserIntegralRecordMapper.getExchangeIntegralRecord", userIntegralRecord, page);
	}
	/**
	 * 更改兑换记录状态
	 * 
	 * @param id
	 */
	public void updateIntegralRecordStatus(Long id) {
		this.update("UserIntegralRecordMapper.updateIntegralRecordStatus", id);
	}
	/**
	 * 查询用户今天是否登陆积分纪录
	 * 
	 * @param userIntegralRecord
	 * @return
	 */
	public Long getUserScoreByToday(UserIntegralRecord userIntegralRecord) {
		List<Long> list=this.selectList("UserIntegralRecordMapper.getUserScoreByToday", userIntegralRecord);
		if(ObjectUtils.isNotNull(list)&&list.size()>0){
			return list.get(0);
		}
		return 0L;
	}
	/**
	 * 查询其它积分纪录
	 * 
	 * @param userIntegralRecord
	 * @return
	 */
	public Long getUserScoreByOther(UserIntegralRecord userIntegralRecord) {
		List<Long> list=this.selectList("UserIntegralRecordMapper.getUserScoreByOther", userIntegralRecord);
		if(ObjectUtils.isNotNull(list)&&list.size()>0){
			return list.get(0);
		}
		return 0L;
	}

	@Override
	public List<Integer> getSignCountByTime(Map<String, Object> map) {
		return this.selectList("UserIntegralRecordMapper.getSignCountByTime", map);
	}

	@Override
	public Integer getSignCount(Map<String, Object> map) {
		return this.selectOne("UserIntegralRecordMapper.getSignCount", map);
	}
}
