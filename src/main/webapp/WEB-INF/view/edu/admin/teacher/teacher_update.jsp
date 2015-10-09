<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改讲师</title>

<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
$(document).ready(function() {
initSimpleImageUpload("fileuploadButton","picpath","imagesUrl");
initSimpleImageUpload("card_fileuploadButton","card","card_imagesUrl");
initSimpleImageUpload("education_fileuploadButton","education","education_imagesUrl");
initSimpleImageUpload("teaching_fileuploadButton","teaching","teaching_imagesUrl");
initSimpleImageUpload("specialty_fileuploadButton","specialty","specialty_imagesUrl");
});
function updateTeacherFormSubmit(){
	/*if($("#teacherName").val()==''||$("#teacherName").val()==null){
		alert("请填写讲师名称");
		return ;
	}
	if($("#profession").val()==''||$("#profession").val()==null){
		alert("请填写教师专业");
		return ;
	}
	if($("#degree").val()==0||$("#degree").val()==null){
		alert("请选择教师学历");
		return ;
	}
	if($("#finishSchool").val()==''||$("#finishSchool").val()==null){
		alert("请填写毕业院校");
		return ;
	}
	if($("#seniority").val()==''||$("#seniority").val()==null){
		alert("请填写教师教龄");
		return ;
	}
	if(isNaN($("#seniority").val())){
		alert("教龄只能填写数字");
		return ;
	}*/
//	if($("#peculiarity").val()==''||$("#peculiarity").val()==null){
//		alert("请填写教师教学特点");
//		return ;
//	}
//	if($("#teacherCareer").val()==''||$("#teacherCareer").val()==null){
//		alert("请填写教师简介");
//		return ;
//	}
	if($("#soft").val()!=''&&$("#soft").val()!=null){
		if(isNaN($("#soft").val())){
			alert("排序值只能是数字");
			return ;
		}
	}
	
//	// 验证教授阶段方式
//	var teacherSubject = document.getElementsByName("teacher_subject");
//	var typenum = 0;
//	var subjectIds = '';
//	for (var i = 0; i < teacherSubject.length; i++) {
//		if (teacherSubject[i].checked == true) {
//			typenum++;
//			subjectIds += teacherSubject[i].value + ",";
//		}
//	}
//    //没有被选择
//    if(typenum <= 0){
//        alert("请选择您教授的阶段");
//        return;
//    } else {
//    	$("#teacher_subject").val(subjectIds);
//    }
// 	// 验证科目
//	var teacherMajor = document.getElementsByName("teacher_major");
//	var typenum = 0;
//	var majorIds = '';
//	for (var i = 0; i < teacherMajor.length; i++) {
//		if (teacherMajor[i].checked == true) {
//			typenum++;
//			majorIds += teacherMajor[i].value + ",";
//		}
//	}
//    //没有被选择
//    if(typenum <= 0){
//        alert("请选择您教授的阶段");
//        return;
//    } else {
//    	$("#teacher_major").val(majorIds);
//    }
//
//	if($("#imagesUrl").val()==''){
//		alert("请上传头像");
//		return ;
//	}
//	if($("#card_imagesUrl").val()==''){
//		alert("请上传身份证 图片");
//		return ;
//	}
//	if($("#education_imagesUrl").val()==''){
//		alert("请上传学历认证图片");
//		return ;
//	}
//	if($("#teaching_imagesUrl").val()==''){
//		alert("请上传教师证图片");
//		return ;
//	}
//	if($("#specialty_imagesUrl").val()==''){
//		alert("请上传专业资质认证图片");
//		return ;
//	}
	var isProfessor = $("[name='teacherisProfessor']:checked").val();
	$("#isProfessor").val(isProfessor);
	$("#updateTeacherForm").submit();
}
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=teacher',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');//absolute,domain
					K('#'+urlId+'').attr("src",'<%=staticImageServer%>' + data.url);
						$("#" + urlId).show();
						$('#' + valSet + '').val(url);
					} else {
						alert(data.message);
					}
				},
				afterError : function(str) {
					alert('自定义错误信息: ' + str);
				}
			});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
		});
	}
	
//加载编辑器
var editor;
KindEditor
		.ready(function(K) {
			editor = K
					.create(
							'textarea[id="peculiarity"]',
							{
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
								uploadJson : '//image.268xue.com/imgk4?base=projectName&param=teacher',//图片上传路径
								allowFileManager : false,
								/* afterFocus:function(){editor.sync();}, */
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

function aptitudeStatus(name, status){
	$("#" + name + "_status").val(status);
	$("[id^='" + name + "_aptitude']").hide();
	$("#" + name + "_" + status + "").show();
}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/update" method="post" id="updateTeacherForm">
			<input id="teacher_subject" type="hidden" name="teacher.teacherSubject" />
			<input id="teacher_major" type="hidden" name="teacher.teacherMajor" />
			<input type="hidden" name="teacher.id" value="${teacher.id}" /> 
			<input type="hidden" name="teacher.picPath" id="imagesUrl" value="${teacher.picPath}" /> 
			<input type="hidden" name="teacher.userId" id="userId" value="${teacher.userId}" /> 
			<input type="hidden" name="teacher.card" id="card_imagesUrl" value="${teacher.card}" /> 
			<input type="hidden" name="teacher.education" id="education_imagesUrl" value="${teacher.education}" /> 
			<input type="hidden" name="teacher.teaching" id="teaching_imagesUrl" value="${teacher.teaching}" />
			<input type="hidden" name="teacher.specialty" id="specialty_imagesUrl" value="${teacher.specialty}" />
			<input type="hidden" name="teacher.status" id="teacherStatus" value="${teacher.status}" />
			<input type="hidden" name="teacher.sex" id="teacherSex" value="${teacher.sex}" />

			<input type="hidden" name="teacher.cardStatus" id="card_status" value="${teacher.cardStatus}" />
			<input type="hidden" name="teacher.educationStatus" id="education_status" value="${teacher.educationStatus}" />
			<input type="hidden" name="teacher.teachingStatus" id="teaching_status" value="${teacher.teachingStatus}" />
			<input type="hidden" name="teacher.specialtyStatus" id="specialty_status" value="${teacher.specialtyStatus}" />

			<input id="hiddenIsProfessor" type="hidden" name="teacherisProfessor" value="${teacher.isProfessor}"/>
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>教师管理</span> &gt; <span>教师修改</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>教师基本属性<tt class="c_666 ml20 fsize12">
										（<font color="red">*</font>&nbsp;为必填项）
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;教师姓名</td>
							<td><input type="text" name="teacher.name" class="{required:true}" readonly="readonly" id="teacherName" value="${teacher.userExpand.showname}" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;手机号</td>
							<td><input type="text" name="teacher.userExpand.mobile" class="{required:true}" readonly="readonly" id="teacherMobile" value="${teacher.userExpand.mobile}" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;邮箱</td>
							<td><input type="text" name="teacher.userExpand.email" class="{required:true}" readonly="readonly" id="teacherEmail" value="${teacher.userExpand.email}" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>教师专业</td>
							<td width="80%"><input type="text" readonly="readonly" name="teacher.profession" style="width: 300px;" id="profession" class="{required:true}" value="${teacher.profession}" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;教师学历</td>
							<td><select name="teacher.degree" id="degree" readonly="readonly">
								<option value="0" <c:if test="${teacher.degree==0}">selected='selected'</c:if>>--请选择--</option>
								<option value="1" <c:if test="${teacher.degree==1}">selected='selected'</c:if>>高中以下</option>
								<option value="2" <c:if test="${teacher.degree==2}">selected='selected'</c:if>>高中或中专</option>
								<option value="3" <c:if test="${teacher.degree==3}">selected='selected'</c:if>>大专</option>
								<option value="4" <c:if test="${teacher.degree==4}">selected='selected'</c:if>>本科</option>
								<option value="5" <c:if test="${teacher.degree==5}">selected='selected'</c:if>>研究生</option>
								<option value="6" <c:if test="${teacher.degree==6}">selected='selected'</c:if>>博士及以上</option>
							</select></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>毕业院校</td>
							<td width="80%"><input type="text" name="teacher.finishSchool" style="width: 300px;" id="finishSchool" class="{required:true}" value="${teacher.finishSchool}" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>课程数量</td>
							<td width="80%"><input readonly="readonly" type="text" style="width: 300px;" class="{required:true}" value="${teacher.courseNum}" />个课程</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>常用地址</td>
							<td width="80%"><input readonly="readonly" type="text" style="width: 300px;" class="{required:true}" value="${teacher.cityName}${teacher.districtName}${teacher.address}" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>教&nbsp;&nbsp;&nbsp;龄</td>
							<td width="80%"><input readonly="readonly" type="text" name="teacher.seniority" style="width: 200px;" id="seniority" class="{required:true}" value="${teacher.seniority}" />&nbsp;&nbsp;年</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>教学特点</td>
							<td width="80%">
								<textarea readonly="readonly" name="teacher.peculiarity" style="width: 300px;" id="peculiarity">
								${teacher.peculiarity}
								</textarea>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>排序</td>
							<td width="80%"><input type="text" name="teacher.soft" style="width: 200px;" id="soft" class="{required:true}" value="${teacher.soft}" />&nbsp;&nbsp;<font color="red">(倒序)</font></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>教授阶段</td>
							<td width="80%">
								<c:forEach items="${subjectTwoList}" var="subject" varStatus="index">
									<span style="<c:if test='${index.index>0 }'>margin-left: 15px</c:if>" >
									<input type="checkbox" disabled="disabled" name="teacher_subject" value="${subject.subjectId}" ${subject.checkSelected == 'true' ? "checked='checked'" : ""}/>&nbsp;${subject.subjectName}
									</span>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>科&nbsp;&nbsp;&nbsp;&nbsp;目</td>
							<td width="80%">
								<c:forEach items="${majorList}" var="major" varStatus="index">
								<span style="<c:if test='${index.index>0 }'>margin-left: 15px</c:if>" >
									<input disabled="disabled" type="checkbox" name="teacher_major" value="${major.id}" ${major.checkSelected == 'true' ? "checked='checked'" : ""}/>&nbsp;${major.name}
								</span>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>韩教授认证</td>
							<td width="80%">
							<input type="radio" name="teacherisProfessor" value="1" ${teacher.isProfessor == 1 || teacher.isProfessor == 0 ? "checked='checked'" : ""}/>&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="teacherisProfessor" value="2" ${teacher.isProfessor == 2 ? "checked='checked'" : ""}/>&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${teacher.isProfessor == 3}">
								<input type="radio" disabled="disabled" name="teacherisProfessor" value="3" ${teacher.isProfessor == 3 ? "checked='checked'" : ""}/>&nbsp;申请认证
							</c:if>
							<input type="hidden" name="teacher.isProfessor" id="isProfessor"/>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;教师简介</td>
							<td><textarea rows="6" cols="80" name="teacher.career" id="teacherCareer" readonly="readonly" class="{required:true}">${teacher.career}</textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;头像</td>
							<td><img src="<%=staticImageServer %>${teacher.picPath}" alt="" id="picpath" width="400px" height="300px" />
								<input type="button" value="上传图片" id="fileuploadButton" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;身份证图片</td>
							<td>
								<img src="<%=staticImageServer %>${teacher.card}" alt="" id="card" width="400px" height="300px" />
								<input type="button" value="上传图片" id="card_fileuploadButton" />
								<c:if test="${teacher.cardStatus == 1}">
								<input type="button" class="tea-update-attestation-btn" value="审核-通过" id="card_aptitude_success" onclick="aptitudeStatus('card', 2)"/>
								<input type="button" class="tea-update-attestation-btn" value="审核-失败" id="card_aptitude_lose" onclick="aptitudeStatus('card', 3)"/>
									<input type="button" class="tea-update-attestation-btn" value="已通过" id="card_2" style="display:none"/>
									<input type="button" class="tea-update-attestation-btn" value="未通过" id="card_3" style="display:none"/>
								</c:if>
								<c:if test="${teacher.cardStatus == 2}">
									<input type="button" class="tea-update-attestation-btn" value="已通过"/>
								</c:if>
								<c:if test="${teacher.cardStatus == 3}">
									<input type="button" class="tea-update-attestation-btn" value="未通过"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;学历认证</td>
							<td>
								<img src="<%=staticImageServer %>${teacher.education}" alt="" id="education" width="400px" height="300px" />
								<input type="button" value="上传图片" id="education_fileuploadButton" />
								<c:if test="${teacher.educationStatus == 1}">
								<input type="button" value="审核-通过" class="tea-update-attestation-btn" id="education_aptitude_success" onclick="aptitudeStatus('education', 2)" />
								<input type="button" value="审核-失败" class="tea-update-attestation-btn" id="education_aptitude_lose" onclick="aptitudeStatus('education', 3)" />
									<input class="tea-update-attestation-btn" type="button" value="已通过" id="education_2" style="display:none"/>
									<input class="tea-update-attestation-btn" type="button" value="未通过" id="education_3" style="display:none"/>
								</c:if>
								<c:if test="${teacher.educationStatus == 2}">
									<input class="tea-update-attestation-btn" type="button" value="已通过"/>
								</c:if>
								<c:if test="${teacher.educationStatus == 3}">
									<input class="tea-update-attestation-btn" type="button" value="未通过"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;教师证认证</td>
							<td>
								<img src="<%=staticImageServer %>${teacher.teaching}" alt="" id="teaching" width="400px" height="300px" />
								<input type="button" value="上传图片" id="teaching_fileuploadButton" />
								<c:if test="${teacher.teachingStatus == 1}">
								<input type="button" value="审核-通过" class="tea-update-attestation-btn" id="teaching_aptitude_success" onclick="aptitudeStatus('teaching', 2)" />
								<input type="button" value="审核-失败" class="tea-update-attestation-btn" id="teaching_aptitude_lose" onclick="aptitudeStatus('teaching', 3)"/>
									<input class="tea-update-attestation-btn" type="button" value="已通过" id="teaching_2" style="display:none"/>
									<input class="tea-update-attestation-btn" type="button" value="未通过" id="teaching_3" style="display:none"/>
								</c:if>
								<c:if test="${teacher.teachingStatus == 2}">
									<input class="tea-update-attestation-btn" type="button" value="已通过" />
								</c:if>
								<c:if test="${teacher.teachingStatus == 3}">
									<input class="tea-update-attestation-btn" type="button" value="未通过" />
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;专业资质认证</td>
							<td>
								<img src="<%=staticImageServer %>${teacher.specialty}" alt="" id="specialty" width="400px" height="300px" />
								<input type="button" value="上传图片" id="specialty_fileuploadButton" />
								<c:if test="${teacher.specialtyStatus == 1}">
								<input type="button" value="审核-通过" class="tea-update-attestation-btn" id="specialty_aptitude_success" onclick="aptitudeStatus('specialty', 2)"/>
								<input type="button" value="审核-失败" class="tea-update-attestation-btn" id="specialty_aptitude_lose" onclick="aptitudeStatus('specialty', 3)"/>
									<input class="tea-update-attestation-btn" type="button" value="已通过" id="specialty_2" style="display:none"/>
									<input class="tea-update-attestation-btn" type="button" value="未通过" id="specialty_3" style="display:none"/>
								</c:if>
								<c:if test="${teacher.specialtyStatus == 2}">
									<input class="tea-update-attestation-btn" type="button" value="已通过"/>
								</c:if>
								<c:if test="${teacher.specialtyStatus == 3}">
									<input class="tea-update-attestation-btn" type="button" value="未通过"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:updateTeacherFormSubmit()">提 交</a> <a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
