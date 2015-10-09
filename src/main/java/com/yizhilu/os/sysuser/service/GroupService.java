package com.yizhilu.os.sysuser.service;

import java.util.List;

import com.yizhilu.os.sysuser.entity.SysGroup;

/**
 * @author liuqinggang
 * @ClassName GroupService
 * @package com.supergenius.sns.service.user
 * @description
 * @Create Date: 2013-3-5 上午10:43:13
 */
public interface GroupService {
    /**
     * 根据id查询Group
     *
     * @param Long id
     * @return Group
     */
    public SysGroup getGroupById(Long id);

    /**
     * 根据所有的Group
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
     * 删除多个groupid,以逗号间隔参数
     *
     * @param groupIds
     */
    public void deleteGroups(String groupIds);


    /**
     * 添加组
     *
     * @param group
     */
    public void addGroup(SysGroup group);

    /**
     * 修改组
     *
     * @param group
     */
    public void updateGroup(SysGroup group);

}
