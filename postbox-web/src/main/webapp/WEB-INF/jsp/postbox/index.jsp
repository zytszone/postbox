<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-title" content="大泰信息技术有限公司"/>
    <meta name="apple-mobile-web-app-capable" content="no"/>
    <meta name="msapplication-tap-highlight" content="no">
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="format-detection" content="telephone=no,email=no"/>
    <meta name="keywords" content="大泰信息技术有限公司"/>
    <meta name="description" content="大泰信息技术有限公司"/>
    <title>礼尚</title>

    <jsp:include page="../include/weixinDefaultShare.jsp"/>

    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>

</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div>
    <form style="font-size: 50px;">
        注册手机号： <input  style="font-size: 50px;" id="phone" name = "phone" value = "请输入手机号"/>
        注册成为快递员:<input style="font-size: 50px;" id="ipSpecial" name = "ipSpecial" value = "是否要成为快递员"/>
    </form>
    <button onclick="submitForm();" style="font-size: 50px;">注册提交</button>

</div>
<script>
function submitForm(){
    var phone = $("#phone").val();
    var ipSpecial = $("#ipSpecial").val();
    $.ajax({
        url: basePath + "main/signIn",
        type: "POST",
        data: {"phone":phone,"ipSpecial":ipSpecial},
        dataType: "json",
        success: function (rsp) {
            if(rsp.code==0){
                alert("跳转页面");
            }else{
                alert(rsp.body);
            }
        }
    });
}

</script>

</body>
</html>