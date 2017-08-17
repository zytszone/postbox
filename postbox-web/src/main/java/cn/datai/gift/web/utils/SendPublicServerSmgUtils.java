package cn.datai.gift.web.utils;

import cn.datai.gift.web.model.templateMessage.TemplateData;
import cn.datai.gift.web.model.templateMessage.WxTemplate;
import cn.datai.gift.web.util.templateMessage.SendTemplateMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息模板
 * Created by Administrator on 2017/8/17.
 */
public class SendPublicServerSmgUtils {

    private static final Logger logger = LoggerFactory.getLogger(SendPublicServerSmgUtils.class);
    /**
     * 推送注册是的公众号消息（模板需要定义好，里面的参数传值，需要根据需要的业务查询数据传入）
     * @param accessToken
     * @param temId
     * @param toUserId
     */
    @Async
    public static void registerMsg( String accessToken,String temId,String toUserId){
        WxTemplate wechatTemplate = new WxTemplate();
        wechatTemplate.setTemplate_id(temId);
        wechatTemplate.setTouser(toUserId);
        wechatTemplate.setUrl("http://baidu.com");
        wechatTemplate.setTopcolor("#173177");

        Map<String,TemplateData> m = new HashMap<>();
        TemplateData first = new TemplateData();
        first.setValue("xxx");
        first.setColor("#173177");
        m.put("first", first);

        TemplateData keyword1 = new TemplateData();
        keyword1.setValue("xxxxxxx");
        keyword1.setColor("#173177");
        m.put("key1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue("xxxxxx");
        keyword2.setColor("#173177");
        m.put("key2", keyword2);

        TemplateData remark = new TemplateData();
        remark.setValue("点击查看最新实时行情");
        remark.setColor("#173177");
        m.put("remark", remark);
        wechatTemplate.setData(m);

        try {
            SendTemplateMessageUtil.sendTemplateMessage(accessToken,wechatTemplate);
        } catch (Exception e) {
            logger.error("模板发送发生异常！！！！！");
            e.printStackTrace();
        }
    }

    /**
     * 快递员放入快递后的通知快递所属人的通知
     * @param accessToken
     * @param temId
     * @param toUserId
     */
    @Async
    public static void expressInfoChangeMobile( String accessToken,String temId,String toUserId){
        WxTemplate wechatTemplate = new WxTemplate();
        wechatTemplate.setTemplate_id(temId);
        wechatTemplate.setTouser(toUserId);
        wechatTemplate.setUrl("http://baidu.com");
        wechatTemplate.setTopcolor("#173177");

        Map<String,TemplateData> m = new HashMap<>();
        TemplateData first = new TemplateData();
        first.setValue("xxx");
        first.setColor("#173177");
        m.put("first", first);

        TemplateData keyword1 = new TemplateData();
        keyword1.setValue("xxxxxxx");
        keyword1.setColor("#173177");
        m.put("key1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue("xxxxxx");
        keyword2.setColor("#173177");
        m.put("key2", keyword2);

        TemplateData remark = new TemplateData();
        remark.setValue("点击查看最新实时行情");
        remark.setColor("#173177");
        m.put("remark", remark);
        wechatTemplate.setData(m);

        try {
            SendTemplateMessageUtil.sendTemplateMessage(accessToken,wechatTemplate);
        } catch (Exception e) {
            logger.error("模板发送发生异常！！！！！");
            e.printStackTrace();
        }
    }
}
