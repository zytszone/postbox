package cn.datai.gift.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;

/**
 * 密码箱URL打开
 * Created by H.CAAHN on 2017/5/15.
 */
@Controller
@RequestMapping("/secret")
public class BoxSecretController extends BaseController {
    /**
     * 通过柜台扫码的URL打开链接，服务器端判断权限合法性
     * @param boxId
     * @param mkey
     * @return
     */
    @RequestMapping("/decode")
    public String decode(@RequestParam("boxId") Long boxId, @RequestParam("mkey") String mkey) {
        return null;
    }

    @RequestMapping("/saveBoxMobile")
    public String saveBoxMobile(@RequestParam("boxId") Long boxId, @RequestParam("mobile") String mobile) {
        return null;
    }
}
