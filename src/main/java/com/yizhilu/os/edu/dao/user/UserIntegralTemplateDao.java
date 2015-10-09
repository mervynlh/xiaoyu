package com.yizhilu.os.edu.dao.user;

import java.util.List;
import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;

/**
 * UserIntegralTemplate管理接口 User: qinggang.liu Date: 2014-05-27
 */
public interface UserIntegralTemplateDao {

	/**
	 * 添加UserIntegralTemplate
	 * 
	 * @param userIntegralTemplate
	 *            要添加的UserIntegralTemplate
	 * @return id
	 */
	public java.lang.Long addUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate);

	/**
	 * 根据id删除一个UserIntegralTemplate
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralTemplateById(Long id);

	/**
	 * 修改UserIntegralTemplate
	 * 
	 * @param userIntegralTemplate
	 *            要修改的UserIntegralTemplate
	 */
	public void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate);

	/**
	 * 根据id获取单个UserIntegralTemplate对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralTemplate
	 */
	public UserIntegralTemplate getUserIntegralTemplateById(Long id);

	/**
	 * 获取UserIntegralTemplate列表
	 * 
	 * @return
	 */
	public List<UserIntegralTemplate> getUserIntegralTemplateList();

	/**
	 * 更新状态 正常 停止
	 * 
	 * @param userIntegralTemplat
	 */
	public void updateUserIntegralTemplateStatus(UserIntegralTemplate userIntegralTemplate);

	/**
	 * 查询积分模板根据关键字
	 * 
	 * @param keyword
	 * @return
	 */
	public UserIntegralTemplate getUserIntegralTemplateByKeyword(String keyword);
}