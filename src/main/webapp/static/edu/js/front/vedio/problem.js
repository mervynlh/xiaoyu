$(function() {
    initKindEditor('problemContextTextareaId','130px');
});
//课程评价区分页
function sugListPage(id,order) {
	ajaxPage("/front/ajax/answerList", "&kpointId=" + id, 1, callBackSug);
}
// ajax回调方法
function callBackSug(result) {
	$(".lh-list-more").remove();
	$("#problemUlListId").append(result);
	//答疑回复
	replyFun();
}

// 添加问答
function addProblemFun(){
	if(!isLogin){
		 dialog('登录','',3,'','1');
		 return;
	}
	var problemcontext = $("#problemContextTextareaId").val();
	if(problemcontext==null || problemcontext.trim()==''){
		$("#notProblemTTId").show();
		$("#problemContextTextareaId").val('');
		return false;
	}else{
		if(problemcontext.length>255){
			dialog('失败', '输入的内容不能超过255个字', 4);
			return;
		}
		$.ajax({
			url:baselocation+'/front/addAnswerQuestion',
  			type : "post",
	   		dataType : "json",
	   		async:false,
			data : {
	   			"answerQuestion.type":"course",
	   			"answerQuestion.content":problemcontext,
	   			"answerQuestion.parentId":currentCourseId,
	   			"answerQuestion.sonId":currentKpointId
	   			},
  			success:function (result){
  				$("#notProblemTTId").hide();
  				KindEditor.html("#problemContextTextareaId", "");
  				getCourse('gswd',2);
	   		}
		});
	}
}

//答疑回复
function replyFun() {
	$(".question-list>ul>li").each(function() {
		var _this = $(this),
			qxFun = function() {
				_this.find(".n-reply").find(".n-reply-wrap").remove();
				_this.find(".n-reply").slideUp(150);
			};
		_this.find(".noter-dy").click(function() {
			if (_this.find(".n-reply").is(":hidden")) {
				//展开时获取回复数据
				var pblId = $(this).attr('id');//问题ID
				queryReply(pblId);
			} else {
				qxFun();
			};
		});
		
	});
}


function queryReply(pblId){
	var hfElem = '<section id="sectionReContext'+pblId+'" class="n-reply-wrap"><fieldset>' +
	 '<textarea name="" id="reProblemInfo'+pblId+'"></textarea>' +
	 '</fieldset><p class="of mt5 tar pl10 pr10">' +
	 '<font id="fontNull'+pblId+'" style="display:none;" class="c-orange">请输入回复内容</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u class="hand mr10 qxBtn c-999">取消</u>' +
	 '<a href="javascript: void(0)" id="addReProblemFun'+pblId+'" title="回复" class="lh-reply-btn">回复</a>' +
	 '</p></section>';
	
	$.ajax({
		url:baselocation+'/front/ajax/answerReplyList',
		type:'post',
		dataType:'json',
		async:false,
		data:{'answerId':pblId},
		success:function (result){
			if(result.success){
				var obj = result.entity;
				var reProblemContext='';
				for(var i=0;i<obj.length;i++){
					if(obj[i].userId!=0){
						var userImg = obj[i].userExpandDto.avatar;
					}else{
						var userImg ="";
					}
					if(userImg!=null&&userImg.trim()!=''){
						uesrImg=staticImageServer+userImg;
					}else{
						uesrImg=baselocation+'/static/edu/images/default/default_head.jpg';
					}
					reProblemContext+='<div class="mt10 pl10 pr10 pb10"><dl class="n-reply-list"><dd><aside class="n-reply-pic"><img src="'+uesrImg+'" width="50" height="50"></aside>';
					var reProName = obj[i].showName;
//					if(obj[i].reManType==1){
//						reProName=reProName+'老师';
//					}
					reProblemContext+='<div class="of"><span class="fl"><font class="fsize12 c-blue">'+reProName+'</font><font class="fsize12 c-999 ml5">回复：</font></span></div>';
					reProblemContext+='<div class="noter-txt mt5"><p>'+obj[i].content+'</p></div><div class="of mt5">';
					reProblemContext+='<span class="fr"><font class="fsize12 c-999 ml5">'+obj[i].addTime+'</font></span><span class="fl"></span></div></dd></dl></div>'; 
				}
				$("#rePrlblemId"+pblId).slideDown(150).html(hfElem+reProblemContext);
				//取消关闭的方法
				$("#rePrlblemId"+pblId).find(".qxBtn").bind("click", function() {
					$("#rePrlblemId"+pblId).find(".n-reply-wrap").remove();
					$("#rePrlblemId"+pblId).slideUp(150);
				});
				$("#addReProblemFun"+pblId).click(function(){
					pblId = $(this).attr('id').replace('addReProblemFun','');

					
					var reInfo = $("#reProblemInfo"+pblId).val();
					if(reInfo==null || reInfo.trim()==''){
						$("#fontNull"+pblId).show();
						return false;
					}
					$.ajax({
						url:baselocation+'/front/ajax/addAnswerReply',
						type:'post',
						dataType:'json',
						data:{"answerReply.content":reInfo,"answerReply.answerId":pblId},
						success:function (result){
							if(result.success){
								var reCount = parseInt($("#reCount"+pblId).text(),10);
								$("#reCount"+pblId).html(reCount + 1);
								queryReply(pblId);
							}
						}
					});
				});
				$("#reProblemInfo"+pblId).keydown(function (){
					pblId = $(this).attr('id').replace('reProblemInfo','');
					$("#fontNull"+pblId).hide();
				});
				initKindEditor('reProblemInfo'+pblId+'','80px');
			}
		}
	});
}

/**
 * 评论编辑器初始化
 */
function initKindEditor(id,height){
    KindEditor.create('textarea[id="'+id+'"]', {
        resizeType:0,
        filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
        pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
        allowPreviewEmoticons : false,
        syncType : 'auto',
        width : '99%',
        minWidth : '10px',
        minHeight : '10px',
        height : height,
        urlType : 'domain',// absolute
        newlineTag : 'br',// 回车换行br|p
        uploadJson : keImageUploadUrl+'&param=note',//图片上传路径
        allowFileManager : false,
        afterBlur : function() {
            this.sync();
        },
        items : [  'forecolor', 'emoticons'],
        afterChange : function() {

        }
    });
}