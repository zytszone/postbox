package cn.datai.gift.web.service;

/**
 * 短消息发送
 * Created by H.CAAHN on 2017/6/19.
 */
public interface SmsSenderService {
    /**
     * 发送短信验证码
     * @param mobileno
     * @param captcha
     * @return
     */
    boolean sendCaptcha(String mobileno, String captcha);

    /**
     * 发送快递抵达通知
     * @param mobileno
     * @param address
     * @param boxCode
     * @return
     */
    boolean sendReached(String mobileno, String address, String boxCode);
}
