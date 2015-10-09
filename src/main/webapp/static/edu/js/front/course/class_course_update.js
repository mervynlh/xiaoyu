var emptyValue='<em class="icon14 fr mt5">&nbsp;</em><span>选择科目</span>'; 
//上传控件加载
function uploadPicLoad(id){
	$("#"+id).uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
		'script'  :uploadSwfUrl,  
        'scriptData':{"base":"mavendemo","param":"userFeedback"},
		'queueID' : 'fileQueue', //文件队列ID
		'fileDataName' : 'fileupload', //您的文件在上传服务器脚本阵列的名称
		'auto' : true, //选定文件后是否自动上传
		'multi' :false, //是否允许同时上传多文件
		'hideButton' : false,//上传按钮的隐藏
		'buttonText' : 'Browse',//默认按钮的名字
		'buttonImg' : '/static/common/uploadify/liulan.png',//使用图片按钮，设定图片的路径即可
		'width' : 105,
		'simUploadLimit' : 3,//多文件上传时，同时上传文件数目限制
		'sizeLimit' : 51200000,//控制上传文件的大小
		'queueSizeLimit' : 3,//限制在一次队列中的次数（可选定几个文件）
		'fileDesc' : '支持格式:jpg/gif/jpeg/png/bmp.',//出现在上传对话框中的文件类型描述
		'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',//支持的格式，启用本项时需同时声明fileDesc
		'folder' : '/upload',//您想将文件保存到的路径
		'cancelImg' : '/static/common/uploadify/cancel.png',
		onSelect : function(event, queueID,fileObj) {
			fileuploadIndex = 1;
			$("#fileQueue").html("");
			if (fileObj.size > 51200000) {
				dialog('文件太大最大限制51200kb',10,'','');
				return false;
			}
		},
		onComplete : function(event,queueID, fileObj, response,data) {
			$("#imgs").val(response);
			$("#classpic").attr('src',staticImageServer+response);
		},
		onError : function(event, queueID, fileObj,errorObj) {
			$("#fileQueue").html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
		}
	});
}
//添加页面编辑器
function initKindEditor(id, width, height) {
	EditorObject = KindEditor.create('textarea[id=' + id + ']', {
		resizeType : 1,
		filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
		allowPreviewEmoticons : false,
		allowUpload : true,// 允许上传
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		width : width,
		height : height,
		minWidth : '10px',
		minHeight : '10px',
		uploadJson : keImageUploadUrl + '&param=article',// 图片上传路径
		afterBlur : function() {
			this.sync();
		},
		allowFileManager : false,
		items : [ 'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
				'image','link','plainpaste' ]
	});
}

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
//删除要上传的图片
function clearPic(obj){
	$(obj).parent().remove();
	var imgs="";
	$(".imgss").each(function(){
		imgs+=$(this).attr("lang")+",";
	});
	 $("#imgs").val(imgs);
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
//切换地址类型0选择地址库地址 1 选择新地址
function chooseAddressType(obj){
	$("#addressId").val('');
	var addressType=$(obj).val();
	if(addressType==1){
		initArea(1, 0,0);
		$("#newAddress").show();
		$("#oldAddress").hide();
	}else{
		$("#newAddress").hide();
		$("#oldAddress").show();
	}
}
function chooseModel(type){
	if(type==1){
		$("#lineModel").hide();
	}else{
		$("#lineModel").show();
	}
}
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
				}
				selFun("#select-1");
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
function saveAddress(){
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
	//将地址保存入地址库中
	$.ajax({
		url : baselocation + "/uc/ajax/address/add",
		data : {
			"userAddress.address":address,
			"userAddress.provinceId":provinceId,
			"userAddress.cityId":cityId,
			"userAddress.townId":townId,
			"userAddress.lng":lng,
			"userAddress.lat":lat
		},
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.success){
				dialog(result.message,8,'','');
				$("#addressId").val(result.entity.id);
			}else{
				dialog(result.message,10,'','');
			}
		}
	});
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
//选择地址
function setAddressId(id){
	$("#addressId").val(id);
}
//选择是否可以插班  控制插班价格的显示隐藏
function chooseJoin(type){
	if(type==0){
		$("#joinPrise").hide();
	}else{
		$("#joinPrise").show();
	}
}
//创建课程
function save(){
	var courseName=$("#courseName").val();
	var subjectMajorId=$("#subjectMajorId").val();
	var addressId=$("#addressId").val();
	var currentprise=$("#currentprise").val();
	var courseTitle=$("#courseTitle").val();
	var courseContent=$("#courseContent").val();
	var imgs=$("#imgs").val();
	
	if(courseName==null||courseName==''){
		dialog('请填写班课名称',10,'','');
		return ;
	}
	if(imgs==null||imgs==''){
		dialog('请上传课程封面',10,'','');
		return ;
	}
	if(subjectMajorId==null||subjectMajorId==''){
		dialog('请选择专业科目',10,'','');
		return ;
	}
	if(addressId==null||addressId==''){
		dialog('请选择授课地址',10,'','');
		return ;
	}
	if(courseTitle==null||courseTitle==''){
		dialog('请填写班级简介',10,'','');
		return ;
	}
	if(courseContent==null||courseContent==''){
		dialog('请填写班级详情',10,'','');
		return ;
	}
	
	$.ajax({
		url : baselocation + "/uc/teacher/updateClassCourse",
		data : $('#submitForm').serialize(), 
		type : "post",
		dataType : "json",
		async : false,
		success : function(result) {
			if(result.success){
				dialog(result.message,17,'','/uc/teacher/classCourse/list');
			}else{
				dialog(result.message,9,'','');
			}
		}
	});
}