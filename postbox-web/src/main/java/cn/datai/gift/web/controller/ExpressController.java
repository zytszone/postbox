package cn.datai.gift.web.controller;

import cn.datai.gift.utils.RespResult;
import cn.datai.gift.web.plugin.annotation.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/8/14.
 */
@Controller
@RequestMapping("express")
public class ExpressController extends BaseController {

    private static final Logger logger  = LoggerFactory.getLogger(ExpressController.class);

    /**
     * 快递领取历史
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @GetMapping("expressTakeInfo")
    public String toTakeInfo(@ModelAttribute("customerInfoId") Long customerInfoId){
        return "postbox/expressTakeInfo";
    }

    /**
     * 查询快递领取历史记录
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @PostMapping("expressTakeInfo")
    @ResponseBody
    public RespResult getTakeInfo(@ModelAttribute("customerInfoId") Long customerInfoId){
        return null;
    }




}
