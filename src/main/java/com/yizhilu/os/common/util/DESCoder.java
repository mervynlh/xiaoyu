package com.yizhilu.os.common.util;
/**  
 * 概要:DES加密算法，兼容PHP的解密  
 * @author cailin  
 * @since v2.0  
 */  


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;  

import com.yizhilu.os.core.util.MD5;

/**
 * 微信加密
 * @author Administrator
 *
 */

public class DESCoder    
{  
	private final static String KEY = "sedu2010"; // 字节数必须是8的倍数  
	public  static String SECONDKEY = "sedu2010";//自定义加密第二变量
	public static byte[] desEncrypt(byte[] plainText) throws Exception  
	{  
		SecureRandom sr = new SecureRandom();  
		DESKeySpec dks = new DESKeySpec(KEY.getBytes());  
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
		SecretKey key = keyFactory.generateSecret(dks);  
		Cipher cipher = Cipher.getInstance("DES");  
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
		byte data[] = plainText;  
		byte encryptedData[] = cipher.doFinal(data);  
		return encryptedData;  
	}  


    
    /**
	 * MessageDigest类为应用程序提供信息摘要算法的功能，如MD5 或SHA算法。
信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
MessageDigest对象开始被初始化。该对象通过使用 update()方法处理数据。
任何时候都可以调用 reset（）方法重置摘要。
一旦所有需要更新的数据都已经被更新了，应该调用digest() 方法之一完成哈希计算。

	 * @param strSrc
	 * @return
	 */
	public static String sha1(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);//获得哈希值
			//对于给定数量的更新数据，digest方法只能被调用一次。
			//在调用digest之后，MessageDigest 对象被重新设置成其初始状态。
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}
	public static String bytes2Hex(byte[] bts) {//二行制转字符串
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
	/**
	 * 微信随机字符串noncestr
	 * @return
	 */
	public static String getNonceStr() {
		Random random = new Random();
		return MD5.getMD5(String.valueOf(random.nextInt(10000)));
	}
	/**
	 * 微信当前时间点timestamp
	 * @return
	 */
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
   //创建签名SHA1
	public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = signParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
			//要采用URLENCODER的原始值！
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
		System.out.println("sha1 sb:" + params);
		return getSha1(params);
	}
	//Sha1签名
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}  