package cn.datai.gift.web.model.queryMenus;

/**
 * Created by Administrator on 2017/3/27.
 */
public class ComplexMenus extends BaseMenu {
    private BaseMenu[] sub_button;


    public BaseMenu[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(BaseMenu[] sub_button) {
        this.sub_button = sub_button;
    }
}
