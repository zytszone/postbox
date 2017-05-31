<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();

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
    <title>开箱扫描结果</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${basePath}static/plugins/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/plugins/bootstrap-3.3.5/css/bootstrap-theme.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container" style="width:98%">
    <div class="row">
        <legend></legend>
        <div class="row-fluid" style="text-align:center;margin-bottom:18px;font-size: 20px;">
            开箱扫描结果
        </div>
        <legend></legend>
        <c:if test="${empty decode}">
        <div class="well">
            <div style="font-size: 18px;margin-bottom: 5px;">对不起，您没有权限打开该箱子！</div>
            <legend></legend>
            <div>请仔细阅读收货短信，确认箱子编号无误。如有疑问，请联系客服400-10000</div>
        </div>
        </c:if>
        <!-- 快递员 -->
        <c:if test="${not empty decode && isSpecial eq true}">
        <div class="well">
            <div style="font-size: 18px;margin-bottom: 5px;">您的开箱密码是：<span style="color:blue;">${decode}</span></div>
        </div>
            <legend></legend>
            <form id="phoneId" method="post" action="${basePath}secret/saveBoxMobile">
                <div class="row">
                    <div class="col-lg-12">
                        <span class="error-notic" style="display:none;color:red;">手机号码格式不正确</span>
                        <div class="input-group" style="margin-bottom: 15px;">
                            <input type="text" class="form-control" id="mobileNumber" placeholder="请输入收件人手机号码" aria-describedby="basic-addon1">
                            <span class="input-group-btn">
                                <button style="width:80px;margin-left: 2px; padding: 7px 10px;" class="btn btn-success btn-sm submit" type="button">确定</button>
                            </span>
                        </div>
                    </div>
                </div>
            </form>
        </c:if>
        <!-- 普通用户 -->
        <c:if test="${not empty decode && isSpecial eq false}">
        <div class="well">
            <div style="font-size: 18px;margin-bottom: 5px;">您的开箱密码是：<span style="color:blue;">${decode}</span></div>
            <legend></legend>
            <div>输入密码完成后,请关闭箱子（点击右上角关闭箱子按钮）</div>
        </div>
        </c:if>

    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    $(function() {
        $('#mobileNumber').on('input',function () {
            var value = $('#mobileNumber').val();
            var re = new RegExp("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
            if (!re.test(value)){
                $('.error-notic').show();
                $('.submit').attr("disabled",true);
                if(value==''){
                    $('.error-notic').hide();
                    $('.submit').attr("disabled",false);
                }
            }else{
                $('.error-notic').hide();
                $('.submit').attr("disabled",false);
            }
        });

        $('.submit').on('click', function () {
            var phone = $("#mobileNumber").val();
            if('' == phone) {
                easyDialog.open({
                    container: {
                        header: '<div style="font-size:15px;">操作提示</div>',
                        content: '<div style="font-size:15px;">请输入收件人手机号码</div>',
                        yesFn: function(){
                            easyDialog.close();
                        },
                        noFn: false
                    }
                });
                return;
            }
            $.ajax({
                url: "${basePath}secret/saveBoxMobile",
                data: {"mobile":phone,"boxId":'${boxId}',"skey":'${skey}'},
                type: 'POST',
                dataType: 'json',
                beforeSend:function(){
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;">操作提示</div>',
                            content: '<div style="font-size:15px;">服务器处理中，请稍候...</div>',
                            yesFn: false,
                            noFn: false
                        }
                    });
                },
                success: function (res) {
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;">操作提示</div>',
                            content: '<div style="font-size:15px;">收件人手机号码更新成功，请关闭箱子！</div>',
                            yesFn: function(){
                                easyDialog.close();
                            },
                            noFn: false
                        }
                    });
                },
                error: function () {
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;">操作提示</div>',
                            content: '<div style="font-size:15px;">请求发生异常！</div>',
                            yesFn: function(){
                                easyDialog.close();
                            },
                            noFn: false
                        }
                    });
                }
            });
        });
    });

    function showMsg(title, msg){
        easyDialog.open({
            container: {
                header: '<div style="font-size:15px;">'+title+'</div>',
                content: '<div style="font-size:15px;">'+msg+'</div>',
                yesFn: false,
                noFn: false
            }
        });
    }
</script>
</html>

