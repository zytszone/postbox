package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.enums.BoxExpressStatus;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.service.CustomerInfoService;
import cn.datai.gift.web.service.ExpressmanInfoService;
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
    private CustomerInfoService customerInfoService;

    @Autowired
    private ExpressmanInfoService expressmanInfoService;

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
            TCustomerInfo customerInfo = customerInfoService.queryById(userLoginInfo.getUserInfoId());
            // 如果用户未注册，则跳转到注册页面
            if (customerInfo == null) {
                return "postbox/register";
            }

            // 检查箱子是否存在
            TBoxInfo box = this.boxInfoService.queryById(boxId);
            if (box == null) {
                logger.warn("没有找到指定的箱子：{}", boxId);
                return "postbox/secret";
            }

            // 尝试作为普通用户打开箱子
            TExpressmanInfo exp = null;
            String decode = this.boxInfoService.updateAsNormalUserForDecode(box, mkey, customerInfo);

            // 若此时无法解码，且箱子为空，则尝试作为快递员打开箱子
            if (StringUtils.isBlank(decode) && BoxExpressStatus.EMPTY.name().equalsIgnoreCase(box.getExpressStatus())) {
                exp = expressmanInfoService.queryByCustomerInfoId(customerInfo.getCustomerInfoId());
                if (exp != null) {
                    decode = boxInfoService.updateAsExpressmanForDecode(box, mkey, exp);
                }
            }

            if (StringUtils.isNotBlank(decode)) {
                model.addAttribute("decode", decode);
                if (exp != null) {
                    String uuid = UUID.randomUUID().toString();
                    model.addAttribute("boxId", boxId);
                    model.addAttribute("isSpecial", "true");
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
     * 更新箱子的收件人手机号
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
                this.boxInfoService.updateBoxMobilePhone(boxId, mobile);
                return new RespResult(RespCode.SUCCESS);
            }
        }
        catch (Exception ex) {
            logger.error("更新箱子的收件人手机号失败", ex);
        }
        // 无权更新箱子的属主手机号
        return new RespResult(RespCode.FAIL);
    }
}
