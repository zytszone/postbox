<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
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
</head>
<body>
    <div class="container" style="width:90%;margin: 45% auto 0">
        <form id="forMeLeadForm" method="post" action="">
            <span class="error-notic mobile-error" style="display:none;color:red;">手机号码格式不正确</span>
            <div class="input-group">
                <span class="input-group-addon" style="text-align:right;">代领人手机号</span>
                <input type="text" class="form-control" placeholder="请输入代领人手机号" id="mobileNumber" aria-describedby="basic-addon3">
            </div>
            <br/>

            <span class="error-notic boxCode-error" style="display:none;color:red;">箱子编码格式不正确</span>
            <div class="input-group">
                <span class="input-group-addon" style="text-align:right;">箱子编码</span>
                <input type="text" class="form-control" placeholder="请输入箱子编码" id="boxCode" aria-describedby="basic-addon3">
            </div>
        </form>

        <legend></legend>
        <div class="row-fluid" style="text-align:center;margin-bottom:10px;">
            <button style="margin-left:8px;width:80px;" class="btn btn-success btn-sm submit" type="button" onclick="submitForm();">确定</button>
        </div>
    </div>
</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">

    $('#mobileNumber').on('input',function () {
        var value = $('#mobileNumber').val();
        var re = new RegExp("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9])|(17[0-9]))\\d{8}$");
        if (!re.test(value)){
            $('.mobile-error').show();
            $('.btn').attr("disabled",true);
            if(value==''){
                $('.mobile-error').hide();
                $('.btn').attr("disabled",false);
            }
        }else{
            $('.mobile-error').hide();
            $('.btn').attr("disabled",false);
        }
    });

    $('#boxCode').on('input',function () {
        var value = $('#boxCode').val();
        var re = new RegExp("^[A-Za-z0-9]+$");
        if (!re.test(value)){
            $('.boxCode-error').show();
            $('.btn').attr("disabled",true);
            if(value==''){
                $('.boxCode-error').hide();
                $('.btn').attr("disabled",false);
            }
        }else{
            $('.boxCode-error').hide();
            $('.btn').attr("disabled",false);
        }
    });

    function submitForm(){
        var mobileNumber = $('#mobileNumber').val();
        var boxCode = $('#boxCode').val();
        if(mobileNumber==''){
            $('.mobile-error').show();
            $('.btn').attr("disabled",true);
            return;
        }
        if(boxCode==''){
            $('.boxCode-error').show();
            $('.btn').attr("disabled",true);
            return;
        }

        $.ajax({
            url: basePath + "customer/forMeLead",
            data: {"mobile":mobileNumber,"boxCode":boxCode},
            type: 'POST',
            dataType: 'json',
            success: function (res) {
                if(res.code == 0) {
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;">提示信息</div>',
                            content: '<div style="font-size:15px;">设置带领人成功</div>',
                            yesFn: function(){
                                easyDialog.close();
                            },
                            noFn: false
                        }
                    });
                }else{
                    easyDialog.open({
                        container: {
                            header: '<div style="font-size:15px;">错误信息</div>',
                            content: '<div style="font-size:15px;">设置带领人失败</div>',
                            yesFn: function(){
                                easyDialog.close();
                            },
                            noFn: false
                        }
                    });
                }
            },
            error:function(err){
                easyDialog.open({
                    container: {
                        header: '<div style="font-size:15px;">错误提示</div>',
                        content: '<div style="font-size:15px;">请求失败,请检查</div>',
                        yesFn: function(){
                            easyDialog.close();
                        },
                        noFn: false
                    }
                });
            }
        });



    }
</script>
</html>