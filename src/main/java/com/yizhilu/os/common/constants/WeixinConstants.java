package com.yizhilu.os.common.constants;

import com.yizhilu.os.core.util.PropertyUtil;

/**
 * 微信登录配置
 * @author Administrator
 * 2015-8-4
 */
public class WeixinConstants {

    public static String propertyFile = "weixinconnectconfig";// 配置文件名字
    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance(propertyFile);

    // 应用唯一标识
    public static final String appid = propertyUtil.getProperty("appid");
    // 重定向地址，需要进行UrlEncode
    public static final String redirect_uri=propertyUtil.getProperty("redirect_uri");
    // 填code
    public static final String response_type=propertyUtil.getProperty("response_type");
    // 网页应用目前仅填写snsapi_login即可
    public static final String scope=propertyUtil.getProperty("scope");
    //应用密钥AppSecret，在微信开放平台提交应用审核通过后获得
    public static final String secret=propertyUtil.getProperty("secret");
    //网站内嵌二维码微信登录JS代码中href
    public static final String href=propertyUtil.getProperty("href");
}
