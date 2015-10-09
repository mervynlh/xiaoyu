package com.yizhilu.os.edu.dao.help;

import java.util.List;

import com.yizhilu.os.edu.entity.help.HelpMenu;





public interface HelpMenuDao {
	
	/**
	 * 查询所有一级菜单 
	 * @return HelpMenu
	 */
    public List<HelpMenu> getHelpMenuOne();
    /**
	 * 根据一级菜单ID查询二级菜单 
	 * @return HelpMenu
	 */
	public List<HelpMenu> getHelpMenuTwoByOne(Long id);
    /**
     * 删除菜单
     * @param id
     */
    public void delHelpMenuById(Long id);
    /**
     * 更新菜单
     * @param HelpMenu
     */
    public void updateHelpMenuById(HelpMenu helpMenu);
    /**
     *  添加菜单
     * @param HelpMenu
     * @return id
     */
    public Long createHelpMenu(HelpMenu helpMenu);
    /**
     * 根据ID查找菜单
     * @param id
     * @return
     */
    public HelpMenu getHelpMenuById(Long id);
}

