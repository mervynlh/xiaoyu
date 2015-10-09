//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果    
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
      
//说明：javascript的减法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的减法结果。
//调用：accSub(arg1,arg2)
//返回值：arg1减上arg2的精确结果    
function accSub(arg1, arg2) {
	return accAdd(arg1, -arg2);
}    
     
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果    
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
     
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果    
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
//金额大小写转换
function convertCurrency(currencyDigits) {
// Constants:
var MAXIMUM_NUMBER = 99999999999.99;
// Predefine the radix characters and currency symbols for output:
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

// Variables:
var integral; // Represent integral part of digit number. 
var decimal; // Represent decimal part of digit number.
var outputCharacters; // The output result.
var parts;
var digits, radices, bigRadices, decimals;
var zeroCount;
var i, p, d;
var quotient, modulus;

// Validate input string:
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

// Normalize the format of input digits:
currencyDigits = currencyDigits.replace(/,/g, ""); // Remove comma delimiters.
currencyDigits = currencyDigits.replace(/^0+/, ""); // Trim zeros at the beginning. 
// Assert the number is not greater than the maximum number.
if (Number(currencyDigits) > MAXIMUM_NUMBER) {
  alert("Too large a number to convert!");
  return "";
}
// http://www.knowsky.com/ Process the coversion from currency digits to characters:
// Separate integral and decimal parts before processing coversion:
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
// Prepare the characters corresponding to the digits:
digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT,CN_NINE);
radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
decimals = new Array(CN_TEN_CENT, CN_CENT);
// Start processing:
outputCharacters = "";
// Process integral part if it is larger than 0:
if (Number(integral) > 0) {
  zeroCount = 0;
  for (i = 0; i < integral.length; i++) {
   p = integral.length - i - 1;
   d = integral.substr(i, 1);
   quotient = p / 4;
   modulus = p % 4;
   if (d == "0") {
    zeroCount++;
   }
   else {
    if (zeroCount > 0)
    {
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
// Process decimal part if there is:
if (decimal != "") {
  for (i = 0; i < decimal.length; i++) {
   d = decimal.substr(i, 1);
   if (d != "0") {
    outputCharacters += digits[Number(d)] + decimals[i];
   }
  }
}
// Confirm and return the final output string:
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
