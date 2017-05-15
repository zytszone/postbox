/**
 * Created by Administrator on 2017/3/22.
 */
var setPacketDetails = new Vue({
    el: '#setPacketDetails',
    data:{
        packet:{
            type:1,
            name:'清清茶香',
            commodityMatter:'',
            img:'',
            commodityName:'普洱',
            desc:'好酒需要与朋友分享',
            commodityUnitName:'毫升',
            typeName:'茶',
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
        isSend:false,
        hasReceive:true,
        isGetPacket:false
    },
    computed:{
        bgClass:function () {
            var commodityMatter = this.packet.typeName;
            if(commodityMatter.indexOf('白酒')>-1){
                return {tea:false,"white-wine":true,"red-wine":false}
            }else if(commodityMatter.indexOf('红酒')>-1){
                return {tea:false,"white-wine":false,"red-wine":true}
            }else if(commodityMatter.indexOf('茶')>-1){
                return {tea:true,"white-wine":false,"red-wine":false}
            }
        }
    },
    beforeMount:function () {
        var giftInfoId =  $('#giftInfoId').val();
        var giftInfoCode = $('#giftInfoCode').val();
        var that = this;
        $.ajax({
            url:basePath+'gift/detail/'+giftInfoId+'/'+giftInfoCode,
            data:{'bizType':'GIVE_GIFT'},
            type:'POST',
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
                    that.packetFlag = 3;
                    that.isOpen = true;
                    dialogComponent.parentTipDialog.show = false;
                    var list = [];
                    var myself = {};
                    var unit = data.unit;
                    if(data.selfGrabInfo){
                        that.isGetPacket = true;
                        myself = {
                            img:that.getImg(data.selfGrabInfo.headImg),
                            name:data.selfGrabInfo.nickname,
                            time:timeToDate(data.selfGrabInfo.grabTime),
                            commodityName:data.commodityName,
                            number:data.selfGrabInfo.grabQtn.toString()+unit
                        };
                        list.push(myself);
                    }else{
                        that.isGetPacket = false;
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
                        commodityMatter:data.commodityMatter,
                        typeName:data.typeName,
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
                    if(that.packet.receiveNumber==that.packet.commodityTotalQuantity){
                        that.hasReceive = false;
                    }else{
                        that.hasReceive = true;
                    }
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