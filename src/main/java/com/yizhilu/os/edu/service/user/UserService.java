package com.yizhilu.os.edu.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yizhilu.os.edu.entity.user.*;

import org.springframework.web.multipart.MultipartFile;

import com.yizhilu.os.core.entity.PageEntity;

/**
 * User管理接口 User: qinggang.liu Date: 2014-01-10
 */
public interface UserService {

	/**
	 * 添加User
	 * 
	 * @param user
	 *            要添加的User
	 * @return id
	 */
	public Long addUser(User user);

	/**
	 * 根据id删除一个User
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserById(Long id);

	/**
	 * 修改User
	 * 
	 * @param user
	 *            要修改的User
	 */
	public void updateUser(User user);

	/**
	 * 修改QueryUser
	 */
	public String updateQueryUser(UserExpandDto queryUser) throws Exception;

	/**
	 * 通过用户id更新用户Isavalible （冻结，解冻操作）
	 * 
	 * @param user
	 *            要修改的User
	 */
	public void updateUserForIsavalibleById(User user);

	/**
	 * 根据id获取单个User对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return User
	 */
	public User getUserById(Long id);

	
	/**
	 * @Author wangzhuang
	 * 根据手机号码查询单个用户
	 * @param mobile
	 * @return
	 */
	public User getUserByMobileNumber(String mobile);
	
	/**
	 * 通过用户id 更新密码
	 */
	public void updatePwdById(User user, UserCode userCode);

	/**
	 * 根据条件获取User列表
	 * 
	 * @param user
	 *            查询条件
	 * @return List<User>
	 */
	public List<User> getUserList(User user);

	/**
	 * 根据条件获取User列表(用户登陆添加了冻结状态)
	 * 
	 * @param user
	 *            查询条件
	 * @return List<User>
	 */
	public List<User> getUserListForLogin(User user);
	 /**
     * 根据手机号获取User列表(用户登陆添加了冻结状态)
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForTelLogin(User user);
	/**
	 * 根据条件获取User列表
	 * 
	 * @param user
	 *            用户
	 * @param page
	 *            分页参数
	 * @return
	 */
	public List<User> getUserListByCondition(User user, PageEntity page);
	
	/**
	 * 根据条件获取User列表  带课程名称
	 * @param user  用户
	 * @param page   分页参数
	 * @return
	 */
	public List<User> getUserListAndCourse(User user, PageEntity page);

	/**
	 * 设置用户为登录状态
	 * 
	 * @param user
	 * @param response
	 * @return
	 */
	public Map<String, Object> setLoginStatus(User user, String autoThirty, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 查询学员邮箱是否存在
	 * 
	 * @param emails
	 * @return Integer
	 */
	public List<User> getUserIsExsitByEmail(List<String> emails);

	/**
	 * 批量添加用户
	 * 
	 * @param user
	 */
	public void addUsers(List<User> users);

	/**
	 * 验证手机唯一
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer getUserByMobile(User user) throws Exception;

	/**
	 * excel批量开通用户 shanXinYing
	 * 
	 * @param myFile
	 * @return
	 * @throws Exception
	 */
	public String updateImportExcel(MultipartFile myFile) throws Exception;

	

	/**
	 * 定时器，用户ip地址修改
	 * 
	 */
	public void updateUserAddress();

	/**
	 * 获得网站注册人数
	 * 
	 * @return
	 */
	public Integer getWebsiteRegNumber();

	/**
	 * 添加用户操作记录
	 * @param userId 用户的id
	 * @param type 业务类型
	 * @param optuser 操作者id
	 * @param optusername 操作者名字
	 * @param bizId 业务id
	 * @param desc 描述
	 */
	public void addUserOptRecord(Long userId, String type,Long optuser, String optusername,Long bizId, String desc);
	
	/**
	 * 根据用户ID，查询用户扩展数据
	 * @param userId
	 * @return
	 */
	public UserExpandDto queryUserExpand(long userId);
	/**
	 * 获取后台赠送操作列表
	 * @param userOptRecord
	 * @param page
	 * @return
	 */
	public List<UserOptRecord> getUserOptRecordListByCondition(UserOptRecord userOptRecord,PageEntity page);
	/**
	 * 绑定用户邮箱手机号
	 * @param user
	 */
	public void jumpUser(User user);
	/**
	 * 查询注册的学生（家长）数
	 * @return
	 */
	public int queryAllStudentUser();
	/**
	 * 查询老师数
	 * @return
	 */
	public int queryAllTeacherUser();
	/**
	 * 根据教师ID查询学生列表
	 * @param teacherId
	 * @param page
	 * @return
	 */
	public List<UserExpand> queryStudentListByTeacherId(Long userId, PageEntity page);
	/**
	 * 首页入驻老师人数与注册人数定时器缓存
	 * @return
	 */
	public void indexQueryTotal();
	/**
	 * 获取首页入驻讲师人数与注册人数
	 * @return
	 */
	public Map<String,Object> getIndexTotal();
	/**
	 * 判断账户的有效性
	 * @param userForm
	 * @return
	 */
	public User checkUserValid(UserForm userForm);
}