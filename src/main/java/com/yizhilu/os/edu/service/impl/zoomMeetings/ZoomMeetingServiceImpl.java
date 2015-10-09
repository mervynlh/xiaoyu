package com.yizhilu.os.edu.service.impl.zoomMeetings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.util.web.HttpUtil;
import com.yizhilu.os.edu.service.zoomMeetings.ZoomMeetingService;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 9/22 0022.
 */
@Service("zoomMeetingService")
public class ZoomMeetingServiceImpl implements ZoomMeetingService {

    private static final Logger logger = LoggerFactory.getLogger(ZoomMeetingServiceImpl.class);
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    /**
     * 创建一个zoom用户
     * @param teacherId
     *              教师ID
     * @param type
     *              zoom类型:1、基本用户(最多30分钟会议) 2、专业用户   3、公司用户
     * @return
     */
    public Map<String, Object> createZoomUser(Long teacherId){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        // 拼装参数
        String param = "api_key=       &api_secret=         email=" + teacherId + "@268xue.com&type=3";
        String resultJson = HttpUtil.doGet(CommonConstants.zoomMeetingAPIUrl.toString() + "/user/create", param);
        logger.info("-----------创建用户成功：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 根据zoom用户ID获得zoom用户信息
     * @param zoomUserId
     * @return
     */
    public Map<String, Object> getZoomUserInfoByZoomId(String zoomUserId){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doGet(CommonConstants.zoomMeetingAPIUrl.toString() + "/user/get", zoomUserId);
        logger.info("-----------用户基本信息：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 创建一个直播视频会议
     * @param map
     * @return
     */
    public Map<String, Object> createMeeting(Map<String, String> map){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doPost(CommonConstants.zoomMeetingAPIUrl.toString() + "/meeting/create", map);
        logger.info("-----------创建直播视频成功：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 结束一个直播视频会议(删除)
     * @param map
     * @return
     */
    public Map<String, Object> endMeeting(Map<String, String> map){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doPost(CommonConstants.zoomMeetingAPIUrl.toString() + "/meeting/end", map);
        logger.info("-----------结束直播视频成功：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 删除一个直播视频会议
     * @param map
     * @return
     */
    public Map<String, Object> deleteMeeting(Map<String, String> map){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doPost(CommonConstants.zoomMeetingAPIUrl.toString() + "/meeting/delete", map);
        logger.info("-----------删除直播视频成功：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 获得教师的直播视频列表
     * @param map
     * @return
     */
    public Map<String, Object> queryMeetingList(Map<String, String> map){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doPost(CommonConstants.zoomMeetingAPIUrl.toString() + "/meeting/list", map);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 获得单个直播视频的详细信息
     * @param map
     * @return
     */
    public Map<String, Object> getMeetingInfoById(Map<String, String> map){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doPost(CommonConstants.zoomMeetingAPIUrl.toString() + "/meeting/get", map);
        logger.info("-----------直播视频信息：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }

    /**
     * 修改直播视频信息
     * @param map
     * @return
     */
    public Map<String, Object> updateMeeting(Map<String, String> map){
        Map<String, Object>  resultMap = new HashMap<String, Object>();
        String resultJson = HttpUtil.doPost(CommonConstants.zoomMeetingAPIUrl.toString() + "/meeting/update", map);
        logger.info("-----------修改直播视频信息成功：", resultJson);
        resultMap = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>(){}.getType());
        return resultMap;
    }
}
