/**
 * Created by Administrator on 9/15 0015.
 */
$(function(){
    $("#fileupload_card").uploadify({
        'uploader' : '/static/common/uploadify/uploadify.swf',
        'script'  :uploadServerUrl + '/goswf',
        'scriptData':{"base":"mavendemo","param":"teacher"},
        'queueID' : 'fileQueue',
        'fileDataName' : 'fileupload',
        'auto' : true,
        'multi' : false,
        'hideButton' : false,
        'simUploadLimit' : 3,
        'buttonImg' : imagesPath + '/static/common/uploadify/bgUpload.png',
        'width' : 200,
        'height' : 30,
        'sizeLimit' : 51200000,
        'queueSizeLimit' : 2,
        'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
        'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
        'cancelImg' : imagesPath + '/static/common/uploadify/uploadify-cancel.png',
        onSelect : function(event, queueID,fileObj) {
            //$('#fileupload').uploadifyUpload();
            fileuploadIndex = 1;
            $("#fileQueue").html("");
            if (fileObj.size > 51200000) {
                alert("文件太大,最大上传51200kb");
                return;
            }
        },
        onComplete : function(event,queueID, fileObj, response,data) {
            $("#card_img").attr("src", staticImageServer + response);
            $("#card_imagesUrl").val(response);
            $("#card_img").show();
        }
    });

    $("#fileupload_education").uploadify({
        'uploader' : '/static/common/uploadify/uploadify.swf',
        'script'  :uploadServerUrl + '/goswf',
        'scriptData':{"base":"mavendemo","param":"teacher"},
        'queueID' : 'fileQueue',
        'fileDataName' : 'fileupload',
        'auto' : true,
        'multi' : false,
        'hideButton' : false,
        'simUploadLimit' : 3,
        'buttonImg' : imagesPath + '/static/common/uploadify/bgUpload.png',
        'width' : 200,
        'height' : 30,
        'sizeLimit' : 51200000,
        'queueSizeLimit' : 2,
        'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
        'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
        'cancelImg' : imagesPath + '/static/common/uploadify/uploadify-cancel.png',
        onSelect : function(event, queueID,fileObj) {
            //$('#fileupload').uploadifyUpload();
            fileuploadIndex = 1;
            $("#fileQueue").html("");
            if (fileObj.size > 51200000) {
                alert("文件太大,最大上传51200kb");
                return;
            }
        },
        onComplete : function(event,queueID, fileObj, response,data) {
            $("#education_img").attr("src", staticImageServer + response);
            $("#education_imagesUrl").val(response);
            $("#education_img").show();
        }
    });

    $("#fileupload_teaching").uploadify({
        'uploader' : '/static/common/uploadify/uploadify.swf',
        'script'  :uploadServerUrl + '/goswf',
        'scriptData':{"base":"mavendemo","param":"teacher"},
        'queueID' : 'fileQueue',
        'fileDataName' : 'fileupload',
        'auto' : true,
        'multi' : false,
        'hideButton' : false,
        'simUploadLimit' : 3,
        'buttonImg' : imagesPath + '/static/common/uploadify/bgUpload.png',
        'width' : 200,
        'height' : 30,
        'sizeLimit' : 51200000,
        'queueSizeLimit' : 2,
        'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
        'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
        'cancelImg' : imagesPath + '/static/common/uploadify/uploadify-cancel.png',
        onSelect : function(event, queueID,fileObj) {
            //$('#fileupload').uploadifyUpload();
            fileuploadIndex = 1;
            $("#fileQueue").html("");
            if (fileObj.size > 51200000) {
                alert("文件太大,最大上传51200kb");
                return;
            }
        },
        onComplete : function(event,queueID, fileObj, response,data) {
            $("#teaching_img").attr("src", staticImageServer + response);
            $("#teaching_imagesUrl").val(response);
            $("#teaching_img").show();
        }
    });

    $("#fileupload_specialty").uploadify({
        'uploader' : '/static/common/uploadify/uploadify.swf',
        'script'  :uploadServerUrl + '/goswf',
        'scriptData':{"base":"mavendemo","param":"teacher"},
        'queueID' : 'fileQueue',
        'fileDataName' : 'fileupload',
        'auto' : true,
        'multi' : false,
        'hideButton' : false,
        'simUploadLimit' : 3,
        'buttonImg' : imagesPath + '/static/common/uploadify/bgUpload.png',
        'width' : 200,
        'height' : 30,
        'sizeLimit' : 51200000,
        'queueSizeLimit' : 2,
        'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
        'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
        'cancelImg' : imagesPath + '/static/common/uploadify/uploadify-cancel.png',
        onSelect : function(event, queueID,fileObj) {
            //$('#fileupload').uploadifyUpload();
            fileuploadIndex = 1;
            $("#fileQueue").html("");
            if (fileObj.size > 51200000) {
                alert("文件太大,最大上传51200kb");
                return;
            }
        },
        onComplete : function(event,queueID, fileObj, response,data) {
            $("#specialty_img").attr("src", staticImageServer + response);
            $("#specialty_imagesUrl").val(response);
            $("#specialty_img").show();
        }
    });
})

// 认证/查看按钮点击事件
function onclickBtn(word, type){
    if (type == 'UP'){
        $("#" + word + "_attestation").hide();
        $("#btn_" + word).attr("onclick", "onclickBtn('" + word + "', 'DOWN')");
    } else {
        $("#" + word + "_attestation").show();
        $("#btn_" + word).attr("onclick", "onclickBtn('" + word + "', 'UP')");
    }
}

// 确认按钮点击事件
function onclickConfirm(word){
    var type = "";
    if(word == 'card'){
        type = "身份证";
    } else if(word == 'education'){
        type = "学历证";
    } else if(word == 'teaching'){
        type = "教师证";
    } else if(word == 'specialty'){
        type = "专业资质";
    }
    var card = $("#card_imagesUrl").val();
    if(card == null || card == ''){
        dialog("请上传您的身份证图片", 10, "", "");
        return;
    }
    $.ajax({
        url : baselocation + "/uc/ajax/teacher/aptitude/attestation",
        type : "post",
        data : {
            "teacher.card" : card,
            "teacher.education" : $("#education_imagesUrl").val(),
            "teacher.teaching" : $("#teaching_imagesUrl").val(),
            "teacher.specialty" : $('#specialty_imagesUrl').val(),
            "teacher.id" : $("#teacherId").val(),
            "teacher.userId" : $("#teacherUserId").val()
        },
        dataType : "json",
        success : function(result) {
            if(result.success == true){
                dialog(type + "认证修改成功！", 17, "", "javascript:window.location.reload()");
            } else if (result.message == 'cardIsNull'){
                dialog("请上传身份证图片", 9, "", "");
            } else if (result.message == 'error'){
                dialog("系统错误，请联系客服", 9, "", "");
            }
        }
    });
}