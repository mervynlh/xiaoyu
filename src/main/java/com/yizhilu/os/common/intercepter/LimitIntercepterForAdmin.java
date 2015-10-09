package com.yizhilu.os.common.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.JsonObject;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.core.service.cache.MemCache;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.sysuser.constants.SysUserConstants;
import com.yizhilu.os.sysuser.entity.QueryFunctionCondition;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.service.FunctionService;
import com.yizhilu.os.sysuser.service.RoleService;
import com.yizhilu.os.sysuser.service.SysUserService;

/**
 * @author liuqinggang
 * @ClassName LimitIntercepterForAdmin
 * @package com.yizhilu.os.sns.common.intercepter
 * @description 后台用户拦截器
 * @Create Date: 2013-3-1 下午12:56:43
 */
public class LimitIntercepterForAdmin extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(LimitIntercepterForAdmin.class);
    MemCache memCache =MemCache.getInstance();
    // admin用户唯一ukey
    public static final String COOKIE_ADMIN_KEY = "sidadmink";

    @Autowired
    private FunctionService functionService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       
        boolean flag = super.preHandle(request, response, handler);
        // 访问的路径
        String invokeUrl = request.getContextPath() + request.getServletPath();
        if(!"1".equals(CommonConstants.l)){
            return false;
        }
        boolean handflag = false;
        JsonObject user =  SingletonLoginUtils.getSysUser(request);
        if (ObjectUtils.isNotNull(user)) {//已经登录了验证权限
                flag = true;
                // 取网站的所有权限
                List<SysFunction> functionList = (List<SysFunction>) memCache.get(MemConstans.SYS_USER_ALL_FUNCTION);
                if (ObjectUtils.isNull(functionList)) {
                    functionList = functionService.getFunctionList(new QueryFunctionCondition());
                    memCache.set(MemConstans.SYS_USER_ALL_FUNCTION,functionList,MemConstans.SYS_USER_ALL_FUNCTION_TIME);
                }
                //判断当前访问的权限，是否在限制中
                boolean hasFunction = false;
                for (SysFunction function : functionList) {
                    if (function.getFunctionUrl() != null && function.getFunctionUrl().indexOf(invokeUrl) != -1) {
                        hasFunction = true;
                    }
                }
                if (!hasFunction) {// 如果权限中不包含此url,代表不对此url限制，返回成功
                    return true;
                }
                String sid = WebUtils.getCookie(request, SysUserConstants.sidadmin);
                // 访问路径在权限管理中时再做验证,取当前登录用户的权限
                List<SysFunction> tabUserFunctionList = (List<SysFunction>) memCache.get(MemConstans.SYS_USER_FUNCTION+sid+user.get("userId").getAsString());
                if(ObjectUtils.isNull(tabUserFunctionList)){
                    tabUserFunctionList=sysUserService.getUserFunction(user.get("userId").getAsLong());
                }
                 if(ObjectUtils.isNotNull(tabUserFunctionList)){
                        for (SysFunction function : tabUserFunctionList) {
                            if (function != null && function.getFunctionUrl().indexOf(invokeUrl) != -1) {
                                handflag = true;
                            }
                    }
                }
                if (handflag) {
                    logger.info("++ user action pass  userName:" + user + ",invokeUrl:" + invokeUrl);
                    flag = true;
                } else {
                    logger.info("++ user want access limit url userName:" + user + ",invokeUrl:" + invokeUrl);
                    response.sendRedirect("/cus/limitVerifyError");// 跳转权限受限制页面
                    flag = false;
                }
        }else {
            logger.info("++ admin intercpter user not login:");
            response.sendRedirect("/admin/sys/login");// 未登录状态跳转到登录页面
            flag = false;
        }

        return flag;
    }

}
