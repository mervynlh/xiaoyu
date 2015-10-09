<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>新建课程</title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/js/jQueryValidate/jquery.validate.errorStyle.css?v=${v}" />
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/localization/messages_zh.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-validation-1.11.1/dist/additional-methods.min.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=1410957986989"></script>

<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/zTreeStyle.css?v=${v}" type="text/css" />
<link rel="stylesheet" href="${ctximg}/static/common/ztree/css/demo.css?v=${v}" type="text/css" />
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.core-3.5.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/ztree/jquery.ztree.exedit-3.5.js?v=${v}"></script>
<link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css"/>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
<script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>

<script type="text/javascript">




//subject ztree start
var subject_setting = {
	view:{
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'subjectId',
			pIdKey:'parentId',
			rootPid:''
		},
		key:{
			name:'subjectName',
			title:'subjectName'
		}
	},
	callback: {
		onClick: subject_treeOnclick
	}
};
var subject_treedata=${subjectList};

//切换专业时操作
function subject_treeOnclick(e,treeId, treeNode) {
	hideSubjectMenu();
	$("#subjectId").val(treeNode.subjectId);
	$("#subjectNameBtn").val(treeNode.subjectName);
}

//清空专业的查询条件时同时清除考点
function subject_cleantreevalue(){
	hideSubjectMenu();
	$("#subjectId").val(0);
	$("#subjectNameBtn").val("");
}
function showSubjectMenu() {
	var cityObj = $("#subjectNameBtn");
	var cityOffset = $("#subjectNameBtn").offset();
	$("#subjectmenuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideSubjectMenu() {
	$("#subjectmenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "subjectNameBtn" || event.target.id == "subjectmenuContent" || $(event.target).parents("#subjectmenuContent").length>0)) {
		hideSubjectMenu();
	}
}

$().ready(function() {
	$.fn.zTree.init($("#subject_ztreedemo"), subject_setting, subject_treedata);
	//专业名称显示
	if($("#subjectId").val()!="" && $("#subjectId").val()!=0){
		var zTree = $.fn.zTree.getZTreeObj("subject_ztreedemo");
		var treeNode=zTree.getNodeByParam("subjectId",$("#subjectId").val(),null);
		if(treeNode!=null){
			$("#subjectNameBtn").val(treeNode.subjectName);
		}
	}
});
//类型change
function sellTypeChange(){
    $("#sellType").change(function() {
        $(".showLive").hide();
        if ($(this).val() == 'LIVE') {
            $(".showLive").show();
        }
    });
    $("#sellType").change();
}
	$(function() {

        var sellType =getParameter("sellType");

        if(sellType!=null && sellType !=""){
            if(sellType=='COURSE' || sellType=='PACKAGE' ||sellType=='LIVE'){
                $("#sellType").val(sellType);
            }else{
                $("#sellType").val('COURSE');
            }
        }

        //类型
        sellTypeChange();
        //时间插件
        $("#liveBeginTime,#liveEndTime").datetimepicker(
                {regional:"zh-CN",
                    changeMonth: true,
                    dateFormat:"yy-mm-dd ",
                    timeFormat: "HH:mm:ss"
                });

		datepicker("#loseAbsTime");
		$("#subjectIdOne").change();
		$("#addCourse").validate();
		querytecher();
		losetypeShow();
		$("#losetype").change();
		
		$("#fileupload").uploadify({
			'uploader' : '${ctximg}/static/common/uploadify/uploadify.swf',
		  'script'  :'<%=uploadServerUrl%>/goswf',
                  'scriptData':{"base":"mavendemo","param":"course"},
			'queueID' : 'fileQueue',
			'fileDataName' : 'fileupload',
			'auto' : true,
			'multi' : false,
			'hideButton' : false,
			'buttonText' : 'Browse',
			'buttonImg' : '${ctximg}/static/common/uploadify/liulan.png',
			'width' : 105,
			'simUploadLimit' : 3,
			'sizeLimit' : 51200000,
			'queueSizeLimit' : 2,
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'folder' : '/upload',
			'cancelImg' : '${ctximg}/static/common/uploadify/cancel.png',
			onSelect : function(event, queueID,
					fileObj) {
				// $('#fileupload').uploadifyUpload();
				fileuploadIndex = 1;
				$("#fileQueue").html("");
				if (fileObj.size > 51200000) {
					alert("文件太大最大上传51200kb");
					return;
				}

			},
			onComplete : function(event,queueID, fileObj, response,data) {
				$("#imgUrl").attr("src","<%=staticImageServer%>" + response);
								$("#logo").val(response);
								$("#imgUrl").show();
				},
				onError : function(event, queueID, fileObj,errorObj) {
					$("#fileQueue").html(
							"<br/><font color='red'>"
									+ fileObj.name + "上传失败</font>");
				}
		});
		$("#mobile_fileupload").uploadify({
			'uploader' : '${ctximg}/static/common/uploadify/uploadify.swf',
		 	'script'  :'<%=uploadServerUrl%>/goswf',
	        'scriptData':{"base":"mavendemo","param":"course"},
			'queueID' : 'mobile_fileQueue',
			'fileDataName' : 'fileupload',
			'auto' : true,
			'multi' : false,
			'hideButton' : false,
			'buttonText' : 'Browse',
			'buttonImg' : '${ctximg}/static/common/uploadify/liulan.png',
			'width' : 105,
			'simUploadLimit' : 3,
			'sizeLimit' : 51200000,
			'queueSizeLimit' : 2,
			'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
			'folder' : '/upload',
			'cancelImg' : '${ctximg}/static/common/uploadify/cancel.png',
			onSelect : function(event, queueID,fileObj) {
				// $('#fileupload').uploadifyUpload();
				fileuploadIndex = 1;
				$("#mobile_fileQueue").html("");
				if (fileObj.size > 51200000) {
					alert("文件太大最大上传51200kb");
					return;
				}

			},
			onComplete : function(event,
					queueID, fileObj, response,
					data) {
								$("#mobile_imgUrl").attr("src","<%=staticImageServer%>" + response);
								$("#mobile_logo").val(response);
								$("#mobile_imgUrl").show();
							},
							onError : function(event, queueID, fileObj,
									errorObj) {
								$("#mobile_fileQueue").html(
										"<br/><font color='red'>"
												+ fileObj.name + "上传失败</font>");
							}
						});

	});
	
	//加载编辑器
	var editor;
	KindEditor.ready(function(K) {
				editor = K.create('textarea[id="context"]',{
					resizeType : 1,
					filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
					allowPreviewEmoticons : false,
					allowUpload : true,//允许上传
					syncType : 'auto',
					width : '620px',
					minWidth : '10px',
					minHeight : '10px',
					height : '300px',
					urlType : 'domain',//absolute
					newlineTag : 'br',//回车换行br|p
					uploadJson : '<%=keImageUploadUrl%>' + '&param=course',// 图片上传路径
					allowFileManager : false,
					afterBlur : function() {
						editor.sync();
					},
					items : [ 'fontname', 'fontsize', '|',
							'forecolor', 'hilitecolor', 'bold',
							'italic', 'underline',
							'removeformat', '|', 'justifyleft',
							'justifycenter', 'justifyright',
							'insertorderedlist',
							'insertunorderedlist', '|',
							'emoticons', 'image', 'link' ],
					afterChange : function() {
					}
				});
			});
	function addCourseFormSubmit() {
		
		
		if($("#subjectId").val()=="0"){
			alert("请选择专业");
			return;
		}
		if($("#tcIds").val()==""){
			alert("请添加教师");
			return;
		}
		
		if($("#logo").val()==""){
			alert("请上传课程图片");
			return;
		}
		$("#addCourse").submit();
	}

	//时间类型change事件
	function losetypeShow() {
		$("#losetype").change(function() {
			$(".loseTimeShow").hide();
			$(".loseAbsTimeShow").hide();
			if ($(this).val() == 1) {
				$(".loseTimeShow").show();
				$("#loseAbsTime").val('');
			}
			if ($(this).val() == 0) {
				$(".loseAbsTimeShow").show();
				$("#loseTime").val('');
			}
		});
	}
	
	//选择教师
	function showNewwin(){
		window.open('${ctx}/admin/teacher/selectlist','newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=200,left=300,width=800,height=600');
	} 
	function addnewTeacherId(newTcIdArr){
		var tcIds = $("#tcIds").val().split(",");
		tcIds = tcIds.concat(newTcIdArr);  
		tcIds = tcIds.unique();
		$("#tcIds").val(tcIds);
		querytecher();
	}

	function querytecher(){
		var ids = $("#tcIds").val();
		if(ids!=null&&ids!=""){
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "${ctx}/admin/teacher/queryByIds",
				data : {
					"ids" : ids
				},
				async : false,
				success : function(result) {
					if (result.success == true) {
						var str = "";
						var teacherList = result.entity;
						for(var i=0;i<teacherList.length;i++){
							var tc = teacherList[i];
							str+='<p style="width:100%;margin: 0 0 0em">'+tc.name+'&nbsp;&nbsp;<a onclick="delTeacher('+tc.id+')" class="btn smallbtn btn-y" href="javascript:void(0)"> 删除</a></p>';
							}
						$("#tchtml").html(str);
					} 
				}
			});
		}else{
			$("#tchtml").html("");
		}
	}
	//删除老师
	function delTeacher(id){
		var tcIds = $("#tcIds").val();
		var pattern = id+"";
		tcIds = tcIds.replace(new RegExp(pattern), "");
		tcIds = tcIds.split(",").unique();
		$("#tcIds").val(tcIds);
		querytecher();
	}
	
	function clearTeacher(){
		$("#tcIds").val("");
		$("#tchtml").html("");
	}
</script>
</head>
<body >
		<form action="${ctx}/admin/cou/addCourse" method="post" id="addCourse">
		<input id="tcIds" type="hidden" name="course.teacherIds" value="" />
			<input name="course.subjectId" id="subjectId" type="hidden" value="0"/>
			<input name="course.logo" id="logo" type="hidden" />
			<input name="course.mobileLogo" id="mobile_logo" type="hidden" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em> &nbsp; <span>课程管理</span> &gt; <span>新建课程</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
			<div class="commonWrap">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span> 课程基本属性 <tt class="c_666 ml20 fsize12">
										（ <font color="red">*</font> &nbsp;为必填项）
									</tt>
							</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;课程名称</td>
							<td><input type="text" name="course.name" class="required "  /></td>
						</tr>
                        <c:if test="${salemap.sale.verifyMember=='ON'}">
						<tr>
							<td align="center"> &nbsp;会员类型</td>
							<td>
								<c:forEach items="${memberTypes }" var="memberType">
									<input type="checkbox" name="courseMember" value="${memberType.id }"/>${memberType.title }&nbsp;&nbsp;
								</c:forEach>

							</td>
						</tr>
                        </c:if>
                        <input type="hidden" name="courseMember" value=""/>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;销售形式</td>
							<td><select name="course.sellType" id="sellType">
									<option value="COURSE">课程</option>
									<option value="PACKAGE">套餐</option>
                                    <option value="LIVE">直播</option>
							</select></td>
						</tr>
                      <tr class="showLive">
                            <td width="20%" align="center"><font color="red">*</font> &nbsp;直播开始时间</td>
                            <td><input id="liveBeginTime" type="text" name="course.liveBeginTime" readonly="readonly" class="required " value="" /></td>
                        </tr>
                        <tr class="showLive">
                           <td width="20%" align="center"><font color="red">*</font> &nbsp;直播结束时间</td>
                           <td><input id="liveEndTime" type="text" name="course.liveEndTime" readonly="readonly" class="required " value="" /></td>
                       </tr>
                        <tr class="showLive">
                            <td width="20%" align="center"><font color="red">*</font> &nbsp;直播编码</td>
                            <td><input id="freeurl" type="text" name="course.freeurl" class="" value="" /></td>
                        </tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font> &nbsp;专业分类</td>
							<td width="80%">
								<input id="subjectNameBtn" type="text" readonly="readonly" value="" style="width:120px;" onclick="showSubjectMenu()"/>
								<div id="subjectmenuContent" class="menuContent" style="display:none; position: absolute;">
									<ul id="subject_ztreedemo" class="ztree" style="margin-top:0; width:160px;"></ul>
								</div>
								</td> 
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font> &nbsp;课程收费</td>
							<td width="80%">
								<input type="radio" value="0" name="course.isPay" checked="checked"/>免费
								<input type="radio" value="1" name="course.isPay"/>收费
							</td>
						</tr>
						<tr>
							<td width="20%" align="center">课程原价格</td>
							<td width="80%"><input type="text" name="course.sourceprice" class="required number" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;课程销售价格</td>
							<td><input type="text" name="course.currentprice" class="required number" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;添加教师</td>
							<td>
							<div id="tchtml"></div>
							<a href="javascript:showNewwin()" title="添加教师" class="ml10 btn smallbtn btn-y">添加教师</a>
							<a href="javascript:clearTeacher()" title="清空" class="ml10 btn smallbtn btn-y">清空</a>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;课程简介</td>
							<td><textarea rows="2" cols="82" name="course.title" maxlength="200" class="required "></textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;课程详情</td>
							<td><textarea id="context" rows="5" cols="70" name="course.context" required ></textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;上架状态</td>
							<td><select name="course.isavaliable" id="isavaliable">
									<option value="0">上架</option>
									<option value="1">下架</option>
							</select></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程图片(400*300)</td>
							<td>
								<input type="file" id="fileupload" class="vam" />
								<font color="red vam ml10">请上传宽400、高300的图片</font> 
								<div class="ml10 mt10"> 
									<img src="" alt="" width="400" height="300" style="display: none;" id="imgUrl" />
								</div>
								<div id="fileQueue" class="mt10"></div> 
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;微站课程图片(380*180)</td>
							<td>
								<input type="file" id="mobile_fileupload" class="vam" />
								<font color="red vam ml10">请上传宽380、高180的图片</font> 
								<div class="ml10 mt10"> 
									<img src="" alt="" width="380" height="180" style="display: none;" id="mobile_imgUrl" />
								</div>
								<div id="mobile_fileQueue" class="mt10"></div> 
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;总课时</td>
							<td><input type="text" name="course.lessionnum" class="required number" /></td>
						</tr>

						<tr>
							<td align="center"><font color="red">*</font> &nbsp;有效期类型</td>
							<td><select name="course.losetype" id="losetype">
									<option value="1">按天数</option>
									<option value="0">到期时间</option>
							</select></td>
						</tr>
						<tr class="loseTimeShow">
							<td align="center"><font color="red">*</font> &nbsp;按天数</td>
							<td><input type="text" name="course.loseTime" id="loseTime" class="required number"/>天</td>
						</tr>
						<tr class="loseAbsTimeShow">
							<td align="center"><font color="red">*</font> &nbsp;到期时间</td>
							<td><input type="text" id="loseAbsTime" readonly="readonly" name="course.loseAbsTime" class="required"/></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;初始化假的销售数量</td>
							<td><input type="text" name="course.pageBuycount" class="required number" value="0" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font> &nbsp;初始化假的浏览数量</td>
							<td><input type="text" name="course.pageViewcount" class="required number" value="0" /></td>
						</tr>
						<!-- <tr>
							<td align="center">&nbsp;试听地址</td>
							<td><input type="text" name="course.freeurl" /></td>
						</tr> -->
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:addCourseFormSubmit()">提 交</a> <a
								class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
