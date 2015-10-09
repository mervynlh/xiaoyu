<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>网站概况统计图</title>
<script type="text/javascript" src="${ctximg}/static/edu/js//highChart/highcharts.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
    <script type="text/javascript" src="${ctximg}/static/common/admin/js/jquery.epiclock.js"></script>
<script type="text/javascript" language="javascript">
    $(function(){
    	aEffect(); //animation fun 
        $('.epiClock').epiclock({ format : ' Y年F月j日　G : i : s　D' });
        $.epiclock();
    });

	$(function() {
		if('${courseOrderChart}'!=''){
			drawCartogram('coursePay','',eval("("+'${courseOrderChart}'+")"));//课程订单量
		}
		if('${memberOrderChart}'!=''){
			drawCartogram('memberOrder','',eval("("+'${memberOrderChart}'+")"));//会员订单
		}
		if('${userPayMemberChart}'!=''){
			drawCartogram('memberPay','',eval("("+'${userPayMemberChart}'+")"));//会员开通人数
		}
		if('${userPayCourseChart}'!=''){
			drawCartogram('courseOrder','',eval("("+'${userPayCourseChart}'+")"));//课程购买人数
		}
		drawCartogramLogin(15);
		if(${defaultSnsKey == 'on'}){
			drawCartogramsns();
		};

		drawCartogram_order();
		drawCartogram_user();
	});
	function aEffect() {
		var db = $(".dashBoradWrap"),
			dbu = db.children("ul"),
			dbli = dbu.children("li");
		db.addClass("animate-enter");
		dbli.addClass("animated scale originTopLeft");
	};
	//圆形图
	function drawCartogram(id,title,chart) {
		$('#'+id).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: title
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: false
	                },
	                showInLegend: true
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '百分比',
	            data: chart
	            
	        }]
	    });
	}
	//活跃度按天数
	function drawCartogramLogin(days) {
        var dateTime = '';
        var studentNum =  "" ;
        $.ajax({
            url:"/admin/statistics/web/detailajax",
            type:"post",
            data:{"days":days,"type":"LOGIN_NUM"},
            dataType:"json",
            async:false,
            success:function(result){
                if(result.success){
                    dateTime=result.entity;
                    studentNum= "["+result.message+"]" ;
                }else{
                    alert("请求错误!");
                }
            }
        })
        $('#container').highcharts({
		        title: {
		            text: '最近'+days+'天活跃学员',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '',
		            x: -20
		        },
		        xAxis: {
		        	categories : eval("(" + dateTime + ")")
		        },
            /*yAxis: {
                title: {
                    text: null
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
		            valueSuffix: '人'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		        	name : '活跃人数',
					data : eval("(" + studentNum + ")")
		        }]
		    });
	}

// 今天登录人数
function logintoday(){

 $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '今天活跃人数'
        },
        subtitle: {
            text: 'Date: '+ new Date()
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: 0,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '今天活跃人数: <b>{point.y} 人</b>'
        },
        series: [{
            name: 'Population',
            data: [
                ['活跃人数', ${todayloginnum}]
            ],
            dataLabels: {
                enabled: true,
                rotation: -360,
                color: '#FFFFFF',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
    });

}
//今天登录人数 ==/


	// sns ====
	function drawCartogramsns() {
    		var strs = new Array();
    		strs = '${chartsns}'.split("|");
    		var weiboNum= strs[0] ;//发表观点数
    		var blogNum= strs[1] ;//博文数
    		var assesNum=strs[2];//课程评论数
    		var quesNum=strs[3];//问题数
    		$('#containersns').highcharts({
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
    	// sns ======/




function drawCartogram_user() {
        var dateTime = '';
        var studentNum =  "" ;
        $.ajax({
            url:"/admin/statistics/web/detailajax",
            type:"post",
            data:{"days":30,"type":"REGISTER_NUM"},
            dataType:"json",
            async:false,
            success:function(result){
                if(result.success){
                    dateTime=result.entity;
                    studentNum= "["+result.message+"]" ;
                }else{
                    alert("请求错误!");
                }
            }
        })
        $('#container_user').highcharts({
		        title: {
		            text: '注册学员',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '',
		            x: -20
		        },
		        xAxis: {
		        	categories : eval("(" + dateTime + ")")
		        },
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
		            valueSuffix: '人'
		        },
		        legend: {
		            layout: 'vertical',
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		        	name : '学员',
					data : eval("(" + studentNum + ")")
		        }]
		    });
	}



function drawCartogram_order() {
        var dateTime = '';
        var studentNum =  "" ;
        $.ajax({
            url:"/admin/statistics/web/detailajax",
            type:"post",
            data:{"days":30,"type":"COURSE_NUM"},
            dataType:"json",
            async:false,
            success:function(result){
                if(result.success){
                    dateTime=result.entity;
                    studentNum= "["+result.message+"]" ;
                }else{
                    alert("请求错误!");
                }
            }
        })
        $('#container_order').highcharts({
		        title: {
		            text: '课程订单',
		            x: -20 //center
		        },
		        subtitle: {
		            text: '',
		            x: -20
		        },
		        xAxis: {
		        	categories : eval("(" + dateTime + ")")
		        },
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
		            align: 'right',
		            verticalAlign: 'middle',
		            borderWidth: 0
		        },
		        series: [{
		        	name : '订单',
					data : eval("(" + studentNum + ")")
		        }]
		    });
	}


</script>
    <style>
        label.epiClock {
            display: inline;
        }
    </style>
</head>
<body >
			<!-- 内容 开始  -->
            <div class="page_head" style=" position: relative; padding: 10px 20px;">
                <h4><font><c:out value="${sysuser.userName}" /></font>&nbsp;欢迎您，今天是&nbsp;&nbsp;<span class="epiClock"></span> </h4>
            </div>

            <div>
            	<!-- 仪表盘 开始 -->
            	<div class="dashBoradBox">
	            	<div class="dashBoradWrap">
	            		<ul class="clearfix">
	            			<li>
	            				<a href="javascript: void(0)" class="db-ico-1">
            						<span class="db-ico">&nbsp;</span>
            						<p class="db-txt-1">${websitemap.web.company}<br>后台管理平台</p>
            					</a>
	            			</li>
	            			<li>
	            				<a href="javascript: void(0)" class="db-ico-2">
            						<span class="db-ico">&nbsp;</span>
            						<p class="db-txt-2"><span class="">历史订单总数：</span><span class="db-num-big"><fmt:formatNumber pattern="#0" value="${courseOrderNum}"/></span></p>
            						<p class="db-txt-3"><span>今日新增订单数：</span><tt>${todayOrderNum}</tt><span> 单</span></p>
            					</a>
	            			</li>
	            			<li>
	            				<a href="javascript: void(0)" class="db-ico-4">
            						<span class="db-ico">&nbsp;</span>
            						<p class="db-txt-2"><span class="">历史注册会员数：</span><span class="db-num-big">${historyRegisterNum }</span></p>
            						<p class="db-txt-3"><span>今日新增会员数：</span><tt>${todayRegisterNum }</tt><span> 位</span></p>
            					</a>
	            			</li>
	            			
	            		</ul>
	            	
	            	</div>
            	</div>
            	<!-- 仪表盘 结束 -->


            	 <div class="page_head">
                					<h4><em class="icon14 i_01"></em>学员活跃度走势
                					</h4>
				</div>
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption><span><input type="button" onclick="logintoday()" value="今天" class="btn btn-y ml10">
								<input type="button" onclick="drawCartogramLogin(7)" value="最近7天" class="btn btn-y ml10">
								 <input type="button" onclick="drawCartogramLogin(30)" value="最近30天" class="btn btn-y ml10">
								</span>
							</caption>
							<tbody >
							<tr align="center">
								<td>
									<div id="container" style="max-width: 85%;margin: auto;height: 260px;"></div>
								</td>
							</tr>
							</tbody>
						</table>
					</div><!-- /commonWrap -->
				</div>



            	<!-- 订单会员数据分析-->
            	 <div class="page_head">
						<h4><em class="icon14 i_01"></em>订单会员数据分析
						</h4>
				</div>
                <div class="commonWrap">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
                        <caption></caption>
                        <thead>
                        <tr>
                            <th><span>课程订单量</span></th>
                            <th><span>会员订单</span></th>
                        </tr>
                        </thead>

                        <tbody id="tabS_01">
                        <tr align="center">
                            <td>
                                <c:if test="${userPayCourseChart!=''}">
                                   <%-- margin: 0 20px;--%>
                                    <span id="coursePay" style="min-width: 210px; height: 220px; max-width: 600px; display: inline-block; "></span>
                                </c:if>
                                <c:if test="${userPayCourseChart==''}">
                                    <div class="tips">
                                        <span>暂无购买课程人数统计数据。</span>
                                    </div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${courseOrderChart!=''}">
                                    <span id="courseOrder" style="min-width: 210px;height: 220px; max-width: 600px; margin: 0 20px;display: inline-block;  "></span>
                                </c:if>
                                <c:if test="${courseOrderChart==''}">
                                    <div class="tips">
                                        <span>暂无开通课程人数统计数据。</span>
                                    </div>
                                </c:if>
                            </td>
                           
                        </tr>
                        </tbody>
                    </table>
                </div><!-- /commonWrap -->
            </div>
            <!-- /tab1 end-->

<!--社区活跃度走势 -->
<c:if test="${defaultSnsKey == 'on'}">
  <div class="mt20">
		<form action="${ctx}/admin/statistics/sns" name="searchForm" id="searchForm" method="post">
			<!-- 内容 开始  -->
				<div class="page_head">
					<h4>
						<em class="icon14 i_01"></em>社区活跃度走势
					</h4>
				</div>
				<!-- /tab1 begin-->
				<div class="mt20">
					<div class="commonWrap">
						<table cellspacing="0"  cellpadding="0" border="0" width="100%" class="commonTab01">
							<caption>
							</caption>
							<thead>
							</thead>
							<tbody>
                                <tr><td><div id="containersns"></div></td></tr>
							</tbody>
						</table>
					</div>
				</div>
		</form>
</div>
</c:if>


 		<div class="page_head">
				<h4><em class="icon14 i_01"></em>学员注册下单走势
				</h4>
		</div>
		<div class="mt20">
				<div class="commonWrap">
					<table cellspacing="0" cellpadding="0" border="0" width="100%" class="commonTab01">
						<caption>
						</caption>
						<tbody >
						<tr>
							<td width="50%">
								<div id="container_user" style="height: 360px;"></div>
							</td>
							<td width="50%">
							<div id="container_order" style="height: 360px;"></div>
							</td>
						</tr>
						</tbody>
					</table>
				</div><!-- /commonWrap -->
			</div>


<div class="page_head">
	<h4></h4>
</div>

</body>
</html>


