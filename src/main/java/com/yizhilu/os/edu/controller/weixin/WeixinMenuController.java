package com.yizhilu.os.edu.controller.weixin;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.edu.common.EduBaseController;
import com.yizhilu.os.edu.entity.weixin.WeixinMenu;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.service.weixin.WeixinMenuService;
import com.yizhilu.os.edu.service.weixin.WeixinSetService;

/**
 * 线上菜单
 * 
 * @description
 * @author :JJL
 * @Create Date : 2014年6月7日 上午9:47:26
 */
@Controller
@RequestMapping("/web")
public class WeixinMenuController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(WeixinMenuController.class);


	
	@Autowired
	private WeixinMenuService weixinMenuService;
	@Autowired
	private WeixinSetService weixinSetService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	
    /**
     * 生成微信菜单
     * @return
     */
	@RequestMapping("/onmenu/create")
	@ResponseBody
    public Map<String,Object> CreateWeixinMenu(HttpServletRequest request)
    {
    	/**
		 *  button 	是 	一级菜单数组，个数应为1~3个
			sub_button 	否 	二级菜单数组，个数应为1~5个
			type 	是 	菜单的响应动作类型，目前有click、view两种类型
			name 	是 	菜单标题，不超过16个字节，子菜单不超过40个字节
			key 	click类型必须 	菜单KEY值，用于消息接口推送，不超过128字节
			url 	view类型必须 	网页链接，用户点击菜单可打开链接，不超过256字节 
		*/
    	try{
    		String access_token=weixinSetService.getWeixinAccessToken();//公众号的全局唯一票据();
    		String param="access_token="+access_token+"";
    		String urlStr=" https://api.weixin.qq.com/cgi-bin/menu/create";
    		//创建连接
            URL url = new URL(urlStr+"?"+param);
            HttpURLConnection urlCon =(HttpURLConnection)url.openConnection();
            // 使用 URL 连接进行输出，则将 DoOutput 标志设置为 true
            urlCon.setDoOutput(true);
            // 使用 URL 连接进行输入，则将 DoInput 标志设置为 true
            urlCon.setDoInput(true);
            urlCon.setRequestMethod("POST");
            urlCon.setUseCaches(false);
            urlCon.setInstanceFollowRedirects(true);
            urlCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlCon.connect();
    		
            //把json数据写入到DataOutputStream中,post请求
            DataOutputStream out = new DataOutputStream(urlCon.getOutputStream());
            String jsonmenu=menuJsonString();
            if(jsonmenu.equals(""))
            {
            	this.setJson(false, "请创建菜单", null);
            	return json;
            }
    		out.write(jsonmenu.getBytes("utf-8"));
    		out.flush();
    		out.close();
    		
    		//读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            //System.out.println(sb);
            reader.close();
            // 断开连接
            urlCon.disconnect();
    		
            this.setJson(true, "true", null);
    	}catch(Exception e){
    		logger.error("WeixinMenuController.menuJsonString", e);
    		this.setJson(false, "error", null);
    	}
    	return json;
    }
    /**
     * 菜单组装成json发送给微信服务端
     * @return
     */
    public String menuJsonString()
    {
    	Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");
		@SuppressWarnings("unchecked")
		Map<String, Object> weixinMapVal=(Map<String, Object>) weixinMap.get("weixin");
		String APPID=(String) weixinMapVal.get("wxAppID");
    	
		Gson gson = new Gson();
    	List<WeixinMenu> firstMenus=weixinMenuService.getWeixinFirstEnableMenu();//启用一级菜单集合
    	Map<String,Object> menuMap=new HashMap<String, Object>();
    	List<Map<String,Object>> button = new ArrayList<Map<String,Object>>();
    	String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
    	Pattern patt = Pattern. compile(regex );
    	for(int i=0;i<firstMenus.size();i++)
    	{
    	    List<WeixinMenu> secondMenus=weixinMenuService.getWeixinSecondEnableMenuByParentId(firstMenus.get(i).getId());
    	    Map<String,Object> firstMenuMap=new HashMap<String, Object>();//一级菜单json
    	    if(secondMenus.size()==0&&!firstMenus.get(i).getKeywordUrl().equals(""))//没有二级菜单
    	    {
    	    	Matcher m = patt.matcher(firstMenus.get(i).getKeywordUrl());
    		    if(m.matches()){//是url
    		    	firstMenuMap.put("type", "view");
    		    	if(firstMenus.get(i).getKeywordUrl().indexOf(CommonConstants.contextPath)>-1||secondMenus.get(i).getKeywordUrl().indexOf("wz")>-1){
    		    		try{
    		    			//需要openid授权的url
        		    		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+URLEncoder.encode(firstMenus.get(i).getKeywordUrl(), "UTF-8")+"&response_type=code&scope=snsapi_base#wechat_redirect";
        		    		firstMenuMap.put("url", url);
    		    		}catch(Exception e){
    		    			e.printStackTrace();
    		    		}
    		    		
    		    	}else{
    		    		firstMenuMap.put("url",firstMenus.get(i).getKeywordUrl());
    		    	}
    		    }else{//是关键词
    		    	firstMenuMap.put("type", "click");
    		    	firstMenuMap.put("key",firstMenus.get(i).getKeywordUrl());
    		    }
    		    firstMenuMap.put("name",firstMenus.get(i).getMenuName());
    		    button.add(firstMenuMap);
    	    }
    	    else//有二级菜单
    	    {
    	    	List<Map<String,Object>> sub_button = new ArrayList<Map<String,Object>>();//二级菜单集合
    	    	for(int j=0;j<secondMenus.size();j++)
        		{
    	    		Map<String,Object> secondMenuMap=new HashMap<String, Object>();//二级菜单json
    	    		Matcher m=patt.matcher(secondMenus.get(j).getKeywordUrl());  
        		    if(m.matches()){//是url
        		    	secondMenuMap.put("type", "view");
        		    	if(secondMenus.get(j).getKeywordUrl().indexOf(CommonConstants.contextPath)>-1||secondMenus.get(j).getKeywordUrl().indexOf("wz")>-1){
        		    		//需要openid授权的url
        		    		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+secondMenus.get(j).getKeywordUrl()+"&response_type=code&scope=snsapi_base#wechat_redirect";
        		    		secondMenuMap.put("url",url);
        		    	}else{
        		    		secondMenuMap.put("url",secondMenus.get(j).getKeywordUrl());
        		    	}
        		    }else{//是关键词
        		    	secondMenuMap.put("type", "click");
        		    	secondMenuMap.put("key",secondMenus.get(j).getKeywordUrl());
        		    }   
        		    secondMenuMap.put("name",secondMenus.get(j).getMenuName());
        		    sub_button.add(secondMenuMap);
        		}
        		firstMenuMap.put("name", firstMenus.get(i).getMenuName());
        		firstMenuMap.put("sub_button", sub_button);
        		button.add(firstMenuMap);
        	}
    	}
    	if(button.size()>0)//如果有菜单
    	{
    		menuMap.put("button",button);
    		System.out.println(jsonParser.parse(gson.toJson(menuMap)).getAsJsonObject().toString());
        	return jsonParser.parse(gson.toJson(menuMap)).getAsJsonObject().toString();
    	}
    	return "";
    }
    /**
     * 删除线上菜单
     * @return
     */
	@RequestMapping("/onmenu/del")
	@ResponseBody
    public Map<String,Object> delOnlineMenu(HttpServletRequest request)
    {
    	try{
    		String access_token=weixinSetService.getWeixinAccessToken();//公众号的全局唯一票据();
    		String param="access_token="+access_token+"";
    		String urlStr="https://api.weixin.qq.com/cgi-bin/menu/delete";
    		//创建连接
            URL url = new URL(urlStr+"?"+param);
            HttpURLConnection urlCon =(HttpURLConnection)url.openConnection();
            urlCon.connect();
          //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            //System.out.println(sb);
            reader.close();
            // 断开连接
            urlCon.disconnect();
            this.setJson(true, "true", null);
    	}catch(Exception e)
    	{
    		logger.error("WeixinMenuController.delOnlineMenu", e);
    		this.setJson(false, "error", null);
    	}
    	return json;
    }

   

}
