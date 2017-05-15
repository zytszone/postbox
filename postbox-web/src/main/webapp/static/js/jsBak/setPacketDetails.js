/**
 * Created by Administrator on 2017/3/22.
 */
var setPacketDetails = new Vue({
    el: '#setPacketDetails',
    data:{
        packet:{
            type:1,
            name:'清清茶香',
            img:'',
            commodityName:'普洱',
            desc:'好酒需要与朋友分享',
            commodityUnitName:'毫升',
            number:0,
            money:0,
            receive:1,
            doneeQuota:2,
            receiveNumber:10,
            commodityTotalQuantity:15,
            packetList:[
                {img:staticPath+'/static/images/user-face.png',name:'星空',time:'2017-02-30 23:59:59',product:'XXX普洱',number:'5克'}
            ]
        },
        isSend:false
    },
    computed:{

    },
    beforeMount:function () {
        var giftInfoId =  $('#giftInfoId').val();
        var giftInfoCode = $('#giftInfoCode').val();
        var that = this;
        $.ajax({
            url:basePath+'gift/detail/'+giftInfoId+'/'+giftInfoCode,
            data:'',
            type:'GET',
            dataType:'json',
            success:function(response){
                if(response.code == '1007'){
                    dialogComponent.parentTipDialog={
                        show:true,
                        header:'礼包信息',
                        body:response.msg,
                        btnText:'确定'
                    }
                    dialogComponent.enterBtn=function () {
                        wx.closeWindow();
                    }
                }else{
                    var data = response.body;
                    var data = response.body;
                    that.packetFlag = 3;
                    that.isOpen = true;
                    dialogComponent.parentTipDialog.show = false;
                    var list = [];
                    var myself = {};
                    var unit = data.unit;
                    if(data.selfGrabInfo){
                        myself = {
                            img:that.getImg(data.selfGrabInfo.headImg),
                            name:data.selfGrabInfo.nickname,
                            time:timeToDate(data.selfGrabInfo.grabTime),
                            commodityName:data.commodityName,
                            number:data.selfGrabInfo.grabQtn.toString()+unit
                        };
                        list.push(myself);
                    }
                    var othersGrabInfoList = data.othersGrabInfoList;
                    for(var i=0;i<othersGrabInfoList.length;i++){
                        var item = {
                            img:that.getImg(othersGrabInfoList[i].headImg),
                            name:othersGrabInfoList[i].nickname,
                            time:timeToDate(data.othersGrabInfoList[i].grabTime),
                            commodityName:data.commodityName,
                            number:othersGrabInfoList[i].grabQtn+data.unit
                        }
                        list.push(item);
                    }
                    if(data.selfGrabInfo){
                        var num = data.selfGrabInfo.grabQtn;

                    }else{
                        var num = 0;
                    }
                    that.packet = {
                        type:1,
                        name:data.nickname,
                        img:that.getImg(data.headImg),
                        desc:data.greeting,
                        commodityName:data.commodityName,
                        commodityUnitName:data.unit,
                        number:num,
                        money:(Number(data.commodityPrice)*Number(num)/100).toFixed(2),
                        receive:Number(data.doneeQuota)-Number(data.doneeSurplus),
                        doneeQuota:data.doneeQuota,
                        receiveNumber:Number(data.commodityTotalQuantity)-Number(data.commoditySurplusQuantity),
                        commodityTotalQuantity:data.commodityTotalQuantity,
                        packetList:list
                    };
                }
            }
        });
    },
    methods:{
        hide:function () {
            this.isSend = false;
        },
        sendContinue:function () {
            share();
            this.isSend = true;
        },
        getImg:function(fileName){
            return basePath + "gift/downloadFile?fileName=" + fileName;
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