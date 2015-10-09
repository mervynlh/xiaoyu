<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="u-pageBar tac clearfix">
	<span id="pager_con">
	
	</span>
	<span class="ml50">
		<tt class="c-666">第<s:property value="page.currentPage"/>页/共<s:property value="page.totalPage"/>页</tt>
	</span>
</div>

<script type="text/javascript">
		var pageNo = <s:property value="page.currentPage"/>;//当前页码
		var totalPage = <s:property value="page.totalPage"/>;//总页码
		totalPage = totalPage < 1 ? 1 : totalPage;
	  	function showPages() {
	  		var startPage = 1;
	  		var html = '';
			if(pageNo > 3) {
				if((totalPage - pageNo)>3) {
					startPage = pageNo - 2;
				} else {
					startPage = totalPage - 5;
				}
				if(startPage < 1) {
					startPage = 1;
				}
			}
	
			if(pageNo == 1) {
				html += '<a href="javascript:void(0)" title="首页"><em class="left-go icon-2-16">&nbsp;</em></a><a href="javascript:void(0)" title="上一页" class="page-up b-fff">上一页</a>';
			} else {
				html += '<a href="javascript:goPage(1)" title="首页"><em class="left-go icon-2-16">&nbsp;</em></a><a href="javascript:goPage(' + (pageNo - 1) + ')" title="上一页" class="page-up b-fff">上一页</a>';
			}
			
	
			/* if((totalPage - startPage) <7) {
				for(var i=0; i <= (totalPage - startPage); i++) {
					if(pageNo == (startPage + i)) {
						html += '<li class="active"><a href="javascript:void(0)">' + (startPage+i) + "</a></li>";
					} else {
						html += "<li><a href='javascript:goPage(" + (startPage+i) + ")'>" + (startPage+i) + "</a></li>";
					}
				}
			} else {
				for(var i=0; i<4; i++) {
					if(pageNo == (startPage + i)) {
						html += '<li class="active"><a href="javascript:void(0)">' + (startPage+i) + "</a></li>";
					} else {
						html += "<li><a href='javascript:goPage(" + (startPage+i) + ")'>" + (startPage+i) + "</a></li>";
					}
				}
				html += '<li><span class="separator">&nbsp;…&nbsp;</span></li>';
				for(var i=1; i>=0; i--) {
					if(pageNo == (totalPage - i)) {
						html += '<li class="active"><a href="javascript:void(0)">' + pageNo + "</a></li>";
					} else {
						html += "<li><a href='javascript:goPage(" + (totalPage-i) + ")'>" + (totalPage-i) + "</a></li>";
					}
				}
			} */
	
			if(pageNo == totalPage) {
				html += '<a href="javascript:void(0)" title="下一页" class="page-down b-fff">下一页</a><a href="javascript:void(0)" title="末页"><em class="right-go icon-2-16">&nbsp;</em></a>';
			} else {
				html += '<a href="javascript:goPage(' + (pageNo + 1) + ')" title="下一页" class="page-down b-fff">下一页</a><a href="javascript:goPage(' + totalPage+ ')" title="末页"><em class="right-go icon-2-16">&nbsp;</em></a>';
			}
	
			$('#pager_con').html(html);
		}
		
		function iptChange() {
			var iptPageNo = parseInt($("#iptPageNo").val());
			if(iptPageNo < 1) {
				$("#iptPageNo").val(1); 
			} else if(iptPageNo > totalPage) {
				$("#iptPageNo").val(totalPage);
			}
		}
		
		function goIptPage() {
			var iptPageNo = $("#iptPageNo").val();
			try{
				if(iptPageNo=='' || isNaN(iptPageNo) || iptPageNo.indexOf(".") != -1) {
					iptPageNo = 1;
				}
			}catch(e){
				iptPageNo = 1;
			}
			goPage(iptPageNo);
		}
		
		function goPage(pageNum){
	        var pageReg = new RegExp("\\.currentPage=[0-9]*");
	        var pageURL = "${pageUrlParms}";
	        document.location=pageURL.replace(pageReg,".currentPage=" + pageNum);
	    }
	    
	    showPages();
	</script>
	