package com.yizhilu.os.edu.dao.user;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.entity.user.UserOptRecord;

/**
 * User管理接口 User: qinggang.liu Date: 2014-01-10
 */
public interface UserDao {

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
     * 根据手机号码查询单个用户
     * @Author wangzhuang
     * @param mobile
     * @return
     */
	public User getUserByMobileNumber(String mobile);

	/**
	 * 通过用户id 更新密码
	 * 
	 * @return User
	 */
	public void updatePwdById(User user);

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
	 * 查询学员邮箱是否存在
	 * 
	 * @param emails
	 * @return Long
	 */
	public List<User> getUserIsExsitByEmail(List<String> emails);

	public User getUserIsExsitByEmail(String email);

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
	 */
	public Integer getUserByMobile(User user);

	
	
	/**
	 * 获取网站注册人数
	 * 
	 * @return
	 */
	public Integer getWebsiteRegNumber();

	/**
	 * 添加用户总操作记录
	 * 
	 * @param userOptRecord
	 */
	public void addUserOptRecord(UserOptRecord userOptRecord);
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
}