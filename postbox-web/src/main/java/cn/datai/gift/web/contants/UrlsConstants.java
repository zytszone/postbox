package cn.datai.gift.web.contants;

/**
 * Created by Administrator on 2017/3/24.
 */
public interface UrlsConstants {

    String SEND_TEMPLAYE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    // 菜单创建（POST） 限100（次/天）
    String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //使用接口创建自定义菜单后，开发者还可使用接口删除当前使用的自定义菜单。另请注意，在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单。
    String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    //使用接口查询自定义菜单的url
    String menu_query_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    //http请求方式：GET 获取所有的模板信息
    String all_private_template_url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

}
