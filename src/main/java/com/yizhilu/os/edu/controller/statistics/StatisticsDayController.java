package com.yizhilu.os.edu.controller.statistics;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.StatisticsQueryType;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.entity.statistics.StatisticsDay;
import com.yizhilu.os.edu.service.statistics.StatisticsDayService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;

/**
 * StatisticsDay管理接口 User: qinggang.liu Date: 2014-11-05
 */
@Controller
@RequestMapping("/admin")
public class StatisticsDayController extends EduBaseController {
	private static final Logger logger = LoggerFactory
			.getLogger(StatisticsDayController.class);
	@Autowired
	private StatisticsDayService statisticsDayService;
	// 注入 WebsiteProfileService 接口
	@Autowired
	private WebsiteProfileService websiteProfileService;

	private static final String registerChart = getViewPath("/admin/statistics/statistics_register");// 用户注册统计图
	private static final String loginChart = getViewPath("/admin/statistics/statistics_login");// 用户登录统计图
	private static final String snsChart = getViewPath("/admin/statistics/statistics_sns");// 社区活跃统计图
	private static final String courseOrderChart = getViewPath("/admin/statistics/statistics_trxorder");// 课程订单统计图
	private static final String webChart = getViewPath("/admin/statistics/statistics_web_detail");// 网站概况饼状图
	private static final String courseClassStatustics = getViewPath("/admin/statistics/statistics_class_course");// 班课课程统计
	private static final String courseOneToOneStatustics = getViewPath("/admin/statistics/statistics_onetoone_course");// 一对一课程统计
	private static final String courseAndTeacher=getViewPath("/admin/statistics/statistics_course_teacher");//课程发布教师入驻统计
	private static final String cashOutChart = getViewPath("/admin/statistics/statistics_cashout");//提现统计图
	
	/**
	 * 会员记录列表
	 * 
	 * @return
	 */
	@RequestMapping("/StatisticsDay/StatisticsDays")
	public String getStatisticsDays(HttpServletRequest request) {

		try {			
			statisticsDayService.addStatisticsDayAuto();
		} catch (Exception e) {
			logger.error("AdminMemberController.getStatisticsDays--会员记录列表出错", e);
			return setExceptionRequest(request, e);
		}
		return "";
	}

	/**
	 * 注册统计信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/register")
	public ModelAndView getRegisterStatistics(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(registerChart);
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy");
			String month = request.getParameter("month");// 月份
			String year = request.getParameter("year");// 年
			if (year == null) {
				year = s.format(new Date());
			}
			Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(month, year);
			@SuppressWarnings("unchecked")
			List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
			String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
			String registerNum = "";// 注册数
			for (int i = statistics.size() - 1; i >= 0; i--) {
				registerNum += statistics.get(i).getRegisterNum() + ",";
			}
			if (registerNum.length() > 0) {
				registerNum = registerNum.substring(0, registerNum.length() - 1);
			}

			modelAndView.addObject("chart", showDate + "|" + registerNum);// 绘图数据
			modelAndView.addObject("statistics", statistics);// 表数据
			modelAndView.addObject("month", month);
			modelAndView.addObject("year", year);
		} catch (Exception e) {
			logger.error("StatisticsDayController.getRegisterStatistics" + e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 登录统计（活跃人数）信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/login")
	public ModelAndView getLoginStatistics(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(loginChart);
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy");
			String month = request.getParameter("month");// 月份
			String year = request.getParameter("year");// 年
			if (year == null) {
				year = s.format(new Date());
			}
			Map<String, Object> sourceMap = statisticsDayService
					.getStatisticsMsg(month, year);
			@SuppressWarnings("unchecked")
			List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap
					.get("statisticsDayList");
			String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
			String loginNum = "";// 登录数
			for (int i = statistics.size() - 1; i >= 0; i--) {
				loginNum += statistics.get(i).getLoginNum() + ",";
			}
			if (loginNum.length() > 0) {
				loginNum = loginNum.substring(0, loginNum.length() - 1);
			}

			modelAndView.addObject("chart", showDate + "|" + loginNum);// 绘图数据
			modelAndView.addObject("statistics", statistics);// 表数据
			modelAndView.addObject("month", month);
			modelAndView.addObject("year", year);
		} catch (Exception e) {
			logger.error("StatisticsDayController.getLoginStatistics" + e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 社区统计（活跃人数）信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/sns")
	public ModelAndView getSnsStatistics(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(snsChart);
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy");

			String year = request.getParameter("year");// 年
			if (year == null) {
				year = s.format(new Date());
			}
			Map<String, Object> sourceMap = statisticsDayService
					.getStatisticsMsg(null, year);
			@SuppressWarnings("unchecked")
			List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap
					.get("statisticsDayList");
			Object[] weiboNum = new Object[12];// 发表观点数
			Object[] blogNum = new Object[12];// 博文数
			Object[] assesNum = new Object[12];// 课程评论数
			Object[] quesNum = new Object[12];// 问题数
			for (int i = statistics.size() - 1; i >= 0; i--) {
				weiboNum[statistics.size() - 1 - i] = statistics.get(i)
						.getWeiboNum();
				blogNum[statistics.size() - 1 - i] = statistics.get(i)
						.getBlogNum();
				assesNum[statistics.size() - 1 - i] = statistics.get(i)
						.getAssesNum();
				quesNum[statistics.size() - 1 - i] = statistics.get(i)
						.getQuesNum();

			}
			modelAndView.addObject(
					"chart",
					gson.toJson(weiboNum) + "|" + gson.toJson(blogNum) + "|"
							+ gson.toJson(assesNum) + "|"
							+ gson.toJson(quesNum));// 绘图数据
			modelAndView.addObject("statistics", statistics);// 表数据
			modelAndView.addObject("year", year);
		} catch (Exception e) {
			logger.error("StatisticsDayController.getSnsStatistics" + e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 课程订单统计信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/order/course")
	public ModelAndView getCourseOrderStatistics(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(courseOrderChart);
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy");
			String month = request.getParameter("month");// 月份
			String year = request.getParameter("year");// 年
			if (year == null) {
				year = s.format(new Date());
			}
			Map<String, Object> sourceMap = statisticsDayService
					.getStatisticsMsg(month, year);
			@SuppressWarnings("unchecked")
			List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap
					.get("statisticsDayList");
			String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
			// 数据整理
			String allOrderNum = "";// 订单数
			String payOrderNum = "";
			String amountNum = "";
			for (int i = statistics.size() - 1; i >= 0; i--) {
				allOrderNum += statistics.get(i).getCourseNum() + ",";
				payOrderNum += statistics.get(i).getCoursePayNum() + ",";
				amountNum += statistics.get(i).getCoursePayAmount() + ",";
			}
			if (allOrderNum.length() > 0) {
				allOrderNum = allOrderNum
						.substring(0, allOrderNum.length() - 1);
			}
			if (payOrderNum.length() > 0) {
				payOrderNum = payOrderNum
						.substring(0, payOrderNum.length() - 1);
			}
			if (amountNum.length() > 0) {
				amountNum = amountNum.substring(0, amountNum.length() - 1);
			}
			modelAndView.addObject("chart", showDate + "|" + allOrderNum + "|"
					+ payOrderNum + "|" + amountNum);// 绘图数据
			modelAndView.addObject("statistics", statistics);// 表数据
			modelAndView.addObject("month", month);
			modelAndView.addObject("year", year);
		} catch (Exception e) {
			logger.error("StatisticsDayController.getCourseOrderStatistics" + e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 网站概况
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/web/detail")
	public ModelAndView getWebStatistics(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(webChart);
		try {
			modelAndView.addObject("userName",
					SingletonLoginUtils.getSysLoginLoginName(request));
			// 今天数据获得。总数据获得
			int todayloginnum = statisticsDayService
					.getTodayLoginNum(new Date());
			int todayOrderNum = statisticsDayService.getStatisticalNumByType(
					new Date(), StatisticsQueryType.ORDER_NUM.toString());
			int todayKpointNum = statisticsDayService.getStatisticalNumByType(
					new Date(), StatisticsQueryType.KPOINT_NUM.toString());
			int todayRegisterNum = statisticsDayService
					.getStatisticalNumByType(new Date(),
							StatisticsQueryType.REGISTER_NUM.toString());
			int todayAssesNum = statisticsDayService.getStatisticalNumByType(
					new Date(), StatisticsQueryType.ASSES_NUM.toString());
			modelAndView.addObject("todayloginnum", todayloginnum);// 今天登录人数
			modelAndView.addObject("todayOrderNum", todayOrderNum);// 今日订单数
			modelAndView.addObject("todayKpointNum", todayKpointNum);// 今日新增课时数
			modelAndView.addObject("todayRegisterNum", todayRegisterNum);// 今日新增会员数
			modelAndView.addObject("todayAssesNum", todayAssesNum);// 今日新增评论数
			// 今天数据获得。总数据获得
			// 获取总课时
			int kpointNum = statisticsDayService.getStatisticsKpoint();
			modelAndView.addObject("kpointNum", kpointNum);// 总课时
			DecimalFormat df = new java.text.DecimalFormat("#.0");// 保留百分比一位小数
			DecimalFormat df1 = new java.text.DecimalFormat("#");
			StatisticsDay statistics = statisticsDayService
					.getStatisticsSumMsg();
			// 历史数据
			modelAndView.addObject("historyRegisterNum",
					statistics.getRegisterNum());// 历史注册会员数
			modelAndView.addObject("historyAssesNum", statistics.getAssesNum());// 历史课程评论数
			modelAndView.addObject("historyQuesNum", statistics.getQuesNum());// 历史互动答疑数
			// modelAndView.addObject("historyRegisterNum",statistics.getRegisterNum());//历史网站课时总数
			Long userPayCourseNum = statistics.getUserPayCourseNum();// 购买课程人数
			Double userNum = Double.parseDouble(Long.toString(statistics
					.getRegisterNum()));// 学员总数
			String userPayMemberChart = "";
			String userPayCourseChart = "";
			Object[][] arry = new Object[2][2];
			if (userNum > 0) {
				userPayMemberChart = gson.toJson(arry).toString();

				// 购买课程人数饼状图
				float coursePersent = Float.parseFloat(df
						.format((userPayCourseNum / userNum) * 100));
				float noCoursePersent = 100 - coursePersent;
				arry[0][0] = "购买课程：" + userPayCourseNum + "人";
				arry[0][1] = coursePersent;
				arry[1][0] = "未购买课程：" + df1.format(userNum - userPayCourseNum)
						+ "人";
				arry[1][1] = Float.parseFloat(df.format(noCoursePersent));
				userPayCourseChart = gson.toJson(arry).toString();
			}
			modelAndView.addObject("userPayMemberChart", userPayMemberChart);// 绘图数据
			modelAndView.addObject("userPayCourseChart", userPayCourseChart);// 绘图数据
			// 课程订单饼状图
			String courseOrderChart = "";
			Long courseOrderPayNum = statistics.getCoursePayNum();// 课程订单支付总数
			Double courseOrderNum = Double.parseDouble(Long.toString(statistics
					.getCourseNum()));// 课程订单总数
			if (courseOrderNum > 0) {
				float courseOrderPayPersent = Float.parseFloat(df
						.format((courseOrderPayNum / courseOrderNum) * 100));
				float noCourseOrderPayPersent = 100f - courseOrderPayPersent;
				arry[0][0] = "支付成功订单：" + courseOrderPayNum + "笔";
				arry[0][1] = courseOrderPayPersent;
				arry[1][0] = "未支付订单："
						+ df1.format(courseOrderNum - courseOrderPayNum) + "笔";
				arry[1][1] = Float.parseFloat(df
						.format(noCourseOrderPayPersent));
				courseOrderChart = gson.toJson(arry).toString();
			}
			modelAndView.addObject("courseOrderNum", courseOrderNum);// 订单总数
			modelAndView.addObject("courseOrderPayNum", courseOrderPayNum);
			modelAndView.addObject("courseOrderChart", courseOrderChart);// 绘图数据

		} catch (Exception e) {
			logger.error("StatisticsDayController.getWebStatistics" + e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}


		return modelAndView;
	}

	/**
	 * 最近统计查询
	 * 
	 * @param request
	 * @param days
	 * @param type
	 * @return
	 */
	@RequestMapping("/statistics/web/detailajax")
	@ResponseBody
	public Map<String, Object> getWebStatisticsAjax(HttpServletRequest request,
			@RequestParam int days, @RequestParam String type) {
		try {
			if (days > 0) {// 历史的
				List<StatisticsDay> loginStatistics = statisticsDayService.getStatisticThirty(days);
				String statisticalNum = "";// 登录数--统计数（包括三中）
				Calendar c = Calendar.getInstance();
				Object[] showDate = new Object[days];// 时间轴
				for (int i = loginStatistics.size() - 1; i >= 0; i--) {
					c.setTime(loginStatistics.get(i).getStatisticsTime());
					if (days > 15) {
						showDate[loginStatistics.size() - 1 - i] = c.get(Calendar.DAY_OF_MONTH);
					} else {
						showDate[loginStatistics.size() - 1 - i] = (c.get(Calendar.MONTH) + 1)+ "月"+ c.get(Calendar.DAY_OF_MONTH);
					}
					if (type.equals(StatisticsQueryType.LOGIN_NUM.toString())) {
						statisticalNum += loginStatistics.get(i).getLoginNum()+ ",";
					} else if (type.equals(StatisticsQueryType.REGISTER_NUM.toString())) {
						statisticalNum += loginStatistics.get(i).getRegisterNum() + ",";
					} else if (type.equals(StatisticsQueryType.COURSE_NUM.toString())) {
						statisticalNum += loginStatistics.get(i).getCourseNum()+ ",";
					}
				}

				if (statisticalNum.length() > 0) {
					statisticalNum = statisticalNum.substring(0,statisticalNum.length() - 1);
				}
				this.setJson(true, statisticalNum, gson.toJson(showDate)
						.toString());
			} else {// 今天的

			}

		} catch (Exception e) {
			logger.error("StatisticsDayController.getWebStatisticsAjax" + e);
			this.setJson(false, "", null);
		}
		return json;
	}

	/**
	 * 生成指定时间段的统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/create/batch")
	@ResponseBody
	public Map<String, Object> createStatisticsByDate(HttpServletRequest request) {
		try {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			List<StatisticsDay> statistics = statisticsDayService
					.getStatisticsByDate(startTime, endTime);
			if (statistics.size() > 0) {// 时间段内已有数据
				this.setJson(true, "exists", null);
				return json;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dBegin = sdf.parse(startTime);
			Date dEnd = sdf.parse(endTime);	
			statisticsDayService.createStatisticsByDate(dBegin, dEnd);
			this.setJson(true, "true", null);
		} catch (Exception e) {
			logger.error("StatisticsDayController.createStatisticsByDate" + e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 删除指定时间段的统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/del/batch")
	@ResponseBody
	public Map<String, Object> delStatisticsByDate(HttpServletRequest request) {
		try {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			statisticsDayService.delStatisticsByDate(startTime, endTime);
			this.setJson(true, "true", null);
		} catch (Exception e) {
			logger.error("StatisticsDayController.createStatisticsByDate" + e);
			this.setJson(false, "error", null);
		}
		return json;
	}

	
	/**
	 * 获得社区开关是否开启
	 * @return
	 */
	public Map<String, Object> getVerifySnsKeyword() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> keywordmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());			
			if (ObjectUtils.isNotNull(keywordmap)) {
				JsonObject jsonObject = jsonParser.parse(gson.toJson(keywordmap.get(WebSiteProfileType.keyword.toString()))).getAsJsonObject();			
				if (ObjectUtils.isNotNull(jsonObject)&& StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					map.put("verifySns", jsonObject.get("verifySns").getAsString());
				}
			}
		} catch (Exception e) {
			logger.error("getVerifySnsKeyword", e);
		}
		return map;
	}
	
	/**
	 * 班课课程统计信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/course/class")
	public ModelAndView statisticsClassCourse(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView(courseClassStatustics);
		try {
			this.setPage(page);
			this.getPage().setPageSize(12);
			
			String queryType=request.getParameter("queryType");//查询条件方式(本月 上月、。。。)
			String orderBy=request.getParameter("orderBy");//排序方式
			Map<String,Object> query=new HashMap<>();
			//排序方式默认销售量倒序
			if(StringUtils.isEmpty(orderBy)){
				orderBy="countDesc";
			}
			
			//自定义时间段查询
			if(StringUtils.isNotEmpty(queryType)&& queryType.equals("other")){
				String startDate=request.getParameter("startDate");
				String endDate=request.getParameter("endDate");
				if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
					query.put("startDate", startDate);
					query.put("endDate", endDate);
					model.addObject("startDate",startDate);
					model.addObject("endDate",endDate);
				}
			}
			
			query.put("queryType",queryType);
			query.put("sellType",2);//班课课程
			query.put("orderBy",orderBy);//倒序
			List<Map<String,Object>> courseStatisticsList=statisticsDayService.queryStatisticsCourseByCondition(query,page);
			model.addObject("courseStatisticsList", courseStatisticsList);
			model.addObject("queryType", queryType);
			model.addObject("orderBy",orderBy);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("statisticsClassCourse---error", e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}
	/**
	 * 一对一课程信息统计
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/statistics/course/onetoone")
	public ModelAndView statisticsOneToOneCourse(HttpServletRequest request,@ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView(courseOneToOneStatustics);
		try {
			this.setPage(page);
			this.getPage().setPageSize(12);
			
			String queryType=request.getParameter("queryType");//查询条件方式(本月 上月、。。。)
			String orderBy=request.getParameter("orderBy");//排序方式
			Map<String,Object> query=new HashMap<>();
			//排序方式默认销售量倒序
			if(StringUtils.isEmpty(orderBy)){
				orderBy="lessionDesc";
			}
			
			//自定义时间段查询
			if(StringUtils.isNotEmpty(queryType)&& queryType.equals("other")){
				String startDate=request.getParameter("startDate");
				String endDate=request.getParameter("endDate");
				if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
					query.put("startDate", startDate);
					query.put("endDate", endDate);
					model.addObject("startDate",startDate);
					model.addObject("endDate",endDate);
				}
			}
			
			query.put("queryType",queryType);
			query.put("sellType",1);//一对一课程
			query.put("orderBy",orderBy);//倒序
			List<Map<String,Object>> courseStatisticsList=statisticsDayService.queryStatisticsCourseByCondition(query,page);
			model.addObject("courseStatisticsList", courseStatisticsList);
			model.addObject("queryType", queryType);
			model.addObject("orderBy",orderBy);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("statisticsOneToOneCourse---error", e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}
	
	/**
	 * 发布课程  入驻教师人数统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/courseAndTeacher")
	public ModelAndView courseAndTeacher(HttpServletRequest request){
		ModelAndView model=new ModelAndView(courseAndTeacher);
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy");
			String month = request.getParameter("month");// 月份
			String year = request.getParameter("year");// 年
			if (year == null) {
				year = s.format(new Date());
			}
			Map<String, Object> sourceMap = statisticsDayService.getStatisticsMsg(month, year);
			@SuppressWarnings("unchecked")
			List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap.get("statisticsDayList");
			String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
			String coursePubNum = "";// 发布课程数
			for (int i = statistics.size() - 1; i >= 0; i--) {
				coursePubNum += statistics.get(i).getCoursePubNum() + ",";
			}
			if (coursePubNum.length() > 0) {
				coursePubNum = coursePubNum.substring(0, coursePubNum.length() - 1);
			}
			String teacherNum = "";// 讲师入驻数
			for (int i = statistics.size() - 1; i >= 0; i--) {
				teacherNum += statistics.get(i).getTeacherNum() + ",";
			}
			if (teacherNum.length() > 0) {
				teacherNum = teacherNum.substring(0, teacherNum.length() - 1);
			}

			model.addObject("chart", showDate + "|" + coursePubNum+"|"+teacherNum);// 绘图数据
//			model.addObject("statistics", statistics);// 表数据
			model.addObject("month", month);
			model.addObject("year", year);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("StatisticsDayController.courseAndTeacher--error",e);
			model.setViewName(setExceptionRequest(request, e));
		}
		return model;
	}
	
	/**
	 * 课程订单统计信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/statistics/cashout")
	public ModelAndView getCashoutStatistics(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(cashOutChart);
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy");
			String month = request.getParameter("month");// 月份
			String year = request.getParameter("year");// 年
			if (year == null) {
				year = s.format(new Date());
			}
			Map<String, Object> sourceMap = statisticsDayService
					.getStatisticsMsg(month, year);
			@SuppressWarnings("unchecked")
			List<StatisticsDay> statistics = (List<StatisticsDay>) sourceMap
					.get("statisticsDayList");
			String showDate = (String) sourceMap.get("showDate");// 统计js的X轴的日期显示
			// 数据整理
			String allUserCashOutNum = "";// 订单数
			String amountNum = "";
			for (int i = statistics.size() - 1; i >= 0; i--) {
				allUserCashOutNum += statistics.get(i).getUserCashOutNum() + ",";
				amountNum += statistics.get(i).getUserCashOutAmount() + ",";
			}
			if (allUserCashOutNum.length() > 0) {
				allUserCashOutNum = allUserCashOutNum
						.substring(0, allUserCashOutNum.length() - 1);
			}
			if (amountNum.length() > 0) {
				amountNum = amountNum.substring(0, amountNum.length() - 1);
			}
			modelAndView.addObject("chart", showDate + "|" + allUserCashOutNum  + "|" + amountNum);// 绘图数据
			modelAndView.addObject("statistics", statistics);// 表数据
			modelAndView.addObject("month", month);
			modelAndView.addObject("year", year);
		} catch (Exception e) {
			logger.error("StatisticsDayController.getCashoutStatistics" + e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}
}