/**
 * Created by Administrator on 2017/3/22.
 */
var setSecurities = new Vue({
    el: '#setSecurities',
    data:{
        product:{
            type:'普洱',
            name:'中茶XX大树',
            number:256,
            unit:'克',
            price:182.5,
            commodityId:1
        },
        packetNumber:0,
        totalUnit:0,
        totalPacket:0,
        packetDesc:'',
        totalMoney:0.00
    },
    computed:{
    },
    beforeMount:function () {
        this.getLists();
    },
    methods:{
        getLists:function () {
            // $.ajax({
            //     url:'',
            //     type:'GET',
            //     data:'',
            //     dataType:'json',
            //     success:function(response){
            //         selectListComponent.lists = response.body;
            //     },
            //     error:function (err) {
            //         toastComponent.parentToastMessage = {
            //             show:true,
            //             body:err
            //         }
            //     }
            // });
        },
        changeProduct:function () {
            $('.select-tab').toggleClass('show');
            if($('.select-tab').hasClass('show')){
                selectListComponent.show();
            }else{
                selectListComponent.hide();
            }
        },
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
            // $.ajax({
            //     url: basePath + "gift/donateGift",
            //     type: "POST",
            //     data: {"giftCount": that.packetNumber, "giftNum": that.packetTotal, "giftType": 0, "commodityId": 1, "greeting": "测试！！！"},
            //     dataType: "json",
            //     success: function (rsp) {
            //         if(rsp.msgCode) {
            //             //包裹红包成功，跳转至准备分享的页面
            //             window.location.href = basePath+'gift/sendPacket/' + rsp.commodityGiftInfoId + "/" + rsp.code;
            //         }
            //     }
            // });
        }
    }
});
setInterval(function () {
    setSecurities.updatePrice();
},50000);
$('.box-wrap').on('input','.box-input input',function () {
    updateData();
});
function updateData() {
    var unit = $('input[name="unit"]');
    var number = $('input[name="number"]');
    var totalUnit=0;
    var totalPacket = 0;
    for(var i=0;i<unit.length;i++){
        var val =Number(unit.eq(i).val())>0?Number(unit.eq(i).val()):0;
        totalUnit+=val;console.log(val)
    }
    for(var j=0;j<number.length;j++){
        var val =Number(number.eq(j).val())>0?Number(number.eq(j).val()):0;
        totalPacket+=val;
    }
    setSecurities.totalUnit = totalUnit;
    setSecurities.totalPacket = totalPacket;
    setSecurities.totalMoney = (totalUnit*setSecurities.product.price).toFixed(2);
}
$('.box-wrap').on('click','.add',function () {
    addBox(this);
});
$('.box-wrap').on('click','.delete',function () {
    deleteBox(this);
});
function addBox(that) {
    $(that).removeClass('add').addClass('delete').html('-');
    var unit = setSecurities.product.unit;
    $('.box-wrap').append('<div class="set-box clearfix"><div class="box-input"><input type="tel" name="unit"><span>'+unit+'</span></div><div class="box-input"><input type="tel" name="number"><span>个</span></div><button class="add">+</button></div>');
}
function deleteBox(that) {
    $(that).parent().remove();
    updateData();
}
var selectListComponent = new Vue({
    el:'#selectListComponent',
    data:{
        parentSelectList:{
            top:'100%',
            height:'100%',
            header: '请选择品种',
            bodyHeight:'100%',
            lists: [
                {commodityId:1,type:'普洱',name:'中茶XX普洱'},
                {commodityId:2,type:'普洱',name:'中茶XX普洱A'},
                {commodityId:3,type:'普洱',name:'中茶XX普洱B'},
                {commodityId:4,type:'普洱',name:'中茶XX普洱C'},
                {commodityId:5,type:'普洱',name:'中茶XX普洱D'},
                {commodityId:6,type:'普洱',name:'中茶XX普洱E'},
                {commodityId:7,type:'普洱',name:'中茶XX普洱F'},
                {commodityId:8,type:'普洱',name:'中茶XX普洱G'},
                {commodityId:9,type:'普洱',name:'中茶XX普洱H'},
                {commodityId:10,type:'普洱',name:'中茶XX普洱I'},
                {commodityId:11,type:'普洱',name:'中茶XX普洱J'},
                {commodityId:12,type:'普洱',name:'中茶XX普洱K'}
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
            setSecurities.product.type = list[this.parentSelectList.selectIndex].type;
            setSecurities.product.name = this.parentSelectList.activeName;
            $('.select-tab').removeClass('show');
            setSecurities.updatePrice();
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
