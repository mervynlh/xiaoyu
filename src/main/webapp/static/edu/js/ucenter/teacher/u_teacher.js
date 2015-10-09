$(document).ready(function(){
	var index = $("#status").val();
	if(index==0){
		$("#uTabTitle li:eq(0)").addClass("current uHover");
	}else if(index==2){
		$("#uTabTitle li:eq(1)").addClass("current uHover");
	}else if(index==1){
		$("#uTabTitle li:eq(2)").addClass("current uHover");
	}else if(index==3){
		$("#uTabTitle li:eq(3)").addClass("current uHover");
	}else if(index==6){
		$("#uTabTitle li:eq(4)").addClass("current uHover");
	}
	$(".j-s-defalt").mouseover(function() {
		index = $(".j-s-defalt").index(this);
		$(".j-s-option").eq(index).show();
	});
	$(".j-s-option").mouseover(function() {
		$(this).show();
	});
	$(".job-select").mouseout(function() {
		$(".j-s-option").hide();
	});
	$(".j-s-option a").click(function() {
		$(".j-s-defalt span").eq(index).html($(this).html());
		var html = selectSubject($(this).prop("id"), index);
		$(".j-s-option").hide();
	});
});
// 确认时间弹窗
function confirmDialog(id){
	dialog("是否确认时间？",12,"","javascript:confirmTime("+id+")");
}
// 确认时间
function confirmTime(id) {
	var userId = $("#userId").val();
		$.ajax({
			url : baselocation + "/trxorder/confirmCourseTeacher",
			type : "post",
			data : {
				"id" : id,
				"userId" : userId
			},
			dataType : "json",
			success : function(result) {
				if (result.success) {
					dialog(result.message,17,"","/uc/teacher/myclass");
				} else {
					dialog(result.message,9,"","");
				}
			}
		});
}
// 修改时间弹窗
function editTime(id) {
	dialog('', 2, id,"javascript:teacherUpdateOrderTime("+id+")");
}
//老师修改预约时间
function teacherUpdateOrderTime(id) {
	$("#timeerror").hide();
	var startDate = $("#startDate").val();
	var startTime = $("#startTime").val();
	var timeArray = startTime.split("-");
	$.ajax({
		url : '/uc/ajax/teacherUpdateTimeAgain',
		type : "post",
		dataType : "json",
		async : false,
		data : {
			"queryTrxorderDetail.startTime" : startDate + " " + timeArray[0],
			"queryTrxorderDetail.endTime" : startDate + " " + timeArray[1],
			"queryTrxorderDetail.id" : id
		},
		success : function(result) {
			if (result.success) {
				dialog(result.message,17,"","/uc/teacher/myclass");
			} else {
				$("#timeerror").text(result.message);
				$("#timeerror").show();
			}
		}
	});
}
//课时退款弹窗
function refund(id){
	dialog("申请退课的原因",11,"","javascript:confirmRefund("+id+")");
}
// 课时退款
function confirmRefund(id){
	var description = $("#description").val();
	$.ajax({
		url:baselocation+"/trxorder/detail/ajax/refund/"+id,
		type:"post",
		data:{"description":description},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,17,"","/uc/teacher/myclass");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
// 课时小结弹窗
function summaryDialog(id){
	dialog("",21,id,"javascript:confirmSummary("+id+")");
}
// 保存课时小结
function confirmSummary(id){
	var description = $("#description").val();
	$.ajax({
		url:baselocation+"/uc/ajax/course/summary",
		type:"post",
		data:{
			"id":id,
			"content":description
		},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,17,"","/uc/teacher/myclass");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
//查看课时评价弹窗
function lookAssessDialog(id){
	dialog("",22,id,"");
}
// 根据阶段id获取年级
function selectSubject(subjectId) {
	var html = "";
	if (subjectId == null || subjectId == '') {
		return;
	}
	$
			.ajax({
				url : baselocation + "/subj/querySubjectByPid",
				type : "post",
				data : {
					"querySubject.parentId" : subjectId
				},
				dataType : "json",
				success : function(result) {
					var list = result.entity;
					if (result.success == true) {
						for (var i = 0; i < list.length; i++) {
							html += "<p><a  title='"
									+ list[i].subjectName
									+ "' href='javascript:void(0)' onclick=\"selectGrade('"
									+ list[i].subjectId + "','"
									+ list[i].subjectName + "')\">"
									+ list[i].subjectName + "</a></p>";
						}
						$(".j-s-o-box").eq(1).html(html);
					}
				}
			});
	$("#subjectId").val(subjectId);
	$("#gradeName").html("--请选择--");
	$("#majorName").html("--请选择--");
}
// 根据年级id获取课程信息
function selectGrade(subjectId, subjectName) {
	if (subjectId == null || subjectId == '') {
		return;
	}
	$
			.ajax({
				url : baselocation + "/front/major/list",
				type : "post",
				data : {
					"id" : subjectId
				},
				dataType : "json",
				success : function(result) {
					var html = "";
					list = result.entity;
					if (result.success == true) {
						for (var i = 0; i < list.length; i++) {
							html += "<p><a title='"
									+ list[i].majorName
									+ "' href='javascript:void(0)' onclick=\"selectMajor('"
									+ list[i].majorid + "','"
									+ list[i].majorName + "')\">"
									+ list[i].majorName + "</a></p>";
						}
						$(".j-s-o-box").eq(2).html(html);
					}
				}
			});
	$("#gradeId").val(subjectId);
	$(".j-s-option").eq(1).hide();
	$("#gradeName").html(subjectName);
	$("#majorName").html("--请选择--");
}
// 选择科目
function selectMajor(majorid, majorName) {
	$(".j-s-option").eq(2).hide();
	$("#majorId").val(majorid);
	$("#majorName").html(majorName);
}
// 查询课程
function searchCourse() {
	$("#subjectMajor").val($("#subjectName").html()+","+$("#gradeName").html()+","+$("#majorName").html());
	$("#searchForm").submit();
}
//清空查询条件
function cleanSelect(){
	$("#subjectMajor").val("");
	$("#subjectId").val("");
	$("#gradeId").val("");
	$("#majorId").val("");
	$("#subjectName").html("--请选择--");
	$("#gradeName").html("--请选择--");
	$("#majorName").html("--请选择--")
	
}
// 教师个人中心首页加载完成事件
function finishShow(){
	// 基本信息
	if(((entity.userExpand.realname != null && entity.userExpand.realname !='')
		|| (entity.userExpand.nickname != null && entity.userExpand.nickname !=''))
		&& entity.degree != null && entity.degree != '' && entity.degree != '0'
		&& entity.userExpand.birthday != null && entity.userExpand.birthday !=''
		&& entity.majorsNum != null && entity.majorsNum != 0
		&& entity.subjectsNum != null && entity.subjectsNum != 0
		&& entity.finishSchool != null && entity.finishSchool != ''
		&& entity.profession != null && entity.profession != ''
	)
	{
		$("#basis_info").removeClass("u-h-t-btn-tx").addClass("u-h-t-btn");
	}
	// 高级信息
	if(entity.seniority >= 0
		&& entity.teachingLive != null && entity.teachingLive != ''
		&& entity.peculiarity != null && entity.peculiarity != ''
		&& entity.teachingEnounce != null && entity.teachingEnounce != ''
	){
		$("#senior_info").removeClass("u-h-t-btn-tx").addClass("u-h-t-btn");
	}
	// 认证信息
	if(entity.cardStatus == 2 && entity.educationStatus == 2
		&& entity.teachingStatus == 2 && entity.specialtyStatus == 2
		&& entity.isProfessor == 2
	){
		$("#identification_info").removeClass("u-h-t-btn-tx").addClass("u-h-t-btn");
		$("#identification_info").html("查&nbsp;&nbsp;&nbsp;&nbsp;看");
	}
	// 课程信息
	if(entity.courseNum != null && entity.courseNum != 0){
		$("#course_info").removeClass("u-h-t-btn-tx").addClass("u-h-t-btn");
		$("#course_info").html("继续添加");
	}
	// 地址信息
	if(entity.addressNum != null && entity.addressNum != 0){
		$("#address_info").removeClass("u-h-t-btn-tx").addClass("u-h-t-btn");
		$("#address_info").html("继续添加");
	}
	// 获得资料填写完全的数量
	var num = $(".u-h-t-btn").length;
	var str = num / 5 * 100;
	$("#integrities").text(str + "%");
	$(".u-s-jdt-bg").css("width", str + "%");
	if(parseInt(str) <= 80){
		$("#explain").text("请完善您的基本、高级信息，并发布至少一个课程和地址，否则不能被学生搜索");
	}
	if(parseInt(str) == 100){
		$(".u-t-home-tit").hide();
	}
}

// 选项卡切换点击事件
function changeTabs(word){
	if(word == 'base'){
		$("#teacher_material_seniorinfo").hide();
		$("#teacher_material_baseinfo").show();
		$("#teacher_material_base").show();
		$("#teacher_material_setting_base").hide();
		$("#base").addClass("current");
		$("#senior").removeClass("current");
	} else if (word == 'senior') {
		$("#teacher_material_seniorinfo").show();
		$("#teacher_material_baseinfo").hide();
		$("#teacher_material_senior").show();
		$("#teacher_material_setting_senior").hide();
		$("#base").removeClass("current");
		$("#senior").addClass("current");
	}
}

// 编辑按钮点击事件
function onclickSetting(word){
	$("#teacher_material_" + word).hide();
	$("#teacher_material_setting_" + word).show();
}

// 取消按钮点击事件
function cancelOnclick(word){
	$("#teacher_material_" + word).show();
	$("#teacher_material_setting_" + word).hide();
}

// 选择学历
function checkdegree(degreeId){
	$("#hiddendegree").val(degreeId);
}

// 基本资料修改
function baseInfoSetting(){
	// 真实姓名
	var realname = $("#realname").val();
	if(realname == null || $.trim(realname) == ''){
		$("#realnameError").text("请输入您的姓名");
		$("#realnameError").parent().addClass("Wrong");
		return;
	}else{
		$("#realnameError").parent().addClass("Correct");
	}
	// 学历
	var degree = $("#hiddendegree").val();
	if(degree == 0) {
		$("#degreeError").text("请选择您的学历");
		$("#degreeError").parent().addClass("Wrong");
		return;
	}else{
		$("#degreeError").parent().addClass("Correct");
	}
	// 毕业院校
	var finishSchool = $("#finishSchool").val();
	if(finishSchool == null || $.trim(finishSchool) == ''){
		$("#finishSchoolError").text("请输入您的毕业院校");
		$("#finishSchoolError").parent().addClass("Wrong");
		return;
	}else{
		$("#finishSchoolError").parent().addClass("Correct");
	}
	// 专业
	var profession = $("#profession").val();
	if(profession == null || $.trim(profession) == ''){
		$("#professionError").text("请输入您的专业");
		$("#professionError").parent().addClass("Wrong");
		return;
	}else{
		$("#professionError").parent().addClass("Correct");
	}
	// 生日
	var birthday = $("#birthday").val();
	if(birthday == null || $.trim(birthday) == ''){
		$("#birthdayError").text("请选择您的出生日期");
		$("#birthdayError").parent().addClass("Wrong");
		return;
	}else{
		$("#birthdayError").parent().addClass("Correct");
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
		return;
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
		return;
	} else {
		$("#teacherMajor").val(majorIds);
	}
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/base/material/setting",
		type : "post",
		data : {
			"teacher.userExpand.realname" : $("#realname").val(),
			"teacher.userExpand.nickname" : $("#user_nickname").val(),
			"teacher.userExpand.hideStatus" : $('input[name="hideStatus"]:checked').val(),
			"teacher.sex" : $('input[name="sex"]:checked').val(),
			"teacher.degree" : $("#hiddendegree").val(),
			"teacher.finishSchool" : $("#finishSchool").val(),
			"teacher.id" : $("#teacherId").val(),
			"teacher.userId" : $("#teacherUserId").val(),
			"teacher.profession" : $("#profession").val(),
			"teacher.userExpand.birthday" : $("#birthday").val(),
			"teacher.teacherSubject" : $("#teacherSubject").val(),
			"teacher.teacherMajor" : $("#teacherMajor").val()
		},
		dataType : "json",
		success : function(result) {
			if(result.success == true){
				dialog("基本信息修改成功！", 17, "", "javascript:window.location.reload()");
				cancelOnclick('base');
			} else if (result.message == 'realnameIsNull'){
				$("#realnameError").text("请输入您的姓名");
				$("#realnameError").parent().addClass("Wrong");
			} else if (result.message == 'degreeIsNull'){
				$("#degreeError").text("请选择您的学历");
				$("#degreeError").parent().addClass("Wrong");
			} else if (result.message == 'finishSchoolIsNull'){
				$("#finishSchoolError").text("请输入您的毕业院校");
				$("#finishSchoolError").parent().addClass("Wrong");
			} else if (result.message == 'professionIsNull'){
				$("#professionError").text("请输入您的专业");
				$("#professionError").parent().addClass("Wrong");
			} else if (result.message == 'birthdayIsNull'){
				$("#birthdayError").text("请输入您的出生日期");
				$("#birthdayError").parent().addClass("Wrong");
			} else if (result.message == 'subjectIsNull'){
				dialog("请选择您教授的年级", 9, "", "");
			} else if (result.message == 'majorIsNull'){
				dialog("请选择您教授的科目", 9, "", "");
			} else if (result.message == 'error'){
				dialog("系统错误，请联系客服", 9, "", "");
			}
		}
	});
}

// 教师高级资料修改
function seniorInfoSetting(){
	// 教龄
	var seniority = $("#seniority").val();
	if ($.trim(seniority) == ''){
		$("#seniorityError").text("请输入您的教龄");
		$("#seniorityError").parent().addClass("Wrong");
		return;
	} else if (isNaN(seniority)) {
		$("#seniorityError").text("只能输入数字");
		$("#seniorityError").parent().addClass("Wrong");
		return;
	} else {
		$("#seniorityError").parent().addClass("Correct");
	}
	// 教学特点
	var peculiarity = $("#peculiarity").val();
	if(peculiarity == null || $.trim(peculiarity) == ''){
		dialog("请输入您的教学特点", 9, "", "");
		return;
	}
	// 教学宣言
	var teachingEnounce = $("#teachingEnounce").val();
	if(teachingEnounce == null || $.trim(teachingEnounce) == ''){
		dialog("请输入您的教学宣言", 9, "", "");
		return;
	}
	// 工作/学习经历
	var teachingLive = $("#teachingLive").val();
	if(teachingLive == null || $.trim(teachingLive) == ''){
		dialog("请输入您的工作/学习经历", 9, "", "");
		return;
	}
	// 教学成功经历
	var teachingSuccess = $("#teachingSuccess").val();
	if(teachingSuccess == null || $.trim(teachingSuccess) == ''){
		dialog("请输入您的教学成功经历", 9, "", "");
		return;
	}
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/senior/material/setting",
		type : "post",
		data : {
			"teacher.seniority" : seniority,
			"teacher.peculiarity" : peculiarity,
			"teacher.teachingLive" : teachingLive,
			"teacher.teachingSuccess" : teachingSuccess,
			"teacher.teachingEnounce" : teachingEnounce,
			"teacher.id" : $("#teacherId").val(),
			"teacher.userId" : $("#teacherUserId").val()
		},
		dataType : "json",
		success : function(result) {
			if(result.success == true){
				dialog("高级信息修改成功！", 17, "", "javascript:window.location.reload()");
				cancelOnclick('senior');
			} else if (result.message == 'seniorityIsNull'){
				$("#seniorityError").text("请输入正确的教龄");
				$("#seniorityError").parent().addClass("Wrong");
			} else if (result.message == 'peculiarityIsNull'){
				dialog("请输入您的教学特点", 9, "", "");
			} else if (result.message == 'teachingEnounceIsNull'){
				dialog("请输入您的教学宣言", 9, "", "");
			} else if (result.message == 'teachingLiveIsNull'){
				dialog("请输入您的工作/学习经历", 9, "", "");
			} else if (result.message == 'teachingSuccessIsNull'){
				dialog("请输入您的教学成功经历", 9, "", "");
			} else if (result.message == 'error'){
				dialog("系统错误，请联系客服", 9, "", "");
			}
		}
	});
}

var timerInterval;
var now = new Date().getTime();
function timer(id, times) {
	var time = times - now;
    timerInterval = window.setInterval(function () {
        var day = 0,
            hour = 0,
            minute = 0,
            second = 0;//时间默认值
        if (time > 0) {
            day = Math.floor(time / (60 * 60 * 24));
            hour = Math.floor(time / (60 * 60)) - (day * 24);
            minute = Math.floor(time / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(time) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        } else {
            clearInterval(timerInterval);
            if (minute == 0) {
                window.location.reload();
            }
        }
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        
        $("#showTime_"+id).html(day+"天"+hour+"小时"+minute+"分"+second+"秒");
        time--;
    }, 1000);
}

//上传控件加载
function uploadPicLoad(){
	$("#fileupload_img").uploadify({
		'swf' : '/static/common/uploadify-3.1/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
		'uploader'  :uploadSwfUrl,
		'formData':{"base":"mavendemo","param":"teacher"},
		'queueID' : 'fileQueue', //文件队列ID
		'fileObjName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
		'auto' : true, //选定文件后是否自动上传
		'multi' :false, //是否允许同时上传多文件
		'hideButton' : true,//上传按钮的隐藏
		'buttonText' : 'Browse',//默认按钮的名字
		'buttonImage' : '/static/common/uploadify-3.1/liulan.png',//使用图片按钮，设定图片的路径即可
		'width' : 105,
		'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
		'fileSizeLimit' : 51200000,//控制上传文件的大小
		'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
		'fileTypeDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',//出现在上传对话框中的文件类型描述
		'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//支持的格式，启用本项时需同时声明fileDesc
		'folder' : '/upload',//您想将文件保存到的路径
		'cancelImg' : '/static/common/uploadify-3.1/cancel.png',
		'onSelect' : function(fileObj) {
			$("#last").removeClass("last-1");
			fileuploadIndex = 1;
			$("#fileQueue").html("");
			if (fileObj.size > 51200000) {
				dialog('文件太大最大限制51200kb',10,'','');
				return false;
			}
			var myself = this;
			if($(".imgss").length >= 3){
				dialog('最多上传3张图片',10,'','');
				myself.cancelUpload(fileObj.id);
			}
		},
		'onUploadSuccess' : function(file, data, response) {
			// file[object]：上传成功的文件对象
			// data[object]：服务器端返回的数据(任何类似的文件)
			// response[Boolean]：如果服务器响应，则为True。如果服务器未响应或忆超过successTimeout，则为False
		/*	alert( 'id: ' + file.id
				+ ' - 索引: ' + file.index
				+ ' - 文件名: ' + file.name
				+ ' - 文件大小: ' + file.size
				+ ' - 类型: ' + file.type
				+ ' - 创建日期: ' + file.creationdate
				+ ' - 修改日期: ' + file.modificationdate
				+ ' - 文件状态: ' + file.filestatus
				+ ' - 服务器端消息: ' + data
				+ ' - 是否上传成功: ' + response);*/
			if($(".imgss").length < 3){
				teacherStylePicUpload(data);
			}
		},
		'onUploadError' : function(file, errorCode, errorMsg, errorString) {
			$("#fileQueue").html("<br/><font color='red'>"+ file.name + "上传失败</font>");
		}
	});
}

// 教师风采图片上传
function teacherStylePicUpload(picUrl){
	var teacherId = $("#teacherId").val();
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/style/picupload",
		type : "post",
		data : {
			"teacherStyle.teacherId" : teacherId,
			"teacherStyle.imageUrl" : picUrl
		},
		dataType : "json",
		success : function(result) {
			if (result.message == 'dataError'){
				dialog("数据错误，请稍后重试", 9, "", "");
				return false;
			} else if (result.message == 'error'){
				dialog("系统错误，请稍后重试", 9, "", "");
				return false;
			} else if (result.success == true){
				$("#imgs").val($("#imgs").val()+picUrl+",");
				$("#last").before('<li style="margin-bottom:30px;margin-right:30px;height:150px;width:150px"><img style="height:150px;width:150px" class="imgss" src="'+staticImageServer+picUrl+'" alt=""><a href="javascript:void(0)" onClick="clearPic(this, ' + result.entity.id + ')" class="close-eva"></a></li>');
			}
		}
	});
}

//删除要上传的图片
function clearPic(obj, id){
	$(obj).parent().remove();
	//var imgs="";
	//$(".imgss").each(function(){
	//	imgs+=$(this).attr("lang")+",";
	//});
	//$("#imgs").val(imgs);
	teacherStylePicDel(id);
}

// 教师风采图片删除
function teacherStylePicDel(picid){
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/style/picdel",
		type : "post",
		data : {
			"ids" : picid
		},
		dataType : "json",
		success : function(result) {
			if (result.message == 'dataError'){
				dialog("数据错误，请稍后重试", 9, "", "");
			} else if (result.message == 'error'){
				dialog("系统错误，请稍后重试", 9, "", "");
			}
		}
	});
}

//教师上传视频控件加载
function uploadVideoLoad(){
	$("#fileupload_video").uploadify({
		'swf' : '/static/common/uploadify-3.1/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
		'uploader'  :'http://v.polyv.net/uc/services/rest?method=uploadfile',
		'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
		'auto' : true, //选定文件后是否自动上传
		'multi' :false, //是否允许同时上传多文件
		'hideButton' : true,//上传按钮的隐藏
		'buttonText' : 'Browse',//默认按钮的名字
		'buttonImage' : '/static/common/uploadify-3.1/liulan.png',//使用图片按钮，设定图片的路径即可
		'width' : 105,
		'method' : 'post',
		'displayData' : 'percentage', //上传时显示的提示（percentage百分比/speed速度）
		'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
		'sizeLimit' : 102400000,//控制上传文件的大小
		'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
		'fileTypeExts' : '*.avi; *.mp4;*.rm;*.mov;*.rmvb;*.wmv;*.3gp;*.mtv',//文件类型过滤
		'fileTypeDesc' : '支持格式avi/mp4/rm/mov/rmvb/wmv/3gp/mtv',//出现在上传对话框中的文件类型描述
		'folder' : '/upload',//您想将文件保存到的路径
		'cancelImg' : '/static/common/uploadify-3.1/cancel.png',
		'onSelect' : function(fileObj) {
			$("#video_last").removeClass("last-1");
			fileuploadIndex = 1;
			$("#fileQueue").html("");
			if (fileObj.size > 102400000) {
				dialog('视频太大,最大限制100MB',10,'','');
				return false;
			}
			var myself = this;
			if($(".videoss").length >= 3){
				dialog('最多上传3个视频',10,'','');
				myself.cancelUpload(fileObj.id);
				$("#" + fileObj.id).remove();
			}
		},
		onUploadSuccess : function(file, data, response) {
			var jsonobj = eval('('+data+')');
			if (jsonobj.error != null && jsonobj.error != 0){
				var message = "";
				var errorNum = jsonobj.error;
				if (errorNum == 1) {
					message = "找不到writetoken关联的用户";
				} else if (errorNum == 2) {
					message = "文件为空或者writetoken为空";
				} else if (errorNum == 4) {
					message = "提交文件格式不正确";
				} else if (errorNum == 16) {
					message = "空间已满";
				}/* else if (errorNum == 2) {
					message = "文件为空或者writetoken为空";
				} else if (errorNum == 2) {
					message = "文件为空或者writetoken为空";
				} else if (errorNum == 2) {
					message = "文件为空或者writetoken为空";
				}*/
				dialog("上传失败,请联系管理员处理<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:#004444;font-size:14px'>(错误信息：' + message + ')</span>",10,'','');
			} else {
				if($(".videoss").length < 3){
					teacherStyleVideoUpload(jsonobj.data[0].first_image, jsonobj.data[0].vid, jsonobj.data[0].title);
				}
			}
		},
		'onUploadStart': function (file) {
			$("#fileupload_video").uploadify("settings", "formData", {
				'fcharset' : 'ISO-8859-1',
				'writetoken' : webSitewritetoken,
				'JSONRPC' : '{"title": "视频展示", "tag": "", "desc": ""}' });
			//在onUploadStart事件中，也就是上传之前，把参数写好传递到后台。
		},
		onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {//上传文件出错是触发（每个出错文件触发一次）
			dialog(swfuploadifyQueue.errorMsg,10,'','');
		}
	});
}

// 教师风采视频上传
function teacherStyleVideoUpload(imgUrl, videoUrl, name){
	var teacherId = $("#teacherId").val();
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/style/videoupload",
		type : "post",
		data : {
			"teacherStyle.teacherId" : teacherId,
			"teacherStyle.imageUrl" : imgUrl,
			"teacherStyle.videoUrl" : videoUrl,
			"teacherStyle.name" : name
		},
		dataType : "json",
		success : function(result) {
			if (result.message == 'dataError'){
				dialog("数据错误，请稍后重试", 9, "", "");
				return false;
			} else if (result.message == 'error'){
				dialog("系统错误，请稍后重试", 9, "", "");
				return false;
			} else if (result.success == true){
				$("#video_last").before('<li style="margin-bottom:30px;margin-right:30px;height:150px;width:150px"><a class="tac txtOf mr30" href="javascript:dialog(\'' + videoUrl + '\' ,20 ,\'\', \'\')"><img style="height:150px;width:150px" class="videoss" src="' + imgUrl + '" alt="' + name + '"><em class="icon-bf pa">&nbsp;</em><div class="teach-fc-bg-shot pa"></div><a href="javascript:void(0)" onClick="clearPic(this, ' + result.entity.id + ')" class="close-eva"></a></a></li>');
			}
		}
	});
}

//删除上传的视频
function clearVideo(obj, id){
	$(obj).parent().remove();
	var videos="";
	$(".imgss").each(function(){
		videos+=$(this).attr("lang")+",";
	});
	$("#imgs").val(videos);
	teacherStylePicDel(id);
}

// 教师风采图片删除
function teacherStylePicDel(picid){
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/style/picdel",
		type : "post",
		data : {
			"ids" : picid
		},
		dataType : "json",
		success : function(result) {
			if (result.message == 'dataError'){
				dialog("数据错误，请稍后重试", 9, "", "");
			} else if (result.message == 'error'){
				dialog("系统错误，请稍后重试", 9, "", "");
			}
		}
	});
}

// 教师资质认证图片上传控件加载
function uploadattestation(){
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
}

// 认证/查看按钮点击事件
function onclickBtn(word, type){
	if (type == 'UP'){
		$("#" + word + "_attestation").hide();
		$("#btn_" + word).attr("onclick", "onclickBtn('" + word + "', 'DOWN')");
	} else {
		$("#" + word + "_attestation").show();
		$("#btn_" + word).attr("onclick", "onclickBtn('" + word + "', 'UP')");
	}
}

// 确认按钮点击事件
function onclickConfirm(word){
	var type = "";
	if(word == 'card'){
		type = "身份证";
	} else if(word == 'education'){
		type = "学历证";
	} else if(word == 'teaching'){
		type = "教师证";
	} else if(word == 'specialty'){
		type = "专业资质";
	}
	var card = $("#card_imagesUrl").val();
	if(card == null || card == ''){
		dialog("请上传您的身份证图片", 10, "", "");
		return;
	}
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/aptitude/attestation",
		type : "post",
		data : {
			"teacher.card" : card,
			"teacher.education" : $("#education_imagesUrl").val(),
			"teacher.teaching" : $("#teaching_imagesUrl").val(),
			"teacher.specialty" : $('#specialty_imagesUrl').val(),
			"teacher.id" : $("#teacherId").val(),
			"teacher.userId" : $("#teacherUserId").val(),
			"type" : word
		},
		dataType : "json",
		success : function(result) {
			if(result.success == true){
				if (result.entity == null || result.entity == ""){
					dialog(type + "认证修改成功！", 17, "", "javascript:window.location.reload()");
				} else {
					dialog(result.entity, 9, "", "");
				}
			} else if (result.message == 'cardIsNull'){
				dialog("请上传身份证图片", 9, "", "");
			} else if (result.message == 'error'){
				dialog("系统错误，请联系客服", 9, "", "");
			}
		}
	});
}

// 教师申请韩教授认证
function attestationApply(teacherId){
	$.ajax({
		url : baselocation + "/uc/ajax/teacher/aptitude/attestation/apply",
		type : "post",
		data : {
			"teacher.id" : teacherId,
			"teacher.userExpand.realname" : $("#teacherRealname").val()
		},
		dataType : "json",
		success : function(result) {
			if(result.success == true){
				dialog("申请韩教授认证成功！", 17, "", "javascript:window.location.reload()");
			} else if (result.message == 'dataError'){
				dialog("请求数据错误", 9, "", "");
			} else if (result.message == 'error'){
				dialog("系统错误，请联系客服", 9, "", "");
			}
		}
	});
}

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
