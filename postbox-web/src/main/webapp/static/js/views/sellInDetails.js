/**
 * Created by Administrator on 2017/3/22.
 */
var sellInDetails = new Vue({
    el: '#sellInDetails',
    data:{
        order:{
            type:'普洱',
            typeName:'',
            timeStart:'2017.04.01 11:11',
            timeCurrent:'2017.04.07 11:11',
            name:'中茶XX树',
            price:'xx',
            unit:'克',
            number:256,
            step:2,
            state:'买入成功',
            orderId:'612312637186',
            pay:'xx',
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
        isPay:true,
        isCancel:false
    },
    computed:{
        totalMoney:function () {
            var quantityType = this.order.quantityType;
            var unitNumber = (quantityType==1)?1:this.order.standardUnitMultiple;
            var money = this.order.unitPrice*this.order.quantity*unitNumber/100;
            return money.toFixed(2);
        }
    },
    beforeMount:function () {
        var that = this;
        var tradeOrderId = getQueryString('tradeOrderId');
        $.ajax({
            url: basePath + "test/pay/tradeOrderDetail",
            type: "POST",
            data: {'tradeOrderId':tradeOrderId},
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0){
                    var data = rsp.body;
                    var state = data.tradeOrder.status;
                    if(state == 'NOPAY'){
                        state = '待付款';
                        that.isPay = false;
                    }else if(state == 'CANCELLED'){
                        //TODO
                        that.isCancel = true;
                        that.isPay = false;
                        state = '已取消';
                    }else{
                        state = '买入成功';
                        that.isPay = true;
                    }
                    that.order={
                        type:data.commodityVo.subjectMatter,
                        typeName:data.commodityVo.typeName,
                        timeStart:(new Date(data.tradeOrder.createTime)).format('yyyy-MM-dd hh:mm:ss'),
                        name:data.commodityVo.commodityName,
                        price:(data.commodityVo.unitPrice/100).toFixed(2),
                        unit:data.commodityVo.unit,
                        number:data.tradeOrder.commodityUnitQuantity,
                        step:2,
                        orderId:data.tradeOrder.tradeOrderId,
                        pay:(data.tradeOrder.actualPrice/100).toFixed(2),
                        commodityContractId:data.commodityVo.commodityContractId,
                        img:that.getImg(data.commodityVo.iconUrl),
                        commodityType:data.commodityVo.subjectMatter,
                        commodityName:data.commodityVo.commodityName,
                        quantity:data.tradeOrder.commodityUnitQuantity,
                        standardUnitMultiple:data.commodityVo.standardUnitMultiple,
                        unitPrice:data.commodityVo.unitPrice,
                        quantityType:1,
                        logicId:data.tradeOrder.tradeOrderId,
                        countdown:'',
                        expressFee:data.tradeOrder.feePrice,
                        deduction:data.tradeOrder.couponPrice,
                        state:state,
                        cash:(data.tradeOrder.actualPrice/100).toFixed(2)
                    }
                    if(data.tradeOrder.status == 'NOPAY') {
                        that.order.countdown = GetRTime(data.tradeOrder.expireTime);
                        window.clearTime = setInterval(function () {
                            that.order.countdown = GetRTime(data.tradeOrder.expireTime);
                            if(that.order.countdown==''){
                                clearInterval(window.clearTime);
                                that.deleteOrder();
                            }
                        }, 1000);
                    }
                }else{
                    dialogComponent.parentTipDialog = {
                        show: true,
                        header: '交易明细',
                        body: '查询数据失败',
                        btnText:'确定'
                    };
                }
            }
        });
    },
    methods:{
        getImg:function(iconUrl){
            return basePath +"static/images/view/"+iconUrl;
        },
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
                        that.isCancel = true;
                        that.isPay = false;
                        that.order.state = '已取消';
                        dialogComponent.parentTipDialog = {
                            show: true,
                            header: '取消操作',
                            body: '取消订单成功！',
                            btnText:'确定'
                        }
                        dialogComponent.enterBtn = function () {
                            window.location.href = basePath+'tradeDetail/transactionDetails';
                        }
                    }else{
                        dialogComponent.parentTipDialog = {
                            show: true,
                            header: '取消操作',
                            body: '取消订单失败！',
                            btnText:'确定'
                        };

                    }
                }
            })
        },
        //支付
        pay:function () {
            var that = this;
            if(that.isCancel){
                window.location.href = basePath + "gift/commodityDetails?commodityId="+ that.order.commodityContractId;
            }else{
                var tradeOrderId = getQueryString('tradeOrderId');
                $.ajax({
                    url: basePath + "test/pay/payforBuyCommodity",
                    type: "POST",
                    data: {
                        tradeOrderId:tradeOrderId,
                    },
                    dataType: "json",
                    success: function (rsp) {
                        if(rsp.code==0){
                            var body = rsp.body;
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
                        }else{
                            dialogComponent.parentTipDialog = {
                                show: true,
                                header: '付款操作',
                                body: '付款失败！',
                                btnText:'确定'
                            };
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
    }
});

var dialogComponent = new Vue({
    el: '#dialogComponent',
    data: {
        parentModalDialog: {
            show: false,
            header: '按钮弹框',
            body: '按钮弹框内容',
            leftBtnText: '取消',
            rightBtnText: '确定'
        },
        parentTipDialog: {
            show: false,
            header: '消息弹框',
            body: 'asfasf',
            btnText:'确定'
        }
    },
    computed: {},
    methods: {
        leftBtn: function () {
            this.parentModalDialog.show = false;
        },
        rightBtn: function () {
            this.parentModalDialog.show = false;
        },
        enterBtn:function () {
            this.parentTipDialog.show = false
        }
    }
});


