package com.yizhilu.os.edu.dao.impl.statistics;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.dao.statistics.StatisticsDayDao;


import com.yizhilu.os.edu.entity.statistics.StatisticsDay;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;

/**
 *
 * StatisticsDay
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-11-05
 */
 @Repository("statisticsDayDao")
public class StatisticsDayDaoImpl extends GenericDaoImpl implements StatisticsDayDao{

	/**
     * 添加StatisticsDay web
     * @param date
     * @return
     */
	public void addStatisticsDay(Date date) {
         this.insert("StatisticsDayMapper.createStatisticsDay",date);
    }
	 /**
     * 日期批量添加StatisticsDay web
     * @param date
     * @return
     */
    public void addStatisticsDayBatch(List<Date> dates){
    	for(Date date:dates){
    		this.insert("StatisticsDayMapper.createStatisticsDay",date);
    	}
    }
    
    /**
     * 更新StatisticsDay sns
     * @param date
     * @return
     */
	public void updateStatisticsDay(StatisticsDay statisticsDay) {
         this.update("StatisticsDayMapper.updateStatisticsDay",statisticsDay);
    }
	 /**
     * 日期批量更新StatisticsDay sns
     * @param date
     * @return
     */
    public void updateStatisticsDayBatch(List<StatisticsDay> statisticsDays){
    	for(StatisticsDay statisticsDay:statisticsDays){
    		this.update("StatisticsDayMapper.updateStatisticsDay",statisticsDay);
    	}
    }
    
	/**
	 * 按年查询网站统计
	 * 
	 * @param queryUser
	 * @return
	 */
	public List<StatisticsDay> getStatisticsByYear(String year){
		return this.selectList("StatisticsDayMapper.getStatisticsByYear", year);
	}
	/**
	 * 按月查询网站统计
	 * 
	 * @param queryUser
	 * @return
	 */
	public List<StatisticsDay> getStatisticsByMonth(String month,String year){
		if(Integer.parseInt(month)<10){
			month="0"+month;
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("nowMonth",year + "-" + month + "-01");//年月日
		map.put("nowYear",year + "-" + month);//年月
		return this.selectList("StatisticsDayMapper.getStatisticsByMonth", map);
	}
	/**
	 * 网站统计 （总记录）
	 * 
	 * @return
	 * @throws Exception
	 */
	public StatisticsDay getStatisticsSumMsg(){
		return this.selectOne("StatisticsDayMapper.getStatisticsSumMsg", 0);
	}
	/**
	 * 查询最近30条的统计数据
	 * @param date
	 */
	public List<StatisticsDay> getStatisticThirty(int days){
		return this.selectList("StatisticsDayMapper.getStatisticThirty",days);
	}
	/**
	 * 查询指定时间段的统计数据
	 * @param date
	 */
	public List<StatisticsDay> getStatisticsByDate(String startTime,String endTime){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return this.selectList("StatisticsDayMapper.getStatisticsByDate", map);
	}
	/**
	 * 删除指定时间段的统计数据
	 * @param date
	 */
	public void delStatisticsByDate(String startTime,String endTime){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		this.delete("StatisticsDayMapper.delStatisticsByDate", map);
	}
	/**
	 * 删除指定日期统计
	 */
	public void delStatisticsDay(Date date) {
		this.delete("StatisticsDayMapper.delStatisticsDay", date);
	}


	/**
	 * 获取日期的登录人数
	 * @param date
	 * @return
	 */
	public int getTodayLoginNum(Date date){
		return this.selectOne("StatisticsDayMapper.statistics_loginNumToday",date);
	}
	/**
	 * 根据类型获取日期的统计数
	 */
	public int getStatisticalNumByType(Date date,String type) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("date", date);
		map.put("type", type);
		return this.selectOne("StatisticsDayMapper.getStatisticalNumByType",map);
	}
	/**
	 * 获取总课时
	 */
	public int getStatisticsKpoint() {
		return this.selectOne("StatisticsDayMapper.getStatisticsKpoint", 0);
	}
	@Override
	public List<Map<String, Object>> queryStatisticsCourseByCondition(
			Map<String, Object> query,PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("StatisticsDayMapper.queryStatisticsCourseByCondition", query,page);
	}
 }
