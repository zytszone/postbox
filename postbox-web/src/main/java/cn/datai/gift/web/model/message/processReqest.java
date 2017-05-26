package cn.datai.gift.web.model.message;


import cn.datai.gift.web.util.message.WechatMessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class processReqest {
     public static String process(HttpServletRequest request) throws Exception{
         @SuppressWarnings("unchecked")
         Map<String, String> map = WechatMessageUtil.xmlToMap(request);
         String result = "";
         String msgType = map.get("MsgType");
         String respContent = "";
         //文本消息
         if (msgType.equals("text")) {
//            respContent = TulingRobot.robot(map.get("Content"));
//            TextMessage textMessage = Map2Bean.parseText(map,respContent);
//            result = Bean2ResponseXML.textMessageToXml(textMessage);
        }
        //图片消息
        else if (msgType.equals("image")) {
            respContent = "";
            return null;
        }
        //声音消息
        else if (msgType.equals("voice")) {
            respContent = "";
            return null;
        }
        //视频消息
        else if (msgType.equals("video")) {
            respContent = "";
            return null;
        }
        //地理位置
        else if (msgType.equals("location")) {
            respContent = "";
            return null;
        }
        //事件类型
        else if (msgType.equals("event")) {
            String eventType = map.get("Event");
            //订阅
            if (eventType.equals("subscribe")) {
                respContent = "欢迎订阅我的公众号！";
//                TextMessage textMessage = Map2Bean.parseText(map,respContent);
//                result = Bean2ResponseXML.textMessageToXml(textMessage);
            }
            //取消订阅
            else if (eventType.equals("unsubscribe")) {
                // TODO
                return null;
            }
            //点击菜单
            else if (eventType.equals("CLICK")) {
                // TODO 
                return null;
            }
        }
        return result;
    }
}