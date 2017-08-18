package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.persist.vo.UserInfoVo;
import cn.datai.gift.utils.PropertiesUtil;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.contants.TokenContants;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.service.CustomerInfoService;
import cn.datai.gift.web.utils.MyStringUtil;
import cn.datai.gift.web.vo.TBoxInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            List<TBoxInfo> dataList = this.boxInfoService.queryTBoxInfoByMobileOrproxyCustomerInfoId(customerInfo.getMobilePhone(),customerInfo.getCustomerInfoId().toString());
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
     * 我的快递/替我代领
     * @param model
     * @param userLoginInfo
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("forMeLead")
    public String forMeLead(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        TCustomerInfo customerInfo = this.customerInfoService.queryById(userLoginInfo.getCustomerInfoId());
        List<TBoxInfo> dataList = this.boxInfoService.queryTBoxInfoByMobileOrproxyCustomerInfoId(customerInfo.getMobilePhone(),null);
        model.addAttribute("dataList", dataList);
        model.addAttribute("customerInfoId",userLoginInfo.getCustomerInfoId());
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
     * 代领人点击链接跳转到代领人确认带领页面
     * @param model
     * @param userLoginInfo
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @RequestMapping("sureForLead")
    public String sureForLead(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo,String boxIds,String tcustomerInfoId) {
        if(MyStringUtil.isBlank(boxIds) || MyStringUtil.isBlank(tcustomerInfoId) || (userLoginInfo.getCustomerInfoId().toString()).equals(tcustomerInfoId)){
            model.addAttribute("dataList",null);
            return "/postbox/sureForLead";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("customerInfoId",tcustomerInfoId);
        UserInfoVo infoVo = this.customerInfoService.queryUserWxInfoByCons(map);
        String[] split = boxIds.split(",");
        List<TBoxInfoVo> dataList = Arrays.asList(split).stream().map(boxId -> {
            TBoxInfo boxInfo = this.boxInfoService.queryById(Long.valueOf(boxId));
            TBoxInfoVo tBoxInfoVo = new TBoxInfoVo();
            BeanUtils.copyProperties(boxInfo,tBoxInfoVo);
            tBoxInfoVo.setNickName(infoVo.getNickname());
            return tBoxInfoVo;
        }).collect(Collectors.toList());
        model.addAttribute("dataList",dataList);
        model.addAttribute("boxIds",boxIds);

        return "/postbox/sureForLead";
    }

    /**
     * 替我代领更新代领人
     * @param customerInfoId
     * @param boxIds
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true,needPhone = true)
    @PostMapping("updateForMeLead")
    @ResponseBody
    public RespResult updateForMeLead(@ModelAttribute("customerInfoId") Long customerInfoId,@RequestParam("boxIds") String boxIds){
        RespResult respResult = null;
        try {
            respResult = this.boxInfoService.updateForMeLead(customerInfoId,boxIds);
        } catch (BizException biz) {
            biz.printStackTrace();
            logger.error("设置代领人信息异常,箱子Ids：{}，错误信息：{}",boxIds,biz.getMessage());
            respResult = new RespResult(RespCode.FAIL,biz.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置代领人信息异常,箱子Ids：{}，错误信息：{}",boxIds,e);
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
