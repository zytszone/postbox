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
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/register.css"/>
    <script src="${staticPath}/static/js/views/jquery.min.js"></script>
</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<form>
<div class="login-con">
    <div class="form-group title">
        <h2>XXXXXXXXXXXX</h2>
       <%-- <h5>同一个世界，同一个梦</h5>--%>
    </div>
    <div class="form-group">
        <input type="tel" placeholder="请输入手机号码" id="mobileNumber">
        <span class="error-notic">手机号码不正确</span>
    </div>
    <div class="form-group checkbox">
        <label for="check">是否成为快递员：</label>
        <input type="checkbox" id="check" name="check" checked>
    </div>
    <div class="form-group">
        <button id="btn"  type="submit" class="tran pr">
            <a href="javascript:;" class="tran" id="register">注册</a>
        </button>
    </div>
</div>
</form>
<!--page end-->
<script>
    $(document).ready(function () {
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
                url: basePath + "main/bind",
                type: "POST",
                data: {"phone":phone,"isSpecial":true},
                type: 'POST',
                dataType: 'json',
                success: function (res) {
                    if(res.code == 0){
                        alert("页面跳转到相关页面");
                    }else{
                        alert(res.body);
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