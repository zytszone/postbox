package cn.datai.gift.web.controller;

import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.web.plugin.annotation.Auth;
import cn.datai.gift.web.service.TradeDetailService;
import cn.datai.gift.web.vo.tradeDetail.TradeDetailVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 交易明细
 * Created by Administrator on 2017/4/27.
 */
@Controller
@RequestMapping("tradeDetail")
public class TradeDetailController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(TradeDetailController.class);

    @Autowired
    private TradeDetailService tradeDetailService;

    /**
     * 跳转交易明细
     * @return
     */
    @RequestMapping(value ="/transactionDetails")
    @Auth(needLogin = true,weixinJsAuth = true)
    public String transactionDetails(){
        return "gift/transactionDetails";
    }

    /**
     * 查询个人交易明细
     * @param bizType 业务类型code
     * @param commodityTypeId 商品类型Id
     * @param date 查询的时间 字符串 如：“2016-04”
     * @param userInfoId 用户ID
     * @return
     */
    @Auth(needLogin = true,weixinJsAuth = true)
    @RequestMapping(value = "/record",method = RequestMethod.POST)
    @ResponseBody
    public RespResult queryRecords(
            @RequestParam("bizType") String bizType,
            @RequestParam("commodityTypeId") Long commodityTypeId,
            @RequestParam("date") String date,
            @ModelAttribute("userInfoId") Long userInfoId){
        try {
            List<TradeDetailVo> tradeDetailVoList = tradeDetailService.queryRecords(bizType, commodityTypeId, date, userInfoId);
            return new RespResult(RespCode.SUCCESS, JSON.toJSONString(tradeDetailVoList, SerializerFeature.DisableCircularReferenceDetect));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询用户：{} 的 业务类型为： {} ，商品类型为 ： {} 在时间：{} 内的交易明细发生异常",userInfoId,bizType,commodityTypeId,date);
            return new RespResult(RespCode.FAIL,null);
        }
    }

}
