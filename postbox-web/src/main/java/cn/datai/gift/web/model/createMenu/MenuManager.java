package cn.datai.gift.web.model.createMenu;


import cn.datai.gift.web.contants.CommonConstants;

/**
* 类名: MenuManager </br>
* 包名： com.souvc.weixin.main
* 描述:菜单管理器类 </br>
* 开发人员： liuhf </br>
* 创建时间：  2015-12-1 </br>
* 发布版本：V1.0  </br>
 */
public class MenuManager {

    /**
     * 组装菜单数据
     * 
     * @return
     */
    public static Menu getMenu() {

        CommonButton btn21 = new CommonButton(CommonConstants.EXPRESS_DELIVERY_NAME,CommonConstants.VIEW_TYPE,"",CommonConstants.EXPRESS_DELIVERY_URL);
        CommonButton btn22 = new CommonButton(CommonConstants.FOR_ME_LEAD_NAME,CommonConstants.CLICK_TYPE,CommonConstants.FOR_ME_LEAD_KEY,"");
        CommonButton btn23 = new CommonButton(CommonConstants.REPAIR_NAME,CommonConstants.CLICK_TYPE,CommonConstants.REPAIR_KEY,"");

        CommonButton btn31 = new CommonButton(CommonConstants.REPORT_REPAIR_NAME,CommonConstants.CLICK_TYPE,CommonConstants.REPORT_REPAIR_KEY,"");
        CommonButton btn32 = new CommonButton(CommonConstants.HELP_FOR_USE_NAME,CommonConstants.CLICK_TYPE,CommonConstants.HELP_FOR_USE_KEY,"");

        /**
         * 微信：  mainBtn1,mainBtn2 ,mainBtn3 底部的3个一级菜单。
         */

        CommonButton mainBtn1 = new CommonButton(CommonConstants.OPEN_BOX_NAME,CommonConstants.CLICK_TYPE, CommonConstants.OPEN_BOX_KEY,"");

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName(CommonConstants.MY_EXPRESS_NAME);
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22,btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn2.setName(CommonConstants.MY_CENTER_NAME);
        mainBtn2.setSub_button(new CommonButton[] { btn31, btn32});

        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2,mainBtn3});

        return menu;
    }
}