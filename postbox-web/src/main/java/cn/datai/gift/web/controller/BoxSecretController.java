package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.service.BoxInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

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
    @RequestMapping("/decode")
    public String decode(Model model, @RequestParam("boxId") Long boxId, @RequestParam("mkey") String mkey,
                         HttpServletRequest request) {
        try {
            UserInfo userInfo = baseInfoService.queryUserInfoByUnionId("");
            // 如果用户未注册，则跳转到注册页面
            if (userInfo == null) {
                return "";
            }

            String decode = this.boxInfoService.updateForDecode(boxId, mkey, userInfo);
            if (StringUtils.isNotBlank(decode)) {
                model.addAttribute("decode", boxId);

                // 快递员
                if ("true".equalsIgnoreCase(userInfo.getIsSpecial())) {
                    String uuid = UUID.randomUUID().toString();
                    model.addAttribute("skey", uuid);
                    request.getSession().setAttribute("skey", uuid);
                }
                return "";
            }
        }
        catch (Exception ex) {
            logger.error("服务器鉴权失败，系统内部异常", ex);
        }
        return null;
    }

    /**
     * 更新箱子的属主手机号
     * @param boxId
     * @param mobile
     * @param skey
     * @param request
     * @return
     */
    @RequestMapping("/saveBoxMobile")
    public String saveBoxMobile(@RequestParam("boxId") Long boxId, @RequestParam("mobile") String mobile,
                                @RequestParam("skey") String skey, HttpServletRequest request) {
        try {
            String uuid = (String)request.getSession().getAttribute("skey");
            // 更新箱子的属主手机号
            if (StringUtils.isNotBlank(uuid) && StringUtils.equals(skey, uuid)) {
                this.boxInfoService.updateBoxMobile(boxId, mobile);
                return "";
            }
        }
        catch (Exception ex) {
            logger.error("服务器鉴权");
        }
        // 无权更新箱子的属主手机号
        return "";
    }
}
