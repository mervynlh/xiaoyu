<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>


<link href="<%=imagesPath%>/static/common/Jcrop/jquery.Jcrop.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctximg}/static/common/uploadify/uploadify.css" />
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
<script type="text/javascript" src="<%=imagesPath%>/static/common/Jcrop/jquery.Jcrop.min.js"></script>
<script type="text/javascript">
	
function initFileUpload(btnid,fieldName){
 	KindEditor.create('');
	var uploadbutton = KindEditor.uploadbutton({
		button : KindEditor('#'+btnid+'')[0],
		fieldName : fieldName,
		url : "<%=uploadSimpleUrl%>&param=temp",
		afterUpload : function(data) {
			
			if (data.error == 0) {
				$("#quxiao").click();
	           	 $("#picture").attr("style","");
	           	 $("#picture").attr("src","<%=staticImageServer%>"+data.url);
	           	 $(".jcrop-preview").attr("src","<%=staticImageServer%>"+data.url);
	           	 $(".pictureWrap").attr("src","<%=staticImageServer%>"+data.url);
	           	 $("#photoPath").val(data.url);
           	
	           	 var img=new Image();
	           	 img.src="<%=staticImageServer%>"+data.url;
                 img.onload=function(){
	               var realHeight = img.height;
	               var realWidth = img.width;
               		if(realHeight>realWidth){
		               	var height=300;
		               	var width = height*realWidth/realHeight;
		               	$("#picture").attr("height",height); 
		               	$("#picture").attr("width",width); 
		               	$("#picture_width").val(Math.ceil(width));
		                $("#picture_height").val(height);
	               }else{
		               	var width=300;
		               	var height = width*realHeight/realWidth;
		               	$("#picture").attr("height",height); 
		               	$("#picture").attr("width",width); 
		               	$("#picture_width").val(width);
	                   $("#picture_height").val(Math.ceil(height));
	               }
               	editingPhotos();
               };
			} else {
				alert("error");
			}
		},
		afterError : function(str) {
			//alert('自定义错误信息: ' + str);
		}
	});
	uploadbutton.fileBox.change(function(e) {
		uploadbutton.submit();
	});
}

	jQuery.ajaxSetup ({cache:false});
	var loadn=0;
	var cusid=${queryUser.id};
	var iconElement1;
	var imagedrag1;
	var iit=0;
    $(document).ready(function() {
    	
//     	initFileUpload("fileupload","fileupload");
    	
        $("#fileupload").uploadify({
                'uploader':'${ctximg}/static/common/uploadify/uploadify.swf',
                'script'  :'<%=uploadServerUrl%>/goswf?cusid='+cusid,
                'height': 45,
    			'width': 150, 
                'queueID':'fileQueue',
                'fileDataName':'fileupload',
                'auto':true,
                'multi':false,
                'hideButton':false,
                'buttonText':'Browse',
                'buttonImg':'${ctximg}/static/common/uploadify/liulan.png',
                'simUploadLimit' : 3,
                'sizeLimit'      : 2048000,
                'queueSizeLimit' : 2,
                'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
                'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
                'folder' : '/upload',
                'cancelImg':'${ctximg}/static/common/uploadify/cancel.png',
                onSelect:function(){ $("#fileQueue").html("");},
                onComplete: function (event, queueID, fileObj, response, data){    
                	 $("#quxiao").click();
                	 $("#picture").attr("style","");
                	 $("#picture").attr("src","<%=staticImageServer%>"+response);
                	 $(".jcrop-preview").attr("src","<%=staticImageServer%>"+response);
                	 $(".pictureWrap").attr("src","<%=staticImageServer%>"+response);
                	 $("#photoPath").val(response);
                	
                	 var img=new Image();
                	 img.src="<%=staticImageServer%>"+response;
                     img.onload=function(){
                    var realHeight = img.height;
                    var realWidth = img.width;
                    if(realHeight>realWidth){
                    	var height=300;
                    	var width = height*realWidth/realHeight;
                    	$("#picture").attr("height",height); 
                    	$("#picture").attr("width",width); 
                    	$("#picture_width").val(Math.ceil(width));
                        $("#picture_height").val(height);
                    }else{
                    	var width=300;
                    	var height = width*realHeight/realWidth;
                    	$("#picture").attr("height",height); 
                    	$("#picture").attr("width",width); 
                    	$("#picture_width").val(width);
                        $("#picture_height").val(Math.ceil(height));
                    }
                   
                 /*    var height = 300*realHeight/realWidth;
                    $("#picture_width").val(300);
                    $("#picture_height").val(Math.ceil(height));
                    $("#picture").attr("height",height);  */
                    editingPhotos();
                     };
                	 // kuan 820
                	 // gao353
                	 // 300*353/800
                	 // kuan >300 kuan <300 以高为准
                },
                 onError: function(event, queueID, fileObj)
                    {
                    	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+" 上传失败</font>");
                    }
                }); 
    });
    
    $(document).ready(function() {
    	//myphoto();
    });
    
    function myphoto(){
    	var myhpoto=getCookie(usercookiemyphoto);
    	if(myhpoto!=null && myhpoto!="notMyPhoto"){
    		myhpoto=myhpoto.trim();
    		if(myhpoto.length>4){
    			$(".jcrop-preview").attr("src",staticImageServer+myhpoto);
    		}
    	}
    }
    
    
    //必须的 
    /* function uploadifyUpload(){ 
       $('#fileupload').uploadifyUpload(); 
    } 
     */
    var params="";  //参数集
    function catparams(){
    	var pathnull=$("#picture").attr("src");
    	if(pathnull.indexOf("uploadDefaultPic.")>=0){
    		$("#save_message").html("&nbsp;&nbsp;&nbsp;&nbsp;请先上传图片");
    		return ;
    	}
	     var path=$("#photoPath").val(); 
	     params="photoPath="+path+"&txt_width="+$("#picture_width").val()+
	     "&txt_height="+$("#picture_height").val()+"&txt_top="+$("#txt_top").val()+
	     "&txt_left="+$("#txt_left").val()+"&txt_DropWidth="+$("#txt_DropWidth").val()+
	     "&txt_DropHeight="+$("#txt_DropHeight").val()+"&cusid="+${queryUser.id};
	     $.getJSON("<%=uploadServerUrl%>/saveface?"+params+"&callback=?",function(json){
	    	 var photoUrl = json.src;
	    	 $.ajax({ 
	    		   type: "POST",
	    		   url: baselocation+"/uc/user/updateavatar",
	    		   data: "userId=${queryUser.id}&avatar="+photoUrl,
	    		   success: function(msg){
	    				  $("#ImageDrag").attr("src","<%=imagesPath%>/images/usercenter/man.GIF");
	    				  $("#ImageIcon").attr("src","<%=imagesPath%>/images/usercenter/man.GIF");
	    				  var img=new Image();
	                      img.onload=function(){
	                    	  
	                      	$("#photoPath").val("<%=staticImageServer%>"+photoUrl+"");	
	                      	SetCookie(usercookiemyphoto,photoUrl);
	        				if(json.status==1)
	        					{
	        			          $("#success_tip").show();
	        					}
	                          }
	                      img.src="<%=imagesPath%>/images/usercenter/man.GIF.png";
	                      window.location.href="${ctx}/uc/home";
	    		   },
	    		   error:function(ex){
	    			 //  alert(ex.responseText);
	    		   }
	    		});
	    } );
     };
	
	function editingPhotos(){
		$(function(){
		    var jcrop_api,
		        boundx,
		        boundy,
		        $preview = $('.preview-pane'),
		        $pcnt2 = $('.preview-pane2 .preview-container'),
		        $pimg2 = $('.preview-pane2 .preview-container img'),
		        $pcnt = $('.preview-pane1 .preview-container'),
		        $pimg = $('.preview-pane1 .preview-container img'),
		        $pcnt3 = $('.preview-pane3 .preview-container'),
		        $pimg3 = $('.preview-pane3 .preview-container img'),
		        xsize = $pcnt.width(),
		        ysize = $pcnt.height();
			    xsize2 = $pcnt2.width(),
		        ysize2 = $pcnt2.height();
			    xsize3 = $pcnt3.width(),
		        ysize3 = $pcnt3.height();
		    $('#picture').Jcrop({
		      onChange: updatePreview,
		      onSelect: updatePreview,
		      allowSelect:false,//是否允许新选框
              minSize: [50,50],//选框最小尺寸
		      aspectRatio: xsize / ysize,
		      aspectRatio: xsize2 / ysize2,
		      aspectRatio: xsize3 / ysize3
		    },function(){
		      var bounds = this.getBounds();
		      boundx = bounds[0];
		      boundy = bounds[1];
		      jcrop_api = this;
		      jcrop_api.animateTo([80,50,80,20]);
		      $preview.appendTo(jcrop_api.ui.holder);
		    });
		    
		    function updatePreview(c){
	    	  $('#txt_left').val(c.x);
		      $('#txt_top').val(c.y);
		      $('#x2').val(c.x2);
		      $('#y2').val(c.y2);
		      $('#txt_DropWidth').val(c.w);
		      $('#txt_DropHeight').val(c.h);
		      if (parseInt(c.w) > 0)
		      {
		        var rx = xsize / c.w;
		        var ry = ysize / c.h;
		        $pimg.css({
		          width: Math.round(rx * boundx) + 'px',
		          height: Math.round(ry * boundy) + 'px',
		          marginLeft: '-' + Math.round(rx * c.x) + 'px',
		          marginTop: '-' + Math.round(ry * c.y) + 'px'
		        });
		        var rx2 = xsize2 / c.w;
		        var ry2 = ysize2 / c.h;
		        $pimg2.css({
			          width: Math.round(rx2 * boundx) + 'px',
			          height: Math.round(ry2 * boundy) + 'px',
			          marginLeft: '-' + Math.round(rx2 * c.x) + 'px',
			          marginTop: '-' + Math.round(ry2 * c.y) + 'px'
			        });
		        var rx3 = xsize3 / c.w;
		        var ry3 = ysize3 / c.h;
		        $pimg3.css({
			          width: Math.round(rx3 * boundx) + 'px',
			          height: Math.round(ry3 * boundy) + 'px',
			          marginLeft: '-' + Math.round(rx3 * c.x) + 'px',
			          marginTop: '-' + Math.round(ry3 * c.y) + 'px'
			        });
		      }
		    };
		    
		    $('#quxiao').click(function(e) {
		    	// Destroy Jcrop widget, restore original state
		    	jcrop_api.destroy();
		    	// Update the interface to reflect un-attached state
		    	return false;
		    	}); 
		  });
	}
	
</script>
