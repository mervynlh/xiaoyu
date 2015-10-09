package com.yizhilu.os.edu.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.yizhilu.os.core.util.ObjectUtils;

import org.apache.log4j.Logger;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.common.util.DateEditor;
import com.yizhilu.os.core.controller.BaseController;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.core.util.web.WebUtils;

/**
 * @ClassName com.yizhilu.os.edu.controller.common.EduBaseController
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-5-27 下午2:11:59
 */
public class EduBaseController extends BaseController {
    protected static final String EDU_VIEW_PATH = "edu";// edu的view路径
    static MemCache memCache = MemCache.getInstance();
    protected static final String msgjsp = ("/common/frontmsg");
   // log对象
    private static final Logger logger = Logger.getLogger(EduBaseController.class);
    /**
     * 返回edu的view路径
     */
    public static String getViewPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            return "/" + EDU_VIEW_PATH + path;
        }
        return "";
    }

    /**
     * 获取登陆用户的id(前台用)
     * 
     * @return int
     * @throws Exception
     */
    protected static Long getLoginUserId(HttpServletRequest request) throws Exception {
      return SingletonLoginUtils.getLoginUserId(request);
    }
    /**
     * 得到城市id(前台用)
     * @param request
     * @return
     */
    protected static Long getCurrentCityId(HttpServletRequest request) {
    	return SingletonLoginUtils.getCurrentCityId(request);
	}
    
    /**
     * 是否登录
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        return SingletonLoginUtils.isLogin(request);
    }

    /** spring接受date类型转换
     */
    @InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

    /**
     * 获取系统给管理员姓名
     * @param request
     * @return
     */
    public String getSysLoginLoginName (HttpServletRequest request){
        JsonObject syuser =SingletonLoginUtils.getSysUser(request);
        if(ObjectUtils.isNotNull(syuser)){
            return syuser.get("loginName").toString();
        }
        return "";
    }
    /**
     * 获取系统管理员id
     * @param request
     * @return
     */
    public Long getSysLoginUserId (HttpServletRequest request){
        return SingletonLoginUtils.getSysUserId(request);
    }
    
    protected Long getLoginSubjectId(HttpServletRequest request) {
        try {
            String ukey = WebUtils.getCookie(request, CommonConstants.COOKIE_SUBJECTID_KEY);
            if (ukey == null || ukey.trim().equals("")) {
                return 0L;
            } else {
                return Long.valueOf(ukey);
            }
        } catch (Exception e) {
            logger.error("SubjectAction.getLoginSubjectId", e);
        }
        return 0L;
    }
    protected Long getUpLoginId(HttpServletRequest request) {
        try {
            String ukey = WebUtils.getCookie(request, CommonConstants.UP_USER_ID);
            if (ukey == null || ukey.trim().equals("")) {
                return 0L;
            } else {
                return Long.valueOf(ukey);
            }
        } catch (Exception e) {
            logger.error("EduBaseController.getUpLoginId", e);
        }
        return 0L;
    }
}
