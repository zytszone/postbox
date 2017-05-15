/**
 * Created by Administrator on 2017/3/22.
 */
var setPacket = new Vue({
    el: '#setPacket',
    data:{
        product:{
            commodityContractId:1,
            commodityType:'普洱',
            commodityName:'中茶XX大树',
            holdCommodityQuantity:477,
            unit:'克',
            standardUnitMultiple:357,
            lastUnitPrice:1000
        },
        activeIndex:0,
        selectList:[],
        packetNumber:'',
        packetTotal:'',
        packetDesc:'',
        singleTotal:'',
        giftType:1
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
                    that.selectList = data;
                    that.activeIndex = data[0].commodityContractId;
                    setPacket.getCommodity();
                }
            }
        });
    },
    computed:{
        totalNumber:function () {
            if(this.giftType==1){
                return Number(this.packetTotal)>0?this.packetTotal:0;
            }else{
                var total = this.singleTotal*this.packetNumber;
                return Number(total)>0?total:0;
            }
        },
        totalMoney:function () {
            if(this.giftType==1){
                return (this.packetTotal*this.product.lastUnitPrice/100).toFixed(2);
            }else{
                return (this.singleTotal*this.packetNumber*this.product.lastUnitPrice/100).toFixed(2);
            }
        }
    },
    methods:{
        changeValue:function () {
            this.getCommodity();
        },
        getCommodity:function () {
            var list = this.selectList;
            var index = this.activeIndex;
            for(var i=0;i<list.length;i++){
                if(index==list[i].commodityContractId){
                    this.product= {
                        commodityContractId:list[i].commodityContractId,
                        commodityType: list[i].subjectMatter,
                        commodityName: list[i].commodityName,
                        holdCommodityQuantity: list[i].holdCommodityQuantity,
                        unit: list[i].unit,
                        standardUnitMultiple: list[i].standardUnitMultiple,
                        lastUnitPrice: list[i].lastUnitPrice,
                        standardUnitName:list[i].standardUnitName
                    }
                }
                break;
            }
        },
        changeType:function () {
            if(this.giftType==1){
                this.giftType = 2;
                $('body').addClass('change-bg');
            }else{
                this.giftType = 1;
                $('body').removeClass('change-bg');
            }
        },
        sendPacket:function () {
            var that = this;
            var desc = '';
            if(that.giftType==1){
                if(Number(that.packetTotal)>Number(that.product.holdCommodityQuantity)){
                    dialogComponent.parentTipDialog={
                        show: true,
                        header: '消息弹框',
                        body: '对不起，您设置的总数量不能超过您当前持有的数量，请重新设置',
                        btnText:'确定'
                    }
                    return;
                }
                if(Number(that.packetNumber)>Number(that.packetTotal)){
                    dialogComponent.parentTipDialog={
                        show: true,
                        header: '消息弹框',
                        body: '对不起，您设置的红包个数不能超过总数量，请重新设置',
                        btnText:'确定'
                    }
                    return;
                }
                if(Number(that.packetTotal)/200>Number(that.packetNumber)){
                    dialogComponent.parentTipDialog={
                        show: true,
                        header: '消息弹框',
                        body: '对不起，当前每人可领礼包数量超过200，请重新设置',
                        btnText:'确定'
                    }
                    return;
                }
            }else{
                if((Number(that.packetNumber)*Number(that.singleTotal))>Number(that.product.holdCommodityQuantity)){
                    dialogComponent.parentTipDialog={
                        show: true,
                        header: '消息弹框',
                        body: '对不起，您设置的总数量不能超过您当前持有的数量，请重新设置',
                        btnText:'确定'
                    }
                    return;
                }
            }
            if(that.packetDesc ==''){
                desc = $('textarea').attr('placeholder');
            }
            $.ajax({
                url: basePath + "gift/donateGift",
                type: "POST",
                data: {"giftCount": that.packetNumber, "giftNum": that.giftType==1?that.packetTotal:that.singleTotal, "giftType": that.giftType, "commodityId": 1, "greeting": desc},
                dataType: "json",
                success: function (rsp) {
                    if(rsp.msgCode) {
                        //包裹红包成功，跳转至准备分享的页面
                        window.location.href = basePath+'gift/sendPacket/' + rsp.commodityGiftInfoId + "/" + rsp.code;
                    }else{
                        dialogComponent.parentTipDialog={
                            show: true,
                            header: '消息弹框',
                            body: rsp.errorMsg,
                            btnText:'确定'
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
