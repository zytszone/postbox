<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>领取历史</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
    <style>
        span{
            width: 25%;float: left;
        }
        span.middle{
            width: 25%;float: left;
            text-align: right;
        }
        span.half{
            width: 50%;float: left;
            text-align: right;
        }
    </style>

</head>
<body>

<li class="list-group-item active"  style="text-align: center;border-top-left-radius:0;border-top-right-radius:0;">快递领取历史列表</li>
<ul class="list-group" id="mainUl">
    <li class="list-group-item clearfix">
        <span>箱子名称</span>
        <span class="middle">箱子编号</span>
        <span class="half">领取时间</span>
    </li>
</ul>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/views/expressTakeInfo.js"></script>
</html>