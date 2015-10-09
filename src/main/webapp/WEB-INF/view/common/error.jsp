<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.yizhilu.os.core.util.ObjectUtils"%>
<%@ include file="/base.jsp"%>
<%@page isErrorPage="true" %>
<html>
<head>
<title>系统异常</title>
	<style type="text/css">
		* {margin: 0;padding: 0;}
		/*body {background: #666666;color: #FEFEFE;}
		.W-body {background: #666;}*/
		.e-4-wrap {background: #e0e0e0 url(${ctx}/static/edu/img/erer.png) no-repeat 50px 71px;margin: 0 auto 0;width: 750px;overflow: hidden;}
		.e-4-wrap .fl-1,.e-4-wrap .fl-2 {float: left;height:385px;position: relative;}
		.e-4-wrap .fl-1 {width: 390px;text-align: center;}
		.e-4-wrap .fl-2 {width: 357px;}
		.e-4-title {position: absolute;top: 302px;width: 283px;}
		.e-4-title span {color: #fff;font: 700 90px/100px 'SimHei';}
		.e-4-txt-wrap {padding: 20px;overflow: hidden;margin-top:20px;}
		.e-4-txt-wrap h2 {font: 30px/60px 'SimHei';color: #DD4C39;margin-bottom: 10px;}
		.e-4-t-1 {font: 15px/24px 'SimHei';color: #777;}
		.e-4-txt-wrap h4 {font: 18px/26px 'SimHei';color: #aaa;}
		.e-4-t-1 a {color: #41bb8a;}
	</style>
	<script type="text/javascript">
	function showOrCloseErrorInfo() {
		var div = document.getElementById("error_div");
		if(div.style.display == "none") {
			div.style.display = "block";
		} else {
			div.style.display = "none";
		}
	}
</script>
</head>
<body >

<!-- 主体 开始-->
<div class="pt50">
	<div class="e-4-wrap">
         <div class="fl-1">
             
         </div>
		<div class="fl-2">
			<div class="e-4-txt-wrap">
				<h2>系统访问异常！</h2>
				<div>
					<p class="e-4-t-1">无法访本页的原因是：</p>
					<p class="e-4-t-1">你使用的URL可能拼写错误或者它只是临时脱机</p>
					<p class="e-4-t-1">所访问的页面不存在或被管理员已删除</p>
				</div>
				<br>
				<br>
				<div>
					<h4>请尝试以下操作：</h4>
					<p class="e-4-t-1">1、尝试按F5进行页面刷新</p>
					<p class="e-4-t-1">2、重新键入URL地址进入访问</p>
					<p class="e-4-t-1">3、或者去 <a href="/" title="首页">首页</a>,<a href="javascript:window.history.go(-1)" title="返回">返回</a></p>
					<p class="e-4-t-1"><a href="#"  onclick="showOrCloseErrorInfo()" class="c-green" > 查看详细异常</a></p>
				</div>
			</div>
		</div>
    </div>
</div>
	<div id="error_div" style="display:none" align="left">
			 <%
			 	//show error message
			 	if(!ObjectUtils.isNull(exception)){
			 	   StackTraceElement[] messages = exception.getStackTrace();
			        if (!ObjectUtils.isNull(messages)) {
			            StringBuffer buffer = new StringBuffer();
			            buffer.append(exception.toString()).append("<br/>");
			            for (int i = 0; i < messages.length; i++) {
			                buffer.append(messages[i].toString()).append("<br/>");
			            }
			            out.println(buffer.toString());
			        }
			 	}
			 	 /* else{
		            out.println(request.getAttribute("myexception").toString());
		        }  */ 
	           %>
	</div>
</body>
</html>