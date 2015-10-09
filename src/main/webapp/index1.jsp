<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>


 <head>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
 	<title>11111111111111111111${websitemap.web.company}-${websitemap.web.title}</title>
 	<meta name="author" content="${websitemap.web.author}"/>
 	<meta name="keywords" content="${websitemap.web.keywords}" />
 	<meta name="description" content="${websitemap.web.description}"/>
 	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
 	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/common.css">
 	<link rel="stylesheet" type="text/css" href="${ctximg}/static/edu/css/page-style.css">
 	<script type="text/javascript" src="${ctximg}/static/common/jquery-1.11.1.min.js?v=<%=version%>"></script>
 	<script type="text/javascript" src="${ctximg}/static/common/pageJs.js?v=<%=version%>"></script>
 	<script type="text/javascript" src="${ctximg}/static/common/webutils.js?v=<%=version%>"></script>
 	<script type="text/javascript" src="${ctximg}/static/common/web_top.js?v=<%=version%>"></script>
 	<script type="text/javascript" src="${ctximg}/static/common/header_msg.js?v=<%=version%>"></script>
     <script type="text/javascript" src="${ctximg}/static/common/emailList.js"></script>
 	<!--[if lt IE 9]><script src="${ctximg}/static/common/html5.js"></script><![endif]-->
 	<script type="text/javascript">
 		var baselocation = "${ctx}";
 		var imagesPath = "${ctximg}";
 		var usercookiekey="<%=usercookiekey%>";
 		var mydomain="<%=mydomain%>";
 		var keImageUploadUrl="<%=keImageUploadUrl%>";//kindeditor中使用的路径需要2个参数来区分项目和模块
 		var uploadSimpleUrl="<%=uploadSimpleUrl%>";//单独的上传按钮使用的路径
 		var uploadSwfUrl="<%=uploadSwfUrl%>";//uplopad的js上传使用的路径
         var staticImageServer ="<%=staticImageServer%>";//上传后返回路径
         var loginkeyword='${keywordmap.keyword.verifyLogin}';
 	</script>




 </head>
 <body class="W-body">

	<iframe width="100%" height="1000px"  style="display:" src="http://openapi.qzone.qq.com/oauth/show?which=ConfirmPage&display=pc&client_id=101070993&redirect_uri=http://t.268xue.com/app/loginReturn&response_type=code&state=2cbd6cd115d6327b4202e3477417ebab&scope=get_user_info"></iframe>


<script type="text/javascript" >
</script>
 </body>
 </html>