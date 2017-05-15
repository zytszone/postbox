package cn.datai.gift.web.controller;

import cn.datai.gift.persist.po.GiftAccount;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.contants.enums.CommodityFlowType;
import cn.datai.gift.web.service.TradeAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

/**
 * 管理后台需要的接口
 * Created by zhangyutao on 2017/5/15.
 */
@Controller("admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private TradeAccountService tradeAccountService;

    /**
     * 修改库存
     * @param giftAccountId 礼包账户id
     * @param commodityQtn  库存改动数量， 正值为增加库存量， 负值为减少库存量
     * @return
     */
    @PostMapping("modifyInventory")
    public RespResult modifyInventory(long giftAccountId, long commodityQtn) {
        logger.info("收到修改商品库存的请求 giftAccountId:{}, commodityQtn;{}", giftAccountId, commodityQtn);

        if (commodityQtn == 0) {
            return new RespResult(RespCode.PARAMS_ERROR);
        }

        if (commodityQtn < 0) {
            //减库存
            GiftAccount giftAccount = tradeAccountService.findTradeAccountById(giftAccountId);
            BigDecimal availableUnitQuantity = giftAccount.getAvailableUnitQuantity();
            if (availableUnitQuantity.compareTo(new BigDecimal(-1 * commodityQtn)) == -1) {
                //库存不够减的
                return new RespResult(RespCode.TRADE_AVAILABLE_NOT_ENOUGH);
            }
            try {
                //调用商品调拨服务
                tradeAccountService.allot(giftAccountId, "available", CommodityFlowType.OTHER_SYS_TRANSFER_OUT, new BigDecimal(commodityQtn), null, "管理后台减少商品库存");
            } catch (BizException e) {
                return new RespResult(e.getErrorCode(), e.getErrorMsg());
            }
        }

        if (commodityQtn > 0) {
            try {
                //调用商品调拨服务
                tradeAccountService.allot(giftAccountId, "available", CommodityFlowType.OTHER_SYS_TRANSFER_IN, new BigDecimal(commodityQtn), null, "管理后台增加商品库存");
            } catch (BizException e) {
                return new RespResult(e.getErrorCode(), e.getErrorMsg());
            }
        }
        return new RespResult(RespCode.SUCCESS);
    }
}
