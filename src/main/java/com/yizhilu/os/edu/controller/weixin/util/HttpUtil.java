package com.yizhilu.os.edu.controller.weixin.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 请求校验工具类
 * 
 * @author 张超
 * @date 2013-05-18   
 */
public class HttpUtil {
	private static Gson gson=new Gson();
	
	public static Map<String,Object> doGet(String urlStr,String param){
		try{
			if(param!=null&&!param.equals("")){
				urlStr+="?"+param;
			}
			URL url = new URL(urlStr);
			URLConnection urlCon = url.openConnection();
			// 使用 URL 连接进行输入，则将 DoInput 标志设置为 true
			urlCon.setDoInput(true);
			// 使用 URL 连接进行输出，则将 DoOutput 标志设置为 true
			urlCon.setDoOutput(true);
			urlCon.setRequestProperty("accept", "*/*");
			urlCon.setRequestProperty("connection", "Keep-Alive");
			urlCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			//建立实际链接
			urlCon.connect();
			//获取所有响应头字段
			Map<String,List<String>> map = urlCon.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			BufferedReader buffer=new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line;
			String tmpStr="";
			while ((line = buffer.readLine())!= null)
			{
				tmpStr +=line;
			}
			return gson.fromJson(tmpStr,new TypeToken<Map<String,Object>>(){}.getType());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	 public static Map<String,Object> doPostJson(String url , Map<String,Object> map){   
        String result = null;     
        try {  
        	 //创建连接
            URL urlClient = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlClient.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            
            out.writeBytes(gson.toJson(map).toString());
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            result=sb.toString();
        } catch(Exception e){
        	e.printStackTrace();
        }
        return gson.fromJson(result.toString(),new TypeToken<Map<String,Object>>(){}.getType());
    }  
	
	public static String doPostXml(String urlStr , String xmlStr){ 
		String result = null; 
		try {   
			 //创建连接
            URL urlClient = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) urlClient.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(xmlStr.getBytes());
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            connection.disconnect();
            result=sb.toString();
		} catch(Exception e){
        	e.printStackTrace();
        } 
		return result;
	}

}