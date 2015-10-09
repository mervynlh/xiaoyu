package com.yizhilu.os.edu.dao.impl.userprofile;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.userprofile.UserProfileDao;
import com.yizhilu.os.edu.entity.userprofile.UserProfile;

/**
 * 
 * UserProfile User: qinggang.liu voo@163.com Date: 2014-01-13
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends GenericDaoImpl implements UserProfileDao {

    public Long addUserProfile(UserProfile userProfile) {
        return this.insert("UserProfileMapper.createUserProfile", userProfile);
    }

    public void deleteUserProfileById(Long id) {
        this.delete("UserProfileMapper.deleteUserProfileById", id);
    }

    public void updateUserProfile(UserProfile userProfile) {
        this.update("UserProfileMapper.updateUserProfile", userProfile);
    }

    public List<UserProfile> getUserProfileByUserId(Long userid) {
        return this.selectList("UserProfileMapper.getUserProfileById", userid);
    }

    public List<UserProfile> getUserProfileList(UserProfile userProfile) {
        return this.selectList("UserProfileMapper.getUserProfileList", userProfile);
    }
}
