<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>提现统计图</title>
<script type="text/javascript" src="${ctximg}/static/edu/js/highChart/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js//highChart/highcharts.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
<script type="text/javascript" language="javascript">
	$(function() {
		var obj = document.getElementById("nowYear");
		var date = new Date();
		for (var i = 0; i < 5; i++) {
			var opt = document.createElement("option");
			opt.value = date.getFullYear() - i;
			opt.innerHTML = (date.getFullYear() - i)+" 年";
			obj.appendChild(opt);
		}
		if('${year}'!=null){
			$("#nowYear").val('${year}');
		}
		if('${month}'!=null){
			$("#nowMonth").val('${month}');
		}
		drawCartogram();
	});
	
	function drawCartogram() {
		var strs = new Array();
		strs ='${chart}'.split("|");
		var dateTime = "[" + strs[0] + "]";
		var allCashOuts = "[" + strs[1] + "]";
		
		$('#container').highcharts({
	        title: {
	            text: '提现信息走势图',
	            x: -20 //center
	        },
	        subtitle: {
	            text: '',
	            x: -20
	        },
	        xAxis: {
	        	categories : eval("(" + dateTime + ")")
	        },
	       /* yAxis: {
	            title: {
	                text: '数量'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }],
                min:0
	        },*/
            yAxis: [{ // left y axis
                title: {
                    text: null
                },
                labels: {
                    align: 'left',
                    x: 3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: false,
                min:0
            }, { // right y axis
                linkedTo: 0,
                gridLineWidth: 0,
                opposite: true,
                title: {
                    text: null
                },
                labels: {
                    align: 'right',
                    x: -3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: false  ,
                min:0
            }],
	        tooltip: {
	            valueSuffix: '笔'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'left',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series : [ {
				name : '提现量',
				data : eval("(" + allCashOuts + ")")
			}]
	    });
	}
</script>
</head>
<body  >
		<form action="${ctx}/admin/statistics/cashout" name="searchForm" id="searchForm" method="post">
			<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>统计管理</span> &gt; <span>提现信息统计图</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
							<div class="mt20">
								<div class="commonWrap">
									<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
										<caption>
											<div class="capHead">
												<div class="clearfix">
													<div class="optionList">
														<span><font>时间：</font></span> <span> 
														<select  style="width: 120px;" id="nowYear" name="year"></select>
														<select style="width: 90px;" id="nowMonth" name="month">
                                                            <option value="">月份</option>
                                                            <option value="1">1月</option>
                                                            <option value="2">2月</option>
                                                            <option value="3">3月</option>
                                                            <option value="4">4月</option>
                                                            <option value="5">5月</option>
                                                            <option value="6">6月</option>
                                                            <option value="7">7月</option>
                                                            <option value="8">8月</option>
                                                            <option value="9">9月</option>
                                                            <option value="10">10月</option>
                                                            <option value="11">11月</option>
                                                            <option value="12">12月</option>
														</select>
														</span>
													</div>
													<div class="optionList">
														<input type="submit" class="btn btn-danger" value="查询" />
														<a href="/admin/cashout/cashoutList" class="btn btn-danger">提现列表</a>
													</div>
												</div>
											</div>
										</caption>
										<thead>
										</thead>
										<tbody>
                                        <tr><td>
                                            <div id="container" style="max-width: 96%;margin: auto;height: 240px;"></div>
                                        </td></tr>
										</tbody>
									</table>
								</div>
								<!-- /commonWrap -->
							</div>
					<!-- /commonWrap -->
				</div>
			</div>

			<!-- 内容 结束 -->
		</form>

        <div class="mt20">
            <div class="commonWrap">
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                    <thead>
                    <tr>
                        <th><span>时间</span></th>
                        <th><span>提现量</span></th>
                        <th><span>实付金额</span></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="t_courseNum" value="0"></c:set>
                    <c:set var="t_coursePayNum" value="0"></c:set>
                    <c:set var="t_coursePayAmount" value="0"></c:set>
                    <c:forEach items="${statistics }" var="statistic" varStatus="index">
                        <c:set var="t_courseNum" value="${t_courseNum+statistic.userCashOutNum}"></c:set>
                        <c:set var="t_coursePayAmount" value="${t_coursePayAmount+statistic.userCashOutAmount}"></c:set>
                        <tr align="center">
                            <td>${year}年
                                <c:if test="${month==null||month=='' }">${statistics.size()-index.index }月</c:if>
                                <c:if test="${month!=null&&month!='' }">${month }月${statistics.size()-index.index }日</c:if>
                            </td>
                            <td>${statistic.userCashOutNum}</td>
                            <td>${statistic.userCashOutAmount}</td>
                        </tr>
                    </c:forEach>
                    <tr align="center">
                        <td >合计 </td>
                        <td>${t_courseNum}</td>
                        <td>${t_coursePayAmount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- /commonWrap -->
        </div>


</body>
</html>
