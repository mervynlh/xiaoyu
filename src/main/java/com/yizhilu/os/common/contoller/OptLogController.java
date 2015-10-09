package com.yizhilu.os.common.contoller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.yizhilu.os.core.util.ObjectUtils;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.common.EduBaseController;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.yizhilu.os.common.contoller.OptLogController
 * @description
 * @Create Date : 2014-6-12 下午5:07:55
 */
public class OptLogController extends EduBaseController {

    private static final Log logger = LogFactory.getLog(OptLogController.class);

    public static String ACTION = "action";// 操作动作事件
    public static String DESC = "desc";// 业务详细描述
    public static String UID = "uid";// 用户id
    public static String IP = "ip";// 操作ip

    /**
     * 获得log的map添加当前用户的uid和ip
     *
     * @param request
     * @return
     */
    public static void dolog(HttpServletRequest request, String action, String desc) {
        try {
            Map<String, Object> logMap = new HashMap<String, Object>();
            // 访问者IP
            String ip = WebUtils.getIpAddr(request);
            Long uid = getLoginUserId(request);
            logMap.put(IP, ip);
            logMap.put(UID, uid.toString());
            logMap.put(ACTION, action);
            logMap.put(DESC, desc);
            printLog(logMap);// 打印日志
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("dolog error", e);
        }
    }

    /**
     * 打印log
     *
     * @param map
     */
    public static void printLog(Map<String, Object> map) {
        if (ObjectUtils.isNotNull(map)) {
            Gson gson = new Gson();
            logger.info("user_opt_log:" + gson.toJson(map));
        }
    }

}
