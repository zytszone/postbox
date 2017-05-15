package cn.datai.gift.web.controller;

import cn.datai.gift.web.plugin.annotation.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/5/15.
 */
@Controller
@RequestMapping(value = "/main")
public class IndexController extends BaseController{

    @RequestMapping(value = "/toIndex")
    @Auth(needLogin = true,weixinJsAuth = true)
    public String toIndex(Model model){
        return "postbox/index";
    }
}
