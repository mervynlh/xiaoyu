<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登陆-${websitemap.web.company}-${websitemap.web.title}</title>
<style type="text/css">
/*=============commonalityStyle=============*/
	body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,form,fieldset,img,div{margin:0;padding:0;border:0;}
	body{background:#209DA6 url(/static/common/admin/images/loginBg.jpg) no-repeat center top;font:12px/20px 'SimHei','Arial','Helvetica Neue';}
	html,body {height: 100%;width: 100%;}
	ul,ol,li{list-style:none}
	.clearfix:after {content: '';display: block;height: 0;clear: both;overflow: hidden;}
	.clearfix {display: inline-block;}
	* html .clearfix {height: 1%;}
	.clearfix {display: block;}
	a{text-decoration:none;}
	a,img{border:0 none;}
	input,button{vertical-align:middle;}
	.ml10 {margin-left: 10px;}
	.wrap {width: 100%;overflow: hidden;position: relative;}
	.loginWrap {background: url(/static/common/admin/images/loginWrapBg.png) no-repeat 50% 50%;height: 630px;width: 844px;position: absolute;top: 50%;left: 50%;margin: -315px 0 0 -422px;}
	.loginIner {overflow: hidden;position: relative;width: 388px;padding: 8px 19px 0 437px;}
	.loginLogo h1 {height: 216px;text-align: center;}
	.loginLogo h1 a {background: url(<%=staticImageServer %>${logomap.logo.url}) no-repeat 50% 50%;display: block;height: 100%;text-indent: -99999px;width: 100%;}
	.loginMain {height: 322px;position: relative;}
	.loginFoot {height: 65px;text-align: center;}
	.lfTxt {color: #075273;font: 16px/65px 'Microsoft JhengHei','SimHei','Arial';}
	.loginMain ul {display: block;padding: 50px 24px 0;}
	.loginMain ul li {margin-bottom: 25px;}
	.loginMain ul li label,.loginMain ul li span {display: inline-block;vertical-align: middle;}
	.loginMain ul li label {color: #3982A3;width: 65px;text-align: right;padding-right: 15px;font: 20px/30px 'SimHei','Arial';}
	.loginMain ul li span input {background: #215770;border: 1px solid #02334A;border-radius: 6px;color: #3982A3;font-size: 18px;font-family: 'SimHei';width: 241px;height: 30px;line-height: 30px;padding: 3px 8px;}
	.loginMain ul li span.vCode input {width: 116px;}
	.loginMain center {padding-top: 10px;}
	.subWrap {border: 1px solid #255F7A;border-radius: 6px;display: inline-block;}
	.loginBtn {background: -webkit-gradient(linear, left top, left bottom, from(#255C75), to(#215770));background: -moz-linear-gradient(top, #255C75, #215770);background: -ms-linear-gradient(top, #255C75, #215770);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#255C75', endColorstr='#215770')\9;border: none;border-radius: 4px;color: #CCE2EC;cursor: default;height: 40px;font: 22px/36px 'SimHei','Arial';width: 160px;}
	.loginBtn:active {background: -webkit-gradient(linear, left top, left bottom, from(#255C75), to(#215770));background: -moz-linear-gradient(top, #2B6681, #215770);background: -ms-linear-gradient(top, #255C75, #215770);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#255C75', endColorstr='#215770')\9;}
	.tipsWrap {background: url(/static/common/admin/images/icon1.png) no-repeat 10px center;border: 1px solid #EC6060;text-align: center;height: 30px;line-height: 30px;width: 88%;position: absolute;top: 10px;left: 50%;margin-left: -44%;}
	.tipsTxt {color: #EC6060;}

</style>

<!--  jquery core -->
<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript"></script>
<script type="text/javascript">

$(document).ready(function(){
	var errorMsg = '${errmsg}';
	if(errorMsg!=null && $.trim(errorMsg.toString())!=''){
		$("#errorDiv").show();
		$("#errorTipsTxt").html(errorMsg);
	}else{
		$("#errorDiv").hide();
	}
	});
function enterSubmit(event) {
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (keyCode == 13) {
	$("#loginForm").submit();
	return false;
	}
	} 
</script>

</head>
<body id="login-bg"> 
<div class="wrap" id="wrap">
	<div id="loginWrap" class="loginWrap">
		<div class="loginIner">
			<div class="loginLogo">
				<h1><a href="/" title="${websitemap.web.company}"></a></h1>
			</div>
			<div class="loginMain">
				<form action="${ctx}/admin/sys/login" name="loginForm" method="post" style="margin: 0px;">
					<ul>
						<li><label>用户名</label><span><input type="text" name="user.loginName" /></span></li>
						<li><label>密&nbsp;&nbsp;码</label><span><input onkeyup="enterSubmit(event)" type="password" name="user.loginPwd"/></span></li>
						<li><label>验证码</label><span class="vCode"><input onkeyup="enterSubmit(event)" type="text" name="randomCode" /></span>
						<span class="ml10"><img src="${ctx}/ran/random" alt="验证码，点击图片更换" onclick="this.src='${ctx}/ran/random?random='+Math.random();" style="cursor:pointer; border:1px  solid #999 ; margin-top:4px; "/></span></li>
					</ul>
					<center>
						<label class="subWrap"><input type="submit" name="" value="登 录" class="loginBtn" ></label>
					</center>
				</form>
				
				<div class="tipsWrap" id="errorDiv">
					<span class="tipsTxt" id="errorTipsTxt"></span>
				</div>
			</div>
			<div class="loginFoot">
				<span class="lfTxt">${websitemap.web.company}</span>
			</div>
		</div>
	</div>
</div>
<div id="errorMsg" style="display: none">
${errmsg}
</div>
<!-- End: login-holder -->
<script type="text/javascript">
	function wrapH() {
		var winH,
			wl = document.getElementById("wrap"),
			ol = document.getElementById("loginWrap"),
			lH = ol.offsetHeight,
			wlH = wl.style.height;
		winH = parseInt(document.documentElement.clientHeight, 10) + parseInt(document.documentElement.scrollTop + document.body.scrollTop);
		if (wlH <= winH) {
			wl.style.height = lH + "px";
			wl.style.marginTop = -lH/2 + "px";
			wl.style.top = "50%";
		} else {
			wl.style.height = winH + "px";
			wl.style.marginTop = "";
			wl.style.top = "";
		};
		window.onresize = wrapH;
	}
	window.onload = function() {wrapH()}
	window.onresize = function() {wrapH()}
</script>
</body>
</html>

