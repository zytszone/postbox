package cn.datai.gift.web.controller;

import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.service.BoxInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 密码箱URL打开
 * Created by H.CAAHN on 2017/5/15.
 */
@Controller
@RequestMapping("/secret")
public class BoxSecretController extends BaseController {
    /** 日志对象 */
    private static final Logger logger = LoggerFactory.getLogger(BoxSecretController.class);

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private BoxInfoService boxInfoService;

    /**
     * 通过柜台扫码的URL打开链接，服务器端判断权限合法性
     * @param boxId
     * @param mkey
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("/decode")
    public String decode(Model model, @RequestParam("boxId") Long boxId, @RequestParam("mkey") String mkey,
                         HttpServletRequest request, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        try {
            UserInfo userInfo = baseInfoService.queryUserInfoByUserId(userLoginInfo.getUserInfoId());
            // 如果用户未注册，则跳转到注册页面
            if (userInfo == null) {
                return "postbox/register";
            }

            String decode = this.boxInfoService.updateForDecode(boxId, mkey, userInfo);
            model.addAttribute("isSpecial",userInfo.getIsSpecial());
            if (StringUtils.isNotBlank(decode)) {
                model.addAttribute("decode", decode);

                // 快递员
                if ("true".equalsIgnoreCase(userInfo.getIsSpecial())) {
                    String uuid = UUID.randomUUID().toString();
                    model.addAttribute("skey", uuid);
                    model.addAttribute("boxId", boxId);
                    request.getSession().setAttribute("skey", uuid);
                }
                return "postbox/secret";
            }
        }
        catch (Exception ex) {
            logger.error("服务器鉴权失败，系统内部异常", ex);
        }
        return "postbox/secret";
    }

    /**
     * 更新箱子的属主手机号
     * @param boxId
     * @param mobile
     * @param skey
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveBoxMobile",method = RequestMethod.POST)
    @ResponseBody
    public RespResult saveBoxMobile(@RequestParam("boxId") Long boxId, @RequestParam("mobile") String mobile,
                                    @RequestParam("skey") String skey, HttpServletRequest request) {
        try {
            String uuid = (String)request.getSession().getAttribute("skey");
            // 更新箱子的属主手机号
            if (StringUtils.isNotBlank(uuid) && StringUtils.equals(skey, uuid)) {
                this.boxInfoService.updateBoxMobile(boxId, mobile);
                return new RespResult(RespCode.SUCCESS);
            }
        }
        catch (Exception ex) {
            logger.error("服务器鉴权");

        }
        // 无权更新箱子的属主手机号
        return new RespResult(RespCode.FAIL);
    }
}
