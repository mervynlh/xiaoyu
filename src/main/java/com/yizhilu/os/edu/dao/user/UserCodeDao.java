package com.yizhilu.os.edu.dao.user;
import java.util.List;
import com.yizhilu.os.edu.entity.user.UserCode;

/**
 * UserCode管理接口
 * User: qinggang.liu
 * Date: 2014-09-15
 */
public interface UserCodeDao {

    /**
     * 添加UserCode
     * @param userCode 要添加的UserCode
     * @return id
     */
    public java.lang.Long addUserCode(UserCode userCode);

    /**
     * 根据id删除一个UserCode
     * @param id 要删除的id
     */
    public void deleteUserCodeById(Long id);

    /**
     * 修改UserCode
     * @param userCode 要修改的UserCode
     */
    public void updateUserCode(UserCode userCode);

    /**
     * 根据id获取单个UserCode对象
     * @param id 要查询的id
     * @return UserCode
     */
    public UserCode getUserCodeById(Long id);

}