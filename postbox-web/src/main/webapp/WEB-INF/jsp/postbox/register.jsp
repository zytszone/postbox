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
    <title>注册成会员</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${basePath}static/plugins/bootstrap-3.3.5/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/plugins/bootstrap-3.3.5/css/bootstrap-theme.css" rel="stylesheet" type="text/css" />
    <link href="${basePath}static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <style>
        .courier{
            display: none;
        }
    </style>
</head>
<body>
    <div class="container" style="width:98%">
        <legend></legend>
        <div class="row-fluid" style="text-align:center;margin-bottom:18px;font-size: 20px;">
            注册成会员
        </div>
        <legend></legend>
        <form id="register" method="post" action="">
            <span class="error-notic name-error" style="display:none;color:red;">请输入真实姓名</span>
            <div class="input-group">
                <span class="input-group-addon" style="text-align:right;">您的真实姓名</span>
                <input type="text" class="form-control" placeholder="请输入真实姓名" id="customerName" aria-describedby="basic-addon3">
            </div>
            <br/>
            <span class="error-notic mobile-error" style="display:none;color:red;">手机号码格式不正确</span>
            <div class="input-group">
                <span class="input-group-addon" style="text-align:right;">您的手机号码</span>
                <input type="text" class="form-control" placeholder="请输入手机号码" id="mobileNumber" aria-describedby="basic-addon3">
            </div>
            <br/>
            <span class="error-notic check-error" style="display:none;color:red;">请输入手机验证码</span>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入手机验证码" id="mobileCheck" aria-describedby="basic-addon3">
                <span class="input-group-btn">
                    <button style="width:80px;margin-left: 2px; padding: 7px 10px;" class="btn btn-default btn-sm" type="button" onclick="smsSend();">发送验证码</button>
                </span>
            </div>
            <br/>
            <div class="input-group">
                <label for="check">是否成为快递员：</label>
                <input type="checkbox" id="check" name="check">
            </div>
            <br/>
            <div class="input-group courier">
                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div class="thumbnail">
                            <img src="${basePath}static/images/card2.png"/>
                            <div class="caption">
                                <h3>手持身份证照片</h3>
                                <p>证件人五官以及身份证上所有信息必须完整有效且清晰可见</p>
                                <p><a href="#" class="btn btn-primary" role="button">上传</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br/>
            <div class="input-group courier">
                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div class="thumbnail">
                            <img src="${basePath}static/images/card3.png"/>
                            <div class="caption">
                                <h3>工作证件照片</h3>
                                <p>证件照片上的信息必须完整有效且清晰可见</p>
                                <p><a href="#" class="btn btn-primary" role="button">上传</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <legend></legend>
            <div class="row-fluid" style="text-align:center;margin-bottom:10px;">
                <button style="margin-left:8px;width:80px;" class="btn btn-success btn-sm submit" type="button">注册</button>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript" src="${basePath}static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    var staticPath = '${staticPath}';
    var basePath = '<%=basePath %>';
    $(function(){
        $('#check').on('change',function(){
            var checked=$(this).is(':checked');
            if(checked){
                $('.courier').show();
            }else{
                $('.courier').hide();
            }
        });

        $('#mobileNumber').on('input',function () {
            var value = $('#mobileNumber').val();
            var re = new RegExp("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9])|(17[0-9]))\\d{8}$");
            if (!re.test(value)){
                $('.mobile-error').show();
                $('#btn').attr("disabled",true);
                if(value==''){
                    $('.mobile-error').hide();
                    $('#btn').attr("disabled",false);
                }
            }else{
                $('.mobile-error').hide();
                $('#btn').attr("disabled",false);
            }
        });

        $('.submit').on('click', function () {
            var customerName=$('#customerName').val();
            if(!customerName) {
                easyDialog.open({
                    container: {
                        header: '<div style="font-size:15px;">操作提示</div>',
                        content: '<div style="font-size:15px;">请输入真实姓名</div>',
                        yesFn: function(){
                            easyDialog.close();
                        },
                        noFn: false
                    }
                });
                return;
            }

            var phone = $("#mobileNumber").val();
            if(!phone) {
                easyDialog.open({
                    container: {
                        header: '<div style="font-size:15px;">操作提示</div>',
                        content: '<div style="font-size:15px;">请输入手机号码</div>',
                        yesFn: function(){
                            easyDialog.close();
                        },
                        noFn: false
                    }
                });
                return;
            }

            var mobileCheckno=$('#mobileCheck').val();
            if(!mobileCheckno) {
                easyDialog.open({
                    container: {
                        header: '<div style="font-size:15px;">操作提示</div>',
                        content: '<div style="font-size:15px;">请输入手机验证码</div>',
                        yesFn: function(){
                            easyDialog.close();
                        },
                        noFn: false
                    }
                });
                return;
            }

            var check = $("input[type='checkbox']").is(':checked');
            $.ajax({
                url: basePath + "main/bind",
                data: {"phone":phone,"isSpecial":check,"redirecturl":'${redirecturl}'},
                type: 'POST',
                dataType: 'json',
                success: function (res) {
                    if(res.code == 0){
                        easyDialog.open({
                            container: {
                                header: '<div style="font-size:15px;">操作提示</div>',
                                content: '<div style="font-size:15px;">恭喜您，注册成功</div>',
                                yesFn: function(){
                                    window.location.href=res.body;
                                },
                                noFn: false
                            }
                        });
                    }else{
                        easyDialog.open({
                            container: {
                                header: '<div style="font-size:15px;">操作提示</div>',
                                content: '<div style="font-size:15px;">'+res.body+'</div>',
                                yesFn: function(){
                                    easyDialog.close();
                                },
                                noFn: false
                            }
                        });
                    }
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
        })
    });

    /**
     * 发送手机验证码
     */
    function smsSend(){
        $.ajax({
            url: basePath + "main/bind",
            data: {"phone":phone,"isSpecial":check,"redirecturl":'${redirecturl}'},
            type: 'POST',
            dataType: 'json',
            success: function (res) {
                if(res.code == 0) {
                }
            }
        });
    }
</script>
</html>