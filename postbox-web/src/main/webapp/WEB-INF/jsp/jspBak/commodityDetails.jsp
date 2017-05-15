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
    <!--- add js --->
    <!--weixin start-->
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
    <!--weixin end-->
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/swiper-3.4.0.min.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/commodityDetails.css"/>
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/swiper.min.js"></script>
    <%--<script type="text/javascript" src="${staticPath}/static/js/echarts.js"></script>--%>
</head>
<body class="bg-gray">
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="commodityDetails">
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide" v-for="item in imgList">
                <img v-bind:src="item.src" width="100%">
            </div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>
    <div class="commodity-wrap">
        <div class="name">[{{commodity.commodityType}}]{{commodity.commodityName}}</div>
        <div class="desc">
            {{commodity.desc}}
        </div>
        <div class="unit-money">￥{{unitMoney}}</div>
        <div class="time"><span>提货有效期至：{{commodity.validDate}}</span><a href="${basePath}gift/agreement" class="float-right">协议</a></div>
    </div>
    <%--<div class="standard-set" v-on:click="setStandard">--%>
        <%--<i class="icon-right float-right"></i>  <span>规格数量选择</span>--%>
    <%--</div>--%>
    <div class="detail-wrap">
        <div class="detail-title">商品详情</div>
        <div class="detail-item">
            <span>商品名</span><span>{{commodity.commodityName}}</span>
        </div>
        <div class="detail-item">
            <span>单价</span><span>￥{{unitMoney}}</span>
        </div>
        <div class="detail-item">
            <span>规格</span><span>{{commodity.standardUnitMultiple}}{{commodity.unit}}/1{{commodity.standardUnitName}}</span>
        </div>
        <div class="detail-item">
            <span>产区(厂家)</span><span>{{commodity.company}}</span>
        </div>
        <div class="detail-item">
            <span>品牌</span><span>{{commodity.brand}}</span>
        </div>
        <div class="detail-item">
            <span>商品类型</span><span>{{commodity.commodityType}}</span>
        </div>
        <div class="detail-item">
            <span>包装种类</span><span>{{commodity.packingType}}</span>
        </div>
        <div class="detail-item">
            <span>包装尺寸</span><span>{{commodity.packingSize}}</span>
        </div>
    </div>
    <div class="detail-wrap detail-img">
        <div v-for="item in commodity.imgList">
            <img v-bind:src="item.src" alt="">
        </div>
    </div>
    <div class="footer-wrap clearfix">
        <div class="footer-item" v-on:click="sellInPacket">
            <i class="icon-sell-in"></i><span>立即购买</span>
        </div>
    </div>
    <div class="page-cover" v-bind:class="{show:isShow}" v-on:click.self="hide">
        <div class="choose-wrap">
            <div class="clearfix choose-detail">
                <div class="float-left head-img">
                    <img v-bind:src="commodity.headImg">
                </div>
                <div class="float-left detail">
                    <p>
                        <span class="label">价格：</span><span class="money">￥{{totalMoney}}</span>
                    </p>
                    <p>
                        <span class="label">已选择：</span><span class="number">{{inputNumber}}{{chooseUnit}}</span><span>({{commodity.standardUnitMultiple}}{{commodity.unit}}/{{commodity.standardUnitName}})</span>
                    </p>
                </div>
            </div>
            <div class="standard">
                <div class="title">规格</div>
                <div class="standard-box">
                    <span class="active">{{commodity.unit}}</span><span>{{commodity.standardUnitName}}</span>
                </div>
            </div>
            <div class="choose-number">
                <div class="title">数量</div>
                <div class="number-box">
                    <span v-on:click="minus">-</span><input type="tel" v-model="inputNumber" placeholder="1"><span v-on:click="plus">+</span>
                </div>
            </div>
        </div>
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
<%--<script type="text/javascript" src="${staticPath}/static/js/chart.js"></script>--%>

<script type="text/javascript" src="${staticPath}/static/js/views/commodityDetails.js"></script>
</body>
</html>