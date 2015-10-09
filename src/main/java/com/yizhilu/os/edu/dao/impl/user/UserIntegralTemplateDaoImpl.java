package com.yizhilu.os.edu.dao.impl.user;

import java.util.List;

import com.yizhilu.os.edu.entity.user.UserIntegralTemplate;
import com.yizhilu.os.edu.dao.user.UserIntegralTemplateDao;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.core.util.ObjectUtils;

/**
 * 
 * UserIntegralTemplate User: qinggang.liu voo@163.com Date: 2014-05-27
 */
@Repository("userIntegralTemplateDao")
public class UserIntegralTemplateDaoImpl extends GenericDaoImpl implements UserIntegralTemplateDao {
	/**
	 * 添加UserIntegralTemplate
	 * 
	 * @param userIntegralTemplate
	 *            要添加的UserIntegralTemplate
	 * @return id
	 */
	public java.lang.Long addUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate) {
		return this.insert("UserIntegralTemplateMapper.createUserIntegralTemplate", userIntegralTemplate);
	}

	/**
	 * 根据id删除一个UserIntegralTemplate
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserIntegralTemplateById(Long id) {
		this.delete("UserIntegralTemplateMapper.deleteUserIntegralTemplateById", id);
	}

	/**
	 * 修改UserIntegralTemplate
	 * 
	 * @param userIntegralTemplate
	 *            要修改的UserIntegralTemplate
	 */
	public void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate) {
		this.update("UserIntegralTemplateMapper.updateUserIntegralTemplate", userIntegralTemplate);
	}

	/**
	 * 根据id获取单个UserIntegralTemplate对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserIntegralTemplate
	 */
	public UserIntegralTemplate getUserIntegralTemplateById(Long id) {
		return this.selectOne("UserIntegralTemplateMapper.getUserIntegralTemplateById", id);
	}

	/**
	 * 根据条件获取UserIntegralTemplate列表
	 * 
	 * @param userIntegralTemplate
	 *            查询条件
	 * @return List<UserIntegralTemplate>
	 */
	public List<UserIntegralTemplate> getUserIntegralTemplateList() {
		return this.selectList("UserIntegralTemplateMapper.getUserIntegralTemplateList",null);
	}
	/**
	 * 更新状态 正常 停止
	 * 
	 * @param userIntegralTemplat
	 */
	public void updateUserIntegralTemplateStatus(UserIntegralTemplate userIntegralTemplate) {
		this.update("UserIntegralTemplateMapper.updateUserIntegralTemplateStatus", userIntegralTemplate);
	}
	/**
	 * 查询积分模板根据关键字
	 * 
	 * @param keyword
	 * @return
	 */
	public UserIntegralTemplate getUserIntegralTemplateByKeyword(String keyword) {
		List<UserIntegralTemplate> userIntegralTemplateList=this.selectList("UserIntegralTemplateMapper.getUserIntegralTemplateByKeyword", keyword);
		if(ObjectUtils.isNotNull(userIntegralTemplateList)&&userIntegralTemplateList.size()>0){
			return userIntegralTemplateList.get(0);
		}
		return null;
	}
}
