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
//确认时间弹窗
function confirmDialog(id){
	dialog("是否确认时间？",12,"","javascript:confirmTime("+id+")");
}
// 确认时间
function confirmTime(id) {
	var teacherId = $("#teacherId").val();
		$.ajax({
			url : baselocation + "/trxorder/confirmCourseStudent",
			type : "post",
			data : {
				"id" : id,
				"teacherId" : teacherId
			},
			dataType : "json",
			success : function(result) {
				if (result.success) {
					dialog(result.message,17,"","/uc/student/home");
				} else {
					dialog(result.message,9,"","");
				}
			}
		});
}
// 修改时间弹窗
function editTime(id) {
	dialog('', 2, id,"javascript:studentUpdateOrderTime("+id+")");
}
//学生修改预约时间
function studentUpdateOrderTime(id) {
	$("#timeerror").hide();
	var startDate = $("#startDate").val();
	var startTime = $("#startTime").val();
	var timeArray = startTime.split("-");
	$.ajax({
		url : '/uc/ajax/studentUpdateTimeAgain',
		type : "post",
		dataType : "json",
		async : false,
		data : {
			"queryTrxorderDetail.startTime" : startDate + " "
					+ timeArray[0],
			"queryTrxorderDetail.endTime" : startDate + " " + timeArray[1],
			"queryTrxorderDetail.id" : id
		},
		success : function(result) {
			if (result.success) {
				dialog(result.message,17,"","/uc/student/home");
			} else {
				$("#timeerror").text(result.message);
				$("#timeerror").show();
			}
		}
	});
}
// 课时退款弹窗
function refund(id){
	dialog("申请退课的原因",11,"","javascript:confirmRefund("+id+")");
}
// 课时退款
function confirmRefund(id){
	var description = $("#description").val();
	$.ajax({
		url:baselocation+"/trxorder/detail/ajax/refund/"+id,
		type:"post",
		data:{
			"description":description
		},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,17,"","/uc/student/home");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
// 课时付款弹窗
function pay(id){
	dialog("是否确认付款",12,"","javascript:confirmPay("+id+")");
}
// 课时付款
function confirmPay(id){
	$.ajax({
		url:baselocation+"/trxorder/detail/ajax/pay/"+id,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,17,"","/uc/student/home");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
// 课时评价弹窗
function assessDialog(detailId,teacherId){
	dialog("",4,"","javascript:confirmAssess("+detailId+","+teacherId+")");
}
// 课时评价
function confirmAssess(detailId,teacherId){
	var description = $("#description").val();
	$.ajax({
		url:baselocation+"/ajax/assess/assessCourse/"+detailId+"/"+teacherId+"/",
		type:"post",
		data:{"content":description},
		dataType:"json",
		success:function(result){
			if(result.success){
				dialog(result.message,17,"","/uc/student/home");
			}else {
				dialog(result.message,9,"","");
			}
		}
	});
}
// 查看课时评价弹窗
function lookAssessDialog(id){
	dialog("",22,id,"");
}
// 根据阶段id获取年级
function selectSubject(subjectId) {
	var html = "";
	if (subjectId == null || subjectId == '') {
		return;
	}
	$.ajax({
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
	$.ajax({
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
// 清空查询条件
function cleanSelect(){
	$("#subjectMajor").val("");
	$("#subjectName").html("--请选择--");
	$("#gradeName").html("--请选择--");
	$("#majorName").html("--请选择--")
	$("#subjectId").val("");
	$("#gradeId").val("");
	$("#majorId").val("");
}
