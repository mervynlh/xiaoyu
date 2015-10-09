package com.yizhilu.os.edu.entity.weixin;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class WeixinXml implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3283018108386486048L;
			
	/**
	 *  ToUserName 	是 	接收方帐号（收到的OpenID）
		FromUserName 	是 	开发者微信号
		CreateTime 	是 	消息创建时间 （整型）
		MsgType 	是 	news
		ArticleCount 	是 	图文消息个数，限制为10条以内
		Articles 	是 	多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
		Title 	 	 否 		图文消息标题
		Description 		否 	图文消息描述
		PicUrl 		否 	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		Url 		否 	点击图文消息跳转链接 
	 */

	
    public String getStringother(String to,String fm,Date ct,String cn) throws UnsupportedEncodingException {
    	String stringother="<xml>"
				+ "<ToUserName><![CDATA["+to+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+fm+"]]></FromUserName>"
				+ "<CreateTime>"+ct.getTime()+"</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA["+URLEncoder.encode("欢迎来到268教育","utf-8")+"]]></Content>" + "</xml>";
		return stringother;
	}
    
    //回复文本消息
	public String getStringXmlText(String to,String fm,Date ct,String cn) throws UnsupportedEncodingException {
		
		String stringXmlText = "<xml>"
				+ "<ToUserName><![CDATA["+to+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+fm+"]]></FromUserName>"
				+ "<CreateTime>"+ct.getTime()+"</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA["+cn+"]]></Content>" + "</xml>";
		return stringXmlText ;
	}
	//回复图片消息
	public String getStringXmlImage(String to,String fm,Date ct,String cn) throws UnsupportedEncodingException {
		String stringXmlImage= "<xml>"
				+ "<ToUserName><![CDATA["+to+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+fm+"]]></FromUserName>"
				+ "<CreateTime>"+ct.getTime()+"</CreateTime>"
				+ "<MsgType><![CDATA[image]]></MsgType>" + "<Image>"
				+ "<MediaId><![CDATA["+URLEncoder.encode(cn,"utf-8")+"]]></MediaId>" + "</Image>" + "</xml>";
		
		return stringXmlImage;
	}
	//回复图文消息
	public String getStringXmlTxtImg(String to,String fm,Date dt,String imgurl,String des) throws UnsupportedEncodingException {
		String stringXmlTxtImg = "<xml>"
				+ "<ToUserName><![CDATA["+to+"]]></ToUserName>"
				+ "<FromUserName><![CDATA["+fm+"]]></FromUserName>"
				+ "<CreateTime>"+dt.getTime()+"</CreateTime>"
				+ "<MsgType><![CDATA[news]]></MsgType>"
				+ "<ArticleCount>2</ArticleCount>" + "<Articles>" + "<item>"
				+ "<Title><![CDATA[title1]]></Title>"
				+ "<Description><![CDATA[description1]]></Description>"
				+ "<PicUrl><![CDATA[picurl]]></PicUrl>"
				+ "<Url><![CDATA[url]]></Url>" + "</item>" + "<item>"
				+ "<Title><![CDATA[title]]></Title>"
				+ "<Description><![CDATA["+URLEncoder.encode(des,"utf-8")+"]]></Description>"
				+ "<PicUrl><![CDATA["+imgurl+"]]></PicUrl>"
				+ "<Url><![CDATA[url]]></Url>" + "</item>" + "</Articles>"
				+ "</xml>";
		
		return stringXmlTxtImg;
	}
	//微信支付package
	public String getStringXmlPackage(String appId,String nonceStr,String timeStamp,String packageStr,String retCode,String retErrMsg,String appSignature) throws UnsupportedEncodingException {
		String stringXmlPackage = "<xml>"
				+ "<AppId><![CDATA["+appId+"]]></AppId>"
				+ "<NonceStr><![CDATA["+nonceStr+"]]></NonceStr>"
				+ "<TimeStamp>"+timeStamp+"</TimeStamp>"
				+ "<Package><![CDATA["+packageStr+"]]></Package>"
				+ "<RetCode>"+retCode+"</RetCode>"
				+ "<RetErrMsg><![CDATA["+retErrMsg+"]]></RetErrMsg>"
				+ "<SignMethod><![CDATA[sha1]]></SignMethod>"
				+ "<AppSignature><![CDATA["+appSignature+"]]></AppSignature>"
				+ "</xml>";
		
		return stringXmlPackage;
	}
	
	
	
	
	
	
}
