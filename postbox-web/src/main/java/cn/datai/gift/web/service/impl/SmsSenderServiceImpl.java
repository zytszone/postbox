package cn.datai.gift.web.service.impl;

import cn.datai.gift.utils.HttpRequestUtil;
import cn.datai.gift.utils.HttpUtils;
import cn.datai.gift.web.service.SmsSenderService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 短消息发送Service
 * Created by H.CAAHN on 2017/6/19.
 */
@Service
public class SmsSenderServiceImpl implements SmsSenderService {
    @Value("${api.sms.open}")
    private boolean open;

    @Value("${api.sms.url}")
    private String url;

    @Value("${api.sms.appcode}")
    private String appcode;

    @Value("${api.sms.signtip}")
    private String signtip;

    @Value("${api.sms.captcha.code}")
    private String captchaCode;

    @Value("${api.sms.reached.code}")
    private String reachedCode;

    private Map<String, String> header;

    private static final Logger logger = LoggerFactory.getLogger(SmsSenderServiceImpl.class);

    @PostConstruct
    public void initSmsSenderService() {
        header = new HashMap<String, String>();
        header.put("Authorization", "APPCODE " + appcode);
    }

    @Async
    public boolean sendCaptcha(String mobileno, String captcha) {
        return this.sendSms(captchaCode, mobileno, "vcode", captcha);
    }

    @Async
    public boolean sendReached(String mobileno, String address, String boxCode) {
        return this.sendSms(reachedCode, mobileno, "address", address, "code" ,boxCode);
    }

    public boolean sendSms(String templateCode, String mobileno, String... args) {
        return sendSms(templateCode, new String[]{mobileno}, args);
    }

    public boolean sendSms(String templateCode, String[] mobileno, String... args) {
        try {
            String mobilenostr = null;
            if (mobileno == null || mobileno.length == 0) {
                logger.warn("SMS API接口调用失败，手机号码不允许为空");
                return false;
            }
            else if (mobileno.length == 1) {
                mobilenostr = mobileno[0];
            }
            else {
                StringBuffer buf = new StringBuffer();
                for (String m : mobileno) {
                    if (buf.length() > 0) {
                        buf.append(",");
                    }
                    buf.append(m);
                }
                mobilenostr = buf.toString();
            }

            Map<String, String> paramStrMap = new HashMap<>();
            for (int i = 0, s = args.length; i < s; i+=2) {
                if (i < s - 1) {
                    paramStrMap.put(args[i], args[i + 1]);
                    continue;
                }
                return false;
            }

            String params = JSON.toJSONString(paramStrMap);
            if (!open) {
                logger.info("SMS API 接口关闭, mobile: {}, 参数: {}", mobileno, params);
                return true;
            }

            Map<String, String> querys = this.buildRequestParams(templateCode, mobilenostr, params);
            String rsp = HttpRequestUtil.get(url, header, querys, "utf-8");
            try {
                JSONObject jsonObject = JSONObject.parseObject(rsp);
                boolean result = jsonObject.getBoolean("success");
                if (result) {
                    logger.debug("SMS API 调用成功, 手机号码: {}, 参数: {}", mobileno, params);
                }
                else {
                    logger.warn("SMS API 调用失败, 手机号码: {}, 参数: {}, 失败原因: {}", mobileno, params, jsonObject.get("message"));
                }
                return result;
            }
            catch (JSONException ex) {
                logger.error("SMS API 调用失败, JSON格式化异常: {}", rsp);
            }
        }
        catch (Exception ex) {
            logger.error("SMS API 调用失败", ex);
        }
        return false;
    }

    private Map<String, String> buildRequestParams(String templateCode, String mobileno, String params) throws UnsupportedEncodingException {
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ParamString", URLEncoder.encode(params, "UTF-8"));
        querys.put("RecNum", mobileno);
        querys.put("SignName", signtip);
        querys.put("TemplateCode", templateCode);
        return querys;
    }
}
