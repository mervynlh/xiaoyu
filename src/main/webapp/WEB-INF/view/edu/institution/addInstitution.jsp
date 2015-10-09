<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>前台_添加机构</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jQueryValidate/jquery.validate.errorStyle.css?v=${v}"/>
<style type="text/css">
	div{background-repeat:no-repeat;}
</style>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/localization/messages_cn.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/common/jQueryValidate/lib/jquery.metadata.js?v=${v}" ></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/institution/institution.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="/static/common/uploadify/uploadify.css?v=${v}" />
<script type="text/javascript" src="/static/common/uploadify/swfobject.js?v=${v}"></script>
<script type="text/javascript" src="/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=${v}"></script>

<script type="text/javascript">
	// 图片上传
	function initSimpleImageUpload(btnId,valSet,urlId){
		$("#" + btnId).uploadify({
			'uploader' : '/static/common/uploadify/uploadify.swf',
			'script'  :uploadServerUrl + '/goswf',
			'scriptData':{"base":"mavendemo","param":"institution"},
			'queueID' : 'fileQueue',
			'fileDataName' : 'fileupload',
			'auto' : true,
			'multi' : false,
			'hideButton' : false,
			'simUploadLimit' : 3,
			'buttonImg' : imagesPath + '/static/common/uploadify/bgUpload.png',
			'width' : 200,
			'height' : 30,
			'sizeLimit' : 51200000,
			'queueSizeLimit' : 2,
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'cancelImg' : imagesPath + '/static/common/uploadify/uploadify-cancel.png',
			onSelect : function(event, queueID,fileObj) {
				//$('#fileupload').uploadifyUpload();
				fileuploadIndex = 1;
				$("#fileQueue").html("");
				if (fileObj.size > 51200000) {
					alert("文件太大,最大上传51200kb");
					return;
				}
			},
			onComplete : function(event,queueID, fileObj, response,data) {
				$("#" + valSet).attr("src", staticImageServer + response);
				$("#" + urlId).val(response);
				$("#" + valSet).show();
			}
		});
	}
</script>
</head>
<body>
	<section id="" class="container">
		<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<div class="u-t-mechan-bg u-t-mechan-bg-3"></div>
					<div class="u-t-intion-box" style="padding-left:120px;">
						<article class="intion-box-in step" style="display:block;">
							<div class="intion-box-in-tit mt50">
								<p class="hLh36">
									<tt class="fsize20 c-333 f-fM">1.</tt>
									<tt class="fsize16 c-333 f-fM">签订入驻协议</tt>
								</p>
							</div>
							<div class="intion-box-in-boy">
								<div class="mechan-onebox mt20">
									<div class="mechan-onebox-in">
										内容区
									</div>
								</div>
								<div class="mt10"><input type="checkbox" id="agreeArticle"  class="vam"/>&nbsp;&nbsp;<span class="f-fM c-666 vam fsize14">同意入驻协议</span></div>
								<div class="pt30">
									<ol class="password-body-boy">
										<li class="tac">
											<a href="javascript:nextStep()" class="c-btn bing-btn">
												下一步
											</a>
										</li>
									</ol>
								</div>
							</div>
						</article>		
						
						<!-- 机构图片url -->
						<input type="hidden" id="inst_url" />
						<!-- 营业执照url -->
						<input type="hidden" id="license_url" />
						<!-- 身份证图片url -->
						<input type="hidden" id="ID_url" />
						
						<article class="intion-box-in step" style="display:none;">
							<div class="intion-box-in-tit mt50">
								<p class="hLh36">
									<tt class="fsize20 c-333 f-fM">2.</tt>
									<tt class="fsize16 c-333 f-fM">提交资料</tt>
								</p>
							</div>
							<div class="intion-box-in-boy">
								<div class="i-b-i-b-l" id="select-1" style="width:100%;">
									<ol class="password-body-boy">
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">机构图标：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<%--<div class="sftp pr mt10" id="inst_pic">
													<div class="sftp-sc pa">
														<section class="uploadBtnWrap pr"> 
															<div class="ke-inline-block ">
																<div style="width: 150px;" class="ke-upload-area ke-form">
																	&lt;%&ndash;<span class="ke-button-common">
																		<input type="button" value="上传图片" class="ke-button-common ke-button">
																	</span>
																	<input type="file" tabindex="-1" id="inst_btn" class="ke-upload-file" />&ndash;%&gt;

																	<img alt="" src="" height="150" width="150" id="education_img" style="display: none">
																	<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:150px;">
																		<input type="file" id="inst_btn"/>
																	</a>

																</div>
															</div>
														</section>
													</div>
												</div>--%>
												<div class="">
													<div style="width:200px;height:200px;" class="sftp pr">
														<img alt="" src="" height="200" width="200" id="inst_pic" style="display: none">
														<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
															<input type="file" id="inst_btn"/>
														</a>
													</div>
												</div>
											</label>
											<span>
											</span>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">机构名称：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input type="text" name="" maxlength="" value="" class="input-1 fl" id="name">
												<em class="" id="nameError">&nbsp;<tt style="top:-9px;right:-102px;" class="fsize14 f-fM c-red-1 pa">请填写机构名称</tt>
												</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">机构简历：</tt>
											</span>
											<label class="pa-bo-boy-txt pr">
												<textarea id="description" maxlength="" name="" class="vam" placeholder="请输入机构简介！"></textarea>
												<tt style="top:40px;right:-78px;" class="fsize14 f-fM c-999 pa">300字以内</tt>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">营业执照：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<%--<div class="sftp pr mt10" id="license_pic">
													<div class="sftp-sc pa">
														<section class="uploadBtnWrap pr"> 
															<div class="ke-inline-block ">
																<div style="width: 150px;" class="ke-upload-area ke-form">
																	<span class="ke-button-common">
																		<input type="button" value="上传图片" class="ke-button-common ke-button">
																	</span>
																	<input type="file" tabindex="-1" id="license_btn" class="ke-upload-file" />
																</div>
															</div>
														</section>
													</div>
												</div>--%>
												<div class="">
													<div style="width:200px;height:200px;" class="sftp pr">
														<img alt="" src="" height="200" width="200" id="license_pic" style="display: none">
														<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
															<input type="file" id="license_btn"/>
														</a>
													</div>
												</div>
											</label>
											<span>
												
											</span>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">申请人：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input type="text" name="" maxlength="" value="" class="input-1 fl" id="applicant">
												<em class="" id="applicantError">&nbsp;<tt style="top:-9px;right:-102px;" class="fsize14 f-fM c-red-1 pa">请填写正确名字</tt>
												</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">身份证：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<%--<div class="sftp pr mt10" id="ID_pic">
													<div class="sftp-sc pa">
														<section class="uploadBtnWrap pr"> 
															<div class="ke-inline-block ">
																<div style="width: 150px;" class="ke-upload-area ke-form">
																	<span class="ke-button-common">
																		<input type="button" value="上传图片" class="ke-button-common ke-button">
																	</span>
																	<input type="file" tabindex="-1" id="ID_btn" class="ke-upload-file" />
																</div>
															</div>
														</section>
													</div>
												</div>--%>
												<div class="">
													<div style="width:200px;height:200px;" class="sftp pr">
														<img alt="" src="" height="200" width="200" id="ID_pic" style="display: none">
														<a href="javascript:void(0)" class="pa sctp-btn dis" style="width:200px;">
															<input type="file" id="ID_btn"/>
														</a>
													</div>
												</div>
											</label>
											<span class="dis fl ml30 pt40">
												<p class="fsize12 f-fM c-org mt10">请上传手持身份证的半身照</p>
												<p class="fsize12 f-fM c-org mt10">文件支持小于2M</p>
												<p class="fsize12 f-fM c-org mt10">支持JPG/PNG/BMP等格式照片</p>
											</span>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">手机号：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input type="text" name="" maxlength="11" value="" class="input-1 fl" id="mobile" placeholder="登录账户">
												<em class="" id="mobileError">&nbsp;<tt style="top:-9px;right:-102px;" class="fsize14 f-fM c-red-1 pa">请填写正确手机</tt>
												</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">企业邮箱：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input type="text" name="" maxlength="" value="" class="input-1 fl" id="email" >
												<em class="" id="emailError">&nbsp;<tt style="top:-9px;right:-102px;" class="fsize14 f-fM c-red-1 pa">请填写正确邮箱</tt>
												</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">输入密码：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input type="password" name="password" maxlength="16" value="" class="input-1 fl" id="password">
												<em class="" id="passwordError">&nbsp;<tt style="top:-9px;right:-102px;" class="fsize14 f-fM c-red-1 pa">请填写正确密码</tt>
												</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>
										<li>
											<span class="vam pa-bo-boy-tit">
												<tt class="fisze20 c-org f-fM vam">*</tt>
												<tt class="fsize14 c-666 f-fM vam">确认密码：</tt>
											</span>
											<label class="pa-bo-boy-txt">
												<input type="password" name="rpassword" maxlength="16" value="" class="input-1 fl" id="rpassword">
												<em class="" id="rpasswordError">&nbsp;<tt style="top:-9px;right:-102px;" class="fsize14 f-fM c-red-1 pa">请填写正确密码</tt>
												</em>
												<div class="clear"></div>
											</label>
											<div class="clear"></div>
										</li>										
									</ol>
								</div>
								<div class="pt30">
									<ol class="password-body-boy">
										<li class="tac">
											<a href="javascript:addInstitution()" class="c-btn bing-btn">
												提交
											</a>
										</li>
									</ol>
								</div>
							</div>
						</article>
						<article class="intion-box-in step" style="display:none;">
							<div class="intion-box-in-boy mt50">
								<div class="pt30 pb50">
									<ol class="password-body-boy">
										<li class="tac">
											<div class="mec-cg">
												<em class="disIb mr20 vam">
													<img src="${ctx }/static/edu/img/jgcg-bg.png" alt="">
												</em>
												<tt class="fsize20 c-666 f-fM vam">
													您的机构已提交成功，请等待审核！
												</tt>
											</div>
										</li>
									</ol>
								</div>
							</div>
						</article>
					</div>
				</section>   
			</article>
		</div>
	</section>
</body>
</html>