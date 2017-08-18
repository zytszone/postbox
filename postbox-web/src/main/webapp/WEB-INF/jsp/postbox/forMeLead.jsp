<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>替我代领</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <%
        String path = request.getContextPath();

        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            basePath = basePath + ":" + request.getServerPort();
        }
        basePath = basePath + path + "/";
    %>
    <c:set var="basePath" value="<%=basePath %>"></c:set>
    <script>
        var basePath = '<%=basePath %>';
    </script>
    <style>
        .banner{
            height:100px;
            background-image: url("${ctx}/static/images/banner.jpg");
        }
    </style>
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
</head>
<body>
<div class="banner">

</div>
<div class="container-fluid" style="width:90%">
    <div class="row" style="margin-top: 10px;">
        <button id="selectAll" type="button" class="btn btn-warning" style="margin-bottom: 5px;">全部选中</button>
        <button id="cancelAll" type="button" class="btn btn-primary" style="margin-bottom: 5px;">全部取消</button>
        <table id="listTable" class="table table-bordered boxList">
            <c:if test="${not empty dataList and dataList.size()>0}">
                <c:forEach items="${dataList}" var="item">
                    <tr>
                        <td>
                            <input name="items" type="checkbox" value="${item.boxInfoId}" onclick="singleCheck();"/>
                        </td>
                        <td>
                            <input name="boxName" type="hidden" value="${item.boxInfoId}"/>
                                ${item.boxName}
                        </td>
                        <td><fmt:formatDate value="${item.opentime}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty dataList or dataList.size()<=0}">
                <tr>
                    <td>您当前没有待领的快递</td>
                </tr>
            </c:if>
        </table>
        <div class="row-fluid">
            <span style="color: red">*</span>&nbsp;选择指定的快递，可以通过<span style="color: red;">右上角微信分享指定好友</span>让好友替你收取快递哦！
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    //要代领的快递
    var boxIds='';

    //全选
    $("#selectAll").on('click',function () {
        $("[name=items]:checkbox").prop("checked", true);
        var chk_value =[];
        $('input[name=items]:checkbox').each(function(){
            chk_value.push($(this).val());
        });
        boxIds = chk_value.join(",");
    });
    //全部取消
    $("#cancelAll").on('click',function () {
        $("[name=items]:checkbox").attr("checked", false);
        boxIds='';
    });
    //单个
    function singleCheck(){
        var chk_value =[];
        $('input[name=items]:checked').each(function(){
            chk_value.push($(this).val());
        });
        boxIds = chk_value.join(",");
    }

    $(function(){
        if(isWeixin()) {
            wx.ready(function () {
                wx.hideMenuItems({
                    menuList: [
                        "menuItem:share:facebook",
                        "menuItem:copyUrl",
                        "menuItem:originPage",
                        "menuItem:readMode",
                        "menuItem:share:email",
                        /* "menuItem:share:appMessage",*/
                        "menuItem:share:timeline",
                        "menuItem:share:qq",
                        "menuItem:share:weiboApp",
                        "menuItem:openWithSafari",
                        "menuItem:favorite",
                        "menuItem:share:QZone"
                    ] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
                });

                wx.onMenuShareAppMessage({
                    title: '${applicationScope['shareTitle']}', // 分享标题
                    desc: '${applicationScope['weixinDefaultShareDesc']}', // 分享描述
                    link: "http:www//baidu.com",//分享当前url
                    imgUrl: basePath +'${applicationScope['weixinDefaultShareIcon']}', // 分享图标
                    type: 'link', // 分享类型,music、video或link，不填默认为link
                    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                    success: function () {
                        // 用户确认分享后执行的回调函数
//                        $.ajax({
//                            url: basePath+'customer/updateProxyCustomerInfoId',
//                            type: "GET",
//                            dataType: "json",
//                            success: function (rsp) {
//                                if (rsp.code == 0) {
//                                    easyDialog.open({
//                                        container: {
//                                            header: '<div style="font-size:15px;">提示信息</div>',
//                                            content: '<div style="font-size:15px;">设置代领人成功</div>',
//                                            yesFn: function(){
//                                                easyDialog.close();
//                                            },
//                                            noFn: false
//                                        }
//                                    });
//                                }else{
//                                    easyDialog.open({
//                                        container: {
//                                            header: '<div style="font-size:15px;">提示信息</div>',
//                                            content: '<div style="font-size:15px;">设置带领人失败</div>',
//                                            yesFn: function(){
//                                                easyDialog.close();
//                                            },
//                                            noFn: false
//                                        }
//                                    });
//                                }
//                            }
//
//                        });
                    },
                    cancel: function () {
                        // 用户取消分享后执行的回调函数
                    }
                });
            })
        }
    })



</script>
</html>