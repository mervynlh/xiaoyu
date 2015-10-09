$(function() {
    initcourseAss();
});
// 课程评价区分页
function assessPage(id) {
	ajaxPage("/front/ajax/assesskpoint", "&kpointId=" + id, 1, callBack);
}
// ajax回调方法
function callBack(result) {
	$(".lh-list-more").remove();
	$(".assessajaxHtml").html(result);
}

// 添加评论
function addAssess() {
	var assessInofContext = $("#assessInofContextId").val();
	if (assessInofContext == null || assessInofContext.trim() == '') {
		$("#notAssessInfoId").show();
		$("#assessInofContextId").val('');
        KindEditor.html("#assessInofContextId", "");
		return false;
	} else {
		var pointId =currentKpointId;// 视频节点ID
		$.ajax({
			url : baselocation + "/front/addassess",
			type : "post",
			dataType : "json",
			data : {
				"courseId" : currentCourseId,
				"kpointId" : pointId,
				"content" : assessInofContext
			},
			success : function(result) {
				if (result.success) {
					assessPage(pointId);
					$("#assessInofContextId").val('');
                    KindEditor.html("#assessInofContextId", "");
				} else {
					dialog('失败', '保存失败，请刷新重试', 4);
				}
			}
		});
	}
}

/**
 * 评论编辑器初始化
 */
function initcourseAss(){//编辑器初始化
    KindEditor.create('textarea[id="assessInofContextId"]', {
        resizeType:0,
        filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
        pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
        allowPreviewEmoticons : false,
        syncType : 'auto',
        width : '99%',
        minWidth : '10px',
        minHeight : '10px',
        height : '130px',
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
};
