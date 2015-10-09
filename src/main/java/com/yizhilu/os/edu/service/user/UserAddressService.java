package com.yizhilu.os.edu.service.user;

import java.util.List;
import com.yizhilu.os.edu.entity.user.UserAddress;

/**
 * UserAddress管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface UserAddressService {

    /**
     * 添加UserAddress
     * @param userAddress 要添加的UserAddress
     * @return id
     */
    public java.lang.Long addUserAddress(UserAddress userAddress);

    /**
     * 根据id删除一个UserAddress
     * @param id 要删除的id
     */
    public void deleteUserAddressById(Long userId,Long id);
    /**
     * 根据id设置常用地址
     */
    public void updateUserAddressById(Long id,Long userId);

    /**
     * 修改UserAddress
     * @param userAddress 要修改的UserAddress
     */
    public void updateUserAddress(UserAddress userAddress);

    /**
     * 根据id获取单个UserAddress对象
     * @param id 要查询的id
     * @return UserAddress
     */
    public UserAddress getUserAddressById(Long id);

    /**
     * 根据条件获取UserAddress列表
     * @param userAddress 查询条件
     * @return List<UserAddress>
     */
    public List<UserAddress> getUserAddressList(UserAddress userAddress);
    /**
     * 根据id或用户id更新isFirst
     */
    public void updateUserAddressisFirst(UserAddress userAddress);
}