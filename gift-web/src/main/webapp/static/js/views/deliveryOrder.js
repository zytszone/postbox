/**
 * Created by Administrator on 2017/3/22.
 */
var deliveryRequest = new Vue({
    el: '#deliveryRequest',
    data:{
        product:{
            commodityContractId:1,
            type:'普洱',
            img:staticPath+'/static/images/product-img1.jpg',
            name:'中茶XX大树',
            number:375,
            package:2,
            unit:'克',
            money:780.00,
            expressFee:20.00,
            deduction:488,
            cash:715.52
        },
        hasAddress:false,
        username:'代用名',
        telNumber:'18888888888',
        proviceFirstStageName:'江苏省',
        addressCitySecondStageName:'南京市',
        addressCountiesThirdStageName:'建邺区',
        addressDetailInfo:'奥体中心文体创业中心'
    },
    beforeMount:function () {
        this.getAddress();
        this.getGift();
    },
    computed:{
        totalMoney:function () {
            return (Number(this.product.cash)+Number(this.product.expressFee)).toFixed(2);
        },
        address:function () {
            return this.proviceFirstStageName+this.addressCitySecondStageName+this.addressCountiesThirdStageName+this.addressDetailInfo;
        }
    },
    methods:{
        getImg:function(iconUrl){
            return basePath +"static/images/view/"+iconUrl;
        },
        getAddress:function () {
            var that= this;
            $.ajax({
                url: basePath + "address/queryUserDeliveryInfoList",
                type: "POST",
                data: '',
                dataType: "json",
                success: function (rsp) {
                    if (rsp.code == 0) {
                        var data = rsp.body;
                        var hasDefault = false;
                        if(data.length>0){
                            for (var i = 0; i < data.length; i++) {
                                if(data[i].isDefault =="true" || data[i].isDefault ==true){
                                    that.userDeliveryInfoId= data[i].userDeliveryInfoId;
                                    that.username = data[i].username;
                                    that.telNumber = data[i].telNumber;
                                    that.proviceFirstStageName=data[i].proviceFirstStageName;
                                    that.addressCitySecondStageName = data[i].addressCitySecondStageName;
                                    that.addressCountiesThirdStageName = data[i].addressCountiesThirdStageName;
                                    that.addressDetailInfo = data[i].addressDetailInfo;
                                    hasDefault = true;
                                }
                            }
                            if(!hasDefault){
                                that.userDeliveryInfoId= data[0].userDeliveryInfoId;
                                that.username = data[0].username;
                                that.telNumber = data[0].telNumber;
                                that.proviceFirstStageName=data[0].proviceFirstStageName;
                                that.addressCitySecondStageName = data[0].addressCitySecondStageName;
                                that.addressCountiesThirdStageName = data[0].addressCountiesThirdStageName;
                                that.addressDetailInfo = data[0].addressDetailInfo;
                            }
                            that.hasAddress = true;
                        }else{
                            that.userDeliveryInfoId= 0;
                            that.username = '未添加';
                            that.telNumber = '未添加';
                            that.proviceFirstStageName='';
                            that.addressCitySecondStageName = '';
                            that.addressCountiesThirdStageName = '';
                            that.addressDetailInfo = '';
                            that.hasAddress = false;
                        }
                    }
                }
            });
        },
        getGift:function () {
            var that = this;
            $.ajax({
                url: basePath + "gift/getGiftAccountData",
                type: "GET",
                data: '',
                dataType: "json",
                success: function (rsp) {
                    if(rsp.code == 0) {
                        var data = rsp.body;
                        var product = null;
                        for(var i=0;i<data.length;i++){
                            if(getQueryString('commodityContractId') == data[i].commodityContractId){
                                product = data[i];
                                break;
                            }
                        }
                        var standardUnitMultiple = product.standardUnitMultiple;
                        var deduct = (Number(getQueryString('takeNumber'))*standardUnitMultiple-product.holdCommodityQuantity)>0?product.holdCommodityQuantity:(Number(getQueryString('takeNumber'))*standardUnitMultiple);
                        var mon = (product.lastUnitPrice*Number(getQueryString('takeNumber'))*standardUnitMultiple/100).toFixed(2);
                        var deductionMoney = (product.lastUnitPrice*deduct/100).toFixed(2);
                        that.product = {
                            commodityContractId:product.commodityContractId,
                            type:product.subjectMatter,
                            typeName:product.typeName,
                            package:getQueryString('takeNumber'),
                            img:that.getImg(product.iconUrl),
                            name:product.commodityName,
                            standardUnitName:product.standardUnitName,
                            number:standardUnitMultiple,
                            unit:product.unit,
                            money:mon,
                            deduction:deduct,
                            expressFee:0.00,
                            cash:(mon-deductionMoney).toFixed(2)
                        }
                    }
                }
            });
        },
        pay:function () {
            var that = this;
            if(that.hasAddress){
                $.ajax({
                    url: basePath+'test/pay/produceOrder',
                    type: "POST",
                    data: {
                        commodityContractId:that.product.commodityContractId,
                        applyCommodityStandardUnitQuantity:that.product.package,
                        addresseeName:that.username,
                        addresseeMobilePhone:that.telNumber,
                        proviceFirstStageName:that.proviceFirstStageName,
                        addressCitySecondStageName:that.addressCitySecondStageName,
                        addressCountiesThirdStageName:that.addressCountiesThirdStageName,
                        addressDetailInfo:that.addressDetailInfo
                    },
                    dataType: "json",
                    success: function (rsp) {
                        var body = rsp.body;
                        if(rsp.code==0){
                            if(null == body){
                                dialogComponent.parentTipDialog = {
                                    show: true,
                                    header: '支付信息',
                                    body: '支付完成！',
                                    btnText:'确定'
                                };
                                dialogComponent.enterBtn = function () {
                                    window.location.href = basePath+'gift/index';
                                }
                            }else{
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
                                                        window.location.href = basePath+'gift/deliveryDetails?deliveryDetailsId='+body.deliveryDetailsId;
                                                    }else if(bizType=='BUY_COMMODITY'){
                                                        window.location.href = basePath+'gift/sellInDetails?deliveryDetailsId='+body.deliveryDetailsId;
                                                    }

                                                }
                                                dialogComponent.rightBtn = function () {
                                                    window.location.href = basePath+'gift/index';
                                                }
                                            }
                                            else if(res.err_msg == "get_brand_wcpay_request:cancel") {
                                                //TODO 支付过程中取消，强制浏览器跳转至订单详情页，页面中需包含 “取消订单”， “支付”按钮
                                                var bizType = getQueryString('bizType');
                                                window.location.href = basePath+'gift/payOrder?deliveryDetailsId='+body.deliveryDetailsId+'&bizType='+bizType;
                                            }
                                            else if(res.err_msg == "get_brand_wcpay_request:fail") {
                                                //TODO 支付失败，强制浏览器跳转至订单详情页，页面中需包含 “取消订单”， “支付”按钮
                                                var bizType = getQueryString('bizType');
                                                window.location.href = basePath+'gift/payOrder?deliveryDetailsId='+body.deliveryDetailsId+'&bizType='+bizType;
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
                        }else{
                            dialogComponent.parentModalDialog = {
                                show: true,
                                header: '提货失败',
                                body: body,
                                leftBtnText: '查看详情',
                                rightBtnText: '返回首页'
                            };
                            dialogComponent.leftBtn=function () {
                                window.location.href = basePath+'/tradeDetail/transactionDetails';
                            }
                            dialogComponent.rightBtn=function () {
                                window.location.href = basePath+'/gift/index';
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
            }else{
                dialogComponent.parentTipDialog = {
                    show:true,
                    header: '消息弹框',
                    body: '还没有收货地址，去设置',
                    btnText:'确定'
                }
                dialogComponent.enterBtn = function () {
                    window.location.href = basePath+'address/createAddress';
                }
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
var toastComponent = new Vue({
    el: '#toastComponent',
    data: {
        parentToastMessage: {
            show: false,
            body: '注意在 JavaScript 中对象和数组是引用类型，指向同一个内存空间，如果 prop 是一个对象或数组，在子组件内部改变它会影响父组件的状态'
        }
    }
});
