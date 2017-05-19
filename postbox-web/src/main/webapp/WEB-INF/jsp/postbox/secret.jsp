<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();

    String basePath = request.getScheme() + "://" + request.getServerName();
    if (request.getServerPort() != 80 && request.getServerPort() != 443) {
        basePath = basePath + ":" + request.getServerPort();
    }
    basePath = basePath + path + "/";
%>
<c:set var="basePath" value="<%=basePath %>"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-title" content=""/>
    <meta name="apple-mobile-web-app-capable" content="no"/>
    <meta name="msapplication-tap-highlight" content="no">
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="format-detection" content="telephone=no,email=no"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>开箱密码</title>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/register.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/views/password.css"/>

    <script src="${basePath}/static/js/views/jquery.min.js"></script>
</head>
<body>
<!--page start-->
<form id="phoneId" style="margin-top:180px;display: none;" >
    <div class="login-con">
        <div class="form-group">
            <input type="tel" placeholder="请输入手机号码" id="mobileNumber">
            <span class="error-notic">手机号码不正确</span>
        </div>
        <div class="form-group">
            <button id="btn"  type="submit" class="tran pr">
                <a href="javascript:;" class="tran" id="register">提交</a>
            </button>
        </div>
    </div>
</form>
<div id="decodeId" class="animate seven" style="display: none;">
    <span></span><span></span><span></span><span></span><span></span><span></span>
</div>

<div id="errorMsgId" class="animate seven" style="display: none;">
</div>

<!--page end-->
<script>
    $(document).ready(function () {


        var decode = '${decode}';
        if(undefined == decode || ''==decode){
            //没有权限打开
            $("#phoneId").attr("display",none);
            $("#decodeId").attr("display",none);
            $("#errorMsgId").attr("display",display);
            $("#errorMsgId").html("没有权限打开");
        }else {
            var number = decode;
            var span = $('.animate span');
            for(var i=0;i<number.length;i++){
                span.eq(i).html(number[i]);
            }
            $("#phoneId").attr("display",none);
            $("#errorMsgId").attr("display",none);
            $("#decodeId").attr("display",none);

            if('${isSpecial}' == 'false'){
                //普通用户
                $("#decodeId").attr("display",display);
            }else{
                //快递员
                $("#phoneId").attr("display",display);
            }
        }

        $('#mobileNumber').on('input',function () {
            var value = $('#mobileNumber').val();
            var re = new RegExp("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
            if (!re.test(value)){
                $('.error-notic').show();
                $('#btn').attr("disabled",true);
                if(value==''){
                    $('.error-notic').hide();
                    $('#btn').attr("disabled",false);
                }
            }else{
                $('.error-notic').hide();
                $('#btn').attr("disabled",false);
            }
        });
        $('#register').on('click', function () {
            var phone = $("#mobileNumber").val();
            if('' == phone) {
                alert("请输入手机号");
                return;
            }
            var check = $("#check").val();
            $.ajax({
                url: basePath + "secret/saveBoxMobile",
                data: {"mobile":phone,"boxId":'${boxId}',"skey":'${skey}'},
                type: 'POST',
                dataType: 'json',
                success: function (res) {
                    if(res.code == 0){
                        $("#phoneId").attr("display",none);
                        $("#errorMsgId").attr("display",none);
                        $("#decodeId").attr("display",display);
                    }else{
                        alert("更新箱子属主失败");
                    }
                },
                error: function () {
                    alert("请求发生异常");
                }
            });
        })

    });
</script>
</body>
</html>