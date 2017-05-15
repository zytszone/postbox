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
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/setPacket.css"/>
</head>
<body>
<!--page start-->
<div id="setSecurities" class="bg-white">
    <div class="select-tab"><span v-on:click="changeProduct">[{{product.type}}]{{product.name}}</span><i class="icon-select-down" v-on:click="changeProduct"></i></div>
    <div class="current-hold">当前持有</div>
    <div class="current-number"><span class="number">{{product.number}}</span><span>{{product.unit}}</span></div>
    <div class="plan-title">红包分配方案</div>
    <div class="box-wrap">
        <div class="set-box clearfix">
            <div class="box-input"><input type="tel" name="unit"><span>{{product.unit}}</span></div>
            <div class="box-input"><input type="tel" name="number"><span>个</span></div>
            <button class="delete">-</button>
        </div>
        <div class="set-box clearfix">
            <div class="box-input"><input type="tel" name="unit"><span>{{product.unit}}</span></div>
            <div class="box-input"><input type="tel" name="number"><span>个</span></div>
            <button class="add">+</button>
        </div>
    </div>
    <div class="input-box">
        <span class="float-left label">红包有效期</span>
        <input type="date" placeholder="请设置有效期">
    </div>
    <div class="packet-tip">礼包{{totalUnit}}{{product.unit}}，总数{{totalPacket}}个</div>
    <div class="values">价值约</div>
    <div class="total-money"><span>价值约</span><span class="number">{{totalMoney}}</span><span>元</span></div>
    <div class="packet-desc"><textarea v-model="packetDesc" placeholder="好酒需要与朋友分享" maxlength="40"></textarea></div>
    <div class="packet-info">发送数量从礼包账户中扣除</div>
    <div class="set-packet" v-on:click="sendPacket"><p>塞礼品</p><p>进红包</p></div>
</div>
<!--page end-->

<!------------------------------------------------------------------------------>
<!--component start-->

<!--selectList component start-->
<jsp:include page="../component/selectList.jsp"/>
<!--selectList component end-->

<!--dialog component start-->
<jsp:include page="../component/dialog.jsp"/>
<!--dialog component end-->

<!--dialog component start-->
<jsp:include page="../component/toast.jsp"/>
<!--dialog component end-->

<!--component end-->
<script type="text/javascript" src="${staticPath}/static/js/common.js"></script>
<script type="text/javascript" src="${staticPath}/static/js/views/setSecurities.js"></script>
</body>
</html>