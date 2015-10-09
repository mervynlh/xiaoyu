<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>售卖方式</title>

<script type="text/javascript">

function updateSubmit(){
	if($("#verifyCourse").val()=='OFF'&&$("#verifyMember").val()=='OFF'){
		alert("必须开起一种售卖方式");
		return;
	}
	$("#updateForm").submit();
}

</script>
</head>
<body >


<div class="page_head">
	<h4><em class="icon14 i_01"></em>&nbsp;<span>售卖方式管理</span> &gt; <span>售卖方式</span> </h4>
</div>
<!-- /tab4 begin -->
<div class="mt20">
	<div class="commonWrap">
	<form action="${ctx}/admin/websiteProfile/sale/update" method="post" id="updateForm">
	<input type="hidden" name="type" id="id"  value="online"/>
	<input type="hidden" name="onlineImageUrl" id="imagesUrl"  value=""/>
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
		<thead>
			<tr>
				<th align="left" colspan="3"><span>售卖方式基本属性<tt class="c_666 ml20 fsize12">（<font color="red">*</font>&nbsp;为必填项）</tt></span></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="20%" align="center"><font color="red">*</font>&nbsp;单课购买开关：</td>
				<td>
		    		<select name="verifyCourse" id="verifyCourse">
			    		<c:if test="${websitemap.sale.verifyCourse=='ON'}">
			    		<option value="ON">ON</option>
			    		<option value="OFF">OFF</option>
			    		</c:if>
			    		<c:if test="${websitemap.sale.verifyCourse=='OFF'}">
			    		<option value="OFF">OFF</option>
			    		<option value="ON">ON</option>
			    		</c:if>
			    		</select>

				</td>
                <td><font style="color: red">课程可以单独购买观看 </font></td>
            </tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;会员功能开关：</td>
				<td>
					<select name="verifyMember" id="verifyMember">
			    		<c:if test="${websitemap.sale.verifyMember=='ON'}">
			    		<option value="ON">ON</option>
			    		<option value="OFF">OFF</option>
			    		</c:if>
			    		<c:if test="${websitemap.sale.verifyMember=='OFF'}">
			    		<option value="OFF">OFF</option>
			    		<option value="ON">ON</option>
			    		</c:if>
			    		</select>
				</td>
                <td><font style="color: red">课程可以通过开通会员的方式观看，需要设置使用模式 </font></td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;会员使用模式：</td>
				<td>
					<select name="verifyLevel" id="verifyLevel">
			    		<c:if test="${websitemap.sale.verifyLevel=='ON'}">
			    		<option value="ON">阶梯模式</option>
			    		<option value="OFF">独立模式</option>
			    		</c:if>
			    		<c:if test="${websitemap.sale.verifyLevel=='OFF'}">
			    		<option value="OFF">独立模式</option>
			    		<option value="ON">阶梯模式</option>
			    		</c:if>
                        <br/>
                    </select>
				</td>
                <td><font style="color: red">1.独立模式：每种会员只能看自己的课程 ，黄金会员只能看课程中设置为黄金会员的课程<br/> 2.阶梯模式：高级会员可以看低级会员的课程 <br/>如：课程设置为黄金会员可观看，开通了白金和钻石会员也能观看,钻石>白金>黄金
                    <br/>即：钻石会员可以观看钻石+白金+黄金的所有课程，白金可以观看白金+黄金的所有课程，黄金可以观看黄金会员的所有课程
                </font></td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<a class="btn btn-danger" title="提 交" href="javascript:updateSubmit()">修改</a>
					<a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
				</td>


			</tr>
		</tbody>
	</table>
	</form>
	</div>
</div>
</body>
</html>
