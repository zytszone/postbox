package cn.datai.gift.web.controller;

import cn.datai.gift.web.contants.TemplateConstants;
import cn.datai.gift.web.contants.TokenContants;
import cn.datai.gift.web.model.createMenu.MenuManager;
import cn.datai.gift.web.model.queryMenus.ComplexMenus;
import cn.datai.gift.web.model.templateMessage.PrivateTemplate;
import cn.datai.gift.web.model.templateMessage.TemplateData;
import cn.datai.gift.web.model.templateMessage.WxTemplate;
import cn.datai.gift.web.service.WechatService;
import cn.datai.gift.web.util.CheckUtil;
import cn.datai.gift.web.util.createMenu.CreateMenuUtil;
import cn.datai.gift.web.util.createMenu.DeleteMenuUtil;
import cn.datai.gift.web.util.queryMenus.QueryMenuUtil;
import cn.datai.gift.web.util.templateMessage.GetAllPrivateTemplateUtil;
import cn.datai.gift.web.util.templateMessage.SendTemplateMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/21.
 */
@Controller
public class PublicSignalController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value("${wx_token}")
    private String TOKEN;

    @Value("${weixin.appID}")
    private String appid;

    @Value("${button.type}")
    private String buttonType;

    @Autowired
    private WechatService wechatService;


    /**
     * 验证微信服务器
     *
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "/postBox", method = RequestMethod.GET)
    public void wechatService(PrintWriter out, HttpServletResponse response,
                              @RequestParam(value = "signature", required = false) String signature, @RequestParam String timestamp,
                              @RequestParam String nonce, @RequestParam String echostr) {
        if (CheckUtil.checkSignature(TOKEN,signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    /**
     * 公众号简单消息
     * @param out
     * @param request
     * @param response
     */
    @RequestMapping(value = "/postBox", method = RequestMethod.POST)
    public void wechatServicePost(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {

        String accessToken = TokenContants.WEIXIN_TOKEN;

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        if (CheckUtil.checkSignature(TOKEN, signature, timestamp, nonce)) {
            // 调用核心业务类接收消息、处理消息
//            String respMessage = null;
//            try {
//                respMessage = processReqest.process(request,response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            PrintWriter out = response.getWriter();
//            out.print(respMessage);
//            out.close();


            String responseMessage = wechatService.processRequest(accessToken,request);
            out.print(responseMessage);
            out.flush();
        }

    }

    /**
     * 测试调用简单模板消息
     * @param out
     * @param request
     * @param response
     */
    @RequestMapping(value = "/testTemplateMessage", method = RequestMethod.POST)
    @ResponseBody
    public void testTemplateMessage(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {

        String accessToken = TokenContants.WEIXIN_TOKEN;
        WxTemplate wechatTemplate = new WxTemplate();
        wechatTemplate.setTemplate_id(TemplateConstants.TEM_ID);
        wechatTemplate.setTouser("olWxVwxa8OI5X1O1FLyRcKsF-bOs");
        wechatTemplate.setUrl("http://baidu.com");
        wechatTemplate.setTopcolor("#173177");

        Map<String,TemplateData> m = new HashMap<>();

        TemplateData first = new TemplateData();

        first.setValue("xxx");
        first.setColor("#173177");
        m.put("first", first);

//                    大于0    #bd4745        小于0  #26b463

        TemplateData keyword1 = new TemplateData();
        keyword1.setValue("xxxxxxx");
        keyword1.setColor("#173177");
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();
        keyword2.setValue("xxxxxx");
        keyword2.setColor("#173177");
        m.put("keyword2", keyword2);

        TemplateData keyword3 = new TemplateData();
        keyword3.setValue("xxxxxx");
        keyword3.setColor("#173177");
        m.put("keyword3", keyword3);

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
     * 微信公众号创建菜单
     */
    @RequestMapping(value = "createMenu",method = RequestMethod.POST)
    @ResponseBody
    public void createMenu() throws Exception {
        String accessToken = TokenContants.WEIXIN_TOKEN;
        CreateMenuUtil.createMenu(MenuManager.getMenu(buttonType),accessToken);
    }

    /**
     * 删除自定义菜单
     */
    @RequestMapping(value = "deleteMenu",method = RequestMethod.GET)
    public void deleteMenu() throws Exception {
        String accessToken = TokenContants.WEIXIN_TOKEN;
        DeleteMenuUtil.deleteMenu(accessToken);
    }

    /**
     * 查询自定义菜单
     */
    @RequestMapping (value = "queryMeuns",method = RequestMethod.GET)
    public List<ComplexMenus> queryMenus(){
        String accessToken = TokenContants.WEIXIN_TOKEN;
        //获取所有自定义菜单信息：主要是获取菜单中的key值，保存好key值；
        // 根据点击不同的菜单，拿到不同的key值，以key值进行不同的消息处理
        return QueryMenuUtil.queryMenus(accessToken);
    }

    /**
     * 获取所有的模板信息
     * @throws Exception
     */
    @RequestMapping(value = "getAllPrivateTemplate",method = RequestMethod.GET)
    public void getAllPrivateTemplate() throws Exception {
        String accessToken = TokenContants.WEIXIN_TOKEN;
        List<PrivateTemplate> list  = GetAllPrivateTemplateUtil.queryAllPrivateTemplate(accessToken);
    }

}
