package com.yizhilu.os.sysuser.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yizhilu.os.common.constants.MemConstans;
import com.yizhilu.os.core.service.cache.MemCache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.yizhilu.os.common.contoller.SingletonLoginUtils;
import com.yizhilu.os.common.util.UserAgentUtil;
import com.yizhilu.os.core.controller.RandomCodeController;
import com.yizhilu.os.core.entity.PageEntity;
import com.yizhilu.os.core.util.MD5;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.sysuser.constants.SysUserConstants;
import com.yizhilu.os.sysuser.entity.AdminLoginLog;
import com.yizhilu.os.sysuser.entity.LoginLog;
import com.yizhilu.os.sysuser.entity.QueryLoginLogCondition;
import com.yizhilu.os.sysuser.entity.SysFunction;
import com.yizhilu.os.sysuser.entity.SysUser;
import com.yizhilu.os.sysuser.service.FunctionService;
import com.yizhilu.os.sysuser.service.LoginLogService;
import com.yizhilu.os.sysuser.service.RoleService;
import com.yizhilu.os.sysuser.service.SysUserService;

/**
 * @author liuqinggang
 * @ClassName LoginAction
 * @package com.supergenius.sns.action.customer
 * @description 系统用户登录
 * @Create Date: 2013-12-13 下午10:21:44
 */
@Controller
@RequestMapping("/admin")
public class SysLoginController extends SysBaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysLoginController.class);

	public static String ERROR_LIMIT_VERIFY = "limitVerifyError";
    MemCache memCache =MemCache.getInstance();
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;
	// 返回页面定义
	private static String LOGIN = getViewPath("/login/login");// 登录页面
	private static String LOGINSUCCESS = "redirect:/admin/sys/main";// 登录成功跳转。do
	private static String adminMain = getViewPath("/login/main");// 登录成功主页面JSP
	private static String BOTTOMFRAME = getViewPath("/login/bottomFrame");// 登录成功bottomFrame
	private static String LEFTFRAME = getViewPath("/login/leftframe");// 登录成功leftframe
	private static String RIGHTFRAME = getViewPath("/login/rightframe");// 登录成功rightframe
	private static String TOPFRAME = getViewPath("/login/topframe");// 登录成功topframe
	private static String LOGINOUT = getViewPath("/login/loginOut");// 登出页面
	private static String LOGINLOG = getViewPath("/sysuser/user_login_log");// 登陆log

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("user")
	public void initBinder1(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("user.");
	}

	/**
	 * 后台登陆地址
	 */
	@RequestMapping("/")
	public String admin1() {
		return LOGIN;
	}

	@RequestMapping("")
	public String admin2() {
		return LOGIN;
	}

	/**
	 * 用户登录验证
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sys/login")
	public ModelAndView login(@ModelAttribute("user") SysUser user, HttpServletRequest request, HttpServletResponse response) {
		String returnPage = LOGIN;
		this.errmsg = "";
		try {

			SysUser userFromDb = null;
			if (user != null && !StringUtils.isBlank(user.getLoginName()) && !StringUtils.isBlank(user.getLoginPwd())) {
				logger.info("+++ do user login name:" + user.getLoginName());
				// 用户名长度
				if (user.getLoginName().length() < 6 || user.getLoginName().length() > 20) {
					this.errmsg = "用户名长度大于6，小于20!";
					ModelAndView modelAndView = new ModelAndView(LOGIN);
					modelAndView.addObject("errmsg", errmsg);
					return modelAndView;
				}
				// 密码长度
				if (user.getLoginPwd().length() < 6 || user.getLoginPwd().length() > 20) {
					this.errmsg = "用户名长度大于6，小于20!";
					ModelAndView modelAndView = new ModelAndView(LOGIN);
					modelAndView.addObject("errmsg", errmsg);
					return modelAndView;
				}
				// 验证码
				String randomCode = request.getParameter("randomCode");
				if (!randomCode.equals(request.getSession().getAttribute(RandomCodeController.RAND_CODE))) {
					request.getSession().removeAttribute(RandomCodeController.RAND_CODE);
					this.errmsg = "验证码输入错误！";
					ModelAndView modelAndView = new ModelAndView(LOGIN);
					modelAndView.addObject("errmsg", errmsg);
					return modelAndView;
				}
				// 查询用户
				userFromDb = sysUserService.getUserByLoginName(user.getLoginName());
				request.getSession().removeAttribute(RandomCodeController.RAND_CODE);
				// 验证密码是否正确
				if (userFromDb != null && userFromDb.getLoginPwd().equals(MD5.getMD5(user.getLoginPwd()))) {
                    String uuid = com.yizhilu.os.core.util.StringUtils.createUUID().replace("-", "");
					JsonParser jsonParser = new JsonParser();
					JsonObject jsonObject  = jsonParser.parse(new Gson().toJson(userFromDb)).getAsJsonObject();
                    memCache.set(uuid, jsonObject.toString(), MemConstans.SYS_USER_TIME);

                    WebUtils.setCookie(response, SysUserConstants.sidadmin, uuid,2);
					// 系统后台登录日志记录
					String ip = WebUtils.getIpAddr(request); // 获取登录人的IP并记录
					LoginLog loginLog = new LoginLog();
					loginLog.setLoginTime(new Date());
					loginLog.setIp(ip);
					loginLog.setUserId(userFromDb.getUserId());
					String userAgent=UserAgentUtil.getUserAgent(request);
					if(StringUtils.isNotEmpty(userAgent)){
						loginLog.setUserAgent(userAgent.split(";")[0]);//浏览器
						loginLog.setOsname(userAgent.split(";")[1]);//操作系统
					}
					loginLogService.addLoginLog(loginLog);
					logger.info(" ++++ sysuser login success: " + user.getLoginName() + " is logined ok !");

					returnPage = LOGINSUCCESS;
				} else {
					this.errmsg = "请输入正确的用户名和密码！";
					ModelAndView modelAndView = new ModelAndView(LOGIN);
					modelAndView.addObject("errmsg", errmsg);
					return modelAndView;
				}
			} else {
				this.errmsg = "请输入正确的用户名和密码！";
				ModelAndView modelAndView = new ModelAndView(LOGIN);
				modelAndView.addObject("errmsg", errmsg);
				return modelAndView;
			}
		} catch (Exception e) {
			logger.error("+++ do login error", e);
			this.errmsg = "请输入正确的用户名和密码！";
			ModelAndView modelAndView = new ModelAndView(LOGIN);
			modelAndView.addObject("errmsg", errmsg);
			return modelAndView;
		}
		logger.info("+++ return returnPage:" + returnPage);
		return new ModelAndView(returnPage);
	}

	/**
	 * 修改后台登陆用户ip地址
	 */
	public void updateAdminLoginLog() {
		List<AdminLoginLog> loginLogList = loginLogService.getLoginLog();
		for (AdminLoginLog adminLoginLog : loginLogList) {
			try {
				loginLogService.updateLoginLog(adminLoginLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 讲权限设置为List<List<SysFunction>>结构
	 * 
	 * @param funcList
	 * @return
	 */
	private List<List<SysFunction>> processTabUserFunctionList(List<SysFunction> funcList) {
		List<List<SysFunction>> tabList = new ArrayList<List<SysFunction>>();
		// 把第一级的筛选出来（parentId=0的）
		for (int i = 0; i < funcList.size(); i++) {
			SysFunction func = funcList.get(i);
			if (func.getParentFunctionId().longValue() == 0) {
				List<SysFunction> list = new ArrayList<SysFunction>();
				list.add(func);
				tabList.add(list);
			}
		}
		int count = funcList.size() + 1;
		while (count > funcList.size()) {
			count = funcList.size();
			for (int i = 0; i < funcList.size(); i++) {
				SysFunction func = funcList.get(i);
				for (int j = 0; j < tabList.size(); j++) {
					List<SysFunction> list = tabList.get(j);
					for (int k = 0; k < list.size(); k++) {
						if (list.get(k).getFunctionId().longValue() == func.getParentFunctionId().longValue()) {
							list.add(func);
							funcList.remove(func);
						}
					}
				}
			}
		}
		return tabList;
	}

	/**
	 * 获取第一级的权限
	 * 
	 * @param funcList
	 * @return
	 */
	private List<SysFunction> processTopTabUser(List<SysFunction> funcList) {
		List<SysFunction> tabList = new ArrayList<SysFunction>();
		// 把第一级的筛选出来（parentId=0的）
		for (int i = 0; i < funcList.size(); i++) {
			SysFunction func = funcList.get(i);
			if (func.getParentFunctionId().longValue() == 0) {
				tabList.add(func);
			}
		}
		return tabList;
	}

	/**
	 * 获取本级下的子权限
	 * 
	 * @param funcList
	 * @return
	 */
	private List<List<SysFunction>> processChildList(List<SysFunction> funcList, Long parentId) {
		List<List<SysFunction>> tabList = new ArrayList<List<SysFunction>>();
		// 把第一级的筛选出来（为parentId的）
		for (int i = 0; i < funcList.size(); i++) {
			SysFunction func = funcList.get(i);
			if (func.getParentFunctionId().longValue() == parentId.longValue()) {
				List<SysFunction> list = new ArrayList<SysFunction>();
				list.add(func);
				tabList.add(list);
			}
		}
		int count = funcList.size() + 1;
		while (count > funcList.size()) {
			count = funcList.size();
			for (int i = 0; i < funcList.size(); i++) {
				SysFunction func = funcList.get(i);
				for (int j = 0; j < tabList.size(); j++) {
					List<SysFunction> list = tabList.get(j);
					for (int k = 0; k < list.size(); k++) {
						if (list.get(k).getFunctionId().longValue() == func.getParentFunctionId().longValue()) {
							list.add(func);
							funcList.remove(func);
						}
					}
				}
			}
		}
		return tabList;
	}

	/**
	 * 查询用户登陆log
	 * 
	 * @param request
	 * @param userId
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/sys/loginlog/{userId}")
	public String getSysLoginLogList(HttpServletRequest request,Model model,@PathVariable("userId") Long userId, @ModelAttribute("page") PageEntity page) {
		try {
			//设置分页
			this.setPage(page);
			QueryLoginLogCondition queryLoginLogCondition = new QueryLoginLogCondition();
			queryLoginLogCondition.setUserId(userId);
			//查询用户登陆log
			List<LoginLog> loginLogList = loginLogService.getLoginLogList(queryLoginLogCondition, this.getPage());
			model.addAttribute("loginLogList", loginLogList);
			model.addAttribute("page", this.getPage());
			model.addAttribute("userId", userId);
		} catch (Exception e) {
			logger.error("getSysLoginLogList", e);
			return setExceptionRequest(request, e);
		}
		return LOGINLOG;
	}

	/**
	 * 退出系统操作
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("/sys/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		JsonObject sysUser=SingletonLoginUtils.getSysUser(request);
		String sid = WebUtils.getCookie(request, SysUserConstants.sidadmin);
        memCache.remove(MemConstans.SYS_USER_FUNCTION+sid+sysUser.get("userId"));
        request.getSession().removeAttribute(SysUserConstants.topTabList);
        request.getSession().removeAttribute(SysUserConstants.leftTabList);
        WebUtils.deleteCookie(request,response,SysUserConstants.sidadmin);
		return LOGINOUT;
	}

	/**
	 * topframe
	 * 
	 * @return
	 */
	@RequestMapping("/sys/topframe")
	public String topframe(Model model,HttpServletRequest request) {
		JsonObject jsonObject=SingletonLoginUtils.getSysUser(request);
		Gson gson=new Gson();
		SysUser sysUser=gson.fromJson(gson.toJson(jsonObject),SysUser.class); 
        model.addAttribute("sysuser",sysUser);
        model.addAttribute("userName",sysUser.getUserName());
		return TOPFRAME;
	}

	/**
	 * topframe
	 * 
	 * @return
	 */
	@RequestMapping("/sys/leftframe")
	public String leftframe() {
		return LEFTFRAME;
	}

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/sys/main")
	public String adminmain(HttpServletRequest request,HttpServletResponse response) {
		JsonObject sysUser=SingletonLoginUtils.getSysUser(request);
        if(ObjectUtils.isNotNull(sysUser)){
			String sid = WebUtils.getCookie(request, SysUserConstants.sidadmin);
            // 取当前用户的权限
            List<SysFunction> curUserFunctionList = (List<SysFunction>) memCache.get(MemConstans.SYS_USER_FUNCTION+sid+sysUser.get("userId"));
            if(ObjectUtils.isNull(curUserFunctionList)){
                curUserFunctionList=sysUserService.getUserFunction(Long.valueOf(sysUser.get("userId").toString()));
                memCache.set(MemConstans.SYS_USER_FUNCTION+sid+sysUser.get("userId"), curUserFunctionList, MemConstans.SYS_USER_TIME);
            }
            // 页面显示顶部只显示第一级的权限
            List<SysFunction> topTabList = processTopTabUser(new ArrayList<SysFunction>(curUserFunctionList));
            this.setSessionAttribute(request,SysUserConstants.topTabList,topTabList);
            // 左侧显示权限的第一个
           if (ObjectUtils.isNotNull(topTabList)) {
               List<SysFunction> leftll = (List<SysFunction>)this.getSessionAttribute(request, SysUserConstants.leftTabList);
               if (ObjectUtils.isNull(leftll)) {
                   this.setSessionAttribute(request, SysUserConstants.leftTabList, processChildList(new ArrayList<SysFunction>(curUserFunctionList), topTabList.get(0).getFunctionId()));
               }
            }
        }
        return adminMain;
	}

	/**
	 * rightframe
	 * 
	 * @return
	 */
	@RequestMapping("/sys/rightframe")
	public String rightframe(Model model,HttpServletRequest request) {
        model.addAttribute("sysuser",SingletonLoginUtils.getSysUser(request));
        return RIGHTFRAME;
	}

	/**
	 * topframe
	 * 
	 * @return
	 */
	@RequestMapping("/sys/bottomFrame")
	public String bottomFrame() {
		return BOTTOMFRAME;
	}

}
