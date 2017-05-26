package cn.datai.gift.web.util.queryMenus;

import cn.datai.gift.web.contants.UrlsConstants;
import cn.datai.gift.web.model.queryMenus.ComplexMenus;
import cn.datai.gift.web.util.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public class QueryMenuUtil {

    private static final Logger logger = LoggerFactory.getLogger(QueryMenuUtil.class);

    /**
     * 查询自定义菜单
     * @param accessToken
     * @return
     */
    public static List<ComplexMenus> queryMenus(String accessToken){

        // 拼装查询菜单的url
        String url = UrlsConstants.menu_query_url.replace("ACCESS_TOKEN", accessToken);

        // 调用接口查询菜单
        String jsonStr = HttpClientUtil.httpGetRequest(url);

        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        if (null != jsonObject) {
            JSONObject jsonObject1 = jsonObject.getJSONObject("menu");
            JSONArray jsonArray = jsonObject1.getJSONArray("button");
            List<ComplexMenus> list = JSONArray.parseArray(jsonArray.toString(),ComplexMenus.class);
            return list ;
        }
        return null;
    }
}
