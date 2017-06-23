package cn.datai.gift.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 帮助中心
 * Created by H.CAAHN on 2017/6/22.
 */
@RequestMapping("help")
@Controller
public class HelperController extends BaseController {
    @RequestMapping("/helpCenter")
    public String toHelp(Model model, HttpServletRequest request) {
        return "/postbox/helpCenter";
    }
}
