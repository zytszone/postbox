package cn.datai.gift.web.util.createMenu;

import cn.datai.gift.web.contants.UrlsConstants;
import cn.datai.gift.web.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/3/24.
 */
public class DeleteMenuUtil {


    private static Logger logger = LoggerFactory.getLogger(CreateMenuUtil.class);

    /**
     * 删除菜单
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int deleteMenu(String accessToken) throws Exception {
        int result = 0;
        // 拼装删除菜单的url
        String url = UrlsConstants.menu_delete_url.replace("ACCESS_TOKEN", accessToken);

        // 调用接口删除菜单
        String jsonStr = HttpClientUtil.httpGetRequest(url);

        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        logger.info("jsonObject="+jsonObject);
        if (null != jsonObject) {
            int errorCode = jsonObject.getInteger("errcode");
            if (0 == errorCode) {
                logger.info("删除菜单成功");
            } else {
                String errorMsg = jsonObject.getString("errmsg");
                logger.info("删除菜单失败,错误是 "+errorCode+",错误信息是"+ errorMsg);
            }
            result = errorCode;
        }
        return result;
    }
}
