<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<title>绑定</title>


<script type="text/javascript">

    function bind(){
    	var xml="<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName>"+
		"<CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event>"+
		"<EventKey><![CDATA[联系电话]]></EventKey></xml>";
	
	 	var xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("POST","${ctx}/web/onweixin/manage?timestamp=123456789&nonce=xxxxxx");
	    xmlHttp.setRequestHeader("Content-Type", "text/xml");    
	    xmlHttp.send(xml);

	}
</script>
</head>
<body>
	<div>
		<input type="button" value="绑定" onclick="bind()"/>
	</div>
</body>
</html>