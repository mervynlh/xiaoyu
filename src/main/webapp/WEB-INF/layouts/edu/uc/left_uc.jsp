<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<menu class="u-m-left">
			<div class="u-elephant tac pr">
				<aside class="u-elephant-img"> 
					<img width="140" height="140" alt="" id="userImgId" onclick="javascript:window.location.href='${ctx}/uc/home'" src="${ctximg}/static/edu/img/avatar-boy.gif">
					<a class="c-fff" title="修改头像" id="updateavatar" href="${ctx }/uc/user/avatar">修改头像</a>
				</aside>
				<div class="u-elephant-name pt85 pb20">
					<span class="dis tac">
						<tt class="fsize20 f-fM c-333 mr10"><span id="usernickname"></span></tt>
						<tt class="fsize14 f-fM c-999">积分：<span id="userintegift"></span></tt>
					</span>
					<span class="dis tac mt10">
						<em class="icon18 icon-nv vam" id="user_gender">&nbsp;</em>
						<tt class="fsize14 f-fM c-999 vam">ID：<span id="u_id"></span></tt>
					</span>
					<span class="dis tac mt10 fsize14 c-999 f-fM" style="display:none" id="isOpenHidden">
						( 隐私模式已开启 )
					</span>
				</div>
			</div>
			<div class="u-Sign">
				<img src="${ctximg }/static/edu/img/u-rl-tit.png" alt="" class="u-rl-tit">
				<span class="u-Sign-span">
					<p class="fsize20 f-fM c-333" id="sign_title">今日未签到</p>
					<p class="mt10">
						<tt class="fsize14 f-fM c-999">连续签到</tt>
						<tt class="fsize18 f-fM c-master" id="contSignIn">${contSignIn}</tt>
						<tt class="fsize14 f-fM c-999">天</tt>
					</p>
				</span>
				<p class="clear"></p>
				<section class="levelTips"> 
					<div class="pr nextScore"> 
						<div class="DT-arrow">
							<em>◆</em>
							<span>◆</span>
						</div> 
					</div>
					<div class="u-Sign-rl">
						<div class="u-s-rl-tit">
							<p class="fsize14 c-666 f-fM" id="sign_date" onclick="sign_backToday()"></p>
							<a href="javascript:void(0)" onclick="sign_changeMonth('up')" class="left"><</a>
							<a href="javascript:void(0)" onclick="sign_changeMonth('down')" class="right">></a>
						</div>
						<div class="u-s-rl-boy">
							<div class="u-s-rl-boy-in u-s-rl-boy-in-1">
								<ul class="clearfix">
									<li>
										<span class="fsize12 f-fM c-666">一</span>
									</li>
									<li>
										<span class="fsize12 f-fM c-666">二</span>
									</li>
									<li>
										<span class="fsize12 f-fM c-666">三</span>
									</li>
									<li>
										<span class="fsize12 f-fM c-666">四</span>
									</li>
									<li>
										<span class="fsize12 f-fM c-666">五</span>
									</li>
									<li>
										<span class="fsize12 f-fM c-red">六</span>
									</li>
									<li>
										<span class="fsize12 f-fM c-red">日</span>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="u-s-rl-boy-in signTable">
								<ul class="clearfix">
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="u-s-rl-boy-in signTable">
								<ul class="clearfix">
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="u-s-rl-boy-in signTable"  >
								<ul class="clearfix">
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="u-s-rl-boy-in signTable">
								<ul class="clearfix">
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="u-s-rl-boy-in signTable">
								<ul class="clearfix">
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="u-s-rl-boy-in signTable">
								<ul class="clearfix">
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
									<li>
										<a href="javascript:void(0)" class="fsize12 f-fM c-666"></a>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
						</div>
						<div class="u-s-rl-btn tac mt15">
							<a href="javascript:void(0)" onclick="sign()" class="fsize16 c-fff f-fM" id="sign_info">签&nbsp;&nbsp;到</a>
						</div>
					</div> 
				</section>
			</div>
	<section class="uMenuFixed" id="menu_student">  
		<div class="u-menu-list pb40"> 
			<dl id="leftdl_schedule_student"> 
				<dt class="" onclick="window.location.href='${ctx}/uc/student/home'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM" >我的课表</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl> 
			<dl id="leftdl_order_student"> 
				<dt class="" >
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的订单</span>
					<em class="icon-2-14 u-up-down ml15">&nbsp;</em>
				</dt> 
				<dd> 
					<ol> 
						<li>
							<a title="全部订单" href="${ctx}/uc/order?trxStatus=all" long="trxorder_all">全部订单</a>
						</li> 
						<li>
							<a title="待支付" href="${ctx}/uc/order?trxStatus=INIT" long="trxorder_INIT">待支付</a>
						</li>
						<li>
							<a title="上课中" href="${ctx}/uc/order?trxStatus=SUCCESS" long="trxorder_SUCCESS">上课中</a>
						</li> 
						<li>
							<a title="已结束" href="${ctx}/uc/order?trxStatus=FINISH" long="trxorder_FINISH">已结束</a>
						</li> 
					</ol> 
				</dd> 
			</dl>
			 
			<dl id="leftdl_setting_student"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">个人设置</span>
					<em class="icon-2-14 u-up-down ml15 ">&nbsp;</em>
				</dt> 
				<dd> 
					<ol> 
						<li>
							<a title="基本信息" href="${ctx }/uc/user/uinfo">基本信息</a>
						</li> 
						<li class="updatePwd">
							<a title="修改密码" href="${ctx }/uc/user/uppwd">修改密码</a>
						</li> 
						<li>
							<a title="修改头像" href="${ctx }/uc/user/avatar">修改头像</a>
						</li> 
						<li>
							<a title="绑定手机" href="${ctx }/uc/user/jumpmobile">绑定手机</a>
						</li> 
						<li>
							<a title="地址管理" href="${ctx }/uc/address">地址管理</a>
						</li>
					</ol> 
				</dd> 
			</dl>
			<dl id="leftdl_wallet_student"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的钱包</span>
					<em class="icon-2-14 u-up-down ml15">&nbsp;</em>
				</dt> 
				<dd> 
					<ol> 
						<li>
							<a title="我的账户" href="${ctx}/uc/student/acc">我的账户</a>
						</li>  
						<li>
							<a title="优惠券" href="${ctx}/uc/student/coupon">优惠券</a>
						</li> 
						<li>
							<a title="我的积分" href="${ctx}/uc/myinte">我的积分</a>
						</li> 
						<li>
							<a title="积分兑换" href="${ctx}/uc/integift">积分兑换</a>
						</li> 
					</ol> 
				</dd> 
			</dl> 
			<dl id="leftdl_Collectteacher"> 
				<dt class="" onclick="javascript:window.location.href='${ctx}/uc/collectTeacherList'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM" >收藏老师</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt>  
			</dl> 
			<dl id="leftdl_studyhistory"> 
				<dt class="" onclick="window.location.href='${ctx}/uc/my/study/history/list'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的学习记录</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl>
			<dl id="leftdl_myteacher"> 
				<dt class="" onclick="window.location.href='${ctx}/uc/front/myteacher/list'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的老师</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl>
			<dl id="leftdl_news_student"> 
				<dt class="" onclick="window.location.href='${ctx}/uc/letter'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的消息</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl> 
			<dl id="leftdl_evaluation"> 
				<dt class="" onclick="javascript:window.location.href='${ctx}/uc/evaluation'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的评价</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl>
		</div> 
	</section>
	
	
	<section class="uMenuFixed" id="menu_teacher" style="display:none">  
		<div class="u-menu-list pb40"> 
			<dl id="leftdl_schedule_teacher"> 
				<dt class="" onclick="javascript:window.location.href='${ctx}/uc/teacher/myclass'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的课表</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl> 
			<dl id="leftdl_order_teacher"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的订单</span>
					<em class="icon-2-14 u-up-down ml15">&nbsp;</em>
				</dt> 
				<dd> 
					<ol> 
						<li>
							<a title="全部订单" href="${ctx}/uc/teacherOrder?trxStatus=all" long="trxorder_all">全部订单</a>
						</li> 
						<li>
							<a title="待支付" href="${ctx}/uc/teacherOrder?trxStatus=INIT" long="trxorder_INIT">待支付</a>
						</li>
						<li>
							<a title="上课中" href="${ctx}/uc/teacherOrder?trxStatus=SUCCESS" long="trxorder_SUCCESS">上课中</a>
						</li> 
						<li>
							<a title="已结束" href="${ctx}/uc/teacherOrder?trxStatus=FINISH" long="trxorder_FINISH">已结束</a>
						</li> 
					</ol> 
				</dd> 
			</dl>
			<dl id="leftdl_setting_teacher"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">个人设置</span>
					<em class="icon-2-14 u-up-down ml15 ">&nbsp;</em>
				</dt> 
				<dd> 
					<ol> 
						<li>
							<a title="资料设置" href="${ctx}/uc/teacher/material/tosetting?materialType=base">资料设置</a>
						</li> 
						<li>
							<a title="认证设置" href="${ctx}/uc/teacher/aptitude/toattestation">认证设置</a>
						</li> 
						<li>
							<a title="地址管理" href="${ctx}/uc/address">地址管理</a>
						</li> 
					</ol> 
				</dd> 
			</dl>  
			<dl id="leftdl_setting_course"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我要开课</span>
					<em class="icon-2-14 u-up-down ml15 ">&nbsp;</em>
				</dt>
				<dd> 
					<ol> 
						<li>
							<a title="一对一开课" href="${ctx}/uc/teacher/ontToOne/list">一对一开课</a>
						</li> 
						<li>
							<a title="小班开课" href="${ctx}/uc/teacher/classCourse/list">小班开课</a>
						</li> 
						<li>
							<a title="课表设置" href="${ctx}/uc/teacher/times/publish">课表设置</a>
						</li> 
						
					</ol> 
				</dd> 
			</dl> 
			<dl id="leftdl_wallet_teacher"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的钱包</span>
					<em class="icon-2-14 u-up-down ml15">&nbsp;</em>
				</dt> 
				<dd> 
					<ol> 
						<li>
							<a title="我的账户" href="${ctx}/uc/teacher/acc">我的账户</a>
						</li> 
						<li>
							<a title="优惠券" href="${ctx}/uc/teacher/coupon">优惠券</a>
						</li> 
						<li>
							<a title="我的积分" href="${ctx}/uc/myinte">我的积分</a>
						</li> 
					</ol> 
				</dd> 
			</dl>
			<dl id="leftdl_honorteacher"> 
				<dt class="" onclick="window.location.href='${ctx}/uc/evaluation'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的信用</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt>  
			</dl> 
			<dl id="leftdl_teaching_journal"> 
				<dt class="" onclick="javascript:window.location.href='${ctx}/uc/teacher/mystudent/list'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的学生</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl>
			<dl id="leftdl_Coursevideo"> 
				<dt class="">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">账户设置</span>
					<em class="icon-2-14 u-up-down ml15">&nbsp;</em>
				</dt>
				<dd> 
					<ol> 
						<li>
							<a title="基本信息" href="${ctx }/uc/user/uinfo">基本信息</a>
						</li> 
						<li class="updatePwd">
							<a title="修改密码" href="${ctx }/uc/user/uppwd">修改密码</a>
						</li> 
						<li>
							<a title="修改头像" href="${ctx }/uc/user/avatar">修改头像</a>
						</li> 
						<li>
							<a title="绑定手机" href="${ctx }/uc/user/jumpmobile">绑定手机</a>
						</li> 
					</ol> 
				</dd>  						
			</dl>  
			<dl id="leftdl_news_teacher"> 
				<dt class="" onclick="window.location.href='${ctx}/uc/letter'">
					<em class="icon-2-18 u-m-icon-2">&nbsp;</em>
					<span class="c-333 fsize18 vam f-fM">我的消息</span>
					<!-- <em class="icon-2-14 u-up-down ml15">&nbsp;</em> -->
				</dt> 
			</dl>
		</div>
	</section>
</menu>

