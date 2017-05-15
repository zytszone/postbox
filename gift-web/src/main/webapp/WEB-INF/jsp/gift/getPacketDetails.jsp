<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <!--- add js --->
    <!--weixin start-->
    <jsp:include page="../include/forbiddenWeixinDefaultShare.jsp"/>
    <!--weixin end-->
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/getPacket.css"/>
</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->

<input id="giftInfoId" value="${giftInfoId}" type="hidden"/><%--礼包id--%>
<input id="giftInfoCode" value="${giftInfoCode}" type="hidden"/><%--礼包code--%>
<div id="getPacketDetails" class="get-packet open">
    <div class="open-wrap">
        <div v-bind:class="bgClass">
            <div class="packet-img-new"><img v-bind:src="packet.img"></div>
            <div class="packet-name">{{packet.name}}的礼品红包</div>
            <div class="packet-diviling"><img src="${staticPath}/static/images/packet-img1.png"></div>
            <div class="packet-desc-small">{{packet.desc}}</div>
            <div v-show="isGetPacket">
            <div class="packet-number"><span class="number">{{packet.number}}</span>{{packet.commodityUnitName}}
            </div>
            </div>
            <div v-show="!isGetPacket" class="zhanwei"></div>
            <div class="packet-product">[{{packet.typeName}}]{{packet.commodityName}}</div>
            <div v-show="isGetPacket">
            <div class="packet-money">价值约 {{packet.money}}元</div>
            <div class="packet-account"><a href="${basePath}gift/index">（已存入微礼包账户，可点击查看）</a></div>
            </div>
            <div v-show="!isGetPacket" class="zhanwei"></div>
            <div class="packet-header">
                已领取{{packet.receive}}/{{packet.doneeQuota}}个,{{packet.receiveNumber}}/{{packet.commodityTotalQuantity}}{{packet.commodityUnitName}}
            </div>
        </div>
        <ul class="list-wrap">
            <li v-for="item in packet.packetList" class="clearfix">
                <div class="float-left">
                    <img v-bind:src="item.img" class="item-img">
                </div>
                <div class="item-user float-left">
                    <p class="item-name">{{item.name}}</p>
                    <p class="item-time">{{item.time}}</p>
                </div>
                <div class="item-info float-right">
                    <p class="item-number">{{item.number}}</p>
                    <%--<p class="item-product">{{item.commodityName}}</p>--%>
                </div>
            </li>
        </ul>
        <div class="btn-wrap"><a href="${basePath}gift/setPacket" class="btn-default">我也要发礼品红包</a></div>
    </div>
</div>
<!--page end-->

<!------------------------------------------------------------------------------>
<!--component start-->

<!--dialog component start-->
<jsp:include page="../component/dialog.jsp"/>
<!--dialog component end-->

<!--dialog component start-->
<jsp:include page="../component/toast.jsp"/>
<!--dialog component end-->
<!--component end-->

<script type="text/javascript" src="${staticPath}/static/js/common.js"></script>
<script type="text/javascript" src="${staticPath}/static/js/views/getPacketDetails.js"></script>
</body>
</html>