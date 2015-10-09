package com.yizhilu.os.edu.service.impl.course;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.service.user.UserMobileMsgService;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.constants.web.SendMsgConstans;
import com.yizhilu.os.edu.dao.course.TeacherDao;
import com.yizhilu.os.edu.dao.teacher.TeacherProfileDao;
import com.yizhilu.os.edu.entity.course.QueryTeacher;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.major.SubjectMajor;
import com.yizhilu.os.edu.entity.teacher.TeacherMajor;
import com.yizhilu.os.edu.entity.teacher.TeacherProfile;
import com.yizhilu.os.edu.entity.teacher.TeacherSubject;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.teacher.TeacherMajorService;
import com.yizhilu.os.edu.service.teacher.TeacherSubjectService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * Teacher管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private TeacherMajorService teacherMajorService;
	@Autowired
	private TeacherSubjectService teacherSubjectService;
	@Autowired
	private TeacherProfileDao teacherProfileDao;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private UserService userService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private UserMobileMsgService userMobileMsgService;
	
	private MemCache memCache = MemCache.getInstance();

	/**
	 * 添加Teacher
	 * 
	 * @param teacher
	 *            要添加的Teacher
	 * @return id
	 */
	public java.lang.Long addTeacher(Teacher teacher) {
		teacher.setCreateTime(new Date());// 创建时间
		teacher.setStatus(-1L); // 教师未完善资料
		teacher.setSoft(0L); // 排序0
		teacher.setSex(0); // 性别  默认 0 男
		teacher.setIsProfessor(1); // 韩教授认证为 0否
		teacher.setSeniority(0L);
		return teacherDao.addTeacher(teacher);
	}

	/**
	 * 根据id删除一个Teacher
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTeacherById(Long id) {
		teacherDao.deleteTeacherById(id);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
		memCache.remove(MemConstans.TEACHER_ALL);
	}

	/**
	 * 后台修改Teacher
	 * 
	 * @param teacher
	 *            要修改的Teacher
	 */
	public void updateTeacherAdmin(Teacher teacher) {
		// 修改教师教授阶段
//		String teacherSubjectIds = teacher.getTeacherSubject();
//		String[] subjectIds = teacherSubjectIds.trim().split(",");
//		List<TeacherSubject> teacherSubjectList = new ArrayList<TeacherSubject>();
//		for (int i = 0; i < subjectIds.length; i++) {
//			if (StringUtils.isNotEmpty(subjectIds[i]) && subjectIds[i] != null && !subjectIds[i].equals("")) {
//				TeacherSubject teacherSubject = new TeacherSubject();
//				teacherSubject.setSubjectId(Long.parseLong(subjectIds[i]));
//				teacherSubject.setTeacherId(teacher.getId());
//				teacherSubjectList.add(teacherSubject);
//			}
//		}
//		teacherSubjectService.addTeacherSubjectBatch(teacherSubjectList);
//
//		// 修改教师教授科目
//		String teacherMajors = teacher.getTeacherMajor();
//		String[] majorIds = teacherMajors.trim().split(",");
//		List<TeacherMajor> teacherMajorList = new ArrayList<TeacherMajor>();
//		for (int i = 0; i < majorIds.length; i++) {
//			if (StringUtils.isNotEmpty(majorIds[i]) && majorIds[i] != null && !majorIds[i].equals("")) {
//				TeacherMajor teacherMajor = new TeacherMajor();
//				teacherMajor.setMajorId(Long.parseLong(majorIds[i]));
//				teacherMajor.setTeacherId(teacher.getId());
//				teacherMajorList.add(teacherMajor);
//			}
//		}
//		teacherMajorService.addTeacherMajorBatch(teacherMajorList);
		UserExpand user = userExpandService.getUserExpandByUserId(teacher.getUserId());
		user.setAvatar(teacher.getPicPath());
		user.setRealname(teacher.getName());
		user.setUserInfo(teacher.getCareer());
		userExpandService.updateUserExpand(user);
		// 修改教师资质认证状态
		TeacherProfile teacherProfile = teacherProfileDao.queryTeacherProfileByTeacher(teacher.getId());
		teacherProfile.setCardStatus(teacher.getCardStatus());
		teacherProfile.setEducationStatus(teacher.getEducationStatus());
		teacherProfile.setTeachingStatus(teacher.getTeachingStatus());
		teacherProfile.setSpecialtyStatus(teacher.getSpecialtyStatus());
		teacherProfileDao.teacherAptitudeAttestation(teacherProfile);
		// 修改教师
		teacherDao.updateTeacher(teacher);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
		memCache.remove(MemConstans.TEACHER_ALL);
	}

	/**
	 * 前台修改Teacher基本信息
	 *
	 * @param teacher
	 *            要修改的Teacher
	 */
	public void updateTeacherBaseInfo(Teacher teacher) throws Exception{
		// 修改教师教授阶段
		String teacherSubjectIds = teacher.getTeacherSubject();
		String[] subjectIds = teacherSubjectIds.trim().split(",");
		List<TeacherSubject> teacherSubjectList = new ArrayList<TeacherSubject>();
		for (int i = 0; i < subjectIds.length; i++) {
			if (StringUtils.isNotEmpty(subjectIds[i]) && subjectIds[i] != null && !subjectIds[i].equals("")) {
				TeacherSubject teacherSubject = new TeacherSubject();
				teacherSubject.setSubjectId(Long.parseLong(subjectIds[i]));
				teacherSubject.setTeacherId(teacher.getId());
				teacherSubjectList.add(teacherSubject);
			}
		}
		teacherSubjectService.addTeacherSubjectBatch(teacherSubjectList);

		// 修改教师教授科目
		String teacherMajors = teacher.getTeacherMajor();
		String[] majorIds = teacherMajors.trim().split(",");
		List<TeacherMajor> teacherMajorList = new ArrayList<TeacherMajor>();
		for (int i = 0; i < majorIds.length; i++) {
			if (StringUtils.isNotEmpty(majorIds[i]) && majorIds[i] != null && !majorIds[i].equals("")) {
				TeacherMajor teacherMajor = new TeacherMajor();
				teacherMajor.setMajorId(Long.parseLong(majorIds[i]));
				teacherMajor.setTeacherId(teacher.getId());
				teacherMajorList.add(teacherMajor);
			}
		}
		teacherMajorService.addTeacherMajorBatch(teacherMajorList);
		Teacher tea = getTeacherByUserId(teacher.getUserId());
		// 修改教师拓展信息
		UserExpand userExpand = userExpandService.getUserExpandByUserId(tea.getUserId());
		userExpand.setRealname(teacher.getUserExpand().getRealname());
		userExpand.setGender(teacher.getSex());
		userExpand.setBirthday(teacher.getUserExpand().getBirthday());
		userExpand.setAge(getAge(teacher.getUserExpand().getBirthday()));
		userExpand.setHideStatus(teacher.getUserExpand().getHideStatus());
		userExpandService.updateUserExpand(userExpand);
		// 修改教师用户表信息
		User user = userService.getUserById(teacher.getUserId());
		user.setNickname(teacher.getUserExpand().getNickname());
		userService.updateUser(user);
		// 修改教师
		tea.setSex(teacher.getSex());
		tea.setFinishSchool(teacher.getFinishSchool());
		tea.setDegree(teacher.getDegree());
		tea.setProfession(teacher.getProfession());
		teacherDao.updateTeacher(tea);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
		memCache.remove(MemConstans.TEACHER_ALL);
	}

	/**
	 * 前台修改Teacher高级信息
	 *
	 * @param teacher
	 *            要修改的Teacher
	 */
	public void updateTeacherSeniorInfo(Teacher teacher){
		Teacher tea = getTeacherByUserId(teacher.getUserId());
		tea.setSeniority(teacher.getSeniority());
		tea.setPeculiarity(teacher.getPeculiarity());
		tea.setTeachingLive(teacher.getTeachingLive());
		tea.setTeachingSuccess(teacher.getTeachingSuccess());
		tea.setTeachingEnounce(teacher.getTeachingEnounce());
		// 修改教师
		teacherDao.updateTeacher(tea);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
		memCache.remove(MemConstans.TEACHER_ALL);
	}

	/**
	 * 前台修改Teacher认证信息
	 *
	 * @param teacher
	 *            要修改的Teacher
	 */
	public String updateTeacherAttestationInfo(Teacher teacher, String type){
		Teacher tea = getTeacherByUserId(teacher.getUserId());
		// 修改教师资质认证状态
		TeacherProfile teacherProfile = teacherProfileDao.queryTeacherProfileByTeacher(teacher.getId());
		if (ObjectUtils.isNull(tea.getCard())) {
			tea.setCard("");
		}
		if (ObjectUtils.isNull(tea.getSpecialty())) {
			tea.setSpecialty("");
		}
		if (ObjectUtils.isNull(tea.getEducation())) {
			tea.setEducation("");
		}
		if (ObjectUtils.isNull(tea.getTeaching())) {
			tea.setTeaching("");
		}
		if (!tea.getCard().equals(teacher.getCard())){
			teacherProfile.setCardStatus(1);
		} else {
			if (type.equals("card")) {
				return "请重新上传身份证图片";
			}
		}
		if (!tea.getEducation().equals(teacher.getEducation())){
			teacherProfile.setEducationStatus(1);
		} else {
			if (type.equals("education")) {
				return "请重新上传学历证图片";
			}
		}
		if (!tea.getTeaching().equals(teacher.getTeaching())){
			teacherProfile.setTeachingStatus(1);
		} else {
			if (type.equals("teaching")) {
				return "请重新上传教师证图片";
			}
		}
		if (!tea.getSpecialty().equals(teacher.getSpecialty())){
			teacherProfile.setSpecialtyStatus(1);
		} else {
			if (type.equals("specialty")) {
				return "请重新上传专业资质证图片";
			}
		}
		teacherProfileDao.teacherAptitudeAttestation(teacherProfile);
		// 修改教师资质认证
		tea.setCard(teacher.getCard());
		tea.setEducation(teacher.getEducation());
		tea.setTeaching(teacher.getTeaching());
		tea.setSpecialty(teacher.getSpecialty());
		teacherDao.updateTeacher(tea);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
		memCache.remove(MemConstans.TEACHER_ALL);
		return "";
	}

	/**
	 * 修改教师信息
	 * @param teacher
	 */
	public void updateTeacher(Teacher teacher) throws Exception{
		Teacher tea = getTeacherByUserId(teacher.getUserId());
		if (tea.getStatus() == -1){
			// 删除缓存
			memCache.remove(MemConstans.USEREXPAND_INFO + tea.getUserId());
		}
		// 修改教师教授阶段
		String teacherSubjectIds = teacher.getTeacherSubject();
		String[] subjectIds = teacherSubjectIds.trim().split(",");
		List<TeacherSubject> teacherSubjectList = new ArrayList<TeacherSubject>();
		for (int i = 0; i < subjectIds.length; i++) {
			if (StringUtils.isNotEmpty(subjectIds[i]) && subjectIds[i] != null && !subjectIds[i].equals("")) {
				TeacherSubject teacherSubject = new TeacherSubject();
				teacherSubject.setSubjectId(Long.parseLong(subjectIds[i]));
				teacherSubject.setTeacherId(teacher.getId());
				teacherSubjectList.add(teacherSubject);
			}
		}
		teacherSubjectService.addTeacherSubjectBatch(teacherSubjectList);

		// 修改教师教授科目
		String teacherMajors = teacher.getTeacherMajor();
		String[] majorIds = teacherMajors.trim().split(",");
		List<TeacherMajor> teacherMajorList = new ArrayList<TeacherMajor>();
		for (int i = 0; i < majorIds.length; i++) {
			if (StringUtils.isNotEmpty(majorIds[i]) && majorIds[i] != null && !majorIds[i].equals("")) {
				TeacherMajor teacherMajor = new TeacherMajor();
				teacherMajor.setMajorId(Long.parseLong(majorIds[i]));
				teacherMajor.setTeacherId(teacher.getId());
				teacherMajorList.add(teacherMajor);
			}
		}
		teacherMajorService.addTeacherMajorBatch(teacherMajorList);
		// 修改教师拓展信息
		UserExpand userExpand = userExpandService.getUserExpandByUserId(teacher.getUserId());
		userExpand.setRealname(teacher.getUserExpand().getRealname());
		userExpand.setGender(teacher.getSex());
		userExpand.setBirthday(teacher.getUserExpand().getBirthday());
		userExpand.setAge(getAge(teacher.getUserExpand().getBirthday()));
		userExpand.setAvatar(teacher.getUserExpand().getAvatar());
		userExpandService.updateUserExpand(userExpand);
		// 修改教师用户表信息
		User user = userService.getUserById(teacher.getUserId());
		user.setNickname(teacher.getUserExpand().getNickname());
		userService.updateUser(user);
		// 教师基本信息
		tea.setSex(teacher.getSex());
		tea.setFinishSchool(teacher.getFinishSchool());
		tea.setDegree(teacher.getDegree());
		tea.setProfession(teacher.getProfession());
		// 修改教师高级信息
		tea.setSeniority(teacher.getSeniority());
		tea.setPeculiarity(teacher.getPeculiarity());
		tea.setTeachingLive(teacher.getTeachingLive());
		tea.setTeachingSuccess(teacher.getTeachingSuccess());
		tea.setTeachingSuccess(teacher.getTeachingSuccess());
		tea.setTeachingEnounce(teacher.getTeachingEnounce());
		// 修改教师认证信息
		tea.setCard(teacher.getCard());
		tea.setEducation(teacher.getEducation());
		tea.setTeaching(teacher.getTeaching());
		tea.setSpecialty(teacher.getSpecialty());
		// 修改教师资质认证状态
		TeacherProfile teacherProfile = teacherProfileDao.queryTeacherProfileByTeacher(teacher.getId());
		// 资质认证设置为1 审核中状态
		teacherProfile.setCardStatus(1);
		teacherProfile.setEducationStatus(1);
		teacherProfile.setTeachingStatus(1);
		teacherProfile.setSpecialtyStatus(1);
		teacherProfileDao.teacherAptitudeAttestation(teacherProfile);
		// 修改教师状态
		tea.setStatus(2L); // 未审核状态
		tea.setIsProfessor(1); // 韩教授认证（否：1  是：2）
		tea.setSoft(0L); // 排序值
		// 修改教师
		teacherDao.updateTeacher(tea);
		memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
		memCache.remove(MemConstans.TEACHER_ALL);
	}

	/** 计算年龄 */
	public  Long getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH)+1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				//monthNow == monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				//monthNow > monthBirth
				age--;
			}
		}
		if (age == 0){
			age = 1;
		}
		return new Long(age);
	}

	/**
	 * 根据id获取单个Teacher对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Teacher
	 */
	public Teacher getTeacherById(Long id) {
		return teacherDao.getTeacherById(id);
	}
	
	public Teacher getTeacherInfoById(Long id){
		Teacher teacher= teacherDao.getTeacherById(id);
		if(ObjectUtils.isNotNull(teacher)){
			if(teacher.getTotalAssess()>0){
				//计算好评中评差评率
				double goodPercent=(double)teacher.getGoodAssess()/teacher.getTotalAssess();
				double middlePercent=(double)teacher.getMiddleAssess()/teacher.getTotalAssess();
				double badPercent=1-goodPercent-middlePercent;
				teacher.setGoodPercent(handlePercent(goodPercent));
				teacher.setMiddlePercent(handlePercent(middlePercent));
				teacher.setBadPercent(handlePercent(badPercent));
			}else{
				//评论为空默认100%好评
				teacher.setGoodPercent("100%");
			}
			//查询老师所教授的专业集合
//			List<TeacherSubject> teacherSubject=teacherSubjectService.queryTeacherParentSubject(teacher.getId());
//    		request.setAttribute("teacherSubject", teacherSubject);
			teacher.setTeacherSubjects(teacherSubjectService.queryTeacherSubjectByOK(teacher.getId()));
			//查询老师所教授的科目集合
			teacher.setTeacherMajors(teacherMajorService.queryTeacherMajorByTeacherId(teacher.getId()));
		}
		return teacher;
	}
	
	/**
	 * 计算好评中评差评率
	 * @param n
	 * @return
	 */
	private String handlePercent(double n){
		NumberFormat num = NumberFormat.getPercentInstance(); 
		num.setMaximumIntegerDigits(3); 
		num.setMaximumFractionDigits(2); 
	    return num.format(n);
	}

	/**
	 * 根据条件获取Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<Teacher> getTeacherList(QueryTeacher teacher) {
		List<Teacher> teacherList=teacherDao.getTeacherList(teacher);
		return teacherList;
	}

	/**
	 * 后台查询Teacher列表
	 * 
	 * @param teacher
	 *            查询条件
	 * @return List<Teacher>
	 */
	public List<QueryTeacher> queryTeacherListPage(QueryTeacher teacher,PageEntity page){
		return teacherDao.queryTeacherListPage(teacher, page);
	}
	
	/**
	 * 根据id获取Teacher列表
	 */
	public List<QueryTeacher> queryTeacherInIds(String ids){
		return teacherDao.queryTeacherInIds(ids);
	}
	
	/**
     * 修改教师状态(韩教授认证)
     * @param teacher
     */
    public void updateTeacherStatus(Teacher teacher){
    	teacherDao.updateTeacherStatus(teacher);
		if (teacher.getIsProfessor() != 3 && (ObjectUtils.isNotNull(teacher.getStatus()) || teacher.getStatus().intValue() > 0)) {
			memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
			memCache.remove(MemConstans.TEACHER_ALL);
		} else if (teacher.getIsProfessor() == 3 && (ObjectUtils.isNull(teacher.getStatus()) || teacher.getStatus().intValue() <= 0)){
			// 获得网站配置 韩教授认证管理的邮箱和手机
			String type = "professorAttestation";
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(type);
			Map<String, Object> _map = (Map<String, Object>)map.get("professorAttestation");

			//发送短信，通知管理员，该用户申请了韩教授认证
			String msgContent = "用户" + teacher.getUserExpand().getRealname() + "申请了‘韩教授认证’资质，请您尽快与该用户取得联系，并安排认证审核！";
			try {
				//发送短信
				userMobileMsgService.sendEx(SendMsgConstans.SEND_HAN_AUTHENTICATION, (String)_map.get("mobile"), teacher.getUserExpand().getRealname(), null, null, null);
				//userMobileMsgService.sendEx(8, (String)_map.get("mobile"), teacher.getUserExpand().getRealname(), null, null);
			} catch (Exception e) {
				logger.info("send orderMsg error mobile:" + _map.get("mobile"));
			}

		}
    }

	@Override
	public Teacher getTeacherByUserId(Long userId) {
		return teacherDao.getTeacherByUserId(userId);
	}

	@Override
	public Map<String,Object> queryTeacherBySubjectMajorIndex(List<SubjectMajor> querySubjectMajor, int num,Long cityId) {
		@SuppressWarnings("unchecked")
		Map<String,Object> list=(Map<String, Object>) memCache.get(MemConstans.TEACHER_ALL+cityId);
		if(ObjectUtils.isNull(list)){
			list=new HashMap<String, Object>();
			//循环专业科目集合 根据专业id 科目id 城市id获取讲师集合
			for(SubjectMajor subjectMajor:querySubjectMajor){
				Map<String,Object> query=new HashMap<>();
				query.put("subjectId",subjectMajor.getSubjectid());
				query.put("majorId", subjectMajor.getMajorid());
				query.put("cityId", cityId);
				query.put("num", num);
				List<Teacher> teacherList= teacherDao.queryTeacherBySubjectMajor(query);
				list.put("teacher_"+subjectMajor.getSubjectid(), teacherList);
			}
			
			if (!ObjectUtils.isNull(list)) {
			   memCache.set(MemConstans.TEACHER_ALL+cityId, list, MemConstans.TEACHER_ALL_TIME);
			}
		}
		return list;
	}
	@Override
	public List<Teacher> queryTeacherBySubjectMajor(Map<String,Object> query) {
		return teacherDao.queryTeacherBySubjectMajor(query);
	}

	@Override
	public int queryAllTeacherCount() {
		return teacherDao.queryAllTeacherCount();
	}

	
	/**
     * 前台页面获得推荐的教师 封装为map key:index_teacher_+recommendId ,List<QueryTeacher>
     *
     * @throws Exception
     * @returnmap key:index_course_1 ,List<Course>
     */
    public Map<String, List<QueryTeacher>> getTeacherListByHomePage() throws Exception {
        Map<String, List<QueryTeacher>> map =(Map<String, List<QueryTeacher>>)memCache.get(MemConstans.RECOMMEND_TEACHER_ALL);
		// 缓存为空，查询数据库
		if (ObjectUtils.isNull(map)){
			map = new HashMap<String, List<QueryTeacher>>();
			// 查询全部推荐教师
			List<QueryTeacher> teacherList = teacherDao.getTeacherListByHomePage(0L);
			if (ObjectUtils.isNotNull(teacherList)) {
				List<QueryTeacher> teacherHotList = new ArrayList<QueryTeacher>();
				Long recommend = teacherList.get(0).getRecommendId();
				for (QueryTeacher teacher : teacherList) {
					if (recommend.longValue() != teacher.getRecommendId().longValue()) {
						map.put("index_teacher_" + recommend, teacherHotList);
						teacherHotList = new ArrayList<QueryTeacher>();
						teacherHotList.add(teacher);
						recommend = teacher.getRecommendId();
					} else {
						teacherHotList.add(teacher);
					}
				}
				if (ObjectUtils.isNotNull(teacherHotList)) {
					map.put("index_teacher_" + recommend, teacherHotList);
				}
			}
			// 将结果存入缓存
			memCache.set(MemConstans.RECOMMEND_TEACHER_ALL, map, MemConstans.RECOMMEND_TEACHER_ALL_TIME);
		}
        return map;
    }

	/**
	 * 前台页面根据条件查询教师列表
	 * @param queryTeacher
	 * @param page
	 * @return
	 */
	public List<Teacher> queryTeacherList(QueryTeacher queryTeacher, PageEntity page) {
		return teacherDao.queryTeacherList(queryTeacher, page);
	}

	/**
     * 根据用户ID查询教师列表
     * @param userId
     * @param page
     * @return
     */
    public List<QueryTeacher> queryTeacherListByUserId(Long userId, PageEntity page){
    	List<QueryTeacher> teacherList = teacherDao.queryTeacherListByUserId(userId, page);
    	return teacherList;
    }

	@Override
	public void updateCityTown(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherDao.updateCityTown(teacher);
	}

	/**
	 * 修改教师直播接口返回的用户ID
	 * @param teacherId
	 *               教师ID
	 */
	public void updateZoomMeetingUserId(Long teacherId, String zoomUserId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teacherId", teacherId);
		map.put("zoomMeetingUserId", zoomUserId);
		teacherDao.updateZoomMeetingUserId(map);
	}

	@Override
	public void updateLowPrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		teacherDao.updateLowPrice(map);
	}

	/**
	 * 判断是否可以审核教师
	 * @param teacherId
	 * @return
	 */
	public String checkTeaCourseNumAndAddress(Long teacherId){
		String message = "";
		// 获得教师详情
		Teacher teacher = teacherDao.getTeacherById(teacherId);
		// 教师姓名
		if ((StringUtils.isEmpty(teacher.getUserExpand().getRealname()) || teacher.getUserExpand().getRealname().equals(""))
			&& (StringUtils.isEmpty(teacher.getUserExpand().getNickname()) || teacher.getUserExpand().getNickname().equals(""))){
			message = "未填写真实姓名或昵称";
		}
		// 教师专业
		if (StringUtils.isEmpty(teacher.getProfession()) || teacher.getProfession().equals("")){
			message = "教师专业为空";
		}
		// 教师毕业院校
		if (StringUtils.isEmpty(teacher.getFinishSchool()) || teacher.getFinishSchool().equals("")){
			message = "毕业院校为空";
		}
		// 教师学历
		if (StringUtils.isEmpty(teacher.getDegree()) || teacher.getDegree().equals("") || teacher.getDegree().equals("0")){
			message = "教师学历为空";
		}
		// 教师教学特点
		if (StringUtils.isEmpty(teacher.getPeculiarity()) || teacher.getPeculiarity().equals("")){
			message = "教学特点为空";
		}
		// 教学阶段
		if (StringUtils.isEmpty(teacher.getSubjects()) || teacher.getSubjects().equals("")){
			message = "教学年级为空";
		}
		// 教授科目
		if (StringUtils.isEmpty(teacher.getMajors()) || teacher.getMajors().equals("")){
			message = "教授科目为空";
		}
		// 课程数量
		if (ObjectUtils.isNull(teacher.getCourseNum()) || teacher.getCourseNum().intValue() == 0){
			message = "教授课程数量必须大于0";
		}
		// 常用上课地址
		if (StringUtils.isEmpty(teacher.getAddress()) || teacher.getAddress().equals("")){
			message = "常用上课地址数量必须大于0";
		}
		return message;
	}

	/**
	 * 前台修改教师个人中心-账户设置-基本信息性别
	 * @param teacher
	 */
	public void updateTeacherSex(Teacher teacher){
		teacherDao.updateTeacherSex(teacher);
	}
}