<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
    <script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>
    <script type="text/javascript">
        $(function(){
            $("#startTime").datetimepicker({
                regional:"zh-CN",
                changeMonth: true,
                dateFormat:"yy-mm-dd",
                timeFormat : 'HH:mm:ss',
                timeFormat : 'HH:mm:ss'
            });
        });

        //加载编辑器
        var editor;
        KindEditor.ready(function(K) {
            editor = K.create('textarea[id="content,textareaemail"]',{
                resizeType : 1,
                filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
                allowPreviewEmoticons : false,
                allowUpload : true,//允许上传
                syncType : 'auto',
                width : '720px',
                minWidth : '10px',
                minHeight : '10px',
                height : '300px',
                urlType : 'domain',//absolute
                newlineTag : 'br',//回车换行br|p
                uploadJson : '<%=keImageUploadUrl%>' + '&param=sendEmail',// 图片上传路径
                allowFileManager : false,
                afterBlur : function() {
                    editor.sync();
                },
                afterChange : function() {
                }
            });
        });

        function update(){
            var content = $("#content").val();
            var title = $("#title").val();

            if(title.trim()==''){
                alert('邮件标题不能为空！');
                return false;
            }
            if(content.trim()==''){
                alert('邮件内容不能为空！');
                return false;
            }

            if(confirm('确定发送?')==false){
                return false;
            }
            var startTime = $("#startTime").val();
            $.ajax({
                url : "${ctx}/admin/sendEmailMsg/update",
                data : {
                    "id" : '${userEmailMsg.id}',
                    "content" : content,
                    "title" : title,
                    "sendTime" : startTime
                },  // 参数
                type : "post",
                async : false,
                dataType : "json",  //返回json数据
                success:function (result){
                    alert(result.message);
                    if(result.message=='成功'){
                        window.location.href="/admin/user/sendEmaillist";
                    }
                }
            });
        }
    </script>
</head>
<body >


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>邮件管理</span> &gt; <span>邮件详情</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/user/updatePwd" method="post" id="addPaperForm">
	<input name="user.id" id="id" type="hidden" value="${user.id}"/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="2"><span>邮件详情<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;邮件标题</td>
				<td>
					<input  type="text" id="title" value="${userEmailMsg.title }"/>
				</td>
			</tr>
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;邮件类型</td>
                <td>
                    ${userEmailMsg.type==1?'普通':'定时' }
                </td>
            </tr>
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;是否发送</td>
                <td>
                    ${userEmailMsg.status==1?'已发送':'未发送' }
                </td>
            </tr>
            <c:if test="${userEmailMsg.type==2}">
            <tr>
                <td align="center"><font color="red">*</font>&nbsp;定时发送时间</td>
                <td>
                    <input type="text" class="" readonly="readonly" <c:if test="${userEmailMsg.status!=2&&userEmailMsg.type!=2 }">disabled="disabled"</c:if> value="<fmt:formatDate value="${userEmailMsg.sendTime}" type="both"/>" id="startTime" name="">

                </td>
            </tr>
            </c:if>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;邮件内容</td>
				<td>
					<textarea id="content" rows="6" cols="80" > ${userEmailMsg.content }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;创建时间</td>
				<td>
				
					<input type="text" disabled="disabled" value="<fmt:formatDate value="${userEmailMsg.createTime }" type="both"/>"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;发送人</td>
				<td>
					<input type="text" value="${userEmailMsg.loginName }" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;接收人</td>
				<td >
                    <textarea id="textareaemail" rows="6" cols="80" >${userEmailMsg.email}</textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
                    <c:if test="${userEmailMsg.type==2&&userEmailMsg.status==2}">
                        <a class="btn btn-danger" title="修 改" href="javascript:update()">修 改</a>
                    </c:if>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1)">返 回</a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	</div>
</div>
<!-- /tab4 end -->
</body>
</html>
