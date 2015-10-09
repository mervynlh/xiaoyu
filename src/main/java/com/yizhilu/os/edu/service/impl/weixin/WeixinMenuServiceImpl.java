package com.yizhilu.os.edu.service.impl.weixin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.dao.weixin.WeixinMenuDao;
import com.yizhilu.os.edu.entity.weixin.WeixinMenu;
import com.yizhilu.os.edu.service.weixin.WeixinMenuService;


@Service("weixinMenuService")
public class WeixinMenuServiceImpl implements WeixinMenuService{
	
	@Autowired
    private WeixinMenuDao weixinMenuDao; 
	/**
	 * 获取自定义菜单一级菜单集合
	 * @return 一级菜单集合
	 */
	public List<WeixinMenu> getWeixinFirstMenu(){
		return weixinMenuDao.getWeixinFirstMenu();
	}
	/**
	 * 根据一级菜单ID获取自定义菜单二级菜单集合
	 * @return 二级菜单集合
	 */
	public List<WeixinMenu> getWeixinSecondMenuByParentId(Long id){
		return weixinMenuDao.getWeixinSecondMenuByParentId(id);
	}
	/**
	 * 获取自定义菜单启用一级菜单集合
	 * @return 一级菜单集合
	 */
	public List<WeixinMenu> getWeixinFirstEnableMenu(){
		return weixinMenuDao.getWeixinFirstEnableMenu();
	}
	/**
	 * 根据一级菜单ID获取自定义菜单启用二级菜单集合
	 * @return 二级菜单集合
	 */
	public List<WeixinMenu> getWeixinSecondEnableMenuByParentId(Long id){
		return weixinMenuDao.getWeixinSecondEnableMenuByParentId(id);
	}
	/**
	 * 根据ID删除菜单及子菜单
	 */
	public void delWeixinMenuById(Long id){
		weixinMenuDao.delWeixinMenuById(id);
	}
	
	/**
	 * 添加菜单
	 */
	public Long addWeixinMenu(WeixinMenu weixinMenu){
		return weixinMenuDao.addWeixinMenu(weixinMenu);
	}
	/**
	 * 更新菜单
	 */
	public void updateWeixinMenu(WeixinMenu weixinMenu){
		weixinMenuDao.updateWeixinMenu(weixinMenu);
	}
	
}


