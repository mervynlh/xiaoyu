package com.yizhilu.os.edu.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.StringUtils;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserLevel;
import com.yizhilu.os.edu.service.user.UserLevelService;

/**
 * 
 * @ClassName com.yizhilu.os.edu.controller.user.AdminUserLevelController
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月27日 下午2:24:09
 */
@RequestMapping("/admin")
@Controller
public class AdminUserLevelController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserLevelController.class);

	private static final String userLevelList = getViewPath("/admin/user/userLevel_list");// 用户等级规则页面
	@Autowired
	private UserLevelService userLevelService;

	/**
	 * 查询用户等级规则列表
	 * 
	 * @param request
	 * @param model
	 * @param userLevel
	 * @return
	 */
	@RequestMapping("/user/levelist")
	public String getuserLevelList(HttpServletRequest request, Model model) {
		try {
			// 查询用户等级规则
			List<UserLevel> userLevelist = userLevelService.getUserLevelList();
			model.addAttribute("userLevelist", userLevelist);
		} catch (Exception e) {
			logger.error("AdminUserLevelController.getuserLevelList", e);
			setExceptionRequest(request, e);
		}
		return userLevelList;
	}

	/**
	 * 更新用户等级规则
	 * 
	 * @param request
	 * @param userLevel
	 * @return
	 */
	@RequestMapping("/user/levelupdate")
	@ResponseBody
	public Map<String, Object> updateuserLevel(HttpServletRequest request, @ModelAttribute UserLevel userLevel) {
		try {
			// 获得所有集合list
			List<UserLevel> userLevelist = userLevel.getUserLevel();
			int lv = 0;// 设置级别
			boolean flag = true;// 验证标示
			if (ObjectUtils.isNotNull(userLevelist) && userLevelist.size() > 0) {
				for (int i = 0; i < userLevelist.size(); i++) {
						if ((StringUtils.isEmpty(userLevelist.get(i).getTitle())||userLevelist.get(i).getExp()==null)&&i<9) {
							lv = i;
							flag = false;
							break;
						}
					if (flag) {// 数据正确进行更新操作
						if(i>8&&userLevelist.get(i).getExp()==null){
							userLevelist.get(i).setExp(0L);
						}
						userLevelService.updateUserLevel(userLevelist.get(i));
					}
				}
				if(flag){
					this.setJson(flag, "修改成功", null);	
				}else{
					this.setJson(flag, "LV" + (lv + 1) + "请确认是否正确填写", null);
					return json;
				}
			}
		} catch (Exception e) {
			logger.error("updateuserLevel", e);
			this.setJson(false, "系統繁忙请稍后重试", null);
		}
		return json;
	}
}
