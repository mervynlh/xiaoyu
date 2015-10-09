<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!-- 公共尾部 -->
	<footer>
		<div class="foot-top">
			<div class="container">
				<section class="foot-list">
					<ul class="clearfix pb20">
						<li>
							<div class="foot-list-in">
								<h4 class="hLh30 of unFw">我是学生</h4>
								<a href="${ctx}/help?id=172" target="blank" class="fsize14 c-999 f-fM dis mt10">如何注册</a>
								<a href="${ctx}/help?id=173" target="blank" class="fsize14 c-999 f-fM dis mt10">如何学习</a>
								<a href="${ctx}/help?id=174" target="blank" class="fsize14 c-999 f-fM dis mt10">如何互动</a>
							</div>
						</li>
						<li>
							<div class="foot-list-in">
								<h4 class="hLh30 of unFw">我是老师</h4>
								<a href="${ctx}/help?id=178" target="blank" class="fsize14 c-999 f-fM dis mt10">发布课程</a>
								<a href="${ctx}/help?id=179" target="blank" class="fsize14 c-999 f-fM dis mt10">使用题库</a>
								<a href="${ctx}/help?id=184" target="blank" class="fsize14 c-999 f-fM dis mt10">教学资料库</a>
							</div>
						</li>
						<li>
							<div class="foot-list-in">
								<h4 class="hLh30 of unFw">我是管理员</h4>
								<a href="${ctx}/help?id=181" target="blank" class="fsize14 c-999 f-fM dis mt10">系统设置</a>
								<a href="${ctx}/help?id=182" target="blank" class="fsize14 c-999 f-fM dis mt10">课程设置</a>
								<a href="${ctx}/help?id=183" target="blank" class="fsize14 c-999 f-fM dis mt10">用户管理</a>
							</div>
						</li>
						<li>
							<div class="foot-list-in">
								<h4 class="hLh30 of unFw">课程发布指南</h4>
								<a href="${ctx}/help?id=186" target="blank" class="fsize14 c-999 f-fM dis mt10">定价规则</a>
								<a href="${ctx}/help?id=187" target="blank" class="fsize14 c-999 f-fM dis mt10">上课方式</a>
								<a href="${ctx}/help?id=188" target="blank" class="fsize14 c-999 f-fM dis mt10">平台协议</a>
							</div>
						</li>
						<li>
							<div class="foot-list-in">
								<h4 class="hLh30 of unFw">关于我们</h4>
								<a href="${ctx}/help?id=198" target="blank" class="fsize14 c-999 f-fM dis mt10">公司简介</a>
								<a href="${ctx}/help?id=199" target="blank" class="fsize14 c-999 f-fM dis mt10">官方微博</a>
								<a href="${ctx}/help?id=200" target="blank" class="fsize14 c-999 f-fM dis mt10">加入我们</a>
							</div>
						</li>
						<li>
							<div class="foot-list-ewm">
								<div>
									<span class="mr30">
										<img src="<%=imagesPath %>/static/edu/img/pic/ewm-wx.jpg" alt="官方微信（91xiaoyu）" title="官方微信（91xiaoyu）">
									</span>
									<span>
										<img src="<%=imagesPath %>/static/edu/img/pic/ewm-xl.jpg" alt="韩教授微信" title="韩教授微信">
									</span>
								</div>
								<div class="mt20">
									<em class="icon-34 icon-phone mr10 vam">&nbsp;</em>
									<span class="disIb c-999 f-fM fsize24 vam hLh30">${websitemap.web.phone}</span>
								</div>
							</div>
						</li>
					</ul>
				</section>
				<section class="friend">
					<dl class="clearfix" id="linkFriend">
						<dt>
							<span class="vam c-888">友情链接：</span>
						</dt>
						<c:forEach items="${navigatemap.FRIENDLINK}" var="friendLinkNavigate">
	       					<dd><a href="${friendLinkNavigate.url}" title="${friendLinkNavigate.name}" <c:if test="${friendLinkNavigate.newPage==0}">target="_blank"</c:if>>${friendLinkNavigate.name}</a></dd>
						</c:forEach>
					</dl>
				</section>
			</div>
		</div>
		<div class="foot-bottom">
			<section class="pt20 pb20 container"> 
				<ul class="tac">
					<li id="linkBottom">
							<c:forEach items="${navigatemap.TAB}" var="tabNavigate" varStatus="statu">
									<a target="_blank"  class="mr10 ml5" href="${tabNavigate.url}" title="${tabNavigate.name}" <c:if test="${tabNavigate.newPage==0}">target="_blank"</c:if>>${tabNavigate.name}</a>
									<c:if test="${!statu.last}">|</c:if>
								</c:forEach> 
						<span class="ml10"> 24小时服务热线：${websitemap.web.phone} Email：${websitemap.web.email} 工作时间：${websitemap.web.workTime}</span> 
					</li>
				</ul>
			</section>
		</div>
	</footer>
	<!-- 右侧浮动 -->
	<div class="onlineConsult-gld"> 
		<dl> 
			<dt style="display: none;"> 
				<a id="goTop" href="javascript: void(0)"> 
					<em class="ocgld-em gT-btn">&nbsp;</em> 
					<span class="fb-hover-text">返回顶部</span> 
				</a> 
			</dt> 
			<dd class="pr"> 
				<a class="smgz-a" href="JavaScript:void(0)"> 
					<em class="ocgld-em smgz-btn">&nbsp;</em> 
					<span class="fb-hover-text">官方微信</span> 
					<div id="scanAtten" class="smgz-pic"> 
						<img width="145" height="150" class="dis" src="<%=imagesPath %>/static/edu/img/pic/ewm-wx.jpg"> 
					</div> 
				</a> 
			</dd> 
			<dd> 
				<a target="_blank" id="online_zx" href=""> 
					<em class="ocgld-em zxzx-btn">&nbsp;</em> 
					<span class="fb-hover-text">在线咨询</span> 
				</a> 
			</dd> 
			<dd> 
				<a target="_blank" href="/front/to_free_back"> 
					<em class="ocgld-em wtfk-btn">&nbsp;</em> 
					<span class="fb-hover-text">问题反馈</span> 
				</a> 
			</dd> 
		</dl> 
	</div>