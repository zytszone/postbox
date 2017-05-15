/**
 * Created by Administrator on 2017/3/22.
 */
var deliveryDetails = new Vue({
    el: '#deliveryDetails',
    data:{
        deliveDetailInfo: {
            type:'普洱',
            timeStart:'2017.04.01 11:11',
            timeMid:'2017.04.01 11:11',
            timeEnd:'2017.04.08 11:11',
            name:'中茶XX树',
            unit:'克',
            rule:'xx克/饼',
            number:6,
            kouNumber:'xx',
            kouMoney:'xx',
            money:1000.22+'元/饼',
            step:1,
            cosh:'xx元',
            fee:'xx元',
            total:'xx元',
            state:'已配送',
            orderId:'612312637186',
            company:'xx快递',
            logisticsId:'xxxxxxxxxx',
            commodityContractId:1,
            commodityType:'普洱',
            img:staticPath+'/static/images/product-img1.jpg',
            commodityName:'中茶XX大树',
            quantity:0,
            standardUnitMultiple:1,
            unitPrice:0,
            quantityType:1,
            countdown:'',
            expressFee:20.00,
            deduction:488,
            cash:10
        },
        isPay:true
    },
    beforeMount:function () {
        var that = this;
        var deliveryDetailsId = getQueryString('deliveryDetailsId');
        $.ajax({
            url: basePath + "pickUp/queryDeliveryDetailInfo",
            type: "POST",
            data: {'deliveryDetailsId':deliveryDetailsId},
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0){
                    var data = rsp.body;
                    var state = data.deliveryOrder.status;
                    if(state == 'NOPAY'){
                        state = '待支付';
                        that.isPay = false;
                    }else{
                        state = '提货成功';
                        that.isPay = true;
                    }
                    that.deliveDetailInfo={
                        type:data.commodityVo.subjectMatter,
                        timeStart:(new Date(data.deliveryOrder.createTime)).format('yyyy-MM-dd'),
                        timeMid:'xxx',
                        timeEnd:'xxxx',
                        name:data.commodityVo.commodityName,
                        unit:data.commodityVo.unit,
                        rule:''+data.commodityVo.standardUnitMultiple+data.commodityVo.unit+'/'+data.commodityVo.standardUnitName,
                        number:data.deliveryOrder.applyCommodityUnitQuantity,
                        kouNumber:data.deliveryOrder.actConsumeCommUnitQuantity,
                        kouMoney:(data.deliveryOrder.actConsumeCommUnitQuantity*data.commodityVo.unitPrice/100).toFixed(2),
                        money:(data.deliveryOrder.applyCommodityUnitQuantity*data.commodityVo.unitPrice/100).toFixed(2),
                        step:1,
                        cosh:(data.deliveryOrder.addOnCommodityUnitQuantity*data.commodityVo.unitPrice/100).toFixed(2),
                        fee:'0.00元',
                        total:(data.deliveryOrder.actualPrice/100).toFixed(2),
                        orderId:data.deliveryOrder.deliveryOrderId,
                        company:'xx快递',
                        logisticsId:'xxxxxxxxxx',
                        commodityContractId:data.commodityVo.commodityContractId,
                        img:staticPath+'/static/images/product-img1.jpg',
                        commodityType:data.commodityVo.subjectMatter,
                        commodityName:data.commodityVo.commodityName,
                        quantity:data.deliveryOrder.commodityUnitQuantity,
                        standardUnitMultiple:data.commodityVo.standardUnitMultiple,
                        unitPrice:data.commodityVo.unitPrice,
                        quantityType:data.commodityVo.standardUnitName,
                        time:data.deliveryOrder.orderTime,
                        logicId:data.deliveryOrder.tradeOrderId,
                        countdown:'',
                        expressFee:data.deliveryOrder.feePrice.toFixed(2),
                        deduction:data.deliveryOrder.couponPrice.toFixed(2),
                        state:state,
                        cash:(data.deliveryOrder.actualPrice/100).toFixed(2)
                    }
                    if(state == 'NOPAY') {
                        window.clearTime = setInterval(function () {
                            that.deliveDetailInfo.countdown = GetRTime(data.endTime);
                            if(that.deliveDetailInfo.countdown==''){
                                clearInterval(window.clearTime);
                            }
                        }, 1000);
                    }
                }
            }
        });
    },
    computed:{
        totalMoney:function () {
            var quantityType = this.deliveDetailInfo.quantityType;
            var unitNumber = (quantityType==1)?1:this.deliveDetailInfo.standardUnitMultiple;
            var money = this.deliveDetailInfo.unitPrice*this.deliveDetailInfo.quantity*unitNumber/100;
            return money.toFixed(2);
        }
    },
    methods:{
        //取消购买
        deleteOrder:function () {
            var tradeOrderId = getQueryString('tradeOrderId');
            var that = this;
            $.ajax({
                url: basePath + "test/pay/cancelBuyCommodityInfo",
                type: "POST",
                data: {tradeOrderId:tradeOrderId},
                dataType: "json",
                success: function (rsp) {
                    if(rsp.code==0){
                        alert("取消成功！");
                    }
                }
            })
        },
        pay:function () {
            var that = this;
            $.ajax({
                url: basePath + "buyCommodity/deliveDetailInfo",
                type: "POST",
                data: {
                    commodityId:that.deliveDetailInfo.commodityContractId,
                    quantity:that.deliveDetailInfo.quantity,
                    quantityType:that.deliveDetailInfo.quantityType
                },
                dataType: "json",
                success: function (rsp) {
                    if(rsp.code==0){
                        var body = response.body;
                        //唤起H5支付API
                        function onBridgeReady(appId, timeStamp, nonceStr, package, signType, paySign){
                            WeixinJSBridge.invoke(
                                'getBrandWCPayRequest', {
                                    "appId": appId,     //公众号名称，由商户传入
                                    "timeStamp": timeStamp,         //时间戳，自1970年以来的秒数
                                    "nonceStr":nonceStr, //随机串
                                    "package": package,
                                    "signType":signType,         //微信签名方式：
                                    "paySign":paySign //微信签名
                                },
                                function(res){
                                    if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                                        // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                                        //TODO 支付成功, 弹出订单支付成功框，显示去首页和去订单详情两个按钮
                                        dialogComponent.parentModalDialog = {
                                            show:true,
                                            header: '支付消息',
                                            body: '支付成功！',
                                            leftBtnText: '查看订单',
                                            rightBtnText: '去首页'
                                        }
                                        dialogComponent.leftBtn = function () {
                                            var bizType = getQueryString('bizType');
                                            if(bizType=='DELIVERY'){
                                                window.location.href = basePath+'gift/deliveryDetails?tradeOrderId='+body.tradeOrderId;
                                            }else if(bizType=='BUY_COMMODITY'){
                                                window.location.href = basePath+'gift/sellInDetails?tradeOrderId='+body.tradeOrderId;
                                            }

                                        }
                                        dialogComponent.rightBtn = function () {
                                            window.location.href = basePath+'gift/index';
                                        }
                                    }
                                    else if(res.err_msg == "get_brand_wcpay_request:cancel") {
                                        //TODO 支付过程中取消，强制浏览器跳转至订单详情页，页面中需包含 “取消订单”， “支付”按钮
                                        var bizType = getQueryString('bizType');
                                        window.location.href = basePath+'gift/payOrder?tradeOrderId='+body.tradeOrderId+'&bizType='+bizType;
                                    }
                                    else if(res.err_msg == "get_brand_wcpay_request:fail") {
                                        //TODO 支付失败，强制浏览器跳转至订单详情页，页面中需包含 “取消订单”， “支付”按钮
                                        var bizType = getQueryString('bizType');
                                        window.location.href = basePath+'gift/payOrder?tradeOrderId='+body.tradeOrderId+'&bizType='+bizType;
                                    }
                                }
                            );
                        }

                        if (typeof WeixinJSBridge == "undefined"){
                            if( document.addEventListener ){
                                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                            }else if (document.attachEvent){
                                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                            }
                        }else{
                            onBridgeReady(body.appId, body.timeStamp, body.nonceStr, body.package, body.signType, body.paySign);
                        }
                    }
                },
                error:function (err) {
                    toastComponent.parentToastMessage = {
                        show:true,
                        body:err
                    }
                }
            });
        }
    }
});


