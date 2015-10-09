<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>教师课程统计图</title>
<script type="text/javascript" src="${ctximg}/static/edu/js//highChart/highcharts.js"></script>
<script type="text/javascript" src="${ctximg}/static/edu/js/highChart/highcharts-3d.js"></script>
<script type="text/javascript" language="javascript">
	$(function() {
		var obj = document.getElementById("nowYear");
		var date = new Date();
		for (var i = 0; i < 5; i++) {
			var opt = document.createElement("option");
			opt.value = date.getFullYear() - i;
			opt.innerHTML = (date.getFullYear() - i)+" 年";;
			obj.appendChild(opt);
		}
		if('${year}'!=null){
			$("#nowYear").val('${year}');
		}
		if('${month}'!=null){
			$("#nowMonth").val('${month}');
		}
		drawCartogram();
		drawCartogramTeacher();
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
		var dateTime = "[" + strs[0] + "]";
		var studentNum = "[" + strs[1] + "]";
		
		$('#container').highcharts({
	        title: {
	            text: '课程发布走势图',
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
	                text: '人数'
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
                    text: '课程发布数'
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
	            valueSuffix: '个'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	        	name : '发布课程',
				data : eval("(" + studentNum + ")")
	        }]
	    });
	}
	function drawCartogramTeacher() {
		var strs = new Array();
		strs = '${chart}'.split("|");
		var dateTime = "[" + strs[0] + "]";
		var studentNum = "[" + strs[2] + "]";
		
		$('#container_teacher').highcharts({
	        title: {
	            text: '教师入驻走势图',
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
	                text: '人数'
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
                    text: '入驻人数'
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
	        	name : '入驻人数',
				data : eval("(" + studentNum + ")")
	        }]
	    });
	}
</script>
</head>
<body >

			<!-- 内容 开始  -->
            <div class="page_head">
                <h4>
                    <em class="icon14 i_01"></em>&nbsp;<span>统计管理</span> &gt; <span>教师课程统计图</span>
                </h4>
            </div>
				<!-- /commonWrap -->
                <form action="${ctx}/admin/statistics/register" name="searchForm" id="searchForm" method="post">
                <div class="mt20">
                    <div class="commonWrap">
                        <table cellspacing="0"  cellpadding="0" border="0" width="100%" class="commonTab01">
                            <caption>
                                <div class="capHead">
<!--                                     <div class="clearfix"> -->
<!--                                         <div class="optionList"> -->
<!--                                             <span><font>时间：</font></span> <span> -->
<!-- 											<select style="width: 120px;" id="nowYear" name="year"></select> -->
<!-- 											</span> -->
<!--                                         </div> -->
<!--                                         <div class="optionList"> -->
<!--                                             <input type="submit" class="btn btn-danger" value="查询" /> -->
<!--                                         </div> -->
<!--                                     </div> -->
								<input type="hidden" id='nowYear'/>
<!-- 								<select style="width: 120px;" id="nowYear" name="year"></select> -->
                                </div>
                            </caption>
                            <thead>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <div id="container" style="max-width:  96%;margin: auto;height: 240px"></div>
                                </td>
                            </tr>
                             <tr>
                                <td>
                                    <div id="container_teacher" style="max-width:  96%;margin: auto;height: 240px"></div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                </form>
</body>
</html>


