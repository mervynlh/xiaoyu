//下拉选择
function initArea(id, index,type,name) {
	var parentId = 1;
	if (id != null && id != 0 && !isNaN(id)) {
		parentId = id;
	}
	//只有第一个下拉框查询一级
	if (index != 0) {
		if (parentId == 1) {
			return;
		}
	}
	if(index<3){
		$.ajax({
			url : baselocation + "/area/ajax/parentid",
			data : {
				"parentId" : parentId
			},
			type : "post",
			dataType : "json",
			cache : false,
			async : false,
			success : function(result) {
				if (result == null || result.entity == null) {
					return;
				}
				var provinces = result.entity;
				var html = '';
				for (var i = 0; i < provinces.length; i++) {
					html += '<p><a href="javascript: void(0)" onClick="initArea(this.lang, '+(index+1)+',0,this.title)" lang="'+provinces[i].id+'" title="'+ provinces[i].areaName +'">'+ provinces[i].areaName +'</a></p>';
				}
				if (index == 0) {
					$("#provinceOption").html(html);
					//修改
					if(type==1){
						$("#provinceTip").html(name);
					}
				} else if (index == 1) {
					$("#cityOption").html(html);
					//修改
					if(type==1){
						$("#cityTip").html(name);
					}else{
						$("#cityTip").html("请选择城市");
						$("#townTip").html("请选择城镇");
						$("#provinceId").val(id);
						$("#provinceId").attr("lang",name);
						$("#cityId").val('');
						$("#townId").val('');
					}
					
				} else {
					$("#townOption").html(html);
					//修改
					if(type==1){
						$("#townTip").html(name);
					}else{
						$("#townTip").html("请选择城镇");
						$("#cityId").val(id);
						$("#cityId").attr("lang",name);
						$("#townId").val('');
					}
					search();
				}
				selFun("#select-1");
			},
			error : function(error) {
				alert('error' + error.responseText);
			}
		});
	}else{
		$("#townId").val(id);
		$("#townId").attr("lang",name);
		search();
	}
	var provinceId=$("#provinceId").val();
	var cityId=$("#cityId").val();
	var townId=$("#townId").val();
	if(isNotEmpty(provinceId)&&isNotEmpty(cityId)&&isNotEmpty(townId)){
		$("#address").removeAttr("disabled");
	}else{
		$("#address").attr("disabled","disabled");
	}
}
//添加地址
function save(){
	var provinceId=$("#provinceId").val();
	var cityId=$("#cityId").val();
	var townId=$("#townId").val();
	var address=$("#address").val();
	var lng=$("#lng").val();
	var lat=$("#lat").val();
	
	if(isEmpty(provinceId)){
		dialog("请选择省份",10,'','');
		return ;
	}
	if(isEmpty(cityId)){
		dialog("请选择城市",10,'','');
		return ;
	}
	if(isEmpty(townId)){
		dialog("请选择城镇",10,'','');
		return ;
	}
	if(isEmpty(address)){
		dialog("请填写详细地址信息",10,'','');
		return ;
	}
	if(isEmpty(lng)||isEmpty(lat)){
		dialog("填写详细地址后请选择下拉框地点，<br/>以便更好的定位",10,'','');
		return ;
	}
	//检查地址是否正确
	if(!checkAddress()){
		dialog('你提供的地址不够准确',10,'','');
		return;
	}
	$("#addAddress").submit();
}
//检查地址是否正确
function checkAddress(){
	var flat=false;
	var allLocation=$("#allLocation").val().split(",");
	var provinceName=$("#provinceTip").html().substring(0,2);
	var cityName=$("#cityTip").html().substring(0,2);
	var townName=$("#townTip").html().substring(0,2);
	if(allLocation[0].indexOf(provinceName)>=0){
		if(allLocation[1].indexOf(cityName)>=0){
			if(allLocation[2].indexOf(townName)>=0){
				flat=true;
			}
		}
	}
	return flat;
}
//修改地址
function initUpdate(isFirst,id,address,provinceId,provinceName,cityId,cityName,townId,townName,lng,lat){
	$("#id").val(id);
	$("#address").val(address);
	$("#provinceId").val(provinceId);
	$("#cityId").val(cityId);
	$("#townId").val(townId);
	$("#lng").val(lng);
	$("#lat").val(lat);
	if(isFirst==1){
		$("input[name='userAddress.isFirst']").attr("checked",'checked');
	}
	initArea(1, 0,1,provinceName);
	initArea(provinceId, 1,1,cityName);
	initArea(cityId, 2,1,townName);
	updateLoad(address);
}
function del(id){
	dialog('确定删除地址？',12,'','javascript:delAddress('+id+')');
}
function delAddress(id){
	$.ajax({
		url:"/uc/address/del/"+id,
		type:"post",
		dataType:"json",
		async:false,
		success:function(result){
			if(result.success){
				window.location.reload();
			}else{
				dialogClose();
				dialog(result.message,10,'','');
			}
		}
	});
}