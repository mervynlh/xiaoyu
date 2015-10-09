//$(function (){
//	showQueryCondition(subjectId);
//});
//function showQueryCondition(subId){
//	$("#queryConditionText").text(teacherName);
//}
////搜索功能
//function queryTeacher(){
//	$("#searchForm").submit();
//}

function enterSubmit(event, str) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
		eval(str);
	}
}

var derCone = '请输入关键字...';
	var index=0;//用于轮换的index
	$(function(){
		var _alink=$(".i-new-Point").find('a');
		var _ul=$(".i-new-list").find('ul');
		_alink.each(function() {
			var _this=$(this);
			
			_this.click(function() {
				var _index=_this.index();

				_this.addClass('on');
				_this.siblings().removeClass('on');
				_ul.eq(_index).show().siblings().hide();
			});
		});

		setInterval("atTimeClick()", 5000);
	})
	//没过一段时间 点击相应index的_alink
	function atTimeClick(){
		index++;
		if(index==3){
			index=0;
		}
		var _alink=$(".i-new-Point").find('a');
		_alink.eq(index).click();
	}
	function prevUlClick(){
		index--;
		if(index<0){
			index=2;
		}
		var _alink=$(".i-new-Point").find('a');
		_alink.eq(index).click();
	}
	/**
	 * 点击搜索
	 * @param type 专业/老师
	 * @param id 对应的值
	 */
	function clickSearch(type, id, obj) {
	    //点击搜索时要把当前页码设置为1
	    $("#pageCurrentPage").val(1);
		if (type == 'subject') {
			$("#hiddenSubjectId").val(id);
			$("#subjectId").val(id);
			if(id!=0){
				$("#grade").show();
				$("#hiddenSubjectId").val(0);
			} else {
				$("#grade").hide();
			}
		} else if (type == 'grade') {
			$("#hiddenSubjectId").val(id);
		} else if (type == 'major') {
			$("#hiddenMajorId").val(id);
		} else if (type == 'sellType') {
			$("#hiddensellType").val(id);
			$("#hiddencourseModel").val('all');
			if (id == 0 || id == 1) {
				$("#oneToOne").show();
				$("#classCourse").hide();
			} else if (id==2){
				$("#oneToOne").hide();
				$("#classCourse").show();
			}
		} else if (type == 'courseModel') {
			$("#hiddencourseModel").val(id);
		} else if (type == 'district') {
			$("#hiddendistrictId").val(id);
		} else if (type == 'suitStudent') {
			$("#hiddensuitStudent").val(id);
		} else if (type == 'clear') {//清空
			$("#hiddenSubjectId").val(0);
			$("#subjectId").val(0);
			$("#hiddenMajorId").val(0);
			$("#hiddensellType").val(0);
			$("#hiddencourseModel").val('all');
			$("#hiddensuitStudent").val('all');
			$("#hiddendistrictId").val(0);
			$("#hiddenlowPrice").val('');
			$("#hiddenseniority").val("");
			$("#hiddensex").val(-1);
		}
		$("#teacherName").val("");
		$("#hiddenTeachername").val("");
		search();
		$(obj).parent().addClass("current");
		$(obj).parent().siblings("li").removeClass("current");
	}
	function search() {
		var teacherName = $("#searteacherName").val();
		if (teacherName == derCone) {
			$("#hiddenTeachername").val("");
		}
		$("#pageCurrentPage").val(1);
		$("#searchForm").submit();
	}
	$(document).ready(function() {
		$(function() {
			upSlideFun("#iQuestion"); //向上滚动互动
			goTop();//右侧悬浮
			gtFun();
			cardChange("#lr-title>li" , "#lr-cont>section" , "current");//登录注册切换
			selFun("#select-1");// 模拟 select 下拉控件
		})
		if($("#hiddenlowPrice").val()=='' || $("#hiddenlowPrice").val() == null){
			$("#price_0").text("价格区间");
			$("#price0").hide();
		} else {
			var str = $("#price_" + $("#hiddenlowPrice").val()).html();
			$("#price_0").text(str);
			$("#price0").show();
		}
		if($("#hiddenseniority").val()=='' || $("#hiddenseniority").val() == null){
			$("#seniority_0").text("教龄");
			$("#seniority0").hide();
		} else {
			var str = $("#seniority_" + $("#hiddenseniority").val()).html();
			$("#seniority_0").text(str);
			$("#seniority0").show();
		}
		if($("#hiddensex").val()=='' || $("#hiddensex").val()==-1){
			$("#sex_-1").text("性别");
			$("#sex0").hide();
		} else {
			var str = $("#sex_" + $("#hiddensex").val()).html();
			$("#sex_-1").text(str);
			$("#sex0").show();
		}
		if($("#subjectId").val() != null && $("#subjectId").val()!=0 && $("#subjectId").val != ''){
			$("#grade").show();
		} else {
			$("#grade").hide();
		}
		/*if($("#hiddenSubjectId").val() != null && $("#hiddenSubjectId").val()!=0 && $("#hiddenSubjectId").val != ''){
			$("#grade").show();
		} else {
			$("#grade").hide();
		}*/
		if($("#hiddensellType").val() != null && $("#hiddensellType").val() != '' && $("#hiddensellType").val() != 2){
			$("#oneToOne").show();
			$("#classCourse").hide();
		} else {
			$("#oneToOne").hide();
			$("#classCourse").show();
		}
		
		$("#subject_query").html($("#subject_"+$("#subjectId").val()).html());
		$("#subject_select").html($("#grade_"+$("#hiddenSubjectId").val()).html());
		$("#major_select").html($("#major_"+$("#hiddenMajorId").val()).html());
		$("#sellType_select").html($("#sellType_"+$("#hiddensellType").val()).html());
		$("#courseModel_select").html($("#courseModel_"+$("#hiddencourseModel").val()).html()); 
		$("#districtId_select").html($("#district_"+$("#hiddendistrictId").val()).html());
		$("#suitStudent_select").html($("#suitStudent_"+$("#hiddensuitStudent").val()).html());

		$("#subject_"+$("#subjectId").val()).parent("li").siblings("li").removeClass('current');
		$("#subject_"+$("#subjectId").val()).parent("li").addClass("current");
		$("#grade_"+$("#hiddenSubjectId").val()).parent("li").siblings("li").removeClass('current');
		$("#grade_"+$("#hiddenSubjectId").val()).parent("li").addClass("current");
		$("#major_"+$("#hiddenMajorId").val()).parent("li").siblings("li").removeClass('current');
		$("#major_"+$("#hiddenMajorId").val()).parent("li").addClass("current");
		$("#sellType_"+$("#hiddensellType").val()).parent("li").siblings("li").removeClass('current');
		$("#sellType_"+$("#hiddensellType").val()).parent("li").addClass("current");
		$("#courseModel_"+$("#hiddencourseModel").val()).parent("li").siblings("li").removeClass('current');
		$("#courseModel_"+$("#hiddencourseModel").val()).parent("li").addClass("current");
		$("#district_"+$("#hiddendistrictId").val()).parent("li").siblings("li").removeClass('current');
		$("#district_"+$("#hiddendistrictId").val()).parent("li").addClass("current");
		$("#suitStudent_"+$("#hiddensuitStudent").val()).parent("li").siblings("li").removeClass('current');
		$("#suitStudent_"+$("#hiddensuitStudent").val()).parent("li").addClass("current");
		
		var order = $("#hiddenorder").val();
		if(order=='human'){
			$("#human_order").siblings("li").removeClass('current');
			$("#human_order").addClass('current');
		}else if (order=='assess'){
			$("#assess_order").siblings("li").removeClass('current');
			$("#assess_order").addClass('current');
		} else if (order=='price'){
			$("#price_order").siblings("li").removeClass('current');
			$("#price_order").addClass('current');
		}else{
			$("#deft_order").siblings("li").removeClass('current');
			$("#deft_order").addClass('current');
		};
	});
	$(document).ready(function() {
		$(".c-tit-name li").hover(function() {
			$(this).addClass('current').siblings().removeClass('current');
			$(".c-in-list-body-infor ul").eq($(this).index()).show().siblings().hide();
		});
	});
	
	function orderByQuery(order){
		$("#hiddenorder").val(order);
		search();
	}
	
	$(function (){
		//课程搜索内容放入上部搜索框
		$(".tscInp").val($("#hiddenTeachername").val());
//		// 将城市显示在上部
//		var cityId = $("#hiddenCityId").val();
//		if(cityId == null || cityId == 0){
//			cityId = 33;
//		}
//		if(cityId != null && cityId != 0){
//			$("#select_area").html($("#area_"+cityId).html());
//		}

		$(".s-c-list>li:nth-child(4n)").css({"margin-right" : "0px"});
		//sort suMore
		$(".select-box2-right .suMore").each(function() {
			var _this = $(this),
				_uList = _this.parent().siblings(".select-box2-mid"),
				_uLw = _uList.height();
				
			if (_uLw <= "40") {
				_this.hide();
			} else {
				_uList.css("height","40px");
				_this.click(function() {
					if(_this.html() == "更多↓") {
						_uList.css("height","auto");
						_this.text("收起↑");
					} else {
						_uList.css("height" , "40px");
						_this.text("更多↓");
					}
				})
			}
		});
	});
// 选择价格区间
function selectPriceChange(str){
	if(str==''){
		$("#hiddenlowPrice").val("");
	} else {
		$("#hiddenlowPrice").val(str);
	}
	search();
}
//选择性别
function selectSexChange(str){
	$("#hiddensex").val(str);
	search();
}
//选择教龄区间
function selectSeniorityChange(str){
	if(str==''){
		$("#hiddenseniority").val("");
	} else {
		$("#hiddenseniority").val(str);
	}
	search();
}

function houseTeacher(teacherId){
	if(isLogin()){
		
		$.ajax({
				url:baselocation+"/front/addteacherfavorites",
				data:{'teacherId':teacherId},
				type : "post", 
				dataType : "json",
				success: function (result){
					if(result.message=="success"){
						dialog('收藏提示',"收藏成功",4);
					}
					if(result.message=="false"){
						dialog('收藏提示',"收藏失败",1);
					}
					if(result.message=="owned"){
						dialog('收藏提示',"您已收藏过，请不要重复收藏",1);
					}
				}
			});
	}else{
			dialog('登录','',3,'',1);
			$("#userEmail").mailAutoComplete({
			    boxClass: "out_box", //外部box样式
			    listClass: "list_box", //默认的列表样式
			    focusClass: "focus_box", //列表选样式中
			    markCalss: "mark_box", //高亮样式
			    autoClass: false,//不使用默认的样式
			    textHint: true //提示文字自动隐藏
			});
		
	}
}

