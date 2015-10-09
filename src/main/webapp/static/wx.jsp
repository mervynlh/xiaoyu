<html xmlns="http://www.w3.org/1999/xhtml">  
<%@ include file="/base.jsp"%>
<head>  
    <title></title>  
    <script src="${ctx }/static/common/jquery-1.11.1.min.js" type="text/javascript"></script>  
    <script src="${ctx }/static/common/qrcode/qrcode-light.js" type="text/javascript"></script> 
    <script src="${ctx }/static/common/qrcode/qrgen.js" type="text/javascript"></script> 
    <script src="${ctx }/static/common/qrcode/doqrgen.js" type="text/javascript"></script> 
    <script type="text/javascript"> 
	    $(function(){
	    	var s=0.2;
	    	$('#qrcode').html("");
			var options={
				cellSize:'8',
				//colorDark:getColor(29,0,0),
				colorLight:'#ffffff',
				data:utf8Encode("1111111"),
			};
			options.effect={key:'round',value:s};
			new QRCanvas(options).appendTo(document.getElementById('qrcode')); 
	    });
    </script> 


</head>  
<body>  
 <div id="qrcode"></div>
</body>  
</html>  
