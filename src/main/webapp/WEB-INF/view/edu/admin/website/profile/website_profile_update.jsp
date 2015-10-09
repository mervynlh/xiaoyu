<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>网站配置管理</title>
<script type="text/javascript">
function submit(){
	if ($("#type").val() == 'professorAttestation'){
		// 验证邮箱格式
		var email = $("#professorEmail").val();
		if(email == null || email.trim() == ''){
			alert("请输入邮箱号");
			return;
		}
		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
		if(reg.test(email) == false){//格式不正确
			alert("请输入正确的邮箱号");
			return;
		};
		// 验证手机
		var phone = $("#professorMobile").val();
		if(phone == null || phone.trim() == ''){
			alert("请输入手机号");
			return;
		}
		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
		if(reg.test(phone)==false){//格式不正确
			alert("请输入正确的手机号");
			return;
		};
	}
	$("#addprofileForm").submit();
};
$(function(){
	$("#verify_tbody tr:lt(9)").hide();
});
</script> 
</head>
<body >
<form action="${ctx }/admin/websiteProfile/update" method="post" id="addprofileForm">
<input type="hidden" name="type" value="${type}" id="type"/>
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>网站配置管理</span> &gt; <span>添加网站配置</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>修改网站配置<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<c:if test="${type=='web' }">
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;网站title(网站头部)</td>
				<td>
					<input type="text" name="title" value="${webSiteMap.web.title}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;网校名称(网站头部)</td>
				<td>
					<input type="text" name="company"  value="${webSiteMap.web.company}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;网站作者</td>
				<td>
					<input type="text" name="author"  value="${webSiteMap.web.author}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;关键词</td>
				<td>
					<input type="text" name="keywords"  value="${webSiteMap.web.keywords}" style="width: 75%"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;描述</td>
				<td>
					<input type="text" name="description" value="${webSiteMap.web.description}" style="width: 75%"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;联系邮箱</td>
				<td>
					<input type="text" name="email"  value="${webSiteMap.web.email}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;24小时客服服务热线</td>
				<td>
					<input type="text" name="phone"  value="${webSiteMap.web.phone}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;工作时间</td>
				<td>
					<input type="text" name="workTime" value="${webSiteMap.web.workTime}"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;版权以及备案号(网站底部)</td>
				<td>
					<input type="text" name="copyright" value="${webSiteMap.web.copyright}" style="width: 75%"/>
				</td>
			</tr>
		</tbody>
		</c:if>
		<c:if test="${type=='alipay' }">
		<tbody>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;合作者身份（PID）</td>
			<td>
				<input type="text" name="alipaypartnerID" value="${webSiteMap.alipay.alipaypartnerID}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;安全校验码（key）</td>
			<td>
				<input type="text" name="alipaykey"  value="${webSiteMap.alipay.alipaykey}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;商家邮箱</td>
			<td>
				<input type="text" name="sellerEmail"  value="${webSiteMap.alipay.sellerEmail}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;支付宝选择(企业或个人，个人支付宝需定制)</td>
			<td>
				<input type="radio" name="status" value="0" <c:if test="${webSiteMap.alipay.status==0}">checked='checked'</c:if> />企业
				<input type="radio" name="status" value="1" <c:if test="${webSiteMap.alipay.status==1}">checked='checked'</c:if>/>个人
			</td>
		</tr>
		</tbody>
		</c:if>
		
		<c:if test="${type=='yee' }">
		<tbody>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;商户编号（p1_MerId）</td>
			<td>
				<input type="text" name="p1_MerId" value="${webSiteMap.yee.p1_MerId}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;商户密钥（keyValue）</td>
			<td>
				<input type="text" name="keyValue"  value="${webSiteMap.yee.keyValue}" style="width: 35%"/>
			</td>
		</tr>
		</tbody>
		</c:if>
		
		<c:if test="${type=='keyword'}">
		<tbody id="verify_tbody">
		
		<tr style="display: none;">
		
			<td align="center"><font color="red">*</font>&nbsp;手机验证：</td>
			<td>
				<select name="verifyPhone">
		    		<c:if test="${webSiteMap.keyword.verifyPhone=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifyPhone=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
			
		</tr>
		
		<tr style="display: none;">
			<td align="center"><font color="red">*</font>&nbsp;邮箱验证</td>
			<td>
				<select name="verifyEmail">
		    		<c:if test="${webSiteMap.keyword.verifyEmail=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifyEmail=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		<tr style="display: none;">
			<td align="center"><font color="red">*</font>&nbsp;开启敏感词过滤：</td>
			<td>
				<select name="verifySensitive">
		    		<c:if test="${webSiteMap.keyword.verifySensitive=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifySensitive=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		<tr style="display: none;">
			<td align="center"><font color="red">*</font>&nbsp;支付宝开关：</td>
			<td>
	    		<select name="verifyAlipay">
		    		<c:if test="${webSiteMap.keyword.verifyAlipay=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${websitemap.keyword.verifyAlipay=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;快钱支付开关：</td>
			<td>
				<select name="verifykq">
		    		<c:if test="${webSiteMap.keyword.verifykq=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifykq=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		
		
			<tr>
			<td align="center"><font color="red">*</font>&nbsp;易宝开关：</td>
			<td>
				<select name="yee">
		    		<c:if test="${webSiteMap.keyword.yee=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.yee=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;微信支付开关：</td>
			<td>
				<select name="verifywx">
		    		<c:if test="${webSiteMap.keyword.verifywx=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifywx=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;登录开关：</td>
			<td>
				<select name="verifyLogin">
		    		<c:if test="${webSiteMap.keyword.verifyLogin=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifyLogin=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;注册开关：</td>
			<td>
	    		<select name="verifyRegister">
		    		<c:if test="${webSiteMap.keyword.verifyRegister=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifyRegister=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>  
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;考试开关：</td>
			<td>
	    		<select name="verifyExam">
		    		<c:if test="${webSiteMap.keyword.verifyExam=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifyExam=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;SNS开关：</td>
			<td>
	    		<select name="verifySns">
		    		<c:if test="${webSiteMap.keyword.verifySns=='ON'}">
		    		<option value="ON">ON</option>
		    		<option value="OFF">OFF</option>
		    		</c:if>
		    		<c:if test="${webSiteMap.keyword.verifySns=='OFF'}">
		    		<option value="OFF">OFF</option>
		    		<option value="ON">ON</option>
		    		</c:if>
		    		</select>
			</td>
		</tr>
		</tbody>
		</c:if>
		<c:if test="${type=='cc'}">
		<tbody>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;CC appID</td>
			<td>
				<input type="text" name="ccappID" value="${webSiteMap.cc.ccappID}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;CC appKEY</td>
			<td>
				<input type="text" name="ccappKEY"  value="${webSiteMap.cc.ccappKEY}" style="width: 35%"/>
			</td>
		</tr>
		</tbody>
		</c:if>
		<c:if test="${type=='p56'}">
		<tbody>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;56 appId</td>
			<td>
				<input type="text" name="p56appID" value="${webSiteMap.p56.p56appID}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;56 appKEY</td>
			<td>
				<input type="text" name="p56appKEY"  value="${webSiteMap.p56.p56appKEY}" style="width: 35%"/>
			</td>
		</tr>
		</tbody>
		</c:if>
		<c:if test="${type=='letv'}">
		<tbody>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;用户唯一标识码</td>
			<td>
				<input type="text" name="user_unique" value="${webSiteMap.letv.user_unique}" style="width: 35%"/>
			</td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;56 appKEY</td>
			<td>
				<input type="text" name="secret_key"  value="${webSiteMap.letv.secret_key}" style="width: 35%"/>
			</td>
		</tr>
		</tbody>
		</c:if>

		<c:if test="${type=='polyv' }">
			<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;writetoken：</td>
				<td>
					<input type="text" name="writetoken" value="${webSiteMap.polyv.writetoken}" style="width: 35%"/>
				</td>
			</tr>
			</tbody>
		</c:if>

		<c:if test="${type=='professorAttestation' }">
			<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;韩教授认证管理者邮箱：</td>
				<td>
					<input type="text" name="email" id="professorEmail" value="${webSiteMap.professorAttestation.email}" style="width: 35%"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;韩教授认证管理者手机：</td>
				<td>
					<input type="text" name="mobile" id="professorMobile" value="${webSiteMap.professorAttestation.mobile}" style="width: 35%"/>
				</td>
			</tr>
			</tbody>
		</c:if>

		<c:if test="${type=='censusCode'}">
		<tbody>
		<tr>
			<td align="center"><font color="red">*</font>&nbsp;统计代码</td>
			<td>
				<textarea rows="6" cols="60"  name="censusCodeString">${webSiteMap.censusCode.censusCodeString}</textarea>
			</td>
		</tr>
		</tbody>
		</c:if>
		<tr>
				<td align="center" colspan="2">
					<a class="btn btn-danger" title="提 交" href="javascript:submit()">提 交</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
				</td>
			</tr>
	</table>
</div>
<!-- /tab4 end -->
</form>
</body>
</html>
