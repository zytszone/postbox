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
    <link rel="stylesheet" type="text/css" href="${basePath}/static/css/views/password.css"/>
    <script src="${basePath}/static/js/views/jquery.min.js"></script>
</head>
<body>
<!--page start-->
<div class="animate seven">
    <span></span><span></span><span></span><span></span><span></span><span></span>
</div>

<!--page end-->
<script>
    $(document).ready(function () {
        //第一种
        var number = '${decode}';
        var span = $('.animate span');
        for(var i=0;i<number.length;i++){
            span.eq(i).html(number[i]);
        }
    });
</script>
</body>
</html>