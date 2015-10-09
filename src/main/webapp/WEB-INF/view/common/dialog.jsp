<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:choose>
	<c:when test="${dialog.index==0}"><!--快速登录弹出框 -->
		<div class="d-tips-0">
			<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">用户登录</span></h4>
			<div class="mt40 mb40 pl90 pr90">
			<div style=""><ul class="l-r-w-Inpt">
			<li><p id="requestErrorID"></p></li>			
			<li class=""><label class="vam">用&nbsp;户&nbsp;名：</label><input type="text" class="lTxt" maxlength="11" id="userMobile"><p class="c-red-1 mt5 fsize12 f-fM pl85" id="userNameError"></p></li>
			<li class="mt30"><label class="vam">密&nbsp;&nbsp;&nbsp;码：</label><input type="password" class="lTxt" id="userPassword"><p class="c-red-1 mt5 fsize12 f-fM pl85" id="passwordError"></p></li>
			<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5" for="forget">自动登录</tt></span><span class="ml10"><a class="c-orange fsize12 f-fM" title="忘记密码？" href="/forget_passwd"><u>忘记密码？</u></a></span><span class="ml50"><a class="c-master fsize12 f-fM" title="还没账号，去注册！" target="_blank" href="/register"><u>还没账号，去注册！</u></a></span></li>
			<li class="mt30"><label class="vam">&nbsp;</label><span class="login-btn"><input type="button" style="margin-left: 0;" value="登 录" onclick="pageLogin(1)"></span></li>
			<li class="mt10 tac"><p class="hLh30 line2"><span class="vam c-666">可以使用以下方式快速登录</span></p>
			<div class="mt30">
				<a title="QQ账号登录" class="vam ml10 mr10" href="javascript:oauthLogin('QQ')"><img width="40" height="40" src="${ctx}/static/edu/img/qq.png" class="vam" alt="QQ账号登录"></a>
				<a title="微信账号登录" class="vam ml10 mr10" href="javascript:oauthLogin('WEIXIN')"><img width="40" height="40" src="${ctx}/static/edu/img/wx.png" class="vam" alt="微信账号登录"></a>
				<a title="新浪账号登录" class="vam ml10 mr10" href="javascript:oauthLogin('SINA')"><img width="40" height="40" src="${ctx}/static/edu/img/xl.png" class="vam" alt="新浪账号登录"></a>
			</div>
			</li>
			</ul>
			</div>
			</div>
	</c:when>
	<c:when test="${dialog.index==1}"><!--快速登录弹出框 -->
		<div class="d-tips-1"> 
			<h4 class="d-s-head pr" style="width:545px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">QQ登录</span></h4> 
			<div class="mt40 mb40 pl70 pr90"> 
			<div style=""><ul class="l-r-w-Inpt"> 		
			<li class=""><label class="vam">Q&nbsp;&nbsp;&nbsp;Q：</label><input type="password" class="lTxt" id="userPassword"><p class="c-red-1 mt5 fsize12 f-fM pl85" id="userNameError"></p></li>
			<li class="mt30"><label class="vam">密&nbsp;&nbsp;&nbsp;码：</label><input type="password" class="lTxt" id="userPassword"><p class="c-red-1 mt5 fsize12 f-fM pl85" id="passwordError"></p></li>
			<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5 fsize12 f-fM" for="forget">自动登录</tt></span><span class="ml10"><a class="c-org fsize12 f-fM" title="忘记密码？" href=""><u>忘记密码？</u></a></span></li>
			<li class="mt30"><label class="vam">&nbsp;</label><span class="login-btn"><input type="button" style="margin-left: 0;" value="登 录" onclick="pageLogin(1)"></span></li>
			</ul>
			</div>
			</div>
	</c:when>
	<c:when test="${dialog.index==2}"><!-- 弹窗重新选择上课时间 -->
		<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}"/>
		<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
		<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
		<script type="text/javascript">
			var nowDate=new Date();
			var text= nowDate.getFullYear()+"-"+ (nowDate.getMonth()+1)+"-"+nowDate.getDate();
			$( "#startDate").val(text);
			$( "#startDate").datepicker({
				regional:"zh-CN",
				changeMonth: true,
				dateFormat:"yy-mm-dd ",
				minDate: nowDate
			});
		</script>
		<div class="d-tips-2"><h4 class="d-s-head pr" style="width:510px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl50 pr50">
		<div><div class="xgsksj">
		<a href="" class="fl xgsksj-tit">
			<img src="<%=staticImageServer%>${teacher.picPath}" alt="" width="70" height="70" class="dis">
			<p class="tac fsize14 c-666">老师：${teacher.name}</p></a>
		<div class="xgsksj-boy ml30 fl"><p class="fsize14 c-333 f-fM">课程名称：${trxorderDetail.courseName}</p>
		<p><tt class="fsize14 f-fM c-666 vam">
			<c:choose>
				<c:when test="${trxorderDetail.courseType==2}">班课</c:when>
				<c:otherwise>一对一</c:otherwise>
			</c:choose>
		</tt><tt class="fsize14 f-fM c-666 vam ml30">小学五年级数学</tt></p>
		<p class="fsize14 c-666 f-fM">线上授课</p>
		</div>
		<div class="clear"></div>
		<div class="xgsksj-bot mt20"><ul>
		
		<input type="hidden" id="trxOrdeId" value="${trxorderDetail.id}"/>
		<li><span class="vam">请选择上课日期</span><label class="vam"><input id="startDate" tupe="text" readonly="readonly"/></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left: 160px;">时间冲突</p></li>
		<li><span class="vam">请选择上课时间</span><label class="vam">
			<select name="" id="startTime">
				<option value="06:00:00-07:00:00">06:00-07:00</option>
				<option value="07:00:00-08:00:00">07:00-08:00</option>
				<option value="08:00:00-09:00:00">08:00-09:00</option>
				<option value="09:00:00-10:00:00">09:00-10:00</option>
				<option value="10:00:00-11:00:00">10:00-11:00</option>
				<option value="11:00:00-12:00:00">11:00-12:00</option>
				
				<option value="12:00:00-13:00:00">12:00-13:00</option>
				<option value="13:00:00-14:00:00">13:00-14:00</option>
				<option value="14:00:00-15:00:00">14:00-15:00</option>
				<option value="15:00:00-16:00:00">15:00-16:00</option>
				<option value="16:00:00-17:00:00">16:00-17:00</option>
				<option value="17:00:00-18:00:00">17:00-18:00</option>
				
				<option value="18:00:00-19:00:00">18:00-19:00</option>
				<option value="19:00:00-20:00:00">19:00-20:00</option>
				<option value="20:00:00-21:00:00">20:00-21:00</option>
				<option value="21:00:00-22:00:00">21:00-22:00</option>
				<option value="22:00:00-23:00:00">22:00-23:00</option>
				<option value="23:00:00-24:00:00">23:00-24:00</option>
			</select>
			</label></li>
		<li class="tac pt20"><a href="javascript:void(0)" onclick="studentUpdateOrderTime()" class="u-btn u-xgsksj-btn ">提交修改</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
		</ul></div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==3}"><!-- 提现 -->
		<div class="d-tips-3">
		<h4 class="d-s-head pr" style="width:610px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">提现</span><span class="ml20 unFw"><tt class="fsize12 c-999 f-fM vam">你可以提现的金额为：</tt><tt class="fsize18 c-master f-fM vam">￥422.00</tt><tt class="fsize12 c-999 f-fM vam">元</tt></span></h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="xgsksj-bot">
		<li><span class="tx-list-tit">提现金额：</span><label class="tx-list-bod"><input type="text" /></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="vam">选择银行：</span><label class="vam"><select name="" id=""><option value="">中国银行</option><option value="">中国邮政</option><option value="">中国华夏</option></select></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="tx-list-tit">银行卡号：</span><label class="tx-list-bod"><input type="text" /></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="tx-list-tit">开户行名称：</span><label class="tx-list-bod"><input type="text" /></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="tx-list-tit">开户人姓名：</span><label class="tx-list-bod"><input type="text" /></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="tx-list-tit">账号(注册人手机号)：</span><label class="tx-list-bod"><input type="text" /></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="tx-list-tit">手机验证码：</span><label class="tx-list-bod"><input type="text" /></label></li>
		<li class="ml50 pt30"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">提交修改</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
		<li class="pt30"><p class="c-red-1">温馨提示：用户您好！提现金额根据不同银行一般为1-3个工作日</p></li>
		</ol>
		<div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==4}"><!-- 		//评论框 -->
		<div class="d-tips-4">
		<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="all-ts-bot">
		<li><p class="mb20 fsize16 c-master f-fM">亲！请输入您要评论的内容：</p><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam"></textarea><p class="c-red-1 mt5 fsize12 f-fM" id=""></p></li>
		<li class="ml50 pt30"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">提交评论</a><a href="" class="u-btn u-xgsksj-btn">取消评论</a></li>
		</ol>
		<div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==5}"><!-- 		//微信登录 -->
		<div class="d-tips-5">
		<h4 class="d-s-head pr" style="width:490px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">微信登录</span></h4>
		<div class="mt40 mb40 pl90 pr90">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/pic/ewm-wx.jpg" alt="" class="ewm"><p class="fsize16 c-666 f-fM mt20">扫一扫,安全登录</p></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==6}"><!-- 		//新浪登录 -->
		<div class="d-tips-6">
		<h4 class="d-s-head pr" style="width:720px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">新浪登录</span></h4>
		<div class="mt40 mb40 pl70 pr90">
		<div style="">
		<div class="fl mr30 pr20 tac xlewm"><img src="${ctx}/static/edu/img/pic/ewm-xl.jpg" alt=""><p class="mt20 fsize14 c-999 f-fM">扫描新浪二维码，安全登录微博</p></div>
		<ul class="l-r-w-Inpt fl pt20">
		<li><p id="requestErrorID"></p></li>
		<li class="mt30"><label class="vam">新浪账号：</label><input type="" class="lTxt" id="" style="width:200px;"><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:85px;"></p></li>
		<li class="mt30"><label class="vam">新浪密码：</label><input type="password" class="lTxt" id="userPassword" style="width:200px;"><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:85px;"></p></li>
		<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5" for="forget">自动登录</tt></span><span class="ml10"><a class="c-org fsize12 f-fM" title="忘记密码？" href=""><u>忘记密码？</u></a></span></li>
		<li class="mt30"><span class="login-btn"><input type="button" style="margin-left: 0;" value="登 录" onclick="pageLogin(1)"></span></li>
		</ul>
		<div class="clear"></div>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==7}"><!-- 		//约课弹框 -->
		<script type="text/javascript">
			var nowDate=new Date();
			var text= nowDate.getFullYear()+"-"+ (nowDate.getMonth()+1)+"-"+nowDate.getDate();
			$( "#startDate").val(text);
			$( "#startDate").datepicker({
				regional:"zh-CN",
				changeMonth: true,
				dateFormat:"yy-mm-dd ",
				minDate: nowDate
			});
		</script>
		<input type="hidden" value="${teacher.userExpand.realname }" id="audi_teacherName" />
		<input type="hidden" value="${teacher.userExpand.mobile }" id="audi_teacherMobile" />
		<div class="d-tips-7">
		<h4 class="d-s-head pr" style="width:620px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl30">
		<div><div class="xgsksj">
		<a href="" class="fl xgsksj-tit pl90"><img src="<%=staticImageServer %>${teacher.userExpand.avatar}" alt="" width="70" height="70" class="dis"></a>
		<div class="xgsksj-boy ml30 fl"><p class="fsize14 c-333 f-fM">${teacher.userExpand.realname }</p>
		<p><tt class="fsize14 f-fM c-666 vam"><c:if test="${teacher.userExpand.gender==0}">男</c:if><c:if test="${teacher.userExpand.gender==1}">女</c:if></tt><tt class="fsize14 f-fM c-666 vam ml50">${teacher.lowPrice }元/小时</tt></p>
		<p class="fsize14 c-666 f-fM">上课区域：${teacher.cityName }</p>
		</div>
		<div class="clear"></div>
		<div class="xgsksj-bot mt20"><ul>
		<li><span class="vam">请选择上课日期</span><label class="vam"><input id="startDate" tupe="text" readonly="readonly"/></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;display:none;">上课时间冲突</p></li>
		<li><span class="vam">请选择上课时间</span><label class="vam">
			<select name="" id="startTime">
				<option value="06:00:00-07:00:00">06:00-07:00</option>
				<option value="07:00:00-08:00:00">07:00-08:00</option>
				<option value="08:00:00-09:00:00">08:00-09:00</option>
				<option value="09:00:00-10:00:00">09:00-10:00</option>
				<option value="10:00:00-11:00:00">10:00-11:00</option>
				<option value="11:00:00-12:00:00">11:00-12:00</option>
				
				<option value="12:00:00-13:00:00">12:00-13:00</option>
				<option value="13:00:00-14:00:00">13:00-14:00</option>
				<option value="14:00:00-15:00:00">14:00-15:00</option>
				<option value="15:00:00-16:00:00">15:00-16:00</option>
				<option value="16:00:00-17:00:00">16:00-17:00</option>
				<option value="17:00:00-18:00:00">17:00-18:00</option>
				
				<option value="18:00:00-19:00:00">18:00-19:00</option>
				<option value="19:00:00-20:00:00">19:00-20:00</option>
				<option value="20:00:00-21:00:00">20:00-21:00</option>
				<option value="21:00:00-22:00:00">21:00-22:00</option>
				<option value="22:00:00-23:00:00">22:00-23:00</option>
				<option value="23:00:00-24:00:00">23:00-24:00</option>
			</select>
			</label></li>
		
		<li><span class="vam"><tt class="fsize14 c-666 f-fM ml40">备注信息：</tt></span><label class="tx-list-bod"><textarea id="description" maxlength="" name="" class="vam"></textarea></li>
		<li class="pt20 tac"><a href="javascript:void(0)" onclick="submitInfo()" class="u-btn u-xgsksj-btn">提交约课</a></li>
		</ul></div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==8}"><!-- 		//正确或成功 -->
		<div class="d-tips-8">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.1.png" alt="" class="zqcg vam"><tt class="fsize20 c-master f-fM vam ml20 dia-info">${dialog.content}</tt></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==9}"><!-- 		//错误或失败 -->
		<div class="d-tips-9">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.2.png" alt="" class="zqcg vam"><tt class="fsize20 c-red-1 f-fM vam ml20 dia-info">${dialog.content}</tt></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==10}"><!-- 		//警告或提示 -->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-666 f-fM vam ml20 dia-info">${dialog.content}</tt></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==11}"><!-- 		//退课弹框 -->
		<div class="d-tips-11">
		<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="all-ts-bot">
		<li><p class="mb20 fsize16 c-666 f-fM">请输入退课原因：</p><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam"></textarea></li>
		<li class="ml50 pt30"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
		</ol>
		<div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==12}"><!-- 		//确认删除 -->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-org f-fM vam ml20">确定删除此项？</tt></li>
		<li class="ml50 pt50"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==13}"><!-- 		//确认跳转 -->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.1.png" alt="" class="zqcg vam"><tt class="fsize20 c-org f-fM vam ml20">${dialog.content}</tt></li>
		<li class="pt50 tac"><a href="${dialog.url}" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==14}"><!--//视频弹窗-->
		<div class="d-tips-11">
			<h4 class="d-s-head pr" style="width:620px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
			<div id="" class="pt20 pb20 pl20 pr20 of" style="">
				<div class="d-tips-55">
					 <%--<object id="polyvplayerec0efff98b33805ec83e722fa3ef07ce_e" width="100%" height="100%" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">
						<param value="http://player.polyv.net/videos/player.swf" name="movie">
						<param value="always" name="allowscriptaccess">
						<param value="Transparent" name="wmode">
						<param value="vid=ec0efff98b33805ec83e722fa3ef07ce_e" name="flashvars">
						<param value="true" name="allowFullScreen">
						<embed width="100%" height="100%" flashvars="vid=ec0efff98b33805ec83e722fa3ef07ce_e" allowfullscreen="true" name="polyvplayerec0efff98b33805ec83e722fa3ef07ce_e" wmode="Transparent" allowscriptaccess="always" type="application/x-shockwave-flash" src="http://player.polyv.net/videos/player.swf">
					</object>--%>
					<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="polyvplayer${dialog.content}">
						<PARAM NAME=movie VALUE="http://player.polyv.net/videos/player.swf"><param name="allowscriptaccess" value="always">
						<param name="wmode" value="Transparent"><param name="flashvars" value="vid=${dialog.content}" />
						<param name="allowFullScreen" value="true" />
						<EMBED src="http://player.polyv.net/videos/player.swf" width="100%" height="100%"  TYPE="application/x-shockwave-flash" allowscriptaccess="always" wmode="Transparent" name="polyvplayer${dialog.content}" allowFullScreen="true" flashvars="vid=${dialog.content}"/></EMBED>
					</OBJECT>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==15}"><!--//图片弹窗-->
		<div class="d-tips-11">
			<h4 class="d-s-head pr" style="width:620px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
			<div id="" class="pt20 pb20 pl20 pr20 of" style="">
				<div class="d-tips-55">
					<img alt="" src="<%=staticImageServer%>${teacherStyle.imageUrl}"/>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==16}"><!-- 		//确认-->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-org f-fM vam ml20">${dialog.content}</tt></li>
		<li class="ml50 pt50"><a href="${dialog.url}" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
		</ul>
		</div>
		</div>
	</c:when>
</c:choose>
