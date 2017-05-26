package cn.datai.gift.web.service;


import cn.datai.gift.web.model.templateMessage.PrivateTemplate;
import cn.datai.gift.web.model.templateMessage.TemplateData;
import cn.datai.gift.web.model.templateMessage.WxTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
public interface WechatService {
    String processRequest(String sccessToken, HttpServletRequest request);


    /**
     * 拼装模板信息
     * @param templateId
     * @param toUser
     * @param topColor
     * @param ulr
     * @return
     */
    WxTemplate getWxTemplate(String templateId, String toUser, String topColor, String ulr);

    /**
     *获取所有的参数值
     * @return
     */
    Map<String,TemplateData> getTemplateDataMap();

    /**
     * 根据模板ID获取对应模板信息
     * @param templateId
     * @param list
     * @return
     */
    PrivateTemplate getTemplateFromTemList(String templateId, List<PrivateTemplate> list);
}
