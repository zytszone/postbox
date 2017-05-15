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
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/iscroll.js"></script>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/getPacket.css"/>
</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="getPacket">
    <div class="packet-wrap" v-show="!isOpen">
        <div v-if="packetFlag==1">
            <div class="packet-img"><img v-bind:src="packet.img"></div>
            <div class="packet-name">{{packet.name}}</div>
            <div class="packet-diviling"><img src="${staticPath}/static/images/packet-img1.png"></div>
            <div class="packet-tip">发了一个礼品红包</div>
            <div class="packet-desc">{{packet.desc}}</div>
            <div class="packet-border"></div>
            <div class="packet-btn"><button v-on:click="openPacket" class="circle-btn">開</button></div>
        </div>
        <div v-if="packetFlag==2">
            <div class="packet-img"><img v-bind:src="packet.img"></div>
            <div class="packet-name">{{packet.name}}的“{{packet.commodityName}}”红包</div>
            <div class="packet-diviling"><img src="${staticPath}/static/images/packet-img1.png"></div>
            <div class="packet-desc-small">好酒需要与朋友分享</div>
            <div class="securities-wrap">
                <div class="packet-number"><span class="number">{{packet.number}}</span>克</div>
                <div class="packet-money">价值约 {{packet.money}}元</div>
            </div>
            <div class="btn-wrap securities"><button class="btn-default" v-on:click="setAccount">存入微礼包账户</button></div>
        </div>
    </div>
    <div v-show="isOpen" class="open-wrap">
        <div v-if="packetFlag==3">
            <div class="packet-img-new"><img v-bind:src="packet.img"></div>
            <div class="packet-name">{{packet.name}}的“{{packet.commodityName}}”红包</div>
            <div class="packet-diviling"><img src="${staticPath}/static/images/packet-img1.png"></div>
            <div class="packet-desc-small">好酒需要与朋友分享</div>
            <div class="packet-number">新人开户劵<span class="number">{{packet.number}}</span>{{packet.commodityUnitName}}</div>
            <div class="packet-money">价值约 {{packet.money}}元</div>
            <div class="packet-account"><a href="index.jsp">（已存入微礼包账户，开户后生效，可点击查看）</a></div>
            <div class="packet-header">已领取{{packet.receive}}/{{packet.doneeQuota}},{{packet.receiveNumber}}/{{packet.commodityTotalQuantity}}克</div>
            <div id="wrapper">
            <div class="scroller-wrap">
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
                            <p class="item-product">{{item.product}}</p>
                        </div>
                    </li>
                </ul>
                <div class="pullUp" >
                    <span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载...</span>
                </div>
            </div>
            </div>
            <div class="btn-wrap"><a href="setPacket.jsp" class="btn-default">我也要发礼品红包</a></div>
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
<script type="text/javascript" src="${staticPath}/static/js/views/getSecurities.js"></script>
</body>
</html>