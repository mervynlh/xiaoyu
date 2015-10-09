<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <script type="text/javascript" src="/static/common/jquery-1.11.1.min.js"></script> 
	<script src="/static/common/mump3/player.js""></script>
	<script src="/static/common/mump3/player.min.js""></script>
	


<script>
	var player;
    $(function() {
		 // 初始化一个MuPlayer的实例。注意，我们默认使用了_mu全局命名空间。
	     player = new _mu.Player({
	        // baseDir是必填初始化参数，指向刚才签出的MuPlayer静态资源目录
	        baseDir: 'http://127.0.0.1/static/common/mump3/'
	    });
	    // 通过add方法添加需要播放的音频，并调用play方法开始播放。
	    player.add('http://127.0.0.1/static/common/mump3/mp3/3.mp3').play();
		
		
		//加载进度
		player.on('progress', function(progress) {
		    // progress为0 - 1以内的浮点数，表示当前音频资源的缓冲进度。
		    $("#jiaing").html("加载进度："+progress*100+"%");
		});
		//播放结束时停止定时器
		player.on('ended', function() {
			 window.clearInterval(timeid);
			 $("#playtimelong").html(player.duration(true));
   	 		 $("#timelong").html(player.duration(true)); 
		});
		
    });
    
    var timeid = window.setInterval('distime()',1000);
     
    //
    
</script>


<script >
   function distime(){
   	  $("#playtimelong").html(player.curPos(true));
   	  $("#timelong").html(player.duration(true));  
   }
   function replay(){
   		player.replay();
   }
   function pause(){
		player.pause();
   }
   function play(){
		player.play();
   }
   
</script>

</head>
<body>
<div class="mod">
    <div class="hd">播放列表及交互</div>
    <div class="bd">
        <ul id="playlist-demo">
    <li id="jiaing"></li>
            <li id="timelong"></li>
            <li id="playtimelong"></li>
        </ul>
    </div>
    <input type="button" onclick="replay()"  value="重新播放"/>
    
    <input type="button" onclick="pause()"  value="暂停"/>
    <input type="button" onclick="play()"  value="播放"/>	
    	
</div>





</body>
</html>