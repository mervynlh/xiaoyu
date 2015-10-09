package com.yizhilu.os.edu.service.impl.user;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.common.util.UserAgentUtil;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.service.gain.GuidGeneratorService;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.PreventInfusion;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.Security.PurseSecurityUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.constants.enums.AccountStatus;
import com.yizhilu.os.edu.constants.enums.IntegralKeyword;
import com.yizhilu.os.edu.constants.enums.ReqChanle;
import com.yizhilu.os.edu.constants.enums.UserExpandFrom;
import com.yizhilu.os.edu.dao.user.UserDao;
import com.yizhilu.os.edu.dao.user.UserExpandDao;
import com.yizhilu.os.edu.dao.user.UserIntegralDao;
import com.yizhilu.os.edu.entity.course.Teacher;
import com.yizhilu.os.edu.entity.teacher.TeacherProfile;
import com.yizhilu.os.edu.entity.user.LoginOnline;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserAccount;
import com.yizhilu.os.edu.entity.user.UserCode;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.entity.user.UserForm;
import com.yizhilu.os.edu.entity.user.UserIntegral;
import com.yizhilu.os.edu.entity.user.UserLoginLog;
import com.yizhilu.os.edu.entity.user.UserOptRecord;
import com.yizhilu.os.edu.service.course.TeacherService;
import com.yizhilu.os.edu.service.order.TrxorderService;
import com.yizhilu.os.edu.service.teacher.TeacherProfileService;
import com.yizhilu.os.edu.service.user.LoginOnlineService;
import com.yizhilu.os.edu.service.user.UserAccountService;
import com.yizhilu.os.edu.service.user.UserCodeService;
import com.yizhilu.os.edu.service.user.UserExpandService;
import com.yizhilu.os.edu.service.user.UserIntegralService;
import com.yizhilu.os.edu.service.user.UserLoginLogService;
import com.yizhilu.os.edu.service.user.UserService;

/**
 * User管理接口 User: qinggang.liu Date: 2014-01-10
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserIntegralDao userIntegralDao;
	private static final int HMAC_KEY_LEN = 60;
	MemCache memCache = MemCache.getInstance();
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserExpandDao userExpandDao;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserCodeService userCodeService;
	@Autowired
	private TrxorderService trxorderService;// 订单服务层
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private GuidGeneratorService guidGeneratorService;
	@Autowired
	private UserLoginLogService userLoginLogService;
    @Autowired
    private  LoginOnlineService loginOnlineService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherProfileService teacherProfileService;
    
	/**
	 * 添加User
	 * 
	 * @param user
	 *            要添加的User
	 * @return id
	 */
	public Long addUser(User user) {
		// 密码加密
		generatePassword(user);
		user.setCreatedate(new Date());
		userDao.addUser(user);
		// 加入扩展信息,扩展的用户信息以后使用
		UserExpand userExpand = new UserExpand();
		userExpand.setCusId(user.getId());
		userExpand.setLastSystemTime(new Date());
        userExpand.setRegisterFrom(user.getRegisterFrom());//账号来源
        userExpand.setExtendCord(user.getExtendCord());// 推广号
		userExpandDao.addUserExpand(userExpand);

		// 添加账户
		UserAccount userAccount = new UserAccount();
		userAccount.setUserId(user.getId());
		userAccount.setCreateTime(new Date());
		userAccount.setLastUpdateTime(new Date());
		userAccount.setBalance(new BigDecimal(0));
		userAccount.setForzenAmount(new BigDecimal(0));
		userAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
		userAccount.setCashAmount(new BigDecimal(0));
		userAccount.setVmAmount(new BigDecimal(0));
		userAccount.setVersion(Long.valueOf(0));
		userAccountService.addUserAccount(userAccount);

		// 添加积分表
		UserIntegral userIntegral = new UserIntegral();
		userIntegral.setUserId(user.getId());// 用户id
		userIntegral.setCurrentScore(0L);// 当前积分
		userIntegral.setTotalScore(0L);// 总积分
		userIntegralDao.addUserIntegral(userIntegral);// 添加用户积分
		// 赠送优惠券
		if(user.getUserType()==0){
			// TODO 相关代码
		}
		
		if(user.getUserType()==1){// 教师
			Teacher teacher = new Teacher();
			teacher.setUserId(user.getId());// 关联userId
			teacherService.addTeacher(teacher);
			TeacherProfile teacherProfile = new TeacherProfile();
			teacherProfile.setTeacherId(teacher.getId());// 关联教师id
			teacherProfile.setStar(0);// 教师星级
			teacherProfile.setAssessNum(0L);// 教师评价数
			teacherProfile.setStudentNum(0L);// 学生数量
			teacherProfile.setBrowseNum(0L);
			teacherProfile.setCollectNum(0L);
			teacherProfile.setCardStatus(0);
			teacherProfile.setSpecialtyStatus(0);
			teacherProfile.setTeachingStatus(0);
			teacherProfile.setEducationStatus(0);
			teacherProfileService.addTeacherProfile(teacherProfile);
		}
		return user.getId();
	}

	/**
	 * 根据id删除一个User
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserById(Long id) {
		userDao.deleteUserById(id);
	}

	/**
	 * 修改User
	 * 
	 * @param user
	 *            要修改的User
	 */
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	/**
	 * 修改QueryUser
	 * 
	 * @throws Exception
	 */
	public String updateQueryUser(UserExpandDto queryUser) throws Exception {
		// 修改用户表
		User user = new User();
		user.setId(queryUser.getId());
		user.setNickname(queryUser.getNickname());
		user.setEmail(queryUser.getEmail());
		this.updateUser(user);
		// 如果当前登录用户是教师，则关联修改教师表的性别
		Teacher teacher = teacherService.getTeacherByUserId(queryUser.getId());
		if (ObjectUtils.isNotNull(teacher)){
			teacher.setSex(queryUser.getGender());
			teacherService.updateTeacherSex(teacher);
		}
		// 修改用户拓展表
		UserExpand uex=userExpandService.getUserExpandByUserId(queryUser.getId());
		UserExpand userExpand = new UserExpand();
		userExpand.setCusId(queryUser.getId());
		userExpand.setGender(queryUser.getGender());
		userExpand.setRealname(queryUser.getRealname());
		userExpand.setAvatar(uex.getAvatar());
		userExpand.setUserInfo(queryUser.getUserInfo());
		userExpand.setRegisterFrom(uex.getRegisterFrom());
		userExpandService.updateUserExpand(userExpand);
		// 删除缓存
		memCache.remove(MemConstans.USEREXPAND_INFO + queryUser.getId());
		return "success";
	}

	/**
	 * 通过用户id更新用户Isavalible （冻结，解冻操作）
	 * 
	 * @param user
	 *            要修改的User
	 */
	public void updateUserForIsavalibleById(User user) {
		userDao.updateUserForIsavalibleById(user);
	}

	/**
	 * 根据id获取单个User对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return User
	 */
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}
	/**
	 * @author wangzhuang
	 * 根据手机号码查询用户
	 */
	public User getUserByMobileNumber(String mobile){
		return userDao.getUserByMobileNumber(mobile);
	}
	
	/**
	 * 通过用户id 更新密码
	 * 
	 * @return User
	 */
	public void updatePwdById(User user, UserCode userCode) {
		generatePassword(user);
		userDao.updatePwdById(user);
		if (ObjectUtils.isNotNull(userCode)) {
			// 更新邮件验证码的状态
			userCode.setStatus(1L);// 更新为已经使用
			userCodeService.updateUserCode(userCode);
		}
	}

	/**
	 * 根据条件获取User列表
	 * 
	 * @param user
	 *            查询条件email
	 * @return List<User>
	 */
	public List<User> getUserList(User user) {
		return userDao.getUserList(user);
	}

	/**
	 * 根据条件获取User列表(用户登陆添加了冻结状态)
	 * 
	 * @param user
	 *            查询条件
	 * @return List<User>
	 */
	public List<User> getUserListForLogin(User user) {
		return userDao.getUserListForLogin(user);
	}
	 /**
     * 根据手机号获取User列表(用户登陆添加了冻结状态)
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForTelLogin(User user){
        return userDao.getUserListForTelLogin(user);
    }
	/**
	 * 根据条件获取User列表
	 * 
	 * @param user
	 *            用户
	 * @param page
	 *            分页参数
	 * @return
	 */
	public List<User> getUserListByCondition(User user, PageEntity page) {
		return userDao.getUserListByCondition(user, page);
	}
	
	/**
	 * 根据条件获取User列表  带课程名称
	 * @param user  用户
	 * @param page   分页参数
	 * @return
	 */
	public List<User> getUserListAndCourse(User user, PageEntity page) {
		return userDao.getUserListAndCourse(user, page);
	}

	/**
	 * 设计加密密码
	 * 
	 * @param user
	 */
	private void generatePassword(User user) {
		String customerKey = StringUtils.getRandomString(HMAC_KEY_LEN);
		String password = PurseSecurityUtils.secrect(user.getPassword(), customerKey);
		user.setPassword(password);
		user.setCustomerkey(customerKey);
	}

	/**
	 * 设置用户为登录状态
	 * 
	 * @param user
	 * @param response
	 * @return
	 */
	public Map<String, Object> setLoginStatus(User user, String autoThirty, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//手机绑定操作
			if(user.getMobileIsavalible()==1){
				map.put("userInfo", "mobileIsavalibleErr");
			}else if(user.getIsavalible()==1){//查询账号是否被冻结
				map.put("userInfo", "isavalibleErr");
			}else{
				 //存储登录的信息到缓存中
				UserExpandDto userExpandDto = queryUserExpand(user.getId());
				Long current=System.currentTimeMillis();
				userExpandDto.setCurrent(current);
				String uuid = StringUtils.createUUID().replace("-", "");
				//存数用户的登录信息
				//memCache.set(uuid, userExpandDto, MemConstans.USER_TIME);
				SingletonLoginUtils.setLoginInfo(uuid,userExpandDto);
				memCache.set(MemConstans.USER_CURRENT_LOGINTIME+user.getId(), current.toString(), MemConstans.USER_TIME);
				WebUtils.setCookie(response, CommonConstants.USER_SINGEL_ID, uuid, 1);
				WebUtils.setCookie(response, CommonConstants.USER_SINGEL_NAME, URLEncoder.encode(user.getEmail(), "UTF-8"), 1);
				WebUtils.setCookie(response, CommonConstants.USER_SINGEL_TYPE, user.getUserType()+"", 1);
				// 添加登录记录
				UserLoginLog userLoginLog = new UserLoginLog();
				userLoginLog.setLoginIp(WebUtils.getIpAddr(request));
				userLoginLog.setUserId(user.getId());
				userLoginLog.setLoginTime(new Date());
				String userAgent=UserAgentUtil.getUserAgent(request);
				if(StringUtils.isNotEmpty(userAgent)){
					userLoginLog.setUserAgent(userAgent.split(";")[0]);//浏览器
					userLoginLog.setOsname(userAgent.split(";")[1]);//操作系统
				}
				userLoginLogService.addUserLoginLog(userLoginLog);
				
	            //添加在线记录状态
	            LoginOnline loginOnline=new LoginOnline();
	            loginOnline.setUserId(user.getId());
	            loginOnline.setLoginsid(uuid);
	            loginOnline.setCreateTime(new Date());
	            loginOnline.setType(ReqChanle.WEB.toString());
	            loginOnlineService.addLoginOnline(loginOnline);
			}
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * 用户定时操作
	 * 更新用户地址
	 */
	public void updateUserAddress(){
			List<UserLoginLog> userLoginLogList= userLoginLogService.getUserLoginLogList();
			for(UserLoginLog userLoginLog: userLoginLogList){
				try {
					//循环处理用户登陆信息
					userLoginLogService.updateUserLoginLog(userLoginLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
	
	
	
	
	/**
	 * 查询学员邮箱是否存在
	 * 
	 * @param emails
	 * @return Integer
	 */
	public List<User> getUserIsExsitByEmail(List<String> emails) {
		return userDao.getUserIsExsitByEmail(emails);
	}

	/**
	 * 批量添加用户
	 * 
	 * @param users
	 */
	public void addUsers(List<User> users) {
		for (User user : users) {
			generatePassword(user);// 给密码加密
		}
		userDao.addUsers(users);
	}

	/**
	 * 验证手机唯一
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer getUserByMobile(User user) throws Exception {
		return userDao.getUserByMobile(user);
	}
	
	// 从excel表中导出的数据处理
	public String updateImportExcel(MultipartFile myFile) throws Exception {
		// datalist拼装List<String[]> datalist,
		HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
	
		int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
		for (int i = 1; i <= rows+1; i++) {
			
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			// 行不为空
			if (row != null) {
				// 获取到Excel文件中的所有的列
				int maxcell = row.getLastCellNum();
				// **读取cell**
				String email = getCellValue(row.getCell((short) 0));// 邮箱
				if (ObjectUtils.isNull(email) || email.equals("")) {
					continue;
				}
				String courseIds =  getCellValue(row.getCell((short) 1));// 商品id
				String pwd=getCellValue(row.getCell((short) 2));//获得密码
				
				User trueUser = userDao.getUserIsExsitByEmail(email);
				int count = 0;
				if (trueUser == null) {
					String uid= guidGeneratorService.gainCode("REG",false);
					User users = new User();
					users.setEmail(email);
					users.setMobile(uid);//默认手机号
					if(StringUtils.isEmpty(pwd)){
						users.setPassword("111111");// 设置默认密码
					}else{
						users.setPassword(pwd);// 设置默认密码
					}
                    users.setRegisterFrom(UserExpandFrom.adminFrom.toString());//后台批量生成账号来源
					addUser(users);
					// 注册送积分
					userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), users.getId(), 0L, 0L, "");
					trueUser=users;
				} 
				if(courseIds!=null&&!courseIds.equals("")){
					String[] couArray = courseIds.replace(",", " ").trim().split(" ");
					for(count = 0;count< couArray.length;count++){
						addOrderMsg(new Long(trueUser.getId()),couArray[count]);
					}
				}
			}
		}
		return null;
	}

	// 订单流水信息
	public void addOrderMsg(Long userId, String courseId) throws Exception {
		// 拼装参数
		Map<String, String> sourceMap = new HashMap<String, String>();
		sourceMap.put("type", "1");// 下单类型
		sourceMap.put("couponcode", "");// 优惠卷编码
		sourceMap.put("userid", userId + "");
		sourceMap.put("reqchanle", ReqChanle.WEB.toString());
		sourceMap.put("reqIp", "");
		sourceMap.put("courseId", courseId);
		sourceMap.put("payType", "FREE");
		trxorderService.addFreeTrxorder(sourceMap);
	}

	/**
	 * 获得Hsscell内容
	 * 
	 * @param cell
	 * @return
	 */
	public String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("0");    
				value = df.format(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().trim();
				break;
			default:
				value = "";
				break;
			}
		}
		return value.trim();
	}

	

	
	/**
	 * 获得网站注册人数
	 * 
	 * @return
	 */
	public Integer getWebsiteRegNumber() {
		return userDao.getWebsiteRegNumber();
	}
	/**
	 * 添加用户操作记录
	 * @param userId 用户的id
	 * @param type 业务类型
	 * @param optuser 操作者id
	 * @param optusername 操作者名字
	 * @param bizId 业务id
	 * @param desc 描述
	 */
	public void addUserOptRecord(Long userId, String type,Long optuser, String optusername,Long bizId, String desc) {
		
		UserOptRecord userOptRecord = new UserOptRecord();
		userOptRecord.setUserId(userId);//添加用户id
		userOptRecord.setType(type);//业务类型
		userOptRecord.setBizId(bizId);//业务id
		userOptRecord.setOptuser(optuser);//操作者id
		userOptRecord.setOptusername(optusername);//操作者名字
		userOptRecord.setDescription(desc);//描述
		userOptRecord.setCreateTime(new Date());
		userDao.addUserOptRecord(userOptRecord);
	}

	@Override
	public UserExpandDto queryUserExpand(long userId) {
		return userExpandDao.queryUserByid(userId);
	}
	/**
	 * 获取后台赠送操作列表
	 * @param userOptRecord
	 * @param page
	 * @return
	 */
	public List<UserOptRecord> getUserOptRecordListByCondition(UserOptRecord userOptRecord,PageEntity page){
		return userDao.getUserOptRecordListByCondition(userOptRecord,page);
	}

	@Override
	public void jumpUser(User user) {
		// TODO Auto-generated method stub
		userDao.jumpUser(user);
	}

	@Override
	public int queryAllStudentUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllStudentUser();
	}

	@Override
	public List<UserExpand> queryStudentListByTeacherId(Long userId, PageEntity page) {
		return userDao.queryStudentListByTeacherId(userId, page);
	}

	@Override
	public void indexQueryTotal() {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		map.put("allTeacherCount", this.queryAllTeacherUser());
		map.put("allStudentCount", this.queryAllStudentUser());
		memCache.set(MemConstans.INDEX_TOTAL_NUM, map, MemConstans.INDEX_TOTAL_NUM_TIME);
	}

	@Override
	public int queryAllTeacherUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllTeacherUser();
	}

	@Override
	public Map<String, Object> getIndexTotal() {
		// TODO Auto-generated method stub
		Map<String, Object> map = (Map<String,Object>)memCache.get(MemConstans.INDEX_TOTAL_NUM);
		if(ObjectUtils.isNull(map)){
			map=new HashMap<>();
			map.put("allTeacherCount", this.queryAllTeacherUser());
			map.put("allStudentCount", this.queryAllStudentUser());
			memCache.set(MemConstans.INDEX_TOTAL_NUM, map, MemConstans.INDEX_TOTAL_NUM_TIME);
		}
		return map;
	}
	/**
	 * 判断账户的有效性
	 * 
	 * @return
	 */
	public User checkUserValid(UserForm userForm) {
		// 验证输入数据合法性
		if (PreventInfusion.sql_inj(userForm.getMobile()) || PreventInfusion.sql_inj(userForm.getPassword())) {
			return null;
		}
		User user = new User();
		user.setMobile(userForm.getMobile());
		user.setUserType(userForm.getUserType());
		List<User> list = null;
		// 如果是手机格式则按手机查询
		String regEx = "^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$"; // 表示a或F
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(user.getMobile());
		boolean rs = mat.find();
		if (rs) {
			list = getUserListForTelLogin(user);
		}
		// 通过数据库，验证用户是否存在
		if (ObjectUtils.isNull(list)) {
			return null;
		} else {
			user = list.get(0);
			// 验证密码是否正确
			if (checkIsRight(user.getPassword(), userForm.getPassword(), user.getCustomerkey())) {
				return user;
			} else {
				return null;
			}
		}
		
	}
	public boolean checkIsRight(String dbPassword, String userPassword, String userkey) {
		String despassword = PurseSecurityUtils.secrect(userPassword, userkey);
		return despassword.equals(dbPassword);
	}
	
}
