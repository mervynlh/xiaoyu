package com.yizhilu.os.edu.service.impl.teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.entity.teacher.TeacherClassHour;
import com.yizhilu.os.edu.dao.teacher.TeacherClassHourDao;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.teacher.TeacherClassHourService;
/**
 * TeacherClassHour管理接口
 * User: qinggang.liu
 * Date: 2015-08-13
 */
@Service("teacherClassHourService")
public class TeacherClassHourServiceImpl implements TeacherClassHourService{

 	@Autowired
    private TeacherClassHourDao teacherClassHourDao;
 	@Autowired
 	private TeacherService teacherService;
 	
 	private static final int FIRST_DAY = Calendar.MONDAY;
 	
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 	
    /**
     * 添加TeacherClassHour
     * @param classHour
     * @return id
     * @throws ParseException 
     */
    public String addTeacherClassHour(String classHour, Long teacherId) throws ParseException{
    	String message = "";
    	List<TeacherClassHour> list = new ArrayList<TeacherClassHour>();
    	String[] _array = classHour.split("DAY");
    	// 去除选择中的重复数据，防止课时发布错误
    	TreeSet<String> tr = new TreeSet<String>();
    	for (int i = 0; i < _array.length; i++) {
    		int num = 0;
    		for (int j = 0; j < _array.length; j++) {
				if (_array[i].equalsIgnoreCase(_array[j])) {
					num++;
				}
			}
    		if (num % 2 != 0) {
    			tr.add(_array[i]);
			}
		}
    	//从TreeSet中取出元素重新赋给数组
    	String[] array= new String[tr.size()];
    	for(int i = 0; i < array.length; i++){
    		array[i] = tr.pollFirst();
   		}
    	if (array.length != 0) {
    		for (int i = 0; i < array.length; i++) {
        		TeacherClassHour teacher = new TeacherClassHour();
        		teacher.setTeacherId(teacherId);
        		String[] date = array[i].split(" ");
        		teacher.setDateDay(sdf.parse(date[0])); // 设置日期
        		teacher.setTime(date[1]); // 设置时间段
        		TeacherClassHour flag = checkAddOrUpdate(date[0], date[1], teacherId);
        		if (ObjectUtils.isNull(flag) || flag == null) { // 如果返回 true，添加
        			teacher.setStatus(2);// 设置状态为2  已发布
        			teacher.setUserId(0L); // 默认为0
            		list.add(teacher);
    			} else { // 返回false则修改
    				if (ObjectUtils.isNull(flag.getUserId()) || flag.getUserId().intValue() == 0) {
    					if (flag.getStatus() == 1 || flag.getStatus() == 0) {
    						flag.setStatus(2);
    					} else if (flag.getStatus() == 2) {
    						flag.setStatus(1);
    					}
    					updateTeacherClassHourStatus(flag); // 未被预约  可修改
    				} else {
    					message = date[0] + " " + date[1] + "已被学生预约，不能取消课时发布状态";
    					return message; // 已被学员预约
    				}
    			}
    		}
		} else {
			message = "您未对课程做出任何修改！";
		}
    	if (list.size() > 0) {
    		Map<String, Object> map = new HashMap<String, Object>();
        	map.put("serial", teacherId.toString().substring(teacherId.toString().length()-1));
        	map.put("list", list);
        	teacherClassHourDao.addTeacherClassHour(map);
		}
    	return message;
    }

    /**
     * 根据id删除一个TeacherClassHour
     * @param id 要删除的id
     */
    public void deleteTeacherClassHourById(Long id, String serial){
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 map.put("serial", serial);
    	 map.put("id", id);
    	 teacherClassHourDao.deleteTeacherClassHourById(map);
    }
    
    /**
     * 删除本月以前的所有TeacherClassHour
     * @param serial 要删除的id
     */
    public void deleteTeacherClassHourByDate(String serial){
    	teacherClassHourDao.deleteTeacherClassHourByDate(serial);
    }

    /**
     * 修改TeacherClassHour
     * @param teacherClassHour 要修改的TeacherClassHour
     */
    public void updateTeacherClassHour(TeacherClassHour teacherClassHour){
    	Map<String, Object> map = new HashMap<String, Object>();
    	String serial = teacherClassHour.getTeacherId().toString().substring(teacherClassHour.getTeacherId().toString().length()-1);
	   	map.put("serial", serial);
	   	map.put("teacherClass", teacherClassHour);
     	teacherClassHourDao.updateTeacherClassHour(map);
    }
    

    /**
     * 根据id获取单个TeacherClassHour对象
     * @param id 要查询的id
     * @return TeacherClassHour
     */
    public TeacherClassHour getTeacherClassHourById(Long id, String serial){
    	Map<String, Object> map = new HashMap<String, Object>();
	   	map.put("serial", serial);
	   	map.put("id", id);
    	return teacherClassHourDao.getTeacherClassHourById( map);
    }

    /**
     * 根据条件获取TeacherClassHour列表
     * @param teacherClassHour 查询条件
     * @return List<TeacherClassHour>
     * @throws ParseException 
     */
    public List<List<TeacherClassHour>> getTeacherClassHourList(TeacherClassHour teacherClassHour) throws ParseException{
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(teacherClassHour.getDateDay());
        // 获得周一的日期
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
        // 设置周一的日期
        teacherClassHour.setMonday(calendar.getTime());
        // 设置周日的日期
        calendar.add(Calendar.DATE, 6);
        teacherClassHour.setSunday(calendar.getTime());
    	Map<String, Object> map = new HashMap<String, Object>();
    	String serial = teacherClassHour.getTeacherId().toString().substring(teacherClassHour.getTeacherId().toString().length()-1);
	   	map.put("serial", serial);
	   	map.put("teachreClass", teacherClassHour);
	   	// 查询结果
	   	List<TeacherClassHour> list = teacherClassHourDao.getTeacherClassHourList(map);
	   	List<List<TeacherClassHour>> result = new ArrayList<List<TeacherClassHour>>();
	   	// 获得指定日期所在周的日期
        List<String> dateList = new ArrayList<String>();
        calendar.setTime(teacherClassHour.getMonday());
        for (int i = 0; i < 7; i++) {
        	dateList.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
		List<TeacherClassHour> newList;
		// 遍历一周日期
		for (String date : dateList) {
			newList = new ArrayList<TeacherClassHour>();
			// 添加每天的授课时间安排为初始化状态
			newList.addAll(getDayTimes(sdf.parse(date), teacherClassHour.getTeacherId()));
			// 已遍历修改过的list集合
			List<TeacherClassHour> clearList = new ArrayList<TeacherClassHour>();
			// 遍历查询结果返回的数据
			for (TeacherClassHour classHour : list) {
				// 如果结果集中的数据与当前遍历的日期相同
				if (sdf.format(classHour.getDateDay()).equals(date)){
					// 修改当前日期的课时安排数据
					newList = updateDayTimes(newList, classHour);
					// 将其放到集合中
					clearList.add(classHour);
				} else {
					// 不相等则直接跳出循环
					break;
				}
			}
			// 结果集中清空遍历过的数据，避免重复遍历
			list.removeAll(clearList);
			// 将一天的新的结果数据添加到结果集中并且返回
			result.add(newList);
		}
    	return result;
    }

	/**
	 * 班课课时安排验证，检查是否可以在该时间段安排班课
	 * @throws ParseException 
	 */
	public List<TeacherClassHour> getTeacherClassHourByCondition(Date starDate, Date endDate, Long teacherId) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDay = sdf.parse(sdf.format(starDate));
		// 格式化时间段
		sdf = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(starDate);
		String str = sdf.format(calendar.getTime());
		// 查询终止时间
		String str_2 = sdf.format(endDate);
		// 时间段集合   如：06:00-07:00, 07:00-08:00, 08:00-09:00
		List<String> times = new ArrayList<String>();
		while (!str.equals(str_2)) {
			str = sdf.format(calendar.getTime());
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			String str_1 = sdf.format(calendar.getTime());
			times.add(str + "-" + str_1);
			str = sdf.format(calendar.getTime());
		}
		map.put("dateDay", dateDay);
		map.put("teacherId", teacherId);
		map.put("list", times);
		map.put("status", 1); // 添加状态查询，1为一对一课程为发布状态，使之查询时不查询出
		map.put("serial", teacherId.toString().substring(teacherId.toString().length() - 1));
		return teacherClassHourDao.getTeacherClassHourByCondition(map);
	}

	/**
	 * 教师添加发布班课时间
	 * @param starDate
	 * @param endDate
	 * @param teacherId
	 * @throws ParseException
	 */
	public void classCourseHourAdd(Date starDate, Date endDate, Long teacherId) throws ParseException{
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateDay = sdf.parse(sdf.format(starDate));
		// 格式化时间段
		sdf = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(starDate);
		String str = sdf.format(calendar.getTime());
		// 查询终止时间
		String str_2 = sdf.format(endDate);
		// 时间段集合   如：06:00-07:00, 07:00-08:00, 08:00-09:00
		List<TeacherClassHour> list = new ArrayList<TeacherClassHour>();
		while (!str.equals(str_2)) {
			str = sdf.format(calendar.getTime());
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			String str_1 = sdf.format(calendar.getTime());

			TeacherClassHour teacherClassHour = new TeacherClassHour();
			teacherClassHour.setDateDay(dateDay);
			teacherClassHour.setTime(str + "-" + str_1);
			teacherClassHour.setStatus(5);
			teacherClassHour.setTeacherId(teacherId);
			teacherClassHour.setUserId(0L);
			list.add(teacherClassHour);

			str = sdf.format(calendar.getTime());
		}
		map.put("serial", teacherId.toString().substring(teacherId.toString().length()-1));
		map.put("list", list);
		teacherClassHourDao.addTeacherClassHour(map);
	}

	/**
	 * 检查是添加还是修改
	 * @return
	 */
	public TeacherClassHour checkAddOrUpdate(String dateDay, String time, Long teacherId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> times = new ArrayList<String>();
		times.add(time);
		map.put("dateDay", dateDay);
		map.put("teacherId", teacherId);
		map.put("list", times);
		map.put("serial", teacherId.toString().substring(teacherId.toString().length() - 1));
		List<TeacherClassHour> list = teacherClassHourDao.getTeacherClassHourByCondition(map);
		if (ObjectUtils.isNull(list) || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 教师修改课时安排
	 */
	public void updateTeacherClassHourStatus(TeacherClassHour teacherClassHour) {
		Map<String, Object> map = new HashMap<String, Object>();
    	String serial = teacherClassHour.getTeacherId().toString().substring(teacherClassHour.getTeacherId().toString().length()-1);
	   	map.put("serial", serial);
	   	map.put("teachreClass", teacherClassHour);
     	teacherClassHourDao.updateTeacherClassHourStatus(map);
	}
	
	// 获得每天的时间段 (06:00 - 24:00)
	public List<String> getTimesList() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar calendar = Calendar.getInstance();
		// 一天开始时间点
		String str = "06:00";
		calendar.setTime(sdf.parse(str));
		// 查询终止时间
		String str_2 = "00:00";
		List<String> times = new ArrayList<String>();
		while (!str.equals(str_2)) {
			str = sdf.format(calendar.getTime());
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			String str_1 = sdf.format(calendar.getTime());
			times.add(str + "-" + str_1);
			str = sdf.format(calendar.getTime());
		}
		return times;
	}

	/**
	 * 获得每一天的时间段信息集合
	 * @param day
	 * 				当前日期
	 * @param teacherId
	 * 				教师ID编号
	 * @return List<TeacherClassHour>
	 * 				每一天的时间段集合结果
	 * @throws ParseException
	 */
	public List<TeacherClassHour> getDayTimes(Date day, Long teacherId) throws ParseException{
		List<TeacherClassHour> teacherList = new ArrayList<TeacherClassHour>();
		List<String> timesList = getTimesList();
		// 遍历时间段集合
		for (int i = 0; i < timesList.size(); i++) {
			// 添加新的 TeacherClassHour 对象
			TeacherClassHour teacher = new TeacherClassHour();
			teacher.setDateDay(day); // 设置日期
			teacher.setTime(timesList.get(i)); // 设置时间段
			teacher.setStatus(0); // 设置状态
			teacher.setUserId(0L); // 设置用户ID
			teacher.setTeacherId(teacherId); // 教师ID
			teacherList.add(teacher);
		}
		return teacherList;
	}
	
	/**
	 * 数据库返回多个相同日期结果时，则修改
	 * @param teacherClass
	 * 				原始当前日期的时间段信息集合
	 * @param teacher
	 * 				要修改的时间段信息
	 * @return
	 * @throws ParseException
	 */
	public List<TeacherClassHour> updateDayTimes(List<TeacherClassHour> teacherClass, TeacherClassHour teacher) throws ParseException{
		// 遍历原始日期的每个时间段信息
		for (TeacherClassHour teacherClassHour : teacherClass) {
			// 修改相同时间段的信息
			if (teacher.getTime().equals(teacherClassHour.getTime())) { 
				teacherClassHour.setStatus(teacher.getStatus());
				teacherClassHour.setUserId(teacher.getUserId());
				teacherClassHour.setDateDay(teacher.getDateDay());
				teacherClassHour.setEmail(teacher.getEmail());
				teacherClassHour.setId(teacher.getId());
				teacherClassHour.setMobile(teacher.getMobile());
				teacherClassHour.setNickname(teacher.getNickname());
				teacherClassHour.setTeacherId(teacher.getTeacherId());
			} 
		}
		// 返回原始结果集
		return teacherClass;
	}

	/**
	 * 学生约课
	 * @param classHour
	 * @param teacherId
	 * @param userId
	 * @return
	 * @throws ParseException
	 */
	public String updateStudentConsultClass(String classHour, Long teacherId, Long userId) throws ParseException {
		String message = "";
    	String[] _array = classHour.split("DAY");
    	if (_array.length != 0) {
    		for (int i = 0; i < _array.length; i++) {
        		TeacherClassHour teacher = new TeacherClassHour();
        		teacher.setTeacherId(teacherId);
        		String[] date = _array[i].split(" ");
        		teacher.setDateDay(sdf.parse(date[0])); // 设置日期
        		teacher.setTime(date[1]); // 设置时间段
        		TeacherClassHour flag = checkAddOrUpdate(date[0], date[1], teacherId);
        		if (ObjectUtils.isNull(flag)) {
        			message = "数据错误，该教师 " + date[0] + " " + date[1] + "未发布课时";
				} else {
					if (ObjectUtils.isNull(flag.getUserId()) || flag.getUserId().intValue() == 0) {
						if (flag.getStatus() == 2) {
							teacher.setStatus(3);
							teacher.setUserId(userId);
							updateTeacherClassHour(teacher); // 未被预约  可修改
						} else {
							message = "操作错误，请联系管理员！";
						}
					} else {
						message = date[0] + " " + date[1] + "已被学生预约，不能取消课时发布状态";
					}
				}
    		}
		} else {
			message = "您未预约任意时间段上课！";
		}
    	return message;
    }
	 /**
     * 是否可约
     * @param dateValue
     * @return
	 * @throws ParseException 
     */
	public Map<String, Object> checkIfSelect(String classHour,Long teacherId) throws ParseException{
		Map<String,Object> returnMap = new HashMap<String, Object>();
		String[] _array = classHour.split("DAY");
		if (_array.length != 0) {
    		for (int i = 0; i < _array.length; i++) {
        		TeacherClassHour teacher = new TeacherClassHour();
        		teacher.setTeacherId(teacherId);
        		String[] date = _array[i].split(" ");
        		teacher.setDateDay(sdf.parse(date[0])); // 设置日期
        		teacher.setTime(date[1]); // 设置时间段
        		Map<String, Object> map = new HashMap<String, Object>();
        		List<String> times = new ArrayList<String>();
        		times.add(date[1]);
        		map.put("dateDay", date[0]);
        		map.put("teacherId", teacherId);
        		map.put("list", times);
        		map.put("serial", teacherId.toString().substring(teacherId.toString().length() - 1));
        		List<TeacherClassHour> list = teacherClassHourDao.getTeacherClassHourByCondition(map);
        		if(list!=null&&list.size()>0){
        			List<TeacherClassHour> rList = new ArrayList<>();//返回的不能约的时间
        			for (TeacherClassHour teacherClassHour : list) {
						if(teacherClassHour.getStatus()!=2){
							rList.add(teacherClassHour);
						}
					}
        			if(rList!=null&&rList.size()>0){
        				returnMap.put("rList", rList);//不能约课的时间
            			returnMap.put("msg", "noSelect");//不能约课
        			}else{
        				returnMap.put("msg", "true");//都能约课
        			}
        			
        		}else{
        			returnMap.put("msg", "data_error");//数据错误
        		}
    		}
		} else {
			returnMap.put("msg", "error");
		}
		return returnMap;
	}
}