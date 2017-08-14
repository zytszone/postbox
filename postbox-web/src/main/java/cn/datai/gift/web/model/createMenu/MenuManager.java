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
    public static Menu getMenu(String type) {

        CommonButton btn21 = new CommonButton(CommonConstants.EXPRESS_DELIVERY_NAME,CommonConstants.VIEW_TYPE,"",type.equalsIgnoreCase("dev") ? CommonConstants.EXPRESS_DELIVERY_URL_DEV : CommonConstants.EXPRESS_DELIVERY_URL_NOT_DEV);
        CommonButton btn22 = new CommonButton(CommonConstants.FOR_ME_LEAD_NAME,CommonConstants.VIEW_TYPE,"",type.equalsIgnoreCase("dev") ? CommonConstants.FOR_ME_LEAD_URL_DEV :CommonConstants.FOR_ME_LEAD_URL_NOT_DEV);
        CommonButton btn23 = new CommonButton(CommonConstants.REPAIR_NAME,CommonConstants.VIEW_TYPE,"",type.equalsIgnoreCase("dev") ? CommonConstants.REPAIR_URL_DEV : CommonConstants.REPAIR_URL_NOT_DEV);

        CommonButton btn31 = new CommonButton(CommonConstants.MY_HOMEPAGE_NAME,CommonConstants.VIEW_TYPE,"",type.equalsIgnoreCase("dev") ? CommonConstants.MY_HOMEPAGE_URL_DEV : CommonConstants.MY_HOMEPAGE_URL_NOT_DEV);
        CommonButton btn32 = new CommonButton(CommonConstants.CENTER_HELP_NAME,CommonConstants.VIEW_TYPE,"",type.equalsIgnoreCase("dev") ? CommonConstants.CENTER_HELP_URL_DEV : CommonConstants.CENTER_HELP_URL_NOT_DEV);

        /**
         * 微信：  mainBtn1,mainBtn2 ,mainBtn3 底部的3个一级菜单。
         */

        CommonButton mainBtn1 = new CommonButton(CommonConstants.OPEN_BOX_NAME,CommonConstants.VIEW_TYPE, "",type.equalsIgnoreCase("dev") ? CommonConstants.OPEN_BOX_URL_DEV : CommonConstants.OPEN_BOX_URL_NOT_DEV);

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName(CommonConstants.MY_EXPRESS_NAME);
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22,btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName(CommonConstants.MY_CENTER_NAME);
        mainBtn3.setSub_button(new CommonButton[] {btn31,btn32});

        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2,mainBtn3});

        return menu;
    }
}