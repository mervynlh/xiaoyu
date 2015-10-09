var lng=$('#lng').val();
var lat=$('#lat').val();
if(lng==null||lng==''||lat==null||lat==''){
	lng=116.404;
	lat=39.915;
}else{
	$("#sub-map").show();
}
var map = new BMap.Map("sub-map");
var point = new BMap.Point(lng, lat);
var marker = new BMap.Marker(point);  // 创建标注
var g=new BMap.Geocoder();//地址逆解析  根据坐标获取名称
var label = new BMap.Label("",{offset:new BMap.Size(20,-10)});
$(function(){
	mapLoad();
//	search();
});

//地图加载
function mapLoad(){
	map.centerAndZoom(point, 15);
	map.addOverlay(marker);
//	marker.enableDragging();//标注点是否可以拖动
	map.panTo(point);
	//标注点拖拽监听
//    marker.addEventListener("dragend", function (e) {
//    	//检查是否选择省市县
//    	if(!checkChoose()){
//    		return;
//    	}
//    	updateLngLat(e.point);
//    	//修改文字标签
//    	getLocation(e.point);
//    	
//    	var cp = map.getCenter();
//    	$('#lat').val( e.point.lat);
//    	$('#lng').val( e.point.lng);
//    	map.panTo(new BMap.Point(e.point.lng, e.point.lat));
//    });
	g.getLocation(point,function(e){
		var addComp = e.addressComponents;
		var locationName=addComp.province + " " + addComp.city+" "+addComp.district+" </br>"+addComp.street + " " + addComp.streetNumber;
		label.setContent(locationName);
	});
	marker.setLabel(label);
	//工具条  比例尺 控件
	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
	map.addControl(top_left_control);        
	map.addControl(top_right_navigation); 
	//地图点击事件
//    map.addEventListener("click", function(e) { 
//    	//检查是否选择省市县
//    	if(!checkChoose()){
//    		return;
//    	}
//    	updateLngLat(e.point);
//    	//修改文字标签
//    	getLocation(e.point);
//    	
//    	var cp = map.getCenter();
//		marker.setPosition(e.point);
//		map.panTo(new BMap.Point(e.point.lng, e.point.lat));
//	});
}
//检查是否已选择省市县
function checkChoose(){
	var provinceId=$("#provinceId").val();
	var cityId=$("#cityId").val();
	var townId=$("#townId").val();
	var address=$("#address").val();
	if(provinceId==''||cityId==''||townId==''||address==''){
		map.panTo(new BMap.Point(lng, lat));
		marker.setPosition(new BMap.Point(lng, lat));
		dialog("请选择省市县详细地址",10,'','');
		return false;
	}
	return true;
}
//修改横纵坐标
function updateLngLat(poimt){
	$('#lng').val(poimt.lng);
	$("#lat").val(poimt.lat);
}
//修改文字标签
function getLocation(poimt){
	g.getLocation(poimt,function(e){
		var addComp = e.addressComponents;
		var locationName=addComp.street + " " + addComp.streetNumber;
		var allLocation=addComp.province +","+ addComp.city  +","+ addComp.district;
		label.setContent(locationName);
		if($.trim(locationName)!=''){
    		$("#address").val(locationName);
		}
		$("#allLocation").val(allLocation);
	});
}
/***搜索开始*****/
function search(map){
	var cityName=$("#cityTip").html();
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "address"
			,"location" : cityName
		});
	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
			
			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
			}    
			str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
			$("#searchResultPanel").html(str);
		});
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
		setPlace();
	});
}
function setPlace(){
	$("#sub-map").show();
	function myFun(){
		var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		updateLngLat(pp);
		getLocation(pp);
		map.centerAndZoom(pp, 15);
		var cp = map.getCenter();
		marker.setPosition(cp);
	}
	var local = new BMap.LocalSearch(map, { //智能搜索
	  onSearchComplete: myFun
	});
	local.search(myValue);
}