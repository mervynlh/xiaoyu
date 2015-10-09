package com.yizhilu.os.edu.controller.website;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.entity.website.WebsiteProfile;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;

/**
 * 网站配置管理
 * 
 * @ClassName 
 *            com.yizhilu.os.edu.controller.website.AdminWebsiteProfileController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月11日 下午3:28:52
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteProfileController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteProfileController.class);
	@Autowired
	private WebsiteProfileService websiteProfileService;

	private static final String getWebSiteList = getViewPath("/admin/website/profile/website_profile_list");// 网站配置管理页面
	private static final String toupdateWebSite = getViewPath("/admin/website/profile/website_profile_update");// 更新网站配置管理页面
	private static final String getWebSiteOnline = getViewPath("/admin/website/profile/website_profile_online");// 在线咨询
	private static final String getWebSiteSale = getViewPath("/admin/website/profile/website_profile_sale");// 售卖方式
	
	private static final String ICONAME="favicon.ico";//定义ico文件常量

	/**
	 * 查询网站配置 根据Type
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/websiteProfile/find/{type}")
	public String getWebSiteList(HttpServletRequest request, Model model, @PathVariable("type") String type) {
		String returnUrl = "";
		try {
			if (WebSiteProfileType.ico.toString().equals(type)) {// 类型是ico文件
				return getWebSiteList;
			}
			String flag = request.getParameter("flag");
			// 查询所有 --存memcache缓存
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(type);
			if (StringUtils.isNotEmpty(flag)) {
				returnUrl = toupdateWebSite;// 跳转到更新页面
			} else {
				returnUrl = getWebSiteList;// 列表页
			}
			model.addAttribute("webSiteMap", map);
			model.addAttribute("type", type);
		} catch (Exception e) {
			logger.error("getWebSiteList", e);
		}
		return returnUrl;
	}

	/**
	 * 更新管理根据类型
	 * 
	 * @return
	 */
	@RequestMapping("/websiteProfile/update")
	public String updateWebSiteByType(HttpServletRequest request, Model model, @RequestParam("type") String type) {
		try {
			if (ObjectUtils.isNotNull(type) && StringUtils.isNotEmpty(type)) {
				Gson gson = new Gson();
				JsonParser jsonParser = new JsonParser();
				Map<String, String> map = new HashMap<String, String>();
				if (type.equals(WebSiteProfileType.web.toString())) {
					map.put("email", request.getParameter("email"));// 公司邮箱
					map.put("phone", request.getParameter("phone"));// 24小时电话
					map.put("workTime", request.getParameter("workTime"));// 工作时间
					map.put("copyright", request.getParameter("copyright"));// 备案
					map.put("author", request.getParameter("author"));// 作者
					map.put("keywords", request.getParameter("keywords"));// 关键词
					map.put("description", request.getParameter("description"));// 描述
					map.put("title", request.getParameter("title"));//
					map.put("company", request.getParameter("company"));// 网校名称
				}
				// 类型为alipay
				if (type.equals(WebSiteProfileType.alipay.toString())) {
					map.put("alipaypartnerID", request.getParameter("alipaypartnerID"));// 合作者身份ID
					map.put("alipaykey", request.getParameter("alipaykey"));// 交易安全校验码
					map.put("sellerEmail", request.getParameter("sellerEmail"));// 商家邮箱
					map.put("status", request.getParameter("status"));// 支付宝选择（企业和个人）
				}
				if(type.equals(WebSiteProfileType.yee.toString())){
					map.put("p1_MerId", request.getParameter("p1_MerId"));// 合作者身份ID
					map.put("keyValue", request.getParameter("keyValue"));// 交易安全校验码
				}
				// 类型为邮箱
				if (type.equals(WebSiteProfileType.email.toString())) {
					map.put("emailHost", request.getParameter("emailHost"));// 邮箱Host
					map.put("emailUsername", request.getParameter("emailUsername"));// 邮箱名称
					map.put("emailPassword", request.getParameter("emailPassword"));// 邮箱密码
				}
				// 类型为keyword
				if (type.equals(WebSiteProfileType.keyword.toString())) {
					map.put("verifyPhone", request.getParameter("verifyPhone"));// 验证手机
																				// NO
																				// Off
					map.put("verifyEmail", request.getParameter("verifyEmail"));// 验证邮箱
																				// NO
																				// Off
					map.put("verifySensitive", request.getParameter("verifySensitive"));// 敏感词
					map.put("verifyRegister", request.getParameter("verifyRegister"));// 注册开关
					map.put("verifyLogin", request.getParameter("verifyLogin"));// 登录开关
					map.put("verifyAlipay", request.getParameter("verifyAlipay"));// 支付宝开关
					map.put("verifykq", request.getParameter("verifykq"));// 快钱支付开关
					map.put("verifywx", request.getParameter("verifywx"));// 微信支付开关
					map.put("verifyExam", request.getParameter("verifyExam"));// 考试开关
					map.put("verifySns", request.getParameter("verifySns"));// sns开关
					map.put("verifySns", request.getParameter("verifySns"));// sns开关
					map.put("yee", request.getParameter("yee"));// sns开关
				}
				// 类型为letv
				if (type.equals(WebSiteProfileType.letv.toString())) {// 类型为letv
					map.put("user_unique", request.getParameter("user_unique"));// letv用户唯一标示
					map.put("secret_key", request.getParameter("secret_key"));// letv秘钥
				}
				// 类型为logo
				if (type.equals(WebSiteProfileType.logo.toString())) {// 类型为logo
					map.put("url", request.getParameter("url"));// clientID
				}
				// 类型为qq
				if (type.equals(WebSiteProfileType.qq.toString())) {// 类型为alipay
					map.put("appID", request.getParameter("appID"));// appID
					map.put("appKEY", request.getParameter("appKEY"));// appKEY
				}
				// 类型为sina
				if (type.equals(WebSiteProfileType.sina.toString())) {// 类型为alipay
					map.put("clientID", request.getParameter("clientID"));// clientID
					map.put("clientSERCRET", request.getParameter("clientSERCRET"));// clientSERCRET
				}
				// 第三方登录
				if (type.equals(WebSiteProfileType.thirdlogin.toString())) {
					map.put("thirdloginstatus", request.getParameter("thirdloginstatus"));// thirdloginstatus第三方登录是否开启
				}
				// 保利威视
				if (type.equals(WebSiteProfileType.polyv.toString())) {
					map.put("writetoken", request.getParameter("writetoken"));// 保利威视
				}
				// 统计代码
				if (type.equals(WebSiteProfileType.censusCode.toString())) {
					map.put("censusCodeString", request.getParameter("censusCodeString"));// 统计代码
				}
				// 韩教授认证管理配置
				if (type.equals(WebSiteProfileType.professorAttestation.toString())) {
					map.put("email", request.getParameter("email"));// 韩教授认证管理者邮箱
					map.put("mobile", request.getParameter("mobile"));// 韩教授认证管理者手机
				}
				// 将map转化json串
				JsonObject jsonObject = jsonParser.parse(gson.toJson(map)).getAsJsonObject();
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
					websiteProfile.setType(type);
					websiteProfile.setDesciption(jsonObject.toString());
					websiteProfileService.updateWebsiteProfile(websiteProfile);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteProfileController.updateWebSiteByType", e);
		}
		return "redirect:/admin/websiteProfile/find/" + type;
	}

	/**
	 * 上传ico文件
	 * 
	 * @param request
	 * @param ico
	 * @return
	 */
	@RequestMapping("/websiteProfile/uploadIco")
	public String uploadIcoFile(HttpServletRequest request, @RequestParam("icoFile") MultipartFile icoFile) {
		try {
			if (!icoFile.isEmpty()) {
				// 获得项目的真实路径
				String path = request.getSession().getServletContext().getRealPath("");
				File file = new File(path + "/" + ICONAME);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					icoFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error("uploadIcoFile", e);
		}
		return "redirect:/admin/websiteProfile/find/ico";
	}

	/**
	 * 查询在线咨询
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/websiteProfile/online")
	public String getWebsiteOnline(HttpServletRequest request, Model model) {
		try {
			// 查询在线咨询详情
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.online.toString());
			model.addAttribute("websiteonlinemap", map);
		} catch (Exception e) {
			logger.error("getWebsiteOnline", e);
			return setExceptionRequest(request, e);
		}
		return getWebSiteOnline;
	}

	/**
	 * 更新WebsiteOnline
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/websiteProfile/online/update")
	public String updateWebsiteOnline(HttpServletRequest request) {
		try {
			JsonParser jsonParser = new JsonParser();
			Map<String, String> map = new HashMap<String, String>();
			map.put("onlineUrl", request.getParameter("onlineUrl"));// 链接
			map.put("onlineImageUrl", request.getParameter("onlineImageUrl"));// 图片链接
			map.put("onlineKeyWord", request.getParameter("onlineKeyWord"));// 开关
			// 将map转化json串
			JsonObject jsonObject = jsonParser.parse(gson.toJson(map)).getAsJsonObject();
			if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
				WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
				websiteProfile.setType(WebSiteProfileType.online.toString());
				websiteProfile.setDesciption(jsonObject.toString());
				websiteProfileService.updateWebsiteProfile(websiteProfile);
			}
		} catch (Exception e) {
			logger.error("updateWebsiteOnline", e);
		}
		return "redirect:/admin/websiteProfile/online";
	}
	
	/**
	 * 查询售卖方式
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/websiteProfile/sale")
	public String getWebsiteSale(HttpServletRequest request, Model model) {
		try {
			// 查询售卖方式
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
			model.addAttribute("websitemap", map);
		} catch (Exception e) {
			logger.error("getWebsiteSale", e);
			return setExceptionRequest(request, e);
		}
		return getWebSiteSale;
	}
	
	/**
	 * 更新售卖方式
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/websiteProfile/sale/update")
	public String updateWebsiteSale(HttpServletRequest request) {
		try {
			JsonParser jsonParser = new JsonParser();
			Map<String, String> map = new HashMap<String, String>();
			map.put("verifyMember", request.getParameter("verifyMember"));// 会员方式开关
			map.put("verifyCourse", request.getParameter("verifyCourse"));// 课程方式开关
			map.put("verifyLevel", request.getParameter("verifyLevel"));// 会员等级开关
			// 将map转化json串
			JsonObject jsonObject = jsonParser.parse(gson.toJson(map)).getAsJsonObject();
			if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
				WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
				websiteProfile.setType(WebSiteProfileType.sale.toString());
				websiteProfile.setDesciption(jsonObject.toString());
				websiteProfileService.updateWebsiteProfile(websiteProfile);
			}
		} catch (Exception e) {
			logger.error("updateWebsiteSale", e);
		}
		return "redirect:/admin/websiteProfile/sale";
	}
	
}
