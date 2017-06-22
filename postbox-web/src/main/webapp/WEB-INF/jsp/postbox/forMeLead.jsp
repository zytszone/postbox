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
    <style>
        .banner{
            height:100px;
            background-image: url("${ctx}/static/images/banner.jpg");
        }
    </style>
</head>
<body style="width: 100%;height:100%;background:#D7FFEE;">
<div class="banner" style="margin-bottom: 5%;opacity:0.8;">

</div>
<div>
    <form class="form-horizontal">
        <div class="form-group" style="width: 100%;">
            <label for="name" class="col-sm-2 control-label" style="width: 35%;float: left;">代领人姓名:</label>
            <div class="col-sm-10" style="width: 65%;float: left;">
                <input type="email" class="form-control" id="name" placeholder="代领人姓名">
            </div>
        </div>

        <div class="form-group" style="width: 100%;">
            <label for="mobile" class="col-sm-2 control-label" style="width: 35%;float: left;">代领人手机号:</label>
            <div class="col-sm-10" style="width: 65%;float: left;">
                <input type="password" class="form-control" id="mobile" placeholder="代领人手机号">
            </div>
        </div>

        <div class="form-group" style="width: 100%;">
            <label for="address" class="col-sm-2 control-label" style="width: 35%;float: left;">箱子地址:</label>
            <div class="col-sm-10" style="width: 65%;float: left;">
                <input type="email" class="form-control" id="address" placeholder="箱子地址">
            </div>
        </div>
        <div class="form-group" style="width: 100%;">
            <label for="boxNo" class="col-sm-2 control-label" style="width: 35%;float: left;">箱子的号码:</label>
            <div class="col-sm-10" style="width: 65%;float: left;">
                <input type="email" class="form-control" id="boxNo" placeholder="箱子的号码">
            </div>
        </div>

        <div class="form-group" style="width: 100%;">
            <label for="beginTime" class="col-sm-2 control-label" style="width: 35%;float: left;">代领开始时间:</label>
            <div class="col-sm-10" style="width: 65%;float: left;">
                <input type="email" class="form-control" id="beginTime" placeholder="可代领的开始时间">
            </div>
        </div>

        <div class="form-group" style="width: 100%;">
            <label for="endTime" class="col-sm-2 control-label" style="width: 35%;float: left;">代领结束时间:</label>
            <div class="col-sm-10" style="width: 65%;float: left;">
                <input type="email" class="form-control" id="endTime" placeholder="可代领的结束时间">
            </div>
        </div>


    </form>
    <div class="form-group" style="width: 100%;">
        <label for="mobile" class="col-sm-2 control-label" style="width: 35%;float: left;"></label>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default" onclick="submitForm();">提交</button>
        </div>
    </div>
</div>

<div class="banner" style="position:fixed;left:0;bottom:0;width: 100%;opacity:0.8;">

</div>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    function submitForm(){
        easyDialog.open({
            container: {
                header: '<div style="font-size:15px;">操作提示</div>',
                content: '<div style="font-size:15px;">该功能暂不对外开放</div>',
                yesFn: function(){
                    easyDialog.close();
                },
                noFn: false
            }
        });
    }
</script>
</html>