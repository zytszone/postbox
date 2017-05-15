/**
 * Created by Administrator on 2017/3/22.
 */
var getPacket = new Vue({
    el: '#getPacket',
    data:{
        isOpen:false,
        packetFlag:1,
        packet:{
            type:1,
            name:'清清茶香',
            img:staticPath+'/static/images/packet-img.png',
            commodityName:'普洱',
            desc:'好酒需要与朋友分享',
            commodityUnitName:'克',
            number:5,
            money:'666.66',
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
    create:function () {
        var data = getQueryString();
        $.ajax({
            url:basePath+'gift/status/'+data.giftInfoId+'/'+data.gifInfoCode,
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
                            window.location.href = basePath+'index';
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
                        this.packetFlag = 4;
                        var list = [];
                        var myself = {
                            img:data.selfGrabInfo.headImg,
                            name:data.selfGrabInfo.nickname,
                            time:data.selfGrabInfo.grabTime,
                            product:data.commodityName,
                            number:data.selfGrabInfo.grabQtn+data.commodityUnitName
                        };
                        list.push(myself);
                        var othersGrabInfoList = data.othersGrabInfoList;
                        for(var i=0;i<othersGrabInfoList.length;i++){
                            var item = {
                                img:othersGrabInfoList.headImg,
                                name:othersGrabInfoList.nickname,
                                time:othersGrabInfoList.grabTime,
                                product:data.commodityName,
                                number:othersGrabInfoList.grabQtn+data.commodityUnitName
                            }
                            list.push(item);
                        }
                        this.packet = {
                            type:1,
                            name:data.donatorNickname,
                            img:data.donatorHeadImg,
                            desc:data.greeting,
                            commodityUnitName:data.commodityUnitName,
                            number:data.selfGrabInfo.grabQtn,
                            money:'666.66',
                            receive:Number(data.doneeQuota)-Number(data.doneeSurplus),
                            doneeQuota:data.doneeQuota,
                            receiveNumber:Number(data.commodityTotalQuantity)-Number(data.commoditySurplusQuantity),
                            commodityTotalQuantity:data.commodityTotalQuantity,
                            packetList:list
                        };
                        break;
                    case "1011":
                        var data = response.body;
                        this.packet.name = data.donatorNickname;
                        this.packet.img = data.donatorHeadImg;
                        this.packet.desc = data.greeting;
                        break;
                }
            }
        });
    },
    updated:function () {
        if(this.packetFlag == 3){
            loaded('wrapper');
        }
    },
    methods:{
        openPacket:function () {
            var that = this;
            $.ajax({
                url:'',
                type:'GET',
                data:'',
                dataType:'json',
                success:function(){
                    that.packetFlag = 2;
                },
                error:function (err) {
                    toastComponent.parentToastMessage = {
                        show:true,
                        body:err
                    }
                }
            });
        },
        setAccount:function () {
            var that = this;
            $.ajax({
                url:'',
                data:'',
                dataType:'',
                success:function(){
                    that.packetFlag = 3;
                    that.isOpen = true;
                },
                error:function (err) {
                    toastComponent.parentToastMessage = {
                        show:true,
                        body:err
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

//上拉刷新
var myScroll,
    pullUpEl,
    pullUpOffset;
function loaded(id) {
    pullUpEl = $('.pullUp');
    pullUpOffset = pullUpEl.height();
    myScroll = new iScroll(id, {
        useTransition: true,
        onRefresh: function () {
            if (pullUpEl.hasClass('loading')) {
                pullUpEl.removeClass('loading');
                pullUpEl.find('.pullUpLabel').html('加载更多...');
            }
            $(".pullUp").hide();
        },
        onScrollMove: function (e) {
            if ( this.scrollerH < this.wrapperH && this.y < (this.minScrollY-pullUpOffset) || this.scrollerH > this.wrapperH && this.y < (this.maxScrollY - pullUpOffset) ) {
                pullUpEl.show();
                pullUpEl.addClass('flip');
            }
            if (this.scrollerH < this.wrapperH && this.y > (this.minScrollY-pullUpOffset) && pullUpEl.hasClass('flip') || this.scrollerH > this.wrapperH && this.y > (this.maxScrollY - pullUpOffset) && pullUpEl.hasClass('flip')) {
                pullUpEl.hide();
                pullUpEl.removeClass('flip');
            }
        },
        onScrollEnd: function () {
            if (pullUpEl.hasClass('flip')) {
                pullUpEl.removeClass('flip').addClass('loading');
                pullUpEl.find('.pullUpLabel').html('加载中...');
                setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
                    getPacket.packet.packetList.push({img:staticPath+'/static/images/user-face.png',name:'星空',time:'2017-02-30 23:59:59',product:'XXX普洱',number:'5克'});
                }, 500);
            }
        }
    });
}

