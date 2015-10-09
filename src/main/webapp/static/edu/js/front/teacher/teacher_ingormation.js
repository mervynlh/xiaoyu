// 选择学历
function checkdegree(degreeId){
	$("#hiddendegree").val(degreeId);
}

// 下一步点击事件
function onclickNext(step){
	if (step == 2){
		if(baseInfoSetting() != false){
			$("#teacher_info_1").hide();
			$("#teacher_info_3").hide();
			$("#teacher_info_2").show();
			$(".u-t-inws-bg").removeClass("u-t-inws-bg-1").addClass("u-t-inws-bg-2");
		}
	} else if (step == 3){
		if (seniorInfoSetting() != false){
			$("#teacher_info_1").hide();
			$("#teacher_info_3").show();
			$("#teacher_info_2").hide();
			$(".u-t-inws-bg").removeClass("u-t-inws-bg-2").addClass("u-t-inws-bg-3");
		}
	} else if (step == 4){
		if (aptitudeAttestation() != false){
			$(".u-t-inws-bg").removeClass("u-t-inws-bg-3").addClass("u-t-inws-bg-4");
			perfectTeacherInfo();
		}
	}
}

// 教师资料完善
function perfectTeacherInfo(){
	$.ajax({
		url : baselocation + "/front/ajax/teacher/info/perfect",
		type : "post",
		data : {
			"teacher.userExpand.realname" : $("#realname").val(),
			"teacher.userExpand.nickname" : $("#user_nickname").val(),
			"teacher.userExpand.avatar" : $("#imagesUrl").val(),
			"teacher.userExpand.birthday" : $("#birthday").val(),

			"teacher.id" : $("#teacherId").val(),
			"teacher.userId" : $("#teacherUserId").val(),
			"teacher.sex" : $('input[name="sex"]:checked').val(),
			"teacher.degree" : $("#hiddendegree").val(),
			"teacher.finishSchool" : $("#finishSchool").val(),
			"teacher.profession" : $("#profession").val(),
			"teacher.teacherSubject" : $("#teacherSubject").val(),
			"teacher.teacherMajor" : $("#teacherMajor").val(),

			"teacher.seniority" : $("#seniority").val(),
			"teacher.peculiarity" : $("#peculiarity").val(),
			"teacher.teachingLive" : $("#teachingLive").val(),
			"teacher.teachingSuccess" : $("#teachingSuccess").val(),
			"teacher.teachingEnounce" : $("#teachingEnounce").val(),

			"teacher.card" : $("#card_imagesUrl").val(),
			"teacher.education" : $("#education_imagesUrl").val(),
			"teacher.teaching" : $("#teaching_imagesUrl").val(),
			"teacher.specialty" : $("#specialty_imagesUrl").val()
		},
		dataType : "json",
		success : function(result) {
			if(result.success == true){
				dialog("资料完善成功，请耐心等待客服审核！", 13, "", baselocation + "/uc/home");
			} else if (result.message == 'realnameIsNull'){
				dialog("真实姓名不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'avatarIsNull'){
				dialog("请选择头像上传", 10, '', '');
				window.location.reload();
			} else if (result.message == 'degreeIsNull'){
				dialog("学历不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'finishSchoolIsNull'){
				dialog("毕业院校不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'professionIsNull'){
				dialog("专业不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'birthdayIsNull'){
				dialog("出生日期不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'subjectIsNull'){
				dialog("请选择您的教学年级", 10, '', '');
				window.location.reload();
			} else if (result.message == 'majorIsNull'){
				dialog("请选择您的教学科目", 10, '', '');
				window.location.reload();
			} else if (result.message == 'seniorityIsNull'){
				dialog("请输入您的教龄", 10, '', '');
				window.location.reload();
			} else if (result.message == 'peculiarityIsNull'){
				dialog("教学特点不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'teachingEnounceIsNull'){
				dialog("教学宣言不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'teachingLiveIsNull'){
				dialog("工作/学习经历不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'teachingSuccessIsNull'){
				dialog("成功经历不能为空", 10, '', '');
				window.location.reload();
			} else if (result.message == 'cardIsNull'){
				dialog("请上传身份证图片", 10, '', '');
				window.location.reload();
			} else if (result.message == 'educationIsNull'){
				dialog("请上传学历证明图片", 10, '', '');
				window.location.reload();
			} else if (result.message == 'teachingIsNull'){
				dialog("请上传教师证图片", 10, '', '');
				window.location.reload();
			} else if (result.message == 'specialtyIsNull'){
				dialog("请上传专业资质证图片", 10, '', '');
				window.location.reload();
			} else if (result.message == 'error'){
				dialog("系统错误，请联系客服", 10, "", "");
			}
		}
	});
}

// 基本资料修改
function baseInfoSetting(){
	// 真实姓名
	var realname = $("#realname").val();
	if(realname == null || $.trim(realname) == ''){
		$("#realnameError").text("请输入您的姓名");
		$("#realnameError").parent().addClass("Wrong");
		return false;
	}else{
		$("#realnameError").parent().addClass("Correct");
	}
	// 学历
	var degree = $("#hiddendegree").val();
	if(degree == 0) {
		$("#degreeError").text("请选择您的学历");
		$("#degreeError").parent().addClass("Wrong");
		return false;
	}else{
		$("#degreeError").parent().addClass("Correct");
	}
	// 毕业院校
	var finishSchool = $("#finishSchool").val();
	if(finishSchool == null || $.trim(finishSchool) == ''){
		$("#finishSchoolError").text("请输入您的毕业院校");
		$("#finishSchoolError").parent().addClass("Wrong");
		return false;
	}else{
		$("#finishSchoolError").parent().addClass("Correct");
	}
	// 专业
	var profession = $("#profession").val();
	if(profession == null || $.trim(profession) == ''){
		$("#professionError").text("请输入您的专业");
		$("#professionError").parent().addClass("Wrong");
		return false;
	}else{
		$("#professionError").parent().addClass("Correct");
	}
	// 生日
	var birthday = $("#birthday").val();
	if(birthday == null || $.trim(birthday) == ''){
		$("#birthdayError").text("请选择您的出生日期");
		$("#birthdayError").parent().addClass("Wrong");
		return false;
	}else{
		$("#birthdayError").parent().addClass("Correct");
	}
	// 头像
	var avatar = $("#imagesUrl").val();
	if(avatar == null || avatar == ''){
		dialog("请上传头像", 9, "", "");
		return false;
	}
	// 验证教授阶段方式
	var teacherSubject = document.getElementsByName("grade");
	var typenum = 0;
	var subjectIds = '';
	for (var i = 0; i < teacherSubject.length; i++) {
		if (teacherSubject[i].checked == true) {
			typenum++;
			subjectIds += teacherSubject[i].value + ",";
		}
	}
	//没有被选择
	if(typenum <= 0){
		dialog("请选择您教授的年级", 9, "", "");
		return false;
	} else {
		$("#teacherSubject").val(subjectIds);
	}
	// 验证科目
	var teacherMajor = document.getElementsByName("major");
	var typenum = 0;
	var majorIds = '';
	for (var i = 0; i < teacherMajor.length; i++) {
		if (teacherMajor[i].checked == true) {
			typenum++;
			majorIds += teacherMajor[i].value + ",";
		}
	}
	//没有选择
	if(typenum <= 0){
		dialog("请选择您教授的科目", 9, "", "");
		return false;
	} else {
		$("#teacherMajor").val(majorIds);
	}
}

// 教师高级资料修改
function seniorInfoSetting(){
	// 教龄
	var seniority = $("#seniority").val();
	if ($.trim(seniority) == ''){
		$("#seniorityError").text("请输入您的教龄");
		$("#seniorityError").parent().addClass("Wrong");
		return false;
	} else if (isNaN(seniority)) {
		$("#seniorityError").text("只能输入数字");
		$("#seniorityError").parent().addClass("Wrong");
		return false;
	} else {
		$("#seniorityError").parent().addClass("Correct");
	}
	// 教学特点
	var peculiarity = $("#peculiarity").val();
	if(peculiarity == null || $.trim(peculiarity) == ''){
		dialog("请输入您的教学特点", 9, "", "");
		return false;
	}
	// 教学宣言
	var teachingEnounce = $("#teachingEnounce").val();
	if(teachingEnounce == null || $.trim(teachingEnounce) == ''){
		dialog("请输入您的教学宣言", 9, "", "");
		return false;
	}
	// 工作/学习经历
	var teachingLive = $("#teachingLive").val();
	if(teachingLive == null || $.trim(teachingLive) == ''){
		dialog("请输入您的工作/学习经历", 9, "", "");
		return false;
	}
	// 成功经历
	var teachingSuccess = $("#teachingSuccess").val();
	if(teachingSuccess == null || $.trim(teachingSuccess) == ''){
		dialog("请输入您教学成功经历", 9, "", "");
		return false;
	}
}

// 教师资质认证
function aptitudeAttestation(){
	// 身份证图片
	var card = $("#card_imagesUrl").val();
	if(card == null || $.trim(card) == ''){
		dialog("请上传身份证图片", 9, "", "");
		return false;
	}
	// 学历证图片
	var education = $("#education_imagesUrl").val();
	if(education == null || $.trim(education) == ''){
		dialog("请上传学历证图片", 9, "", "");
		return false;
	}
	// 教师证图片
	var teaching = $("#teaching_imagesUrl").val();
	if(teaching == null || $.trim(teaching) == ''){
		dialog("请上传教师证图片", 9, "", "");
		return false;
	}
	// 专业资质证图片
	var specialty = $("#specialty_imagesUrl").val();
	if(specialty == null || $.trim(specialty) == ''){
		dialog("请上传专业资质证图片", 9, "", "");
		return false;
	}
}

// 模拟 select 下拉控件
function selFun(op) {
	var _sel = $(op).find(".selectUI"),
		_timer = null;
	_sel.each(function() {
		var _this = $(this),
			_selU = _this.children(".job-select"),
			_opt = _this.find(".j-s-option"),
			_sTxt = _selU.find(".j-s-defalt").find("span"),
			_oTxt = _opt.find(".j-s-o-box").find("p");
		_this.hover(function() {
			if (_opt.is(":hidden")) {
				_selU.addClass("selected");
				_timer = setInterval(function() {
					_opt.stop().slideDown(50);
				}, 100)
			};
		}, function() {
			clearInterval(_timer);
			_selU.removeClass("selected");
			_opt.hide();
		});
		_oTxt.each(function() {
			var __this = $(this);
			__this.click(function() {
				_sTxt.html(__this.children("a").text());
				_opt.hide();
			})
		})
	})
}

$(function(){
	selFun("#select-1");// 模拟 select 下拉控件
	$("#fileuploadButton").uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf',
		'script'  :uploadServerUrl + '/goswf',
		'scriptData':{"base":"mavendemo","param":"teacher"},
		'queueID' : 'fileQueue',
		'fileDataName' : 'fileupload',
		'auto' : true,
		'multi' : false,
		'hideButton' : false,
		'simUploadLimit' : 3,
		'buttonImg' : imagesPath + '/static/common/uploadify/imgUpload.png',
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
			$("#picpath").attr("src", staticImageServer + response);
			$("#defaultavatar").attr("src", staticImageServer + response);
			$("#imagesUrl").val(response);
			$("#picpath").show();
		},
		onError : function(event, queueID, fileObj,errorObj) {
			$("#fileQueue").html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
		}
	});

	$("#fileupload_card").uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf',
		'script'  :uploadServerUrl + '/goswf',
		'scriptData':{"base":"mavendemo","param":"teacher"},
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
			$("#card_img").attr("src", staticImageServer + response);
			$("#card_imagesUrl").val(response);
			$("#card_img").show();
		}
	});

	$("#fileupload_education").uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf',
		'script'  :uploadServerUrl + '/goswf',
		'scriptData':{"base":"mavendemo","param":"teacher"},
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
			$("#education_img").attr("src", staticImageServer + response);
			$("#education_imagesUrl").val(response);
			$("#education_img").show();
		}
	});

	$("#fileupload_teaching").uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf',
		'script'  :uploadServerUrl + '/goswf',
		'scriptData':{"base":"mavendemo","param":"teacher"},
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
			$("#teaching_img").attr("src", staticImageServer + response);
			$("#teaching_imagesUrl").val(response);
			$("#teaching_img").show();
		}
	});

	$("#fileupload_specialty").uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf',
		'script'  :uploadServerUrl + '/goswf',
		'scriptData':{"base":"mavendemo","param":"teacher"},
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
			$("#specialty_img").attr("src", staticImageServer + response);
			$("#specialty_imagesUrl").val(response);
			$("#specialty_img").show();
		}
	});

});

// 富文本编辑器
function kindeditInit(contextId){
	var editor;
	//加载编辑器
	KindEditor
		.ready(function(K) {
			editor = K
				.create(
				'textarea[id="' + contextId + '"]',
				{
					resizeType : 1,
					filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
					allowPreviewEmoticons : false,
					allowUpload : false,//允许上传
					syncType : 'auto',
					width : '320px',
					minWidth : '10px',
					minHeight : '10px',
					height : '200px',
					urlType : 'domain',//absolute
					newlineTag : 'br',//回车换行br|p
					uploadJson : '//image.268xue.com/imgk4?base=projectName&param=question',//图片上传路径
					allowFileManager : false,
					/* afterFocus:function(){editor.sync();}, */
					afterBlur : function() {
						this.sync();
					},
					items : [ 'fontname', 'fontsize', '|',
						'forecolor', 'hilitecolor', 'bold',
						'italic', 'underline',
						'removeformat', '|', 'justifyleft',
						'justifycenter', 'justifyright'],
					afterChange : function() {
					}
				});
		});
}
