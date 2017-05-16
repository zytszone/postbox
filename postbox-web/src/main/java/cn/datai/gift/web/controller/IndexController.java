package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.persist.po.UserWxRelt;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.contants.PhotoContants;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.service.BaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping(value = "/main")
public class IndexController extends BaseController{

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private BaseInfoService baseInfoService;

    @RequestMapping(value = "/toIndex")
    @Auth(needLogin = true,weixinJsAuth = true)
    public String toIndex(Model model){
        return "postbox/index";
    }

    /**
     * 注册（用户、快递员）
     * @param phone 手机号
     * @param isSpecial true:注册成为快递员，false:注册成为普通用户
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    @ResponseBody
    public RespResult signIn(@RequestParam("phone") String phone, @RequestParam("isSpecial") String isSpecial, @ModelAttribute("userWxInfo")UserWxInfo userWxInfo){
        try {
            RespResult respResult = checkParams(phone, isSpecial);
            if(!respResult.getCode().equals(RespCode.SUCCESS.getCode())){
                return respResult;
            }
            UserInfo userInfo = this.baseInfoService.queryUserInfoByPhone(phone);
            if(null != userInfo){
                return new RespResult(RespCode.FAIL,"该手机号已注册");
            }

            //开始注册
            userInfo = userWxInfo2UserInfo(userWxInfo);
            userInfo.setMobilePhone(phone);
            userInfo.setIsSpecial(isSpecial);

            baseInfoService.insertUserInfo(userInfo);//用户

            //微信用户信与基本用户信息关联信息
            baseInfoService.insertUserWxRelt(this.assemblyUserWxRelt(userWxInfo.getUnionid(),userInfo.getUserInfoId()));//用户与微信关心表

            return new RespResult(RespCode.SUCCESS,"注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("注册失败，错误信息：{}",e);
            return new RespResult(RespCode.FAIL,"注册发生异常");
        }

    }


    /**
     * 验证注册参数
     * @param phone
     * @param isSpecial
     * @return
     */
    public static RespResult checkParams(String phone,String isSpecial){
      if(!isMobileNO(phone)){
          return new RespResult(RespCode.FAIL,"手机号输入错误");
      }
        if((!"true".equals(isSpecial)) && (!"false".equals(isSpecial))){
            return new RespResult(RespCode.FAIL,"请选择成为用户还是快递员");
        }
        return new RespResult(RespCode.SUCCESS);
    }

    /**
     * 验证手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        if(StringUtils.isEmpty(mobiles)){
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
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


}
