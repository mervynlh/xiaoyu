<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/base.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发送邮件消息</title>
    <link rel="stylesheet" type="text/css"
          href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
    <link rel="stylesheet" type="text/css"
          href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}"/>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript"
            src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-form.js"></script>
<script type="text/javascript">

        $(function () {
            $("#type").change(function () {
                if ($(this).val() == 2) {
                    $("#sendtime").show();
                } else {
                    $("#sendtime").hide();
                }
            });
            $("#startTime").datetimepicker({
                regional: "zh-CN",
                changeMonth: true,
                dateFormat: "yy-mm-dd",
                timeFormat: 'HH:mm:ss',
                timeFormat: 'HH:mm:ss'
            });
        });
        //加载编辑器
        var editor;
        KindEditor.ready(function (K) {
            editor = K.create('textarea[id="message"]', {
                resizeType: 1,
                filterMode: false,//true时过滤HTML代码，false时允许输入任何代码。
                allowPreviewEmoticons: false,
                allowUpload: true,//允许上传
                syncType: 'auto',
                width: '720px',
                minWidth: '10px',
                minHeight: '10px',
                height: '300px',
                urlType: 'domain',//absolute
                newlineTag: 'br',//回车换行br|p
                uploadJson : '<%=keImageUploadUrl%>' + '&param=sendEmail',// 图片上传路径
                allowFileManager: false,
                afterBlur: function () {
                    editor.sync();
                },
                afterChange: function () {
                }
            });
        });

        function sendmessage() {
            var sendLinkss = $("#pepole").val();
            if (sendLinkss.trim() == '') {
                alert('邮件接收人不能为空');
                return false;
            }
            var valL = sendLinkss.split(",").length - 1;
            var sendTitle = $("#title").val();
            if (sendTitle.trim() == '') {
                alert('邮件标题不能为空！');
                return false;
            }
            var sendInfo = $("#message").val();
            if (sendInfo.trim() == '') {
                alert('邮件内容不能为空！');
                return false;
            }

            if (confirm('确定发送?') == false) {
                return false;
            }
            var startTime = $("#startTime").val();
            var type = $("#type").val();
            $.ajax({
                url: "${ctx}/admin/user/sendEmailMsg",
                data: {
                    "linksman": sendLinkss,
                    "content": sendInfo,
                    "startTime": startTime,
                    "type": type,
                    "title": sendTitle
                },  // 参数
                type: "post",
                async: false,
                dataType: "json",  //返回json数据
                success: function (result) {
                    if (result.message == '发送成功'&&type==1) {
                        window.location.href = "/admin/user/progressbar?type=1";
                    } else {
                        alert(result.message);
                        window.location.href = "/admin/user/sendEmaillist";
                    }
                }
            });
        }
        //选择用户邮箱号
        function showNewwin() {
            window.open('${ctx}/admin/user/select_userlist/2', 'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
        }
        //显示 去重
        function addnewUserId(newUserPhoneArr) {
            var phoneIds = new Array();
            if ($("#pepole").val().trim() != "") {
                phoneIds = $("#pepole").val().split(",");
            }
            phoneIds = phoneIds.concat(newUserPhoneArr);
            phoneIds.sort();
            phoneIds = uniqueArray(phoneIds);
            $("#pepole").val(phoneIds);
        }
        function uniqueArray(a) {
            temp = new Array();
            for (var i = 0; i < a.length; i++) {
                if (!contains(temp, a[i])) {
                    temp.length += 1;
                    temp[temp.length - 1] = a[i];
                }
            }
            return temp;
        }
        function contains(a, e) {
            for (var j = 0; j < a.length; j++)if (a[j] == e)return true;
            return false;
        }

		//选择用户邮箱号
		function showNewwin(){
			window.open('${ctx}/admin/user/select_userlist/2','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
		} 
		//显示 去重
		function addnewUserId(newUserPhoneArr){
		    var phoneIds=new Array();
			if($("#pepole").val().trim()!=""){
				phoneIds=$("#pepole").val().split(",");
			}
			phoneIds = phoneIds.concat(newUserPhoneArr);  
			phoneIds.sort();
			phoneIds = uniqueArray(phoneIds);
			$("#pepole").val(phoneIds);
		}
		function uniqueArray(a){
		    temp = new Array();
		    for(var i = 0; i<a.length; i ++){
		        if(!contains(temp, a[i])){
		            temp.length+=1;
		            temp[temp.length-1] = a[i];
		        }
		    }
		    return temp;
		}
		function contains(a, e){
		    for(var j=0;j<a.length;j++)if(a[j]==e)return true;
		    return false;
		}
		
		function importExcel(){
			var myFile = $("#myFile").val();
			if(myFile.length <= 0){
			alert("请选择导入内容");
			return false;
			}
			$("#importP").submit();
		}
		
		//form 以ajax提交
		$(function() {  
	        $("#importP").submit(function(){  
	            $(this).ajaxSubmit({  
	                type:"post",  //提交方式  
	                dataType:"json", //数据类型  
	                url:"${ctx}/admin/user/importMsgExcel/2", //请求url  
	                success:function(result){ //提交成功的回调函数  
	                	if(result.success==true){
	                		alert("导入成功");
	                		$("#pepole").val(result.entity);
	                	}else{
	                		alert(result.message);
	                	}
	                }  
	            });  
	            return false; //不刷新页面  
	        });  
	    });  
</script>

</head>
<body>
<div class="mt20">
	<div class="commonWrap">
		<form action="/admin/user/importMsgExcel/2" method="post" id="importP" enctype="multipart/form-data">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>&nbsp;</caption>
					<thead>
						<tr>
							<th colspan="3" align="left"><span>发送邮件消息</span></th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<td width="20%"><font color="red">*</font>&nbsp;添加联系人</td>
							<td width="50%">
								<textarea name="numerStr" style="width: 80%; height: 200px;" id="pepole"></textarea><br />
								<font color="red">
											规则<br/>
											1、邮箱格式:********@qq.com  <br/>
											   或者********@126.com,********@163.com,********@sina.com...等<br/>
											2、发送流程：添加邮箱号-&gt;设置邮件内容&gt;提交发送<br/>
											3、群发邮件最多不能超过1000条<br/>
                                            4、定时邮件会有几分钟的延迟。
								</font>
							</td>
							<td></td>
							<td style="display: none">
								<a class="btn btn-danger" onclick="showNewwin()" title="添 加" href="javascript:void(0)">添 加</a><br/>
								<font color="red">
								批量导入&nbsp;&nbsp;&nbsp;&nbsp;<a href="/static/common/admin/masterplate/email.xls">示例模版</a><br />
								1、必须是excel格式,详情请参照模版sheet1<br/>
								2、格式不能有误<br/>
								3、记录要挨着输入，不能有空行<br/>
								</font>
								<input id="myFile" type="file" value="" name="myFile" /><input type="button" value="提交" class="btn btn-danger"  onclick="importExcel()"/> 
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;邮箱标题</td>
							<td>
								<input name="" type="text" style="width: 80%;" id="title" />
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;正文内容</td>
							<td>
								<textarea name="" style="width: 80%;height: 98px;" id="message"></textarea>
							</td>
							<td>&nbsp;</td>
						</tr>
                        <tr>
                            <td align="center"><font color="red">*</font>邮箱类型</td>
                            <td>
                                <select name="type" id="type">
                                    <option value="1">正常</option>
                                    <option value="2">定时</option>
                                </select>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    <tr class="undis" id="sendtime">
                        <td align="center"><font color="red">*</font>发送时间</td>
                        <td>
                            <input type="text" class="" readonly="readonly" value="" id="startTime" name="">
                        </td>
                        <td>&nbsp;</td>
                    </tr>
						<tr>
							<td colspan="3" align="center">
								<a href="javascript:void(0)" title="发 送" onclick="sendmessage()" class="btn btn-danger">发 送</a>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>