/**
 * Created by Administrator on 2017/3/22.
 */
var takeGoods = new Vue({
    el: '#takeGoods',
    data:{
        product:{
            commodityContractId:1,
            commodityType:'普洱',
            commodityName:'中茶XX大树',
            holdCommodityQuantity:477,
            unit:'克',
            standardUnitMultiple:357,
            lastUnitPrice:10.00
        },
        activeIndex:0,
        selectList:[],
        inputNumber:'',
        needMore:false
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
                    takeGoods.getCommodity();
                }
            }
        });
    },
    computed:{
        moreNumber:function () {
            var num = this.inputNumber==''?0:this.inputNumber;
            var max = num*this.product.standardUnitMultiple;
            if(max>this.product.holdCommodityQuantity){
                this.needMore = true;
                return num*this.product.standardUnitMultiple-this.product.holdCommodityQuantity;
            }
            else{
                this.needMore = false;
                return 0;
            }
        },
        moreMoney:function () {
            var num = this.inputNumber==''?0:this.inputNumber;
            if(this.needMore){
                return (this.inputNumber*this.product.standardUnitMultiple*this.product.lastUnitPrice/100).toFixed(2);
            }
            else{
                return 0;
            }
        }
    },
    methods:{
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
        changeValue:function () {
            this.getCommodity();
        },
        sellPacket:function () {
            if(this.inputNumber==0){
                dialogComponent.parentTipDialog = {
                    show:true,
                    body:'提货数量不能为0，请重新设置提货数量',
                    btnText:'确定'
                }
                return;
            }
            window.location.href = basePath+'pickUp/deliveryOrder?takeNumber='+this.inputNumber+'&commodityContractId='+this.product.commodityContractId;
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
