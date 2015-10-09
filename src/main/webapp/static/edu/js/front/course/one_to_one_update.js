var emptyValue='<em class="icon14 fr mt5">&nbsp;</em><span>请选择科目</span>'; 
//获得专业下的科目
function getMajor(subjectId){
	$("#majorTip").html(emptyValue);
	$("#subjectMajorId").val('');
	$.ajax({
		url : baselocation + "/front/ajax/getTeacherMajor",
		data : {"subjectId" : subjectId},
		type : "post",
		dataType : "json",
		async : false,
		success : function(result) {
			if(result.success){
				var list=result.entity;
				if(list!=null && list.length>0){
					var content='';
					for(i=0;i<list.length;i++){
						content+='<p><a id="major'+list[i].id+'" href="javascript: void(0)" onClick="chooseMajor('+list[i].id+')" title="'+list[i].subjectName+list[i].majorName+'">'+list[i].subjectName+list[i].majorName+'</a></p>';	
					}
					$("#majorOption").html(content);
					selFun("#select-1");
				}
			}
		}
	});
}
function chooseMajor(id){
	$("#subjectMajorId").val(id);
}
//金钱格式的判断
function onlyNumber(input, n) {
    input.value = input.value.replace(/[^0-9\.]/ig, '');
    var dotIdx = input.value.indexOf('.'), dotLeft, dotRight;
    if (dotIdx >= 0) {
        dotLeft = input.value.substring(0, dotIdx);
        dotRight = input.value.substring(dotIdx + 1);
        if (dotRight.length > n) {
            dotRight = dotRight.substring(0, n);
        }
        input.value = dotLeft + '.' + dotRight;
    }
}
//金额的处理
function setMoney(obj){
	var valu=$(obj).siblings(":checkbox").attr("lang")+":"+$(obj).val();
	$(obj).siblings(":checkbox").val(valu);
}
//提交
function save(){
	var subjectMajorId=$("#subjectMajorId").val();
	if(subjectMajorId==''){
		dialog('请选择专业科目',10,'','');
		return;
	}
	if($(":checked").length==0){
		dialog('请选择授课方式',10,'','');
		return;
	}
	var isok=true; 
	$(":checked").each(function(){
		if($(this).nextAll(":input").val()==''){
			isok=false;
			return;
		}
	});
	if(!isok){
		dialog('请完善价格信息',10,'','');
		return;
	}
	
	$.ajax({
		url : baselocation + "/front/ajax/updateOneToOne",
		data : $('#submitForm').serialize(), 
		type : "post",
		dataType : "json",
		async : false,
		success : function(result) {
			if(result.success){
				dialog(result.message,17,'','/uc/teacher/ontToOne/list');
			}else{
				dialog(result.message,10,'','');
			}
		}
	});
}