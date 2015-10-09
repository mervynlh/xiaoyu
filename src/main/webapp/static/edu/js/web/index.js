$(function(){
	$(".n-l-menu dl dd").show();
});
//获取推荐老师
function randRecommandTeacher(){
	$.ajax({
		url:'/front/ajax/teacher/recommandTeacher',
		type:'post',
		dataType:'text',
		data:{},
		success:function(result){
			$("#recomandTea").html(result);
		}
	});
}

//换一换
function randomTeacher(subjectId,obj,showId){
	var majorId=$(obj).siblings('ul').find('.current').attr("id");
	showId=showId+majorId;
	if(majorId!=null&&majorId!=''){
		$.ajax({
			url:'/front/ajax/getTeacherBySubjectMajor',
			type:'post',
			dataType:'text',
			data:{"subjectId":subjectId,"majorId":majorId,"random":"random"},
			async:false,
			success:function(result){
				$("#"+showId).html(result);
			}
		});
	}
}
// 入驻机构
function addInst(){
	window.location.href="/front/toAddInstitution";
}

$(function() {
	// 首页焦点图
	$("#hotSlide").hover(function(){
		$('.hotSlideWrap .prev, .hotSlideWrap .next').show();
	},function(){
		$('.hotSlideWrap .prev, .hotSlideWrap .next').hide();
	})
	$("#hotSlide").slides({
		container: 'hotSlide',
		generatePagination:true,
		preload: true,
		preloadImage: 'img/loading.gif',
		play: 4000,
		pause: 1500,
		hoverPause: true
	});
})
$(function() {
	// 最新入驻
	$("#hotSlideFun").hover(function(){
		$('.hotSlideWrapFun .prev, .hotSlideWrapFun .next').show();
	},function(){
		$('.hotSlideWrapFun .prev, .hotSlideWrapFun .next').hide();
	})
	$("#hotSlideFun").slides({
		container: 'hotSlideFun',
		generatePagination:true,
		preload: true,
		preloadImage: 'img/loading.gif',
		play: 4000,
		pause: 1500,
		hoverPause: true
	});
})
$(function() {
	// 入驻机构
	$("#hotSlideFunjg").hover(function(){
		$('.hotSlideWrapFunjg .prev, .hotSlideWrapFunjg .next').show();
	},function(){
		$('.hotSlideWrapFunjg .prev, .hotSlideWrapFunlg .next').hide();
	})
	$("#hotSlideFunjg").slides({
		container: 'hotSlideFunjg',
		generatePagination:true,
		preload: true,
		preloadImage: 'img/loading.gif',
		play: 4000,
		pause: 1500,
		hoverPause: true
	});
})