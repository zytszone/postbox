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
        canOpen:true,
        overDate:false,
        getOver:false,
        tip:'',
        packet:{
            type:1,
            name:'清清茶香',
            commodityMatter:'',
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
        },
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
    created:function () {
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
                    // case "1007":
                    //     dialogComponent.parentTipDialog={
                    //         show:true,
                    //         header:'礼包信息',
                    //         body:response.msg,
                    //         btnText:'确定'
                    //     }
                    //     dialogComponent.enterBtn=function () {
                    //         wx.closeWindow();
                    //     };
                    //     break;
                    case "1001":
                        var data = response.body;
                        that.packet.img= that.getImg(data.headImgPath);
                        that.packet.name= data.nickname;
                        that.canOpen = false;
                        that.overDate = true;
                        that.tip = '手慢了，礼包已过期';
                        break;
                    case '1002':
                        var data = response.body;
                        that.packet.img= that.getImg(data.headImgPath);
                        that.packet.name= data.nickname;
                        that.canOpen = false;
                        that.getOver = true;
                        that.tip = '手慢了，礼包派完了';
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
                        var list = [];
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
                        }else {
                            that.isGetPacket = false;
                        }
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
                            commodityMatter:data.commodityMatter,
                            typeName:data.typeName,
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
                type:'POST',
                dataType:'json',
                success:function(response){
                    // if(response.code == '1007'){
                    //     dialogComponent.parentTipDialog={
                    //         show:true,
                    //         header:'礼包信息',
                    //         body:response.msg,
                    //         btnText:'确定'
                    //     }
                    //     dialogComponent.enterBtn=function () {
                    //         wx.closeWindow();
                    //     }
                    // }else{
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
                        var num = data.selfGrabInfo?data.selfGrabInfo.grabQtn:0;
                        that.packet = {
                            type:1,
                            commodityMatter:data.commodityMatter,
                            typeName:data.typeName,
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
                   // }
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
                    var code = response.code;
                    switch (code){
                        // case "1007":
                        //     dialogComponent.parentTipDialog={
                        //         show:true,
                        //         header:'礼包信息',
                        //         body:response.msg,
                        //         btnText:'确定'
                        //     }
                        //     dialogComponent.enterBtn=function () {
                        //         wx.closeWindow();
                        //     };
                        //     break;
                        case "1001":
                            that.canOpen = false;
                            that.overDate = true;
                            that.tip = '手慢了，礼包已过期';
                            break;
                        case '1002':
                            that.canOpen = false;
                            that.getOver = true;
                            that.tip = '手慢了，礼包派完了';
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
                        case "1010":
                            that.queryPacket();
                            break;
                        case '0':
                            var data = response.body;
                            that.packetFlag = 3;
                            that.isOpen = true;
                            var list = [];
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
                                    time:timeToDate(othersGrabInfoList[i].grabTime),
                                    commodityName:data.commodityName,
                                    number:othersGrabInfoList[i].grabQtn+data.unit
                                }
                                list.push(item);
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
                                number:data.selfGrabInfo.grabQtn,
                                money:(Number(data.commodityPrice)*Number(data.selfGrabInfo.grabQtn)/100).toFixed(2),
                                receive:Number(data.doneeQuota)-Number(data.doneeSurplus),
                                doneeQuota:data.doneeQuota,
                                receiveNumber:Number(data.commodityTotalQuantity)-Number(data.commoditySurplusQuantity),
                                commodityTotalQuantity:data.commodityTotalQuantity,
                                packetList:list
                            };
                            break;
                    }
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