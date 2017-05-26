package cn.datai.gift.web.util.templateMessage;

import cn.datai.gift.web.contants.UrlsConstants;
import cn.datai.gift.web.model.templateMessage.WxTemplate;
import cn.datai.gift.web.util.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/3/24.
 */
public class SendTemplateMessageUtil {

    private static final Logger logger = LoggerFactory.getLogger(SendTemplateMessageUtil.class);

    public static void sendTemplateMessage(String accessToken, WxTemplate wechatTemplate) throws Exception {
        String jsonString = new Gson().toJson(wechatTemplate).toString();
        String requestUrl = UrlsConstants.SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);

        String jsonStr = HttpClientUtil.httpPostWithJSON(requestUrl,jsonString);
        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        logger.info("jsonObject="+jsonObject);
        if (null != jsonObject) {
            int errorCode = jsonObject.getInteger("errcode");
            if (0 == errorCode) {
                logger.info("模板消息发送成功");
            } else {
                String errorMsg = jsonObject.getString("errmsg");
                logger.info("模板消息发送失败,错误是 "+errorCode+",错误信息是"+ errorMsg);
            }
        }
    }
}
