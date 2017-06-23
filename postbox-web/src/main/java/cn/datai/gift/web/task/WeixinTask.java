package cn.datai.gift.web.task;

import cn.datai.gift.web.call.weixin.auth.WeixinAuthService;
import cn.datai.gift.web.contants.TokenContants;
import cn.datai.gift.web.plugin.vo.JsapiTicket;
import cn.datai.gift.web.plugin.vo.WeixinErrorResp;
import cn.datai.gift.web.plugin.vo.WeixinResult;
import cn.datai.gift.web.plugin.vo.WeixinToken;
import cn.datai.gift.web.utils.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 微信刷新基础token
 * Created by Administrator on 2017/3/7.
 */
@Component
public class WeixinTask {

    private static Logger logger = LoggerFactory.getLogger(WeixinTask.class);

    @Value("${weixin.appID}")
    private String appid;

    @Value("${weixin.appsecret}")
    private String secret;

   /* @Autowired
    private RedisTemplate redisTemplate;*/

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeixinAuthService weixinAuthService;

    @PostConstruct
    public void init(){
        try {
            //系统启动时加redis锁检查redis中是否拥有微信token和jsapiTicket
            logger.info("系统启动微信公众号授权");

            //检查是否拥有微信token和jsapiTicket
            logger.info("检查系统不存在授权信息，获取授权信息中......");
            //如果系统中没有两个值，系统启动时就需要执行一次获取动作
            this.refreshToken();
            logger.info("微信授权信息获取结束。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 定时刷微信Token
     *
     * @return
     */
    public WeixinToken refreshToken() throws InterruptedException, IOException {
        try {
            StringBuffer apiURL = new StringBuffer("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&&appid=");
            apiURL.append(this.appid)
                    .append("&secret=")
                    .append(this.secret);
            String tokenJson = restTemplate.getForObject(apiURL.toString(), String.class);
            logger.info("获取微信服务器令牌：{}", tokenJson);
            ObjectMapper jsonMapper = new ObjectMapper();
            WeixinToken weixinToken = null;
            try {
                weixinToken = jsonMapper.readValue(tokenJson, WeixinToken.class);
            } catch (IOException e) {
                WeixinErrorResp weixinErrorResp = jsonMapper.readValue(tokenJson, WeixinErrorResp.class);
                if (weixinErrorResp.getErrcode() == WeixinResult.SYS_BUSY) {
                    //微信服务器忙，稍后重试，抛出异常，让elastic job重试
                    logger.warn("获取微信accessToken异常：系统忙，稍后再试, {}", weixinErrorResp);
                    throw new RuntimeException("获取微信accessToken异常：系统忙，稍后再试");
                }else {
                    //其他错误，重试也不可能成功
                    logger.error("获取微信access token 失败,{}", weixinErrorResp);
                    return null;
                }
            }
            //获取到微信accessToken之后的动作

            TokenContants.WEIXIN_TOKEN = weixinToken.getAccessToken();
            logger.info("存储微信accessToken成功");
            JsapiTicket jsapiTicket = this.refreshJsapiTicket(weixinToken.getAccessToken());//尝试获取jsapiTicket
            return weixinToken;
        } catch (IOException e) {
            logger.error(e.toString());
            throw e;
        }
    }

    /**
     * 刷微信jsapiTicket
     *
     * @param weixinAccessToken @see {@link WeixinToken#accessToken}
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public JsapiTicket refreshJsapiTicket(String weixinAccessToken) throws IOException, InterruptedException {
        JsapiTicket jsapiTicket = new JsapiTicket();
        jsapiTicket = weixinAuthService.getTicket(weixinAccessToken, "jsapi").execute().body();
        long errcode = jsapiTicket.getErrcode();
        if (errcode == WeixinResult.REQUEST_SUCCESS) {

            TokenContants.JSAPI_TICKET = jsapiTicket.getTicket();
            logger.info("获取jsapi_ticket成功, {}", jsapiTicket);
            return jsapiTicket;
        } else if (errcode == WeixinResult.SYS_BUSY) {
            //系统忙才可以重试，其他状态重试也不可能成功
            logger.warn("获取微信jsapi_ticket失败，稍后重试，{}", jsapiTicket);
            throw new RuntimeException("获取微信jsapi_ticket失败");//抛异常，让elastic job 重新执行
        }
        logger.error("获取微信jsapi_ticket失败， 原因：{}", jsapiTicket);
        return null;
    }


}
