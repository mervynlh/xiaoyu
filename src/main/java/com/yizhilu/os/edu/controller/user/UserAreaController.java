package com.yizhilu.os.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.user.UserArea;
import com.yizhilu.os.edu.service.user.UserAreaService;
/**
 * UserArea管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Controller
public class UserAreaController extends EduBaseController{
	private Logger logger = LoggerFactory.getLogger(UserAreaController.class);
 	@Autowired
    private UserAreaService userAreaService;
    
    /**
	 * 快速登录，为ajax提供
	 * 
	 * @return
	 */
	@RequestMapping("/area/ajax/parentid")
	@ResponseBody
	public Object areaByParentId(HttpServletRequest request,@RequestParam("parentId") Long parentId) {
		try {
			//通过父id查询子类
			UserArea userArea = new UserArea();
			userArea.setParentId(parentId);
			List<UserArea> userAreaList = userAreaService.getUserAreaList(userArea);
			this.setJson(true, "success", userAreaList);
		} catch (Exception e) {
			logger.error("Usercontroller.dologin", e);
			this.setJson(false, "error", "系统异常");
			return json;
		}
		return json;
	}

   
}