<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<script type="text/javascript" src="/static/common/qrcode/jquery.qrcode.min.js"></script>

<script type="text/javascript"> 
    var link='${wxPayUrl}';        
	$(document).ready(function(){    
		 try
		 {  
		      document.createElement('canvas').getContext('2d');
		      $('#qrcode').qrcode({render:"canvas",height:160, width:160,correctLevel:0,text:link});
		 } catch (e)
		 {
		     $('#qrcode').qrcode({render:"table",height:160, width:160,correctLevel:0,text:link});
		 }      
   });   

	
	function reviewTrxOrder(){
		$.ajax({
			url:'/order/review',
			data:{"requestId":requestId},
			type:'post',
			dataType:'json',
			success:function(result){
				if(result.message=="true"){
					window.location.href='/front/success?msg=订单支付成功';
				}
			}
		});
	}
	    
</script>
<style>
	.QRcode-lt-ie9 {width: 160px;height: 160px;margin: 0 auto 10px;}
	.QRcode-lt-ie9 table {border-collapse:collapse;border-spacing:0;empty-cells:show;width: 100%;border: 0;}
	.QRcode-lt-ie9 table td {overflow: hidden;line-height: 2px;font-size: 0;}
</style>
</head>
<body class="scrol">
		
		 <div class="mt30">
			 <div class="pb20"  >
			 	<section class="mt20 mr20 mb20 ml20">
					<div class=" pr ml20">
						<ol>
							<li class="tac">
								<div id="qrcode" class="QRcode-lt-ie9"></div>
								<div id="wxStr" class="wxTips">
									<img src="${ctx}/static/edu/images/buy/wx_01.png">
									<span class="c-666 f-fM fsize14 ml10">请使用微信扫描二维码以完成支付</span>
								</div>
							</li>
							<li class="mt20" style="text-align: center">
								<span class="c-333 fsize14">您现在可以：
									<%--<a id="repaylink" class="c-333 mr10" title="查看订单信息" href="/front/repay/${orderId }">
										<u>返回修改支付方式</u>
									</a> |--%>
									<a class="c-4e ml10 mr10" title="进入学习中心" href="${ctx }/uc/home"><u>进入个人中心</u>
									</a> |
									<a class="c-4e ml10" title="返回首页" href="${ctx }/"><u>返回首页</u>
									</a>
								</span>
							</li>
						</ol>
					</div>
				</section>
			</div>
		</div> 
		
</body>
</html>
