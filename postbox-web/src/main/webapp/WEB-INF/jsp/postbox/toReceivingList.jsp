<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的待领列表</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <style>
        .banner{
            height:100px;
            background-image: url("${ctx}/static/images/banner.jpg");
        }
    </style>
</head>
<body>
<div class="banner">

</div>
<div class="container-fluid" style="width:90%">
    <div class="row">
        <div class="row-fluid" style="text-align:left;margin-bottom:12px;font-size: 18px;margin-top:12px;font-weight: bold;">
            我的待领快递
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered boxList">
            <c:if test="${not empty dataList and dataList.size()>0}">
            <c:forEach items="${dataList}" var="item">
            <tr>
                <td>
                    <input name="boxInfoId" type="hidden" value="${item.boxInfoId}"/>
                    ${item.boxName}
                </td>
                <td><fmt:formatDate value="${item.opentime}" pattern="yyyy-MM-dd"/></td>
                <c:if test="${empty item.proxyCustomerInfoId}">
                    <td><a style="color: brown;text-decoration: underline;">替我代领</a></td>
                </c:if>
                <c:if test="${not empty item.proxyCustomerInfoId}">
                    <td><a style="color: green;text-decoration: underline;">好友叫我代领</a></td>
                </c:if>
                <td><a style="color: red;text-decoration: underline;">详情</a></td>
            </tr>
            </c:forEach>
            </c:if>
            <c:if test="${empty dataList or dataList.size()<=0}">
            <tr>
                <td>您当前没有待领的快递</td>
            </tr>
            </c:if>
        </table>
    </div>
    <c:forEach items="${dataList}" var="item" varStatus="status">
    <div class="row boxDetail" id="detail_${item.boxInfoId}" ${status.index>0?'style="display:none;"':''}>
        <table class="table table-bordered">
            <tr>
                <td>快递地点：${item.boxName}</td>
            </tr>
            <tr>
                <td>箱子编号：${item.boxCode}</td>
            </tr>
            <tr>
                <td>投递日期：<fmt:formatDate value="${item.opentime}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>详细：您的快递包裹位于${item.boxName}附近，${item.boxCode}号箱子。您可以直接扫描箱子上的二维码，即刻领取包裹。感谢您的使用。</td>
            </tr>
            <tr>
                <td>
                    <div style="float:left;padding-top:8px;">无法取出？<a href="javascript:void(0);">点击报错</a></div>
                   <%-- <div style="float:right;">
                        <a href="javascript:void(0);" class="btn btn-sm btn-warning" style="width:65px;">替我代领</a>
                        <a href="javascript:void(0);" class="btn btn-sm btn-primary" style="width:65px;">导&nbsp;&nbsp;航</a>
                    </div>--%>
                </td>
            </tr>
        </table>
    </div>
    </c:forEach>
</div>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    $('.boxList').on('click', 'tr', function() {
        var $input=$(this).find('input[name="boxInfoId"]');
        $('.boxDetail').hide();
        $('#detail_'+$input.val()).show();
    });
</script>
</html>