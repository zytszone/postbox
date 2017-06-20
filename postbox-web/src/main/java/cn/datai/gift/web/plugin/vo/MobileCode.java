package cn.datai.gift.web.plugin.vo;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MobileCode {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码过期时间
     */
    private Long dateTime;

    public MobileCode(){}

    public MobileCode(String mobile,String code,Long dateTime){
        this.mobile = mobile;
        this.code = code;
        this.dateTime = dateTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "MobileCode{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
