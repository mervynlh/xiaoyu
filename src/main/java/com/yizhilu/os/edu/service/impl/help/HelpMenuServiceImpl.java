package com.yizhilu.os.edu.service.impl.help;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.edu.dao.help.HelpMenuDao;
import com.yizhilu.os.edu.entity.help.HelpMenu;
import com.yizhilu.os.edu.service.help.HelpMenuService;


@Service("helpMenuService")
public class HelpMenuServiceImpl implements HelpMenuService{
	MemCache memCache = MemCache.getInstance();
	@Autowired
	private HelpMenuDao helpMenuDao;
	
	/**
	 * 查询所有菜单 
	 * @return HelpMenu
	 */
    public List<List<HelpMenu>> getHelpMenuAll(){
    	@SuppressWarnings("unchecked")
		List<List<HelpMenu>> helpMenus=(List<List<HelpMenu>>) memCache.get(MemConstans.HELP_CENTER);
    	if(helpMenus!=null){
    		return helpMenus;
    	}
    	helpMenus=new ArrayList<List<HelpMenu>>();//用于前台显示的菜单集合
		List<HelpMenu> HelpMenuOnes=getHelpMenuOne();//一级菜单集合
		for(HelpMenu HelpMenuOne:HelpMenuOnes)
		{
			List<HelpMenu> helpMenuOneAndTwo=new ArrayList<HelpMenu>();//一级菜单+该菜单的二级菜单集合
			List<HelpMenu> helpMenuTwos=getHelpMenuTwoByOne(HelpMenuOne.getId());//二级菜单集合
			helpMenuOneAndTwo.add(HelpMenuOne);
			for(HelpMenu helpMenuTwo:helpMenuTwos)//组装一级菜单和二级菜单
			{
				helpMenuOneAndTwo.add(helpMenuTwo);
			}
			helpMenus.add(helpMenuOneAndTwo);
		}
		if(helpMenus!=null){
			memCache.set(MemConstans.HELP_CENTER, helpMenus,MemConstans.HELP_CENTER_TIME);
		}
		return helpMenus;
    }
	/**
	 * 查询所有一级菜单 
	 * @return helpMenu
	 */
    public List<HelpMenu> getHelpMenuOne(){
    	return helpMenuDao.getHelpMenuOne();
    }
    /**
	 * 根据一级菜单ID查询二级菜单 
	 * @return helpMenu
	 */
	public List<HelpMenu> getHelpMenuTwoByOne(Long id){
		return helpMenuDao.getHelpMenuTwoByOne(id);
	}
    /**
     * 删除菜单
     * @param id
     */
    public void delHelpMenuById(Long id){
    	helpMenuDao.delHelpMenuById(id);
    	memCache.remove(MemConstans.HELP_CENTER);
    }
    /**
     * 更新菜单
     * @param helpMenu
     */
    public void updateHelpMenuById(HelpMenu helpMenu){
    	helpMenuDao.updateHelpMenuById(helpMenu);
    	memCache.remove(MemConstans.HELP_CENTER);
    }
    /**
     *  添加菜单
     * @param helpMenu
     * @return id
     */
    public Long createHelpMenu(HelpMenu helpMenu){
    	memCache.remove(MemConstans.HELP_CENTER);
    	return helpMenuDao.createHelpMenu(helpMenu);
    	
    }
    /**
     * 根据ID查找菜单
     * @param id
     * @return
     */
    public HelpMenu getHelpMenuById(Long id){
    	return helpMenuDao.getHelpMenuById(id);
    }
    
}

