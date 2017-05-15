/**
 * Created by Administrator on 2017/3/22.
 */
var commodityDetails = new Vue({
    el:'#commodityDetails',
    data:{
        commodity:{
            commodityContractId:1,
            headImg:'http://wangruoyu.developer.jsdttec.com/static/images/commodity-img1.jpg',
            commodityType:'普洱',
            commodityName:'中茶XX树',
            desc:'精选自普洱核心产区，品质上乘',
            unit:'毫升',
            standardUnitName:"瓶",
            standardUnitMultiple:'750',
            unitPrice:0,
            validDate:'2018年12月31号'
        },
        imgList:[
            {src:'http://wangruoyu.developer.jsdttec.com/static/images/commodity-img1.jpg'},
            {src:'http://wangruoyu.developer.jsdttec.com/static/images/commodity-img2.jpg'},
            {src:'http://wangruoyu.developer.jsdttec.com/static/images/commodity-img3.jpg'}
        ],
        isShow:false,
        inputNumber:'',
        chooseUnit:'',
        chooseUnitNumber:1
    },
    computed:{
        unitMoney:function () {
            return (this.commodity.standardUnitMultiple*this.commodity.unitPrice/100).toFixed(2)
        },
        totalMoney:function () {
            if(this.inputNumber==''){
                return 0;
            }else{
                var num = this.inputNumber*this.chooseUnitNumber*this.commodity.unitPrice/100;
                return num.toFixed(2);
            }
        }
    },
    beforeMount:function () {
        var commodityId = getQueryString("commodityId");
        var that = this;
        $.ajax({
            url: basePath + "buyCommodity/queryCommodityDetail",
            type: "POST",
            data: {'commodityId':commodityId},
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0){
                    var data = rsp.body;
                    that.commodity={
                        commodityContractId:data.commodityContractId,
                        headImg:'http://wangruoyu.developer.jsdttec.com/static/images/commodity-img1.jpg',
                        commodityType:data.subjectMatter,
                        commodityName:data.commodityName,
                        desc:'精选自普洱核心产区，品质上乘',
                        unit:data.unit,
                        company:data.company,
                        brand:data.brand,
                        packingType:data.packingType,
                        packingSize:data.packingSize,
                        standardUnitName:data.standardUnitName,
                        standardUnitMultiple:data.standardUnitMultiple,
                        unitPrice:data.unitPrice,
                        chooseUnitNumber:data.chooseUnitNumber,
                        validDate:(new Date(data.validDate)).format('yyyy年MM月dd'),
                        imgList:that.imgList
                    }
                    that.chooseUnit = that.commodity.unit;
                }
            }
        });
    },
    methods:{
        hide:function () {
          this.isShow = false;
          this.inputNumber = '';
        },
        setStandard:function () {
            this.isShow = true;
            this.inputNumber = 1;
        },
        minus:function () {
            var num = Number(this.inputNumber);
            if(num>1){
                this.inputNumber = num-1;
            }
        },
        plus:function () {
            var num = Number(this.inputNumber);
            this.inputNumber = num+1;
        },
        sellInPacket:function () {
            if(this.inputNumber==''){
                this.setStandard();
            }else{
                window.location.href = basePath+'gift/sellInOrder?commodityId='+this.commodity.commodityContractId+'&quantity='+this.inputNumber+'&quantityType='+(this.chooseUnitNumber==1?1:2);
            }
        }
    }
});
$('.standard-box').on('click','span',function () {
    var idx = $(this).index();
    $('.standard-box span').removeClass('active');
        commodityDetails.inputNumber=1;
    $(this).addClass('active');
    if(idx==0){
        commodityDetails.chooseUnit=commodityDetails.commodity.unit;
        commodityDetails.chooseUnitNumber=1;
    }else{
        commodityDetails.chooseUnit=commodityDetails.commodity.standardUnitName;
        commodityDetails.chooseUnitNumber=commodityDetails.commodity.standardUnitMultiple;
    }
})
var swiper = new Swiper('.swiper-container', {
    effect: 'coverflow',
    grabCursor: true,
    centeredSlides: true,
    pagination: '.swiper-pagination',
    slidesPerView: 1,
    loop: true,
    autoplay:3000
});
/*
 *显示分时数据
 * */
// function showTrend() {
//     myChart1.setOption(lineOption);
//     var url = basePath+"/quotation/trend/XAG";
//     var data;
//     $.get(url, data, function (resp) {
//         if (resp.code != 0) {
//             console.log(resp.msg);
//             return;
//         }
//         var len = resp.body.length;
//         var lineData = [];  // 时间
//         var lineDate = [];  // 价格
//         for (var index = 0; index < len; index++) {
//             var item = resp.body[index];
//             lineData.push(item.close);
//             var now = new Date(item.timestamp);
//             lineDate.push(now.Format('hh:mm'));
//         }
//         lineOption.xAxis.data = lineDate;
//         lineOption.series[0].data = lineData;
//         myChart1.setOption(lineOption);
//         lineData=null;
//         lineDate=null;
//     });
// }
// /*
//  *显示K线数据 type:1min,5min,15min
//  * */
// function showKline(type) {
//     myChart2.setOption(KOption);
//     var url = basePath+"/quotation/kline/XAG/" + type;
//     var data;
//     $.get(url, data, function (resp) {
//         if (resp.code != 0) {
//             console.log(resp.msg);
//             return;
//         }
//         var rawList = [];
//         var len = resp.body.length;
//         for (var index = 0; index < len; index++) {
//             var item = resp.body[index];
//             var date = new Date(item.timestamp);
//             raw = [date.Format('hh:mm'), item.open, item.close, item.low, item.high];
//             rawList.push(raw);
//         }
//         var kDate = rawList.map(function (item) {
//             return item[0];
//         });
//
//         var kData = rawList.map(function (item) {
//             return [+item[1], +item[2], +item[3], +item[4]];
//         });
//         KOption.xAxis.data = kDate;
//         KOption.series[0].data = kData;
//         myChart2.setOption(KOption);
//         rawList = null;
//         kDate = null;
//         kData = null;
//     });
// }
// //showTrend(); // 默认显示分时数据
// //K线切换
// $('.chart-tab').on('click', 'li', function () {
//     $('.chart-tab li').removeClass('active');
//     $(this).addClass('active');
//     var index = $(this).index();
//     switch (index) {
//         case 0:
//             $('#chart2').css({'z-index':1,'opacity':0});
//             $('#chart1').css({'z-index':2,'opacity':1});
//             showTrend();
//             break;
//         case 1:
//             $('#chart1').css({'z-index':1,'opacity':0});
//             $('#chart2').css({'z-index':2,'opacity':1});
//             showKline("1min");
//             break;
//         case 2:
//             $('#chart1').css({'z-index':1,'opacity':0});
//             $('#chart2').css({'z-index':2,'opacity':1});
//             showKline("5min");
//             break;
//     }
// });
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

