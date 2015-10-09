<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>社区活跃度统计图</title>
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
		//
	    drawCartogram();
		//}
		
	});
	function parseDay(val){
		if(val<10){
			return '0'+val;
		}else{
			return val;
		}
	}
	

	function drawCartogram() {
		var strs = new Array();
		strs = '${chart}'.split("|");
		var weiboNum= strs[0] ;//发表观点数
		var blogNum= strs[1] ;//博文数
		var assesNum=strs[2];//课程评论数
		var quesNum=strs[3];//问题数
		$('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '社区活跃走势图'
	        },
	        subtitle: {
	            text: ''
	        },
	        xAxis: {
	            categories: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	        },
	       /* yAxis: {
	            min: 0,
	            title: {
	                text: '活跃数'
	            },
                min:0
	        },*/
            yAxis: [{ // left y axis
                title: {
                    text: '活跃人数'
                },
                labels: {
                    align: 'left',
                    x: 3,
                    y: 16,
                    format: '{value:.,0f}'
                },
                showFirstLabel: false
            }, { // right y axis
                linkedTo: 0,
                gridLineWidth: 0,
                opposite: true,
                title: {
                    text:null
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
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '观点',
	            data: eval("("+weiboNum+ ")")

	        }, {
	            name: '博文',
	            data: eval("("+blogNum+ ")")

	        }, {
	            name: '评论',
	            data: eval("("+assesNum+ ")")

	        }, {
	            name: '问题',
	            data: eval("("+quesNum+ ")")

	        }]
	    });
	}
</script>
</head>
<body style="background: #F5F5F5;">
	
		<form action="${ctx}/admin/statistics/sns" name="searchForm" id="searchForm" method="post">
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>&nbsp;<span>统计管理</span> &gt; <span>社区活跃度统计图</span>
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0"  cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
								<div class="capHead">
									<div class="clearfix">
										<div class="optionList">
											<span><font>时间：</font></span> <span> 
											<select style="width: 120px;" id="nowYear" name="year"></select>
											</span>
										</div>
										<div class="optionList">
											<input type="submit" class="btn btn-danger" value="查询" />
										</div>
									</div>
								</div>
							</caption>
							<thead>
							</thead>
							<tbody>
                                <tr><td><div id="container"></div></td></tr>
							</tbody>
						</table>
					</div>
				</div>
		</form>

        <div class="mt20">
            <div class="commonWrap">
					<table class="commonTab01" width="100%" cellspacing="0" cellpadding="0" border="0">
                        <thead>
                        <tr>
                            <th><span>时间</span></th>
                            <th><span>观点</span></th>
                            <th><span>博文</span></th>
                            <th><span>评论</span></th>
                            <th><span>问题</span></th>
                        </tr>
                        </thead>
					<tbody>
                    <c:set var="t_weiboNum" value="0"></c:set>
                    <c:set var="t_blogNum" value="0"></c:set>
                    <c:set var="t_assesNum" value="0"></c:set>
                    <c:set var="t_quesNum" value="0"></c:set>

						<c:forEach items="${statistics }" var="statistic" varStatus="index">
                            <c:set var="t_weiboNum" value="${t_weiboNum+statistic.weiboNum}"></c:set>
                            <c:set var="t_blogNum" value="${t_blogNum+statistic.blogNum}"></c:set>
                            <c:set var="t_assesNum" value="${t_assesNum+statistic.assesNum}"></c:set>
                            <c:set var="t_quesNum" value="${t_quesNum+statistic.quesNum}"></c:set>
						<tr align="center">
							<td>${year}年
								<c:if test="${month==null }">${statistics.size()-index.index }月</c:if>
								<c:if test="${month!=null }">${month }月${statistics.size()-index.index }日</c:if>
							</td>
							<td>${statistic.weiboNum}</td>
							<td>${statistic.blogNum}</td>
							<td>${statistic.assesNum}</td>
							<td>${statistic.quesNum}</td>
						</tr>
						</c:forEach>
                    <tr align="center">
                        <td >合计 </td>
                        <td>${t_weiboNum}</td>
                        <td>${t_blogNum}</td>
                        <td>${t_assesNum}</td>
                        <td>${t_quesNum}</td>
                    </tr>
					</tbody>
					</table>
				</div>
			</div>
</body>
</html>


