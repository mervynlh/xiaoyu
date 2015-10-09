
	function uploadifyUpload(){
		 if(fileuploadIndex==0)
		 {
			 alert("请选择图片 ");
			 return;
		 }	
		$('#fileupload').uploadifyUpload();
	
	}
	function addImage(){
		window.open('/admin/weixin/imagepage?page.currentPage=1',
				+'newwindow', 'toolbar=no,scrollbars=yes,location=no,resizable=no,top=150,left=300,width=923,height=802'
			);
	}
	function getImageList(ImageList){
		//p对象的name获取已存在的课程集合，去除提交过来的重复对象
		$("p[name='imageName']").each(function(i,val){
			for(var j=0;j<ImageList.length;j++){
				var id = ImageList[j];
				if(val.id=='imagespan'+id[0]){
					ImageList.splice(j,1);
				}
			}
		});
		//插入到表格中
		imagePAdd(ImageList);
	}	
	
	function imagePAdd(myArray){
	 	var str="";
	    var imageIds=$("#imageIdHidden").val();
	 	for(var i=0;i<myArray.length;i++){
		 	var arr=myArray[i];
		 	str+="<p style='width:100%;margin: 0 0 0em' id='imagespan"+arr[0]+"' name='imageName' value='"+arr[0]+"' title='"+arr[1]+"'>"+arr[1]+"&nbsp;&nbsp;<a href='javascript:void(0)' class='btn btn-danger' onclick=delimage('"+arr[0]+"','"+arr[1]+"')>删除</a></p>";
		 	imageIds+=arr[0]+",";
	 	}
	 	$("#imagestr").prepend(str);
		$("#imageIdHidden").val(imageIds);
	}
	function delimage(imageId,title){
		if(confirm("确定要删除【"+title+"】")){
			$("#imagespan"+imageId).remove();
			var ids= $("#imageIdHidden").val();
			ids=ids.replace(imageId+",","");
			$("#imageIdHidden").val(ids);
		};
	}
	function clearimage()
	{
		$("#imageIdHidden").val("");
		$("#imagestr>p").remove();
	}
