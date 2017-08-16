<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String path = request.getContextPath();
//    String basePath = request.getScheme() + "://"
//            + request.getServerName() + ":" + request.getServerPort()
//            + path + "/";
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            basePath = basePath + ":" + request.getServerPort();
        }
        basePath = basePath + path + "/";
    %>
    <c:set var="basePath" value="<%=basePath %>"></c:set>
    <title>个人中心</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <script>
        var basePath = '<%=basePath %>';
    </script>
</head>
<body>
<div class="jumbotron" style="background:#C1FFE4;margin-bottom: 0px;">
    <div class="container" style="padding-left: 5%;">
        <div><img style="width: 40px;height: 40px;border-radius:5px" src="${ctx}/static/images/headImg.jpg"></div>
        <div>姓名：${customerInfo.realname}</div>
        <div>手机号：${customerInfo.mobilePhone}</div>
        <div>注册信息：已注册</div>
    </div>
</div>
<ul class="list-group"></div>
    <li class="list-group-item" onclick="submit('myWallet');"><span style="color: red;" class="glyphicon glyphicon-yen" aria-hidden="true"></span>&nbsp;&nbsp;我的钱包<span style="float: right;color: #777777;" class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
    <li class="list-group-item" onclick="submit('expressTakeInfo');"><span style="color: orangered;" class="glyphicon glyphicon-move" aria-hidden="true"></span>&nbsp;&nbsp;领取快递历史<span style="float: right;color: #777777;" class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
    <li class="list-group-item" onclick="submit();"><span style="color: green;" class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;邀请好友<span style="float: right;color: #777777;" class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
    <li class="list-group-item" onclick="submit();"><span style="color: #a5cb21;" class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;设置<span style="float: right;color: #777777;" class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
    <li class="list-group-item" onclick="submit();"><span style="color: blue;" class="glyphicon glyphicon-leaf" aria-hidden="true"></span>&nbsp;&nbsp;版本说明<span style="float: right;color: #777777;" class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></li>
</ul>

<ul class="list-group" style="position:fixed;left:0;bottom:0;text-align: center;width: 100%;margin-bottom: 0px;">
    <li class="list-group-item" style="color: orange;"><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>&nbsp;&nbsp;客服电话: 40008 86868</li>
    <li class="list-group-item" style="color: #5b8496;">服务时间  8:30 - 22:00</li>
</ul>
</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">

    function submit(type){
        if(type == "expressTakeInfo"){
            window.location.href = basePath + "express/expressTakeInfo";
        }else if(type == "myWallet"){
            window.location.href = basePath + "customer/toMyWallet";
        }else{
            easyDialog.open({
                container: {
                    header: '<div style="font-size:15px;">操作提示</div>',
                    content: '<div style="font-size:15px;">该功能暂不对外开放</div>',
                    yesFn: function(){
                        easyDialog.close();
                    },
                    noFn: false
                }
            });
        }
    }
</script>
</html>