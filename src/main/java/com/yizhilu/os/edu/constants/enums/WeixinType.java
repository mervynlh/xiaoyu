package com.yizhilu.os.edu.constants.enums;

/**
 * @ClassName  com.yizhilu.os.edu.constant.enums.WeixinType
 * @description
 * @author : qinggang.liu voo@163.com
 * @Create Date : 2014-6-24 下午2:37:38
 */
public enum WeixinType {
	 add,//常规回复类型：被关注时
	 tolerate,//默认回复
	 text,//消息类型：文本
	 news,//消息类型：图文
	 image,//消息类型：图片
	 link,//消息类型：链接
	 event,//消息类型：推送
	 subscribe,//事件类型：订阅
	 unsubscribe,//事件类型：取消订阅
	 CLICK,//事件类型：自定义菜单点击事件
}
