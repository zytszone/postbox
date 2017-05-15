/**
 * Created by Administrator on 2017/3/22.
 */
//注册数字键盘
var numericKeypadComponent = new Vue({
    el: '#numericKeypadComponent',
    data: {
        parentNumericKeypad:{
            keyWord:[]
        }
    }
});
$('.numeric-keypad').on('click',function () {
    checkPasswordComponent.showPassword(numericKeypadComponent.parentNumericKeypad.keyWord);
});
var checkPasswordComponent = new Vue({
    el: '#checkPasswordComponent',
    data:{
        passwordTip:'请输入支付密码，以验证身份',
        forgetPassword:true,
        errorShow:false,
        errorText:'',
        passwordValue:{
            value1:'',
            value2:'',
            value3:'',
            value4:'',
            value5:'',
            value6:''
        }
    },
    computed:{
    },
    methods:{
        showPassword:function (arr) {
            var len = arr.length;
            switch(len){
                case 1:
                    this.passwordValue.value1 = '*';
                    this.passwordValue.value2 = '';
                    this.passwordValue.value3 = '';
                    this.passwordValue.value4 = '';
                    this.passwordValue.value5 = '';
                    this.passwordValue.value6 = '';
                    break;
                case 2:
                    this.passwordValue.value1 = '*';
                    this.passwordValue.value2 = '*';
                    this.passwordValue.value3 = '';
                    this.passwordValue.value4 = '';
                    this.passwordValue.value5 = '';
                    this.passwordValue.value6 = '';
                    break;
                case 3:
                    this.passwordValue.value1 = '*';
                    this.passwordValue.value2 = '*';
                    this.passwordValue.value3 = '*';
                    this.passwordValue.value4 = '';
                    this.passwordValue.value5 = '';
                    this.passwordValue.value6 = '';
                    break;
                case 4:
                    this.passwordValue.value1 = '*';
                    this.passwordValue.value2 = '*';
                    this.passwordValue.value3 = '*';
                    this.passwordValue.value4 = '*';
                    this.passwordValue.value5 = '';
                    this.passwordValue.value6 = '';
                    break;
                case 5:
                    this.passwordValue.value1 = '*';
                    this.passwordValue.value2 = '*';
                    this.passwordValue.value3 = '*';
                    this.passwordValue.value4 = '*';
                    this.passwordValue.value5 = '*';
                    this.passwordValue.value6 = '';
                    break;
                case 6:
                    this.passwordValue.value1 = '*';
                    this.passwordValue.value2 = '*';
                    this.passwordValue.value3 = '*';
                    this.passwordValue.value4 = '*';
                    this.passwordValue.value5 = '*';
                    this.passwordValue.value6 = '*';
                    break;
            }
            if(len==6){
                this.postPassword();
                return;
            }
        },
        postPassword:function () {
            var that = this;
            $.ajax({
                url:basePath+'password/checkPassword',
                type:'POST',
                data:{"oldPwd":numericKeypadComponent.parentNumericKeypad.keyWord},
                dataType:'json',
                success:function(response){
                    var code = response.code;
                    if(0==code){
                        window.location.href=basePath+"password/setPassword"
                    }

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