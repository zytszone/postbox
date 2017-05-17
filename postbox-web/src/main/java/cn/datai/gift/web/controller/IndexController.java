package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.persist.po.UserWxRelt;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.contants.PhotoContants;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/register")
    @Auth(needLogin = true,weixinJsAuth = true)
    public String toIndex(Model model){
        return "postbox/register";
    }

    /**
     * 注册（用户、快递员）
     * @param phone 手机号
     * @param isSpecial true:注册成为快递员，false:注册成为普通用户
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value = "/bind",method = RequestMethod.POST)
    @ResponseBody
    public RespResult bind(@RequestParam("phone") String phone,
                             @RequestParam("isSpecial") String isSpecial,
                             @ModelAttribute("userWxInfo")UserWxInfo userWxInfo,
                             @ModelAttribute("userLoginInfo")UserLoginInfo userLoginInfo, String redirecturl){
        try {
            RespResult respResult = checkParams(phone, isSpecial);
            if(!respResult.getCode().equals(RespCode.SUCCESS.getCode())){
                return new RespResult(RespCode.FAIL,"注册参数错误");
            }

            //绑定手机号操作
            UserInfo userInfo = this.baseInfoService.queryUserInfo(userLoginInfo.getUserInfoId());
            if(null == userInfo){
                return new RespResult(RespCode.FAIL,"没有找到该用户");
            }
            if(!StringUtils.isEmpty(userInfo.getMobilePhone())){
                return new RespResult(RespCode.FAIL,"您已绑定手机号，不能重复绑定");
            }

            userInfo.setMobilePhone(phone);
            userInfo.setIsSpecial(isSpecial);

            baseInfoService.updateUserInfo(userInfo);

            return new RespResult(RespCode.SUCCESS,redirecturl);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("注册失败，错误信息：{}",e);
            return new RespResult(RespCode.FAIL,"注册绑定失败");
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


}
