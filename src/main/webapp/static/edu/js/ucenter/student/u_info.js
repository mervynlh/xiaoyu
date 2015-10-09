//切换编辑效果
function editInfo(){
	$("#showInfo").hide();
	$("#editInfo").show();
}
//切换显示效果
function cancel(){
	$("#showInfo").show();
	$("#editInfo").hide();
}
//保存
function save(){
	$(".tip").html("");
	var realname= $("#realname").val();
	var email= $("#email").val();
	var reg = /^[\u4E00-\u9FA5]+$/;
	var nickname=$("#nicknamme").val();
	//真实姓名验证
	if(isEmpty(realname)||!reg.test(realname)){
		$("#realnameTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top:0px;right:-102px;">请填写正确姓名</tt></em>');
		return;
	}
	if(isEmpty(nickname)){
		$("#nicknameTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top:0px;right:-102px;">昵称必填</tt></em>');
		return;
	}
	var reg_email=/^\w+@\w+(\.\w+){1,3}$/; //验证正则
	if(isNotEmpty(email)&&!reg_email.test(email)){
		$("#emailTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top:0px;right:-102px;">邮箱格式不正确</tt></em>');
		return;
	}
	var data = $("#searchForm").serialize();
	$.ajax({
		url:'/uc/user/updateInfo',
		type:'post',
		dataType:'json',
		data:data,
		success:function (result){
			if(result.success){
				dialog(result.message,17,'','/uc/user/uinfo');
			}else{
				dialog(result.message,10,'','');
			}
		}
	});
}