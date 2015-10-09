var setTimeoutflag;
$(function() {
	initdiss();//样式初始化
	if(isNotEmpty(currentKpointId)){
		displaycourseTree(currentCourseId,currentKpointId);
	}else{
		displaycourseTree(currentCourseId);//初始化课程并播放
	}
});


//样式初始化
function initdiss(){
    //兼容客户端
    elePos();
    //显隐右侧方法
    rMenu();
    //checkbox控件（笔记选择是否为公开笔记的控件）
    oCheck();
    //tree menu
    treeMenu();
    //无三级节点
    noJd();
    //选项卡公共方法
    cardChange("#sup-chageCard-title>li", "#sup-chageCard-cont>section", "current");
}

//棵树目录 ztree  配置
var setting = {
		view: {
			showIcon: true,//是否显示节点图片
			dblClickExpand: false,//是否双击展开节点
			showLine: true,//是否显示节点之间的连接线
			selectedMulti: false,//是否可以按Ctrl键选择多个节点
			nameIsHTML:true,
			fontCss: setFontCss,
			showTitle:false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "parentId"
			},
			key:{
				name:'name'
			}
		},
		callback:{
			onClick:clickPlay
		}
	};

//设置树样式
function setFontCss(treeId, treeNode){
}

/**
 * 点击播放  (树的click事件)
 */
function clickPlay(event,treeId,treeNode,clickFlag,kpointId){
	
	var tzree = $.fn.zTree.getZTreeObj(treeId);
	$.fn.zTree.getZTreeObj('courseTree_'+currentCourseId).cancelSelectedNode();
	var node = treeNode;
	var index = treeId.replace('courseTree_'+currentCourseId,'');
	if(kpointId){
	}else{
		kpointId = treeNode.id;
		if(treeNode.isParent&&(treeNode.voUrl==null||treeNode.voUrl.trim()=='')){
			tzree.expandNode(treeNode, true, true, true);
			return false;
		}
	}
	var nodes = tzree.transformToArray(tzree.getNodes());
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].id==kpointId){
			node = nodes[i];
			tzree.selectNode(node);
			break;
		}
	}
	var initvoUrlObj=node;//当前播放节点
	var prevoUrlObj=recursive(node,-1);//上一个节点
	var nextvoUrlObj=recursive(node,1);//下一个节点
	//player(prevoUrlObj,node,nextvoUrlObj);
}

/**
 * 判断是否是苹果客户端
 */
function isApple(){
	if ((/iPhone|iPad|iPod/i).test(navigator.userAgent) || (/Mac68K|MacPPC|Macintosh|MacIntel/i).test(navigator.platform)){  
	    return true;
	}  
	return false;
}
/**
 * 执行播放的方法
 * @param prevoUrlObjs 上一个视频
 * @param initvoUrlObjs 当前视频
 * @param nextvoUrlObjs 下一个视频
 */
function player(prevoUrlObjs,initvoUrlObjs,nextvoUrlObjs){
	//把点击的节点数据放入全局变量
    currentKpointId=initvoUrlObjs.id;
	initvoUrlObj = initvoUrlObjs;
	if(setTimeoutflag!=null){//上次如果未执行的先取消掉
		clearTimeout(setTimeoutflag);
	}
	if(checkSellWayById(courseId,initvoUrlObj.id)){
		if(initvoUrlObjs!=null &&initvoUrlObjs!=''){
	 		var initTitle = initvoUrlObjs.name;
	 		if(initTitle.indexOf('<b')>-1){
	 			initTitle = initTitle.substring(0,initTitle.indexOf('<b'));
	 		}
	 		$("#titleIdBig").html(initTitle);
		}
		
		if(initvoUrlObj.videourl==null ||initvoUrlObj.videourl==''){
			dialog('提示','视频节点暂时不能播放',4);
		}else{
			getPlayerHtml(currentCourseId,currentKpointId);
			//记录播放次数
			setTimeoutflag=setTimeout('addPlayTimes('+initvoUrlObj.courseId+','+initvoUrlObj.id+')',Number(countPlayTimeOut)*1000);
		}
		//当前展示的树的id
		var currztreeId="courseTree_"+currentCourseId;
 		if(prevoUrlObjs&&prevoUrlObjs!=null&&prevoUrlObjs.name!=''){
 			var title = prevoUrlObjs.name;
	   		$("#upLast").html('<a onclick="clickPlay(null,\''+currztreeId+'\',null,null,'+prevoUrlObjs.id+')" href="javascript:void(0)">上一节课：'+title+'</a>');
 		}else{
 			$("#upLast").html('<a href="javascript:void(0)">上一节课：无</a>');
 		}
 		if(nextvoUrlObjs&&nextvoUrlObjs!=null&&nextvoUrlObjs.name!=''){
 			var title = nextvoUrlObjs.name;
	   		$("#downLast").html('<a onclick="clickPlay(null,\''+currztreeId+'\',null,null,'+nextvoUrlObjs.id+')" href="javascript:void(0)">下一节课：'+title+'</a>');
 		}else{
 			$("#downLast").html('<a href="javascript:void(0)">下一节课：无</a>');
 		}
	}else{
		alert("您还未购买此课程，谢谢！");
		window.location.href=baselocation+"/front/couinfo/"+currentCourseId;
	}
}

// 获得播放器的html
function getPlayerHtml(courseId,kpointId) {
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
			$("#vedio").html(result);
		}
	});
}

/**
 * 验证是否可以观看
 */
function checkSellWayById(courseId,ipointId){
	var checkflag=false;
	return true;
}

//swf播放的方法
function playerSwf(voUrl){
	var vedioContext='<embed src="'+voUrl+'" pluginspage="http://www.macromedia.com/go/getflashplayer" flashvars="showAd=0&VideoIDS=XNTkwNDc5MTA0&isAutoPlay=true&winType=adshow&isDebug=false&playMovie=true&MMControl=false&MMout=false&isShowRelatedVideo=false&allowScriptAccess=never"  allowfullscreen="true" quality="high" bgcolor="#FFFFFF" type="application/x-shockwave-flash" allownetworking="internal" wmode="transparent" width="100%" height="96%"/>';
	$("#vedio").html(vedioContext);
}


/**
 * 检测电脑是否安装了flash
 * @returns {___anonymous44316_44376}
 */
function flashChecker(){
    var hasFlash = 0; //是否安装了flash  
    var flashVersion = 0; //flash版本  
    if(document.all) {  
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');  
        if(swf) {  
            hasFlash = 1;  
            VSwf = swf.GetVariable("$version");  
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);  
        }  
    }else{  
        if(navigator.plugins && navigator.plugins.length > 0) {  
            var swf = navigator.plugins["Shockwave Flash"];  
            if(swf) {  
                hasFlash = 1;  
                var words = swf.description.split(" ");  
                for(var i = 0; i < words.length; ++i) {  
                    if(isNaN(parseInt(words[i]))) continue;  
                    flashVersion = parseInt(words[i]);  
                }  
            }  
        }  
    }  
    return {  
        f: hasFlash,  
        v: flashVersion  
    };  
}

/**
 * 返回true表示已经安装了Flash
 * 返回flase表示没有安装Flash
 * @returns {Boolean}
 */
function testingFlash(){
	var fls = flashChecker();  
    if(fls.f) {
    	return true;
    }else{
    	return false;
    } 
}

var learn_pageSize=7;
var learn_pageNo=1;//当前页数
var learn_totalPage=0;//总页数
var learn_totalRecord=0;//总记录数
$(window).resize(function() {
	elePos();
});
	//兼容客户端
	function elePos() {
		var winW = parseInt(document.documentElement.clientHeight, 10) + parseInt(document.documentElement.scrollTop || document.body.scrollTop, 10);
		$(".lh-play-body,.lh-r-body,.flash-play-wrap").css("height" , winW-148 + "px");
		$(".lh-menu-wrap").css("height" , winW-200 + "px");
		$(".lh-bj-list").css("height" , winW-362 + "px");
		$(".noteTextArea").css({"height" : winW-223 + "px","overflow-y" : "auto","overflow-x" : "hidden"});//noteTextArea>textarea 
	};
	//显隐右侧方法
	function rMenu() {
		$("#o-c-btn").click(function(){
			if($(this).is('.ocb-current')){
				$(".lh-play-box").animate({"margin-right" : "435px"}, 400);
				$(".lh-right-wrap").animate({"right" : "35px"}, 400);
				$(this).removeClass("ocb-current");
				$(this).find("a").text("展开").attr("title","展开");
			}else{
				$(".lh-play-box").animate({"margin-right" : "0px"}, 400);
				$(".lh-right-wrap").animate({"right" : "-400px"}, 400);
				$(this).addClass("ocb-current");
				$(this).find("a").text("缩小").attr("title","缩小");
			}
		});
		
		
	}
	//选项卡公共方法
	function cardChange(oTitle, oCont, current) {
		var oTitle = $(oTitle),
			oCont = $(oCont),
			_index;
		oTitle.click(function() {
			_index = oTitle.index(this);
			$(this).addClass(current).siblings().removeClass(current);
			oCont.eq(_index).show().siblings().hide();
		}).eq(0).click();
	}
	//checkbox控件
	function oCheck() {
		//checkbox控件
		$(".inpCb").click(function() {
			if (!$(".inpCb>input").is(":checked")) {
				$(this).addClass("unforget");
			} else {
				$(this).removeClass("unforget");
			};
		});
	}
	
	//tree menu
	function treeMenu() {
		//一级目录
		$("#lh-menu>ul>li>a").click(function() {
			if ($(this).siblings("ol").is(":hidden")) {
				$(this).siblings("ol").slideDown(150);
				$(this).addClass("current-1");
				$(this).parent("li").siblings().children("ol").slideUp(150);
				$(this).parent("li").siblings().children("a:first").removeClass("current-1");
			} else {
				$(this).siblings("ol").slideUp(150);
				$(this).removeClass("current-1");
			};
		});
		//二级目录
		$(".lh-menu-ol>li>a").click(function() {
			if ($(this).siblings("dl").is(":hidden")) {
				$(this).siblings("dl").slideDown(120);
				$(this).addClass("current-2");
			} else {
				$(this).siblings("dl").slideUp(120);
				$(this).removeClass("current-2");
			};
		});
	}
	//无三级节点
	function noJd() {
		$(".lh-menu-ol>li").each(function() {
			var _this = $(this);
			if (_this.children("dl").length <= 0) {
				_this.children("a").addClass("no-jd-play");
			};
		});
	};
	function aboutCourse(){
		getCourse('xxpj',2);
	}
	function getCourse(className,type){
		$("#lh-r-li-course").removeClass("current");
		$("#lh-r-li-about").addClass("current");
		if(className=='xxpj'){//课程笔记
			if(currentKpointId!=null&&currentKpointId!=0){
				queryNote(currentCourseId,currentKpointId);
			}
		}else if(className=='gsbr'){//课程评论
			$(".assessajaxHtml").html("");
            if(currentKpointId!=null&&currentKpointId!=0){
                assessPage(currentKpointId);
            }
		}else if(className=='gswd'){//课程问答
			$("#problemUlListId").html("");
            if(currentKpointId!=null&&currentKpointId!=0){
                if(type==1){//精华互动
                    sugListPage(currentKpointId,'replycount');
                    $("#hudongSapnId1").removeClass("current");
                    $("#hudongSapnId2").addClass("current");
                }
                if(type==2){//互动答疑
                    sugListPage(currentKpointId,'addTime');
                    $("#hudongSapnId1").addClass("current");
                    $("#hudongSapnId2").removeClass("current");
                }
            }
		}
		
	}

function changeicurrent(){//目录切换方法
	$("#lh-r-li-course").addClass("current");
	$("#lh-r-li-about").removeClass("current");
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
 * 显示课程目录
 * @param courseId
 * @param kpointId 加载完后播放该节点
 */
function displaycourseTree(courseId,kpointId){
	currentCourseId=courseId;//记录全局当前课程id
	//初始化课程树
	var zNodes=getCourseTreeString(courseId);
	if( isNull(zNodes)){
		return ;
	}
	//先清空之前的
	$('ul[id^=courseTree_]').html('');
	$('#courseTree_'+courseId).prev().addClass("current-1").siblings().removeClass("current-1");
	$('#courseTree_'+courseId).addClass("ztUlOpen").siblings().removeClass("ztUlOpen");
	$.fn.zTree.init($("#courseTree_"+courseId), setting, zNodes);
	var tzree = $.fn.zTree.getZTreeObj("courseTree_"+courseId);
	var nodes = tzree.transformToArray(tzree.getNodes());
	//是否传来要播放的节点
	if(isNotNull(kpointId)){
		kpointId=parseInt(kpointId);
	}else{
		kpointId=0;
	}
	
	//处理显示播放次数(改为显示时长)
	for(var m=0;m<nodes.length;m++){
        if(nodes[m].isParent==false){
            //var name = nodes[m].name + '<b><em class="lh-p-icon icon14 mr5">&nbsp;</em>播放：' + nodes[m].playcount + '次</b>';
            var name = nodes[m].name + '<b><em class="lh-p-icon icon14 mr5">&nbsp;</em>' + nodes[m].courseMinutes + '分'+ nodes[m].courseSeconds + '秒</b>';
            nodes[m].name = name;
            tzree.updateNode(nodes[m]);
        }
	}
	
	//处理上一节和下一节,如果要播放currentKpointId，获取相应的位置，否则获取该课程的第一个可观看的节点播放
	for(var m=0;m<nodes.length;m++){
        if(isNotEmpty( nodes[m].videourl)){//可以播放
			if(kpointId>0){//判断是否是播放指定视频节点
				if(nodes[m].id==kpointId){
					currentKpointId=kpointId;
					initvoUrlObj=nodes[m];
					break;
				}
			}else{
				//得到树中第一个可以播放的视频------------------
				initvoUrlObj=nodes[m];
				currentKpointId=nodes[m].id;
				break;
			}
		}
	}
	
	tzree.selectNode(initvoUrlObj);//让播放的视频节点选中
	var prevoUrlObj=recursive(initvoUrlObj,-1);//上一个节点
	var nextvoUrlObj=recursive(initvoUrlObj,1);//下一个节点
	player(prevoUrlObj,initvoUrlObj,nextvoUrlObj);
}

/**
 * 递归获取上一个或者下一个节点
 * @param tzree
 * @param node 当前节点
 * @param type +1返回下一个,-1返回上一个
 */
function recursive(node,type){
	var resnode;
	if(type==1){//下一个
		resnode=node.getNextNode();
	}else if(type==-1){//上一个
		resnode=node.getPreNode();
	}
	if(isNull(resnode)){
		return resnode;
	}else if(resnode.isParent || isEmpty(resnode.videourl) ){//是父节点或者视频地址为空，继续获取
		return  recursive(resnode,type);
	}else{
		return resnode;
	}
	return resnode;
	
}
/**
 * 记录播放次数
 * @param courseId 课程id
 * @param kpointId 节点id
 */
function addPlayTimes(courseId,kpointId){
	$.ajax({
		url :  baselocation + "/couser/playertimes",
		data : {
			"kpointId" : kpointId,
			"courseId" : courseId
		},
		type : "post",
		dataType : "text",
		async:false,
		success : function(result) {
		}
	});
}