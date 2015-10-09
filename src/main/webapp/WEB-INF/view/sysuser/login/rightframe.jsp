<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame.css?v=${v}"/>
<link rel="stylesheet" type="text/css" href="${ctximg}/static/common/admin/css/common_frame_right.css?v=${v}"/>
<script src="${ctximg}/static/common/jquery-1.11.1.min.js?v=${v}" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctximg}/static/common/admin/js/jquery.epiclock.js"></script>
<style>
.main_content {
    height: auto !important;
    padding: 20px;
    position: relative;
}
label {
    display: inline;
    margin-bottom: 5px;
}
</style>

<script type="text/javascript"> 
$(function(){
	    $('.epiClock').epiclock({ format : ' Y年F月j日　G:i:s　D' });
	    $.epiclock();
	});
</script>
</head>
<body>
 <!-- start right-content -->
  <div class="main_content" >
  		<div>
				<div class="page_head">
					<h4><font color="red "><c:out value="${userName}" /></font>&nbsp;欢迎你，今天是&nbsp;&nbsp;<span class="epiClock"></span> </h4>
				</div>
				<div class="mt20">
					    ${websitemap.web.company}
                       <%-- <div class="welcomWrap">
                            <a href="javascript:void(0)" class="disIb"></a>
                        </div>--%>
				</div>
			</div>
    </div>
  <!--  end right-content -->
</body>
</html>