package cn.datai.gift.web.service;

/**
 * 短消息发送
 * Created by H.CAAHN on 2017/6/19.
 */
public interface SmsSenderService {

    boolean sendCaptcha(String mobileno, String captcha);

}
