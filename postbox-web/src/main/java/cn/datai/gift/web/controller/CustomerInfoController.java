package cn.datai.gift.web.controller;

import cn.datai.gift.web.service.BoxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 客户信息管理
 * Created by H.CAAHN on 2017/6/19.
 */
@Controller
@RequestMapping("customer/")
public class CustomerInfoController extends BaseController {
    @Autowired
    private BoxInfoService boxInfoService;
}
