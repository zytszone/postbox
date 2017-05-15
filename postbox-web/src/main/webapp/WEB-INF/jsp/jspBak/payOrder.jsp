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
    <title>微礼包</title>
    <!--weixin start-->
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
    <!--weixin end-->
    <!--- add js --->
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/deliveryOrder.css"/>
</head>
<body class="bg-white">
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="payOrder">
    <div class="page-title">
        订单详情
    </div>
    <div class="order-state">
        <div class="flex"><span>订单待付款</span><span class="delete-order" c-on:click="deleteOrder">取消订单</span></div>
        <div class="countdown">{{commodity.countdown}}后订单取消</div>
    </div>
    <div class="order-info">
        <div><label>下单时间：</label><span>{{commodity.time}}</span></div>
        <div><label>交易单号：</label><span>{{commodity.logicId}}</span></div>
        <div><label>状态：</label><span class="red">{{commodity.state}}</span></div>
    </div>
    <div class="gift-info clearfix">
        <div class="float-right gift-package">
            ×{{commodity.quantity}}
        </div>
        <div class="float-left product-img">
            <img v-bind:src="commodity.img">
        </div>
        <div class="float-left">
            <p class="gift-name">[{{commodity.commodityType}}]{{commodity.commodityName}}</p>
            <p class="gift-number">1{{commodity.unit}}</p>
            <p class="gift-money">￥{{totalMoney}}</p>
        </div>
    </div>
    <div class="gift-control">
        <label>抵扣</label><span>{{commodity.deduction.toFixed(2)}}{{commodity.unit}}</span>
    </div>
    <div class="gift-control" v-if="commodity.cash>0">
        <label>现金</label><span>￥{{commodity.cash.toFixed(2)}}元</span>
    </div>
    <div class="gift-control">
        <label>运费</label><span>￥{{commodity.expressFee.toFixed(2)}}元</span>
    </div>
    <div class="bottom-wrap">
        <div>
            <span class="label">实付</span><span class="total-money">￥{{totalMoney}}元</span>
        </div>
        <button class="payment" v-on:click="pay">去付款</button>
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
<script type="text/javascript" src="${staticPath}/static/js/views/payOrder.js"></script>
</body>
</html>