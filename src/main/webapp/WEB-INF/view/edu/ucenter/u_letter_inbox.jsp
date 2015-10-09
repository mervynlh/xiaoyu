<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>

<head>
<title>系统消息</title>
<script type="text/javascript">
$(function(){
	selFun("#select-1");// 模拟 select 下拉控件
	var type = $("#hiddenMessageType").val();
	$("#meg_type").html($("#meg_type_" + type).text());
	if (type == 0) {
		$("#meg_type_0").hide();
	} else {
		$("#meg_type_0").show();
	}
})

//选择消息类型
function selectMessageType(str){
	$("#hiddenMessageType").val(str);
	search();
}
function search() {
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}

function isDeleteLetter(id){
	dialog("是否确认删除消息？",16,"","javascript:delULetter("+id+")");
}

function delULetter(id){//删除站内信
 	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delLetterInbox",
		data:{"msgReceive.id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+id).remove();
				dialog("删除成功！",17,"","/uc/letter");
			}
		}
	}); 
}
// 全选、取消全选
function allCheck(th) {
	$("input[name='field_name']:checkbox").prop('checked', th.checked);
}

// 批量删除
function batchDelMessage(){
	var messageIds = document.getElementsByName("field_name");
	var num = 0;
	var ids = '';
	for (var i = 0; i < messageIds.length; i++) {
		if (messageIds[i].checked == true) {
			num++;
			ids += messageIds[i].value + ",";
		}
	}
	$.ajax({
		url : "${ctx}/letter/delLetterInbox/batch",
		data : {
			"ids" : ids
		},
		type : "post",
		dataType : "json",
		success : function(result) {
			if (result.message == 'success') {
				dialog("操作成功",17,"","/uc/letter");
				$("[name='field_name']").prop('checked',false);
				window.location.reload();
			} else {
				dialog("操作失败",17,"","/uc/letter");
			}
		}
	});
}
//领取优惠券
function getCoupon(couponId){
	if(!isLogin()){
		dialog("您未登录，请先登录",10,"","");
		return;
	}
	$.ajax({
		url:baselocation+"/coupon/createcode",
		type:"post",
		data:{"couponId":couponId},
		dateType:"json",
		success:function(result){
			if(result.success){
				dialog("领取成功",8,"","");
			}else if(result.message=='isEmpty'){
				dialog("对不起,优惠券已经没有了",10,"","");
			}else if(result.message=='owned'){
				dialog("您已领取,请勿重复操作",10,"","");
			}else if(result.message=='youIsTeacher'){
				dialog("对不起,您是教师,无法领取优惠券",10,"","");
			}
		}
	});
}
</script>
</head>
<body>
<form id="searchForm" action="${ctx}/uc/letter" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
	<input type="hidden" id="hiddenMessageType" name="msgReceive.type" value="${msgReceive.type}"/>
 </form>
			<div class="u-m-right">
			<article class="u-m-center"> 
				<section class="u-m-c-wrap"> 
					<section class="u-m-c-head"> 
						<ul id="uTabTitle" class="fl u-m-c-h-txt of"> 
							<li class="current uHover">
								<a title="" onclick="" href="javascript:void(0)">消息</a>
							</li> 
						</ul> 
						<div class="clear"></div> 
					</section> 
					<div id="uTabCont" class="mt30"> 
						<article id="newWelcomeSellWayListUlId" style="display:block;">
							<section class="mb50"> 
								<div class="pl15 pr15" id="select-1"> 
									<section> 
										<ul class="u-sys-message"> 
											<li>
												<table width="100%" cellspacing="0" cellpadding="0" border="0">
													<thead>
														<tr> 
															<th width="30%" valign="top"> 
																<div class="selectUI">
																	<div style="width:145px;" class="job-select">
																		<section class="j-s-defalt">
																			<em class="icon14 fr mt5">&nbsp;</em>
																			<span class="job-select-xl" id="meg_type">消息类型</span>
																		</section>
																		<section class="j-s-option" style="display: none;">
																			<div class="j-s-o-box">
																				<p><a style="display:none" title="" href="javascript: void(0)" id="meg_type_0" onclick="selectMessageType(0)">消息类型</a></p>
																				<p><a title="" href="javascript: void(0)" id="meg_type_1" onclick="selectMessageType(1)">系统消息</a></p>
																				<p><a title="" href="javascript: void(0)" id="meg_type_2" onclick="selectMessageType(2)">上课通知</a></p>
																				<p><a title="" href="javascript: void(0)" id="meg_type_3" onclick="selectMessageType(3)">订单信息</a></p>
																				<p><a title="" href="javascript: void(0)" id="meg_type_4" onclick="selectMessageType(4)">评论信息</a></p>
																			</div>
																		</section>
																	</div>
																</div> 
															</th> 
															<th width="53%" valign="top" class="u-s-m-desc"> 
																<div class="mt5"> 
																	<p class="c-master"> 内容</p> 
																</div> 
															</th> 
															<th>
																<p class="c-master">操作</p>
															</th> 
														</tr> 
													</thead> 
												</table>
											</li>
											<c:if test="${empty queryLetterList }">
												<div class="u-T-body-in mt30">
													<div class="u-T-body-infor">
														<p class="c-master fsize24 f-fM tac">最近没有新消息</p>
													</div>
												</div>
											</c:if>
											<c:if test="${!empty queryLetterList && queryLetterList.size() > 0}">
											<c:forEach items="${queryLetterList }" var="letter">
											<li> 
												<table width="100%" cellspacing="0" cellpadding="0" border="0"> 
													<tbody> 
														<tr> 
															<td width="31%" valign="top"> 
																<div class="fl">
																	<label class="vam pt30 mr20">
																		<input type="checkbox" value="${letter.id }" name="field_name"> 
																	</label>
																</div>
																<div class="fl">
																	<p class="hLh20 of">
																		<tt class="c-red-1 fsize14 f-fM">
																			<c:if test="${letter.type == 1 }">系统消息</c:if>
																			<c:if test="${letter.type == 2 }">上课通知</c:if>
																			<c:if test="${letter.type == 3 }">订单信息</c:if>
																			<c:if test="${letter.type == 4 }">评论信息</c:if>
																		</tt>
																	</p> 
																	<p class="mt10">
																		<tt class="c-999 fsize12 f-fM">发送于:</tt>
																		<tt class="c-999 fsize12 f-fM ml5"><fmt:formatDate value="${letter.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></tt>
																	</p> 
																</div>
																<div class="clear"></div>
															</td> 
															<td width="55%" valign="top" class="u-s-m-desc"> 
																<div class="mt5"> 
																	<p class="c-666 fsize12 f-fM">${letter.content}</p> 
																</div> 
															</td> 
															<td>
																<span class="u-teaname-btn disIb vam">
																	<a href="javascript:void(0)" onclick="isDeleteLetter(${letter.id })" style="margin-bottom:0;">删除</a>
																</span>
															</td> 
														</tr> 
													</tbody> 
												</table> 
											</li>
											</c:forEach>
											</c:if>
										</ul> 
									</section>  
								</div>
								<c:if test="${not empty queryLetterList }">
								<div class="table-bottom mt10">
									<div class="">
										<span class="vam ml15">
											<label class="vam">
												<input type="checkbox" value="" onclick="allCheck(this)" name="field_name"> 
											</label>
										</span>
										<span class="c-666 fsize14 f-fM vam mr50">
												全选
										</span>
										<!-- <a class="fsize14 f-fM vam mr50" title="" href="">标记为已读</a> -->
										<a class="fsize14 f-fM vam" title="" href="javascript:void(0)" onclick="batchDelMessage()">删除</a>
									</div>	
								</div>
								</c:if>
							</section> 
						</article>
					</div>
					
					<!-- 公共分页开始 -->
					<section class="mt50">
						<div class="Paging tac">
						<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
						</div>
					</section>
					<!-- 公共分页结束 -->
					
				</section>   
			</article>
		</div>
</body>

