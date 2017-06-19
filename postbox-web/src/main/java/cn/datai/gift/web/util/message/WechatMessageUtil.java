package cn.datai.gift.web.util.message;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WechatMessageUtil {
    // 各种消息类型,除了扫带二维码事件
    /**
     * 文本消息
     */
    public static final String MESSAGE_TEXT = "text";
    /**
     * 图片消息
     */
    public static final String MESSAtGE_IMAGE = "image";
    /**
     * 图文消息
     */
    public static final String MESSAGE_NEWS = "news";
    /**
     * 语音消息
     */
    public static final String MESSAGE_VOICE = "voice";
    /**
     * 视频消息
     */
    public static final String MESSAGE_VIDEO = "video";
    /**
     * 小视频消息
     */
    public static final String MESSAGE_SHORTVIDEO = "shortvideo";
    /**
     * 地理位置消息
     */
    public static final String MESSAGE_LOCATION = "location";
    /**
     * 链接消息
     */
    public static final String MESSAGE_LINK = "link";
    /**
     * 事件推送消息
     */
    public static final String MESSAGE_EVENT = "event";
    /**
     * 事件推送消息中,事件类型，subscribe(订阅)
     */
    public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
    /**
     * 事件推送消息中,事件类型，unsubscribe(取消订阅)
     */
    public static final String MESSAGE_EVENT_UNSUBSCRIBE = "unsubscribe";
    /**
     * 事件推送消息中,上报地理位置事件
     */
    public static final String MESSAGE_EVENT_LOCATION_UP = "LOCATION";
    /**
     * 事件推送消息中,自定义菜单事件,点击菜单拉取消息时的事件推送
     */
    public static final String MESSAGE_EVENT_CLICK = "CLICK";
    /**
     * 事件推送消息中,自定义菜单事件,点击菜单跳转链接时的事件推送
     */
    public static final String MESSAGE_EVENT_VIEW = "VIEW";

    /**
     *扫码
     */
    public static final String MESSAGE_EVENT_SCAN = "SCAN";

    /**
     * 将xml转化为Map集合
     *
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = reader.read(ins);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        Element root = doc.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        try {
            ins.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return map;
    }


    /**

     * @param obj 要转换的对象

     * @param child 去除包路径名，如果不写会生产com.**.**...

     * @return

     */

    public static String simpleobject2xml(Object obj,Object ... child) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", obj.getClass());
        for(int i=0;i < child.length; i++){
            Object objs =child[i];
            xStream.alias(objs.getClass().getSimpleName(), objs.getClass());
        }
        String xml = xStream.toXML(obj);
        return xml;

    }

    /**
     * 将XML转为对象
     * @param xml
     * @param node 节点实体类，如果xml有多个实体则实例化多少实体
     * @return

     */

    public static Object simplexml2object(String xml, Object... node) {
        XStream xStream = new XStream(new DomDriver());
        for (int i = 0; i < node.length; i++) {
            Object objs = node[i];
            xStream.alias(objs.getClass().getSimpleName(), objs.getClass());
        }
        Object reobj = xStream.fromXML(xml);
        return reobj;

    }




}