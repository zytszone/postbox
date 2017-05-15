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
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/sellOut.css"/>
</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="sellOut">
    <div class="page-title">
        卖出
    </div>
    <div class="bg-white">
        <div class="select-tab"><span v-on:click="changeProduct">[{{product.type}}]{{product.name}}</span><i class="icon-select-down" v-on:click="changeProduct"></i></div>
        <div class="current-hold clearfix">
            <div class="float-left">
                <label>当前价约</label><span>{{product.current}}元/{{product.unit}}</span>
            </div>
            <div class="float-right">
                <label>账户剩余</label><span>{{product.number}}{{product.unit}}</span>
            </div>
        </div>
        <div class="input-box clearfix">
            <span class="float-right unit">{{product.unit}}</span>
            <div class="float-right input-inner">
                <div class="max-tip" id="sellMax">本次最多可卖</div>
                <input type="tel" v-model="inputNumber" v-on:focus="focusInput">
            </div>
            <span class="float-left label">数量</span>
        </div>
        <div class="total"><label>总价值约</label><div class="total-money"><span>{{totalMoney}}</span><span>元</span></div></div>
        <div class="total"><label>预计到账</label><div class="total-money"><span class="number">{{packetTotal}}</span><span>元</span></div></div>
        <div class="service-fee">(手续费{{product.feePercent}},手续费<span class="red">{{serviceFee}}</span>元)</div>
        <div class="rules"><a href="rules.jsp" class="red">卖出规则</a></div>
    </div>
    <div class="submit-btn">
        <button v-on:click="sellPacket">确认卖出</button>
    </div>
    <div class="tip">预计{{arrivalTime}}到账</div>
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
<script type="text/javascript" src="${staticPath}/static/js/views/sellOut.js"></script>
</body>
</html>