<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="com.yizhilu.os.common.constants.CommonConstants"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%!//项目路径
	//图片、CSS、js 服务器地址，静态资源地址
	static String imagesPath = CommonConstants.staticServer;
	//资源文件版本号 
	static  String version = CommonConstants.VERSION;
	
	//存储用户cookie的key
	static String usercookiekey = CommonConstants.USER_SINGEL_ID;
	
	static String usercookiename = CommonConstants.USER_SINGEL_NAME;
	
	static String mydomain=CommonConstants.MYDOMAIN;

	//上传服务用服务器地址，uploadImageServer，数据库中不存储域名
	static String uploadServerUrl=CommonConstants.uploadImageServer;
	/*
	*上传图片后访问的地址，正常跟upStaticPath或者imagesPath为同一路径
	*防止项目部署到多台机器，单独定义此变量
	*/
    static String staticImageServer=CommonConstants.staticImageServer;

	//kindeditor中使用的路径需要2个参数来区分项目和模块
	static String keImageUploadUrl = uploadServerUrl+"/imgk4?base="+CommonConstants.projectName;
	//单独的上传按钮使用的路径
	static String uploadSimpleUrl = uploadServerUrl+"/gok4?base="+CommonConstants.projectName;
	//upload的js上传使用的路径
	static String uploadSwfUrl = uploadServerUrl + "/goswf";
	//小组头像上传路径
	static String disFaceUpUrl = uploadServerUrl+"/saveface?base="+CommonConstants.projectName+"&param=dis";
	//上传视频文件
	static String videoUpload = uploadServerUrl + "/file";
	%>
	<c:set var="ctx" value="<%=CommonConstants.contextPath%>"></c:set>
	<c:set var="ctximg" value="<%=CommonConstants.contextPath%>"></c:set>
	<c:set var="v" value="<%=version%>"></c:set>
