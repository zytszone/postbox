/**
 * Created by Administrator on 2017/3/22.
 */
var sellInOrder = new Vue({
    el: '#sellInOrder',
    data:{
        commodity:{
            commodityContractId:1,
            commodityType:'普洱',
            img:staticPath+'/static/images/product-img0.jpg',
            commodityName:'中茶XX大树',
            quantity:0,
            standardUnitMultiple:1,
            unit:'',
            unitPrice:0,
            quantityType:1
        }
    },
    computed:{
        totalMoney:function () {
            var quantityType = this.commodity.quantityType;
            var unitNumber = quantityType==1?1:this.commodity.standardUnitMultiple;
            var money = this.commodity.unitPrice*this.commodity.quantity*unitNumber/100;
            return money.toFixed(2);
        }
    },
    beforeMount:function () {
        var that = this;
        var commodityId = getQueryString('commodityId');
        $.ajax({
            url: basePath + "buyCommodity/queryCommodityDetail",
            type: "POST",
            data: {'commodityId':commodityId},
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0){
                    var data = rsp.body;
                    var quantityType = getQueryString('quantityType');
                    var quantity = getQueryString('quantity');
                    that.commodity={
                        commodityContractId:data.commodityContractId,
                        img:staticPath+'/static/images/product-img0.jpg',
                        commodityType:data.subjectMatter,
                        commodityName:data.commodityName,
                        quantity:quantity,
                        unit:quantityType==1?data.unit:data.standardUnitName,
                        standardUnitMultiple:data.standardUnitMultiple,
                        unitPrice:data.unitPrice,
                        quantityType:quantityType
                    }
                }
            }
        });
    },
    methods:{
        pay:function () {
            var that = this;
            $.ajax({
                url: basePath + "test/pay/buyCommodity",
                type: "POST",
                data: {
                    commodityId:that.commodity.commodityContractId,
                    quantity:that.commodity.quantity,
                    quantityType:that.commodity.quantityType
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
                                    alert(JSON.stringify(res));
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
                }
            });
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
            body: 'asfasf'
        }
    },
    computed: {},
    methods: {
        leftBtn: function () {
            this.parentModalDialog.show = false;
        },
        rightBtn: function () {
            this.parentModalDialog.show = false;
            alert('ok')
        },
        enterBtn:function () {
            this.parentTipDialog.show = false
        }
    }
});
var toastComponent = new Vue({
    el: '#toastComponent',
    data: {
        parentToastMessage: {
            show: false,
            body: '注意在 JavaScript 中对象和数组是引用类型，指向同一个内存空间，如果 prop 是一个对象或数组，在子组件内部改变它会影响父组件的状态'
        }
    }
});
