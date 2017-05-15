/**
 * Created by Administrator on 2017/3/22.
 */
var check = true;
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
        },
        saveInfo:null
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
                        };
                        var option = {
                            province:data.proviceFirstStageName,
                            city:data.addressCitySecondStageName,
                            district:data.addressCountiesThirdStageName,
                            autoSelect:false
                        };
                        $('#distpicker').distpicker(option);
                        that.saveInfo = $.extend(true,{},that.userDeliveryInfo);
                    }
                }
            });
        }else{
            this.title='新建地址';
        }
        that.saveInfo = $.extend(true,{},that.userDeliveryInfo);
    },
    mounted:function () {
        var id = getQueryString('userDeliveryInfoId');
        if(!id){
            $('#distpicker').distpicker({'province':"省市",'city':"市区",'district':"区县",autoSelect:false});
        }
    },
    methods:{
        saveAddress:function () {
            var that = this;
            if(that.userDeliveryInfo.username==''){
                dialogComponent.parentTipDialog = {
                    show: true,
                    header: '消息提示',
                    body: '收件人不能为空',
                    btnText:'确定'
                }
                return;
            }
            if(that.userDeliveryInfo.telNumber==''){
                dialogComponent.parentTipDialog = {
                    show: true,
                    header: '消息提示',
                    body: '手机号码不能为空',
                    btnText:'确定'
                }
                return;
            }else {
                var re = new RegExp("^1[0-9]*$");
                if (!re.test(that.userDeliveryInfo.telNumber) || that.userDeliveryInfo.telNumber.length!=11) {
                    dialogComponent.parentTipDialog = {
                        show: true,
                        header: '消息提示',
                        body: '请输入正确的手机号码',
                        btnText:'确定'
                    }
                    return;
                }
            }
            if(that.userDeliveryInfo.proviceFirstStageName==''){
                dialogComponent.parentTipDialog = {
                    show: true,
                    header: '消息提示',
                    body: '省市不能为空',
                    btnText:'确定'
                }
                return;
            }
            if(that.userDeliveryInfo.addressCitySecondStageName==''){
                dialogComponent.parentTipDialog = {
                    show: true,
                    header: '消息提示',
                    body: '市区不能为空',
                    btnText:'确定'
                }
                return;
            }
            if(that.userDeliveryInfo.addressCountiesThirdStageName==''){
                if($('#countiesThirdStageName').children().length>1){
                    dialogComponent.parentTipDialog = {
                        show: true,
                        header: '消息提示',
                        body: '区县不能为空',
                        btnText:'确定'
                    }
                    return;
                }
            }
            if(that.userDeliveryInfo.addressDetailInfo==''){
                dialogComponent.parentTipDialog = {
                    show: true,
                    header: '消息提示',
                    body: '详细地址不能为空',
                    btnText:'确定'
                }
                return;
            }
            if(that.userDeliveryInfo.addressDetailInfo.length<5){
                dialogComponent.parentTipDialog = {
                    show: true,
                    header: '消息提示',
                    body: '详细地址不少于5个字，请填写更详细的地址',
                    btnText:'确定'
                }
                return;
            }
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
                        check = false;
                        window.history.go(-1);
                    }else{
                        dialogComponent.parentTipDialog = {
                            show: true,
                            header: '地址信息',
                            body: rsp.body,
                            btnText:'确定'
                        };
                    }
                }
            });
        }
    }
});
// window.removeEventListener('popstate',goBack);
// pushHistory();
// window.addEventListener("popstate", goBackNew,false);
// function goBackNew() {
//     var info1 = JSON.stringify(createAddress.userDeliveryInfo);
//     var info2 = JSON.stringify(createAddress.saveInfo);
//     if (info1 != info2) {
//         if (check) {
//             dialogComponent.parentModalDialog = {
//                 show: true,
//                 header: '信息提示',
//                 body: '修改了信息还未保存，确认现在返回吗？',
//                 leftBtnText: '返回',
//                 rightBtnText: '保存'
//             };
//             dialogComponent.leftBtn = function () {
//                 window.history.go(-1);
//             }
//             dialogComponent.rightBtn = function () {
//                 dialogComponent.parentModalDialog.show = false;
//                 createAddress.saveAddress();
//             }
//         } else {
//             window.history.go(-1);
//         }
//     } else {
//         window.history.go(-1);
//     }
// }
// function pushHistory(){
//     var state = {
//         title:"",
//         url: "#"
//     };
//     window.history.pushState(state, "", '');
// }
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
            body: '消息弹框内容！',
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
