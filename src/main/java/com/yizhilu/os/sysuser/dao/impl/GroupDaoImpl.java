package com.yizhilu.os.sysuser.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.sysuser.dao.GroupDao;
import com.yizhilu.os.sysuser.entity.SysGroup;

/**
 * @author liuqinggang
 * @ClassName GroupDaoImpl
 * @package com.supergenius.sns.dao.impl.user
 * @description
 * @Create Date: 2013-3-5 下午02:48:53
 */
@Repository("groupDao")
public class GroupDaoImpl extends GenericDaoImpl implements GroupDao {
    /**
     * 根据id查询Group
     *
     * @param id
     * @return Group
     */
    public SysGroup getGroupById(Long id) {
        return this.selectOne("GroupMapper.getGroupById", id);

    }

    /**
     * 查询所有的Group
     *
     * @return List<Group>
     */
    public List<SysGroup> getGroupList() {
        return this.selectList("GroupMapper.getGroupList", null);
    }

    /**
     * 根据parentId查询Group
     *
     * @param parentId
     * @return List<Group>
     */
    public List<SysGroup> getChildGroupById(Long parentId) {
        return this.selectList("GroupMapper.getChildGroupById", parentId);
    }

    /**
     * 根据id删除group
     *
     * @param id
     */
    public void deleteGroupById(Long id) {
        this.delete("GroupMapper.deleteGroupById", id);
    }

    /**
     * 根据id更新group
     *
     * @param group
     */
    public void updateGroup(SysGroup group) {
        this.update("GroupMapper.updateGroup", group);
    }

    /**
     * 添加组
     *
     * @param group
     */
    public void addGroup(SysGroup group) {
        this.insert("GroupMapper.addGroup", group);
    }
}
