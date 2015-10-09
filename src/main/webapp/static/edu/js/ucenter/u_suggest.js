
var suggestpath="suggest";
	//未解决问题
	function unsolved(){
		ajaxPage("/sug/ajax/suglist","&falg=unsolved",1,callBack);
		clearCurrent();
		$(".unsolved").addClass("current");
		$(".unsolved").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
	}
	function clearCurrent(){
		$(".solved").removeClass("current");
		$(".unsolved").removeClass("current");
		$(".mysug").removeClass("current");
		$(".mysughd").removeClass("current");
		$(".myrecommend").removeClass("current");
		$(".ct-tabarrow-bg").remove();
	}
	//已解決
	function solved(){
		
		ajaxPage("/sug/ajax/suglist","&falg=solved",1,callBack);
		clearCurrent();
		$(".solved").addClass("current");
		$(".solved").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
	}
	//我的问题
	function mysug(){
		//未登录不可操作
		  if(checkLogin()=='unLogin'){
		   return ;
		  }
		ajaxPage("/sug/ajax/suglist","&falg=mysug",1,callBack);
		clearCurrent();
		$(".mysug").addClass("current");
		$(".mysug").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
	}
	//我的回答
	function mysughd(){
		//未登录不可操作
		  if(checkLogin()=='unLogin'){
		   return ;
		  }
		ajaxPage("/sug/ajax/suglist","&falg=mysughd",1,callBack);
		clearCurrent();
		$(".mysughd").addClass("current");
		$(".mysughd").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
	}
	//我推荐的问题
	function myrecommend(){
		//未登录不可操作
		  if(checkLogin()=='unLogin'){
		   return ;
		  }
		ajaxPage("/sug/ajax/suglist","&falg=myrecommend",1,callBack);
		clearCurrent();
		$(".myrecommend").addClass("current");
		$(".myrecommend").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
	}
	
	//ajax回调
	function callBack(result){
		$(".sugHtml").html(result);
	}
	
var EditorObject;
function initKindEditor(id,width,height){//加载编辑器
		EditorObject  = KindEditor.create('textarea[id="'+id+'"]', {
			resizeType : 1,
			filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
			allowPreviewEmoticons : false,
			allowUpload : true,// 允许上传
			syncType : 'auto',
			width : width,
			minWidth : '10px',
			minHeight : '10px',
			height : height,
			urlType : 'domain',// absolute
			newlineTag : 'br',// 回车换行br|p
			uploadJson : keImageUploadUrl+'&param='+suggestpath,//图片上传路径
			allowFileManager : false,
			   items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline','formatblock','lineheight',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link','plainpaste'],
			/* afterFocus:function(){editor.sync();}, */
			afterBlur : function() {
				EditorObject.sync();
			},
			afterChange : function() {
				$('#wenzinum'+id).html(this.count('text'));
			}
		});
}
function submitwushi() {//提交务实问题
	//未登录不可操作
	  if(checkLogin()=='unLogin'){
	   return ;
	  }
	var title = $("#title").val();
	var suggestContent = KindEditor.get("#suggestContent").html();
	if (title.trim() == "") {
		dialog("提示","输入标题不能为空",1);//弹出框
		return;
	}
	if (title.trim().length > 24) {
		dialog("提示","标题不能多于24个字符",1);
		return;
	}
	
	if (suggestContent.trim() == "") {
		dialog("提示","输入内容不能为空",1);
		return;
	}
	if (KindEditor.get("#suggestContent").count('text') > max_text_length) {//内容不能超过4000字
		dialog("提示","输入内容不能超过"+max_text_length+"字,请删减！",1);
		return;
	}
	if (KindEditor.get("#suggestContent").count('text') < 5) {//内容小于5个字
		dialog("提示","输入内容不能小于5个字,请添加！",1);
		return;
	}
	$("#type").val(1);
	$.ajax({
		url:baselocation+"/uc/sug/addSugSuggest",
		type : "post",
		dataType : "json",
		data : $("#addProblem").serialize(),
		success : function(result) {
			if (result.message == "success") {
				dialog("提示","提交成功",16,'/uc/ques/my');
			}
			if (result.message == "false") {
				dialog("提示","请刷新重试",1);
			}
			if (result.message == "addMost") {
				dialog("提示","请不要频繁操作，请稍后再试。",1);//弹出框
			}
			if (result.message == "contentTooLong") {
				dialog("提示","内容过长请删减内容再试。",1);//弹出框
			}
		}
	});
}

function submityijing() {
	var title = $("#title").val();
	var suggestContent = KindEditor.get("#suggestContent").html();
	if (title.trim() == "") {
		dialog("标题不能为空",0);//弹出框
		return;
	}
	if (title.trim().length > 24) {
		dialog("标题不能多于24个字符",0);
		return;
	}
	if (suggestContent.trim() == "") {
		dialog("内容不能为空",0);
		return;
	}
	if (KindEditor.get("#suggestContent").count('text') > max_text_length) {//内容不能超过4000字
		dialog("提示","请输入内容不要超过"+max_text_length+"字,请删减！",4);
		return;
	}
	if (KindEditor.get("#suggestContent").count('text') < 5) {//内容小于5个字
		dialog("提示","输入内容不能小于5个字,请添加！",0);
		return;
	}
	$("#type").val(2);
	$.ajax({
		url:baselocation+"/uc/sug/addSugSuggest",
		type : "post",
		dataType : "json",
		data : $("#addSugSuggest").serialize(),
		success : function(result) {
			if (result.message == "success") {
				dialog("提交成功",1);//弹出框
				$(".queding1").attr("href","javascript:window.location.href = '/sug/my';");//确定按钮的方法
			}
			if (result.message == "false") {
				dialog("提示","请刷新重试",4);//弹出框
			}
			if (result.message == "addMost") {
				dialog("提示","请不要频繁操作，请稍后再试。",0);//弹出框
			}
			if (result.message == "contentTooLong") {
				dialog("提示","内容过长请删减内容再试。",0);//弹出框
			}
		}
	});
}

function suggestHuifu(replyId,cusId){//添加回复框
	var str = $("#removeHuifu"+replyId).html();
	if(str==null||str==""){//如果没有回复的编辑器则添加 有则移除
		var str2 = '<div id="removeHuifu'
			+ replyId
			+ '"></br><textarea  id="contenthuifu'
			+ replyId
			+ '">回复@'
			+ cusId
			+ ':</textarea><br/><a class="reply-sub ml10" onclick="addhuifu('+replyId+')"><span>回复</span></a><span style="display:none" id="numcontenthuifu'+replyId+'"></span></div>';
	$("#replyAnswer"+replyId).append(str2);
	initKindEditor('contenthuifu'+replyId, 540, 140);// 加载编辑器
	}else{
		$("#removeHuifu"+replyId).remove();//移除编辑器
	}
}
function removehuifu(obj,replyId,cusId){//移除回复框
	$("#removeHuifu"+replyId).remove();//移除回复框
	$(obj).attr("onclick","suggestHuifu(this,"+replyId+",'"+cusId+"')");// 修改回复的方法为隐藏
}
function addhuifu(replyId){
	var sugSuggestReplyContent = KindEditor.get("#contenthuifu"+replyId).html();
	if(sugSuggestReplyContent.trim()==""){
		dialog("提示","回复的内容不能为空",1);//弹出框
		return;
	}
	
	 if(KindEditor.count("#contenthuifu"+replyId,"text")>5000){
		 dialog("提示","请输入内容不要超过5000字,请删减！",1);//弹出框
		 return;
	 } 
	$.ajax({
		url:baselocation+"/uc/sug/addSugSuggestReply",//添加问题回复
		dataType : "json",
		type:"post",
		data : {
			"sugSugestId" : sugSugestId,
			"content" : sugSuggestReplyContent,
			"type" : type
		},
		cache : true,
		async : false,
		success : function(result) {
			if (result.message == "success") {
				KindEditor.html("#contenthuifu"+replyId,"");
				queryPaperListByType(sugSugestId, 1);//回复的ajax分页
				dialog("提示","回复成功",15);//弹出框
				var replycount = $(".replycount").html();// 获得回复数
				$(".replycount").html(parseInt(replycount) + 1);// 回复数加一
			}
			if (result.message == "false") {
				dialog("提示","请您刷新重试。",1);//弹出框
			}
			if (result.message == "addMost") {
				 dialog("提示","请不要频繁操作，请稍后再试。",1);//弹出框
			}
			if (result.message == "contentTooLong") {
				 dialog("提示","内容过长请删减内容再试.",1);//弹出框
			}
		}
	});
}


function ReplyListCB(result){
	$("#pbdivId").html(result);
}
function queryPaperListByType(sugSugestId,currentPage){//回复分页
	var parameter ="&sugSugestId="+sugSugestId+"&sugSuggest.cusId="+sugSugestCusId+"&sugSuggest.status="+status+
	"&sugSuggest.type"+type;
	ajaxPage("/uc/question/ajax/replyList",parameter,currentPage,ReplyListCB);
}
function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
function recommend(id){//推荐回复为最佳答案
	$.ajax({
		url:baselocation+"/uc/sug/recommendSugSuggestReply",
		dataType:"json",
		data:{"sugSuggestReplyId":id,
			"sugSuggestId":sugSugestId},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){
			dialog("提示","提交成功",15);//弹出框
			setTimeout(function() {
				window.location.href="/uc/question/info/"+sugSugestId;
			}, 2000);
			}
			if(result.message=="false"){
				dialog("提示","请刷新重试",1);//弹出框
			}
		}
	});
}

function addPage(page,sugSugestId){//添加分页的html
	str="";
	if(page.first){
		str=str+'<li class="disabled"><a href="#">← 上一页</a></li>';
	}else{
		var num = page.currentPage-1;
		str=str+'<li><a href="javascript:queryPaperListByType('+sugSugestId+','+num+')">← 上一页</a></li>';
	}
	var currentPage = page.currentPage-1<1?1:page.currentPage;
	var totalPage = page.totalPageSize;
	var maxNum_new = currentPage>4?6:8-currentPage;//最大显示页码数
	var discnt=1;
	for(var i=3; i>0; i--) {
		if(currentPage>i) {
			str =str+ "<li><a href='javascript:queryPaperListByType("+sugSugestId+","+(currentPage-i)+")'>"+ (currentPage-i) +"</a></li>";
			discnt++;
		};
	}
	str = str + '<li class="active"><a href="javascript:void(0)">'+currentPage+'</a></li>';
	for(var i=1; i<maxNum_new; i++) {
		if(currentPage+i<=totalPage && discnt<7) {
			str = str + "<li><a href='javascript:queryPaperListByType("+sugSugestId+","+(currentPage+i)+")'>"+ (currentPage+i) +"</a></li>";
			discnt++;
		} else {
			break;
		};
	}
	if(page.last){
		str=str+'<li class="disabled"><a href="#">下一页 →</a></li>';
	}else{
		str=str+'<li><a href="javascript:queryPaperListByType('+sugSugestId+','+(page.currentPage+1)+')">下一页 →</a></li>';
	}
	$("#pageFlag").html(str);
}


function addSugSuggestReply(id,type){//添加回复
	//未登录不可操作
	  if(checkLogin()=='unLogin'){
	   return ;
	  }
	var sugSuggestReplyContent = KindEditor.get("#sugSuggestReplyContent").html();
	if(sugSuggestReplyContent.trim()==""){
		dialog("提示","回复的内容不能为空",1);//弹出框
		return;
	}
	
	 if(KindEditor.count("#sugSuggestReplyContent","text")>2000){
		 dialog("提示","请输入内容不要超过2000字,请删减！",1);//弹出框
		 return;
	 } 
	$.ajax({
		url:baselocation+"/uc/sug/addSugSuggestReply",
		dataType : "json",
		type:"post",
		data : {
			"sugSugestId" : id,
			"content" : sugSuggestReplyContent,
			"type" : type
		},
		cache : true,
		async : false,
		success : function(result) {
			if (result.message == "success") {
				KindEditor.html("#sugSuggestReplyContent","");//清除编辑器内容
				queryPaperListByType(id, 1);
				dialog("提示","提交成功",15);//弹出框
				var replycount = $(".replycount").html();// 获得回复数
			    $(".replycount").html(parseInt(replycount) + 1);// 回复数加一
			}
			if (result.message == "false") {
				dialog("提示","请刷新重试",1);//弹出框
			}
			if (result.message == "addMost") {
				dialog("提示","请不要频繁操作，请稍后再试",1);//弹出框
			}
		}
	});
}
function delSugSuggestReply(sugSuggestReplyId){//删除问题回复
	deldialog();
	$.ajax({
		url:baselocation+"/sug/delSugSuggestReplyById",
			dataType : "json",
			data : {
				"sugSuggestReplyId" : sugSuggestReplyId
			},
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					queryPaperListByType(sugSugestId, 1);
					dialog("提示","提交成功",15);
					var replycount = $(".replycount").html();// 获得回复数
					replycount = parseInt(replycount)- 1;
					if(replycount<0){//验证不能减成负数
						replycount = 0;
					}
				    $(".replycount").html(replycount);// 回复数加一
				}
				if (result.message == "false") {
					dialog("提示","请刷新重试",1);
				}
			}
		});
}
function delconfirm(sugSuggestReplyId){
	dialog("确定删除？",2);
	$(".queding2").attr("href","javascript:delSugSuggestReply("+sugSuggestReplyId+")");//删除回复的方法
}
function updateSugSuggest() {//更新问题
	var title = $("#title").val();
	var suggestContent = $("#suggestContent").val();
	if (title.trim() == "") {
		dialog("标题不能为空",0);//弹出框
		$(".queding0").attr("href","javascript:deldialog()");//确定按钮的方法
		return;
	}
	if (suggestContent.trim() == "") {
		dialog("内容不能为空",0);//弹出框
		$(".queding0").attr("href","javascript:deldialog()");//确定按钮的方法
		return;
	}
	 if($("#wenzistr").html()=="你已经超过"){
		 dialog("文字超过140字",0);//弹出框
		$(".queding0").attr("href","javascript:deldialog()");//确定按钮的方法
		 return;
	 } 
	 
	$.ajax({
		url:baselocation+"/sug/updateSugSuggestById",
		dataType : "json",
		data : {
			"sugSuggest.id" : sugSugestId,
			"sugSuggest.content" : suggestContent,
			"sugSuggest.title" : title
		},
		cache : true,
		async : false,
		success : function(result) {
			if (result.message == "success") {
				dialog("提交成功",1);//弹出框
				$(".queding1").attr("href","javascript:deldialog()");//确定按钮的方法
				window.location.href="/sug/my";
			}
			if (result.message == "false") {
				dialog("请刷新重试",0);//弹出框
				$(".queding0").attr("href","javascript:deldialog()");//确定按钮的方法
			}
		}
	});
	
}