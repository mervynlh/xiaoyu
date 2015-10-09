package com.yizhilu.os.edu.service.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.dao.user.UserExpandDao;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
import com.yizhilu.os.edu.service.user.UserExpandService;

/**
 * UserExpand管理接口 User: qinggang.liu Date: 2014-01-10
 */
@Service("userExpandService")
public class UserExpandServiceImpl implements UserExpandService {
    @Autowired
    private UserExpandDao userExpandDao;

    private MemCache memCache = MemCache.getInstance();

    /**
     * 添加UserExpand
     * 
     * @param userExpand
     *            要添加的UserExpand
     * @return id
     */
    public Long addUserExpand(UserExpand userExpand) {
        return userExpandDao.addUserExpand(userExpand);
    }

    /**
     * 修改UserExpand
     * 
     * @param userExpand
     *            要修改的UserExpand
     */
    public void updateUserExpand(UserExpand userExpand) {
        // 删除缓存
        memCache.remove(MemConstans.USEREXPAND_INFO + userExpand.getCusId());
        memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
        userExpandDao.updateUserExpand(userExpand);
    }

    public void updateUserSign(UserExpand userExpand){
    	 // 删除缓存
        memCache.remove(MemConstans.USEREXPAND_INFO + userExpand.getCusId());
        memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
        this.userExpandDao.updateUserSign(userExpand);
    }
    
    /**
     * 修改UserExpand的上传头像
     */
    public void updateAvatarById(UserExpand userExpand) {
        // 删除缓存
        memCache.remove(MemConstans.USEREXPAND_INFO + userExpand.getCusId());
        memCache.remove(MemConstans.RECOMMEND_TEACHER_ALL);
        userExpandDao.updateAvatarById(userExpand);
    }


    /**
     * 根据id获取单个UserExpand对象
     * 
     * @param userId
     *            要查询的id
     * @return UserExpand
     */
    public UserExpand getUserExpandByUserId(Long userId) {
        return userExpandDao.getUserExpandByUserId(userId);
    }

    /**
     * 根据条件获取UserExpand列表
     * 
     * @param userExpand
     *            查询条件
     * @return List<UserExpand>
     */
    public List<UserExpand> getUserExpandList(UserExpand userExpand) {
        return userExpandDao.getUserExpandList(userExpand);
    }
    
    /**
     * 拼用户id加逗号间隔
     * @param queryCustomerList
     * @return
     */
    public String getQueryCustomerListCusId(List<UserExpandDto> queryCustomerList) {// 获得用户ids
        String ids = "";
        if (queryCustomerList != null && queryCustomerList.size() > 0) {
            for (UserExpandDto queryCustomer : queryCustomerList) {
                ids += queryCustomer.getCusId() + ",";
            }
        }
        return ids;
    }

   

    /**
     * 通过showname 查询customer
     * 
     * @param showName
     */
    public List<UserExpandDto> queryCustomerByShowName(String showName, int size) {
        List<UserExpandDto> customerList = userExpandDao.queryCustomerByShowName(showName, size);// 通过showname
        return customerList;
    }

    public String getCustomerListCusId(List<UserExpand> customerList) {// 获得用户ids
        String ids = "";
        if (customerList != null && customerList.size() > 0) {
            for (UserExpand customer : customerList) {
                ids += customer.getCusId() + ",";
            }
        }
        return ids;
    }

    /**
     * 通过集合cusId 查询customer 返回map
     * 
     * @param cusIds
     * @return
     * @throws Exception
     */
    public Map<String, UserExpandDto> queryCustomerInCusIds(List<Long> cusIds) throws Exception {
        if(ObjectUtils.isNotNull(cusIds)){
            Map<String, UserExpandDto> map = new HashMap<String, UserExpandDto>();
            // 通过传入的cusIds 查询customer
            List<UserExpandDto> queryCustomerList = userExpandDao.queryUsersByIds(cusIds);
            // 整理数据放回map
            if (ObjectUtils.isNotNull(queryCustomerList)) {
                for (UserExpandDto queryCustomer : queryCustomerList) {
                    map.put(queryCustomer.getId().toString(), queryCustomer);
                }
            }
            return map;
        }else{
            return null;
        }
       
    }
    

    /**
     * 根据用户uid获取用户信息
     *
     * @param uids
     * @return
     */
    public Map<String, UserExpandDto> getUserExpandByUids(String uids) throws Exception {
        String [] arrays=uids.split(",");
        List<Long> list = new ArrayList<Long>();
        for(String lo:arrays){
            if(StringUtils.isNotEmpty(lo)&&!"null".equals(lo)){
                list.add(Long.valueOf(lo));
            }
        }
        return queryCustomerInCusIds(list);
    }

    /**
     * 根据用户uid获取用户信息
     *
     * @param cusId
     * @return
     */
    public UserExpandDto getUserExpandByUids(Long cusId) {
        UserExpandDto dto=(UserExpandDto) memCache.get(MemConstans.USEREXPAND_INFO+cusId);
        if(ObjectUtils.isNull(dto)){
            dto= userExpandDao.queryUserByid(cusId);
            if(ObjectUtils.isNotNull(dto)){
                dto.setShowname(dto.getShowname());
                memCache.set(MemConstans.USEREXPAND_INFO+cusId, dto,MemConstans.USEREXPAND_INFO_TIME);
            }
        }
        return dto;
    }
    
    /**
	 * 根据用户uid获取用户信息 （非缓存）
	 * 
	 * @param uids
	 * @return
	 */
	public UserExpandDto getUserExpandByUid(Long uid){
		return userExpandDao.queryUserByid(uid);
	}

    public String checkString(Object str) {// 检查字符串空的方法
        if (ObjectUtils.isNotNull(str) && !"null".equals(str.toString())) {
            return str.toString();
        } else {
            return "";
        }
    }

    public String checkInteger(Object str) {// 检查字符串空的方法
        if (ObjectUtils.isNotNull(str) && !"null".equals(str.toString())) {
            return str.toString();
        } else {
            return "0";
        }
    }

    /**
     * 补齐用户的信息list对象必须包含字段id
     * @return
     */
    public List<UserExpandDto> setUserExpandDtoInfo(List<UserExpandDto> list)  throws Exception{
        List<Long> cusIds = new ArrayList<Long>();
        for(UserExpandDto dto:list){
            cusIds.add(dto.getId());
        }
        Map<String, UserExpandDto>  map= queryCustomerInCusIds(cusIds);
        for(UserExpandDto dto:list){
            UserExpandDto dto2 = map.get(dto.getId());
            if(ObjectUtils.isNotNull(dto2)){
                dto.setNickname(dto2.getNickname());
                dto.setMobile(dto2.getMobile());
                dto.setEmail(dto2.getEmail());
                dto.setCusId(dto2.getCusId());
                dto.setRealname(dto2.getRealname());
                dto.setGender(dto2.getGender());
                dto.setAvatar(dto2.getAvatar());
                dto.setCommonFriendNum(dto2.getCommonFriendNum());
                dto.setMsgNum(dto2.getMsgNum());
                dto.setLastSystemTime(dto2.getLastSystemTime());
            }
        }
        return list;
    }
    /**
     * 通过showname 查询customer(精确搜索)
     * 
     */
    public List<UserExpandDto> queryCustomerByShowNameEquals(String showName){
        return userExpandDao.queryCustomerByShowNameEquals(showName);
    }
    /**
	 * 更新个人中心用户封面
	 * 
	 * @param userId
	 */
    public void updateUserExpandBannerUrl(Long userId, String bannerUrl) {
    	userExpandDao.updateUserExpandBannerUrl(userId, bannerUrl);
    }
   
   


	/**
	 * 查询全部好友
	 * 
	 * @param customer
	 *            好友实体
	 * @param page
	 *            分页参数
	 * @return List<QueryCustomer>
	 * @throws Exception
	 */
	public List<UserExpandDto> queryAllCustomer(UserExpandDto customer, PageEntity page){
		return userExpandDao.queryAllCustomer(customer, page);
	}
	
	/**
	 * 通过标识更新未读数加一
	 */
	public void updateUnReadMsgNumAddOne(String falg, Long cusId){
		userExpandDao.updateUnReadMsgNumAddOne(falg, cusId);
	}

	/**
	 * 通过标识更新未读数清零
	 */
	public void updateUnReadMsgNumReset(String falg, Long cusId){
		userExpandDao.updateUnReadMsgNumReset(falg, cusId);
	}

	/**
	 * 更新用户的上传统计系统消息时间
	 */
	public void updateCusForLST(Long cusId, Date date){
		userExpandDao.updateCusForLST(cusId, date);
	}
	/**
     * 要更新的数量类型
     * 修改UserExpand浏览次数
     * @param SnsUserExpand 
     */
    public void updateUserExpandCount(String type,Long userId,Long count,String operation){
    	userExpandDao.updateUserExpandCount(type, userId, count, operation);
    }
}