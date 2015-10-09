package com.yizhilu.os.edu.service.impl.statistics;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.dao.statistics.StatisticsDayDao;
import com.yizhilu.os.edu.entity.statistics.StatisticsDay;
import com.yizhilu.os.edu.service.statistics.StatisticsDayService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
/**
 * StatisticsDayDay管理接口
 * User: qinggang.liu
 * Date: 2014-11-05
 */
@Service("statisticsDayService")
public class StatisticsDayServiceImpl implements StatisticsDayService{
	MemCache memCache = MemCache.getInstance();
 	@Autowired
    private StatisticsDayDao statisticsDayDao;
 	// 注入 websiteProfileService 接口
 	@Autowired
 	private WebsiteProfileService websiteProfileService;
 	
 	
 	@Getter@Setter
	private Map<String,Object> userMsg= new HashMap<String,Object>();
    /**
     * 定时添加StatisticsDayDay
     * @param date
     * @return
     */
    public void addStatisticsDayAuto(){
    	Calendar c = Calendar.getInstance();		
    	c.setTime(new Date());		
    	//每天定时统计前一天的数据，天数减1
    	c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);		
    	Date date = c.getTime();
    	//先删除再生成，防止数据重复
    	statisticsDayDao.delStatisticsDay(date);
    	//先添加网校统计
    	statisticsDayDao.addStatisticsDay(date);
  	
    	memCache.remove(MemConstans.WEB_STATISTICS);
    	memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+7);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+15);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+30);
    }
    
    /**
     * 查询网站统计
     */
	public Map<String,Object> getStatisticsMsg(String month, String year){
		
		if (month!=null &&month!= "") {
			getStatisticsDayByMonth(month, year);// 按月查
		} else if (year != null) {
			getStatisticsDayByYear(year);//按年查
		}
		return userMsg;
	}
	
	/**
	 * 按月查询网站统计
	 * 
	 * @param month
	 * @return
	 */
	public void getStatisticsDayByMonth(String month, String year) {
		String showDate = "";
		List<StatisticsDay> statisticsDayList = statisticsDayDao.getStatisticsByMonth(month, year);
		for (int i=0;i<statisticsDayList.size();i++ ) {
			showDate += (i+1) + ",";
		}
		showDate = showDate.substring(0, showDate.length() - 1);
		userMsg.put("showDate", showDate);
		userMsg.put("statisticsDayList", statisticsDayList);
	}

	/**
	 * 按年查询网站统计
	 * @param year
	 * @return
	 */
	public void getStatisticsDayByYear(String year) {
		List<StatisticsDay> statisticsList = statisticsDayDao.getStatisticsByYear(year);
		String showDate = "01,02,03,04,05,06,07,08,09,10,11,12";
		// 数据拼凑
		userMsg.put("showDate", showDate);
		userMsg.put("statisticsDayList", statisticsList);
	}
	
	/**
	 * 网站统计 （总记录）
	 * 
	 * @return
	 * @throws Exception
	 */
	public StatisticsDay getStatisticsSumMsg(){

		StatisticsDay statisticsDay=  (StatisticsDay) memCache.get(MemConstans.WEB_STATISTICS);
        if (statisticsDay!=null) {
            return statisticsDay;
        }
        statisticsDay=statisticsDayDao.getStatisticsSumMsg();
        if (statisticsDay!=null) {
        	memCache.set(MemConstans.WEB_STATISTICS, statisticsDay);
        }
		return statisticsDay;
	}
	/**
	 * 查询指定时间段的统计数据
	 * @param date
	 */
	public List<StatisticsDay> getStatisticsByDate(String startTime,String endTime){
		return statisticsDayDao.getStatisticsByDate(startTime,endTime);
	}
	/**
	 * 动态查询活跃度
	 * @param date
	 */
	@SuppressWarnings("unchecked")
	public List<StatisticsDay> getStatisticThirty(int days){

		List<StatisticsDay> statistics=(List<StatisticsDay>) memCache.get(MemConstans.WEB_STATISTICS_THIRTY+days);
        if (statistics!=null&&statistics.size()>0) {
            return statistics;
        }
        statistics=statisticsDayDao.getStatisticThirty(days);
        if (statistics!=null) {
        	memCache.set(MemConstans.WEB_STATISTICS_THIRTY+days, statistics, MemConstans.WEB_STATISTICS_TIME);
        }
		return statistics;
	}
	/**
	 * 删除指定时间段的统计数据
	 * @param date
	 */
	public void delStatisticsByDate(String startTime,String endTime){
		statisticsDayDao.delStatisticsByDate(startTime,endTime);
		memCache.remove(MemConstans.WEB_STATISTICS);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+7);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+15);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+30);
	}
	/**
	 * 生成指定时间段的统计数据
	 * @param date
	 */
	public void createStatisticsByDate(Date startDate,Date endDate){ 
		Calendar cal = Calendar.getInstance(); 
		List<Date> dates=new ArrayList<Date>();
		dates.add(startDate);
        // 使用给定的 Date 设置此 Calendar 的时间   
        cal.setTime(startDate);   
        while (true) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量   
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后   
            if (endDate.after(cal.getTime())) {  
            	dates.add(cal.getTime());
            } else {  
                break;  
            }  
        }   
        if(dates.get(0).getTime()!=endDate.getTime()){//首尾日起相同，只添加一条
        	dates.add(endDate);
        }
        //批量添加网校统计
        statisticsDayDao.addStatisticsDayBatch(dates);
        
        memCache.remove(MemConstans.WEB_STATISTICS);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+7);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+15);
        memCache.remove(MemConstans.WEB_STATISTICS_THIRTY+30);
	}

	/**
	 * 获取日期的登录人数
	 * @param date
	 * @return
	 */
	public int getTodayLoginNum(Date date){
		return statisticsDayDao.getTodayLoginNum(date);
	}

	/**
	 * 根据类型获取日期的统计数
	 */
	public int getStatisticalNumByType(Date date,String type) {
		return statisticsDayDao.getStatisticalNumByType(date,type);
	}

	/**
	 * 获取总课时
	 */
	public int getStatisticsKpoint() {
		return statisticsDayDao.getStatisticsKpoint();
	}

	/**
	 * 获得社区开关是否开启
	 * @return
	 */
	public String getVerifySnsKeyword() {
		Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());				
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String,Object>) keywordmap.get(WebSiteProfileType.keyword.toString());			
		return map.get("verifySns").toString();
	}

	@Override
	public List<Map<String, Object>> queryStatisticsCourseByCondition(
			Map<String, Object> query,PageEntity page) {
		// TODO Auto-generated method stub
		return statisticsDayDao.queryStatisticsCourseByCondition(query,page);
	}
	
}