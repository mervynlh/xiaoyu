 window.onbeforeunload =  function(){ //页面关闭方法
		if(initvoUrlObj!=null&&(initvoUrlObj.voUrl==null||initvoUrlObj.voUrl.trim()=='')){
	    	addWatchTime(initvoUrlObj.courseId,initvoUrlObj.id);
		}
  };
  function initCCplayer(ccUserId,vid) {
	var swfobj = new SWFObject('http://union.bokecc.com/flash/player.swf','playerswf',"100%", "100%", '8');
  	swfobj.addVariable("userid",ccUserId ); // partnerID,用户id
  	swfobj.addVariable("videoid", vid); // spark_videoid,视频所拥有的 api id
  	swfobj.addVariable("mode", "api"); // mode, 注意：必须填写，否则无法播放
  	swfobj.addVariable("autostart", "true"); // 开始自动播放，true/false
  	swfobj.addVariable("jscontrol", "true"); // 开启js控制播放器，true/false
  	swfobj.addParam('allowFullscreen', 'true');
  	swfobj.addParam('allowScriptAccess', 'always');
  	swfobj.addParam('wmode', 'transparent');
  	swfobj.write('vedio');
  	$(".v-loading-gif").show();//播放器加载等待图片
  }

 
function get_position(){
	return document.getElementById("playerswf").spark_player_position();
}
function player_play() { //	调用播放器开始播放函数
	document.getElementById("playerswf").spark_player_start();
}
function player_pause() { //	调用播放器暂停函数
	document.getElementById("playerswf").spark_player_pause();
}
function player_resume() { //	调用播放器恢复播放函数
	document.getElementById("playerswf").spark_player_resume();
}

function on_spark_player_ready() {
	$(".v-loading-gif").hide();//播放器加载完毕等待图片隐藏
}

//-------------------
//调用者：flash
//功能：播放器开始播放时所调用函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
//-------------------
function on_spark_player_start() {
	addWatchTime(initvoUrlObj.courseId,initvoUrlObj.id);
	timeInterval = setInterval("addWatchTime("+initvoUrlObj.courseId+","+initvoUrlObj.id+")","60000");
}

//-------------------
//调用者：flash
//功能：播放器暂停时所调用函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
//-------------------
function on_spark_player_pause() {
}

//-------------------
//调用者：flash
//功能：播放器暂停后，继续播放时所调用函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
//-------------------
function on_spark_player_resume() {
	
}

//-------------------
//调用者：flash
//功能：播放器播放停止时所调用函数
//时间：2010-12-22
//说明：用户可以加入相应逻辑
function on_spark_player_stop() {
	
}


//记录观看时间
function addWatchTime(courseId,pointId){
    var watchTimeold=document.getElementById("playerswf")["spark_player_position"]();
   if(watchTime != watchTimeold){
   	 watchTime = document.getElementById("playerswf")["spark_player_position"]();
   	 var subjectId = initvoUrlObj.subjectId;
   	 $.ajax({
   			url : baselocation + "/cou/courselimit!addWatchTime.action",
   			type : "post",
   			data : {
   				"watchTime" : watchTime,
   				"course.courseId":courseId,
   				"course.subjectId":subjectId,
   				"kpoint.pointId":pointId,
   				"sellWay.sellId":sellid
   			},
   			async: false,
   			success : function(result) {
   			},
   			error : function(error) {
   			}
   		});
   } 
};


