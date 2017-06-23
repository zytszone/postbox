<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>帮助中心</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <style>
        .banner{
            height:100px;
            background-image: url("${ctx}/static/images/banner.jpg");
        }
        .show_grid{
            border:1px solid rgba(81,61,124,0.2);
        }
    </style>
</head>
<body>
<div class="banner">

</div>
<div class="container-fluid" style="width:90%">
    <div class="row">
        <div class="row-fluid" style="text-align:left;margin-bottom:12px;font-size: 18px;margin-top:12px;font-weight: bold;">
            帮助中心
        </div>
    </div>
    <div class="row show_grid">
        <div class="col-xs-12">
            尊敬的用户：<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            e元柜可24小时自助取件，解决用户上班、外出情况下无人收件导致收件不及时，甚至快件丢失等问题。通过e元柜，您也可以让您的朋友、家人帮您取件，而无需您亲自领取。<br/>
            <br/>
            使用方法：<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            快递公司将快件投递进e元柜后，系统将锁定该快递柜，您可以选择方便合适的时间，通过扫描柜子外面的二维码，获取到开柜密码后，正确输入即可打开柜子，取出您的快递。<br/>
            <br/>
            具体步骤如下：<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            1、确认您快递所在的箱子<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            2、打开您的手机微信，进入e元柜微信公众号<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            3、点击扫码，当扫码正常打开后，将摄像头对准e元柜中的二维码<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            4、等待片刻，将服务器返回的开箱密码输入后，即可开箱<br/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            5、取出快递，关闭箱子
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    $('.boxList').on('click', 'tr', function() {
        var $input=$(this).find('input[name="boxInfoId"]');
        $('.boxDetail').hide();
        $('#detail_'+$input.val()).show();
    });
</script>
</html>