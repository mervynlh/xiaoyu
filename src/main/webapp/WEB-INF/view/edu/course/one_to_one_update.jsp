<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>教师一对一课程</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/front/course/one_to_one_update.js"></script>
	<script type="text/javascript">
		$(function(){
			$("input:checkbox").on("click",function(){
				if(!$(this).is(':checked')){
					$(this).val($(this).attr('lang'));
					$(this).nextAll(":input").val("");
					$(this).nextAll(":input").attr("disabled","disabled");
				}else{
					$(this).nextAll(":input").removeAttr("disabled");
				}
			});
			
			//将上课方式的价格填入页面
			<c:forEach items="${oneToOne.courseModelMap}" var="model">
				$("[name='course.courseModel']").each(function(){
					if($.trim($(this).val())==$.trim('${model.key}')){
						$(this).attr("checked","checked");
						$(this).val('${model.key}:${model.value}');
						$(this).nextAll(":input").val("${model.value}");
						$(this).nextAll(":input").removeAttr("disabled");
					}
				});
			</c:forEach>
			
			//专业处理
			var subjectId='${subjectId}';
			$("#"+subjectId+"").click();
			$("#major${oneToOne.subjectMajor.id}").click();
		});
	</script>
</head>
<body>
<form action="" id="submitForm">
<input type="hidden" name="course.id" value="${oneToOne.id}"/>
<input id="subjectMajorId" type="hidden" name="course.subjectMajorId" value=""/>
<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap">
					<section class="ml50">
						<div class="u-one-tit">
							<p class="fsize18 c-666 f-fM">
								请选择课程分类
							</p>
							<div id="select-1" class="mt20">
								<div class="selectUI fl">
									<div class="job-select" style="width:145px;">
										<section class="j-s-defalt">
											<em class="icon14 fr mt5">&nbsp;</em>
											<span id="subjectTip">请选择阶段</span>
										</section>
										<section style="display: none;" class="j-s-option">
											<div class="j-s-o-box">
												<c:forEach items="${teacherSubject}" var="sub">
													<p><a id="${sub.subjectId}" href="javascript: void(0)" onclick="getMajor(${sub.subjectId})" class="sub" title="${sub.subjectName}">${sub.subjectName}</a></p>
												</c:forEach>
											</div>
										</section>
									</div>
								</div>
								<div class="selectUI fl">
									<div style="width:145px;" class="job-select">
										<section class="j-s-defalt" id="majorTip">
											<em class="icon14 fr mt5">&nbsp;</em>
											<span>请选择科目</span>
										</section>
										<section class="j-s-option">
											<div class="j-s-o-box" id="majorOption"></div>
										</section>
									</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="u-one-boy mt50">
							<p class="fsize18 c-666 f-fM">
								选择授课方式及设定课时费
							</p>
							<div class="u-one-boy-in mt30">
								<ol class="tjyhj-bot">
									<li class="ml50">
										<label class="vam tjyhj">
											<input type="checkbox" name="course.courseModel" class="vam" style="width:14px;border:none;" placeholder="" value="TEACHERVISIT" lang="TEACHERVISIT">
											<tt class="fsize14 f-fM c-666 vam">老师上门</tt>
											<tt class="fsize14 f-fM c-org ml30 vam">￥</tt>
											<input type="text" value="" disabled="disabled" onkeyup="onlyNumber(this, 2)" onblur="setMoney(this)">
											<tt class="fsize14 f-fM c-666 vam">元/每课时</tt>
										</label>
									</li>
									<li class="ml50">
										<label class="vam tjyhj">
											<input type="checkbox" name="course.courseModel" class="vam" style="width:14px;border:none;" placeholder="" value="TALKADDRESS" lang="TALKADDRESS">
											<tt class="fsize14 f-fM c-666 vam">协商地点</tt>
											<tt class="fsize14 f-fM c-org ml30 vam">￥</tt>
											<input type="text" value="" disabled="disabled" onkeyup="onlyNumber(this, 2)" onblur="setMoney(this)">
											<tt class="fsize14 f-fM c-666 vam">元/每课时</tt>
										</label>
									</li>
									<li class="ml50">
										<label class="vam tjyhj">
											<input type="checkbox" name="course.courseModel" class="vam" style="width:14px;border:none;" placeholder="" value="STUDENTVISIT" lang="STUDENTVISIT">
											<tt class="fsize14 f-fM c-666 vam">学生上门</tt>
											<tt class="fsize14 f-fM c-org ml30 vam">￥</tt>
											<input type="text" value="" disabled="disabled" onkeyup="onlyNumber(this, 2)" onblur="setMoney(this)">
											<tt class="fsize14 f-fM c-666 vam">元/每课时</tt>
										</label>
									</li>
									<li class="ml50">
										<label class="vam tjyhj">
											<input type="checkbox" name="course.courseModel" class="vam" style="width:14px;border:none;" placeholder="" value="ONLINEOTO" lang="ONLINEOTO">
											<tt class="fsize14 f-fM c-666 vam">在线授课</tt>
											<tt class="fsize14 f-fM c-org ml30 vam">￥</tt>
											<input type="text" value="" disabled="disabled" onkeyup="onlyNumber(this, 2)" onblur="setMoney(this)">
											<tt class="fsize14 f-fM c-666 vam">元/每课时</tt>
										</label>
									</li>
									<li class="pt10 tac">
										<a class="u-btn u-xgsksj-btn u-xgsksj-btn-current" href="javascript:save()">保存</a>
										<a class="u-btn u-xgsksj-btn" href="${ctx}/uc/teacher/ontToOne/list">取消</a>
									</li>
								</ol>
							</div>
						</div>
						<div class="u-one--txt mt50">
							<p class="fsize18 c-666 f-fM">
								如何设定课时费
							</p>
							<p class="fsize14 c-666 f-fM mt20">
								1.建议教师根据自己所在的线下价格作为参照，以低于同地区线下加个的10%-30%作为定价的原则。
							</p>
							<p class="fsize14 c-666 f-fM mt20">
								2.建议每个教师根据自身的教学经验、教学能力、教学效果、市场的接受等，确定合适的价格，只有合适的加个才能为自己创造最好的结果。
							</p>
						</div>
					</section>
				</section>   
			</article>
		</div>
</form>
		<script src="/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				selFun("#select-1");// 模拟 select 下拉控件
			});
		</script>
</body>
</html>
