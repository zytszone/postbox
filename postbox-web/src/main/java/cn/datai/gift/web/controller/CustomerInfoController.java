package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.contants.TokenContants;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.service.CustomerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户信息管理
 * Created by H.CAAHN on 2017/6/19.
 */
@Controller
@RequestMapping("customer/")
public class CustomerInfoController extends BaseController {
    @Autowired
    private BoxInfoService      boxInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoController.class);

    /**
     * 待领快递
     * @param model
     * @param userLoginInfo
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("toReceivingList")
    public String toReceivingList(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        try {
            TCustomerInfo customerInfo = this.customerInfoService.queryById(userLoginInfo.getCustomerInfoId());
            List<TBoxInfo> dataList = this.boxInfoService.queryByMobilePhone(customerInfo.getMobilePhone());
            model.addAttribute("dataList", dataList);
        }
        catch (Exception ex) {
            logger.error("获取待领快件信息失败", ex);
        }
        return "/postbox/toReceivingList";
    }

    /**
     * 个人中心/我的主页
     * @param model
     * @param userLoginInfo
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("myHomePage")
    public String myHomePage(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        TCustomerInfo customerInfo = this.customerInfoService.queryById(userLoginInfo.getCustomerInfoId());
        model.addAttribute("customerInfo",customerInfo);
        return "/postbox/myHomePage";
    }

    /**
     * 我的快递/替我待领
     * @param model
     * @param userLoginInfo
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("forMeLead")
    public String forMeLead(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        return "/postbox/forMeLead";
    }

    /**
     * 我的快递/报错修理
     * @param model
     * @param userLoginInfo
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("repair")
    public String repair(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        return "/postbox/repair";
    }

    /**
     * 替我代领更新代领人的手机号
     * @param customerInfoId
     * @param mobile
     * @param boxCode
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @PostMapping("forMeLead")
    @ResponseBody
    public RespResult updateForMeLead(@ModelAttribute("customerInfoId") Long customerInfoId, @RequestParam("mobile") String mobile,@RequestParam("boxCode") String boxCode){
        RespResult respResult = null;
        try {
            respResult = this.boxInfoService.updateForMeLead(customerInfoId,mobile, boxCode);
        } catch (BizException biz) {
            biz.printStackTrace();
            logger.error("设置代领人信息异常,手机号：{}，箱子编码：{}，错误信息：{}",mobile,boxCode,biz.getMessage());
            respResult = new RespResult(RespCode.FAIL,biz.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置代领人信息异常,手机号：{}，箱子编码：{}，错误信息：{}",mobile,boxCode,e);
            respResult = new RespResult(RespCode.FAIL,"设置代领人失败");
        }
        return respResult;
    }

    /**
     * 个人中心/我的钱包
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("toMyWallet")
    public String toMyWallet() {
        return "/postbox/myWallet";
    }



}
