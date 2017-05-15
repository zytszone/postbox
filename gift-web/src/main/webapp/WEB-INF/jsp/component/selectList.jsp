<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="selectListComponent">
<select-list v-bind:select-list="parentSelectList" v-on:selected="getSelected">
</select-list>
</div>
<script>
    // 注册弹框和消息提示框组件
    Vue.component('select-list', {
        props: {
            selectList: {
                top:'100%',
                height:'100%',
                header: '',
                bodyHeight:'100%',
                lists: [
                    {"commodityContractId":1,"commodityName":"拉菲古堡1号","commodityType":"红酒","holdCommodityQuantity":100,"lastUnitPrice":"100"}
                ],
                activeIndex:0,
                activeName: 'XX普洱'
            }
        },
        template: '<div class="list-wrap" v-bind:style="{top:selectList.top,height:selectList.height}">' +
        '<div class="list-header"><div class="icon-close" v-on:click="listHide"></div>{{selectList.header}}</div>' +
        '<div class="list-body" v-bind:style="{height:selectList.bodyHeight}"><div>' +
        '<div v-for="(item,index) in selectList.lists" class="list-item">[{{item.commodityType}}]{{item.commodityName}}<i class="float-right icon-list-active"></i></div>' +
        '</div></div>' +
        '</div>',
        methods: {
            listHide:function () {
                this.selectList.top = '100%';
            },
            listShow:function () {
                this.selectList.top = '0';
            }
        }
    });
</script>