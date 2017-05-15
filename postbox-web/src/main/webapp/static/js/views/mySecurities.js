/**
 * Created by Administrator on 2017/3/22.
 */
var mySecurities = new Vue({
    el: '#mySecurities',
    data:{
        securitiesList:[
            {type:'普洱',name:'中茶XX树',number:10,unit:'克',from:'XX会员单位',date:'2017.02.14-2017.03.14',isUse:true},
            {type:'普洱',name:'中茶XX树',number:50,unit:'克',from:'XX会员单位',date:'2017.02.14-2017.03.14',isUse:true}
        ]
    },
    methods:{
        getList:function () {
            var that = this;
            $.ajax({
                url:'',
                type:'POST',
                data:'',
                dataType:'json',
                success:function(response){
                    var data = response.body;

                },
                error:function (err) {

                }
            });
        }
    }
});
$('.tab-box').on('click','.tab-item',function () {
    $('.tab-box .tab-item').removeClass('active');
    $(this).addClass('active');
});

