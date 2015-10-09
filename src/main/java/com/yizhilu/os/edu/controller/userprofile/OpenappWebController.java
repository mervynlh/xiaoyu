package com.yizhilu.os.edu.controller.userprofile;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import weibo4j.Users;
import weibo4j.util.WeiboConfig;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.yizhilu.os.common.constants.WeixinConstants;
import com.yizhilu.os.core.controller.BaseController;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.edu.entity.user.User;
import com.yizhilu.os.edu.entity.userprofile.ProfileType;
import com.yizhilu.os.edu.entity.userprofile.UserProfile;
import com.yizhilu.os.edu.service.user.UserService;
import com.yizhilu.os.edu.service.userprofile.UserProfileService;

@Controller
public class OpenappWebController extends BaseController{
    /**
	 * 
	 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -9046516845301034752L;

    private static final Logger logger = LoggerFactory.getLogger(OpenappWebController.class);
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
   
    
    /**
     * 跳转到登录连接，返回联合登录的地址 根据传的type处理不同的请求。
     */
    @RequestMapping("/app/authlogin")
    public String authlogin(HttpServletRequest request,HttpServletResponse response,@RequestParam String appType) {
    	String callBack="";
        try {
            // 检查类型并转为统一的大写格式
            appType = checkType(appType);
            if (StringUtils.isEmpty(appType)) {
                return "redirect:/login";
            }
            // QQ联合登录的返回处理
            if (ProfileType.QQ.toString().equalsIgnoreCase(appType)) {
                // 获取QQ联合登录的地址
                callBack = new Oauth().getAuthorizeURL(request);
                logger.info("++ QQ callBack:" + callBack);
                return "redirect:"+callBack;
            }
            if (ProfileType.SINA.toString().equalsIgnoreCase(appType)) {
                // 获取微博联合登录的地址
                try {
                    String weiboKey = WeiboConfig.getValue("client_ID");
                    String weiboSecret = WeiboConfig.getValue("client_SERCRET");
                    weibo4j.Oauth weibooauth = new weibo4j.Oauth();
                    callBack = weibooauth.authorize("code", weiboKey, weiboSecret);
                    logger.info("++ SINA callBack:" + callBack);
                    // BareBonesBrowserLaunch.openURL(weibooauth.authorize("code",weiboKey,weiboSecret));
                    return "redirect:"+callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the sina access token.");
                }
            }
            //微信联合登录
            if (ProfileType.WEIXIN.toString().equalsIgnoreCase(appType)) {
                try {
                	callBack+="https://open.weixin.qq.com/connect/qrconnect?appid="+WeixinConstants.appid;
                 	callBack+="&redirect_uri="+URLEncoder.encode(WeixinConstants.redirect_uri,"utf-8");
                 	callBack+="&response_type="+WeixinConstants.response_type+"&scope="+WeixinConstants.scope;
//                 	callBack+="&state=3d6be0a4035d839573b04816624a415e#wechat_redirect";
                	logger.info("++ WEIXIN callBack:" + callBack);
                    return "redirect:"+callBack;
                } catch (Exception e) {
                    logger.info("Unable to get the weixin access token.");
                }
            }
        } catch (QQConnectException e) {
            logger.error("+++authlogin：get unio login url error:", e);
            return "login";
            // setResult(new Result<String>(false, "false", "", null));
        }
        // setResult(new Result<String>(false, "success", "", callBack));
        return "success";
    }

    /**
     * QQ联合登录成功回调地址
     */
    @RequestMapping("/app/loginReturn")
    public String loginReturn(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) {	
        try {
        	String appType =request.getParameter("appType");
            appType = ProfileType.QQ.toString();
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            System.out.println(accessTokenObj.getAccessToken());
            if (accessTokenObj.getAccessToken().equals("")) {
                logger.info("+++loginRetrun，获取返回的参数失败:");
                return "login";
            } else {
                String accessToken = accessTokenObj.getAccessToken();
                long tokenExpirein = accessTokenObj.getExpireIn();
                logger.info("+++accessToken:" + accessToken + ",tokenExpirein:" + tokenExpirein);
                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                String appId = openIDObj.getUserOpenID();
                logger.info("+++ loginReturn openIDObj:" + appId);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                UserInfo qzoneUserInfo = new UserInfo(accessToken, appId);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(appType);
                List<UserProfile> list = userProfileService.getUserProfileList(userProfile);
                if (ObjectUtils.isNotNull(list)) {//已经绑定过存在的帐号
                	userProfile = list.get(0);
                    // 登录操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + appId);
                    //userExpandService.getDoLogin(null, cusId, request, response);
                    //执行登陆操作
                    User user = userService.getUserById(Long.valueOf(cusId));
                    Map<String, Object> userInfo=userService.setLoginStatus(user,"true",request, response);
                    if(ObjectUtils.isNotNull(userInfo)){
                    	if(userInfo.get("userInfo").toString().equals("mobileIsavalibleErr")){
                    		//跳转绑定邮箱手机号页面
                    		return "redirect:/jump_user?userId="+user.getId();
                    	}else{
                    		redirectAttributes.addAttribute("msg","对不起,该账号已被冻结。");
        					return "redirect:/front/success";
                    	}
                    }else{
                    	// 登录成功跳转到首页
                        return "redirect:/index";
                    }
                } else {
                    // 自动生成邮箱帐号
                    String photo=null;
                    String  cusName=null;
                    if (userInfoBean != null) {
                    	//photo=HttpUtil.doPost(CommonConstants.uploadImageServer+"/netfile?url="+userInfoBean.getAvatar().getAvatarURL100()+"&base="+CommonConstants.projectName+"&param=customer", null);
                    	cusName = userInfoBean.getNickname().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+","");
                       logger.info("++++ getNickname:" + cusName);
                    }
                    String userId=userProfileService.addOpenAppRegister(photo,cusName, request, response, ProfileType.QQ, appId);
                    return "redirect:/jump_user?userId="+userId;// "bindJump";//跳转到绑定的登录页面
                }
            }
        } catch (Exception e) {
            logger.error("++++ loginReturn exception", e);
            return "redirect:/login";
        }

    }

    /**
     * sina联合登录成功回调地址
     */
    @RequestMapping("/app/sinalogin")
    public String sinaloginReturn(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) {
        try {
            String appType = ProfileType.SINA.toString();
            logger.info("+++sinaloginReturn sina invoce:");
            // 根据code获得授权
            String code = request.getParameter("code");
            weibo4j.Oauth oauth = new weibo4j.Oauth();
            weibo4j.http.AccessToken accessToken = oauth.getAccessTokenByCode(code);
            if (accessToken == null) {
                logger.info("+++sinaloginReturn,gettoken null");
                return "redirect:/login";
            } else {

                Users um = new Users();
                um.client.setToken(accessToken.getAccessToken());
                weibo4j.model.User user = um.showUserById(accessToken.getUid());
                String photo="";
                String cusName="";
                if (user != null) {
                    logger.info("+++ sinauser name:" + user);
                    cusName = user.getScreenName().replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+","");
                    //photo=HttpUtil.doPost(CommonConstants.uploadImageServer+"/netfile?url="+user.getavatarLarge()+"&base="+CommonConstants.projectName+"&param=customer", null);
                }

                logger.info("++++sinaloginReturn token:" + accessToken.toString());
                String appId = accessToken.getUid();
                // 未绑定的跳转到登录页面,要将QQkey放到页面中，提交注册时用
                UserProfile userProfile = new UserProfile();
                userProfile.setValue(appId);
                userProfile.setProfiletype(appType);
                List<UserProfile> userProfileList = userProfileService.getUserProfileList(userProfile);
                if (userProfileList != null && userProfileList.size() > 0) {
                	userProfile = userProfileList.get(0);
                    // 登录操作
                    Long cusId = userProfile.getUserid();
                    logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + appId);
                    //执行登陆操作
                    User user1 = userService.getUserById(cusId);
                    Map<String, Object> userInfo=userService.setLoginStatus(user1,"true",request, response);
                    if(ObjectUtils.isNotNull(userInfo)){
                    	if(userInfo.get("userInfo").toString().equals("mobileIsavalibleErr")){
                    		//跳转绑定邮箱手机号页面
                    		return "redirect:/jump_user?userId="+user.getId();
                    	}else{
                    		redirectAttributes.addAttribute("msg","对不起,该账号已被冻结。");
        					return "redirect:/front/success";
                    	}
                    }else{
                    	// 登录成功跳转到首页
                        return "redirect:/index";
                    }
                } else {
                	 String userId=userProfileService.addOpenAppRegister(photo,cusName, request, response, ProfileType.SINA, appId);
                	 return "redirect:/jump_user?userId="+userId;
                }
            }
        } catch (Exception e) {
            logger.error("++++ sinaloginReturn exception", e);
            return "redirect:/login";
        }

    }
    /**
     * 微信登录回调(获取code参数)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/app/weixinlogin")
    public String weixinloginReturn(HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) {
        try {
        	String code =request.getParameter("code");
        	if(StringUtils.isNotEmpty(code)){
        		//拼写微信获取access_token访问地址
        		String url="https://api.weixin.qq.com/sns/oauth2/access_token";
            	url+="?appid="+WeixinConstants.appid;
            	url+="&secret="+WeixinConstants.secret;
            	url+="&code="+code;
            	url+="&grant_type=authorization_code";
                
            	//获取返回数据
        		JSONObject userMap = urlHalder(url);
        		
        		//获取openid
        		String openId=userMap.get("openid")+"";
        		String accessToken=userMap.get("access_token")+"";
        		if(StringUtils.isNotEmpty(openId)&&StringUtils.isNotEmpty(accessToken)){
					 UserProfile userProfile = new UserProfile();
					 userProfile.setValue(openId);
					 userProfile.setProfiletype(ProfileType.WEIXIN+"");
					 List<UserProfile> list = userProfileService.getUserProfileList(userProfile);
					 if (ObjectUtils.isNotNull(list)) {//已经绑定过存在的帐号
						 userProfile = list.get(0);
						 // 登录操作
						 Long cusId = userProfile.getUserid();
						 logger.info("+++ already bindsuccess cusId:" + cusId + ",openkey:" + openId);
						 //userExpandService.getDoLogin(null, cusId, request, response);
						 //执行登陆操作
						 User user = userService.getUserById(Long.valueOf(cusId));
						 Map<String, Object> userInfo=userService.setLoginStatus(user,"true",request, response);
	                    if(ObjectUtils.isNotNull(userInfo)){
	                    	if(userInfo.get("userInfo").toString().equals("mobileIsavalibleErr")){
	                    		//跳转绑定邮箱手机号页面
	                    		return "redirect:/jump_user?userId="+user.getId();
	                    	}else{
	                    		redirectAttributes.addAttribute("msg","对不起,该账号已被冻结。");
	        					return "redirect:/front/success";
	                    	}
	                    }else{
	                    	// 登录成功跳转到首页
	                        return "redirect:/index";
	                    }
					 } else {
						 String infourl="https://api.weixin.qq.com/sns/userinfo";
						 infourl+="?access_token="+accessToken;
						 infourl+="&openid="+openId;
						 //获取返回数据
						 JSONObject userInfoMap = urlHalder(infourl);
						 String photo=userInfoMap.get("headimgurl")+"";
						 String  cusName=userInfoMap.get("nickname")+"";
						 cusName = cusName.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+","");
						 String userId= userProfileService.addOpenAppRegister(photo,cusName, request, response, ProfileType.WEIXIN, openId);
						 return "redirect:/jump_user?userId="+userId;
					 }
				 }else{
					 logger.info("++weixinloginReturn，获取返回的参数失败");
		        	 return "redirect:/login";
				 }
    		}else{
        		logger.info("++weixinloginReturn，获取返回的参数失败");
        		return "redirect:/login";
        	}
        } catch (Exception e) {
            logger.error("++++ weixinloginReturn exception", e);
            return "redirect:/login";
        }

    }
    /**
     * 访问处理
     * @param url
     * @return
     * @throws Exception
     */
    private JSONObject urlHalder(String url) throws Exception{
    	//请求访问
    	HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		String result = method.getResponseBodyAsString();
		method.releaseConnection();
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject;
    }

   /* *//**
     * 获得用户绑定的第三方网站
     * 
     * @return
     *//*
    public String getOpenlist() {
    	 Openapp openApp = new Openapp();
    	 openApp.setCusId();
        openappList = openappService.getOpenappList(openApp);
        for (Openapp openapp : openappList) {
            if (openapp.getAppType().equalsIgnoreCase(ProfileType.QQ.toString())) {
                //bindqq=true;
            } else if (openapp.getAppType().equalsIgnoreCase(ProfileType.SINA.toString())) {
                //bindsina=true;
            }
        }
        return "openlist";
    }

    *//**
     * 解绑操作
     * 
     * @return
     *//*
    public String unBindOpenApp() {
        try {
            if (StringUtils.isNotEmpty(appType)) {
                // 解绑
                Openapp openapp = new Openapp();
                openapp.setCusId(this.getLoginUserId());
                openapp.setAppType(appType);
                openappService.delOpenappById(openapp);
                this.setJson(false, "success", null);
            } else {
            	this.setJson(false, "error", null);
            }
        } catch (Exception e) {
            logger.error("++unBindOpenApp error", e);
            this.setJson(false, "error", null);
        }
        return "json";
    }*/

    // 检查登录类型是否符合
    public String checkType(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        if (ProfileType.QQ.toString().equalsIgnoreCase(type)) {
            type = ProfileType.QQ.toString();
        }
        if (ProfileType.SINA.toString().equalsIgnoreCase(type)) {
            type = ProfileType.SINA.toString();
        }
        return type;
    }

}
