package com.yizhilu.os.edu.dao.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhilu.os.edu.entity.user.UserExpand;
import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.dao.user.UserDao;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.user.UserOptRecord;

/**
 * 
 * User User: qinggang.liu voo@163.com Date: 2014-01-10
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

    public Long addUser(User user) {
        return this.insert("UserMapper.createUser", user);
    }
    
    

    public void deleteUserById(Long id) {
        this.delete("UserMapper.deleteUserById", id);
    }

    public void updateUser(User user) {
        this.update("UserMapper.updateUser", user);
    }
    
    /**
     * 通过用户id更新用户Isavalible （冻结，解冻操作）
     * @param user 要修改的User
     */
    public void updateUserForIsavalibleById(User user){
        this.update("UserMapper.updateUserForIsavalibleById", user);
    }

    
    
    
    public User getUserById(Long id) {
        return this.selectOne("UserMapper.getUserById", id);
    }
    
    /**
     * @Author wangzhuang
     * 根据手机号码查询单个用户
     */
    public User getUserByMobileNumber(String mobile){
    	return this.selectOne("UserMapper.getUserByMobileNumber", mobile);
    }
    
    
    
    /**
     * 通过用户id 更新密码
     * @return User
     */
    public void updatePwdById(User user){
        this.update("UserMapper.updatePwdById", user);
    }

    /**
     *
     * @param user
     *            查询条件
     * @return
     */
    public List<User> getUserList(User user) {
        return this.selectList("UserMapper.getUserList", user);
    }
    /**
     * 根据邮箱获取User列表(用户登陆添加了冻结状态)
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForLogin(User user){
        return this.selectList("UserMapper.getUserListForLogin", user);
    }
    /**
     * 根据手机号获取User列表(用户登陆添加了冻结状态)
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForTelLogin(User user){
        return this.selectList("UserMapper.getUserListForTelLogin", user);
    }
    public List<User> getUserListByCondition(User user,PageEntity page) {
        return this.queryForListPage("UserMapper.getUserListByCondition", user, page);
    }
	/**
	 * 批量查询学员邮箱是否存在
	 * 
	 * @param emails
	 * @return Long
	 */
	public List<User> getUserIsExsitByEmail(List<String> emails) {
	    List<User> userList=this.selectList("UserMapper.getUserIsExsitByEmail", emails);
		if(ObjectUtils.isNotNull(userList)){
			return userList;
		}
		return null;
	}
	@Override
	public User getUserIsExsitByEmail(String email) {
		User user = this.selectOne("UserMapper.getUserIsExsitByOneEmail", email);
		if(ObjectUtils.isNotNull(user)){
			return  user;
		}else{
			return null;
		}
	}
	/**
	 * 批量添加用户
	 * 
	 * @param user
	 */
	public void addUsers(List<User> users) {
		this.insert("UserMapper.addUsers", users);
	}
	
	/**
	 * 
	 */
	public Integer getUserByMobile(User user) {
		List<Integer> integerList=  this.selectList("UserMapper.getUserByMobile", user);
		if(ObjectUtils.isNotNull(integerList)&&integerList.size()>0){
			return integerList.get(0);
		}
		return 0;
	}


	/**
	 * 获取网站注册人数
	 * 
	 * @return
	 */
	public Integer getWebsiteRegNumber() {
		List<Integer> integerList=this.selectList("UserMapper.getWebsiteRegNumber", null);
		if(ObjectUtils.isNotNull(integerList)&&integerList.size()>0){
			return integerList.get(0);
		}
		return 0;
	}
	/**
	 * 添加用户总操作记录
	 * 
	 * @param userOptRecord
	 */
	public void addUserOptRecord(UserOptRecord userOptRecord) {
		this.insert("UserMapper.addUserOptRecord", userOptRecord);
	}



	@Override
	public List<User> getUserListAndCourse(User user, PageEntity page) {
		return this.queryForListPage("UserMapper.getUserListAndCourse", user, page);
	}
	/**
	 * 获取后台赠送操作列表
	 * @param userOptRecord
	 * @param page
	 * @return
	 */
	public List<UserOptRecord> getUserOptRecordListByCondition(UserOptRecord userOptRecord,PageEntity page){
		return this.queryForListPage("UserMapper.getUserOptRecordListByCondition", userOptRecord, page);
	}



	@Override
	public void jumpUser(User user) {
		// TODO Auto-generated method stub
		update("UserMapper.jumpUser", user);
	}



	@Override
	public int queryAllStudentUser() {
		// TODO Auto-generated method stub
		return selectOne("UserMapper.queryAllStudentUser", null);
	}



	/**
	 * 根据教师ID查询学生列表
	 * @param teacherId
	 * @param page
	 * @return
	 */
	public List<UserExpand> queryStudentListByTeacherId(Long userId, PageEntity page){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return this.queryForListPage("UserMapper.queryStudentListByTeacherId", map, page);
	}

	@Override
	public int queryAllTeacherUser() {
		// TODO Auto-generated method stub
		return selectOne("UserMapper.queryAllTeacherUser", null);
	}
}
