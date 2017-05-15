package cn.datai.gift.web.controller;

import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 *密码相关
 * Created by Administrator on 2017/4/26.
 */
@Controller
@RequestMapping("password")
public class PasswordController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(PasswordController.class);

    @Autowired
    private PasswordService passwordService;


    /**
     * 跳转设置密码
     * @return
     */
    @RequestMapping(value ="/setPassword",method = RequestMethod.GET)
    @Auth(needLogin = true,weixinJsAuth = true)
    public String setPassword(){
        return "gift/setPassword";
    }

    @RequestMapping(value ="/paymentPassword")
    @Auth(needLogin = true,weixinJsAuth = true)
    public String paymentPassword(){
        return "gift/paymentPassword";
    }

    /**
     * 设置密码
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value="/savePassword",method = RequestMethod.POST)
    @ResponseBody
    public RespResult savePassword( @ModelAttribute("userInfoId") long userInfoId,
                                    @RequestParam("pwd") String pwd){
        try {
            passwordService.savePwd(userInfoId,pwd);
            return new RespResult(RespCode.SUCCESS,null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("保存密码失败：{}",pwd);
            return new RespResult(RespCode.FAIL,null);
        }
    }

    /**
     * 验证密码
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value = "/checkPassword",method = RequestMethod.POST)
    @ResponseBody
    public RespResult checkPassword(
            @ModelAttribute("userInfoId") long userInfoId,
            @RequestParam("oldPwd") String oldPwd){
        try {
            boolean checkPassword = passwordService.checkPassword(userInfoId, oldPwd);
            return new RespResult(RespCode.SUCCESS,checkPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("验证密码失败，输入的旧密码为：{}",oldPwd);
            return new RespResult(RespCode.FAIL,null);
        }
    }

}
