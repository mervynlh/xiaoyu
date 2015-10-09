package com.yizhilu.os.edu.dao.statistics;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.statistics.StatisticsDay;



/**
 * StatisticsDay管理接口
 * User: qinggang.liu
 * Date: 2014-11-05
 */
public interface StatisticsDayDao {

	 /**
     * 添加StatisticsDay
     * @param date
     * @return
     */
    public void addStatisticsDay(Date date);
    /**
     * 日期批量添加StatisticsDay
     * @param date
     * @return
     */
    public void addStatisticsDayBatch(List<Date> dates);
    
    /**
     * 更新StatisticsDay sns
     * @param date
     * @return
     */
	public void updateStatisticsDay(StatisticsDay statisticsDay);
	 /**
     * 日期批量更新StatisticsDay sns
     * @param date
     * @return
     */
    public void updateStatisticsDayBatch(List<StatisticsDay> statisticsDays);
    
    /**
	 * 按年查询网站统计
	 * 
	 * @param queryUser
	 * @return
	 */
	public List<StatisticsDay> getStatisticsByYear(String year);
	/**
	 * 按月查询网站统计
	 * 
	 * @param queryUser
	 * @return
	 */
	public List<StatisticsDay> getStatisticsByMonth(String month,String year);
	
	/**
	 * 网站统计 （总记录）
	 * 
	 * @return
	 * @throws Exception
	 */
	public StatisticsDay getStatisticsSumMsg();
	/**
	 * 查询指定时间段的统计数据
	 * @param date
	 */
	public List<StatisticsDay> getStatisticsByDate(String startTime,String endTime);
	/**
	 * 删除指定时间段的统计数据
	 * @param date
	 */
	public void delStatisticsByDate(String startTime,String endTime);
	/**
	 * 查询最近30条的统计数据
	 * @param date
	 */
	public List<StatisticsDay> getStatisticThirty(int days);
	/**
	 * 删除指定日期统计
	 * @param date
	 */
	public void delStatisticsDay(Date date);

	/**
	 * 获取日期的登录人数
	 * @param date
	 * @return
	 */
	public int getTodayLoginNum(Date date);
	/**
	 * 根据类型获取日期的统计数
	 * @param date
	 * @param type
	 * @return
	 */
	public int getStatisticalNumByType(Date date,String type);
	/**
	 * 获取总课时
	 * @return
	 */
	public int getStatisticsKpoint();
	/**
	 * 统计课程信息
	 * @param query
	 * @return
	 */
	public List<Map<String,Object>> queryStatisticsCourseByCondition(Map<String,Object>  query,PageEntity page);
}