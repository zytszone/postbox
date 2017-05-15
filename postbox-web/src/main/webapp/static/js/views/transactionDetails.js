/**
 * Created by Administrator on 2017/3/22.
 */
var $statu = $('.loading-warp .text');
var transactionDetails = new Vue({
    el: '#transactionDetails',
    data: {
        unit: '克',
        url1: basePath + 'gift/setPacketDetails/',
        url2: basePath + 'gift/sellInDetails?tradeOrderId=',
        url3: basePath + 'gift/getPacketDetails/',
        url4: basePath + 'gift/deliveryDetails?deliveryDetailsId=',
        url5: basePath + 'gift/payOrder?tradeOrderId=',
        typeValue: "",
        typeList: [
            {text: '全部类型', value: ""},
            {text: '发礼包', value: 'GIVE_GIFT'},
            {text: '收礼包', value: 'ACCEPT_GIFT'},
            {text: '提货', value: 'DELIVERY'},
            {text: '买入', value: 'BUY_COMMODITY'}
        ],
        productValue: 0,
        productList: [],
        dateValue: '',
        giftList: null
    },
    computed: {},
    beforeMount: function () {
        var that = this;
        $.ajax({
            url: basePath + "gift/queryCommodityTypeList",
            type: "GET",
            data: '',
            dataType: "json",
            success: function (rsp) {
                if (rsp.code == 0) {
                    var data = rsp.body;
                    var arr = [{code: 'all', commodityTypeId: 0, name: '全部品种'}];
                    for (var i = 0; i < data.length; i++) {
                        var obj = {
                            code: data[i].code,
                            commodityTypeId: data[i].commodityTypeId,
                            name: data[i].name
                        }
                        arr.push(obj);
                    }
                    that.productList = arr;
                    that.dateValue = (new Date()).format('yyyy-MM');
                    listPage = 1;
                    that.getList(that.typeValue, that.productValue, that.dateValue, listPage);
                    var pullRefresh = $('.container').pPullRefresh({
                        $el: $('.container'),
                        $loadingEl: $('.loading-warp'),
                        sendData: function () {
                            that.getList(that.typeValue, that.productValue, that.dateValue, listPage);
                        },
                        url: '',
                        autoHide: true,
                        callbacks: {
                            pullStart: function(){
                                $statu.text('松开开始刷新');
                            }
                        }
                    });
                }
            }
        });

    },
    mounted: function () {
        //scroll();
    },
    methods: {
        changeDate: function (num) {
            this.dateValue = GetDateStr(this.dateValue, num);
            listPage = 1;
            this.getList(this.typeValue, this.productValue, this.dateValue, listPage);
        },
        changeType: function () {
            listPage = 1;
            this.getList(this.typeValue, this.productValue, this.dateValue, listPage);
        },
        getList: function (type, product, date, page) {
            var that = this;
            $.ajax({
                url: basePath + "tradeDetail/record",
                type: "POST",
                data: {'bizType': type, 'commodityTypeId': product, "date": date},
                dataType: "json",
                success: function (rsp) {
                    if (rsp.code == 0) {
                        var jsonstr = rsp.body;
                        var data = eval(jsonstr);
                        var arr = [];
                        for (var i = 0; i < data.length; i++) {
                            var obj = {
                                commodityId:data[i].commodityContractId,
                                commodityTypeId: data[i].commodityTypeId,
                                bizId:data[i].bizId,
                                bizType: data[i].bizType,
                                name: data[i].commodityName,
                                type:data[i].subjectMatter,
                                typeName:data[i].typeName,
                                number: data[i].opCommodityUnitQuantity,
                                unit: data[i].unit,
                                money: (data[i].opCommodityUnitQuantity * data[i].unitPrice/100).toFixed(2),
                                state: data[i].bizStatus,
                                date: (new Date(data[i].createTime)).format('yyyy-MM-dd hh:mm:ss')
                                // giftInfoId: 7,
                                // giftInfoCode: 'JDJhJDA1JGlNalNGcHppN2xVWDNrQzFlZExESE9xZ1lrQUM5ZGlrL2xRNzlYZmZjZEp1TWcvdWhLOS8y'
                            }
                            arr.push(obj);
                        }
                        that.giftList = arr;
                    }else {
                        toastComponent.parentToastMessage = {
                            show:true,
                            body:'数据刷新失败'
                        };
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
            rightBtnText: '去设置'
        },
        parentTipDialog: {
            show: false,
            header: '消息弹框',
            body: 'asfasf',
            btnText: '确定'
        }
    },
    computed: {},
    methods: {
        leftBtn: function () {
            this.parentModalDialog.show = false;
        },
        rightBtn: function () {
            this.parentModalDialog.show = false;
            window.location.href = basePath + 'setPassword';
        },
        enterBtn: function () {
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

//上拉加载
var listPage = 1;
function scroll() {
    var windowH = $(window).height();
    var wrapperH = windowH;
    $('#wrapper').css('height', wrapperH);
    $('#scrollList').on('scroll', function () {
        var scrollHeight = $('#scrollList ul').outerHeight() - 50;
        var scrollTop = $('#scrollList').scrollTop();
        var fixHeight = $('.fixed').outerHeight();
        var scroll = wrapperH + scrollTop - fixHeight;
        if (scroll > scrollHeight) {
            listPage++;
            transactionDetails.getList(transactionDetails.typeValue, transactionDetails.productValue, transactionDetails.dateValue, listPage);
        }
    });
}


