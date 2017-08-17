<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的钱包</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
    <%
        String path = request.getContextPath();

        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            basePath = basePath + ":" + request.getServerPort();
        }
        basePath = basePath + path + "/";
    %>
    <c:set var="basePath" value="<%=basePath %>"></c:set>
    <script>
        var basePath = '<%=basePath %>';
    </script>
</head>
<body>
<a class="list-group-item active" style="text-align: center;font-size: 20px;font-weight: bolder;">
    账户余额:&nbsp;&nbsp;&nbsp;<span style="color: orange">0&nbsp;元</span>
</a>
<a href="#" class="list-group-item">
   <span style="width: 50%;text-align: center; color: orangered;font-weight: bolder;">充值</span>
   <span style="width: 50%;text-align: center;color: #1b6d85;font-weight: bolder;">提现</span>
</a>
<a href="#" class="list-group-item">我的银行卡</a>
<a href="#" class="list-group-item">绑定银行卡</a>
<a href="#" class="list-group-item">设置支付密码</a>
<a href="#" class="list-group-item">账户明细</a>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
</html>