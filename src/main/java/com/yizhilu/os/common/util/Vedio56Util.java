package com.yizhilu.os.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import com.yizhilu.os.core.util.MD5;


public class Vedio56Util {
	
	private final String domain;
	private final String interfaceURL;
	private final String p56appID;
	private final String p56appKEY;
	
	
	public Vedio56Util(String domain,String interfaceURL,String p56appID,String p56appKEY) {
		//初始化appkey和secret
		this.p56appID = p56appID;
		this.p56appKEY = p56appKEY;
		this.interfaceURL = interfaceURL;
		this.domain = domain;
	}
	
	/**
	 * 
	 * 功能：获取视频上传组件的url地址
	 * @param category(第三方视频分类)
	 * @param css (自定义视频组件的样式)
	 * @param ourl (上传成功后回调你的地址，必须是外网可访问的，如:http://www.wlotx.com/test/success.jsp)
	 * @param rurl (上传失败后的回调地址，如:http://www.wlotx.com/test/fail.jsp)
	 * @param isPublic(是否公开视频(公开-y,不公开-n))
	 * @return 
	 */
	public String getVideoComponenUrl(String category,String css,String ourl,String rurl ,String isPublic,String sid){
		 String url=domain+interfaceURL;
		 String fields = null;
	        try {
	        	category = URLEncoder.encode(category,"utf-8");
	        	//css = URLEncoder.encode(css,"utf-8");
	        	ourl = URLEncoder.encode(ourl,"utf-8");
	        	rurl = URLEncoder.encode(rurl,"utf-8");
	        	//isPublic = URLEncoder.encode(isPublic,"utf-8");
	        	sid = URLEncoder.encode(sid,"utf-8");
	        	fields = URLEncoder.encode("title-标题,tag-标签,content-内容","utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
	        HashMap<String, String> params = new HashMap<String, String>();
	        params.put("category", category);
	        params.put("css", css);
	        params.put("ourl", ourl);
	        params.put("rurl", rurl);
	        params.put("sid",sid);
	        params.put("public", isPublic);
	        params.put("fields", fields);
	        url = url+"?"+signRequest(params);
	        if(signRequest(params).equals(""))
	        {
	        	url+="f";
	    		
	        }
	        return url;
	        	
	}
	
	//获取视频信息
	public String  getVideoInfoApp(String vid){
        String url=domain+interfaceURL;
        try {
			vid = URLEncoder.encode(vid,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
        HashMap<String, String> params = new HashMap<String, String>();
        
        params.put("vid", vid);
        
        if(signRequest(params).equals(""))
        {
        	return "";
        }

        	url = url+"?"+signRequest(params);
        	return httpCall(url);

        
   }
	
	
	//统一的http请求
	private String httpCall(String url){
		
		HttpClient client = new HttpClient();	//实例化httpClient
		HttpMethod method = new GetMethod(url);	//
		String responseContent = "";
		try {
			client.executeMethod(method);		//执行		

			InputStream jsonStr;

			jsonStr = method.getResponseBodyAsStream();			
				
			ByteArrayOutputStream   baos   =   new   ByteArrayOutputStream(); 
			
	        int   i=-1; 
	        while((i=jsonStr.read())!=-1){ 
	        	baos.write(i); 
	        }
	        
	        responseContent = baos.toString(); 
	        
	        jsonStr.close();
	        baos.close();
	        method.releaseConnection();
	        
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseContent;
	}
	
    //签名方法实现，并构造一个参数串
    private String signRequest(HashMap< String, String> params){
    	String req = MD5.getMD5(mapToString(params));	//第一轮次计算 MD5加密
    	//获取时间戳
    	Date date = new Date();
    	long time = date.getTime();
    	//mysq 时间戳只有10位 要做处理
    	String ts = time + "";
    	ts = ts.substring(0, 10);
    	if(p56appID==null||p56appID.equals("")||p56appKEY==null||p56appKEY.equals(""))
    	{
    		return "";
    	}

	    	params.put("sign", MD5.getMD5(req+"#"+p56appID+"#"+p56appKEY+"#"+ts));   //第二轮次计算 MD5加密
	    	params.put("appkey", p56appID);
	    	params.put("ts", ts);
	    	return mapToString(params);

    }
    
    //将 map 中的参数及对应值转换为字符串
    private String mapToString(HashMap<String, String> params){
    	Object[] array = params.keySet().toArray();
    	
    	java.util.Arrays.sort(array);
    	String str = "";
    	for(int i =0; i<=array.length-1;i++){
    		String key = array[i].toString();
    		if(i!=array.length-1){
    			str += key+"="+params.get(key)+"&";
    		}else{
    			str += key+"="+params.get(key);
    		}
    	}
    	return str;
    }
    
}
