package com.yizhilu.os.sysuser.dao;

import java.util.List;

import com.yizhilu.os.sysuser.entity.SysGroup;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.supergenius.sns.dao.sysuser.GroupDao
 * @description 用户组管理
 * @Create Date : 2013-12-17 上午9:25:53
 */

public interface GroupDao {
    /**
     * 根据id查询Group
     *
     * @param id
     * @return Group
     */
    public SysGroup getGroupById(Long id);

    /**
     * 查询所有的Group
     *
     * @return List<Group>
     */
    public List<SysGroup> getGroupList();

    /**
     * 根据parentId查询Group
     *
     * @param parentId
     * @return List<Group>
     */
    public List<SysGroup> getChildGroupById(Long parentId);

    /**
     * 根据id删除group
     *
     * @param id
     */
    public void deleteGroupById(Long id);

    /**
     * 根据id更新group
     *
     * @param group
     */
    public void updateGroup(SysGroup group);

    /**
     * 添加组
     *
     * @param group
     */
    public void addGroup(SysGroup group);
}
