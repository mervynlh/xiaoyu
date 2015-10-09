<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<c:choose>
	<c:when test="${dialog.index==0}"><!--快速登录弹出框 -->
		<div class="d-tips-0">
			<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">用户登录</span></h4>
			<div class="mt40 mb40 pl90 pr90">
			<div style=""><ul class="l-r-w-Inpt">
			<li><p id="requestErrorID"></p></li>			
			<li class=""><label class="vam">用&nbsp;户&nbsp;名：</label><input type="" class="lTxt" id="userNameError"><p class="c-red-1 mt5 fsize12 f-fM pl85" id="userNameError"></p></li>
			<li class="mt30"><label class="vam">密&nbsp;&nbsp;&nbsp;码：</label><input type="password" class="lTxt" id="userPassword"><p class="c-red-1 mt5 fsize12 f-fM pl85" id="passwordError"></p></li>
			<li class="mt15"><label class="vam">&nbsp;</label><span class="inpCb"><input type="checkbox" id="autoThirty" class="c-icon" checked="checked" value="" name=""><tt class="vam c-999 ml5" for="forget">自动登录</tt></span><span class="ml10"><a class="c-orange fsize12 f-fM" title="忘记密码？" href=""><u>忘记密码？</u></a></span><span class="ml50"><a class="c-master fsize12 f-fM" title="还没账号，去注册！" target="_blank" href=""><u>还没账号，去注册！</u></a></span></li>
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
			<c:choose>
				<c:when test="${userType==0 }">
					<img src="<%=staticImageServer%>${teacher.picPath}" alt="" width="70" height="70" class="dis" >
					<p class="tac fsize14 c-666">老师：${teacher.name}</p>
				</c:when>
				<c:otherwise>
					<img src="<%=staticImageServer%>${user.headPhoto}" alt="" width="70" height="70" class="dis">
					<p class="tac fsize14 c-666">学生：${user.showname}</p>
				</c:otherwise>
			</c:choose>
		</a>
		<div class="xgsksj-boy ml30 fl"><p class="fsize14 c-333 f-fM">课程名称：${trxorderDetail.courseName}</p>
		<p><tt class="fsize14 f-fM c-666 vam">
			<c:choose>
				<c:when test="${trxorderDetail.courseType==2}">班课</c:when>
				<c:otherwise>一对一</c:otherwise>
			</c:choose>
		</tt><tt class="fsize14 f-fM c-666 vam ml30">${trxorderDetail.gradeName}${trxorderDetail.majorName}</tt></p>
		<p class="fsize14 c-666 f-fM">
			<c:if test="${detail.courseModel=='TEACHERVISIT' }">老师上门</c:if>
			<c:if test="${detail.courseModel=='STUDENTVISIT' }">学生上门</c:if>
			<c:if test="${detail.courseModel=='TALKADDRESS' }">协商地点</c:if>
			<c:if test="${detail.courseModel=='ONLINEOTO' }">在线教学</c:if>
			<c:if test="${detail.courseModel=='ONLINECOU' }">在线授课</c:if>
			<c:if test="${detail.courseModel=='LINEDOWNCOU' }">线下面授</c:if>
		</p>
		</div>
		<div class="clear"></div>
		<div class="xgsksj-bot mt20"><ul>
		
		<input type="hidden" id="trxOrdeId" value="${trxorderDetail.id}"/>
		<li><span class="vam">请选择上课日期</span><label class="vam"><input id="startDate" tupe="text" readonly="readonly"/></label><p class="c-red-1 mt5 fsize12 f-fM" id="timeerror" style="padding-left: 160px;display:none;">时间冲突</p></li>
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
		<li class="tac pt20"><a href="${dialog.url }" class="u-btn u-xgsksj-btn ">提交修改</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
		</ul></div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==3}"><!-- 提现 -->
		<div class="d-tips-3">
		<h4 class="d-s-head pr" style="width:600px;">
			<a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a>
			<span class="d-s-head-txt">提现</span>
			<span class="ml20 unFw"><tt class="fsize12 c-999 f-fM vam">你可以提现的金额为：</tt><tt class="fsize18 c-master f-fM vam">￥<fmt:formatNumber value="${dialog.content}" pattern="#,##0.00#" maxFractionDigits="2"/></tt><tt class="fsize12 c-999 f-fM vam">&nbsp;元</tt></span>
		</h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="xgsksj-bot">
		<li><span class="tx-list-tit">提现金额：</span><label class="tx-list-bod">
		<input type="text" id="cashOutMoney" name="cashOut.cashoutMoney"/></label>
		<p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li><span class="tx-list-tit">提现方式：</span><label class="tx-list-bod">
		<select name="cashOut.cashoutType" id="cashoutType" onchange="typeChange()">
		<option value="BANK">银行卡</option>
		<option value="ALIPAY">支付宝</option>
		</select>
		<script type="text/javascript">
		</script>
		</label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p></li>
		<li name="bank"><span class="vam">选择银行：</span><label class="vam">
			<select name="cashOut.bankName" id="bankName">
			<option value="中国银行">中国银行</option>
			<option value="中国邮政">中国邮政</option>
			<option value="中国华夏银行">中国华夏银行</option>
			<option value="中国建设银行">中国建设银行</option>
			<option value="中国工商银行">中国工商银行</option>
			<option value="中国农业银行">中国农业银行</option>
			<option value="广大银行">广大银行</option>
			<option value="招商银行">招商银行</option>
			<option value="中国交通银行">中国交通银行</option>
			<option value="兴业银行">兴业银行</option>
			<option value="中国民生银行">中国民生银行</option>
			<option value="中兴银行">中兴银行</option>
			<option value="平安银行">平安银行</option>
			<option value="深圳发展银行">深圳发展银行</option>
			<option value="上海银行">上海银行</option>
			<option value="北京农商银行">北京农商银行</option>
			</select></label>
			<p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p>
		</li>
		<li name="bank"><span class="tx-list-tit">银行卡号：</span><label class="tx-list-bod">
		<input type="text" id="bankCard" name="cashOut.bankCard"/></label>
		<p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;"></p>
		</li>
		<li name="bank"><span class="tx-list-tit">开户行名称：</span><label class="tx-list-bod">
		<input type="text" id="openbankName" name="cashOut.openBankName"/></label>
		<p class="c-red-1 mt5 fsize12 f-fM" id="openBankNameError" style="padding-left:160px;"></p>
		</li>
		<li><span class="tx-list-tit" id="userRealName">开户人姓名：</span><label class="tx-list-bod">
		<input type="text" id="bankPerson" name="cashOut.openBankPerson"/></label>
		<p class="c-red-1 mt5 fsize12 f-fM" id="openBankPersonError" style="padding-left:160px;"></p>
		</li>
		<li name="alipay" style="display:none"><span class="tx-list-tit">支付宝账号：</span><label class="tx-list-bod">
		<input type="text" id="alipayAccount" name="cashOut.alipayAccount"/></label>
		<p class="c-red-1 mt5 fsize12 f-fM" id="alipayAccountError" style="padding-left:160px;"></p>
		</li>

		<%--<li name="bank">
			<span class="tx-list-tit">账号(注册人手机号)：</span><label class="tx-list-bod"><input type="text" id="mobile" name="cashOut.userMobile"/></label>
			<span class="vam ml5 disIb">
				<a id="validCodeBtn" href="javascript:getVerifications()" class="u-btn u-xgsksj-btn u-xgsksj-btn-current" style="width: 100px;margin-left:10px">获取验证码</a>
			</span>
			<span id="mobileError" class="getnumber-wait" style="display: none"></span>
		</li>
		<li name="bank">
			<span class="tx-list-tit">手机验证码：</span><label class="tx-list-bod"><input type="text" id="randomCode" name="randomCode"/></label>
			<span id="randomCodeError" class="getnumber-wait" style="display: none"></span>
		</li>--%>

		<li class="ml50 pt30"><a href="javascript:void(0)" onclick="cashOut()" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">提交申请</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
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
		<li><p class="mb20 fsize16 c-master f-fM">亲！请输入您要评论的内容：</p><label class="tx-list-bod"><textarea id="description" maxlength="" name="" class="vam"></textarea></li>
		<li class="ml50 pt30"><a href="${dialog.url }" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">提交评论</a><a href="" class="u-btn u-xgsksj-btn">取消评论</a></li>
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
		<input type="hidden" id="" value="${teacher.id }" id="teacherId" />
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
		<li><span class="vam">请选择上课日期</span><label class="vam"><input id="startDate" tupe="text" readonly="readonly"/></label><p class="c-red-1 mt5 fsize12 f-fM" id="" style="padding-left:160px;">上课时间冲突</p></li>
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
		
		<li><span class="vam"><tt class="fsize14 c-666 f-fM ml40">备注信息：</tt></span><label class="tx-list-bod"><textarea id="userInfo" maxlength="" name="" class="vam">268教育</textarea></li>
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
		<li class="tac"><img src="${ctx}/static/edu/img/tc.1.png" alt="" class="zqcg vam"><tt class="fsize20 c-master f-fM vam ml20">${dialog.content}</tt></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==9}"><!-- 		//错误或失败 -->
		<div class="d-tips-9">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.2.png" alt="" class="zqcg vam"><tt class="fsize20 c-red-1 f-fM vam ml20">${dialog.content}</tt></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==10}"><!-- 		//警告或提示 -->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-666 f-fM vam ml20">${dialog.content}</tt></li>
		</ul>
		</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==11}"><!-- 		//退款，退课弹框 -->
		<div class="d-tips-11">
		<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="all-ts-bot">
		<li><p class="mb20 fsize16 c-666 f-fM">请输入您的原因:</p><label class="tx-list-bod"><textarea id="description" maxlength="" name="" class="vam"></textarea></li>
		<li class="ml50 pt30"><a href="${dialog.url }" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
		</ol>
		<div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==12}"><!-- 		//确认弹框 -->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style="">
		<ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-666 f-fM vam ml20">${dialog.content}</tt></li>
		<li class="ml50 pt30"><a href="${dialog.url }" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="javascript:dialogClose()" class="u-btn u-xgsksj-btn">取消</a></li>
		</ul>
		</div>
		</div>
	</c:when>
		<c:when test="${dialog.index==13 }">  <!-- 添加优惠券 -->
		<script type="text/javascript">
			$(function() {
				$("#startTime,#endTime").datetimepicker({
					regional : "zh-CN",
					changeMonth : true,
					dateFormat : "yy-mm-dd ",
					timeFormat : "HH:mm:ss"
				});
			});
		</script>
		<div class="d-tips-12">
			<h4 class="d-s-head pr" style="width: 600px;">
				<a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span
					class="d-s-head-txt">添加优惠券</span>
			</h4>
			<div class="mt40 mb40 pl50 pr50">
				<div class="tjyhj-bot mt20">
					<ul>
						<li><span class="vam tjyhj-span"><tt
									class="fsize14 c-666 f-fM">面值：</tt></span> <label class="vam tjyhj"><input
								type="text" id="amount" />
							<tt class="fsize14 f-fM c-999 ml10 vam">元</tt>
								<tt class="fsize12 f-fM c-red-1 ml30 vam">请输入1-2000元以内的整数金额</tt></label></li>
						<li><span class="vam tjyhj-span"><tt
									class="fsize14 c-666 f-fM">张数：</tt></span> <label class="vam tjyhj"><input
								type="text" id="count" />
							<tt class="fsize14 f-fM c-999 ml10 vam">张</tt>
								<tt class="fsize12 f-fM c-red-1 ml30 vam">不能超过100张</tt></label></li>
						<li><span class="vam tjyhj-span"><tt
									class="fsize14 c-666 f-fM">开始时间：</tt></span><input type="text"
							id="startTime" class="AS-inp" /></li>
						<li><span class="vam tjyhj-span"><tt
									class="fsize14 c-666 f-fM">结束时间：</tt></span><input type="text"
							id="endTime" class="AS-inp" /></li>
						<li><span class="vam tjyhj-span"><tt
									class="fsize14 c-666 f-fM">使用条件：</tt></span> <label class="vam tjyhj"><input
								type="radio" onclick="selectAmount(1)" name="radio" id="gender0"
								checked value="1" placeholder="" style="width: 16px;"
								class="vam">
							<tt class="fsize14 c-333 f-fM vam mr50">无限制</tt><input
								type="radio" onclick="selectAmount(2)" name="radio" id="gender0"
								value="0" placeholder="" style="width: 16px;" class="vam"><input
								type="text" id="limitAmount" disabled />
							<tt class="fsize14 f-fM c-333 ml10 vam">元以上可用</tt></label></li>
						<li><span class="vam tjyhj-span"><tt
									class="fsize14 c-666 f-fM">备注信息：</tt></span><label class="tx-list-bod"><textarea
									id="info" maxlength="" name="" class="vam">最多30个字</textarea></li>
						<li class="ml50 pt30 tac"><a href="javascript:saveCoupon()"
							class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href=""
							class="u-btn u-xgsksj-btn">取消</a></li>
					</ul>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${dialog.index==14}"><!--//添加折扣  -->
			<script type="text/javascript">
				//只能输入0-99之间的整数
				function onlyFromts(obj){
					var number = obj.value;
					var number2 = parseInt(number);
					if((/^[0-9]+$/).test(number) && number2>=1 && number2<=100){
						 return;
					}
					else{
						 if(number==0){
							 obj.value = 1;
						 }else{
							 obj.value = number.substring(0,number.length-1);
						 }
					}
				}
				//只能输入0~9.99折
				function onlycountFromt(input,n){
					var number = input.value;
					var number2 = parseInt(number);
					if((/^[.0-9]+$/).test(number) && number2>=0 && number2<10){
						var dotIdx = number.indexOf('.'), dotLeft, dotRight;
					    if (dotIdx >= 0) {
					        dotLeft = input.value.substring(0, dotIdx);
					        dotRight = input.value.substring(dotIdx + 1);
					        if (dotRight.length > n) {
					            dotRight = dotRight.substring(0, n);
					        }
					        input.value = dotLeft + '.' + dotRight;
					    }
						return;
					}
					else{
						 input.value = number.substring(0,number.length-1);
					}
				}
			</script>
			<div class="d-tips-13">
			<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">添加折扣</span></h4>
			<div class="mt40 mb40 pl90 pr90">
			<div style=""><ul class="l-r-w-Inpt">
			<li><p id="requestErrorID"></p></li>
			<li><label class="vam">简介：</label>
			<span style="display:inline-block;position:relative;">
			<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>
			<input type="text" id="details" value="" class="lTxt" style="width:200px" maxlength="30" placeholder="30字以内">
			</span>
			<p class="disIb ml5 c-red-1 fsize12 f-fM minusError" id="detailsError"></p></li>
			<li class="mt30"><label class="vam">课时：</label><input type="text" class="lTxt" id="minusNum" style="width:200px" placeholder="输入1-99的整数" onkeyup="onlyFromts(this)"  value="1"><p class="disIb ml5 c-red-1 fsize12 f-fM minusError" id="minusNumError"></p></li>
			<li class="mt30"><label class="vam">折扣：</label><input type="text" class="lTxt" id="discount" style="width:100px" placeholder="输入0.1-9.9" onkeyup="onlycountFromt(this,1)"><p class="disIb ml5 c-red-1 fsize12 f-fM minusError" id="discountError">输入1-9.9对应1-9.9折，不填写表示没有</p></li>
			<li class="pt30 tac"><a href="javascript:void(0)" onclick="addMinus()" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
			</ul>
			</div>
			</div>
	</c:when>
	<c:when test="${dialog.index==15}"><!--//编辑折扣  -->
		<script type="text/javascript">
				//只能输入0-99之间的整数
				function onlyFromts(obj){
					var number = obj.value;
					var number2 = parseInt(number);
					if((/^[0-9]+$/).test(number) && number2>=1 && number2<=100){
						 return;
					}
					else{
						 if(number==0){
							 obj.value = 1;
						 }else{
							 obj.value = number.substring(0,number.length-1);
						 }
					}
				}
				//只能输入0~9.99折
				function onlycountFromt(input,n){
					var number = input.value;
					var number2 = parseInt(number);
					if((/^[.0-9]+$/).test(number) && number2>=0 && number2<10){
						var dotIdx = number.indexOf('.'), dotLeft, dotRight;
					    if (dotIdx >= 0) {
					        dotLeft = input.value.substring(0, dotIdx);
					        dotRight = input.value.substring(dotIdx + 1);
					        if (dotRight.length > n) {
					            dotRight = dotRight.substring(0, n);
					        }
					        input.value = dotLeft + '.' + dotRight;
					    }
						return;
					}
					else{
						 input.value = number.substring(0,number.length-1);
					}
				}
			</script>
			<input type="hidden" id="minusId" value="${minus.id}"/>
			<div class="d-tips-13">
			<h4 class="d-s-head pr" style="width:585px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">添加折扣</span></h4>
			<div class="mt40 mb40 pl90 pr90">
			<div style=""><ul class="l-r-w-Inpt">
			<li><p id="requestErrorID"></p></li>
			<li><label class="vam">简介：</label>
			<span style="display:inline-block;position:relative;">
			<div style="position:absolute;left:-6000px;top:34px;z-index:1;" class="justForJs out_box" id="mailListBox_0"></div>
			<input type="text" id="details" value="${minus.details}" class="lTxt" style="width:200px" maxlength="30" placeholder="30字以内">
			</span>
			<p class="disIb ml5 c-red-1 fsize12 f-fM minusError" id="detailsError"></p></li>
			<li class="mt30"><label class="vam">课时：</label><input type="text" class="lTxt" id="minusNum" style="width:200px" placeholder="输入1-99的整数" onkeyup="onlyFromts(this)"  value="${minus.minusNum}"><p class="disIb ml5 c-red-1 fsize12 f-fM minusError" id="minusNumError"></p></li>
			<li class="mt30"><label class="vam">折扣：</label><input type="text" class="lTxt" id="discount" style="width:100px" placeholder="输入0.1-9.9" onkeyup="onlycountFromt(this,1)" value="${minus.discount}"><p class="disIb ml5 c-red-1 fsize12 f-fM minusError" id="discountError">输入1-9.9对应1-9.9折，不填写表示没有</p></li>
			<li class="pt30 tac"><a href="javascript:void(0)" onclick="updateMinus()" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
			</ul>
			</div>
			</div>
	</c:when>
	<c:when test="${dialog.index==16}"><!-- 		//确认删除 -->
		<div class="d-tips-10">
		<h4 class="d-s-head pr" style="width:400px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb50">
		<div style=""><ul class="l-r-w-Inpt">
		<li class="tac"><img src="${ctx}/static/edu/img/tc.3.png" alt="" class="zqcg vam"><tt class="fsize20 c-org f-fM vam ml20">确定删除此项？</tt></li>
		<li class="ml50 pt50"><a href="${dialog.url}" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
		</ul>
		</div>
		</div>
	</c:when>
		<c:when test="${dialog.index==17}"><!-- 		//确认跳转 -->
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
	<c:when test="${dialog.index==18}"><!-- 个人中心添加课时 -->
			<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
			<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
			<script type="text/javascript">
				var now=new Date();
				var month = now.getMonth() + 1 < 10 ? "0" + (now.getMonth() + 1) : now.getMonth() + 1;
				var currentDate = now.getDate() < 10 ? "0" + now.getDate() : now.getDate();
				var hour=now.getHours()< 10 ? "0" + now.getHours() : now.getHours();
				var nowTime=now.getFullYear()+"-"+month+"-"+ currentDate+" "+hour+":"+"00:00";
				$( "#startDate").val(nowTime);
				$( "#startDate").datetimepicker({
					regional:"zh-CN",
					changeMonth: true,
					dateFormat : "yy-mm-dd",
					timeFormat : 'HH:mm:ss',
					showMinute:false,
					showSecond:false,
				  	hourGrid: 4,
					hourMin: 6,
					minDate: nowTime,
					onSelect: function(dateText, inst) {
						$('#endDate').datepicker('option', 'minDate', new Date(dateText.replace('-',',')));}
				});
				$( "#endDate").val(nowTime);
				$( "#endDate").datetimepicker({
					regional:"zh-CN",
					changeMonth: true,
					dateFormat : "yy-mm-dd",
					timeFormat : 'HH:mm:ss',
					showMinute:false,
					showSecond:false,
					hourGrid: 4,
					hourMin: 6,
					minDate: nowTime,
					onSelect: function(dateText, inst) {
						$('#startDate').datepicker('option', 'maxDate', new Date(dateText.replace('-',',')));}
				});
				//保存课节
				function saveKpoint(){
					$("#kpointNameError").html("&nbsp;");
					$("#timeError").html("&nbsp;");
					$("#detailsError").html("&nbsp;");
					
					var kpointName=$("#kpointName").val();
					var details=$("#details").val();
					if(kpointName==null||kpointName==''){
						$("#kpointNameError").html("请输入课节名");
						return;
					}
					if(details==null||details==''){
						$("#detailsError").html("请输入简介");
						return;
					}
					$.ajax({
						url:"/uc/teacher/addKpoint",
						data:{
							"kpoint.name":kpointName,
							"kpoint.beginTime":$("#startDate").val(),
							"kpoint.endTime":$("#endDate").val(),
							"kpoint.details":details,
							"kpoint.courseId":$("#courseId").val(),
							"kpoint.sort":$("#sort").val()
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(result){
							if(result.success){
								window.location.reload();
							}else{
								if(result.message=='timeError'){
									$("#timeError").html("课程时间段无效");
									return;
								}else if(result.message=='classError'){
									$("#timeError").html("班课时间冲突");
									return;
								}else if(result.message=='oneToOneError'){
									$("#timeError").html("一对一时间冲突,请取消一对一再设置课节");
									return;
								}else{
									dialog(result.message,9,'','');
								}
							}
						}
					});
				}
			</script>
			
			<div class="d-tips-15">
			<h4 class="d-s-head pr" style="width:500px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">添加课时</span></h4>
			<div class="mt40 mb40 pl90 pr90">
			<div style=""><ul class="l-r-w-Inpt">
			<li><p id="requestErrorID"></p></li>
			<li><label class="vam">课时名称：</label><input type="text" id="kpointName" placeholder="30字以内" value="" class="lTxt" style="width:200px"><p class="mt5 c-red-1 fsize12 f-fM" id="kpointNameError" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">开始时间：</label><input type="text" readonly="readonly" class="lTxt" id="startDate" style="width:200px"><p class="mt5 c-red-1 fsize12 f-fM" id="timeError" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">结束时间：</label><input type="text" readonly="readonly" class="lTxt" id="endDate" style="width:200px"><p class="mt5 c-red-1 fsize12 f-fM" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">课程简介：</label><textarea id="details" maxlength="300" name="" placeholder="最多100个字" class="vam fsize14 c-999 f-fM" style="width:218px;height:63px;"></textarea><p class="mt5 c-red-1 fsize12 f-fM" id="detailsError" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">排序：</label><input type="text" class="lTxt" id="sort" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  style="width:200px"><p class="mt5 c-red-1 fsize12 f-fM" id="detailsError" style="padding-left: 80px;">排序值越大,课节显示越靠前</p></li>
			<li class="pt20 tac"><a href="javascript:void(0)" onclick="saveKpoint()" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
			</ul>
			</div>
			</div>
	</c:when>
	<c:when test="${dialog.index==19}"><!-- 个人中心修改课时 -->
			<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}" />
			<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-timepicker-addon.css?v=${v}" />
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-addon.js?v=${v}"></script>
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
			<script type="text/javascript" src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-timepicker-zh-CN.js?v=${v}"></script>
			<script type="text/javascript">
				var now=new Date();
				var month = now.getMonth() + 1 < 10 ? "0" + (now.getMonth() + 1) : now.getMonth() + 1;
				var currentDate = now.getDate() < 10 ? "0" + now.getDate() : now.getDate();
				var hour=now.getHours()< 10 ? "0" + now.getHours() : now.getHours();
				var nowTime=now.getFullYear()+"-"+month+"-"+ currentDate+" "+hour+":00:00";
				$( "#startDate").datetimepicker({
					regional:"zh-CN",
					changeMonth: true,
					dateFormat : "yy-mm-dd",
					timeFormat : 'HH:mm:ss',
					showMinute:false,
					showSecond:false,
					hourGrid: 4,
					hourMin: 6,
					onSelect: function(dateText, inst) {
						$('#endDate').datepicker('option', 'minDate', new Date(dateText.replace('-',',')));}
				});
				$( "#endDate").datetimepicker({
					regional:"zh-CN",
					changeMonth: true,
					dateFormat : "yy-mm-dd",
					timeFormat : 'HH:mm:ss',
					showMinute:false,
					showSecond:false,
					hourGrid: 4,
					hourMin: 6,
					onSelect: function(dateText, inst) {
						$('#startDate').datepicker('option', 'maxDate', new Date(dateText.replace('-',',')));}
				});
				//保存课节
				function saveKpoint(){
					$("#kpointNameError").html("&nbsp;");
					$("#timeError").html("&nbsp;");
					$("#detailsError").html("&nbsp;");
					
					var kpointName=$("#kpointName").val();
					var details=$("#details").val();
					if(kpointName==null||kpointName==''){
						$("#kpointNameError").html("请输入课节名");
						return;
					}
					if(details==null||details==''){
						$("#detailsError").html("请输入简介");
						return;
					}
					$.ajax({
						url:"/uc/teacher/updateKpoint",
						data:{
							"kpoint.name":kpointName,
							"kpoint.beginTime":$("#startDate").val()+":00",
							"kpoint.endTime":$("#endDate").val()+":00",
							"kpoint.details":details,
							"kpoint.courseId":$("#courseId").val(),
							"kpoint.sort":$("#sort").val(),
							"kpoint.id":$("#kpointId").val()
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(result){
							if(result.success){
								window.location.reload();
							}else{
								if(result.message=='timeError'){
									$("#timeError").html("课程时间段无效");
									return;
								}else if(result.message=='classError'){
									$("#timeError").html("班课时间冲突");
									return;
								}else if(result.message=='oneToOneError'){
									$("#timeError").html("一对一时间冲突,请取消一对一再设置课节");
									return;
								}else{
									dialog(result.message,9,'','');
								}
							}
						}
					});
				}
			</script>
			
			<div class="d-tips-15">
			<h4 class="d-s-head pr" style="width:500px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt">添加课时</span></h4>
			<div class="mt40 mb40 pl90 pr90">
			<div style=""><ul class="l-r-w-Inpt">
			<li><p id="requestErrorID"></p></li>
			<input type="hidden" id="kpointId" value="${kpoint.id}"/>
			<li><label class="vam">课时名称：</label><input type="text" id="kpointName" placeholder="30字以内" value="${kpoint.name}" class="lTxt" style="width:200px"><p class="mt5 c-red-1 fsize12 f-fM" id="kpointNameError" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">开始时间：</label><input type="text" readonly="readonly" class="lTxt" id="startDate" style="width:200px" value="<fmt:formatDate value='${kpoint.beginTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss'/>"><p class="mt5 c-red-1 fsize12 f-fM" id="timeError" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">结束时间：</label><input type="text" readonly="readonly" class="lTxt" id="endDate" style="width:200px" value="<fmt:formatDate value='${kpoint.endTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss'/>"><p class="mt5 c-red-1 fsize12 f-fM" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">课程简介：</label><textarea id="details" maxlength="300" name="" placeholder="最多100个字" class="vam fsize14 c-999 f-fM" style="width:218px;height:63px;">${kpoint.details}</textarea><p class="mt5 c-red-1 fsize12 f-fM" id="detailsError" style="padding-left: 80px;">&nbsp;</p></li>
			<li class="mt10"><label class="vam">排序：</label><input type="text" class="lTxt" id="sort" value="${kpoint.sort}" onkeyup="this.value=this.value.replace(/\D/g,'')"  style="width:200px"><p class="mt5 c-red-1 fsize12 f-fM" id="detailsError" style="padding-left: 80px;">排序值越大,课节显示越靠前</p></li>
			<li class="pt20 tac"><a href="javascript:void(0)" onclick="saveKpoint()" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="javascript:void(0)" class="u-btn u-xgsksj-btn dialogClose">取消</a></li>
			</ul>
			</div>
			</div>
	</c:when>
	<c:when test="${dialog.index==20}"><!--//视频播放弹窗-->
		<div class="d-tips-11">
			<h4 class="d-s-head pr" style="width:620px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
			<div id="" class="pt20 pb20 pl20 pr20 of" style="">
				<div class="d-tips-55">
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
	<c:when test="${dialog.index==21}"><!--  课程小结	 -->
		<div class="d-tips-4">
		<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="all-ts-bot">
		<li><p class="mb20 fsize16 c-master f-fM">您的课程小结：</p><label class="tx-list-bod"><textarea id="description" maxlength="" name="" class="vam">${dialog.content }</textarea></li>
		<li class="ml50 pt30"><a href="${dialog.url }" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">保存</a><a href="" class="u-btn u-xgsksj-btn">取消</a></li>
		</ol>
		<div>
		</div></div>
		</div>
	</c:when>
	<c:when test="${dialog.index==22}"><!--  查看课时评价	 -->
		<div class="d-tips-4">
		<h4 class="d-s-head pr" style="width:645px;"><a href="javascript:void(0)" title="关闭" class="dtClose icon14 pa">&nbsp;</a><span class="d-s-head-txt"></span></h4>
		<div class="mt40 mb40 pl90 pr50">
		<ol class="all-ts-bot">
		<li><p class="mb20 fsize16 c-master f-fM">课时评价：</p><label class="tx-list-bod"><textarea id="description" maxlength="" name="" disabled class="vam">${dialog.content }</textarea></li>
		<li class="pt50 tac"><a href="" class="u-btn u-xgsksj-btn u-xgsksj-btn-current">确认</a></li>
		</ol>
		<div>
		</div></div>
		</div>
	</c:when>
</c:choose>
