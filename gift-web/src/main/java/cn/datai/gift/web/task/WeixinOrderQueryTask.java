package cn.datai.gift.web.task;

import cn.datai.gift.persist.po.PayInfo;
import cn.datai.gift.web.contants.enums.PayChannel;
import cn.datai.gift.web.service.PayInfoService;
import cn.datai.gift.web.service.PayOrderService;
import cn.datai.gift.web.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 微信查询订单处理状态Task
 * Created by H.CAAHN on 2017/4/19.
 */
@Component
public class WeixinOrderQueryTask {
    private static final Logger logger = LoggerFactory.getLogger(WeixinOrderQueryTask.class);

    private int size = 300;

    @Resource
    private PayInfoService payInfoService;

    @Resource
    private PayOrderService payOrderService;

    @Resource
    private PayService payService;

    public void orderQuery() {
        try {
            int total = payInfoService.countUnPayInfo(PayChannel.WX.getPersistKey());
            if (total <= 0) {
                logger.info("微信查询待支付订单状态[未查询到待支付订单]");
                return;
            }

            int totalPage = total / size + (total % size == 0 ? 0 : 1);
            logger.info("微信查询待支付订单状态[待处理总数:{}, 每批处理数量:{}, 总批数:{}]", total, size, totalPage);
            if (totalPage == 1) {
                List<PayInfo> payList = this.payInfoService.pageUnPayInfo("wx", 1, size, total);
                this.doTask(payList);
                return;
            }
            for (int i = 1; i <= totalPage; i++) {
                logger.info("微信查询待支付订单状态[开始处理第{}批，总批数:{}]", i, totalPage);
                List<PayInfo> payList = this.payInfoService.pageUnPayInfo("wx", i, size, total);
                this.doTask(payList);
            }
        }
        catch (Exception ex) {
            logger.error("微信查询待支付订单状态[查询失败]", ex);
        }
    }

    private void doTask(List<PayInfo> payList) {
        if (payList == null || !payList.isEmpty()) {
            logger.info("微信查询待支付订单状态[待处理列表为空]");
            return;
        }
        for (PayInfo pay : payList) {
            try {
                this.payService.payOrderQuery(pay);
            }
            catch (Exception ex) {
                logger.error("微信查询待支付订单状态[处理失败, id:" + pay.getPayDetailId() + "]", ex);
            }
        }
    }
}
