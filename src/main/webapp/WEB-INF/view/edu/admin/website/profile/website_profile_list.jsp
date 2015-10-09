<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站管理</title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
var type='';
$(document).ready(function() {
	type='${type}';
	$("#"+type).attr("href","javascript:void(0)");
	if(type=='logo'){
		initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
	}
	});
	function initSimpleImageUpload(btnId,urlId,valSet){
		KindEditor.ready(function(K) {
			var uploadbutton = K.uploadbutton({
				button : K('#'+btnId+'')[0],
				fieldName : "fileupload",
				url : '<%=uploadSimpleUrl%>&param=websiteLogo',
				afterUpload : function(data) {
					if (data.error === 0) {
						var url = K.formatUrl(data.url, 'absolute');//absolute,domain
						K('#'+urlId+'').attr("src",'<%=staticImageServer%>'+data.url);
						$("#"+urlId).show();
						$('#'+valSet+'').val(url);
					} else {
						alert(data.message);
					}
				},
				afterError : function(str) {
					alert('自定义错误信息: ' + str);
				}
			});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
		});
	}
	function submit(){
		if(type!=''){
			if(type=='logo'){
				if($("#imagesUrl").val()==''){
					alert("请上传logo");
					return ;
				}
				$("#searchForm").attr("action","${ctximg}/admin/websiteProfile/update");
			}else if(type=='ico'){
				var icoFile=$("#icoFile").val();
				if(icoFile!=null&&icoFile!=''){
					var fileNames=icoFile.split('.');
			  		var fileName=fileNames[0];
			  		var fileNameSuffix=fileNames[1];
			  		if(!(fileNameSuffix == "ico" || fileNameSuffix == "ICO")) {
			  			alert("请选择ico格式的图片!");
			  			return;
			  		}else if(fileName.indexOf('favicon')==-1)
			  		{
			  			alert("文件必须命名为favicon");
			  			return;
			  		}else{
			  			//$("#searchForm").attr("enctype","multipart/form-data");
			  			$("#icoForm").attr("action","${ctx}/admin/websiteProfile/uploadIco");
			  			$("#icoForm").submit();
			  			return;
			  		}
				}else{
					alert("请上传ioc文件");
					return;
				}
			}else{
				$("#searchForm").attr("action","${ctx}/admin/websiteProfile/find/"+type+"");
			}
			$("#searchForm").submit();
		}
	}
</script>
</head>
<body  >
	<div class="page_head">
		<h4>
			<em class="icon14 i_01"></em> <span>网站管理</span>
		</h4>
	</div>
		<div class="mt20">
			<div class="commonWrap">
				<table class="commonTab01" width="100%" cellspacing="0"
					cellpadding="0" border="0">
					<caption>
						<div class="capHead">
							<div class="clearfix">
									<div class="optionList">
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/web" id="web"><span><font>基本信息</font></span></a>
										&nbsp;&nbsp;&nbsp;
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/alipay" id="alipay"><span><font>支付宝</font></span></a>
										<%-- &nbsp;&nbsp;&nbsp;
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/logo" id="logo"><span><font>logo</font></span></a> --%>
										<%-- &nbsp;&nbsp;&nbsp;
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/ico" id="ico"><span><font>ico</font></span></a> --%>
										&nbsp;&nbsp;&nbsp;
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/polyv" id="polyv"><span><font>保利威视</font></span></a>
										&nbsp;&nbsp;&nbsp;
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/professorAttestation" id="professorAttestation"><span><font>认证管理邮箱</font></span></a>
										&nbsp;&nbsp;&nbsp;
										<a class="btn smallbtn btn-y" href="${ctx}/admin/websiteProfile/find/censusCode" id="censusCode"><span><font>统计代码</font></span></a>
									</div>
							</div>
						</div>
						<%-- <div class="clearfix"></div>
						<div class="mt10 clearfix">
								<p class="fl czBtn">
								<span><a href="${ctx}/admin/websitemem/toAdd" title="添加"><em class="icon14 new">&nbsp;</em>添加</a></span>
								</p>
							</div>  --%>
					</caption>
					<thead>
						<tr>
							<th width="30%"><span>名称</span></th>
							<th width="70%"><span>描述</span></th>
						</tr>
					</thead>
					<c:if test="${type=='web' }">
					<tbody id="tabS_web" align="center">
							<tr>
								<td>网站title(网站头部)</td>
								<td>${webSiteMap.web.title}</td>
							</tr>
							<tr>
								<td>网校名称(网站头部)</td>
								<td>${webSiteMap.web.company}</td>
							</tr>
							<tr>
								<td>网站作者</td>
								<td>${webSiteMap.web.author}</td>
							</tr>
							<tr>
								<td>关键词</td>
								<td>${webSiteMap.web.keywords}</td>
							</tr>
							<tr>
								<td>描述</td>
								<td>${webSiteMap.web.description}</td>
							</tr>
							<tr>
								<td>联系邮箱(网站底部)</td>
								<td>${webSiteMap.web.email}</td>
							</tr>
							<tr>
								<td>联系电话(网站底部)</td>
								<td>${webSiteMap.web.phone}</td>
							</tr>
							<tr>
								<td>工作时间(网站底部)</td>
								<td>${webSiteMap.web.workTime}</td>
							</tr>
							<tr>
								<td>版权以及备案号(网站底部)</td>
								<td>${webSiteMap.web.copyright}</td>
							</tr>
					</tbody>
					</c:if>
					<c:if test="${type=='yee'}">
					<tbody id="tabS_alipay" align="center">
							<tr>
								<td>商户编号（p1_MerId）</td>
								<td>${webSiteMap.yee.p1_MerId}</td>
							</tr>
							<tr>
								<td>商户密钥（keyValue）</td>
								<td>${webSiteMap.yee.keyValue}</td>
							</tr>
					</tbody>
					</c:if>
					
					
					<c:if test="${type=='alipay'}">
					<tbody id="tabS_alipay" align="center">
							<tr>
								<td>合作者身份（PID）</td>
								<td>${webSiteMap.alipay.alipaypartnerID}</td>
							</tr>
							<tr>
								<td>安全校验码（key）</td>
								<td>${webSiteMap.alipay.alipaykey}</td>
							</tr>
							<tr>
								<td>商家邮箱</td>
								<td>${webSiteMap.alipay.sellerEmail}</td>
							</tr>
							<tr>
								<td>支付宝选择(企业或个人，个人支付宝需定制)</td>
								<c:if test="${webSiteMap.alipay.status==0}">
								<td>企业支付宝</td>
								</c:if>
								<c:if test="${webSiteMap.alipay.status==1}">
								<td>个人支付宝</td>
								</c:if>
							</tr>
					</tbody>
					</c:if>	
					<%-- <c:if test="${type=='email' }">
					<tbody id="tabS_email" align="center">
							<tr>
								<td>邮箱Host</td>
								<td>${webSiteMap.email.emailHost}</td>
							</tr>
							<tr>
								<td>邮箱名称</td>
								<td>${webSiteMap.email.emailUsername}</td>
							</tr>
							<tr>
								<td>邮箱密码</td>
								<td>${webSiteMap.email.emailPassword}</td>
							</tr>
					</tbody>
					</c:if> --%>
					<c:if test="${type=='keyword'}">
					<tbody id="tabS_keyword" align="center">
							<%--<tr>
								<td>邮箱验证</td>
								<td>${webSiteMap.keyword.verifyEmail}</td>
							</tr>
							<tr>
								<td>快钱支付开关</td>
								<td>${webSiteMap.keyword.verifykq}</td>
							</tr>
							<tr>
								<td>易宝支付开关</td>
								<td>${webSiteMap.keyword.yee}</td>
							</tr>
							<tr>
								<td>微信支付开关</td>
								<td>${webSiteMap.keyword.verifywx}</td>
							</tr>
							<tr>
								<td>登录开关</td>
								<td>${webSiteMap.keyword.verifyLogin}</td>
							</tr>
							<tr>
								<td>注册开关</td>
								<td>${webSiteMap.keyword.verifyRegister}</td>
							</tr>
							<tr>--%>
								<td>考试开关</td>
								<td>${webSiteMap.keyword.verifyExam}</td>
							</tr>
							<tr>
								<td>SNS开关</td>
								<td>${webSiteMap.keyword.verifySns}</td>
							</tr>
					</tbody>
					</c:if>
					<form action="?" id="searchForm" method="post">
					<input type="hidden" name="flag" id="flag" value="flag"/>
					<input type="hidden" name="type" id="type" value="${type}"/>
					<c:if test="${type=='logo'}">
					<tbody id="tabS_logo" align="center">
					<tr>
					<td>图片地址</td>
					<td><input type="text" name="url" id="imagesUrl" value="${webSiteMap.logo.url}" style=" width: 485px; "/><font color="red">*LOGO链接,可直接修改</font></td>
					</tr>
							<tr>
								<td>设置新logo</td>
								<td>
								<img alt="" src="<%=staticImageServer %>${webSiteMap.logo.url}" id="subjcetpic" width="144px" height="90px"/>
								<input type="button" id="fileuploadButton" value="上传"/>
								<font color="red">*LOGO链接，支持JPG、PNG格式</font>
								</td>
							</tr>
					</tbody>
					</c:if>
					</form>
					<c:if test="${type=='cc' }">
					<tbody id="tabS_cc" align="center">
							<tr>
								<td>CC appID</td>
								<td>${webSiteMap.cc.ccappID}</td>
							</tr>
							<tr>
								<td>CC appKEY</td>
								<td>${webSiteMap.cc.ccappKEY}</td>
							</tr>
					</tbody>
					</c:if>
					<c:if test="${type=='p56' }">
					<tbody id="tabS_p56" align="center">
							<tr>
								<td>56 appId</td>
								<td>${webSiteMap.p56.p56appID}</td>
							</tr>
							<tr>
								<td>56 appKEY</td>
								<td>${webSiteMap.p56.p56appKEY}</td>
							</tr>
					</tbody>
					</c:if>
					<c:if test="${type=='letv' }">
					<tbody id="tabS_p56" align="center">
							<tr>
								<td>用户唯一标识码</td>
								<td>${webSiteMap.letv.user_unique}</td>
							</tr>
							<tr>
								<td>秘钥</td>
								<td>${webSiteMap.letv.secret_key}</td>
							</tr>
					</tbody>
					</c:if>
					<c:if test="${type=='polyv'}">
						<tbody id="tabS_polyv" align="center">
						<tr>
							<td>writetoken：</td>
							<td>${webSiteMap.polyv.writetoken}</td>
						</tr>
						</tbody>
					</c:if>

					<c:if test="${type=='professorAttestation'}">
						<tbody id="tabS_professorAttestation" align="center">
						<tr>
							<td>韩教授认证管理者邮箱</td>
							<td>${webSiteMap.professorAttestation.email}</td>
						</tr>
						<tr>
							<td>韩教授认证管理者手机</td>
							<td>${webSiteMap.professorAttestation.mobile}</td>
						</tr>
						</tbody>
					</c:if>

					<c:if test="${type=='censusCode' }">
					<tbody id="tabS_censusCode" align="center">
							<tr>
								<td>统计代码</td>
								<td><textarea rows="6" cols="60" disabled="disabled">${webSiteMap.censusCode.censusCodeString}</textarea> </td>
							</tr>
					</tbody>
					</c:if>
					<form action="?" method="post" enctype="multipart/form-data" id="icoForm">
					<c:if test="${type=='ico' }">
					
					<tbody id="tabS_censusCode" align="center">
							<tr>
								<td>ico文件</td>
								<td><img alt="" src="${ctx}/favicon.ico"></td>
							</tr>
							<tr>
								<td>设置新的ico文件</td>
								<td><font color="red">（请美工制作图片的大小标为32*32的ico图片,否则图片会失真）</font><input type="file" name="icoFile" id="icoFile"/></td>
							</tr>
					</tbody>
					</c:if>	
					</form>																			
					<tr>
					<td colspan="2" align="center">
					<a class="btn btn-danger" title="提 交" href="javascript:submit()">修改</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
					</td>
					</tr>  
					</table>
				<!-- /pageBar begin -->
					<%-- 	<jsp:include page="/WEB-INF/view/common/admin_page.jsp" /> --%>
				<!-- /pageBar end -->
			</div>
		</div>
</body>
</html>