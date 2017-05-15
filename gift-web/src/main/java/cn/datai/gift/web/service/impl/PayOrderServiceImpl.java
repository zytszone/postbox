package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.PayOrderWxMapperExt;
import cn.datai.gift.persist.po.PayInfo;
import cn.datai.gift.persist.po.PayOrderWx;
import cn.datai.gift.web.service.PayOrderService;
import cn.datai.gift.wxpay.bean.response.WxPayOrderQueryResponse;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by H.CAAHN on 2017/4/19.
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {
    @Autowired
    private PayOrderWxMapperExt payOrderWxMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    public PayOrderWx queryPayOrderWxById(String payOrderWxId) {
        return payOrderWxMapperExt.selectByPrimaryKey(payOrderWxId);
    }

    public void updateForQueryPayOrder(PayInfo payInfo, PayOrderWx record, WxPayOrderQueryResponse rsp) {
        record.setPayResultCode(rsp.getResultCode());
        // 支付成功
        if ("SUCCESS".equalsIgnoreCase(rsp.getTradeState())) {
            payInfo.setStatus("F");

            // TODO 更新微信付款明细
            this.buildProperties(record, rsp);
            this.payOrderWxMapperExt.updateByPrimaryKeySelective(record);
            return;
        }
        // 转入退款
        else if ("REFUND".equalsIgnoreCase(rsp.getTradeState())) {
            // TODO 更改订单支付状态为：支付已取消
            payInfo.setStatus("C");

            // TODO 更改微信付款明细状态为关闭或付款异常等
            this.buildProperties(record, rsp);
        }
        // 未支付
        else if ("NOTPAY".equalsIgnoreCase(rsp.getTradeState())) {
            // TODO 待下次处理
        }
        // 已关闭
        else if ("CLOSED".equalsIgnoreCase(rsp.getTradeState())) {
            // TODO 更改订单支付状态为：支付已取消
            payInfo.setStatus("C");

            // TODO 更改微信付款明细状态为关闭或付款异常等
        }
        // 已撤销（刷卡支付）
        else if ("REVOKED".equalsIgnoreCase(rsp.getTradeState())) {
            // TODO 暂无刷卡支付，可不需要处理
        }
        // 支付失败(其他原因，如银行返回失败)
        else if ("PAYERROR".equalsIgnoreCase(rsp.getTradeState())) {
            // TODO 更改订单支付状态为关闭或付款异常等
            payInfo.setStatus("C");

            // TODO 更改微信付款明细状态为关闭或付款异常等
        }
        this.payOrderWxMapperExt.updateByPrimaryKeySelective(record);
        logger.info("微信查询支付信息[不成功，tradeStatus:{}, outTradeNo:{}]", rsp.getTradeState(), rsp.getOutTradeNo());
    }

    private void buildProperties(PayOrderWx record, WxPayOrderQueryResponse rsp) {
        record.setBankType(rsp.getBankType());
        record.setSettlementTotalFee(rsp.getSettlementTotalFee());
        record.setCashFee(rsp.getCashFee());
        record.setCashFeeType(rsp.getCashFeeType());
        record.setTimeEnd(rsp.getTimeEnd());

        // 代金券详情
        record.setCouponFee(rsp.getCouponFee());
        record.setCouponCount(rsp.getCouponCount());
        if (rsp.getCouponDetails() != null) {
            record.setCouponDetailList(JSON.toJSONString(rsp.getCouponDetails()));
        }
    }
}
