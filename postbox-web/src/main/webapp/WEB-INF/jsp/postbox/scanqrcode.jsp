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
    <title>扫描二维码</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${basePath}static/plugins/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/plugins/bootstrap-3.3.5/css/bootstrap-theme.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
</head>
<body>
<div class="container" style="width:98%">

</div>
</body>
<script type="text/javascript" src="${basePath}static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    $(function(){
        if(isWeixin()) {
            console.log("唤起扫一扫");
            wx.ready(function () {
                wx.scanQRCode({
                    needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                    success: function (res) {
                        console.log(res.toString());
                        var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                    }
                });
            });
        }
    });

</script>
</html>

