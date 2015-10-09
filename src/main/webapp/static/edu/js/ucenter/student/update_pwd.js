function save(){
$(".tip").html("");

var oldPwd=$("#oldPwd").val();
var newPwd=$("#newPwd").val();
var newPwdConfirm=$("#newPwdConfirm").val();

var reg=/(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;

if(oldPwd.length > 15 || oldPwd.length < 6 ){
	$("#oldPwdTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top: -7px;right: -134px;">请输入6-15位密码</tt></em>');
	return;
}
if(!reg.test(oldPwd)){
	$("#oldPwdTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top: -7px;right: -134px;">请不要输入非法关键字</tt></em>');
	return ;
}
if(newPwd.length > 15 || newPwd.length < 6 ){
	$("#newPwdTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top: -7px;right: -134px;">请输入6-15位密码</tt></em>');
	return;
}

if(!reg.test(newPwd)){
	$("#newPwdTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top: -7px;right: -134px;">请不要输入非法关键字</tt></em>');
	return ;
}
if(newPwdConfirm!=newPwd){
	$("#newPwdConfirmTip").html('<em class="vam fl Wrong ml10 mt13 pr">&nbsp;<tt class="fsize14 f-fM c-red-1 pa" style="top: -7px;right: -134px;">两次输入密码不一致</tt></em>');
	return ;
}
$.ajax({
	url : "/uc/user/updatepwd",
	type : "post",
	dataType : "json",
	data:{
		"oldpwd" : oldPwd,
		"newpwd" : newPwd
	},
	success : function(result) {
		 $("#oldPwd").val("");
		 $("#newPwd").val("");
		 $("#newPwdConfirm").val("");
			 if(result.success){
				 dialog(result.message,8,'','');
			 }else{
				 dialog(result.message,10,'','');
			 }
		}
	 });
}