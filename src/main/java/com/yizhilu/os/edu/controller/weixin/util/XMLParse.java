/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.yizhilu.os.edu.controller.weixin.util;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.SortedMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.core.util.web.WebUtils;
import com.yizhilu.os.edu.constants.enums.WeixinType;
import com.yizhilu.os.edu.entity.weixin.WeixinReply;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
public class XMLParse {

	/**
	 * 提取出xml数据包中的加密消息
	 * @param xmltext 待提取的xml字符串
	 * @return 提取出的加密消息字符串
	 * @throws AesException 
	 */
	public static Object[] extract(String xmltext) throws AesException     {
		Object[] result = new Object[3];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("ToUserName");
			result[0] = 0;
			result[1] = nodelist1.item(0).getTextContent();
			result[2] = nodelist2.item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseXmlError);
		}
	}

	/**
	 * 生成xml消息
	 * @param encrypt 加密后的消息密文
	 * @param signature 安全签名
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return 生成的xml字符串
	 */
	public static String generate(String encrypt, String signature, String timestamp, String nonce) {

		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
				+ "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
		return String.format(format, encrypt, signature, timestamp, nonce);

	}
	
	
	
	/**
	 * 客服xml
	 * @param toUserName 发送方为订阅号
	 * @param fromUserName 接收方为微信用户
	 * @param timestamp 时间
	 * @param content 回复内容
	 * @return
	 */
	public static String generateService(String toUserName, String fromUserName, long timestamp) {
		String format = "<xml>\n" + "<ToUserName><![CDATA[%1$s]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>\n"
				+ "<CreateTime>%3$s</CreateTime>\n"
				+ "<MsgType><![CDATA[transfer_customer_service]]></MsgType>\n" + "</xml>";
		//回复客服类型消息
		return String.format(format, toUserName, fromUserName, timestamp);

	}
	
	/**
	 * 文本xml
	 * @param toUserName 发送方为订阅号
	 * @param fromUserName 接收方为微信用户
	 * @param timestamp 时间
	 * @param content 回复内容
	 * @return
	 */
	public static String generateText(String toUserName, String fromUserName, long timestamp,String content) {
		String format = "<xml>\n" + "<ToUserName><![CDATA[%1$s]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>\n"
				+ "<CreateTime>%3$s</CreateTime>\n"+ "<MsgType><![CDATA[%4$s]]></MsgType>\n"
				+ "<Content><![CDATA[%5$s]]></Content>\n" + "</xml>";
		//回复类型，文本
		return String.format(format, toUserName, fromUserName, timestamp, WeixinType.text.toString(),content);

	}
	/**
	 * 多图文xml
	 * @param toUserName 发送方为订阅号
	 * @param fromUserName 接收方为微信用户
	 * @param timestamp 时间
	 * @param weixinReplys 回复List
	 * @return
	 */
	public static String generateImageText(String toUserName, String fromUserName, long timestamp,List<WeixinReply> weixinReplys) {
		String format = "<xml>\n" + "<ToUserName><![CDATA[%1$s]]></ToUserName>\n"
				+ "<FromUserName><![CDATA[%2$s]]></FromUserName>\n"
				+ "<CreateTime>%3$s</CreateTime>\n"+ "<MsgType><![CDATA[%4$s]]></MsgType>\n"
				+ "<ArticleCount>%5$s</ArticleCount>\n<Articles>\n";
		
		for(WeixinReply weixinReply : weixinReplys){
			format+="<item>\n"
				+ "<Title><![CDATA["+weixinReply.getTitle()+"]]></Title>\n"
				+ "<Description><![CDATA["+getSummary(weixinReply.getContent())+"]]></Description>\n"
				+ "<PicUrl><![CDATA["+CommonConstants.staticImageServer+weixinReply.getImageUrl()+"]]></PicUrl>\n"
				+ "<Url><![CDATA["+CommonConstants.contextPath+"/web/weixin/content/"+weixinReply.getId()+"]]></Url>\n"
				+ "</item>\n";
		}		
		format+= "</Articles>\n</xml>";
		//回复类型，图文
		return String.format(format, toUserName, fromUserName, timestamp, WeixinType.news.toString(),weixinReplys.size());

	}
	
	/**
	 * 获取图文回复摘要
	 * @return
	 */
	public static String  getSummary(String content)
	{
		String summary=WebUtils.replaceTagHTML(content);//只保存图文内容的文字
		summary=summary.replaceAll("[\\n\\r]"," ");//换行替换为" "
		summary=summary.replaceAll("[\\s]"," ");//把多个" "替换为一个" "
		if(summary.length()>52)
		{
			summary="    "+summary.substring(0,52);//截取部分作为摘要,并设置首行缩进
		}
		else
		{
			summary="    "+summary;
		}
		return  summary;
	}
	
	/**
	 * 微信支付package
	 * @param packageParams
	 * @param sign
	 * @return
	 */
	public static String generateXmlPackage(SortedMap<String, String> packageParams,String sign) {
		String xmlPackage="";
		try {
			xmlPackage = "<xml>"
					+ "<appid>"+packageParams.get("appid")+"</appid>"
					+ "<attach><![CDATA["+packageParams.get("attach")+"]]></attach>"
					+ "<body><![CDATA["+packageParams.get("body")+"]]></body>"
					+ "<mch_id>"+packageParams.get("mch_id")+"</mch_id>"
					+ "<nonce_str>"+packageParams.get("nonce_str")+"</nonce_str>"
					+ "<notify_url>"+packageParams.get("notify_url")+"</notify_url>"
					+ "<out_trade_no>"+packageParams.get("out_trade_no")+"</out_trade_no>"
					+ "<spbill_create_ip>"+packageParams.get("spbill_create_ip")+"</spbill_create_ip>"
					+ "<total_fee>"+packageParams.get("total_fee")+"</total_fee>"
					+ "<trade_type>"+packageParams.get("trade_type")+"</trade_type>"
					+ "<product_id>"+packageParams.get("product_id")+"</product_id>"
					+ "<time_start>"+packageParams.get("time_start")+"</time_start>"
					+ "<time_expire>"+packageParams.get("time_expire")+"</time_expire>"
					+ "<sign><![CDATA["+sign+"]]></sign>"
					+ "</xml>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlPackage;
	}
	
	/**
	 * 微信支付返回package
	 * @param packageParams
	 * @param sign
	 * @return
	 */
	public static String returnXmlPackage(SortedMap<String, String> packageParams,String sign) {
		String xmlPackage = "<xml>"
				+ "<return_code>"+packageParams.get("return_code")+"</return_code>"
				+ "<appid><![CDATA["+packageParams.get("appid")+"]]></appid>"
				+ "<mch_id>"+packageParams.get("mch_id")+"</mch_id>"
				+ "<nonce_str>"+packageParams.get("nonce_str")+"</nonce_str>"
				+ "<prepay_id>"+packageParams.get("prepay_id")+"</prepay_id>"
				+ "<result_code>"+packageParams.get("result_code")+"</result_code>"
				+ "<err_code_des><![CDATA["+packageParams.get("err_code_des")+"]]></err_code_des>"
				+ "<sign><![CDATA["+sign+"]]></sign>"
				+ "</xml>";
		return xmlPackage;
	}
	
}
