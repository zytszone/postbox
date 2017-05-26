package cn.datai.gift.web.util.createMenu;

import cn.datai.gift.web.contants.UrlsConstants;
import cn.datai.gift.web.model.createMenu.Menu;
import cn.datai.gift.web.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 类名: WeixinUtil </br>
* 包名： com.souvc.weixin.util
* 描述: 公众平台通用接口工具类 </br>
* 开发人员： souvc  </br>
* 创建时间：  2015-12-1 </br>
* 发布版本：V1.0  </br>
 */
public class CreateMenuUtil {
    
    private static Logger logger = LoggerFactory.getLogger(CreateMenuUtil.class);

    /**
     * 创建菜单
     * 
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) throws Exception {
        int result = 0;
        // 拼装创建菜单的url
        String url = UrlsConstants.menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString(menu);

        // 调用接口创建菜单
        String jsonStr = HttpClientUtil.httpPostWithJSON(url, jsonMenu);

        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        logger.info("jsonObject="+jsonObject);
        if (null != jsonObject) {
            int errorCode = jsonObject.getInteger("errcode");
            if (0 == errorCode) {
                logger.info("创建菜单成功");
            } else {
                String errorMsg = jsonObject.getString("errmsg");
                logger.info("创建菜单失败,错误是 "+errorCode+",错误信息是"+ errorMsg);
            }
            result = errorCode;
        }
        return result;
    }

}