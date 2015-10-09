package com.yizhilu.os.edu.entity.website;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * banner图管理
 * @ClassName  com.yizhilu.os.edu.entity.website.WebsiteImages
 * @description
 * @author :xujunbao
 * @Create Date : 2014年6月9日 下午1:52:49
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class WebsiteImages implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String imagesUrl;//banner图地址
    private String mobileImagesUrl;//微站banner图地址
    private String linkAddress;//banner图连接地址
    private String title;//banner图标题
    /**
     * indexCenterBanner,//首页banner图轮播
    indexAdvertImages1,//首页广告位1
    indexAdvertImages2,//首页广告位2
    userPersonalityImages//个人中心自定义皮肤功能
     */
    private String keyWord;//关键字
    private int seriesNumber;//序列号
    private String color;
    private String previewUrl;
	
}
