package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.TExpressTakeInfo;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.service.ExpressTakeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
@Controller
@RequestMapping("express")
public class ExpressController extends BaseController {

    private static final Logger logger  = LoggerFactory.getLogger(ExpressController.class);

    @Autowired
    private ExpressTakeInfoService expressTakeInfoService;

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
    @PostMapping("getTakeInfoList")
    @ResponseBody
    public RespResult getTakeInfoList(@ModelAttribute("customerInfoId") Long customerInfoId){

        try {
            List<TExpressTakeInfo> tExpressTakeInfos = this.expressTakeInfoService.queryTExpressTakeInfo(customerInfoId);
            return new RespResult(RespCode.SUCCESS,tExpressTakeInfos);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户快递领取历史记录发生异常,错误信息：{}",e);
            return new RespResult(RespCode.FAIL,"查询领取历史失败");
        }
    }




}
