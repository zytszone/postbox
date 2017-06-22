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
<form class="form-inline">
    <div class="form-group">
        <input type="email" class="form-control" id="photo" placeholder="请选择上传的照片">
    </div>
</form>
<button type="submit" class="btn btn-default" onclick="uploadPhoto();">上传照片</button>
</body>
<script type="text/javascript" src="${ctx}/static/js/views/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/plugins/easydialog/easydialog.min.js"></script>
<script type="text/javascript">
    if(isWeixin()) {
        wx.ready(function () {
            wx.chooseImage({
                count: 1, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                }
            });
        });

        function uploadPhoto(){
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
    }

</script>
</html>