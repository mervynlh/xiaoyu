//全局变量default布局中重新赋值
var mydomain ="";//主站域
var usercookiekey="";//用户key,只存key，不存实际信息
var baselocation = "";
var imagesPath="";
var keImageUploadUrl="";//kindeditor中使用的路径需要2个参数来区分项目和模块
var uploadSimpleUrl="";//单独的上传按钮使用的路径
var disFaceUpUrl="";//小组头像上传路径
var max_text_length=50000;

/**
 * 判断Cookies中是否有用户信息
 * 	仅页面操作用，实际操作中要判断用户是否登录成功
 * @param baseLocation
 * @returns {Boolean}
 */
function isLogin() {
	var _isl=false;
	if (isNotEmpty(getCookie(usercookiekey))) {
		$.ajax({
			url:baselocation+"/islogin",
			data:{},
			type : "post", 
			dataType : "json",
			async:false,
			success: function (result){
				if(result.success){
					_isl=true;
				}else{
					//DeleteCookie(usercookiekey);
				}
			}
		});
	}
	return _isl;
}

//退出
function exit() {
	$.ajax({
		url:baselocation+"/exit",
		data:{},
		type : "post", 
		dataType : "json",
		async:false,
		success: function (result){
		}
	});
	window.location.href = baselocation;
}

if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length >>> 0;

    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;

    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}
 


/**
 * 获取Cookies方法
 * @param cookieName
 * @returns
 */
function getCookie(cookieName) {

	var cookieString = document.cookie;
	var start = cookieString.indexOf(cookieName + '=');
	// 加上等号的原因是避免在某些 Cookie 的值里有
	// 与 cookieName 一样的字符串。
	if (start == -1) // 找不到
		return null;
	start += cookieName.length + 1;
	var end = cookieString.indexOf(';', start);
	if (end == -1) {
		return unescape(cookieString.substring(start));
	} else {
		return unescape(cookieString.substring(start, end));
	}
}

/**
 * 获取Cookies方法,解决中文乱码
 * @param cookieName
 * @returns
 */
function getCookieFromServer(cookieName) {
	var cookieString = document.cookie;
	var start = cookieString.indexOf(cookieName + '=');
	// 加上等号的原因是避免在某些 Cookie 的值里有
	// 与 cookieName 一样的字符串。
	if (start == -1) // 找不到
		return null;
	start += cookieName.length + 1;
	var end = cookieString.indexOf(';', start);
	if (end == -1) {
		return Url.decode(cookieString.substring(start));
	} else {
		return Url.decode(cookieString.substring(start, end));
	}
}

/**
 * 删除Cookies
 * @param name
 */
function DeleteCookie(name) {
	DeleteCookieDomain(name,mydomain);
}

/**创建Cookies
 * @param name
 * @param value
 */
function SetCookie(name, value) {
	SetCookieDomain(name, value,mydomain);
}

//自定义cookies失效时间 s指秒 h指天数 d指天数 如s40代表40秒
function SetCookieOutTime(name, value, outTime) {
	var strsec = getsec(outTime);
	var exp = new Date();
	exp.setTime(exp.getTime() + strsec * 1);
	if(isNotEmpty(mydomain)){
		document.cookie = name + "=" + escape(value) + ";expires="
				+ exp.toGMTString()+";path=/"+";domain="+mydomain;
	}else{
		document.cookie = name + "=" + escape(value) + ";expires="
		+ exp.toGMTString()+";path=/";
	}
}

// 转换cookies时间
function getsec(str) {
	var str1 = str.substring(1, str.length) * 1;
	var str2 = str.substring(0, 1);
	if (str2 == "s") {
		return str1 * 1000;
	} else if (str2 == "h") {
		return str1 * 60 * 60 * 1000;
	} else if (str2 == "d") {
		return str1 * 24 * 60 * 60 * 1000;
	}
}

/**
 * 删除指定域名下的共享cookie.二级域名可用
 * @param name
 * @param domain
 */
function DeleteCookieDomain(name,domain) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if(isNotEmpty(domain)){
		document.cookie = name + "=" + escape(cval) + ";expires="
		+ exp.toGMTString() + ";path=/"+";domain="+domain;
	}else{
		document.cookie = name + "=" + escape(cval) + ";expires="
		+ exp.toGMTString() + ";path=/";
	}
}

/**
 * 创建Cookies 可设置域名
 * @param name
 * @param value
 * @param domain
 */
function SetCookieDomain(name, value,domain) {
	var Days = 2;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	if(isNotEmpty(domain)){
		document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString() + ";path=/"+";domain="+domain;
	}else{
		document.cookie = name + "=" + escape(value) + ";expires="
		+ exp.toGMTString() + ";path=/";
	}
}

/**
 * 删除所有的cookie
 */
function clearCookie(){
	var strCookie=document.cookie;
	  var arrCookie=strCookie.split("; "); // 将多cookie切割为多个名/值对
	  for(var i=0;i <arrCookie.length;i++)
	{ // 遍历cookie数组，处理每个cookie对
	    var arr=arrCookie[i].split("=");
	    if(arr.length>0)
	    	DeleteCookieDomain(arr[0],mydomain);
	}
}
/**
 * 获取URL中的参数
 * @param val
 * @returns
 */
function getParameter(val) {
	var uri = window.location.search;
	var re = new RegExp("" + val + "=([^&?]*)", "ig");
	return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
}

var Url = {
	encode : function(string) {
		return escape(this._utf8_encode(string));
	},
	decode : function(string) {
		return this._utf8_decode(unescape(string));
	},
	_utf8_encode : function(string) {
		string = string.replace(/\r\n/g, "\n");
		var utftext = "";

		for (var n = 0; n < string.length; n++) {

			var c = string.charCodeAt(n);

			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}

		return utftext;
	},

	// private method for UTF-8 decoding
	_utf8_decode : function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while (i < utftext.length) {

			c = utftext.charCodeAt(i);

			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}

		}
		return string;
	}
};


/**
 * String去空格函数
 * @returns
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
};

/**
 * 去掉< >代码
 * @param data
 * @returns
 */	
function ReplaceTagHTML(data){
	var r = /\<(.+?)\>/gi;
	data = data.replace(r,"");
	return data;
}

/**
 * 去掉< >代码 然后截取指定长度
 * @param data
 * @returns
 */	
function ReplaceTagHTMLSubLength(data,length){
	var r = /\<(.+?)\>/gi;
	data = data.replace(r,"");
	if (data == null||data == '') {
		return null;
	} else {
		if (data.length <= length) {
			return data;
		} else {
			data = data.substring(0, length) + "...";
			return data;
		}
	}
	return data;
}

/**
 * 自定义JS.StringBuffer
 * @returns
 */
function StringBuffer()
{ 
	this.data = []; 
}
StringBuffer.prototype.append = function(){ 
	this.data.push(arguments[0]); 
	return this; 
}; 
StringBuffer.prototype.toString = function(){ 
	return this.data.join(""); 
};
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
    } else {  
        return this.replace(reallyDo, replaceWith);  
    }  
}; 

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};	
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, "");
};

/**
 * 判断字符串是否为空
 * @param str
 * @returns {Boolean}
 */
function isNotEmpty(str){
	if(str==null || str=="" || str.trim()==''){
		return false;
	}
	return true;
}

function isEmpty(str){
	if(str==null || str=="" || str.trim()==''){
		return true;
	}
	return false;
}


//BASE64 encode and decode
var BASE64={
enKey: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
deKey: new Array(
	-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
	52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
	-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
	15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
	-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
	41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
),
encode: function(src){
	var str=new Array();
	var ch1, ch2, ch3;
	var pos=0;
	while(pos+3<=src.length){
	ch1=src.charCodeAt(pos++);
	ch2=src.charCodeAt(pos++);
	ch3=src.charCodeAt(pos++);
	str.push(this.enKey.charAt(ch1>>2), this.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
	str.push(this.enKey.charAt(((ch2<<2)+(ch3>>6))&0x3f), this.enKey.charAt(ch3&0x3f));
	}
	if(pos<src.length){
	ch1=src.charCodeAt(pos++);
	str.push(this.enKey.charAt(ch1>>2));
	if(pos<src.length){
	ch2=src.charCodeAt(pos);
	str.push(this.enKey.charAt(((ch1<<4)+(ch2>>4))&0x3f));
	str.push(this.enKey.charAt(ch2<<2&0x3f), '=');
	}else{
	str.push(this.enKey.charAt(ch1<<4&0x3f), '==');
	}
	}
	return str.join('');
},
decode: function(src){
	var str=new Array();
	var ch1, ch2, ch3, ch4;
	var pos=0;
	src=src.replace(/[^A-Za-z0-9\+\/]/g, '');
	while(pos+4<=src.length){
	ch1=this.deKey[src.charCodeAt(pos++)];
	ch2=this.deKey[src.charCodeAt(pos++)];
	ch3=this.deKey[src.charCodeAt(pos++)];
	ch4=this.deKey[src.charCodeAt(pos++)];
	str.push(String.fromCharCode(
	(ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2), (ch3<<6&0xff)+ch4));
	}
	if(pos+1<src.length){
	ch1=this.deKey[src.charCodeAt(pos++)];
	ch2=this.deKey[src.charCodeAt(pos++)];
	if(pos<src.length){
	ch3=this.deKey[src.charCodeAt(pos)];
	str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4), (ch2<<4&0xff)+(ch3>>2)));
	}else{
	str.push(String.fromCharCode((ch1<<2&0xff)+(ch2>>4)));
	}
	}
	return str.join('');
	}
};

var ajaxUrl;//记录上次ajax分页的url
var ajaxparameters;//记录上次ajax分页的参数
var ajaxcallBack;
//ajax分页方法获取数据
function ajaxPage(url,parameters,pageNum,callBack){
	ajaxUrl = url;
	ajaxparameters = parameters;
	ajaxcallBack=callBack;
	parameters='page.currentPage='+pageNum+''+parameters;
	$.ajax({
		type : "POST",
		dataType : "text",
		url:baselocation+url,
		data : parameters,
		cache : true,
		async : false,
		success : callBack
	});
}
//点击分页
function goPageAjax(pageNum){
	ajaxPage(ajaxUrl,ajaxparameters,pageNum,ajaxcallBack);
}
//判断是否登陆并进行一些操作
function checkLogin(){
	//登陆才可观看
	 if(!isLogin()){
	 	//记录当前地址登陆后回调
		  var uri = window.location.href;
		  var name = "forward";
		  var value = uri;
		  //存会话级cookies
		  if(isNotEmpty(mydomain)){
				document.cookie = name + "=" + escape(value) + ";path=/"+";domain="+mydomain;
			}else{
				document.cookie = name + "=" + escape(value) + ";path=/";
			}
		  window.location.href=demoWebDomain+"/login";
		  return "unLogin";
	 }else{
	 	return "login";
	 }

}
function isNotEmpty(str){
	return !isEmpty(str);
}

function isEmpty(str){
	if(str==null || str=="" || str.trim().length==0){
		return true;
	}
	return false;
}

function isNull(object){
	if(typeof(object)=="undefined" || object==null ||  object==''){
		return true;
	}
	return false;
}


function isNotNull(object){
	return !isNull(object);
}

function enterSubmit(event, str) {
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
    if (keyCode == 13) {
		eval(str);
	}
}

//自定义方法-IE下不支持indexOf方法
    if (!Array.indexOf) {
        Array.prototype.indexOf = function(obj) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == obj) {
                 return i;
                }
            }
        return -1;
        };
    }
	//自定义方法-数组去重复
	Array.prototype.unique = function(){
		var newArr = []; //一个新的临时数组
		for(var i = 0; i < this.length; i++){ //遍历当前数组
			if(this[i]==""){
				continue;
			}
			//如果当前数组的第i已经保存进了临时数组，那么跳过，否则把当前项push到临时数组里面
			if (newArr.indexOf(this[i]) == -1){
			    newArr.push(this[i]);
			 }
		}
		return newArr;
	}


/**
 * 检测电脑是否安装了flash
 * @returns {___anonymous44316_44376}
 */
function flashChecker(){
    var hasFlash = 0; //是否安装了flash  
    var flashVersion = 0; //flash版本  
    if(document.all) {  
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');  
        if(swf) {  
            hasFlash = 1;  
            VSwf = swf.GetVariable("$version");  
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);  
        }  
    }else{  
        if(navigator.plugins && navigator.plugins.length > 0) {  
            var swf = navigator.plugins["Shockwave Flash"];  
            if(swf) {  
                hasFlash = 1;  
                var words = swf.description.split(" ");  
                for(var i = 0; i < words.length; ++i) {  
                    if(isNaN(parseInt(words[i]))) continue;  
                    flashVersion = parseInt(words[i]);  
                }  
            }  
        }  
    }  
    return {  
        f: hasFlash,  
        v: flashVersion  
    };  
}
/**
 * 是否安装了Flash
 * @returns {Boolean}
 */
function testingFlash(){
	var fls = flashChecker();  
    if(fls.f) {
    	return true;
    }else{
    	return false;
    } 
}
/**
 * 数字格式化
 * @param money
 * @param n
 * @returns
 */
function fixNumber(money,n){
    if(typeof(money) == 'undefined' || money == null || money ==""|| isNaN(money) ||money == Infinity){
        return money;
    }else{
        return parseFloat(money).toFixed(n);
    }
};
/**
 * 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 * 调用：accAdd(arg1,arg2)
 * @param arg1
 * @param arg2
 * @returns {Number}返回值：arg1加上arg2的精确结果  
 */
function accAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = (1 * arg1).toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = (1 * arg2).toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m + arg2 * m) / m;
}    
/**
 * 说明：javascript的减法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的减法结果。
 * 调用：accSub(arg1,arg2)
 * 返回值：arg1减上arg2的精确结果
 * @param arg1
 * @param arg2
 * @returns
 */    
function accSub(arg1, arg2) {
	return accAdd(arg1, -arg2);
}    
 /**
  * 
  * 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
  * 调用：accMul(arg1,arg2)
  * 返回值：arg1乘以arg2的精确结果    
  * @param arg1
  * @param arg2
  * @returns {Number}
  */  

function accMul(arg1, arg2) {
	var m = 0, s1 = (1 * arg1).toString(), s2 = (1 * arg2).toString();
	try {
		m += s1.split(".")[1].length;
	}
	catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	}
	catch (e) {
	}
	var ss = Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
	return Math.round(ss * 100) / 100;
}    
 /**
  * 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
  * 调用：accDiv(arg1,arg2)
  * 返回值：arg1除以arg2的精确结果
  * @param arg1
  * @param arg2
  * @returns {Number}
  */  
function accDiv(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = (1 * arg1).toString().split(".")[1].length;
	}
	catch (e) {
	}
	try {
		t2 = (1 * arg2).toString().split(".")[1].length;
	}
	catch (e) {
	}
	with (Math) {
		r1 = Number((1 * arg1).toString().replace(".", ""));
		r2 = Number((1 * arg2).toString().replace(".", ""));
		var ss = (r1 / r2) * pow(10, t2 - t1);
		return Math.round(ss * 100) / 100;
	}
}
/**
 * 金额大小写转换
 * @param currencyDigits
 * @returns
 */
function convertCurrency(currencyDigits) {
	//Constants:
	var MAXIMUM_NUMBER = 99999999999.99;
	//Predefine the radix characters and currency symbols for output:
	var CN_ZERO = "零";
	var CN_ONE = "壹";
	var CN_TWO = "贰";
	var CN_THREE = "叁";
	var CN_FOUR = "肆";
	var CN_FIVE = "伍";
	var CN_SIX = "陆";
	var CN_SEVEN = "柒";
	var CN_EIGHT = "捌";
	var CN_NINE = "玖";
	var CN_TEN = "拾";
	var CN_HUNDRED = "佰";
	var CN_THOUSAND = "仟";
	var CN_TEN_THOUSAND = "万";
	var CN_HUNDRED_MILLION = "亿";
	var CN_SYMBOL = "人民币";
	var CN_DOLLAR = "元";
	var CN_TEN_CENT = "角";
	var CN_CENT = "分";
	var CN_INTEGER = "整";
	
	//Variables:
	var integral; // Represent integral part of digit number. 
	var decimal; // Represent decimal part of digit number.
	var outputCharacters; // The output result.
	var parts;
	var digits, radices, bigRadices, decimals;
	var zeroCount;
	var i, p, d;
	var quotient, modulus;
	
	//Validate input string:
	currencyDigits = currencyDigits.toString();
	if (currencyDigits == "") {
		alert("Empty input!");
		return "";
	}
	if (currencyDigits.match(/[^,.\d]/) != null) {
		alert("Invalid characters in the input string!");
		return "";
	}
	if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
		alert("Illegal format of digit number!");
		return "";
	}
	
	//Normalize the format of input digits:
	currencyDigits = currencyDigits.replace(/,/g, ""); // Remove comma delimiters.
	currencyDigits = currencyDigits.replace(/^0+/, ""); // Trim zeros at the beginning. 
	//Assert the number is not greater than the maximum number.
	if (Number(currencyDigits) > MAXIMUM_NUMBER) {
		alert("Too large a number to convert!");
		return "";
	}
	//http://www.knowsky.com/ Process the coversion from currency digits to characters:
	//Separate integral and decimal parts before processing coversion:
	parts = currencyDigits.split(".");
	if (parts.length > 1) {
		integral = parts[0];
		decimal = parts[1];
		// Cut down redundant decimal digits that are after the second.
		decimal = decimal.substr(0, 2);
	}
	else {
		integral = parts[0];
		decimal = "";
	}
	//Prepare the characters corresponding to the digits:
	digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT,CN_NINE);
	radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
	bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
	decimals = new Array(CN_TEN_CENT, CN_CENT);
	//Start processing:
	outputCharacters = "";
	//Process integral part if it is larger than 0:
	if (Number(integral) > 0) {
		zeroCount = 0;
		for (i = 0; i < integral.length; i++) {
			 p = integral.length - i - 1;
			 d = integral.substr(i, 1);
			 quotient = p / 4;
			 modulus = p % 4;
			 if (d == "0") {
				 zeroCount++;
			 }  else {
				  if (zeroCount > 0) {
				   outputCharacters += digits[0];
				  }
				  zeroCount = 0;
				  outputCharacters += digits[Number(d)] + radices[modulus];
			 }
			 if (modulus == 0 && zeroCount < 4) { 
				 outputCharacters += bigRadices[quotient];
			 }
		}
		outputCharacters += CN_DOLLAR;
	}
	//Process decimal part if there is:
	if (decimal != "") {
		for (i = 0; i < decimal.length; i++) {
			 d = decimal.substr(i, 1);
			 if (d != "0") {
				 outputCharacters += digits[Number(d)] + decimals[i];
			 }
		}
	}
	//Confirm and return the final output string:
	if (outputCharacters == "") {
		outputCharacters = CN_ZERO + CN_DOLLAR;
	}
	if (decimal == "") {
		outputCharacters += CN_INTEGER;
	}
	//outputCharacters = CN_SYMBOL + outputCharacters;
	outputCharacters = outputCharacters;
	return outputCharacters;
}// 
//还原金额   
function rmoney(s){ 
	return parseFloat(s.replace(/[^\d\.-]/g,""));  
} 	

//金额合计的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
function fmoney(s,n){
	n = n>0 && n<=20 ? n : 2;
	s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
	var l = s.split(".")[0].split("").reverse(), 
	r = s.split(".")[1]; 
	t = "";
	for(i = 0;i<l.length;i++){
		t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "," : ""); 
	}
	return t.split("").reverse().join("")+"."+r;
}
function killIe6() {
    if (!window.XMLHttpRequest) {
        window.location.href="/static/common/kill-ie.html";
    };
}

killIe6();

var totalPageSize=0;//总页码
var currentPage =0;
var totalPage =0;
function showPageNumber() {
	var pageHtml = "";
	var maxNum_new = currentPage > 4 ? 6 : 7 - currentPage;//最大显示页码数
	var discnt = 1;
	for ( var i = 3; i > 0; i--) {
		if (currentPage > i) {
			pageHtml = pageHtml + "<li><a href='javascript:goPageAjax("
					+ (currentPage - i) + ")'>" + (currentPage - i)
					+ "</a></li>";
			discnt++;
		}
		;
	}
	pageHtml = pageHtml + '<li><a href="javascript:void(0)"  class="current">'
			+ currentPage + '</a></li>';
	for ( var i = 1; i < maxNum_new; i++) {
		if (currentPage + i <= totalPageSize && discnt < 7) {
			pageHtml = pageHtml + "<li><a href='javascript:goPageAjax("
					+ (currentPage + i) + ")'>" + (currentPage + i)
					+ "</a></li>";
			discnt++;
		} else {
			break;
		}
		;
	}
	$(pageHtml).insertBefore("#nextpage");
}
