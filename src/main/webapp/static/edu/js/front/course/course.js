// try_listen list
var derCone = '输入你想要查找的课程名称！';
var free_kpoint_id=null;//免费试听第一个可以观看的节点
var free_name;//免费试听第一个可以观看的节点
function search() {
	var courseName = $("#courseName").val();
	if (courseName == derCone) {
		$("#hiddencourseName").val("");
	}
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}
function inputSell(type) {
	if (type == 1) {
		if ($("#courseName").val() == derCone) {
			$("#courseName").val('');
		}
	} else if (type == 2) {
		if ($("#courseName").val() == null || $("#courseName").val().trim() == '') {
			$("#courseName").val(derCone);
		}
	}
}

//课程搜索的排序
function orderByQuery(em, type) {
	if (type != 3) {
		if ($(em).hasClass('current')) {
			return false;
		}
		$("#hiddenorder").val(type);
	} else {
		var val = $("#order").val();
		if (val == 3) {
			$("#hiddenorder").val('4');
		} else {
			$("#hiddenorder").val('3');
		}
	}
	search();
}

/**
 * 点击搜索
 * @param type 专业/老师
 * @param id 对应的值
 */
function clickSearch(type, id) {
    //点击搜索时要把当前页码设置为1
    $("#pageCurrentPage").val(1);
	if (type == 'subject') {
		$("#hiddenSubjectId").val(id);
	} else if (type == 'teacher') {
		if (id == 0) {
			$("#hiddenTeacherIds").val('');
		} else {
			$("#hiddenTeacherIds").val(id);
		}
	} else if (type == 'memberType') {
		if (id == 0) {
			$("#hiddenMemberId").val('');
		} else {
			$("#hiddenMemberId").val(id);
		}
	} else if (type == 'clear') {//清空
		$("#hiddenTeacherIds").val('');
		$("#hiddenSubjectId").val('');
		$("#hiddencourseName").val('');
		$("#hiddenMemberId").val('');
	}
	$("#teacherName").val("");
	$("#hiddenTeachername").val("");
	search();
}

/**
 * 点击搜索
 * @param type 专业/老师
 * @param id 对应的值
 */
function teacherSearch(type, id) {
	if (type == 'subject') {
		$("#hiddenSubjectId").val(id);
	} else if (type == 'teacher') {
		if (id == 0) {
			$("#hiddenTeacherIds").val('');
		} else {
			$("#hiddenTeacherIds").val(id);
		}
	} else if (type == 'memberType') {
		if (id == 0) {
			$("#hiddenMemberId").val('');
		} else {
			$("#hiddenMemberId").val(id);
		}
	} else if (type == 'clear') {//清空
		$("#hiddenTeacherIds").val('');
		$("#hiddenSubjectId").val('');
		$("#hiddencourseName").val('');
	}
	search();
}


// 课程详情的节点树
var setting = {
	view : {
		showIcon : false,// 是否显示节点图片
		dblClickExpand : false,// 是否双击展开节点
		showLine : true,// 是否显示节点之间的连接线
		selectedMulti : false,// 是否可以按Ctrl键选择多个节点
		nameIsHTML : true,// 是否可以html脚本
		showTitle : false
	},
	data : {
		simpleData : {
			enable : true,// true false 分别表示 使用 不使用 简单数据模式
			idKey : "id",// 节点数据中保存唯一标识的属性名称,enable = true 时生效
			pIdKey : "parentId"// 节点数据中保存其父节点唯一标识的属性名称。[setting.data.simpleData.enable
			// = true 时生效]

		},
		key : {
			name : "name",// ztree显示的名称
			title : "name"
		}
	},
	callback : {
		onClick : function(event, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("courseTree_"+currentCourseId);
			if (!treeNode.isParent) {
				// 判断是否为试听节点，是不是父节点0为视频
				if (isNotEmpty(treeNode.videourl) &&  (treeNode.isfree == 1 || currentprice <= 0 ) ) {
					getPlayerHtml(treeNode.id, currentCourseId,treeNode.name.replaceAll('<tt class="green-btn st-btn">免费试听</tt>',''));
				} else if (treeNode.isfree == 0) {
					dialog('提示', "该课程暂不支持试听，请购买后观看。", 1);
					return false;
					
				}else if (isEmpty(treeNode.videourl)) {
					dialog('提示', "对不起，视频地址暂未录入。", 1);
				}else {
					if($("#playPower").val()=='no'){
						dialog('提示', "该课程暂不支持试听，请购买后观看。", 1);
						return false;
					}else{
						getPlayerHtml(treeNode.id, currentCourseId,treeNode.name.replaceAll('<tt class="green-btn st-btn">免费试听</tt>',''));
					}
				}
			} else {
				treeObj.expandNode(treeNode);
			}
		}
	}
};


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
					reProblemContext+='<div class="of clearfix"><span class="fl"><font class="fsize12 c-blue">'+reProName+'</font><font class="fsize12 c-999 ml5">回复：</font></span></div>';
					reProblemContext+='<div class="noter-txt mt5"><p>'+obj[i].content+'</p></div><div class="of mt5">';
					reProblemContext+='<span class="fr mt10 clearfix"><font class="fsize12 c-999 ml5">'+obj[i].addTime+'</font></span><span class="fl"></span></div></dd></dl></div>'; 
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
				initKindEditor('reProblemInfo'+pblId+'','100px');
			}
		}
	});
}

// 课程咨询区
function consultationSellWay() {
    initcourseAss();
	ajaxPage("/front/ajax/answer_list","&kpointId="+courseId,1,callBackconsultation);
}
//ajax回调方法
function callBackconsultation(result){
	$("#problemUlListId").html(result);
}

//课程评价区
function assessSellWay() {
	ajaxPage("/front/ajax/assess","&courseId="+courseId,1,callBackassess);
}
//ajax回调方法
function callBackassess(result){
	$(".ajaxHtml1").html(result);
}

/**
 * 获得播放器的html
 * @param kpointId节点id
 * @param courseId 课程id
 * @param name 弹窗显示的课程名称（title）
 */
function getPlayerHtml(kpointId, courseId,name) {
	if(sellType=='LIVE'){
		$.ajax({
			url : "" + baselocation + "/front/getLiveKopintUrl",
			data : {
				"kpointId" : kpointId
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				window.location.href=result.message;
			}
		});
	}else{
		$("#playTitle").html(name);
		$("#playTitleTwo").html(name);
		$("#smallDiv").show();
		$.ajax({
			url : "" + baselocation + "/front/ajax/getKopintHtml",
			data : {
				"kpointId" : kpointId,
				"courseId" : courseId
			},
			type : "post",
			dataType : "text",
			async:false,
			success : function(result) {
				//$("#loadingDiv").hide();
				$("#smallPlayer").html(result);
				$("html, body").animate({scrollTop : 0}, 500);
				
			}
		});
	}
	
	
}

// 不是flash
function notFlash(tryUrl) {
	if (tryUrl != null && tryUrl != '') {
		var element = document.getElementById('vedioSpace');
		$(element).html('');
		var script = document.createElement('script');
		script.type = 'text/javascript';// '+tryUrl+'
		script.src = 'http:\//union.bokecc.com\/player?vid=5DAE1A8C8E0897789C33DC5901307461&siteid=4E76CEFF9BBB5FE4&autoStart=true&width=402&height=317&playerid=A98017125E31827D&playertype=1';
		element.appendChild(script);
	}
}
$(function() {
	$(".expandClass").click(function() {
		$(".expandClass").removeClass('current');
		$(this).addClass('current');
		var id = $(this).attr('lang');
		if (id != 'house') {
			$(".publicClass").hide();
			$("#" + id).css('display', '');
		}
	});
});

/**
 * 课程详情节点树初始化加载
 * @param id 课程的id
 */
function kpointInit(id){
	currentCourseId=id;
	free_kpoint_id=null;
	//上次的div不为空 代表是相同的，直接返回不用再获取了。
	if( isNotEmpty($("#courseTree_"+id).html())){
		return;
	}
	//先清空之前的
	$('div[id^=courseTree_]').html('');
	$('span[id^=flztreeopen_]').removeClass('zTreeOpen');
	$('#flztreeopen_'+id).addClass('zTreeOpen');
	//取得商品的所有的视频节点
	var vedioConent = getCourseTreeString(id);
	//判断是否有视频节点
	if(isNull(vedioConent)){
		var conent ='<div class=""><div class="">';
		conent+='<section class="comm-tips-1"><p><em class="vam c-tips-1">&nbsp;</em><font class="c-999 fsize12 vam">对不起，该课程暂未上传视频！</font></p></section>';
		conent+='</div></div>';
		$("#courseTree_").html(conent);
	}else{
		var t = $("#courseTree_"+id);
		//初始化树结构，三个参数中前两个必须的
		t = $.fn.zTree.init(t, setting, vedioConent);
		//获取树结构对象，参数是页面中的显示树结构的Element的ID
		var zTrees = $.fn.zTree.getZTreeObj("courseTree_"+id);
		//获取所有的父节点和子节点
		var childNodes = zTrees.transformToArray(zTrees.getNodes());
 		 
		var ta=0;//标识符初始值是0 试听节点只取第一个可以试听的
		var name='';
		//重新显示。加免费试听按钮，并记录第一个可以试听的地址
		zTrees.expandNode(childNodes[0], true, true, true);
		for(var i=0;i<childNodes.length;i++){
			//判断如果有播放路径type=0是小节，1是章节
			if(childNodes[i].type==0 &&   (isNotEmpty(childNodes[i].videourl))){
				//判断是否可以试听
				if(childNodes[i].isfree==1 || currentprice<=0){
					if(ta==0){
						//记录下获取的第一个节点，用于试听点击
						kpointObj = childNodes[i];
						ta++; 
						free_kpoint_id=childNodes[i].id;
                        free_name = childNodes[i].name;
                    }
					//检测到可以试听的子节点修改子节点名
					childNodes[i].name=childNodes[i].name+'<tt class="green-btn st-btn">免费试听</tt>';
					zTrees.updateNode(childNodes[i]);
				}
			}
			//**
			
		} 
	}
}
//提交搜索表单之前的操作
 function seachSubmit(){
	 $("#searchForm").submit(function(){
	 	//如果课程名为默认的则设置为空
		 var courseName = $("#courseName").val();
			if (courseName == derCone) {
				$("#courseName").val("");
			}
			//搜索之前到第一页
			$("#pageCurrentPage").val(1);
			return true;
	 });
 }
 //表单初始化方法
 function searchInit(){
		subjectId = subjectId.trim();
		//设置 显示检索条件。开始------------------
		if(subjectId!='0'){
			subjectId = $("#subjectAId"+subjectId).text();
		}else{
			subjectId = '';
		}
		
		teacherId = teacherId.replace(/\|/g,'');
		teacherId = teacherId.trim();
		if(teacherId!=null && teacherId!=''){
			teacherId = $("#teacherAId"+teacherId).text();
		}else{
			teacherId ='';
		}
		var queryConditionConent='';
		if(subjectId!=''){
			queryConditionConent+=subjectId+'(专业)';
		}
		
		if(teacherId!=''){
			if(subjectId!=''){
				if(order!=null && order.trim()!=''){
					queryConditionConent+='+'+teacherId+'(老师)+';
				}else{
					queryConditionConent+='+'+teacherId+'(老师)';
				}
			}else{
				if(order!=null && order.trim()!=''){
					queryConditionConent+=teacherId+'(老师)+';
				}else{
					queryConditionConent+=teacherId+'(老师)';
				}
			}
		}
		if(order==1){
			queryConditionConent+='关注度';
		}else if(order==2){
			queryConditionConent+='最新';
		}else if(order==3){
			queryConditionConent+='价格(升序)';
		}else if(order==4){
			queryConditionConent+='价格(降序)';
		}
		if(name!=null && name.trim()!=''){
			if(queryConditionConent!=''){
				queryConditionConent=name+'+'+queryConditionConent;
			}else{
				queryConditionConent=name;
			}
		}
		$("#retrievalContentId").text(queryConditionConent);
		//设置 显示检索条件。结束------------------
		if(name==null || name==''){
			$("#courseName").val(derCone);
		}
		if(order=='1'||order=='2'){
			$("#orderAid"+order).parent('li').find('a').removeClass('current');
			$("#orderAid"+order).addClass('current');
		}else{
			if(order=='3'){
				$("#orderAid3").addClass('current');
				$(".u-jg").css('background-position','-149px -270px');
			}else if(order=='4'){
				$("#orderAid3").addClass('current');
				$(".u-jg").css('background-position','-134px -270px');
			}
		};
		$(".s-c-list>li:nth-child(4n)").css({"margin-right" : "0px"});
 }
 
 function shitingSwf(tryUrl){
		if(tryUrl==null||tryUrl==''){
			dialog('试听播放提示',"对不起，该课程暂无试听的视频!",1);
		}else{
			var vedioContext='<embed src="'+tryUrl+'" pluginspage="http://www.macromedia.com/go/getflashplayer" flashvars="showAd=0&VideoIDS=XNTkwNDc5MTA0&isAutoPlay=true&winType=adshow&isDebug=false&playMovie=true&MMControl=false&MMout=false&isShowRelatedVideo=false&allowScriptAccess=never"  allowfullscreen="true" quality="high" bgcolor="#FFFFFF" type="application/x-shockwave-flash" allownetworking="internal" wmode="transparent" width="440px" height="382px"/>';
			$("#vedioSpace").html(vedioContext);
		}
	}

 /**
  * 获得课程结构
  * @param id
  */
 function getCourseTreeString(id){
	 var res;
	 $.ajax({
			url:"/front/coursetree/"+id,
			data:{},
			type : "post", 
			dataType : "json",
			async:false,
			success: function (result){
				if(result.success){
					res= result.entity;
				}
			}
		});
	 return res;
 }

 /**
  * 提交课程评论
  * @param courseId
  */
 function addReview(courseId){
	 if(isLogin()){
		 var dyId=$("#dyId").val();
		 if(isEmpty(dyId)){
			 dialog('提示信息','请输入评论内容',4);
			 return;
		 }
		 $.ajax({
				url : "/front/addAnswerQuestion",
		   		type : "post",
		   		dataType : "json",
		   		data : {
		   			"answerQuestion.parentId":courseId,
		   			"answerQuestion.sonId":'0',
		   			"answerQuestion.content":dyId,
		   			"answerQuestion.type":'COURSE'
		   			},
				success:function(result){
					if(result.success){
						consultationSellWay();
						$("#dyId").val('');
                        KindEditor.html("#dyId", "");
					}/*else if(result.message=="noBuy"){
						dialog('提示信息','您还没有购买该课程，请购买后评论',1);
					}*/else{
						dialog('提示信息','提交失败，请刷新重试',1);
					}
					
				}
			});
	 }else{
		 dialog('登录','',3,'',1);
		 $("#userEmail").mailAutoComplete({
			    boxClass: "out_box", //外部box样式
			    listClass: "list_box", //默认的列表样式
			    focusClass: "focus_box", //列表选样式中
			    markCalss: "mark_box", //高亮样式
			    autoClass: false,//不使用默认的样式
			    textHint: true //提示文字自动隐藏
			});
	 }

 }
 /**
  * 课程大图片点击试听按钮
  */
		 
 function openListFree (){
	 if(free_kpoint_id==null){
		 dialog('试听播放提示',"对不起，该课程暂无试听的视频!",1);
	 }else{
		 getPlayerHtml(free_kpoint_id,currentCourseId,free_name);
	 }
 }
 /**
  * 个人中心播放页
  */
 function doPlay(id){
	 if(!isLogin()){
         dialog('登录','',3,'','1');
         $("#userEmail").mailAutoComplete({
             boxClass: "out_box", //外部box样式
             listClass: "list_box", //默认的列表样式
             focusClass: "focus_box", //列表选样式中
             markCalss: "mark_box", //高亮样式
             autoClass: false,//不使用默认的样式
             textHint: true //提示文字自动隐藏
         });
         return;
     }
     //非直播课程直接播放
     if(sellType!='LIVE'){
         window.location.href="/front/playkpoint/"+id;
     }else{
         //直播课程跳直播页面
         window.location.href="/front/playkpoint/"+id+"?falg=live";
     }
 }



//直播倒计时
var timerInterval;
function timer(intDiff, i) {
    timerInterval = window.setInterval(function () {
        var day = 0,
            hour = 0,
            minute = 0,
            second = 0;//时间默认值
        if (intDiff > 0) {
            day = Math.floor(intDiff / (60 * 60 * 24));
            hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
            minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        } else {
            clearInterval(timerInterval);
            if (minute == 0) {
                window.location.reload();
            }
        }
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        $('#day_show' + i).html(day);
        $('#hour_show' + i).html(hour);
        $('#minute_show' + i).html(minute);
        $('#second_show' + i).html(second);
        intDiff--;
    }, 1000);
}

/**
 * 评论编辑器初始化
 */
function initcourseAss(){//编辑器初始化
    KindEditor.create('textarea[id="dyId"]', {
        resizeType:0,
        filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
        pasteType:0,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
        allowPreviewEmoticons : false,
        syncType : 'auto',
        width : '99%',
        minWidth : '10px',
        minHeight : '10px',
        height : '150px',
        urlType : 'domain',// absolute
        newlineTag : 'br',// 回车换行br|p
        uploadJson : keImageUploadUrl+'&param=suggest',//图片上传路径
        allowFileManager : false,
        afterBlur : function() {
            this.sync();
        },
        items : [  'forecolor', 'emoticons'],
        afterChange : function() {

        }
    });
};


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