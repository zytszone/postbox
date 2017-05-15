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
    <!--weixin start-->
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
    <!--weixin end-->
    <!--- add js --->
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
</head>
<body class="bg-white">
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="packetRequest">
    <div class="page-title">
        礼包规则
    </div>
    <div class="rule-body">
        <h3>买礼品规则</h3>
        <h4>买礼品手续费</h4>
        <p>无手续费</p>
        <h4>支持买礼品方式</h4>
        <p>支持微信零钱支付或银行卡支付</p>
        <h4>买礼品限额</h4>
        <p>单笔最低买入：1克/1毫升</p>
        <p>单笔最高买入：以微信支付限额为准</p>
        <p>日买入限额：以微信支付限额为准</p>
        <p>（微信零钱年支付：日限额1w元，年限额20w元；银行卡支付时，各家银行的支付限额可能不同，以该银行卡的限额为准，点击【钱包】->【银行卡】->某张银行卡，可查看具体支付限额）</p>
        <h4>买礼品交易时间</h4>
        <p>不限制</p>
        <h3>收礼包规则</h3>
        <h4>领取个数</h4>
        <p>同一批礼包每人只能领取一个</p>
        <h3>发礼包规则</h3>
        <h4>发礼包限额</h4>
        <p>单个礼包不小于：1克/1毫升</p>
        <p>单个礼包限额：200克/200毫升</p>
        <p>每次最多可发送礼包数：100个</p>
        <p>最多发送数量：20000克/20000毫升</p>
        <h4>未领取礼包退款时间</h4>
        <p>未领取完的礼包，将在24小时后自动退回到微礼包账户中</p>
        <h4>包好的礼包未发送出去</h4>
        <p>方法一：继续发送给好友</p>
        <p>点击【交易明细】->打开未发送的礼包->点击【继续发送】，选择您向要发送的好友即可</p>
        <p>方法二：等待退款</p>
        <p>包好后未领取的礼包，将在24小时后退回到您的微礼包账户</p>


    </div>
</div>
<!--page end-->
</body>
</html>