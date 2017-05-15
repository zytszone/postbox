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
<html lang="en" style="background: none;">
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
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="sellInDetails">
    <div v-show="isPay">
        <div class="page-title">
            买入详情
        </div>
        <div class="order-process">
            <div class="process-line">
                <div class="process-item active">
                    <div class="circle"><div class="high-light"></div></div>
                    <div class="line right-line"></div>
                </div>
                <div class="process-item active">
                    <div class="line left-line"></div>
                    <div class="circle"><div class="high-light"></div></div>
                </div>
                <div class="line-bg"></div>
            </div>
            <div class="process-text">
                <div class="text-item">
                    <div class="title">提交订单</div>
                    <div>{{order.timeStart}}</div>
                </div>
                <div class="text-item">
                    <div class="title">{{order.state}}</div>
                    <div>{{order.timeCurrent}}</div>
                </div>
            </div>
        </div>
        <ul class="list-user border-top">
            <li class="nav-item">
                <label>礼品名</label><span>[{{order.type}}]{{order.name}}</span>
            </li>
            <li class="nav-item">
                <label>买入数量</label><span>{{order.number}}{{order.unit}}</span>
            </li>
            <li class="nav-item">
                <label>买入价</label><span>{{order.price}}元/{{order.unit}}</span>
            </li>
            <li class="nav-item">
                <label>当前状态</label><span>{{order.state}}</span>
            </li>
            <li class="nav-item">
                <label>交易单号</label><span>{{order.orderId}}</span>
            </li>
            <li class="nav-item">
                <label>支付金额</label><span>{{order.pay}}元</span>
            </li>
        </ul>
    </div>
    <div v-show="!isPay">
        <div class="page-title">
            订单详情
        </div>
        <div v-show="!isCancel">
        <div class="order-state">
            <div class="flex"><span>订单待付款</span><span class="delete-order" v-on:click="deleteOrder">取消订单</span></div>
            <div class="countdown">{{order.countdown}}后订单取消</div>
        </div>
        <div class="order-info">
            <div><label>下单时间：</label><span>{{order.timeStart}}</span></div>
            <div><label>交易单号：</label><span>{{order.orderId}}</span></div>
            <div><label>状态：</label><span class="red">{{order.state}}</span></div>
        </div>
        </div>
        <div class="gift-info clearfix">
            <div class="float-right gift-package">
                ×{{order.quantity}}
            </div>
            <div class="float-left product-img">
                <img v-bind:src="order.img">
            </div>
            <div class="float-left">
                <p class="gift-name">[{{order.commodityType}}]{{order.unit}}</p>
                <p class="gift-number">1{{order.unit}}</p>
                <p class="gift-money">￥{{totalMoney}}</p>
            </div>
        </div>
        <div class="gift-control">
            <label>抵扣</label><span>{{order.deduction.toFixed(2)}}{{order.unit}}</span>
        </div>
        <div class="gift-control" v-if="order.cash>0">
            <label>现金</label><span>￥{{order.cash}}元</span>
        </div>
        <div class="gift-control">
            <label>运费</label><span>￥{{order.expressFee.toFixed(2)}}元</span>
        </div>
        <div class="bottom-wrap">
            <div>
                <span class="label">实付</span><span class="total-money">￥{{totalMoney}}元</span>
            </div>
            <button class="payment" v-on:click="pay"><span v-show="!isCancel">去付款</span><span v-show="isCancel">再次购买</span></button>
        </div>
    </div>
</div>
<!--page end-->
<script type="text/javascript" src="${staticPath}/static/js/common.js"></script>
<script type="text/javascript" src="${staticPath}/static/js/views/sellInDetails.js"></script>
</body>
</html>