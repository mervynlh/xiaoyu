function searchCourse(){
    $("#courseName").val(encodeURIComponent($("#courseName").val()));
    $("#searchForm").submit();
}
function checkCustomCourse() {
    var pattern = /^1{1}[0-9]{10}$/;
    var patternem = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/;
    var isForm = true;
    var title = $("#title").val();
    if(title==null||title.trim()==''){
        $(".titleErrorClass").html('<em class="icon18 newIcon18Cs"></em>');
        isForm = false;
    }
    var content= $("#content").val();
    var teacherName= $("#teacherName").val();
    if(teacherName==null||teacherName.trim()==''){
        $(".teacherNameErrorClass").html('<em class="icon18 newIcon18Cs"></em>');
        isForm = false;
    }
    var mobile =$("#mobile").val();
    if(mobile==null && mobile.trim()==''||!pattern.test(mobile)){
        	//if(!pattern.test(mobile)){}
            $(".mobileErrorClass").html('<em class="icon18 newIcon18Cs"></em>');
            isForm = false;
        
    }
    var email = $("#email").val();
    if(email==null && email.trim()==''||!patternem.test(email)){
       /* if(!patternem.test(email)){ }*/
            $(".emailErrorClass").html('<em class="cssFrom icon18 newIcon18Cs"></em>');
            isForm = false;

    }
    if(isForm){
       /* var feedback=0;
        if((mobile!=null&&mobile.trim()!='') || (email!=null&&email.trim()!='')){
            feedback = 1;
        }*/
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"/front/tocommitCusCourse",
            data:{
                "customerCourse.title":title,
                "customerCourse.content":content,
                "customerCourse.teacherName":teacherName,
                "customerCourse.mobile":mobile,
                "customerCourse.email":email
            },
            success:function(result){
                if(result.success == true){
                	dialog('提示','提交成功.',4);
                    $(".changesClass").val('');
                    $(".c-c-up-down").unbind("click");
                    $(".c-c-body").hide();
                    $(".c-c-up-down").click(function() {
            			if (!$(".c-c-body").is(":hidden")) {
            				$(".c-c-body").slideUp(300);
            				$(".c-c-up-down").attr("title" , "展开");
            				$(".c-c-up-down>em").removeClass("c-c-ud").addClass("c-c-down");
            			} else {
            				$(".c-c-body").slideDown(300);
            				$(".c-c-up-down").attr("title" , "关闭");
            				$(".c-c-up-down>em").removeClass("c-c-down").addClass("c-c-ud");
            			};
            		});
                }else{
                    dialog('提示','系统繁忙，请您稍后再操作!!',1);
                }
            },
            error:function (error){
                dialog('提示','系统繁忙，请您稍后再操作!!',1);
            }
        });
    }
}



function updateCusNuber(customerCourseId){
    if(!isLogin()){
        dialog('提示','请登录',1);
        return false;
    }
    if(customerCourseId!=null){
        $.ajax({
            url:"/front/userjoinCourse",
            data:{"queryCusCourseRecord.cusCourseId":customerCourseId},
            dataType:"json",
            type:"post",
            async:false,
            success:function(result){
                if(result.success==true){
                    dialog('提示','加入成功,您已经加入该课程',4);
                }else{
                    dialog('提示','您已经参加过了,不能重复加入',1);
                }
            }

        });
    }

}