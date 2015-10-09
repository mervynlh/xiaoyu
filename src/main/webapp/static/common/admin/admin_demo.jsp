<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8" http-equiv="Content-Type"/>
<title>ceshi</title>
	<meta http-equiv="X-UA-Compatible" content="IE=9, IE=8" /> 
	<meta name="application-name" content="" /> 
	<meta name="description" content="" /> 
	<meta name="application-url" content="" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}" />
    <link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" />
    <script type="text/javascript" src="${ctximg}/static/common/admin/js/admin-268xue.js?v=${v}" /></script>


</head>
<body>
	
	<div id="layout">
			<div class="page_head">
				<h4><em class="icon14 i_01"></em>&nbsp;<span>用户管理</span> &gt; <span>用户列表</span> </h4>
			</div>
			<!-- /tab1 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption><span><font>按用户名/姓名检索：</font></span><span><input type="text" name="" value="" placeholder=""><input type="submit" name="" value="查询" class="btn btn-y ml10"></span></caption>
						<thead>
							<tr>
								<th><span>用户名</span></th>
								<th><span>真实姓名</span></th>
								<th><span>部门</span></th>
								<th><span>状态</span></th>
								<th><span>联系电话</span></th>
								<th><span>加入时间</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_01">
							<tr>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
							</tr>
							<tr>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
							</tr>
							<tr>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
							</tr>
							<tr>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
							</tr>
						</tbody>
					</table>
					<!-- /pageBar begin -->
					<div class="mt20">
						<div class="clearfix">
							<div class="fr">
								<div class="pagination pagination-large">
									<ul>
										<li class="disabled"><a href="#">← Prev</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">Next →</a></li>
									</ul>
								</div>
							</div>
							<div class="pageDesc fl">
								<span>共查询到&nbsp;4&nbsp;条记录，当前第&nbsp;1/12&nbsp;页</span>
							</div>
						</div>
					</div>
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
			<!-- /tab1 end-->
			<!-- /tab2 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="capHead">
								<div class="clearfix">
									<div class="optionList">
										<span><font>项目专业：</font></span>
										<span>
											<select name="">
												<option value="0">—— 请选择 ——</option>
												<option value="1">会计上岗系列</option>
												<option value="2">Toefl考试系列</option>
											</select>
										</span>
									</div>
									<div class="optionList">
										<span><font>商品状态：</font></span>
										<span>
											<select name="" style="width: 100px;">
												<option value="0">—— 全部 ——</option>
												<option value="1">正常</option>
												<option value="2">冻结</option>
											</select>
										</span>
									</div>
									<div class="optionList">
										<span><font>显示位置：</font></span>
										<span>
											<select name="">
												<option value="0">—— 全部 ——</option>
												<option value="1">正常</option>
												<option value="2">冻结</option>
											</select>
										</span>
									</div>
									<div class="optionList">
										<span><font>显示属性：</font></span>
										<span>
											<select name="">
												<option value="0">—— 全部 ——</option>
												<option value="1">正常</option>
												<option value="2">冻结</option>
											</select>
										</span>
									</div>
									<div class="optionList">
										<span><font>banner图片：</font></span>
										<span>
											<select name="" style="width: 100px;">
												<option value="0">—— 全部 ——</option>
												<option value="1">正常</option>
												<option value="2">冻结</option>
											</select>
										</span>
									</div>
									<div class="optionList">
										<span><font>主讲教师：</font></span>
										<span>
											<input type="text" name="" value="" placeholder="">
										</span>
									</div>
									<div class="optionList">
										<span><font>关键字：</font></span>
										<span>
											<input type="text" name="" value="" placeholder="">
										</span>
									</div>
									<div class="optionList">
										<input type="submit" class="btn btn-danger" value="查询" name="">
									</div>
								</div>
							</div>
							<div class="mt10 clearfix">
								<p class="fl czBtn">
									<span><a href="" title=""><em class="icon14 new">&nbsp;</em>新建</a></span>
									<span class="ml10"><a href="" title=""><em class="icon14 delete">&nbsp;</em>删除</a></span>
									<span class="ml10"><a href="" title=""><em class="icon14 frost">&nbsp;</em>冻结</a></span>
									<span class="ml10"><a href="" title=""><em class="icon14 export">&nbsp;</em>导出数据表</a></span>
								</p>
								<p class="fr c_666"><span>商品列表</span><span class="ml10">共查询到&nbsp;4&nbsp;条记录，当前第&nbsp;1/12&nbsp;页</span></p>
							</div>
						</caption>
						<thead>
							<tr>
								<th><span><input type="checkbox">&nbsp;ID</span></th>
								<th><span>商品名称</span></th>
								<th><span>所属项目</span></th>
								<th><span>主讲教师</span></th>
								<th><span>课时</span></th>
								<th><span>价格</span></th>
								<th><span>创建时间</span></th>
								<th><span>显示位置</span></th>
								<th><span>显示属性</span></th>
								<th><span>banner图片</span></th>
								<th><span>状态</span></th>
								<th><span>售卖数量</span></th>
								<th><span>浏览数量</span></th>
								<th><span>更新时间</span></th>
								<th><span>创建人</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody id="tabS_02">
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox">&nbsp;863</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td>data</td>
								<td class="c_666 czBtn" align="center">
									<a href="" title="查看"><em class="icon14 check">&nbsp;</em></a>
									<a href="" title="修改"><em class="icon14 new">&nbsp;</em></a>
									<a href="" title="删除"><em class="icon14 delete">&nbsp;</em></a>
									<a href="" title="冻结"><em class="icon14 frost">&nbsp;</em></a>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- /pageBar begin -->
					<div class="mt20">
						<div class="clearfix">
							<div class="fr">
								<div class="pagination pagination-large">
									<ul>
										<li class="disabled"><a href="#">← Prev</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">Next →</a></li>
									</ul>
								</div>
							</div>
							<div class="pageDesc fl">
								<p class="czBtn">
									<span><a href="" title=""><em class="icon14 delete">&nbsp;</em>删除</a></span>
									<span class="ml10"><a href="" title=""><em class="icon14 frost">&nbsp;</em>冻结</a></span>
									<span class="ml10"><a href="" title=""><em class="icon14 export">&nbsp;</em>导出数据表</a></span>
									<span class="ml10"><a href="" title=""><em class="icon14 new">&nbsp;</em>新建</a></span>
									<span class="ml50">共查询到&nbsp;4&nbsp;条记录，当前第&nbsp;1/12&nbsp;页</span>
								</p>
							</div>
						</div>
					</div>
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
			<!-- /tab2 end-->
			<!-- /tab3 begin-->
			<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
							<div class="capHead">
								<div class="w50pre fl">
									<ul class="ddBar">
										<li><span class="ddTitle"><font>订单编号：</font></span><input type="text" name="" value="" placeholder="" class="ml10"></li>
										<li><span class="ddTitle"><font>邮箱地址：</font></span><input type="text" name="" value="" placeholder="" class="ml10"></li>
										<li>
											<span class="ddTitle"><font>下单开始时间：</font></span>
											<input type="text" name="" value="" placeholder="" class="ml10">
											<select name="" style="width: 100px;margin-left: 5px;">
												<option value="">00:00:00</option>
												<option value="">00:00:00</option>
											</select>
											<span class="c_666">&nbsp;&nbsp;(时:分:秒)</span>
										</li>
										<li>
											<span class="ddTitle"><font>付款开始时间：</font></span>
											<input type="text" name="" value="" placeholder="" class="ml10">
											<select name="" style="width: 100px;margin-left: 5px;">
												<option value="">00:00:00</option>
												<option value="">00:00:00</option>
											</select>
											<span class="c_666">&nbsp;&nbsp;(时:分:秒)</span>
										</li>
										<li>
											<span class="ddTitle"><font>优惠券：</font></span>
											<select name="" class="ml10">
												<option value="">——请选择——</option>
												<option value="">00:00:00</option>
											</select>
										</li>
									</ul>
								</div>
								<div class="w50pre fl">
									<ul class="ddBar">
										<li>
											<span class="ddTitle"><font>支付类型：</font></span>
											<select name="" class="ml10">
												<option value="">00:00:00</option>
												<option value="">00:00:00</option>
											</select>
										</li>
										<li>
											<span class="ddTitle"><font>订单状态：</font></span>
											<select name="" class="ml10">
												<option value="">00:00:00</option>
												<option value="">00:00:00</option>
											</select>
										</li>
										<li>
											<span class="ddTitle"><font>下单开始时间：</font></span>
											<input type="text" name="" value="" placeholder="" class="ml10">
											<select name="" style="width: 100px;margin-left: 5px;">
												<option value="">00:00:00</option>
												<option value="">00:00:00</option>
											</select>
											<span class="c_666">&nbsp;&nbsp;(时:分:秒)</span>
										</li>
										<li>
											<span class="ddTitle"><font>付款开始时间：</font></span>
											<input type="text" name="" value="" placeholder="" class="ml10">
											<select name="" style="width: 100px;margin-left: 5px;">
												<option value="">00:00:00</option>
												<option value="">00:00:00</option>
											</select>
											<span class="c_666">&nbsp;&nbsp;(时:分:秒)</span>
										</li>
										<li>
											<span class="ddTitle"><font>专业：</font></span>
											<select name="" class="ml10">
												<option value="">——请选择——</option>
												<option value="">00:00:00</option>
											</select>
											<span class="disIb ml10"><font>商品：</font></span>
											<select name="" class="ml10" style="width: auto;">
												<option value="">——请选择——</option>
												<option value="">00:00:00</option>
											</select>
										</li>
									</ul>
								</div>
								<div class="clear"></div>
							</div>
							<div class="mt10 clearfix">
								<p class="fl czBtn">
									<span><a title="" href=""><em class="icon14 check">&nbsp;</em>查询</a></span>
									<span class="ml10"><a title="" href=""><em class="icon14 delete">&nbsp;</em>清空</a></span>
									<span class="ml10"><a title="" href=""><em class="icon14 export">&nbsp;</em>导出数据表</a></span>
								</p>
								<p class="fr c_666"><span>订单列表</span><span class="ml10">共查询到&nbsp;0&nbsp;条记录，当前第&nbsp;0/0&nbsp;页</span></p>
							</div>
						</caption>
						<thead>
							<tr>
								<th><span><input type="checkbox">&nbsp;ID</span></th>
								<th><span>商品名称</span></th>
								<th><span>所属项目</span></th>
								<th><span>主讲教师</span></th>
								<th><span>课时</span></th>
								<th><span>价格</span></th>
								<th><span>创建时间</span></th>
								<th><span>显示位置</span></th>
								<th><span>显示属性</span></th>
								<th><span>banner图片</span></th>
								<th><span>状态</span></th>
								<th><span>售卖数量</span></th>
								<th><span>浏览数量</span></th>
								<th><span>更新时间</span></th>
								<th><span>创建人</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="16" align="center">
									<div class="tips">
										<span>还没有订单数据！</span>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- /pageBar begin -->
					<div class="mt20">
						<div class="clearfix">
							<div class="fr">
								<div class="pagination pagination-large">
									<ul>
										<li class="disabled"><a href="#">← Prev</a></li>
										<li class="disabled"><a href="#">Next →</a></li>
									</ul>
								</div>
							</div>
							<div class="pageDesc fl">
								<span class="ml50">共查询到&nbsp;0&nbsp;条记录，当前第&nbsp;0/0&nbsp;页</span>
							</div>
						</div>
					</div>
					<!-- /pageBar end -->
				</div><!-- /commonWrap -->
			</div>
			<!-- /tab3 end-->
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<caption>&nbsp;</caption>
					<thead>
						<tr>
							<th colspan="2" align="left"><span>课程基本属性，课程详细属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>&nbsp;所属项目专业</td>
							<td width="80%">
								<select name="">
									<option value="0">——请选择——</option>
									<option value="1">会计上岗系列</option>
									<option value="2">学历教育系列</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程名称</td>
							<td>
								<input type="text" name="" value="" placeholder="">
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;课程描述</td>
							<td>
								<textarea name="" style="width: 48%;height: 68px;"></textarea>
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;计划上传课时</td>
							<td>
								<input type="text" name="" value="" placeholder="">
							</td>
						</tr>
						<tr>
							<td align="center">实际上传课时</td>
							<td>
								<input type="text" name="" value="" placeholder="">
							</td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;主讲教师</td>
							<td>
								<span class="c_666 fsize12"><tt>林秀晶;</tt>&nbsp;&nbsp;<tt>李象线;</tt>&nbsp;&nbsp;<tt>林秀晶;</tt>&nbsp;&nbsp;<tt>李象线;</tt></span>
								<span class="ml50"><a href="javascript: void(0);" title="" class="btn" onclick="dialog();">添加教师</a></span><span><a href="" title="" class="ml10"><u>清空</a></span>
							</td>
						</tr>
						<tr>
							<td align="center">课程图片</td>
							<td>
								<span class="ml10"><a href="javascript:void(0)" title="上传图片" class="btn btn-success">上传图片</a></span>
							</td>
						</tr>
						<tr>
							<td align="center">课程详情</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<a href="" title="" class="btn btn-danger">提 交</a>
								<a href="" title="" class="btn ml10">重 置</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
	</div><!-- /layout -->
	
	
</body>
</html>