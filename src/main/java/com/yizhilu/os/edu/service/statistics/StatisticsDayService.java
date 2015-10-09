package com.yizhilu.os.edu.service.statistics;


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
public interface StatisticsDayService {

	 /**
     * 定时添加StatisticsDay
     * @param date
     * @return
     */
    public void addStatisticsDayAuto();
   

    /**
	 * 网站统计 （按年、月）
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getStatisticsMsg(String month, String year);
	/**
	 * 网站统计 （总记录）
	 * 
	 * @return
	 * @throws Exception
	 */
	public StatisticsDay getStatisticsSumMsg();
	/**
	 * 查询最近30条的统计数据
	 * @param date
	 */
	public List<StatisticsDay> getStatisticThirty(int days);
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
	 * 生成指定时间段的统计数据
	 * @param date
	 */
	public void createStatisticsByDate(Date startTime,Date endTime);

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

	public String getVerifySnsKeyword();
	/**
	 * 统计课程信息
	 * @param query
	 * @return
	 */
	public List<Map<String,Object>> queryStatisticsCourseByCondition(Map<String,Object>  query,PageEntity page);
}