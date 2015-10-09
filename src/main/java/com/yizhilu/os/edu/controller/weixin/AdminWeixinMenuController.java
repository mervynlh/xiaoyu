package com.yizhilu.os.edu.controller.weixin;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.weixin.WeixinMenu;
import com.yizhilu.os.edu.service.weixin.WeixinMenuService;

/**
 * 微信菜单
 * 
 * @ClassName com.yizhilu.os.edu.controller.website.AdminWeixinMenuController
 * @description
 * @author :JJL
 * @Create Date : 2014年6月7日 上午9:47:26
 */
@Controller
@RequestMapping("/admin")
public class AdminWeixinMenuController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWeixinMenuController.class);

	@Autowired
	private WeixinMenuService weixinMenuService;

	private static final String getWeixinMenu = getViewPath("/admin/weixin/set/weixin_menu");
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("weixinMenu")
	public void initBinderWeixinMenu(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("weixinMenu.");
	}
	
    /**
    * 查询微信菜单
    * @return
    */
	@RequestMapping("/menu/query")
    public ModelAndView designMenu(HttpServletRequest request)
    {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(getWeixinMenu);
		try{
			List<List<WeixinMenu>> weixinMenus=new ArrayList<List<WeixinMenu>>();//页面显示菜单集合
			List<WeixinMenu> firstMenus=weixinMenuService.getWeixinFirstMenu();//获得一级菜单集合
			for(int i=0;i<firstMenus.size();i++)
			{
				List<WeixinMenu> weixinMenus1=new ArrayList<WeixinMenu>();//将一级菜单和所属二级菜单放在一个集合
				weixinMenus1.add(firstMenus.get(i));//将一级菜单存入集合
				List<WeixinMenu> secondMenus=weixinMenuService.getWeixinSecondMenuByParentId(firstMenus.get(i).getId());//获得该一级菜单的二级菜单集合
				for(int j=0;j<secondMenus.size();j++)//将该一级菜单的所属二级菜单存入集合
				{
					weixinMenus1.add(secondMenus.get(j));
				}
				for(int k=weixinMenus1.size();k<6;k++)//若二级菜单加所属菜单小于6，补足6用于页面显示
				{
					WeixinMenu menu1=new WeixinMenu(0L, 0L, "","",0, 0);
					weixinMenus1.add(menu1);
				}
				weixinMenus.add(weixinMenus1);//存入菜单集合
			}	
			for(int m=weixinMenus.size();m<3;m++)//若一级菜单小于3，补足3用于页面显示
			{
				List<WeixinMenu> weixinMenus1=new ArrayList<WeixinMenu>();
				for(int k=0;k<6;k++)
				{
					WeixinMenu menu1=new WeixinMenu(0L, 0L, "","",0, 0);
					weixinMenus1.add(menu1);
				}
				weixinMenus.add(weixinMenus1);
			}
			modelAndView.addObject("weixinMenus",weixinMenus);
		}catch(Exception e)
		{
			logger.error("AdminWeixinSetController.designMenu", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
   	 	return modelAndView;
    }
   /**
    * 根据ID删除菜单
    * @return
    */
	@RequestMapping("/menu/del/{id}")
	@ResponseBody
    public Map<String,Object> delWeixinMenu(@PathVariable Long id,HttpServletRequest request)
    {
    	try{
    		weixinMenuService.delWeixinMenuById(id);
    		this.setJson(true, "true", null);
	    }catch(Exception e)
		{
	    	logger.error("AdminWeixinSetController.delWeixinMenu", e);
			this.setJson(false, "error", null);
		}
	    return json;
    }
   /**
    * 更新菜单
    * @return
    */
	@RequestMapping("/menu/update")
	@ResponseBody
    public Map<String,Object> menuUpdate(WeixinMenu weixinMenu)
    {
    	try{
    		if(weixinMenu.getId()>0){//更新微信菜单项
    			weixinMenuService.updateWeixinMenu(weixinMenu);
    		}else{//添加微信菜单项
    			weixinMenuService.addWeixinMenu(weixinMenu);
    		}
    		this.setJson(true, "true", null);
		}catch(Exception e)
		{
			logger.error("AdminWeixinSetController.menuUpdate", e);
			this.setJson(false, "error", null);
		}
		return json;
   }
   
}
