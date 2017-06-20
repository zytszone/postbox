package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.MobileCode;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.service.SmsSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;
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

    @Autowired
    private SmsSenderService smsSenderService;

    @RequestMapping(value = "/register")
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = false)
    public String toIndex(Model model,String redirecturl) throws UnsupportedEncodingException {
        model.addAttribute("redirecturl",redirecturl);
        return "postbox/register";
    }

    /**
     * 注册（用户、快递员）
     * @param phone 手机号
     * @param isSpecial true:注册成为快递员，false:注册成为普通用户
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true, needPhone = false)
    @RequestMapping(value = "/bind",method = RequestMethod.POST)
    @ResponseBody
    public RespResult bind(@RequestParam("phone") String phone,
                           @RequestParam("isSpecial") String isSpecial,
                           @ModelAttribute("userWxInfo")UserWxInfo userWxInfo,
                           @ModelAttribute("userLoginInfo")UserLoginInfo userLoginInfo,
                           @RequestParam("redirecturl") String redirecturl,
                           @RequestParam("code") String code,
                           HttpServletRequest request){
        try {
            RespResult respResult = checkParams(phone, isSpecial);
            if(!respResult.getCode().equals(RespCode.SUCCESS.getCode())){
                return new RespResult(RespCode.FAIL,"注册参数错误");
            }
            //判断验证是否正确是不是超时
            MobileCode mobileCode = userLoginInfo.getMobileCode();
            if(!checkCode(mobileCode,phone,code)){
                return new RespResult(RespCode.FAIL,"手机号或验证码错误");
            }

            //绑定手机号操作
            TCustomerInfo tCustomerInfo = this.baseInfoService.queryTCustomerInfo(userLoginInfo.getCustomerInfoId());
            if(null == tCustomerInfo){
                return new RespResult(RespCode.FAIL,"没有找到该用户");
            }
            if(!StringUtils.isEmpty(tCustomerInfo.getMobilePhone())){
                return new RespResult(RespCode.FAIL,"您已绑定手机号，不能重复绑定");
            }

            TCustomerInfo tCustomerInfo1 = this.baseInfoService.queryTCustomerInfoIdByPhone(phone);
            if(null != tCustomerInfo1){
                return new RespResult(RespCode.FAIL,"改手机号已被绑定");
            }


            tCustomerInfo.setMobilePhone(phone);

            if("true".equals(isSpecial)){
                //快递员信息表中插入数据
                TExpressmanInfo tExpressmanInfo = new TExpressmanInfo();
                tExpressmanInfo.setCustomerInfoId(tCustomerInfo.getCustomerInfoId());
                tExpressmanInfo.setApplytime(new Date());
                tExpressmanInfo.setStatus("NORMAL");
            }

            baseInfoService.updateTCustomerInfo(tCustomerInfo);

            return new RespResult(RespCode.SUCCESS,redirecturl);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("注册失败，错误信息：{}",e);
            return new RespResult(RespCode.FAIL,"注册绑定失败");
        }

    }

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/smsSend",method = RequestMethod.POST)
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = false)
    @ResponseBody
    public RespResult smsSend(@RequestParam("mobile") String mobile,@ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo){
        try {
            if(!isMobileNO(mobile)){
                return new RespResult(RespCode.FAIL,"手机号输入错误");
            }
            String code = getRandomCode();//验证码
            boolean result = smsSenderService.sendCaptcha(mobile,code);//发送短信

            if(result){
                MobileCode mobileCode = new MobileCode();
                mobileCode.setMobile(mobile);//手机号
                mobileCode.setCode(code);//验证码
                mobileCode.setDateTime(System.currentTimeMillis() + 2 * 60 * 1000);//设置为两分钟超时
                userLoginInfo.setMobileCode(mobileCode);//存放session中
                return new RespResult(RespCode.SUCCESS,"发送成功");
            }else{
                return new RespResult(RespCode.FAIL,"短信发送失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送短信失败，错误信息：{}",mobile);
            return new RespResult(RespCode.FAIL,"发送短息验证码失败");
        }
    }

    /**
     * 跳转扫码页面
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value = "/scanqrcode" ,method = RequestMethod.GET)
    public String scanqrcode(){
        return "postbox/scanqrcode";
    }


    /**
     * 跳转密码
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value = "/getSecret" ,method = RequestMethod.GET)
    public String getSecret(){
        return "postbox/secret";
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
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * 获取6位随机数字字符串
     * @return
     */
    private static String getRandomCode(){
        String str="0123456789";
        StringBuilder sb=new StringBuilder(6);
        for(int i=0;i<6;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 校验手机验证码是否正确是否超时
     * @param mobileCode
     * @param code
     * @return
     */
    private static boolean checkCode(MobileCode mobileCode,String mobile,String code){
        if(null == mobileCode || StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)){
            return false;
        }
        if(!mobileCode.getCode().equals(code)){
            return false;
        }
        if(!mobileCode.getMobile().equals(mobile)){
            return false;
        }
        Long periodTime = System.currentTimeMillis() - mobileCode.getDateTime();
        if(periodTime > 2 * 60 * 1000){
            return false;
        }

        return true;
    }


}
