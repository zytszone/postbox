/**
 * Created by Administrator on 2017/3/22.
 */
var first = getCookie('isFirst');
var isFirst = (first=='false') ? false : true;
var getPacket = new Vue({
    el: '#getPacket',
    data:{
        isOpen:false,
        packetFlag:1,
        isFirst:true,
        packet:{
            type:1,
            name:'清清茶香',
            img:'',
            commodityName:'普洱',
            desc:'',
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
        }
    },
    computed:{

    },
    beforeMount:function () {
        this.isFirst = isFirst;
        var giftInfoId =  $('#giftInfoId').val();
        var giftInfoCode = $('#giftInfoCode').val();
        var that = this;
        $.ajax({
            url:basePath+'gift/status/'+giftInfoId+'/'+giftInfoCode,
            data:'',
            type:'GET',
            dataType:'json',
            success:function(response){
                var code = response.code;
                switch (code){
                    case "1007":
                        dialogComponent.parentTipDialog={
                            show:true,
                            header:'礼包信息',
                            body:response.msg,
                            btnText:'确定'
                        }
                        dialogComponent.enterBtn=function () {
                            wx.closeWindow();
                        };
                        break;
                    case "1001":
                        dialogComponent.parentTipDialog={
                            show:true,
                            header:'礼包信息',
                            body:'该礼包已超过24小时，如已领取，可在“礼包账户“中查看',
                            btnText:'进入礼包账户'
                        }
                        dialogComponent.enterBtn=function () {
                            window.location.href = basePath+'gift/index';
                        };
                        break;
                    case '1002':
                        dialogComponent.parentTipDialog={
                            show:true,
                            header:'礼包信息',
                            body:'该礼包以被抢光',
                            btnText:'查看详情'
                        }
                        dialogComponent.enterBtn=function () {
                            that.queryPacket();
                        }
                        break;
                    case '1008':
                        dialogComponent.parentTipDialog={
                            show:true,
                            header:'礼包信息',
                            body:'礼包已指定受赠人,无权限操作'
                        }
                        dialogComponent.enterBtn=function () {
                            wx.closeWindow();
                        }
                        break;
                    case '1010':
                        var data = response.body;
                        that.packetFlag = 3;
                        that.isOpen = true;
                        $('body').removeClass('bg');
                        var list = [];
                        var unit = data.unit;
                        var myself = {
                            img:that.getImg(data.selfGrabInfo.headImg),
                            name:data.selfGrabInfo.nickname,
                            time:timeToDate(data.selfGrabInfo.grabTime),
                            commodityName:data.commodityName,
                            number:data.selfGrabInfo.grabQtn.toString()+ unit
                        };
                        list.push(myself);
                        var othersGrabInfoList = data.othersGrabInfoList;
                        for(var i=0;i<othersGrabInfoList.length;i++){
                            var item = {
                                img:that.getImg(othersGrabInfoList[i].headImg),
                                name:othersGrabInfoList[i].nickname,
                                time:timeToDate(othersGrabInfoList[i].grabTime),
                                commodityName:data.commodityName,
                                number:othersGrabInfoList[i].grabQtn+data.unit
                            }
                            list.push(item);
                        }
                        that.packet = {
                            type:1,
                            name:data.nickname,
                            img:that.getImg(data.headImg),
                            desc:data.greeting,
                            commodityName:data.commodityName,
                            commodityUnitName:data.unit,
                            number:data.selfGrabInfo.grabQtn,
                            money:(Number(data.commodityPrice)*Number(data.selfGrabInfo.grabQtn)/100).toFixed(2),
                            receive:Number(data.doneeQuota)-Number(data.doneeSurplus),
                            doneeQuota:data.doneeQuota,
                            receiveNumber:Number(data.commodityTotalQuantity)-Number(data.commoditySurplusQuantity),
                            commodityTotalQuantity:data.commodityTotalQuantity,
                            packetList:list
                        };
                        break;
                    case "1011":
                        var data = response.body;
                        that.packet.name = data.donatorNickname;
                        that.packet.img = that.getImg(data.donatorHeadImg);
                        that.packet.desc = data.greeting;
                        break;
                }
            }
        });
    },
    methods:{
        queryPacket:function () {
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
                        that.packetFlag = 3;
                        that.isOpen = true;
                        $('body').removeClass('bg');
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
                        }
                        list.push(myself);
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
                        var num = data.selfGrabInfo?data.selfGrabInfo.grabQtn:0;
                        that.packet = {
                            type:1,
                            name:data.nickname,
                            img:that.getImg(data.headImg),
                            desc:data.greeting,
                            commodityName:data.commodityName,
                            commodityUnitName:data.unit,
                            number:num,
                            money:Number(data.commodityPrice)*Number(num),
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
        openPacket:function () {
            var giftInfoId = $('#giftInfoId').val();
            var giftInfoCode = $('#giftInfoCode').val();
            var that = this;
            $.ajax({
                url: basePath + 'gift/open/' + giftInfoId + '/' + giftInfoCode,
                type: 'GET',
                data: '',
                dataType: 'json',
                success: function (response) {
                    that.packetFlag = 3;
                    that.isOpen = true;
                    $('body').removeClass('bg');
                    that.packet.commodityName = response.body.commodityName;
                    that.packet.number = response.body.selfGrabInfo.grabQtn;
                    that.packet.money = response.body.selfGrabInfo.grabQtn * response.body.commodityPrice;
                    that.queryPacket();
                },
                error: function (err) {
                    toastComponent.parentToastMessage = {
                        show: true,
                        body: err
                    }
                }
            });
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

//上拉刷新
// var offsetTop = $('.list-wrap').offset().top;
// $(window).on('scroll',function () {
//     var scrollHeight = $('.list-wrap').outerHeight()+offsetTop;
//     var scrollTop = $(window).scrollTop();
//     var scroll = wrapperH+scrollTop;
//     if(scroll>scrollHeight){
//         accountDetails.giftList.push({
//             img:othersGrabInfoList[i].headImg,
//             name:othersGrabInfoList[i].nickname,
//             time:othersGrabInfoList[i].grabTime,
//             product:data.commodityName,
//             number:othersGrabInfoList[i].grabQtn+data.commodityUnitName
//         });
//     }
// });