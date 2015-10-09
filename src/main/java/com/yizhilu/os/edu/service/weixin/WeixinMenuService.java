package com.yizhilu.os.edu.service.weixin;


import java.util.List;
import com.yizhilu.os.edu.entity.weixin.WeixinMenu;



public interface WeixinMenuService {
	
	/**
	 * 获取自定义菜单一级菜单集合
	 * @return 一级菜单集合
	 */
	public List<WeixinMenu> getWeixinFirstMenu();
	/**
	 * 根据一级菜单ID获取自定义菜单二级菜单集合
	 * @return 二级菜单集合
	 */
	public List<WeixinMenu> getWeixinSecondMenuByParentId(Long id);
	/**
	 * 获取自定义菜单启用一级菜单集合
	 * @return 一级菜单集合
	 */
	public List<WeixinMenu> getWeixinFirstEnableMenu();
	/**
	 * 根据一级菜单ID获取自定义菜单启用二级菜单集合
	 * @return 二级菜单集合
	 */
	public List<WeixinMenu> getWeixinSecondEnableMenuByParentId(Long id);
	/**
	 * 根据ID删除菜单及子菜单
	 */
	public void delWeixinMenuById(Long id);
	
	/**
	 * 添加菜单
	 */
	public Long addWeixinMenu(WeixinMenu weixinMenu);
	/**
	 * 更新菜单
	 */
	public void updateWeixinMenu(WeixinMenu weixinMenu);
}


