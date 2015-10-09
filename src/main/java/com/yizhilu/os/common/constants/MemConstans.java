package com.yizhilu.os.common.constants;

import com.yizhilu.os.core.util.PropertyUtil;

/**
 * @author : qinggang.liu 305050016@qq.com
 * @ClassName com.yizhilu.os.sns.entity.constans.MemConstans
 * @description memcache缓存相关常量
 * @Create Date : 2014-3-26 上午9:42:06
 */
public class MemConstans {
    public static PropertyUtil webPropertyUtil = PropertyUtil.getInstance("memtimes");

    //项目统一存memcache值时的前缀
    public static final String MEMFIX = webPropertyUtil.getProperty("memfix");


    //*******SNS全站排行数据缓存时间*******
    public static final String MSGRECEIVE_UNREAD = MEMFIX + "MSGRECEIVE_UNREAD";// 头部未读消息数量
    public static final int MSGRECEIVE_UNREAD_TIME = Integer.parseInt(webPropertyUtil.getProperty("MSGRECEIVE_UNREAD_TIME")); // 头部未读消息数量缓存1分钟
    //*******SNS全站排行数据缓存时间*******

    public static final String MEM_HOT_BAK = "_bak";//备份的后缀

    // 热门词按分类存mem前缀
    public static final String MEM_SEARCH_WORD = MEMFIX + "sns_search_word_";
    // 敏感词存缓存
    public static final String MEM_KEYWORD = MEMFIX + "sns_key_word_";
    public static final int MEM_KEYWORD_TIME = Integer.parseInt(webPropertyUtil.getProperty("MEM_KEYWORD_TIME"));

    //lucene索引是否生成开始生成标识。值存在代表正在生成
    public static final String MEM_LUCENE_CREATING = MEMFIX + "MEM_LUCENE_CREATING";

    // 考试exam  **memcache key 前缀统一，全部以MEM_开头 start
    public static final int MEM_COMMON_TIME = 600;//一般数据缓存的时间 10分钟
    public static final String MEM_SUBECJT =MEMFIX + "MEM_SUBECJT_";// 单个专业信息。后追加专业id

    public static final String MEM_ALL_SUBECJT = MEMFIX +"MEM_ALL_SUBECJT";// 所有专业信息
    public static final int  MEM_ALL_SUBECJT_TIME = Integer.parseInt(webPropertyUtil.getProperty("MEM_ALL_SUBECJT_TIME"));;// 所有专业信息时间

    // 考试exam end
    

    //网校相关 start

    public static final String TEACHER_ALL = MEMFIX +"TEACHER_ALL";// 所有专业信息
    public static final int  TEACHER_ALL_TIME = Integer.parseInt(webPropertyUtil.getProperty("TEACHER_ALL_TIME"));;// 所有专业信息时间
    
    public static final String INDEX_CUSTOMER_COURSE = MEMFIX + "customer_course";//首页自定义课程
    public static final int INDEX_CUSTOMER_COURSE_TIME = Integer.parseInt(webPropertyUtil.getProperty("INDEX_CUSTOMER_COURSE_TIME"));//自定义课程缓存1小时


    public static final String RECOMMEND_COURSE = MEMFIX + "recommend_course";//首页推荐课程
    public static final int RECOMMEND_COURSE_TIME = Integer.parseInt(webPropertyUtil.getProperty("RECOMMEND_COURSE_TIME"));//缓存一小时

    public static final String HOME_INDEX_ARTILE = MEMFIX + "home_index_artilce";//首页公告
    public static final String HOME_INDEX_ARTILE_TOP = MEMFIX + "home_index_artilce_top";//首页文章排行
    public static final int HOME_INDEX_ARTILE_TIME = Integer.parseInt(webPropertyUtil.getProperty("HOME_INDEX_ARTILE_TIME"));//缓存一小时

    public static final String USER_CURRENT_LOGINTIME = MEMFIX+"USER_CURRENT_LOGINTIME_";//记录当前用户当前的登录时间，下次登录时会更新此缓存
    public static final int USER_TIME = Integer.parseInt(webPropertyUtil.getProperty("USER_TIME"));//用户登录保存时间缓存6小时

    public static final String WEBSITE_PROFILE = MEMFIX + "website_profile";//网站配置
    public static final int WEBSITE_PROFILE_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEBSITE_PROFILE_TIME"));//缓存6小时

    public static final String SHOPCART = MEMFIX + "shopcats";//购物车+userId
    public static final int SHOPCART_TIME = Integer.parseInt(webPropertyUtil.getProperty("SHOPCART_TIME"));//缓存1小时

    public static final String BANNER_IMAGES = MEMFIX + "banner_images";//广告图
    public static final int BANNER_IMAGES_TIME = Integer.parseInt(webPropertyUtil.getProperty("BANNER_IMAGES_TIME"));//缓存6小时

    public static final String WEBSITE_NAVIGATE = MEMFIX + "website_navigate";//导航配置
    public static final int WEBSITE_NAVIGATE_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEBSITE_NAVIGATE_TIME"));//缓存12小时

    public static final String USEREXPAND_INFO = MEMFIX + "user_expand_info_";//单个用户的综合信息
    public static final int USEREXPAND_INFO_TIME = Integer.parseInt(webPropertyUtil.getProperty("USEREXPAND_INFO_TIME"));//缓存1小时;

    public static final String HELP_CENTER = MEMFIX + "help_center";//帮助页面左侧菜单
    public static final int HELP_CENTER_TIME = Integer.parseInt(webPropertyUtil.getProperty("HELP_CENTER_TIME"));//缓存1小时;

    
    public static final String WEB_STATISTICS = MEMFIX + "web_statistics";//网站统计
    public static final String WEB_STATISTICS_THIRTY = MEMFIX + "web_statistics_thirty";//网站最近30条活跃统计
    public static final int WEB_STATISTICS_TIME = Integer.parseInt(webPropertyUtil.getProperty("WEB_STATISTICS_TIME"));//缓存1小时;

    public static final int SYS_USER_TIME = Integer.parseInt(webPropertyUtil.getProperty("SYS_USER_TIME"));//缓存24小时;key为随机生成的
    public static final String SYS_USER_FUNCTION = MEMFIX + "SYS_USER_FUNCTION_";//系统当前用户的权限+userid
    public static final String SYS_USER_ALL_FUNCTION = MEMFIX + "SYS_USER_ALL_FUNCTION";//系统所有权限
    public static final int SYS_USER_ALL_FUNCTION_TIME = Integer.parseInt(webPropertyUtil.getProperty("SYS_USER_ALL_FUNCTION_TIME"));//缓存24小时;
    
    public static final String WX_ACCESS_TOKEN = MEMFIX + "WX_ACCESS_TOKEN";//微信access_token
    public static final int WX_ACCESS_TOKEN_TIME = Integer.parseInt(webPropertyUtil.getProperty("WX_ACCESS_TOKEN_TIME"));//缓存1小时;

	public static final String FIND_PHONE = MEMFIX+"FIND_PHONE";//@wangzhuang 用于找回密码  
	public static final String VERIFICATION_CODE = MEMFIX+"VERIFICATION_CODE";//@wangzhuang  校验码
	public static final String VERIFICATION_CODE1 = MEMFIX+"VERIFICATION_CODE1";//@wangzhuang 用于找回密码  校验码码
	public static final int VERIFICATION_CODE_TIME = Integer.parseInt(webPropertyUtil.getProperty("VERIFICATION_CODE_TIME"));//缓存1小时;
	public static final int REGISTER_PHONE_TIME = Integer.parseInt(webPropertyUtil.getProperty("REGISTER_PHONE_TIME"));//@wangzhuang 用于找回密码  登记手机时间
	
	public static final String CHECK_CODE = MEMFIX+"CHECK_CODE";//系统发送校验码
	
	public static final String MOBILE_CODE = MEMFIX + "MOBILE_CODE_";//短信验证码 + mobile
    public static final int MOBILE_CODE_TIME = Integer.parseInt(webPropertyUtil.getProperty("MOBILE_CODE_TIME"));//缓存5分钟;

    public static final String ASSESS_INDEX="ASSESS_INDEX";//首页评论
    public static final int ASSESS_INDEX_TIME=Integer.parseInt(webPropertyUtil.getProperty("ASSESS_TIME"));//缓存一小时
    
    public static final String ORDER_INDEX="ORDER_INDEX";//首页购买记录展示
    public static final int ORDER_INDEX_TIME=Integer.parseInt(webPropertyUtil.getProperty("ORDER_TIME"));//缓存一小时
    
    public static final String INDEX_TOTAL_NUM="INDEX_TOTAL_NUM";//首页入驻老师人数  注册人数
    public static final int INDEX_TOTAL_NUM_TIME=Integer.parseInt(webPropertyUtil.getProperty("INDEX_TOTAL_NUM_TIME"));//缓存6小时

    // ---- 教师列表搜索条件缓存
    public static final String SEARCH_SUBJECT = MEMFIX + "SEARCH_SUBJECT"; // 教学阶段
    public static final int SEARCH_SUBJECT_TIME = Integer.parseInt(webPropertyUtil.getProperty("SEARCH_SUBJECT_TIME")); // 缓存12小时

    public static final String ALL_SUBJECT_GRADE = MEMFIX + "ALL_SUBJECT_GRADE"; // 教学阶段
    public static final int ALL_SUBJECT_GRADE_TIME = Integer.parseInt(webPropertyUtil.getProperty("ALL_SUBJECT_GRADE_TIME")); // 缓存12小时

    public static final String SEARCH_CITY = MEMFIX + "SEARCH_CITY"; // 城市
    public static final int SEARCH_CITY_TIME = Integer.parseInt(webPropertyUtil.getProperty("SEARCH_CITY_TIME")); // 缓存12小时

    public static final String RECOMMEND_TEACHER_ALL = MEMFIX + "recommend_teacher_all"; // 前台页面全部推荐教师
    public static final int RECOMMEND_TEACHER_ALL_TIME = Integer.parseInt(webPropertyUtil.getProperty("RECOMMEND_TEACHER_ALL_TIME"));//缓存12小时

    public static final String SEARCH_MAJOR_ALL = MEMFIX + "search_major_all"; // 前台页面全部科目集合
    public static final int SEARCH_MAJOR_ALL_TIME = Integer.parseInt(webPropertyUtil.getProperty("SEARCH_MAJOR_ALL_TIME"));//缓存12小时

    // 推荐机构缓存
    public static final String RECOMMEND_INSTITUTION_ALL = MEMFIX +"recommend_institution_all"; // 前台页面推荐机构
    public static final int RECOMMEND_INSTITUTION_ALL_TIME = Integer.parseInt(webPropertyUtil.getProperty("RECOMMEND_INSTITUTION_ALL_TIME"));//缓存6小时

} 
