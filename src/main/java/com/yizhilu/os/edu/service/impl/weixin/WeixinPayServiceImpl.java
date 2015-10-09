package com.yizhilu.os.edu.service.impl.weixin;


import java.util.HashMap;
import java.util.Map;


import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhilu.os.edu.constants.enums.WebSiteProfileType;
import com.yizhilu.os.edu.controller.weixin.util.PackageUtil;
import com.yizhilu.os.edu.service.website.WebsiteProfileService;
import com.yizhilu.os.edu.service.weixin.WeixinPayService;
/**
 * 微信常规回复设置service
 * @author Administrator
 *
 */
@Service("weixinPayService")
public class WeixinPayServiceImpl implements WeixinPayService {
	
	@Autowired
	private WebsiteProfileService websiteProfileService;

	/**
	 * 微信支付请求
	 *
	 * @return
	 */
	public String getWxpayUrl(String requestId, String type) {
		Map<String,String> websitemap = getWxpayInfo();//获得微信支付配置
		String timeStanmp = PackageUtil.getTimeStamp();//timestamp 时间点
		String nonceStr = PackageUtil.getNonceStr();//noncestr 随机字符串
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		signParams.put("appid",websitemap.get("appid"));//qppid
		signParams.put("mch_id",websitemap.get("mchid"));//商户号id
		signParams.put("time_stamp",timeStanmp);//时间戳
		signParams.put("nonce_str",nonceStr);//随机字符串
		signParams.put("product_id",requestId + "#" + type);// 对外支付订单号

		String sign = PackageUtil.createSign(signParams,websitemap.get("payKey"));
		String params = "sign="+sign+"&appid="+websitemap.get("appid")+"&mch_id="+websitemap.get("mchid")+"&product_id="+requestId+"#"+type+"&time_stamp="+timeStanmp+"&nonce_str="+nonceStr;
		return "weixin://wxpay/bizpayurl?"+params;
	}

	/**
	 * 获取微信 密钥
	 * @return
	 */
	public Map<String,String> getWxpayInfo(){
		//获得微信支付配置
		Map<String,Object> weixinMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.weixin.toString());
		//获得详细info
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String, Object>) weixinMap.get(WebSiteProfileType.weixin.toString());
		if(map != null){
			Map<String,String> websitemap = new HashMap<String,String>();
			websitemap.put("appid", map.get("wxAppID").toString());//appId
			websitemap.put("payKey", map.get("wxPayKey").toString());//支付密钥
			websitemap.put("mchid",map.get("wxMchId").toString() );//商户id
			return websitemap;
		}
		return null;
	}
}


