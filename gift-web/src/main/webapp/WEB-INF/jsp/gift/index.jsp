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
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
    <!--weixin end-->
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/swiper-3.4.0.min.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/index.css"/>
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/swiper.min.js"></script>
</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
    var isBackControl = true;
</script>
<!--page start-->
<div id="index">
    <div class="account-wrap">
        <div class="account-header"><a href="${basePath}gift/accountDetails">账户详情</a></div>
        <div class="null-wrap" v-show="noGift">
            <div class="icon-null"></div>
            <div class="tip">您的账户暂时没有可用礼品</div>
        </div>
        <div class="swiper-container" v-show="!noGift">
            <div class="swiper-wrapper">
                <div class="swiper-slide" v-for="item in giftList">
                    <a v-bind:href="url+item.commodityId">
                    <div class="clearfix">
                        <div class="float-left">
                            <i class="icon-tea"><img v-bind:src="item.icon"></i>
                        </div>
                        <div class="float-left right">
                            <p class="name">[{{item.typeName}}] {{item.name}}</p>
                            <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                            <p class="money">价值约 {{item.money}}{{item.moneyUnit}}</p>
                        </div>
                    </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="index-title">礼包服务</div>
    <ul class="index-table">
        <li>
            <a href="${basePath}gift/sellIn">
                <p><i class="icon-base icon-index0"></i></p>
                <p class="name">买礼品</p>
            </a>
        </li>
       <li>
           <a href="javascript:void(0);" v-on:click="goUrl('${basePath}gift/setPacket?commodityId='+urlId)">
               <p><i class="icon-base icon-index1"></i></p>
               <p class="name">发礼包</p>
           </a>
       </li>
        <li>
            <a href="javascript:void(0);" v-on:click="goUrl('${basePath}pickUp/takeGoods?commodityId='+urlId)">
                <p><i class="icon-base icon-index2"></i></p>
                <p class="name">提货</p>
            </a>
        </li>
        <li>
            <a href="${basePath}tradeDetail/transactionDetails">
                <p><i class="icon-base icon-index4"></i></p>
                <p class="name">交易明细</p>
            </a>
        </li>
        <li>
            <a href="${basePath}gift/userSettings">
                <p><i class="icon-base icon-index6"></i></p>
                <p class="name">个人设置</p>
            </a>
        </li>
        <li class="no-bb">
            <a href="${basePath}gift/packetRules">
                <p><i class="icon-base icon-index7"></i></p>
                <p class="name">礼包规则</p>
            </a>
        </li>
    </ul>
    <!--二维码提示框-->
    <div class="page-cover" v-show="isFirst" v-on:click.self="closeQrcode">
        <div class="code-wrap">
            <div class="close" v-on:click="closeQrcode">x</div>
            <div class="code-img">
                <img src="${staticPath}/static/images/qrcode.png">
            </div>
            <div class="code-desc">长按二维码关注微礼包公众号，便于您随时查看礼包账户</div>
        </div>
    </div>
    <div class="qrcode-small" v-on:click="showQrcode"></div>
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
<script type="text/javascript" src="${staticPath}/static/js/views/index.js"></script>
</body>
</html>