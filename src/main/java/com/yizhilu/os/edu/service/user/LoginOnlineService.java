package com.yizhilu.os.edu.service.user;

import java.util.List;

import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.LoginOnline;

/**
 * LoginOnline管理接口
 * User: qinggang.liu
 * Date: 2014-11-18
 */
public interface LoginOnlineService {

    /**
     * 添加LoginOnline
     * @param loginOnline 要添加的LoginOnline
     * @return id
     */
    public java.lang.Long addLoginOnline(LoginOnline loginOnline);

    /**
     * 根据id删除一个LoginOnline
     * @param id 要删除的id
     */
    public void deleteLoginOnlineById(Long id);

    /**
     * 修改LoginOnline
     * @param loginOnline 要修改的LoginOnline
     */
    public void updateLoginOnline(LoginOnline loginOnline);

    /**
     * 根据id获取单个LoginOnline对象
     * @param id 要查询的id
     * @return LoginOnline
     */
    public LoginOnline getLoginOnlineById(Long id);

    /**
     * 根据条件获取LoginOnline列表
     * @param loginOnline 查询条件
     * @return List<LoginOnline>
     */
    public List<LoginOnline> getLoginOnlineList(LoginOnline loginOnline,PageEntity pageEntity);
}