
var permission = '0';	//权限
var isLogin = 1;	//是否已登录
var loginFlag = "isLogin";	//登录标记
 

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
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间
    document.cookie = name+"="+""+";expires="+expdate.toGMTString()+";path=/";
}

//获取用户权限
function getPermission(){
var permission = getCookie("permission");
return permission;
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