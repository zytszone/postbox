/**
 * Created by Administrator on 2017/3/22.
 */
var sellIn = new Vue({
    el: '#sellIn',
    data:{
        list:[],
        detailUrl:basePath+'gift/commodityDetails?commodityId=',
        typeList:null
    },
    computed:{
    },
    beforeMount:function () {
        var that = this;
        $.ajax({
            url: basePath + "gift/queryCommodityTypeList",
            type: "GET",
            data: '',
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0){
                    var data = rsp.body;
                    var arr = [];
                    for(var i=0;i<data.length;i++){
                        var obj = {
                            code:data[i].code,
                            commodityTypeId:data[i].commodityTypeId,
                            name:data[i].name
                        }
                        arr.push(obj);
                    }
                    that.typeList = arr;
                }
            }
        });

        $.ajax({
            url: basePath + "gift/queryCommodityContract",
            type: "POST",
            data: {commodityTypeId:0},
            dataType: "json",
            success: function (rsp) {
                if(rsp.code == 0){
                    var data = rsp.body;
                    var arr = [];
                    for(var i=0;i<data.length;i++){
                        var mon = Number(data[i].standardUnitMultiple)*Number(data[i].unitPrice)/100;
                        var obj = {
                            commodityContractId:data[i].commodityContractId,
                            img:that.getImg(data[i].iconUrl),
                            type:data[i].subjectMatter,
                            typeName:data[i].typeName,
                            name:data[i].commodityName,
                            unit:data[i].unit,
                            standardUnitMultiple:data[i].standardUnitMultiple,
                            standardUnitName:data[i].standardUnitName,
                            money:mon
                        }
                        arr.push(obj);
                    }
                    that.list = arr;
                }
            }
        });
    },
    methods:{
        getImg:function(iconUrl){
            return basePath +"static/images/view/"+iconUrl;
        },
        search:function (key) {
            var that = this;
            $.ajax({
                url: basePath + "gift/queryCommodityContract",
                type: "POST",
                data: {commodityTypeId:key},
                dataType: "json",
                success: function (rsp) {
                    if(rsp.code == 0){
                        var data = rsp.body;
                        var arr = [];
                        for(var i=0;i<data.length;i++){
                            var mon = Number(data[i].standardUnitMultiple)*Number(data[i].unitPrice)/100;
                            var obj = {
                                commodityContractId:data[i].commodityContractId,
                                img:that.getImg(data[i].iconUrl),
                                type:data[i].subjectMatter,
                                typeName:data[i].name,
                                name:data[i].commodityName,
                                unit:data[i].unit,
                                standardUnitMultiple:data[i].standardUnitMultiple,
                                standardUnitName:data[i].standardUnitName,
                                money:mon
                            }
                            arr.push(obj);
                        }
                        that.list = arr;
                    }
                }
            });
        }
    }
});
$('.search-box').on('click','span',function () {
    $('.search-box span').removeClass('active');
    $(this).addClass('active');
    sellIn.search($(this).attr('id'));
})
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

//上拉加载
// var windowH = $(window).height();
// var wrapperH = windowH;
// $('#wrapper').css('height',wrapperH);
// $('#scrollList').on('scroll',function () {
//     var scrollHeight = $('#scrollList ul').outerHeight()-50;
//     var scrollTop = $('#scrollList').scrollTop();
//     var scroll = wrapperH+scrollTop;
//     if(scroll>scrollHeight){
//         var list =[{
//             type:'普洱11',
//             img:staticPath+'/static/images/product-img.jpg',
//             name:'中茶XX大树',
//             number:375,
//             unit:'克',
//             money:780.00
//         }];
//         var arr = sellIn.list.concat(list)
//         sellIn.list=arr;
//     }
// });