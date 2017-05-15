/**
 * Created by Administrator on 2017/3/22.
 */
var accountDetails = new Vue({
    el:'#accountDetails',
    data:{
        unit:'毫升',
        url:basePath+'gift/commodityDetails?commodityId=',
        giftList:[]
    },
    beforeMount:function () {
        var that = this;
        $.ajax({
            url: basePath + "gift/getGiftAccountData",
            type: "GET",
            data: '',
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0) {
                    var data = rsp.body;
                    var arr = [];
                    for (var i = 0; i < data.length; i++) {
                        var mon = Number(data[i].holdCommodityQuantity) * Number(data[i].lastUnitPrice) / 100;
                        var obj = {
                            commodityId: data[i].commodityContractId,
                            type: data[i].subjectMatter,
                            name: data[i].commodityName,
                            number: data[i].holdCommodityQuantity,
                            unit: data[i].unit,
                            money: mon.toFixed(2)
                        }
                        arr.push(obj);
                    }
                    that.giftList = arr;
                    // if(arr.length>5){
                    //     scroll();
                    // }
                }
            }
        });
    },
    computed:{
        // totalNumber:function () {
        //     var list = this.giftList;
        //     var total = 0;
        //     for(var i=0;i<list.length;i++){
        //         total += parseFloat(list[i].number);
        //     }
        //     return total.toFixed(0);
        // },
        totalMoney:function () {
            var list = this.giftList;
            var total = 0;
            for(var i=0;i<list.length;i++){
                total += parseFloat(list[i].money);
            }
            return total.toFixed(2);
        }
    },
    methods:{

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
            window.location.href = basePath+'setPassword';
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

function scroll() {
    var windowH = $(window).height();
    var footer = $('.list-footer').outerHeight();
    var wrapperH = windowH-footer;
    $('#wrapper').css('height',wrapperH);
    $('#scrollList').on('scroll',function () {
        var scrollHeight = $('#scrollList ul').outerHeight()-50;
        var scrollTop = $('#scrollList').scrollTop();
        var fixHeight = $('.fixed').outerHeight();
        var scroll = wrapperH+scrollTop-fixHeight;
        if(scroll>scrollHeight){
            accountDetails.giftList.push({commodityId:1,type:'普洱',name:'中茶X树2016',number:22256,unit:'毫升',money:35.65,current:275.47,cost:275.47,float:1.47});
        }
    });
}





