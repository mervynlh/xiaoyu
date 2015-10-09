<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>授课地址</title>
	<script type="text/javascript" src="${ctx}/static/edu/js/ucenter/address.js"></script>
	<script type="text/javascript">
		$(function(){
			initArea(1, 0,0);
		});
	</script>
</head>
<body>

<div class="u-m-right">
	<article class="u-m-center"> 
		<section class="u-m-c-wrap"> 
			<section class="u-t-add">
				<form id="addAddress" action="${ctx}/uc/address/add" method="post">
				<input id="id" type="hidden" name="userAddress.id" value="" lang=""/>
				<input id="provinceId" type="hidden" name="userAddress.provinceId" value="" lang=""/>
				<input id="cityId" type="hidden" name="userAddress.cityId" value="" lang=""/>
				<input id="townId" type="hidden" name="userAddress.townId" value="" lang=""/>
				<input id="lng" type="hidden" name="userAddress.lng" value="" lang=""/>
				<input id="lat" type="hidden" name="userAddress.lat" value="" lang=""/>
				<input id="allLocation" type="hidden" value=""/>
				
				<div class="u-t-add-tit">
					<div class="u-t-add-tit-l fl">
						<p class="fsize18 c-master f-fM hLh30">新增面授地点：</p>
						<div id="select-1" class="mt20">
							<div class="selectUI fl">
								<div class="job-select" style="width:145px;">
									<section class="j-s-defalt">
										<em class="icon14 fr mt5">&nbsp;</em>
										<span id="provinceTip">请选择省份</span>
									</section>
									<section style="display: none;" class="j-s-option">
										<div class="j-s-o-box" id="provinceOption"></div>
									</section>
								</div>
							</div>
							<div class="selectUI fl">
								<div style="width:145px;" class="job-select">
									<section class="j-s-defalt">
										<em class="icon14 fr mt5">&nbsp;</em>
										<span id="cityTip">请选择城市</span>
									</section>
									<section class="j-s-option">
										<div class="j-s-o-box" id="cityOption"></div>
									</section>
								</div>
							</div>
							<div class="selectUI fl">
								<div style="width:145px;" class="job-select">
									<section class="j-s-defalt" >
										<em class="icon14 fr mt5">&nbsp;</em>
										<span id="townTip">请选择城镇</span>
									</section>
									<section class="j-s-option">
										<div class="j-s-o-box" id="townOption"></div>
									</section>
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<div class="u-t-add-l-t mt10">
							<textarea id="address" maxlength="" name="userAddress.address" class="vam" placeholder="不需要重复填写省市区，为保证定位可靠，建议提供精确到街道的地址" disabled="disabled"></textarea>
							<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
						</div>
						<div class="u-t-add-l-b mt10">
							<div class="fl">
								<input type="checkbox" name="userAddress.isFirst" value="1" class="c-icon">
								<tt for="forget" class="vam c-666 fsize12 f-fM">设为常用地址</tt>
							</div>
							<div class="fr u-t-add-l-b">
<!-- 								<a href="javascript:void(0)" onclick="mapShow()"> -->
<!-- 									<em class="icon18 icon-bj vam">&nbsp;</em> -->
<!-- 									<tt class="fsize14 c-org f-fM vam">地图定位</tt> -->
<!-- 								</a> -->
							</div>
							<div class="clear"></div>
							<div class="u-t-add-l-b mt20">
								<a href="javascript:void(0)" onclick="save()" class="c-btn bc-btn">保存</a>
							</div>
						</div>
					</div>
					<div id="mapShow" class="u-t-add-tit-r fl">
						<div class="u-t-add-tit-r-in ml30 mt30">
							<a herf="" class="ckdt">
								查看大图
							</a>
							<div class="bcwz"></div> 
							<div id="map" class="map"></div>
<!-- 							<div class="map-btn"> -->
<!-- 								<a href="" class="c-btn wz-btn">保存位置</a> -->
<!-- 							</div> -->
						</div>	
					</div>
					<div class="clear"></div>
				</div>
				</form>
				<div class="u-t-add-boy mt50">
					<table cellspacing="0" cellpadding="0" border="0" style="width: 100%;" class="u-t-coupon-tab">
						<thead> 
							<tr> 
								<th style="width:20%;">
									所在地区
								</th> 
								<th style="width:50%;">
									详细地址
								</th>
								<th style="width:30%;">
									操作
								</th>
							</tr> 
						</thead>
						<tbody>
							<c:forEach items="${userAddressList}" var="address">
							<tr>
								<td>
									<span class="vam">
										<tt class="fsize14 f-fM c-999 vam mr5">${address.provinceStr} , 
										${address.cityStr} , ${address.townStr} </tt>
									</span>
								</td>
								<td>
									<span class="vam">
										<tt class="fsize14 f-fM c-999 vam mr5">${address.address}</tt>
									</span>
								</td>
								<td>
									<span class="u-teaname-btn disIb vam">
										<a href="javascript:void(0)" onclick="initUpdate(${address.isFirst},${address.id},'${address.address}',${address.provinceId},'${address.provinceStr}',${address.cityId},'${address.cityStr}',${address.townId},'${address.townStr}',${address.lng},${address.lat})" class="mr20">编辑</a>
										<a href="javascript:del(${address.id})">删除</a>
										<p class="">
											<tt class="fsize14 c-999 f-fM">
											<c:if test="${address.isFirst==1}">常用地址</c:if>
											<c:if test="${address.isFirst!=1}">
												<a href="${ctx}/uc/address/common/${address.id}" class="szdz">设为常用地址</a>
											</c:if>
											</tt>
										</p>
									</span>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</section>
		</section>   
	</article>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BUuYf2lWpYBQNVPu39PSZGBZ"></script>
<script type="text/javascript" src="${ctx}/static/edu/js/ucenter/map.js"></script>
<script src="${ctx}/static/edu/js/web/jquery.slides.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		selFun("#select-1");// 模拟 select 下拉控件
	});
 </script> 
</body>
</html>
