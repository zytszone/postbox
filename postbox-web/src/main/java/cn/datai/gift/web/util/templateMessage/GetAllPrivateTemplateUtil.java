package cn.datai.gift.web.util.templateMessage;

import cn.datai.gift.web.contants.UrlsConstants;
import cn.datai.gift.web.model.templateMessage.PrivateTemplate;
import cn.datai.gift.web.model.templateMessage.TemplateData;
import cn.datai.gift.web.util.HttpClientUtil;
import cn.datai.gift.web.util.createMenu.CreateMenuUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/31.
 */
public class GetAllPrivateTemplateUtil {

    private static Logger logger = LoggerFactory.getLogger(CreateMenuUtil.class);

    /**
     * 获取所有的模板信息
     *
     * @param accessToken 有效的access_token
     */
    public static List<PrivateTemplate> queryAllPrivateTemplate(String accessToken) throws Exception {
        List<PrivateTemplate> result = new ArrayList<>();
        // 拼装创建菜单的url
        String url = UrlsConstants.all_private_template_url.replace("ACCESS_TOKEN", accessToken);

        // 调用接口创建菜单
        String jsonStr = HttpClientUtil.httpGetRequest(url);

        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        logger.info("jsonObject="+jsonObject);
        if (null != jsonObject) {
            List<PrivateTemplate> list = JSONObject.parseArray(jsonObject.get("template_list").toString(),PrivateTemplate.class);
            result = list;
        }
        return result;
    }

    /**
     * 获取一个模板中要填写的参数
     * @param str
     * @return
     */
    private static Map<String,TemplateData> getParams(String str){
        Map<String,TemplateData> map = new HashMap<>();
        Pattern p = Pattern.compile("\\{\\{(.*?)\\.");
        Matcher m = p.matcher(str);
        ArrayList<String> strs = new ArrayList<String>();
        for (String key : strs){
           map.put(key,new TemplateData());
        }
        return map;
    }

}
