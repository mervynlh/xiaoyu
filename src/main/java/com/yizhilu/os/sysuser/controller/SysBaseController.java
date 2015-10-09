package com.yizhilu.os.sysuser.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.controller.BaseController;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.sysuser.entity.SysUser;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.supergenius.sns.action.common.CommonAction
 * @description 通用的action.所有的Controller继承，公用的写到此方法中
 * @Create Date : 2013-12-13 下午2:30:00
 */
public class SysBaseController extends BaseController {

    protected static final String SYSUSER_VIEW_PATH = "sysuser";// sysuser的view路径
    MemCache memCache =MemCache.getInstance();

    /**
     * 获得系统登录用户
     *
     * @param request
     * @return
     */
    public SysUser getSysLoginedUser(HttpServletRequest request) {
    	JsonObject jsonObject=SingletonLoginUtils.getSysUser(request);
		Gson gson=new Gson();
		SysUser sysUser=gson.fromJson(gson.toJson(jsonObject),SysUser.class); 
       
        return  sysUser;
    }

    /**
     * 返回sns的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + SYSUSER_VIEW_PATH + path;
        }
        return "";
    }

}
