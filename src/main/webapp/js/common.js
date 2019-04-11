


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
 


        

//左侧菜单栏选中方法
function selectItem(item) {
    console.log(item);
//alert('点击了'+item.text);
    switch (item.text) {
        case '类型管理':
        //页面跳转
            break;
        case '用户管理':

            break;
        case '系统管理':

            break;
        default:


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