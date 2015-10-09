<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8">
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript"
	src="<%=imagesPath%>/static/edu/js/front/course/course_customer.js">
</script>
<title>用户意见反馈</title>
<script type="text/javascript">
	$(function() {
		$(".changesClass").keyup(function() {
			$(this).next('span').html('');
		});
		var exState = '${exState}';
		var pageNo = '${queryCustomCourseCondition.currentPage}';
		if (exState == '1') {
			$("#qitEmId").removeClass('c-c-down');
			$("#qitEmId").addClass('c-c-ud');
			$(".c-c-body").show();
		}
	});
	//提交反馈新行
	//必填的 是由jquery来控制的
	function sumbitFeed() {
		//验证文本是否输入
		var content = $("#content").val();
		if (content == "") {
			$(".contentErrorClass").html("请输入文本");
			return false;
		}	
		//验证手机号格式是否正确
		var mobile = $("#mobile").val();
		if (isNotEmpty(mobile)==false) {
			$("#message_mobile").html("请输入手机号码");
			return;
		}
 		var reg_mobile=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
		if (reg_mobile.test(mobile)==false) {
			$("#message_mobile").html("请输入正确的手机格式");
			return;
		}
		
		//验证邮箱格式是否正确
	    var email = $("#email").val();
		if (email == "") {
			$("#message_email").html("请输入邮箱");
			return;
		}
		var reg_email = /^\w+@\w+(\.\w+){1,3}$/;
		if (!reg_email.test(email) ) {
			$("#message_email").html("请输入正确的邮箱格式!");
			return;
		}
		
		//********

		var mobile = $("#mobile").val();
		var qq = $("#qq").val();
		var email = $("#email").val();
		var name = $("#name").val();
		var type = $("input[type='radio']:checked").val();
 		var img = 	$("#img").val();
		$.ajax({
			url : "${ctx}/front/addfreeback",
			data : {
				"userFeedback.content" : content,
				"userFeedback.mobile" : mobile,
				"userFeedback.qq" : qq,
				"userFeedback.email" : email,
				"userFeedback.name" : name,
				"userFeedback.type" : type,
				"userFeedback.img" : img
			},
			dataType : "json",
			type : "post",
			async : false,
			success : function(result) {
				if (result.success == true) {
					dialog('反馈信息已经添加成功', 13,"${ctx}/front/to_free_back");
				} else {
					dialog('系统繁忙，请稍后操作', 9);
				}
			}
		})
	}
//test3*******************************************************
$(function(){
	$("#fileupload").uploadify({
		'uploader' : '${ctximg}/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
		'script'  :'<%=uploadServerUrl%>/goswf',  
        'scriptData':{"base":"mavendemo","param":"userFeedback"},
		'queueID' : 'fileQueue', //文件队列ID
		'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
		'auto' : true, //选定文件后是否自动上传
		'multi' : false, //是否允许同时上传多文件
		'hideButton' : false,//上传按钮的隐藏
		'buttonText' : 'Browse',//默认按钮的名字
		'buttonImg' : '${ctximg}/static/common/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
		'width' : 105,
		'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
		'sizeLimit' : 51200000,//控制上传文件的大小
		'queueSizeLimit' : 2,//限制在一次队列中的次数（可选定几个文件）
		'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',//出现在上传对话框中的文件类型描述
		'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//支持的格式，启用本项时需同时声明fileDesc
		'folder' : '/upload',//您想将文件保存到的路径
		'cancelImg' : '${ctximg}/static/common/uploadify/cancel.png',
		onSelect : function(event, queueID,fileObj) {
			// $('#fileupload').uploadifyUpload();
			fileuploadIndex = 1;
			$("#fileQueue").html("");
			if (fileObj.size > 51200000) {
				alert("文件太大最大限制51200kb");
				return;
			}
		},
		onComplete : function(event,queueID, fileObj, response,data) {
			$("#imgUrl").attr("src","<%=staticImageServer%>" + response);
							$("#img").val(response);
							$("#imgUrl").show();
			},
			onError : function(event, queueID, fileObj,errorObj) {
				$("#fileQueue").html(
						"<br/><font color='red'>"
								+ fileObj.name + "上传失败</font>");
			}
	});
});
			
</script>
<style type="text/css">
.cssFrom {
	margin: -1px -25px 7px 4px;
}
</style>
</head>
<body>
	<!-- /用户反馈 -->
	<div class="custom-course-wrap">
		<section class="w1000">
			<div class="pathwray">
				<ol class="clearfix c-master f-fM fsize14">
					<li><a href="/" title="首页" class="c-master">首页</a> &gt;</li>
					<li><span>用户反馈</span></li>
				</ol>
			</div>
			<div class="of">
				<section class="mt20 mb20"></section>
				<section class="custom-course">
					<div>
						<div class="c-c-head of">
							<span class="fr c-c-up-down hand"></span>
							<h4 class="unFw">
								<em class="icon18 c-c-icon vam mr5">&nbsp;</em><font
									class="c-666 fsize14 vam">请您选择反馈分类 （<tt class="c-orange">*</tt>为必填项）
									<!-- 									type  start 设置type的  0意见1投诉 --> 
									<input id="opinion" type="radio" value="0" name="userFeedback.type" checked="checked">意见
									<input id="complaints" type="radio" value="1" name="userFeedback.type">投诉
									 <!-- 									type  end   -->
								</font>
							</h4>
						</div>
						<form action="${ctx}/front/addfreeback" id="freeBack"
							method="post">
<!-- 							hidden                                  -->
			<input name="userFeedback.img" id="img" type="hidden" />
<!-- 							hidden                                   -->
							<div class="c-c-body">
								<section class="clearfix">
									<div>
										<ul class="c-c-li">
											<li>
												<p>
													<span><font style="color: red">*</font>反馈信息:</span>
												</p> <textarea id="content" class="changesClass"
													name="userFeedback.content" style="width: 94%"></textarea>
												<span class="contentErrorClass dis mt5 c-red-1"></span>
											</li>
										</ul>
									</div>
								</section>
								<section class="c-c-b-tip">
									<span>反馈信息：我希望收到反馈消息的通知 </span>
								</section>
								<!-- 								img -->
								<section class="mt30 mb30">
									<div>
										<span class="dis fl mr20" style="line-height:30px;">
											反馈图片(400*300)
										</span>
										<span class="dis fl mr20">
											<input type="file" id="fileupload" class="vam" /> 
										</span>
									
										<div class="clear"></div>
									</div>
									<div class="ml10 mt10">
										<img src="" alt="" width="400" height="300"  style="display: none;" id="imgUrl" />
									</div>
									<div id="fileQueue" class="mt10"></div>
								</section>
								<!-- 								img -->
								<section class="mt30">
									<section class="clearfix">
										<div class="fl w50pre">
											<ul class="c-c-li">
												<li>
													<p>
														<span><font style="color: red">*</font>手机号码:</span>
													</p> <input type="text" class="changesClass" id="mobile"
													name="userFeedback.mobile" /> <span
													class="mobileErrorClass"></span>
		                            	           <p class="chooseitem padl80 colorred"><font id="message_mobile"></font></p><br>
													
												</li>
											</ul>
										</div>
										<div class="fr w50pre">
											<div class="ml20">
												<ul class="c-c-li">
													<li>
														<p>
															<span><font style="color: red">*</font>电子邮箱:</span>
														</p> <input class="changesClass" type="text" id="email"
														name="userFeedback.email" /> <span
														class="emailErrorClass"></span>
		                            	           <p class="chooseitem padl80 colorred"><font id="message_email"></font></p><br>
													</li>
												</ul>
											</div>
										</div>
										<!-- 										<div class="fl w50pre"> -->
										<!-- 											<ul class="c-c-li"> -->
										<!-- 												<li> -->
										<!-- 													<p> -->
										<!-- 														<span>QQ:</span> -->
										<!-- 													</p> <input type="text" class="changesClass" id="qq" -->
										<!-- 													name="userFeedback.qq" /> <span class="mobileErrorClass"></span> -->
										<!-- 												</li> -->
										<!-- 											</ul> -->
										<!-- 										</div> -->
										<!-- 										<div class="fr w50pre"> -->
										<!-- 											<div class="ml20"> -->
										<!-- 												<ul class="c-c-li"> -->
										<!-- 													<li> -->
										<!-- 														<p> -->
										<!-- 															<span>姓名:</span> -->
										<!-- 														</p> <input class="changesClass" type="text" id="name" -->
										<!-- 														name="userFeedback.name" /> <span class="emailErrorClass"></span> -->
										<!-- 													</li> -->
										<!-- 												</ul> -->
										<!-- 											</div> -->
										<!-- 										</div> -->
									</section>
								</section>
								<section class="mt20 tac">
									<!-- 									<input type="submit"> 提交按钮 点击后就会执行form表单中的action命令 -->
									<!-- 									<input type="reset"> 重置页面 -->
									<label><input type="button" onclick="sumbitFeed()"
										value="提交反馈" class="c-c-sub-btn"></label>
								</section>
							</div>
						</form>
					</div>
				</section>
			</div>
		</section>
		<div id="intro"></div>
	</div>
</body>
</html>
