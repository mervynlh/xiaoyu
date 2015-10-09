<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提示信息</title>
<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
<script type="text/javascript" src="<%=imagesPath%>/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<style type="text/css">
.retrieve_password, .e-mail_back, .phone_back {background: none repeat scroll 0 0 #FFFFFF;border: 1px solid #E2E2E2;margin: 20px auto 0;overflow: hidden;padding: 20px;width: 1000px;}
.retrieve_password h3, .e-mail_back h3, .phone_back h3 {border-bottom: 2px solid #00A8FF;color: #00A8FF;font-family: "Microsoft YaHei";font-size: 20px;height: 20px;line-height: 20px;overflow: hidden;padding: 5px;}
.retrieve_password h3 span, .contents h4 span {color: #BABABA;font-size: 12px;font-weight: normal;line-height: 25px;padding-left: 8px;}
.con_right {border-left: 1px solid #DCDCDC;}
.retrieve_password h3 span,.contents h4 span{ font-size:12px; color:#bababa; padding-left:8px; line-height:25px; font-weight:normal;}
.contents{ width:405px; margin:15px auto 30px 33px; float:left; display:inline;overflow: hidden;}

.con_left{ overflow:hidden;}
.contents h4{ width:340px; height:30px; line-height:30px; color:#000000; font-size:14px; margin:10px 0 20px 10px; border-bottom:#dcdcdc 1px dashed;}
.fa_p1{ width:330px; line-height:24px; overflow:hidden; padding:8px 0; margin:0;}
.fa_p1 label,.chooseitem label{ width:80px; text-align:right; font-size:12px; float:left;}
.fa_p1 .text_input{ width:240px; height:26px; line-height:26px; border:1px solid #16559F;border-radius: 4px; font-size:12px; color:#aaa; text-indent:5px;}
.fa_p1 .code_input{ width:80px; height:26px; line-height:26px; border:1px solid #16559F;border-radius: 4px; float:left;color:#aaa; margin-right:5px;text-indent:5px;}
.fa_p1 .code_img{ float:left;}
.change_code a {color: #FE7F02;}
.fa_p1 .change_code{ width:85px; float:left; height:24px; line-height:24px; padding-left:5px;}
.commBtn04 {display: inline-block;padding: 0px 16px;font-size: 14px;color: #FFF;border-radius: 6px;font-weight: bold; padding: 6px 30px;}
.commBtn04 {background: -webkit-gradient(linear, left top, left bottom, from(#FF7300), to(#EF7C1D));background: -moz-linear-gradient(top, #FF7300, #EF7C1D);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF7300', endColorstr='#EF7C1D');}
.commBtn04:hover {background: -webkit-gradient(linear, left top, left bottom, from(#FF7300), to(#EF7C1D));background: -moz-linear-gradient(top, #FF7300, #EF7C1D);filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#FF7300', endColorstr='#EF7C1D'); text-decoration: none; color: #F2F2F2;}

.send_password{ width:860px; overflow:hidden; margin:0 auto;}
.send_password h6{ width:850px; text-align:right; font-size:12px; padding-top:5px;}
.send_password h6 a{ color:#333333; text-decoration:none;}
.send_password h6 a:hover{ color:#333333;}
.send_pass_txt{ width:660px; overflow:hidden; margin:30px auto 30px;}
.send_pass_txt .pic{ width:115px; height:94px; float:left;}
.send_pass_txt .cont{ width:500px; float:left; margin-left:40px;}
.send_pass_txt .cont .fg_p1{ font-size:14px; color:#000; padding:5px; margin:0;}
.send_pass_txt .cont .fg_p1 span{ color:#FF3366; padding:0 5px;}
.send_pass_txt .cont .fg_p1 a{ color:#0000FF; text-decoration:underline; font-size:12px;}
.send_pass_txt .cont .fg_p2{ color:#bababa; font-size:14px; padding:5px; margin:0;}
.send_pass_txt .cont .fg_p2 a{ text-decoration:underline;}
.send_pass_txt .cont .fg_p3{ font-size:14px; padding:5px; margin:0;}
.send_pass_txt .cont .fg_p3 span{ font-size:14px; padding:0 5px;}
.forget_pass{ width:800px; border:#CCCCCC 2px solid; color:#CC3366; font-family:"Microsoft YaHei"; font-size:36px; font-weight:bold; margin:0 auto 50px auto; padding:15px 0; text-align:center; clear:both;}

.retrieve_password, .e-mail_back, .phone_back{ width:960px; margin:20px auto 20px; background:#fff; border:1px solid #e2e2e2; overflow:hidden; padding:20px;}
.retrieve_password h3, .e-mail_back h3, .phone_back h3{ font-size:20px; color:#16559F; height:20px; line-height:20px; border-bottom:2px solid #16559F; padding:5px; overflow:hidden; font-family:"Microsoft YaHei";}
.retrieve_password h3 a, .e-mail_back h3 a, .phone_back h3 a{ float:right; padding-right:10px; color:#666; font-size:12px; font-weight:100;}
#forgotEmail p.pad-mali,#forgotMobile p.pad-mali{margin: 0;padding: 0 0 0 80px;}
.service span{ color:#B6AFAF;}
.service span b{ display:block; font-size:18px;}

.padl80{ padding-left:80px;}
.colorred{color:#e80f0f;}
.chooseradio{ float:left}
.con_right{ border-left:1px solid #dcdcdc}

.chooseitem{ width:330px; line-height:24px; overflow:hidden;  margin:0;}
</style>
</head>

<body>
<section class="w1000">
	<div class="pathwray">
		<ol class="clearfix c-master f-fM fsize14">
			<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
			<li><span>提示信息</span></li>
		</ol>
	</div>
</section>
<!-- 内容区 -->
<!--start e-mail_back-->
	<div id="getmail" class="e-mail_back" style="display: ;">
		<h3>提示信息</h3>
		<div class="send_password">
			<div class="send_pass_txt">
				<div class="pic" ><img src="/static/edu/images/page/web/big_mail_bg.png"/><img src="/static/edu/images/page/web/big_mail_bg.png" /></div>
				<div class="cont">
					<p class="fg_p1">${msg}</p>
					<p class="mt20"><a class="commBtn04" href="${ctx }/" >返回首页</a></p></div>
			</div>
		</div>
	</div>
<!--stop e-mail_back-->
	

<!-- 内容区 //-->


</body>
</html>
