<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>主页评论管理</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js"></script>
	<script type="text/javascript">
		$(function(){
			uploadPicLoad('fileupload','imgs','imgUrl','fileQueue');
		});
		function save(){
			var content= $("#content").val();
			if(content==null || content==''){
				alert('请输入内容列表');
				return;
			}
			$.ajax({
				url:"${ctx}/admin/index/saveAssessFile",
				data:{"content":content},
				type:"post",
				dataType:"json",
				success:function(result){
					alert(result.message);
					if(result.success){
						window.location.reload();	
					}
				}
			});
		}
		//上传控件加载
		function uploadPicLoad(controlId,ids,showId,errId){
			$("#"+controlId).uploadify({
				'uploader' : '/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
				'script'  :'<%=uploadSwfUrl%>',  
				'scriptData':{"base":"mavendemo","param":"userFeedback"},
				'queueID' : 'fileQueue', //文件队列ID
				'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
				'auto' : true, //选定文件后是否自动上传
				'multi' :false, //是否允许同时上传多文件
				'hideButton' : false,//上传按钮的隐藏
				'buttonText' : 'Browse',//默认按钮的名字
				'buttonImg' : '/static/common/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
				'width' : 105,
				'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
				'sizeLimit' : 51200000,//控制上传文件的大小
				'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
				'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',//出现在上传对话框中的文件类型描述
				'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//支持的格式，启用本项时需同时声明fileDesc
				'folder' : '/upload',//您想将文件保存到的路径
				'cancelImg' : '/static/common/uploadify/cancel.png',
				onSelect : function(event, queueID,fileObj) {
					fileuploadIndex = 1;
					$("#"+errId).html("");
					if (fileObj.size > 51200000) {
						alert('文件太大最大限制51200kb');
						return false;
					}
				},
				onComplete : function(event,queueID, fileObj, response,data) {
					$("#"+ids).val(response);
					$("#"+showId).attr('src','<%=staticImageServer%>'+response);
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#"+errId).html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
				}
			});
		}
	</script>
</head>
<body  >
<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>主页评论管理</span> &gt; <span>主页评论管理</span> </h4>
</div>
<div class="mt20">
	<div class="commonWrap">
		<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>&nbsp;</caption>
					<thead>
						<tr>
							<th colspan="2" align="left"><span>主页评论管理</span></th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<td align="center" colspan="2"><font color="red">*</font>&nbsp;主页要显示的评论<br/>
							<font color="red">*</font>&nbsp;格式：{昵称,头像图片,评论的课程名,评论时间,内容}<br/>
							<font color="red">*</font>&nbsp;示例：{张三,/upload/eduplatform_268/default/avatar/1.jpg,刘老师初一语文,2015-09-11 12:00:00,讲课很生动，同学们很喜欢...}
							</td>
						</tr>
						<tr>
							<td align="center" style="width: 300px;">
								<font color="red vam ml10">请上传宽200、高200的图片</font> <br/>
								<input type="file" id="fileupload" class="vam" />
								<div class="ml10 mt10"> 
									<img id="imgUrl"  src="/static/edu/images/default/uploadDefaultPic.jpg" alt="" width="200" height="200"/>
									<br/>上传头像地址：<input type="text" id="imgs"/>
								</div>
								<div id="fileQueue" class="mt10"></div> 
							</td>
								<td align="center">
								<textarea name="" style="width: 100%; height: 500px"  id="content">${assessContent}</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<a href="javascript:void(0)" title="发 送" onclick="save()" class="btn btn-danger">保存</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	</div><!-- /commonWrap -->
</div>
<!-- /tab2 end-->
</body>
</html>