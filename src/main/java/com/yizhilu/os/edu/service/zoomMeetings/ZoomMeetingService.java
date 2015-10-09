package com.yizhilu.os.edu.service.zoomMeetings;

import java.util.Map;

/**
 * Created by WangKaiping on 9/21 0021.
 *
 * 教师直播视频对接接口管理
 */
public interface ZoomMeetingService {

    /**
     * 创建一个zoom用户
     * @param teacherId
     *              教师ID
     * @param type
     *              zoom类型:1、基本用户(最多30分钟会议) 2、专业用户   3、公司用户
     * @return
     */
    public Map<String, Object> createZoomUser(Long teacherId);

    /**
     * 根据zoom用户ID获得zoom用户信息
     * @param zoomUserId
     * @return
     */
    public Map<String, Object> getZoomUserInfoByZoomId(String zoomUserId);

    /**
     * 创建一个直播视频会议
     * @param map
     * @return
     */
    public Map<String, Object> createMeeting(Map<String, String> map);

    /**
     * 结束一个直播视频会议
     * @param map
     * @return
     */
    public Map<String, Object> endMeeting(Map<String, String> map);

    /**
     * 删除一个直播视频会议
     * @param map
     * @return
     */
    public Map<String, Object> deleteMeeting(Map<String, String> map);

    /**
     * 获得教师的直播视频列表
     * @param map
     * @return
     */
    public Map<String, Object> queryMeetingList(Map<String, String> map);

    /**
     * 获得单个直播视频的详细信息
     * @param map
     * @return
     */
    public Map<String, Object> getMeetingInfoById(Map<String, String> map);

    /**
     * 修改直播视频信息
     * @param map
     * @return
     */
    public Map<String, Object> updateMeeting(Map<String, String> map);
}
