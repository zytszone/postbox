package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.service.CustomerInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("toReceivingList")
    public String toReceivingList(Model model) {
        return "/postbox/toReceivingList";
    }

    @RequestMapping("getReceivingList")
    public String getReceivingList(Model model, @ModelAttribute("userLoginInfo") UserLoginInfo userLoginInfo) {
        try {
            TCustomerInfo customerInfo = this.customerInfoService.queryById(userLoginInfo.getCustomerInfoId());
            List<TBoxInfo> dataList = this.boxInfoService.queryByMobilePhone(customerInfo.getMobilePhone());
            model.addAttribute("dataList", dataList);
        }
        catch (Exception ex) {
            logger.error("获取待领快件信息失败", ex);
        }
        return null;
    }
}
