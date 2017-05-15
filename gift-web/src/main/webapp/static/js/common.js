//重写jquery的ajax方法
(function($){
    //首先备份下jquery的ajax方法
    var _ajax=$.ajax;

    //重写jquery的ajax方法
    $.ajax=function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){}
        }
        if(opt.error){
            fn.error=opt.error;
        }
        if(opt.success){
            fn.success=opt.success;
        }

        //扩展增强处理
        var _opt = $.extend(opt,{
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //错误方法增强处理
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },
            success:function(data, textStatus){
                //成功回调方法增强处理
                if(data.code && data.code.toString() =='502'){
                    window.location.reload();
                }
                fn.success(data, textStatus);
            },
            beforeSend:function(XHR){
                //提交前回调方法

            },
            complete:function(XHR, TS){
                //请求完成后回调函数 (请求成功或失败之后均调用)。
            }
        });
        return _ajax(_opt);
    };
})(jQuery);

//获取url参数
function getQueryString(name) {
    var r = window.location.search.substr(1);
    var arr = r.split('&');
    var obj = {};
    for(var i=0;i<arr.length;i++){
        var val = arr[i].split('=');
        obj[val[0]] = decodeURI(val[1]);
    }
    if(name){
        if(obj[name]) {
            return obj[name];
        }else{
            return "";
        }

    }
    return obj;
}

//日期格式化
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

function timeToDate(time) {
    var date = new Date(time);
    return date.format('yyyy-MM-dd hh:mm:ss');
}

function GetDateStr(currDate,AddMonthCount) {
    var dd = new Date(currDate);
    var currMonth = parseInt(dd.getMonth(),10);
    var year =  parseInt(dd.getFullYear(),10);
    if(AddMonthCount==-1){
        if(currMonth==0){
            currMonth = 12;
            year-=1;
        }else{
            currMonth=currMonth+AddMonthCount+1;
        }
    }else if(AddMonthCount==1){
        if(currMonth==11){
            currMonth = 1;
            year+=1;
        }else{
            currMonth=currMonth+AddMonthCount+1;
        }
    }
    if(currMonth<10){
        currMonth='0'+currMonth;
    }
    return year+'-'+currMonth;
}

//写cookies

function setCookie(name,value)
{
    var Days = 90;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

//读取cookies
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

//倒计时
function GetRTime(time){
    var EndTime= new Date(time);
    var NowTime = new Date();
    var t =EndTime.getTime() - NowTime.getTime();
    var d=0;
    var h=0;
    var m=0;
    var s=0;
    if(t>=0){
        d=Math.floor(t/1000/60/60/24);
        h=Math.floor(t/1000/60/60%24);
        m=Math.floor(t/1000/60%60);
        s=Math.floor(t/1000%60);
    }
    var str = '';
    // if(d!=0){
        str+=d + "天";
    // }
    // if(h!=0){
        str+=timeFormat(h) + "时";
    // }
    // if(m!=0){
        str+=timeFormat(m) + "分";
    // }
    // if(s!=0){
        str+=timeFormat(s) + "秒";
    // }
    return str;
}

function timeFormat(time) {
    if(time.toString().length==1){
        time='0'+time;
    }
    return time;
}
if(isBackControl){
    console.log('notBack');
}else {
    pushHistory();
    window.addEventListener("popstate", goBack,false);
    function goBack() {alert(1)
        //window.history.back();
        //在历史记录中后退,这就像用户点击浏览器的后退按钮一样。

        window.history.go(-2);
        //你可以使用go()方法从当前会话的历史记录中加载页面（当前页面位置索引值为0，上一页就是-1，下一页为1）。

        //self.location=document.referrer;
        //可以获取前一页面的URL地址的方法,并返回上一页。
    }
    function pushHistory(){
        var state = {
            title:"",
            url: "#"
        };
        window.history.pushState(state, "", '');
    }
}

