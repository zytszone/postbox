<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>报错修理</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link href="${ctx}/static/plugins/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/plugins/easydialog/easydialog.css" rel="stylesheet" type="text/css">
    <jsp:include page="../include/weixinDefaultShare.jsp"/>
</head>
<body>

<div class="panel panel-default" style="margin: 30% auto 0;width: 75%;">
    <div class="panel-body">
        <img id="faceImg" style="height: 300px;height: 300px;"/>
    </div>
</div>

<button type="submit" class="btn btn-default form-control" style="position:fixed;bottom: 0px;background:green;height: 50px;font-size: 20px;" onclick="uploadPhoto();">上传照片</button>
</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">

        function uploadPhoto(){
            if(isWeixin()) {
                wx.ready(function () {
                    var localIds = '';
                    wx.chooseImage({
                        count: 1, // 默认9
                        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                        success: function (res) {
                            localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                            uploadImg(localIds);
                        }
                    });
                    var uploadImg=function(localIds){
                        wx.uploadImage({
                            localId: ''+localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
                            isShowProgressTips: 1, // 默认为1，显示进度提示
                            success: function (r) {
                                    var serverId = r.serverId; // 返回图片的服务器端ID
                                    $("#faceImg").attr("src",localIds);
                            }
                        });
                    }
                });
            }
        }




</script>
</html>