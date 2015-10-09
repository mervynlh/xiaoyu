package com.yizhilu.os.edu.controller.weixin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class PackageUtil {
	
	// 特殊字符处理
	public static String UrlEncode(String src) throws UnsupportedEncodingException {
		return URLEncoder.encode(src,"UTF-8").replace("+", "%20");
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public static String createSign(SortedMap<String, String> packageParams,String payKey) {
		StringBuffer sb = new StringBuffer();
		Set<Map.Entry<String,String>> es = packageParams.entrySet();
		Iterator<Map.Entry<String,String>> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry<String,String> entry = (Map.Entry<String,String>) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + payKey);
		System.out.println("md5 sb:" + sb);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();

		return sign;

	}
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
}
