$(function(){
	queryUnReadNum();
});
function queryUnReadNum(){//查询未读消息
	if(!isLogin()){
		return;
	}
	$.ajax({
		type : "POST",
		dataType : "json",
		url:baselocation+"/letter/queryUnReadLetter",
		cache : true,
		async : false,
		success : function(result) {
			var letter = result.entity;
			if(letter==null){
				return;
			}
			//未读系统消息数
			var systemNum = letter.SMNum;
			//未读站内信数
			var letterNum = letter.mNum;
			//未读粉丝数
			var unreadFansNum = letter.unreadFansNum;
			unReadNum = letter.unReadNum;
			if(systemNum!=0){
				$("#msgCountId").removeClass("undis");
				//$("#msgCountId").before('<span class="gt-mail-num" title="'+unReadNum+'条未读消息"></span>');
			}
			if(systemNum>0){
				$("#msgCountId").attr("title",systemNum+"条未读消息");
				$("#msgCountId").html(systemNum);
			}else{
				$("#msgCountId").addClass("undis");
			}
		}
	});
}