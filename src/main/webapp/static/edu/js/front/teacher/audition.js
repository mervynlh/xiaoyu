// 根据阶段id获取年级
function selectSubject(subjectId){
	if(subjectId==null||subjectId==''){
		return;
	}
	$.ajax({
		url : baselocation + "/subj/querySubjectByPid",
		type : "post",
		data : {
			"querySubject.parentId":subjectId
		},
		dataType : "json",
		success : function(result) {
			var list = result.entity;
			var str="";
			if(result.success==true){
				for(var i=0;i<list.length;i++){									
					str+="<p><a  title='"+list[i].subjectName+"' href='javascript:void(0)' onclick=\"selectGrade('"+list[i].subjectId+"','"+list[i].subjectName+"')\">"+list[i].subjectName+"</a></p>";
				}
				$("#selectGradeResult").html(str);
			}
		}
	});
	$("#auditionSubjectId").val(subjectId);
	$("#gradeName").html("--请选择--");
	$("#majorName").html("--请选择--");
}
// 根据年级id获取课程信息
function selectGrade(subjectId,subjectName){
	if(subjectId==null||subjectId==''){
		return;
	}
	$.ajax({
		url : baselocation + "/front/major/list",
		type : "post",
		data : {
			"id":subjectId
		},
		dataType : "json",
		success : function(result) {
			var str="";
			list = result.entity;
			if(result.success==true){
				for(var i=0;i<list.length;i++){									
					str+="<p><a title='"+list[i].majorName+"' href='javascript:void(0)' onclick=\"selectMajor('"+list[i].majorid+"','"+list[i].majorName+"')\">"+list[i].majorName+"</a></p>";
				}
				$("#selectMajor").html(str);
			}
		}
	});
	$("#auditionGradeId").val(subjectId);
	$("#gradeName").html(subjectName);
	$("#gradeSelect").hide();
	$("#majorName").html("--请选择--");
}
// 选择科目
function selectMajor(majorid,majorName){
	$("#auditionMajorId").val(majorid);
	$("#majorName").html(majorName);
	$("#majorSelect").hide();
}
// 为你制定
function submitAudition(){
	var name = $("#auditionName").val();
	var mobile = $("#auditionMobile").val();
	var subjectId = $("#auditionSubjectId").val();
	var gradeId = $("#auditionGradeId").val();
	var majorId = $("#auditionMajorId").val();
	// 判断用户名
	if(name==''){
		dialog("用户名不能为空",10,"","");
		return;
	}
	// 判断手机号码
	var reg = /^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; // 验证手机正则
	if (!reg.test(mobile)) {
		dialog("手机号码格式错误",10,"","");
		return;
	}
	$.ajax({
		url : baselocation + "/audition/addAudition",
		type : "post",
		data : {
			"audition.type":0,
			"audition.studentName":name,
			"audition.studentMobile":mobile,
			"audition.subjectId":subjectId,
			"audition.gradeId":gradeId,
			"audition.majorId":majorId
		},
		dataType : "json",
		success : function(result) {
			if(result.success==true){
				dialog("预约申请成功",13,"","/front/teacher/query/list");
			}
		}
	});
}