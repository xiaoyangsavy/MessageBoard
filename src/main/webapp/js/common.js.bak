//获取缓存
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
 

//左侧菜单栏数据
  var data = [{
            text: '类别',
            iconCls: 'icon-more',
            state: 'open',
            children: [{
                text: '所有类别'
            },{
				text: '教务教学'
            },{
                text: '后勤服务'
            },{
                text: '学生管理'
            },{
                text: '书记信箱'
            },{
                text: '校长信箱'
            }]
        },{
            text: '数据管理',
            iconCls: 'icon-more',
            state: 'open',
            children: [{
                text: '类型管理'
            },{
                text: '用户管理'
            },{
                text: '系统管理'
            }]
        }];
        

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