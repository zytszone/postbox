$(function(){
    $.ajax({
        url: basePath + "express/getTakeInfoList",
        type: "POST",
        data:'',
        dataType: "json",
        success: function (rsp) {
            if(rsp.code == 0){
                var data = rsp.body;
                var mainUl=$("#mainUl").html();
                if(data.length > 0){
                    for(var i =0;i<data.length;i++){
                       mainUl += '<li class="list-group-item clearfix"><span>' + data[i].boxName +'</span><span class="middle">' + data[i].boxCode + '</span><span class="half">'+ (new Date(data[i].taketime)).Format('yyyy-MM-dd hh:mm:ss')  +'</span></li>';
                    }
                    $("#mainUl").html(mainUl);
                }else{
                    $("#mainUl").html('<li class="list-group-item" style="text-align: center;">暂时没有领取历史</li>');
                }
            }else{
                easyDialog.open({
                    container: {
                        header: '<div style="font-size:15px;">错误信息</div>',
                        content: '<div style="font-size:15px;">data</div>',
                        yesFn: function(){
                            easyDialog.close();
                        },
                        noFn: false
                    }
                });
            }
        }
    });
});

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}