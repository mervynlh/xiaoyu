package com.yizhilu.os.edu.service.user;


import java.util.Date;
import java.util.List;
import java.util.Map;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.edu.entity.user.UserExpand;
import com.yizhilu.os.edu.entity.user.UserExpandDto;
/**
 * UserExpand管理接口 User: qinggang.liu Date: 2014-01-10
 */
public interface UserExpandService {

	/**
	 * 添加UserExpand
	 * 
	 * @param userExpand
	 *            要添加的UserExpand
	 * @return id
	 */
	public Long addUserExpand(UserExpand userExpand);

	/**
	 * 修改UserExpand
	 * 
	 * @param userExpand
	 *            要修改的UserExpand
	 */
	public void updateUserExpand(UserExpand userExpand);

	 public void updateUserSign(UserExpand userExpand);
	/**
	 * 修改UserExpand的上传头像
	 */
	public void updateAvatarById(UserExpand userExpand);


	/**
	 * 根据id获取单个UserExpand对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserExpand
	 */
	public UserExpand getUserExpandByUserId(Long userId);

	/**
	 * 根据条件获取UserExpand列表
	 * 
	 * @param userExpand
	 *            查询条件
	 * @return List<UserExpand>
	 */
	public List<UserExpand> getUserExpandList(UserExpand userExpand);

	

	/**
	 * 通过showname 查询customer
	 * 
	 * @param showName
	 */
	public List<UserExpandDto> queryCustomerByShowName(String showName, int size);


	/**
	 * 通过集合cusId 查询customer 返回map
	 * 
	 * @param cusIds
	 * @return
	 * @throws Exception
	 */
	public Map<String, UserExpandDto> queryCustomerInCusIds(List<Long> cusIds) throws Exception;


	/**
	 * 根据用户uid获取用户信息
	 * 
	 * @param uids
	 * @return
	 */
	public Map<String, UserExpandDto> getUserExpandByUids(String uids) throws Exception;

	/**
	 * 根据用户uid获取用户信息
	 * 
	 * @param uids
	 * @return
	 */
	public UserExpandDto getUserExpandByUids(Long uids);
	
	/**
	 * 根据用户uid获取用户信息 （非缓存）
	 * 
	 * @param uids
	 * @return
	 */
	public UserExpandDto getUserExpandByUid(Long uid);

	/**
	 * 补齐用户的信息list对象必须包含字段id
	 * 
	 * @return
	 */
	public List<UserExpandDto> setUserExpandDtoInfo(List<UserExpandDto> list) throws Exception;
	/**
     * 通过showname 查询customer(精确搜索)
     * 
     */
    public List<UserExpandDto> queryCustomerByShowNameEquals(String showName);
	/**
	 * 更新个人中心用户封面
	 * 
	 * @param userId
	 */
	public void updateUserExpandBannerUrl(Long userId, String bannerUrl);

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
	public List<UserExpandDto> queryAllCustomer(UserExpandDto customer, PageEntity page);
	
	/**
	 * 通过标识更新未读数加一
	 */
	public void updateUnReadMsgNumAddOne(String falg, Long cusId);

	/**
	 * 通过标识更新未读数清零
	 */
	public void updateUnReadMsgNumReset(String falg, Long cusId);

	/**
	 * 更新用户的上传统计系统消息时间
	 */
	public void updateCusForLST(Long cusId, Date date);
	/**
     * 要更新的数量类型
     * 修改UserExpand浏览次数
     * @param SnsUserExpand 
     */
    public void updateUserExpandCount(String type,Long userId,Long count,String operation);

}