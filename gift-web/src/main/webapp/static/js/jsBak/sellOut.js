/**
 * Created by Administrator on 2017/3/22.
 */
var sellOut = new Vue({
    el: '#sellOut',
    data:{
        product:{
            type:'普洱',
            name:'中茶XX大树',
            number:256,
            unit:'克',
            current:2.5,
            feePercent:'22.22%',
            time:120
        },
        inputNumber:0
    },
    computed:{
        packetNumber:function () {
            var num = this.inputNumber == ''? 0 : this.inputNumber;
            if(Number(num)>Number(this.product.number)){
                this.inputNumber = this.product.number;
                num = this.product.number;
            }
            return num;
        },
        packetTotal:function () {
            return (this.totalMoney-this.serviceFee).toFixed(2);
        },
        serviceFee:function () {
            var num = this.totalMoney*Number(this.product.feePercent.split('%')[0])/100;
            return num.toFixed(2);
        },
        totalMoney:function () {
            return (this.product.current*this.packetNumber).toFixed(2);
        },
        arrivalTime:function () {
            return this.updateTime();
        }
    },
    methods:{
        updateTime:function () {
            var date = new Date();
            var arrival = date.getTime()+this.product.time*60*1000;
            var arrivalDate = new Date(arrival);
            return arrivalDate.format('hh:mm');
        },
        changeProduct:function () {
            $('.select-tab').toggleClass('show');
            if($('.select-tab').hasClass('show')){
                selectListComponent.show();
            }else{
                selectListComponent.hide();
            }
        },
        focusInput:function () {
            $('#sellMax').hide();
        },
        sellPacket:function () {
            $.ajax({
                url: '',
                type: "POST",
                data: {"giftCount": 10, "giftNum": 15, "giftType": 1, "commodityId": 1, "greeting": "测试！！！"},
                dataType: "json",
                success: function (r) {
                    window.location.href = basePath+'paymentPassword';
                }
            });
            window.location.href = basePath+'gift/paymentPassword';
        }
    }
});
sellOut.inputNumber = sellOut.product.number;
setInterval(function () {
    sellOut.updateTime();
},60000);

var selectListComponent = new Vue({
    el:'#selectListComponent',
    data:{
        parentSelectList:{
            top:'100%',
            height:'100%',
            header: '请选择品种',
            bodyHeight:'100%',
            lists: [
                {type:'普洱',name:'中茶XX普洱'},
                {type:'普洱',name:'中茶XX普洱A'},
                {type:'普洱',name:'中茶XX普洱B'},
                {type:'普洱',name:'中茶XX普洱C'},
                {type:'普洱',name:'中茶XX普洱D'},
                {type:'普洱',name:'中茶XX普洱E'},
                {type:'普洱',name:'中茶XX普洱F'},
                {type:'普洱',name:'中茶XX普洱G'},
                {type:'普洱',name:'中茶XX普洱H'},
                {type:'普洱',name:'中茶XX普洱I'},
                {type:'普洱',name:'中茶XX普洱J'},
                {type:'普洱',name:'中茶XX普洱K'}
            ],
            selectIndex:0,
            activeName:'中茶XX普洱'
        },
    },
    methods:{
        show:function () {
            var top = $('.select-tab').offset().top+$('.select-tab').outerHeight();
            this.parentSelectList.top = top+'px';
            var height = $(window).height() - top;
            this.parentSelectList.height = height+'px';
            var bodyHeight = height-$('.list-header').outerHeight();
            this.parentSelectList.bodyHeight = bodyHeight+'px';
        },
        hide:function () {
            this.parentSelectList.top = '100%';
        },
        getSelected:function () {
            var list = this.parentSelectList.lists;
            sellOut.product.type = list[this.parentSelectList.selectIndex].type;
            sellOut.product.name = this.parentSelectList.activeName;
            $('.select-tab').removeClass('show');
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
