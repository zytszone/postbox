/**
 * Created by Administrator on 2017/3/22.
 */
var first = getCookie('isFirst');
var isFirst = (first=='false') ? false : true;
var index = new Vue({
    el:'#index',
    data:{
        isFirst:true,
        url:basePath+'gift/commodityDetails?commodityId=',
        giftList:[]
    },
    beforeMount:function () {
        this.isFirst = isFirst;
        var that = this;
        $.ajax({
            url: basePath + "gift/getGiftAccountData",
            type: "GET",
            data: '',
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0) {
                    var data = rsp.body;
                    var arr = [];
                    for (var i = 0; i < data.length; i++) {
                        var mon = Number(data[i].holdCommodityQuantity) * Number(data[i].lastUnitPrice) / 100;
                        var obj = {
                            commodityId: data[i].commodityContractId,
                            type: data[i].subjectMatter,
                            name: data[i].commodityName,
                            number: data[i].holdCommodityQuantity,
                            unit: data[i].unit,
                            money: mon.toFixed(2)
                        }
                        arr.push(obj);
                    }
                    that.giftList = arr;
                    setTimeout(function(){
                        var swiper = new Swiper('.swiper-container', {
                            effect: 'coverflow',
                            grabCursor: true,
                            centeredSlides: true,
                            slidesPerView: 'auto',
                            coverflow: {
                                rotate: 0,
                                stretch: 0,
                                depth: 100,
                                modifier: 1,
                                slideShadows : true
                            }
                        });
                    },0);
                    if(data.length==0){
                        dialogComponent.parentTipDialog={
                            show:true,
                            header: '消息弹框',
                            body: '您的账户暂时没有可用礼包',
                            btnText:'确定'
                        }
                    }

                }
            }
        });
    },
    computed:{
    },
    methods:{
        closeQrcode:function () {
            this.isFirst = false;
            setCookie('isFirst','false');
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

