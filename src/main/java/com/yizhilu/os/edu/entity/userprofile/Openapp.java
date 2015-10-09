package com.yizhilu.os.edu.entity.userprofile;

import java.io.Serializable;

public class Openapp implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8018354152776078682L;
	private String appId;
    private String appType;
    private String email;
    private String desc;
    private Long cusId;
    private Long id;
    private String sign;
    public static String Key="hqkswww";
        
    
    public static String getKey() {
		return Key;
	}

	public static void setKey(String key) {
		Key = key;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the appType
	 */
	public String getAppType() {
		return appType;
	}

	/**
	 * @param appType the appType to set
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email; 
    }
        
    public String getDesc(){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc; 
    }
        
    public Long getCusId(){
        return cusId;
    }

    public void setCusId(Long cusId){
        this.cusId = cusId; 
    }
        
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id; 
    }

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
    
}
