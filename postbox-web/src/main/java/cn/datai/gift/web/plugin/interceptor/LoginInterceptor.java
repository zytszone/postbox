package cn.datai.gift.web.plugin.interceptor;


import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.persist.po.UserWxRelt;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.contants.PhotoContants;
import cn.datai.gift.web.contants.SessionAttrs;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.plugin.vo.WeixinUserToken;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.utils.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/6.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Value("${weixin.appID}")
    private String APPID;

    @Value("${weixin.appsecret}")
    private String SECRET;

    @Autowired
    private BaseInfoService baseInfoService;

    /**
     * 通过注解进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        //没有注解或者不需要用户授权登录
        if(null == auth || !auth.needLogin()){
            return true;
        }

        //有注解需要验证是否需要用户授权
        return wechatAuthHandler(auth,request,response);
    }

    /**
     *根据不同的注解方式分别校验
     * @param auth
     * @param request
     * @param response
     * @return
     */
    private boolean wechatAuthHandler(Auth auth,HttpServletRequest request,HttpServletResponse response) throws Exception {
        boolean result = false;

        //获取session
        UserLoginInfo userLoginInfo = (UserLoginInfo)(request.getSession().getAttribute(SessionAttrs.USER_LOGIN_INFO));

        //获取请求中的参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        if(null == userLoginInfo || null == userLoginInfo.getWeixinOpenId()) {
            //情况一：无用户信息可能有两种情况，一种是用户第一次访问，无授权信息， 需要重定向去授权
            //情况二：另一种是可能用户的页面已被重定向至微信授权页面，微信再重定向回本系统的页面，但会带上授权信息例如code，这时不能把用户重定向，否则会死循环，而要根据拿到的code去获取用户openid，成功后放入session
            //如果包含参数code和state，说明微信服务器对用户已做了授权操作判断，然后微信服务器给客户做了个跳转，并带上能获取用户令牌的code参数及自定义参数state
            if (parameterMap.containsKey("code") && parameterMap.containsKey("state")) {
                return haveCodeAndState(auth,request,response);
            }else{
                return haveNoCodeAndNoState(request,response);
            }
        }else{
            boolean needPhone = auth.needPhone();
            if(!needPhone){
                return true;
            }
            if(StringUtils.isEmpty(userLoginInfo.getPhone())){
                String redirect = getPath(request) + "main/register";
                response.sendRedirect(redirect + "?redirecturl=" + URLEncoder.encode(getWholeURL(request),"UTF-8"));
                return false;
            }
            //SESSION中有值
            result = true;
        }

        return result;
    }


    /**
     * 情况一
     * @param request
     * @param response
     * @return
     */
    private boolean haveNoCodeAndNoState(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //此处为第一种情况
        if (this.isAjaxRequest(request)) {
            //返回前端用户未登录的状态码，前端处理：强制刷新当前页面，目的重新获取微信授权信息
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONObject.toJSONString(new RespResult(RespCode.USER_NOT_LOGIN)));
        }else {
            String authPage = buildWeixinOAuthUrl(getWholeURL(request), "null", request);
            logger.debug("sessionid:{}会话无用户任何信息或微信账号信息， 重定向至微信认证接口", request.getRequestedSessionId());
            response.sendRedirect(authPage);
        }
        return false;
    }

    /**
     * 情况二
     * @param request
     * @param response
     * @return
     */
    private boolean haveCodeAndState(Auth auth, HttpServletRequest request,HttpServletResponse response) throws Exception {

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.debug("重定向回本系统包含微信认证必要的参数 code:{}, state:{}", code, state);
        WeixinUserToken weixinUserToken = getWeixinUserAuthInfo(APPID, SECRET, code);
        if (weixinUserToken != null && weixinUserToken.getOpenid() != null) {
            logger.debug("成功获取微信用户令牌 {}", JSONObject.toJSONString(weixinUserToken));
            //成功获取令牌后，使用令牌去获取用户基本信息
            UserWxInfo weixinUserInfo = getWeixinUserInfo(weixinUserToken);
            weixinUserInfo.setAppid(APPID);

            //数据库插入（或更新）用户微信信息,插入微信用户与基本用户关联信息，插入用户（包含特别用户）基本信息
            baseInfoService.insertOrUpDate(weixinUserInfo);
            //存入session
            UserLoginInfo userLoginInfo = this.cacheWeixinUserInfo(weixinUserInfo, request.getSession());
            if(null == userLoginInfo){
                return false;
            }else{
                if (!auth.needPhone()) {
                    return true;
                }
                if(StringUtils.isEmpty(userLoginInfo.getPhone())){
                    String redirect = getPath(request) + "main/register";
                    response.sendRedirect(redirect + "?redirecturl=" + URLEncoder.encode(getWholeURL(request),"UTF-8"));
                    return false;
                }
            }
            logger.info("微信用户登录成功，登录信息：{}", request.getSession().getAttribute(SessionAttrs.USER_LOGIN_INFO));

            return true;
        } else {
            if (this.isAjaxRequest(request)) {
                //返回前端用户未登录的状态码，前端处理：强制刷新当前页面，目的重新获取微信授权信息
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSONObject.toJSONString(new RespResult(RespCode.USER_NOT_LOGIN)));
            }else {
                //用户会话过期， 但用户依然访问原附带code和state参数的链接，这时使用code去腾讯获取令牌时会返回无效令牌
                //将重新去获取code，并在之后重走之前申请token的流程
                response.sendRedirect(getWholeURL(request));
            }
            return false;
        }
    }

    /**
     * 构建微信认证地址
     * @param wholeURL 用户尝试请求的地址
     * @param state
     * @return
     */
    private String buildWeixinOAuthUrl(String wholeURL, String state, HttpServletRequest request) throws UnsupportedEncodingException {
        StringBuffer weixinAuthUrl = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        weixinAuthUrl.append(this.APPID)
                .append("&redirect_uri=")
                .append(URLEncoder.encode(wholeURL, "utf-8"))
                .append("&response_type=code&scope=snsapi_userinfo&state=")
                .append(state)
                .append("#wechat_redirect");

        return weixinAuthUrl.toString();
    }

    /**
     * 获取完整的url 包括请求参数, 剔除 code 和 state 参数
     * @return String
     */
    private String getWholeURL(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
        parameterMap.keySet().stream().filter(key -> !"code".equals(key) && !"state".equals(key)).forEach(key -> uriComponentsBuilder.queryParam(key, parameterMap.get(key)));
        return uriComponentsBuilder.build().toString();
    }

    /**
     * 获取能够得到用户信息的令牌
     *
     * @param appid
     * @param secret
     * @param code
     * @return
     */
    private WeixinUserToken getWeixinUserAuthInfo(String appid, String secret, String code) {
        WeixinUserToken weixinUserToken = new WeixinUserToken();
        String o_auth_openid_url = "https://api.weixin.qq" +
                ".com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        String requestUrl = o_auth_openid_url.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code);
        logger.debug("本地服务器向微信服务器发送获取用户令牌的请求：" + requestUrl);
        JSONObject jsonObject = null;
        try {
            jsonObject = httpClientGet(requestUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {
            jsonObject = StrUtil.convertToCamelKey(jsonObject);
            weixinUserToken = JSONObject.toJavaObject(jsonObject, WeixinUserToken.class);
            logger.debug("获取微信服务器发来的获取用户信息的令牌:{}", JSONObject.toJSONString(weixinUserToken));
        }


        return weixinUserToken;

    }

    public JSONObject httpClientGet(String requestUrl) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = new JSONObject();
        try {
            response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new Exception("失败!");
            }

            System.out.println(response.getStatusLine().toString());
            jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(), "utf-8"));
            logger.debug("获取用户令牌:{}", jsonObject.toJSONString());
            response.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 通过令牌获取用户信息
     *
     * @param weixinUserAuthInfo 微信用户令牌
     * @return
     */
    private UserWxInfo getWeixinUserInfo(WeixinUserToken weixinUserAuthInfo) {
        UserWxInfo weixinUserInfo = new UserWxInfo();
        StringBuffer stringBuffer = new StringBuffer("https://api.weixin.qq.com/sns/userinfo?access_token=");
        stringBuffer.append(weixinUserAuthInfo.getAccessToken());
        stringBuffer.append("&openid=");
        stringBuffer.append(weixinUserAuthInfo.getOpenid());
        stringBuffer.append("&lang=zh_CN");
        logger.debug("请求获取openid:{} 的用户信息", weixinUserAuthInfo.getOpenid());
        JSONObject jsonObject = null;
        try {
            jsonObject = httpClientGet(stringBuffer.toString());
            logger.debug("获取微信服务器发来用户信息:{}", jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {
            jsonObject = StrUtil.convertToCamelKey(jsonObject);
            weixinUserInfo = JSONObject.toJavaObject(jsonObject, UserWxInfo.class);
        }

        return weixinUserInfo;

    }

    /**
     * 将微信用户信息缓存入session中
     * @param weixinUserInfo @See {@link UserWxInfo}
     * @param session        @See {@link HttpSession}
     * @return boolean
     */
    private UserLoginInfo cacheWeixinUserInfo(UserWxInfo weixinUserInfo, HttpSession session) {

        session.setAttribute(SessionAttrs.USER_WX_INFO, weixinUserInfo);//微信信息放入缓存

        //根据微信信息查询用户信息
        UserWxRelt userWxRelt = baseInfoService.queryUserWxReltByUnionId(weixinUserInfo.getUnionid());

        UserLoginInfo userLoginInfo = new UserLoginInfo();
        if(null == userWxRelt){
            //用户和微信不存在关系说明该用户还没有注册

            //开始注册
            UserInfo userInfo = userWxInfo2UserInfo(weixinUserInfo);
            baseInfoService.insertUserInfo(userInfo);//用户

            //微信用户信与基本用户信息关联信息
            baseInfoService.insertUserWxRelt(this.assemblyUserWxRelt(weixinUserInfo.getUnionid(),userInfo.getUserInfoId()));//用户与微信关系表

            userLoginInfo.setUserInfoId(userInfo.getUserInfoId());//用户Id
        }else{
            //关系存在
            UserInfo userInfo = baseInfoService.queryUserInfo(userWxRelt.getUserInfoId());

            userLoginInfo.setPhone(userInfo.getMobilePhone());
            userLoginInfo.setIsSpecial(userInfo.getIsSpecial());
            userLoginInfo.setUserInfoId(userInfo.getUserInfoId());//用户Id
            session.setAttribute(SessionAttrs.USER_LOGIN_INFO, userLoginInfo);
        }

        userLoginInfo.setAppId(this.APPID);//appID
        userLoginInfo.setUnionId(weixinUserInfo.getUnionid());//unionId
        userLoginInfo.setWeixinOpenId(weixinUserInfo.getOpenid());//openId

        session.setAttribute(SessionAttrs.USER_LOGIN_INFO, userLoginInfo);

        return userLoginInfo;
    }


    /**
     * 判断是否是ajax请求
     * @param request
     * @return
     */
    private static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) {
            return true;
        }else
            return false;
    }

    /**
     * 用户信息的基本信息目前我们只获取用户第一次微信进入时的基本信息，
     * 在此之后用户再更新用户头像昵称等信息时，用户微信信息表和session中的用户微信信息会更新，但是用户基本信息表不会再更新
     * @param userWxInfo
     * @return
     */
    private static UserInfo userWxInfo2UserInfo(UserWxInfo userWxInfo){
        UserInfo userInfo = new UserInfo();
        userInfo.setCountry(userWxInfo.getCountry());
        userInfo.setProvince(userWxInfo.getProvince());
        userInfo.setCity(userWxInfo.getCity());
        userInfo.setNickname(userWxInfo.getNickname());
        userInfo.setSex(userWxInfo.getSex());
        userInfo.setHeadImgPath(userWxInfo.getUnionid()+ PhotoContants.FILENAME_SUFFIX);//只保存用户头像名称（unionId.jpg）
        userInfo.setRegisterTime(new Date());
        //...
        return userInfo;
    }

    private static UserWxRelt assemblyUserWxRelt(String unionId, Long userInfoId){
        UserWxRelt userWxRelt = new UserWxRelt();
        userWxRelt.setUnionid(unionId);
        userWxRelt.setUserInfoId(userInfoId);
        userWxRelt.setCreateTime(new Date());
        return userWxRelt;
    }

    private static String getPath(HttpServletRequest request){
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            basePath = basePath + ":" + request.getServerPort();
        }
        basePath = basePath + path + "/";
        return basePath;
    }






}
