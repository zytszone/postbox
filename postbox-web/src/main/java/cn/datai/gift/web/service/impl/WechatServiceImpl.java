package cn.datai.gift.web.service.impl;

import cn.datai.gift.web.contants.CommonConstants;
import cn.datai.gift.web.contants.TemplateConstants;
import cn.datai.gift.web.enums.QuoteStatusEnum;
import cn.datai.gift.web.model.message.AllMessage;
import cn.datai.gift.web.model.templateMessage.PrivateTemplate;
import cn.datai.gift.web.model.templateMessage.TemplateData;
import cn.datai.gift.web.model.templateMessage.WxTemplate;
import cn.datai.gift.web.model.vo.CommodityPriceVo;
import cn.datai.gift.web.service.WechatService;
import cn.datai.gift.web.util.message.WechatMessageUtil;
import cn.datai.gift.web.util.templateMessage.SendTemplateMessageUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service
public class WechatServiceImpl implements WechatService {

    private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Override
    public String processRequest(String accessToken,HttpServletRequest request) {
        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        logger.info("含有的参数：{}", JSONObject.toJSONString(map));
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        // 默认回复一个"success"
        String responseMessage = "success";
        // 对消息进行处理
        AllMessage allMessage = new AllMessage();
        allMessage.setToUserName(fromUserName);
        allMessage.setFromUserName(toUserName);
        allMessage.setCreateTime(System.currentTimeMillis());

        switch (msgType){
            case WechatMessageUtil.MESSAGE_TEXT:
                String content = map.get("Content");
                try {
                    //// TODO: 2017/5/26
                    String str = "";
                    CommodityPriceVo commodityPriceVo = JSONObject.parseObject(str,CommodityPriceVo.class);


                    WxTemplate wechatTemplate = new WxTemplate();
                    wechatTemplate.setTemplate_id(TemplateConstants.CHANGEPX_TEM_ID);
                    wechatTemplate.setTouser(fromUserName);
                    wechatTemplate.setUrl("http://price.jsdttec.com/quotesserver/quotation?comCode="+content);
                    wechatTemplate.setTopcolor("#173177");

                    Map<String,TemplateData> m = new HashMap<>();

                    TemplateData first = new TemplateData();

                    first.setValue(QuoteStatusEnum.getStatusName(commodityPriceVo.getTradeStatus()));
                    first.setColor("#173177");
                    m.put("first", first);

//                    大于0    #bd4745        小于0  #26b463

                    TemplateData keyword1 = new TemplateData();
                    keyword1.setValue(commodityPriceVo.getProdName());
                    keyword1.setColor("#173177");
                    m.put("keyword1", keyword1);

                    TemplateData keyword2 = new TemplateData();
                    keyword2.setValue(getSubString(commodityPriceVo.getLastPx()));
                    keyword2.setColor(getColorString(commodityPriceVo.getPxChangeRate()));
                    m.put("keyword2", keyword2);

                    TemplateData keyword3 = new TemplateData();
                    keyword3.setValue(getSubString(commodityPriceVo.getPxChangeRate())+"%");
                    keyword3.setColor(getColorString(commodityPriceVo.getPxChangeRate()));
                    m.put("keyword3", keyword3);

                    TemplateData remark = new TemplateData();
                    remark.setValue("点击查看最新实时行情");
                    remark.setColor("#173177");
                    m.put("remark", remark);
                    wechatTemplate.setData(m);

                    SendTemplateMessageUtil.sendTemplateMessage(accessToken,wechatTemplate);

                } catch (Exception e) {
                    allMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
                    allMessage.setContent("未找到对应的藏品信息，请输入正确的藏品代码！如：399001");
                    responseMessage = WechatMessageUtil.simpleobject2xml(allMessage);
                    logger.error("用户用户输入错误的行情code，没有查询到相关信息，输入信息为：{}",content);
                }
                break;
            case WechatMessageUtil.MESSAtGE_IMAGE:
                break;
            case WechatMessageUtil.MESSAGE_EVENT:
                String event = map.get("Event");
                if (WechatMessageUtil.MESSAGE_EVENT_UNSUBSCRIBE.equals(event)) {
                    allMessage.setContent("取消关注！");
                }else if(WechatMessageUtil.MESSAGE_EVENT_SUBSCRIBE.equals(event)){
                    allMessage.setContent("欢迎关注postBox公众号，实现一键取货的便捷");
                }else if(WechatMessageUtil.MESSAGE_EVENT_CLICK.equals(event)){
                    //自定义菜单点击事件
                    String eventKey = map.get("EventKey");
                    switch (eventKey){
                        //自选股
//                        case CommonConstants.SOTCK_QUERY_MENU_KEY:
//                            allMessage.setContent("点击键盘图标，输入框内回复藏品代码，即可查看藏品实时行情；如回复399001查询普洱指数。");
//                            break;
//                        case CommonConstants.QUOTES_MAKET_KEY:
//                            break;
//                        case CommonConstants.SELF_SOTCK_KEY:
//                            break;
                    }

                }else if(WechatMessageUtil.MESSAGE_EVENT_CLICK.equals(event)){

                }else if(WechatMessageUtil.MESSAGE_EVENT_VIEW.equals(event)){
                    break;
                }else{
                    allMessage.setContent("推送点击事件，还需先查询出所有自定义菜单的相关信息，然后根据消息类型和事件类型以及事件key值做出不同的响应现在（现已经查询出所有菜单的信息，只连接上业务场景进行模板消息推送）！！");
                }

                allMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
                responseMessage = WechatMessageUtil.simpleobject2xml(allMessage);
                logger.error("\n回复的消息内容为：{}", JSONObject.toJSONString(responseMessage));

            //// TODO: 2017/3/22

        }
        return responseMessage;

    }

    /**
     * 拼装模板信息
     *
     * @param templateId
     * @param toUser
     * @param topColor
     * @param ulr
     * @return
     */
    @Override
    public WxTemplate getWxTemplate(String templateId, String toUser, String topColor, String ulr) {
        return null;
    }

    /**
     * 获取所有的参数值
     *
     * @return
     */
    @Override
    public Map<String, TemplateData> getTemplateDataMap() {
        return null;
    }

    /**
     * 根据模板ID获取对应模板信息
     *
     * @param templateId
     * @param list
     * @return
     */
    @Override
    public PrivateTemplate getTemplateFromTemList(String templateId, List<PrivateTemplate> list) {

        for(PrivateTemplate privateTemplate : list){
            if(templateId.equals(privateTemplate.getTemplate_id())){
                return privateTemplate;
            }
        }
        return null;
    }

    private String getSubString(String str){
        if(StringUtils.isEmpty(str)){
            return "";
        }
        int endIndex = str.indexOf(".");
        return str.substring(0,endIndex+3);
    }

    private String getColorString(String str){
        if(StringUtils.isEmpty(str)){
            return "";
        }
        if("0.000".equals(str)){
            return "#173177";
        }
        String first =  str.substring(0,1);
        if("-".equals(first)){
            return "#26b463";
        }else{
            return "#bd4745";
        }

    }

}
