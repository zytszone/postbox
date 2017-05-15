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
<html lang="en" style="background: none">
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
<div id="deliveryDetails">
    <div v-show="isPay">
        <div class="page-title">
            提货详情
        </div>
        <div class="order-process">
            <div class="process-line">
                <div class="process-item active">
                    <div class="circle"><div class="high-light"></div></div>
                    <div class="line right-line"></div>
                </div>
                <div class="process-item" v-bind:class="{active:deliveDetailInfo.step==1}">
                    <div class="circle"><div class="high-light"></div></div>
                    <div class="line right-line"></div>
                </div>
                <div class="process-item" v-bind:class="{active:deliveDetailInfo.step==2}">
                    <div class="circle"><div class="high-light"></div></div>
                </div>
                <div class="line-bg"></div>
            </div>
            <div class="process-text">
                <div class="text-item">
                    <div class="title">提交订单</div>
                    <div>{{deliveDetailInfo.timeStart}}</div>
                </div>
                <div class="text-item">
                    <div class="title">待发货</div>
                    <div v-if="deliveDetailInfo.step==1">{{deliveDetailInfo.timeMid}}</div>
                </div>
                <div class="text-item">
                    <div class="title">已发货</div>
                    <div v-if="deliveDetailInfo.step==2">{{deliveDetailInfo.timeEnd}}</div>
                </div>
            </div>
        </div>
        <ul class="list-user border-top">
            <li class="nav-item">
                <label>礼品名</label><span>[{{deliveDetailInfo.typeName}}]{{deliveDetailInfo.name}}</span>
            </li>
            <li class="nav-item">
                <label>提货规格</label><span>{{deliveDetailInfo.rule}}</span>
            </li>
            <li class="nav-item">
                <label>提货数</label><span>{{deliveDetailInfo.applyCommodityStandardUnitQuantity}}{{deliveDetailInfo.standardUnitName}}</span>
            </li>
            <li class="nav-item">
                <label>价值</label><span>{{deliveDetailInfo.money}}元</span>
            </li>
            <li class="nav-item">
                <label>当前状态</label><span>{{deliveDetailInfo.state}}</span>
            </li>
            <li class="nav-item">
                <label>交易单号</label><span>{{deliveDetailInfo.orderId}}</span>
            </li>
            <li class="nav-item">
                <label>抵扣数量</label><span>{{deliveDetailInfo.kouNumber}}{{deliveDetailInfo.unit}}</span>
            </li>
            <li class="nav-item">
                <label>抵扣金额</label><span>{{deliveDetailInfo.kouMoney}}元</span>
            </li>
            <li class="nav-item">
                <label>现金支出</label><span>{{deliveDetailInfo.cash}}元</span>
            </li>
            <li class="nav-item">
                <label>总支出</label><span>{{deliveDetailInfo.total}}元</span>
            </li>
            <li class="nav-item" v-if="deliveDetailInfo.step==2">
                <label>物流公司</label><span>{{deliveDetailInfo.company}}</span>
            </li>
            <li class="nav-item" v-if="deliveDetailInfo.step==2">
                <label>物流单号</label><span>{{deliveDetailInfo.logisticsId}}</span>
            </li>
        </ul>
    </div>
    <div v-show="!isPay">
        <div class="page-title">
            提货详情
        </div>
        <div v-show="!isCancel">
        <div class="order-state">
            <div class="flex"><span>订单待付款</span><span class="delete-order" v-on:click="deleteOrder">取消订单</span></div>
            <div class="countdown">{{deliveDetailInfo.countdown}}后订单取消</div>
        </div>
        </div>
        <div class="order-info">
            <div><label>下单时间：</label><span>{{deliveDetailInfo.timeStart}}</span></div>
            <div><label>交易单号：</label><span>{{deliveDetailInfo.orderId}}</span></div>
            <div><label>状态：</label><span class="red">{{deliveDetailInfo.state}}</span></div>
        </div>
        <%--<div class="user-info">--%>
            <%--<div class="clearfix">--%>
                <%--<label>收件人</label><span class="name">{{username}}</span>--%>
                <%--<div class="float-right mobile">{{telNumber}}</div>--%>
            <%--</div>--%>
            <%--<div class="address-wrap clearfix">--%>
                <%--<a href="${basePath}address/address">--%>
                    <%--<div class="float-right">--%>
                        <%--<i class="icon-right"></i>--%>
                    <%--</div>--%>
                    <%--<div class="float-left">--%>
                        <%--<i class="icon-address"></i>--%>
                    <%--</div>--%>
                    <%--<div class="float-left address">--%>
                        <%--{{address}}--%>
                    <%--</div>--%>
                <%--</a>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="gift-info clearfix">
            <div class="float-right gift-package">
                ×{{deliveDetailInfo.applyCommodityStandardUnitQuantity}}{{deliveDetailInfo.standardUnitName}}
            </div>
            <div class="float-left product-img">
                <img v-bind:src="deliveDetailInfo.img">
            </div>
            <div class="float-left">
                <p class="gift-name">[{{deliveDetailInfo.typeName}}]{{deliveDetailInfo.commodityName}}</p>
                <p class="gift-number">{{deliveDetailInfo.standardUnitMultiple}}{{deliveDetailInfo.unit}}</p>
                <p class="gift-money">￥{{totalMoney}}</p>
            </div>
        </div>
        <div class="gift-control">
            <label>抵扣</label><span>{{deliveDetailInfo.deduction}}{{deliveDetailInfo.unit}}</span>
        </div>
        <div class="gift-control" v-if="deliveDetailInfo.cash>0">
            <label>现金</label><span>￥{{deliveDetailInfo.cash}}元</span>
        </div>
        <div class="gift-control">
            <label>运费</label><span>￥{{deliveDetailInfo.expressFee}}元</span>
        </div>
        <div class="bottom-wrap">
            <div>
                <span class="label">实付</span><span class="total-money">￥{{deliveDetailInfo.cash}}元</span>
            </div>
            <button class="payment" v-on:click="pay"><span v-show="!isCancel">去付款</span><span v-show="isCancel">再次购买</span></button>
        </div>
    </div>
</div>

<!--dialog component start-->
<jsp:include page="../component/dialog.jsp"/>
<!--dialog component end-->

<!--dialog component start-->
<jsp:include page="../component/toast.jsp"/>
<!--dialog component end-->

<!--page end-->
<script type="text/javascript" src="${staticPath}/static/js/common.js"></script>
<script type="text/javascript" src="${staticPath}/static/js/views/deliveryDetails.js"></script>
</body>
</html>