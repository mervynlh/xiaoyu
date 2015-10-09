package com.yizhilu.os.common.constants;

import com.yizhilu.os.core.util.PropertyUtil;
import com.yizhilu.os.core.util.web.WebUtils;

/**
 * @author : qinggang.liu voo@163.com
 * @ClassName com.yizhilu.os.common.entity.CommonConstant
 * @description
 * @Create Date : 2014-5-27 下午4:55:03
 */
public class CommonConstants {

    public static String propertyFile = "project";// 配置文件名字
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(propertyFile);

    public static final String redirect = "redirect";
    
    public static PropertyUtil prosecurity = PropertyUtil.getInstance("prosecurity");
    
    // 统一加密key
    public static  String SecurityKey = prosecurity.getProperty("securitykey");

    public static String domiankey= prosecurity.getProperty("domiankey");

    // 资源文件版本号
    public static final String VERSION = System.currentTimeMillis() + "";
    // 存cookie存根域
    public static final String MYDOMAIN = propertyUtil.getProperty("mydomain");
    // 268视频上传ukey
    public static final String UKEY = "ukey"; // 用户id
    // 用户登录
    public static final String USER_SINGEL_ID = "sid"; // 用户id

    public static final String USER_SINGEL_NAME = "sname"; // 用户名
    
    public static final String USER_SINGEL_TYPE = "userType"; // 用户类型
    
    public static final String UP_USER_ID = "userId";
    
    public static final double TEACHER_INCOME_RATIO = 0.9; // 老师收入占课时金额比重
    
    public static final double TEACHER_EXTEND_TEACHER_RATIO = 0.02; // 老师推广老师后，收入占课时金额比重
    
    public static final double TEACHER_EXTEND_STUDENT_RATIO = 0.02; // 老师推广学生后，收入占课时金额比重
    
    public static final String USER_SELECT_ATER_ID = "areaid"; // 城市ID
    
    public static final String USER_DEFAULT_ATER_ID = "326"; // 默认城市ID ：326  西安市
    
    // 存用户的专业id
    public static final String COOKIE_SUBJECTID_KEY = "e.subject";

    // 项目路径
    public static final String contextPath = propertyUtil.getProperty("contextPath");
    // zoom视频直播api地址
    public static final String zoomMeetingAPIUrl = propertyUtil.getProperty("zoomMeetingAPIUrl");

    // 静态资源：图片、CSS、js 服务器地址
    public static final String staticServer = propertyUtil.getProperty("staticServer");

    // 上传服务用服务器地址，访问时用upStaticPath，数据库中不存储域名
    public static final String uploadImageServer = propertyUtil.getProperty("uploadImageServer");

    // 上传图片后访问的地址，使用importURL或者upStaticPath防止项目部署到多台机器，单独定义此变量
    public static final String staticImageServer = propertyUtil.getProperty("staticImage");

    public static final String projectName = propertyUtil.getProperty("projectName");
    
    public static final String assessFile=propertyUtil.getProperty("assessFile");
    
    //短信接口
    public static final String msgurl=propertyUtil.getProperty("msgurl");
    public static final String acountSid=propertyUtil.getProperty("acountSid");
    public static final String authToken=propertyUtil.getProperty("authToken");
    public static final String appId=propertyUtil.getProperty("appId");

//    public  static final String seniorSchoolDown = "1"; // 高中以下
//    public  static final String seniorSchool = "2"; // 高中或中专
//    public  static final String juniorCollege = "3"; // 大专
//    public  static final String undergraduateCourse = "4"; // 本科
//    public  static final String postgraduate = "5"; // 研究生
//    public  static final String learnedScholar = "6"; // 博士

    public static  String l = "1";
    public static  String w = "1";
    static {
        WebUtils.MYDOMAIN = MYDOMAIN;
    }
}
