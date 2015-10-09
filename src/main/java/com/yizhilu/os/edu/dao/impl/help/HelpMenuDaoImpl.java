package com.yizhilu.os.edu.dao.impl.help;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhilu.os.core.dao.impl.common.GenericDaoImpl;
import com.yizhilu.os.edu.dao.help.HelpMenuDao;
import com.yizhilu.os.edu.entity.help.HelpMenu;


@Repository("helpMenuDao")
public class HelpMenuDaoImpl extends GenericDaoImpl implements HelpMenuDao {
	
	/**
	 * 查询所有一级菜单 
	 * @return HelpMenu
	 */
    public List<HelpMenu> getHelpMenuOne(){
    	return this.selectList("HelpMenuMapper.getHelpMenuOneAll", 0);
    }
    /**
	 * 根据一级菜单ID查询二级菜单 
	 * @return HelpMenu
	 */
	public List<HelpMenu> getHelpMenuTwoByOne(Long id){
		return this.selectList("HelpMenuMapper.getHelpMenuTwoByOneId", id);
	}
    /**
     * 删除菜单
     * @param id
     */
    public void delHelpMenuById(Long id){
    	this.delete("HelpMenuMapper.delHelpMenuById", id);
    }
    /**
     * 更新菜单
     * @param HelpMenu
     */
    public void updateHelpMenuById(HelpMenu helpMenu){
    	this.update("HelpMenuMapper.updateHelpMenuById", helpMenu);
    }
    /**
     *  添加菜单
     * @param HelpMenu
     * @return id
     */
    public Long createHelpMenu(HelpMenu helpMenu){
    	return this.insert("HelpMenuMapper.createHelpMenu", helpMenu);
    }
    /**
     * ID查找菜单
     * @param id
     * @return
     */
    public HelpMenu getHelpMenuById(Long id){
    	return this.selectOne("HelpMenuMapper.getHelpMenuById", id);
    }
}

