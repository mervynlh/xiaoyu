//清空下拉框显示的信息
var emptyValue='<em class="icon14 fr mt5">&nbsp;</em><span>--请选择--</span>';

$(function(){
	uploadPicLoad();
	//一级专业默认选择第一个
	chooseSub(defaultSubjectId,defaultSubjectName);
});

function chooseSub(subjectId,subjectName){
	$("#subjectId").val(subjectId);
	$("#subjectName").val(subjectName);
	//清空科目
	$("#majorOption").html(emptyValue);
	$("#majorName").val('');
	showModel();
	selectMajorCourse();
}
//查询科目课程
function selectMajorCourse(){
	$("#majorCourse").html('');
	var teacherId= $("#teacherId").val();
	var subjectId=$("#subjectId").val();
	
	$.ajax({
		url:'/front/ajax/getMajorCourseBySubject',
		type:'post',
		data:{'queryCourse.teacherId':teacherId,'queryCourse.subjectId':subjectId,'queryCourse.sellType':1},
		dataType:'json',
		success:function(result){
			if(result.success){
				var list=result.entity;
				if(list!=null &&list.length>0){
					var content='';
					//科目默认加载第一
					$("#majorOption").html('<em class="icon14 fr mt5">&nbsp;</em><span>'+list[0].subjectName+' '+list[0].majorName+'</span>');
					$("#majorName").val(list[0].subjectName+' '+list[0].majorName);
					for(i=0;i<list.length;i++){
						content+='<p><a title="'+list[i].subjectName+' '+list[i].majorName+'" href="javascript: void(0)" onclick="chooseMajor(this)">'+list[i].subjectName+' '+list[i].majorName+'</a></p>';
					}
					$("#majorCourse").html(content);
					selFun("#select-1");// 模拟 select 下拉控件
				}
			}
		}
	});
}
//选择科目
function chooseMajor(obj){
	$("#majorName").val($(obj).attr("title"));
	$("#modelOption").html(emptyValue);
}
//显示上课方式
function showModel(){
	var subjectId=$("#subjectId").val();
	var courseId=$("#courseId").val();
	$("#showModel").hide();
	if(subjectId!=null&&subjectId!=''&&courseId!=null&&courseId!=''){
		$("#showModel").show();
	}
}
//选择上课方式
function chooseModel(obj){
	$("#courseModelValue").val($(obj).attr('title'));
}
function chooseScore(obj){
	$(obj).parent().siblings().removeClass("current");
	$(obj).parent().prevAll().addClass("current");
	$(obj).parent().addClass("current");
}
$(function(){
	$(".cStar-2 li a").on("click",function(){
		chooseScore(this);
		var id=$(this).parent().parent().attr("lang");
		$("#"+id).val($(this).attr("lang"));
	});
});

//上传控件加载
function uploadPicLoad(){
	$("#fileupload").uploadify({
		'uploader' : '/static/common/uploadify/uploadify.swf', //上传控件的主体文件，flash控件  默认值='uploadify.swf'
		'script'  :uploadServerUrl+'/goswf',  
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
			if($(".imgss").length>=3){
				dialog('最多上传3张',10,'','');
				return false;
			}
		},
		onComplete : function(event,queueID, fileObj, response,data) {
			if($(".imgss").length<3){
				 $("#last").before('<li><img class="imgss" src="'+staticImageServer+response+'" alt=""><a href="javascript:void(0)" onClick="clearPic(this)" class="close-eva"></a></li>');
			}
		},
		onError : function(event, queueID, fileObj,errorObj) {
			$("#fileQueue").html("<br/><font color='red'>"+ fileObj.name + "上传失败</font>");
		}
	});
	
}

//点击上传图片
function uploadImg(){
	if($(".Evaluation-box-tp").is(":visible")){
		$(".Evaluation-box-tp").hide();
		$("#last").siblings().remove();
	}else{
		$(".Evaluation-box-tp").show();
	}
}
//删除要上传的图片
function clearPic(obj){
	$(obj).parent().remove();
}
//保存
function save(){
	if(!isLogin()){
		dialog('请登录后评价',10,'','');
		return;
	}
	var subjectId=$("#subjectId").val();
	var majorName=$("#majorName").val();
	var courseModelValue=$("#courseModelValue").val();
	var description=$("#description").val();
	var attribute=$("#attribute").val();
	var speed=$("#speed").val();
	if(subjectId==null||subjectId==''){
		dialog('请选择阶段',9,'','');
		return;
	}
	if(majorName==null||majorName==''){
		dialog('请选择科目',9,'','');
		return;
	}
	if(courseModelValue==null||courseModelValue==''){
		dialog('请选择上课方式',9,'','');
		return;
	}
	if(description==null||description==''){
		dialog('请评价课程',9,'','');
		return;
	}
	if(attribute==null||attribute==''){
		dialog('请评价老师教学态度',9,'','');
		return;
	}
	if(speed==null||speed==''){
		dialog('请选择老师讲课的速度',9,'','');
		return;
	}
	var content=$("#content").val();
	var imgs='';
	if($(".Evaluation-box-tp").is(":visible")){
		$(".imgss").each(function(){
			imgs+='<a href="javascript:void(0)" class="talk-nr-img"><img alt="" width="170px;" height="170px;" src="'+$(this).attr("src")+'"></a>';
		});
	}
	if(content==null||content==''){
		dialog('请输入评论内容',9,'','');
		return;
	}
	var type="1";
	type=$('.type:checked').val();
	$.ajax({
		url:'/front/assess/add',
		type:'post',
		data:{
			'assess.userId':$("#userId").val(),
			'assess.orderId':$("#orderId").val(),
			'assess.subjectId':subjectId,
			'assess.description':description,
			'assess.attribute':attribute,
			'assess.speed':speed,
			'assess.content':content,
			'assess.imgs':imgs,
			'assess.type':type,
			'assess.courseModel':courseModelValue,
			'assess.subjectMajorName':$("#subjectName").val()+" "+majorName
		},
		dataType:'json',
		success:function(result){
			if(result.success){
				dialog(result.message,13,'','/uc/teacher/mystudent/list');
			}else{
				dialog(result.message,10,'','');
			}
		}
	});
}