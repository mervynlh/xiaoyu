package com.yizhilu.os.edu.service.user;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.yizhilu.os.common.constants.CommonConstants;
import com.yizhilu.os.edu.constants.web.SendMsgConstans;

/**
 * 
 * @ClassName  com.yizhilu.os.edu.service.user.SmsServiceStub
 * @description
 * @author :xujunbao
 * @Create Date : 2014年9月22日 下午5:30:39
 */
public class SmsServiceStub {
	public SmsServiceStub(){
		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
		//this.restAPI.init("app.cloopen.com", "8883");
		this.restAPI.init(CommonConstants.msgurl, "8883");
		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		this.restAPI.setAccount(CommonConstants.acountSid, CommonConstants.authToken);
		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************
		restAPI.setAppId(CommonConstants.appId);
	}
	//初始化SDK
	private CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
    private String msgContent;
    private String destNumber;
    private String param_1;
    private String param_2;
    private String param_3;
    private String param_4;
    /**
     * 发送短信 模版类型 1.发送注册码，2 忘记密码 3.发送订单购课消息 4，发送退课消息 5，发送修改时间6.确认时间，7确认课酬
     */
    private int type;
    public void sendmsg(){
    	HashMap<String, Object> result = null;
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
    	
    	if(type==SendMsgConstans.SEND_REGISTER){
    		// 注册账号短信 
    		// destNumber 用户手机号 param_1验证码 param_2 有效时间
    		result = restAPI.sendTemplateSMS(destNumber,type+"",new String[]{param_1,param_2});
    	}else if(type==SendMsgConstans.SEND_FORGET_PASSWORD){
    		// 忘记密码短信
    		// destNumber 用户手机号 param_1验证码 param_2 有效时间
    		result = restAPI.sendTemplateSMS(destNumber,type+"" ,new String[]{param_1,param_2});
    	}else if(type==SendMsgConstans.SEND_BUY_COURSE){
    		// 订单购课短信
    		// destNumber 教师手机号 param_1 学生姓名 param_2 课程姓名
    		// 用户 param_1 向您购买了 param_2 具体上课信息请您去查看订单！
    		result = restAPI.sendTemplateSMS(destNumber,type+"" ,new String[]{param_1,param_2});
    	}else if(type==SendMsgConstans.SEND_REFUND_COURSE){
    		// 退课短信
    		// destNumber 教师手机号 param_1 学生姓名 param_2 课时名
    		// 用户 param_1 已退您的课时 param_2
    		result = restAPI.sendTemplateSMS(destNumber,type+"" ,new String[]{param_1,param_2});
    	}else if(type==SendMsgConstans.SEND_UPDATE_TIME){
    		// 修改时间短信
    		// destNumber 用户手机号 param_1 姓名 param_2 时间
    		// param_1 已修改上课时间为 param_2 请您及时确认修改 !
    		result = restAPI.sendTemplateSMS(destNumber,type+"" ,new String[]{param_1,param_2});
    	}else if(type==SendMsgConstans.SEND_CONFIRM_TIME){
    		// 确认时间短信
    		// destNumber 用户手机号 param_1 姓名
    		// param_1 已确认上课时间,请您按时上课！
    		result = restAPI.sendTemplateSMS(destNumber,type+"" ,new String[]{param_1});
    	}else if(type==SendMsgConstans.SEND_CONFIRM_COURSE){
    		// 确认课酬短信
    		// destNumber 老师手机号 param_1 学生姓名 param_2 课时名
    		// 用户 param_1 已向您支付 param_2 课时的费用,请您及时查收！！
    		result = restAPI.sendTemplateSMS(destNumber,type+"" ,new String[]{param_1,param_2});
    	}
		
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
            
    }
   
    public String getMsgContent() {
        return msgContent;
    }
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    public String getDestNumber() {
        return destNumber;
    }
    public void setDestNumber(String destNumber) {
        this.destNumber = destNumber;
    }
	public String getParam_1() {
		return param_1;
	}
	public void setParam_1(String param_1) {
		this.param_1 = param_1;
	}
	public String getParam_2() {
		return param_2;
	}
	public void setParam_2(String param_2) {
		this.param_2 = param_2;
	}
	public String getParam_3() {
		return param_3;
	}
	public void setParam_3(String param_3) {
		this.param_3 = param_3;
	}
	public String getParam_4() {
		return param_4;
	}
	public void setParam_4(String param_4) {
		this.param_4 = param_4;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
    
}
