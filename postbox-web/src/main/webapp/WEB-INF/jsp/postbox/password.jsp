<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-title" content=""/>
    <meta name="apple-mobile-web-app-capable" content="no"/>
    <meta name="msapplication-tap-highlight" content="no">
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta content="telephone=no,email=no" name="format-detection"/>
    <meta name="format-detection" content="telephone=no,email=no"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="password.css"/>
    <script src="jquery.min.js"></script>
</head>
<body>
<!--page start-->
<div class="animate seven">
    <span></span><span></span><span></span><span></span><span></span><span></span>
</div>


<div class="link-wrap">
    <a class="link link--kukuri" href="#" data-letters="123456">123456</a>
</div>

<!--page end-->
<script>
    $(document).ready(function () {
        //第一种
        var number = '123456';
        var span = $('.animate span');
        for(var i=0;i<number.length;i++){
            span.eq(i).html(number[i]);
        }

        //第二种
        $('.link').addClass('hover');
        setTimeout(function () {
            $('.link').removeClass('hover')
        },2000);
        setInterval(function () {
            $('.link').addClass('hover');
            setTimeout(function () {
                $('.link').removeClass('hover')
            },2000);
        },4000);
    });
</script>
</body>
</html>