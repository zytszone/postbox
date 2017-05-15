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
    <link rel="stylesheet" type="text/css" href="${staticPath}/static/css/views/transactionDetails.css"/>
    <script type="text/javascript" src="${staticPath}/static/js/flexible_css.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/flexible.debug.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/vue.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/touche.js"></script>
    <script type="text/javascript" src="${staticPath}/static/js/p-pull-refresh.js"></script>
</head>
<body>
<script>
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
</script>
<!--page start-->
<div id="transactionDetails">
    <div class="fixed">
        <div class="page-title">
            交易明细
        </div>
        <div class="list-header select-tab clearfix">
            <select v-model="typeValue" v-on:change="changeType">
                <option v-for="option in typeList" v-bind:value="option.value">{{option.text}}</option>
            </select>
            <select v-model="productValue" v-on:change="changeType">
                <option v-for="option in productList" v-bind:value="option.commodityTypeId">{{option.name}}</option>
            </select>
        </div>
        <div class="date-wrap">
            <div class="date-box">
                <i class="icon-arrow-left" v-on:click="changeDate(-1)"></i><span>{{dateValue}}</span><i
                    class="icon-arrow-right" v-on:click="changeDate(1)"></i>
            </div>
        </div>
    </div>
    <div class="container" id="container">
        <div class="loading-warp">
            <div class="box">
                <div>
                    <img src="${staticPath}/static/images/loading.gif" />
                    <span class="text">下拉开始刷新</span>
                </div>
            </div>
        </div>
        <div class="list-container" id="wrapper">
        <div id="scrollList" class="scroll">
            <ul>
                <template v-for="item in giftList">
                    <li class="clearfix" v-if="item.bizType == 'GIVE_GIFT'">
                        <a v-bind:href="url1+item.bizId+'/'+item.giftInfoCode">
                            <div class="float-left fa"><div class="type">发</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">{{item.state}}</p>
                            </div>
                        </a>
                    </li>
                    <li class="clearfix" v-if="item.bizType == 'ACCEPT_GIFT'">
                        <a v-bind:href="url3+item.bizId+'/'+item.giftInfoCode">
                            <div class="float-left shou"><div class="type">收</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">{{item.state}}</p>
                            </div>
                        </a>
                    </li>
                    <li class="clearfix" v-if="item.bizType == 'DELIVERY'">
                        <a v-bind:href="url4+item.bizId+'&bizType='+item.bizType" v-if="item.state=='NOPAY'">
                            <div class="float-left ti"><div class="type">提</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state red">待付款</p>
                            </div>
                        </a>
                        <a v-bind:href="url4+item.bizId" v-if="item.state=='CANCELLED'">
                            <div class="float-left ti"><div class="type">提</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">已取消</p>
                            </div>
                        </a>
                        <a v-bind:href="url4+item.bizId" v-if="item.state=='NOT_DELIVER'">
                            <div class="float-left ti"><div class="type">提</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">待发货</p>
                            </div>
                        </a>

                        <a v-bind:href="url4+item.bizId" v-if="item.state=='DELIVERED'">
                            <div class="float-left ti"><div class="type">提</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">已发货</p>
                            </div>
                        </a>
                    </li>
                    <li class="clearfix" v-if="item.bizType == 'BUY_COMMODITY'">
                        <a v-bind:href="url2+item.bizId" v-if="item.state=='NOPAY'">
                            <div class="float-left mai"><div class="type">买</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state red">待付款</p>
                            </div>
                        </a>
                        <a v-bind:href="url2+item.bizId" v-if="item.state=='CANCELLED'">
                            <div class="float-left mai"><div class="type">买</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">已取消</p>
                            </div>
                        </a>
                        <a v-bind:href="url2+item.bizId" v-if="item.state=='SUCCESS'">
                            <div class="float-left mai"><div class="type">买</div></div>
                            <div class="float-left">
                                <p class="name">[{{item.typeName}}]{{item.name}}</p>
                                <p class="date">{{item.date}}</p>
                            </div>
                            <div class="float-left">
                                <p class="number"><span>{{item.number}}</span>{{item.unit}}</p>
                                <p></p>
                            </div>
                            <div class="float-left">
                                <p class="money">价值<span>{{item.money}}</span>元</p>
                                <p class="state">已付款</p>
                            </div>
                        </a>
                    </li>
                </template>
            </ul>
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
<script type="text/javascript" src="${staticPath}/static/js/views/transactionDetails.js"></script>
</body>
</html>