
var permission = '0';
var itemId = '';

//设置网页缓存
function Setcookie (name, value)
{ 
    //设置名称为name,值为value的Cookie
    var expdate = new Date();   //初始化时间
    expdate.setTime(expdate.getTime() + 30 * 60 * 1000);   //时间
    document.cookie = name+"="+value+";expires="+expdate.toGMTString()+";path=/";
   //即document.cookie= name+"="+value+";path=/";   时间可以不要，但路径(path)必须要填写，因为JS的默认路径是当前页，如果不填，此cookie只在当前页面生效！~
}

//获取网页缓存
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
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

//退出
 function logout() {
	delCookie("u_name")
	 window.location.href="login.html";
} 


 
//左侧菜单栏选中方法
function selectItem(item) {
    console.log("selectItem:");
	console.log(item.id);
	console.log("permission="+permission);
//alert('点击了'+item.text);
    switch (item.text) {
        case '类型管理':
        //页面跳转
		 window.location.href='./type_management.html?permission='+permission;
            break;
        case '用户管理':
         window.location.href='./user_management.html?permission='+permission;  
            break;
        default:
		 window.location.href='./main.html?permission='+permission+'&itemId='+item.id; 
            break;
    }
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


//获取本地存储token
function getToken(){
	var token = "";
	if (!window.localStorage) {	
		alert("浏览器不支持localstorage，请更换浏览器！");
	} else {// 支持，继续操作
		var storage = window.localStorage;
		token = storage["token"];
		console.log("get location token:"+token);
		if (token == null || token == undefined || token == '') {
			 console.log("token get error！");
		}else{
			token = "token:"+token;
		}
	}
	return token;
}