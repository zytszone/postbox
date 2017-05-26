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
//        CommonButton btn11 = new CommonButton(CommonConstants.OPEN_BOX_NAME,CommonConstants.CLICK_TYPE, CommonConstants.OPEN_BOX_NAME_KEY,"");
//
//        CommonButton btn12 = new CommonButton(CommonConstants.QUOTES_MAKET_NAME,CommonConstants.VIEW_TYPE,"",CommonConstants.QUOTES_MAKET_URL);
        
        /**
         * 微信：  mainBtn1,mainBtn2 底部的两个一级菜单。
         */
        
//        ComplexButton mainBtn1 = new ComplexButton();
//        mainBtn1.setName(CommonConstants.PUER_QUOTES_NAME);
//        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12});

//        CommonButton mainBtn2 = new CommonButton(CommonConstants.SELF_SOTCK_NAME,CommonConstants.VIEW_TYPE,"",CommonConstants.SELF_SOTCK_URL);

        CommonButton mainBtn1 = new CommonButton(CommonConstants.OPEN_BOX_NAME,CommonConstants.CLICK_TYPE, CommonConstants.OPEN_BOX_NAME_KEY,"");

        CommonButton mainBtn2 = new CommonButton(CommonConstants.OTHER_SERVER_NAME,CommonConstants.CLICK_TYPE,CommonConstants.OTHER_SERVER_NAME_KEY,"");


        
        /**
         * 封装整个菜单
         */
        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2});

        return menu;
    }
}