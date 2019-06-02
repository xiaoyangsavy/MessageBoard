var version = '1.0.13'	//版本号
var debug  = false	//测试模式
var permission = '0';	//权限
var isLoginFlag = 'isLogin';	//是否已登录
var usernameFlag = 'username';	//用户名
//var serverUrl = "http://192.168.191.5:8080/message/"	//服务器地址
//var fileUrl = "http://192.168.191.5:8080/"	//文件地址
//var serverUrl = "http://210.30.128.69:8080/message/"	//服务器地址
//var fileUrl = "http://210.30.128.69:8080/"	//文件地址
var serverUrl = "http://192.168.191.4:8080/message/"	//服务器地址
var fileUrl = "http://192.168.191.4:8080/"	//文件地址

var userIdFlag = 'userId';

 

//设置网页缓存
function setCookie (name, value)
{ 
    //设置名称为name,值为value的Cookie
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间
    document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/";
   //即document.cookie= name+"="+value+";path=/";   时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
}

//获取网页缓存
function getCookie(cookieName)
{
if (document.cookie.length>0)
  {
  cookieStart=document.cookie.indexOf(cookieName + "=")
  if (cookieStart!=-1)
    { 
    cookieStart=cookieStart + cookieName.length+1 
    cookieEnd=document.cookie.indexOf(";",cookieStart)
    if (cookieEnd==-1) cookieEnd=document.cookie.length
    return unescape(document.cookie.substring(cookieStart,cookieEnd))
    } 
  }
return ""
}
 
//删除用户缓存
 function delCookie(name)
{
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime()-1);   //时间
    document.cookie = name+"="+""+";expires="+expdate.toGMTString()+";path=/";
}

//获取用户权限
function getPermission(){
var permission = getCookie("permission");
return permission;
}

//获取用户名
function getUsername(){
var username = getCookie(usernameFlag);
return username;
}

//获取用户编号
function getUserId(){
var userId = getCookie(userIdFlag);
return userId;
}
 
 
 
 //获取页面跳转时传递的值
 function getUrlParams(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    };

//线程休眠
function sleep(numberMillis) { var now = new Date(); var exitTime = now.getTime() + numberMillis; while (true) { now = new Date(); if (now.getTime() > exitTime) return true; } }

 
//跳转到指定网页
function goWebpage(url){
 window.location.href=url;
}


//显示提示框
function openPopup(content){
	var popupWrp = '';	//弹框对象
	popupWrp = $('<div id="popupDiv" data-role="popup" data-theme="e" data-overlay-theme="a" data-dismissible="true" class="ui-popup-container ui-popup-active ui-popup ui-corner-all"><p>'+content+'</p></div>');
	popupWrp.append('<a class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right" onclick="closePopup()" href="#">Close</a>');
	popupWrp.appendTo($.mobile.pageContainer);
	popupWrp.trigger('create');			//创建弹窗
	popupWrp.popup().enhanceWithin();	//初始化弹窗
	popupWrp.popup("open");
	return popupWrp;
}

//关闭提示框
 function closePopup(){ 
 var popupWrp = $("#popupDiv"); 
 popupWrp.popup().enhanceWithin(); 
 popupWrp.popup("close"); 
 }


//屏蔽手机返回键
// $(document).ready(function() {
//     if (window.history && window.history.pushState) {
//         $(window).on('popstate', function () {
//             window.history.pushState('forward', null, '#');
//             window.history.forward(1);
//         });
//     }
//     window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
//     window.history.forward(1);
// });
