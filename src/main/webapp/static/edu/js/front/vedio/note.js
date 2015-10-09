
$(function() {
    initKindEditornote();
});
//添加笔记
function addNotest() {
	var notesContext = $("#notesContextId").val();
	if (KindEditor.get("#notesContextId").isEmpty()) {
		$("#notContextId").show();
		$("#notesContextId").val('');
		dialog('提示', '请输入内容', 4);
		return false;
	} else {
		if (notesContext.length > 50000) {
			dialog('提示', '内容过长', 4);
            return false;
		}
		$.ajax({
			url : baselocation + "/front/addnote",
			type : 'post',
			dataType : 'json',
			data : {
				'kpointId' :currentKpointId,
                'courseId':currentCourseId,
				'content' : notesContext
			},
            async:false,
			success : function(result) {
				if (result.message == "success") {
					dialog('成功', '保存成功', 4);
				}
				if (result.message == "false") {
					dialog('失败', '保存失败，请刷新重试', 4);
				}
			}
		});
	}
}
//查询笔记
function queryNote(courseId,kpointId){
	$.ajax({
		url : baselocation+"/front/querynote",
		type : "post",
		dataType : "json",
		data:{'kpointId':kpointId,
        "courseId":courseId},
		success : function(result){
			if(result.courseNote!=undefined){
				KindEditor.html("#notesContextId", result.courseNote.content);
			}else{
				KindEditor.html("#notesContextId",'');
			}
		}
	});
}
/**
 * 笔记编辑器初始化
 */
function initKindEditornote(){//编辑器初始化
	KindEditor.create('textarea[id="notesContextId"]', {
		filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
		pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
		allowPreviewEmoticons : false,
		syncType : 'auto',
		width : '99%',
		minWidth : '10px',
		minHeight : '10px',
		height : '300px',
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		uploadJson : keImageUploadUrl+'&param=note',//图片上传路径
		allowFileManager : false,
		afterBlur : function() {
			this.sync();
		},
		items : [  'forecolor', 'emoticons'],
		afterChange : function() {
			$('#numweiBoContent').html(140 - this.count('text'));
			var num = 140 - this.count('text');
			
			if (num >= 0) {
				$(".error").html("您还可以输入"+num+"字");
			} else {
				$(".error").html("您输入的文字超过140字！");
				$('#wenzistrweiBoContent').html("你已经超过");
				num = -num;
			}
			$('#numweiBoContent').html(num);
		}
	});
};