/**
 * Created by Administrator on 2017/3/22.
 */
var createAddress = new Vue({
    el: '#createAddress',
    data:{
        title:'',
        isAdd:true,
        userDeliveryInfo:{
            userDeliveryInfoId:'',
            username:'',
            telNumber:'',
            proviceFirstStageName:'',
            addressCitySecondStageName:'',
            addressCountiesThirdStageName:'',
            addressDetailInfo:'',
            isDefault:false
        }
    },
    computed:{
    },
    beforeMount:function () {
        var that = this;
        var id = getQueryString('userDeliveryInfoId');
        if(id){
            that.isAdd = false;
            that.title='编辑地址';
            $.ajax({
                url: basePath + "address/queryUserDeliveryInfo",
                type: "POST",
                data: {'userDeliveryInfoId':id},
                dataType: "json",
                success: function (rsp) {
                    if (rsp.code == 0) {
                        var data = rsp.body;
                        that.userDeliveryInfo = {
                            userDeliveryInfoId:data.userDeliveryInfoId,
                            username:data.username,
                            telNumber:data.telNumber,
                            proviceFirstStageName:data.proviceFirstStageName,
                            addressCitySecondStageName:data.addressCitySecondStageName,
                            addressCountiesThirdStageName:data.addressCountiesThirdStageName,
                            addressDetailInfo:data.addressDetailInfo,
                            isDefault:(!data.isDefault||data.isDefault=='false') ? false: true
                        }
                        var option = {
                            province:data.proviceFirstStageName,
                            city:data.addressCitySecondStageName,
                            district:data.addressCountiesThirdStageName
                        };
                        $('#distpicker').distpicker(option)
                    }
                }
            });
        }else{
            this.title='新建地址';
        }
    },
    mounted:function () {
        var id = getQueryString('userDeliveryInfoId');
        if(!id){
            $('#distpicker').distpicker({'province':"省市",'city':"市区",'district':"区县"});
        }
    },
    methods:{
        saveAddress:function () {
            var that = this;
            var ajaxUrl = null;
            if(that.isAdd){
                ajaxUrl = basePath + "address/insertAddress";
            }else{
                ajaxUrl = basePath + "address/updateAddress";
            }
            $.ajax({
                url: ajaxUrl,
                type: "POST",
                data: that.userDeliveryInfo,
                dataType: "json",
                success: function (rsp) {
                    if (rsp.code == 0) {
                        window.history.back();
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
