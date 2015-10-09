package com.yizhilu.os.common.intercepter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.edu.entity.user.UserExpandDto;

/**
 * 
 * @ClassName  com.yizhilu.os.common.intercepter.LimitIntercepterForUserAccessUrl
 * @description 网站配置管理拦截器
 * @author :xujunbao
 * @Create Date : 2014年10月9日 下午5:50:49
 */
public class LimitIntercepterForUserAccessUrl extends HandlerInterceptorAdapter{
    public static Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
     @Override
     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         super.afterCompletion(request, response, handler, ex);
     }

     @Override
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         super.postHandle(request, response, handler, modelAndView);
     }
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 super.preHandle(request, response, handler);
		 // 获得登录用户信息
		 JsonObject userJsonObject = SingletonLoginUtils.getLoginUser(request);
		 UserExpandDto user = gson.fromJson(userJsonObject, new TypeToken<UserExpandDto>(){}.getType());
         // 登录用户为教师
		 if (user.getUserType() == 1){
             if (request.getRequestURL().indexOf("/uc/student") != -1){
                 // 跳转去教师首页
                 response.sendRedirect("/uc/teacher/home");
                 return false;
             }
             return true;
		 } else { // 登录用户为学生
            if (request.getRequestURL().indexOf("/uc/teacher") != -1){
                // 跳转去教师首页
                response.sendRedirect("/uc/student/home");
                return false;
            }
             return true;
         }
    } 
}
