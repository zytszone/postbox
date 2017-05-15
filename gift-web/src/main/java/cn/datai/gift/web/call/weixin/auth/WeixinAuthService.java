package cn.datai.gift.web.call.weixin.auth;

import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.web.plugin.vo.JsapiTicket;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeixinAuthService {

    /**
     * 获取access_token
     * @param grantType 微信授权类型
     * @param appid 公众号appid
     * @param secret 公众号密码
     * @return
     */
    @GET("cgi-bin/token")
    Call<Object> pullToken(@Query("grant_type") String grantType, @Query("appid") String appid, @Query("secret") String secret);

    /**
     * 获取微信jsticket
     * @param accessToken
     * @param type
     * @return
     */
    @GET("/cgi-bin/ticket/getticket")
    Call<JsapiTicket> getTicket(@Query("access_token") String accessToken, @Query("type") String type);

    /**
     * 获取微信用户信息
     * @param accessToken
     * @param openid
     * @param language
     * @return
     */
    @GET("/sns/userinfo")
    Call<UserWxInfo> callWeixinUserInfo(@Query("access_token") String accessToken, @Query("openid") String openid, @Query("lang") String language);
}
