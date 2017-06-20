package cn.datai.gift.web.plugin.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/6.
 */
public class UserLoginInfo implements Serializable {

    /**
     * 微信APPID
     */
    private String appId;

    /**
     * 微信openID
     */
    private String weixinOpenId;

    /**
     * 用户信息Id
     */
    private Long customerInfoId;

    /**
     * 微信unionId
     */
    private String unionId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * true：快递员，false:普通用户
     */
    private String isSpecial;

    /**
     * 验证码信息
     */
    private MobileCode mobileCode;


    public MobileCode getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(MobileCode mobileCode) {
        this.mobileCode = mobileCode;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWeixinOpenId() {
        return weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }

    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "appId='" + appId + '\'' +
                ", weixinOpenId='" + weixinOpenId + '\'' +
                ", customerInfoId=" + customerInfoId +
                ", unionId='" + unionId + '\'' +
                ", phone='" + phone + '\'' +
                ", isSpecial='" + isSpecial + '\'' +
                ", mobileCode=" + mobileCode +
                '}';
    }
}
