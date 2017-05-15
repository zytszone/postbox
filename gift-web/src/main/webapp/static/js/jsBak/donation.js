/**
 * Created by Administrator on 2017/3/22.
 */
var donation = new Vue({
    el: '#donation',
    data:{
        product:{
            type:'普洱',
            name:'中茶XX大树',
            number:256,
            unit:'克',
            price:2.5,
            commodityId:1
        },
        packetDesc:''
    },
    computed:{
        money:function () {
            return (this.product.price*this.product.number).toFixed(2);
        }
    },
    beforeMount:function () {

    },
    methods:{
        updatePrice:function () {
            var that = this;
            // $.ajax({
            //     url:'',
            //     type:'GET',
            //     data:'',
            //     dataType:'json',
            //     success:function(response){
            //         var data = response.body;
            //         that.product.price = data.price;
            //         var total = this.packetTotal == '' ? 0 : this.packetTotal;
            //         that.totalMoney = total * this.product.price;
            //     },
            //     error:function (err) {
            //         toastComponent.parentToastMessage = {
            //             show:true,
            //             body:err
            //         }
            //     }
            // });
        },
        sendPacket:function () {
            var that = this;
            $.ajax({
                url: basePath + "gift/donateGift",
                type: "POST",
                data: {"giftCount": that.packetNumber, "giftNum": that.packetTotal, "giftType": 0, "commodityId": 1, "greeting": "测试！！！"},
                dataType: "json",
                success: function (rsp) {
                    if(rsp.msgCode) {
                        //包裹红包成功，跳转至准备分享的页面
                        window.location.href = basePath+'gift/sendPacket/' + rsp.commodityGiftInfoId + "/" + rsp.code;
                    }
                }
            });
        }
    }
});
setInterval(function () {
    donation.updatePrice();
},50000);

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
