package cn.datai.gift.web.controller;


import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.web.contants.SessionAttrs;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/3/3.
 */
public class BaseController {

    @Autowired
    private BaseInfoService baseInfoService;

    @ModelAttribute("userLoginInfo")
    public static UserLoginInfo getUserLoginInfo(HttpSession httpSession) {
        UserLoginInfo userLoginInfo = (UserLoginInfo) httpSession.getAttribute(SessionAttrs.USER_LOGIN_INFO);
        return userLoginInfo;
    }

    public static void setUserLoginInfo(HttpSession httpSession, UserLoginInfo userLoginInfo) {
        httpSession.setAttribute(SessionAttrs.USER_LOGIN_INFO, userLoginInfo);
    }


    public void setUserWxInfo(HttpSession httpSession, UserWxInfo userWxInfo) {
        httpSession.setAttribute(SessionAttrs.USER_WX_INFO, userWxInfo);
    }

    @ModelAttribute("customerInfoId")
    public long getUserInfoId(HttpSession httpSession) {
        UserLoginInfo userLoginInfo = (UserLoginInfo) httpSession.getAttribute(SessionAttrs.USER_LOGIN_INFO);
        if (userLoginInfo == null) {
            return 0L;
        }
        return userLoginInfo.getCustomerInfoId();
    }

    @ModelAttribute("customerInfo")
    public TCustomerInfo getTCustomerInfo(HttpSession httpSession) {
        UserLoginInfo userLoginInfo = (UserLoginInfo) httpSession.getAttribute(SessionAttrs.USER_LOGIN_INFO);
        if (userLoginInfo == null) {
            return null;
        }
        return baseInfoService.queryTCustomerInfo(userLoginInfo.getCustomerInfoId());
    }

    @ModelAttribute("userWxInfo")
    public UserWxInfo getUserWxInfo(HttpSession httpSession) {
        return  (UserWxInfo) httpSession.getAttribute(SessionAttrs.USER_WX_INFO);
    }

}
