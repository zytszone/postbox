<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>替我代领</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
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
    <style>
        .banner{
            height:100px;
            background-image: url("${ctx}/static/images/banner.jpg");
        }
    </style>
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
</head>
<body>
<div class="banner">

</div>
<div class="container-fluid" style="width:90%">
    <input id="boxIds" value = "${boxIds}" type="hidden"/>
    <div class="row" style="margin-top: 10px;">
        <c:if test="${not empty dataList and dataList.size()>0}">
            <button type="button" class="btn btn-success" style="margin-bottom: 5px;" onclick="sureForLead();">确定代领</button>
            <table id="listTable" class="table table-bordered boxList">
                <c:forEach items="${dataList}" var="item">
                    <tr>
                        <td>
                            <input name="boxName" type="hidden" value="${item.boxInfoId}"/>
                                ${item.boxName}
                        </td>
                        <td><fmt:formatDate value="${item.opentime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty dataList or dataList.size()<=0}">
            <table id="listTable" class="table table-bordered boxList">
                <tr>
                    <td style="text-align: center;">没有您需要代领的快递!</td>
                </tr>
            </table>
        </c:if>
    </div>
</div>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    function sureForLead(){
        var boxIds = $("#boxIds").val();
        $.ajax({
            url: basePath + "customer/updateForMeLead",
            data: {"boxIds":boxIds},
            type: 'POST',
            dataType: 'json',
            success: function (res) {
                if(res.code == 0){
                }else{

                }
            },
            error:function(err){

        }
        });
    }
</script>
</html>