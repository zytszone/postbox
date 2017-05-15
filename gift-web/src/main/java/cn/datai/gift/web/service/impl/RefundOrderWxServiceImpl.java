package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.PayInfoMapperExt;
import cn.datai.gift.persist.mapper.RefundOrderWxMapperExt;
import cn.datai.gift.persist.po.*;
import cn.datai.gift.persist.vo.Page;
import cn.datai.gift.web.contants.enums.PayInfoRefundStatus;
import cn.datai.gift.web.service.PayService;
import cn.datai.gift.web.service.RefundOrderWxService;
import cn.datai.gift.wxpay.bean.response.WxPayRefundOrderResponse;
import cn.datai.gift.wxpay.bean.response.WxPayRefundQueryResponse;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by H.CAAHN on 2017/4/25.
 */
@Service
public class RefundOrderWxServiceImpl implements RefundOrderWxService {
    @Autowired
    private RefundOrderWxMapperExt refundOrderWxMapperExt;

    @Autowired
    private PayInfoMapperExt payInfoMapperExt;

    @Resource
    private PayService payService;

    private static final Logger logger = LoggerFactory.getLogger(RefundOrderWxServiceImpl.class);

    private List<String> refundStatus;

    {
        refundStatus = new ArrayList<String>(2);
        refundStatus.add("NOTSURE");
        refundStatus.add("PROCESSING");
    }

    public int countProcessingWxRefund(Date querytime) {
        RefundOrderWxExample example = new RefundOrderWxExample();
        example.createCriteria().andRefundStatusIn(refundStatus).andQueryTimeLessThan(querytime);
        return refundOrderWxMapperExt.countByExample(example);
    }

    public List<RefundOrderWx> pageProcessingWxRefund(Date querytime, int pageNo, int size, int total) {
        RefundOrderWxExample example = new RefundOrderWxExample();
        example.createCriteria().andRefundStatusIn(refundStatus).andQueryTimeLessThan(querytime);
        Page page = new Page(true, pageNo, size, total);
        example.setPage(page);
        return refundOrderWxMapperExt.selectByExample(example);
    }

    public RefundOrderWx queryByOutTradeNo(String outTradeNo) {
        RefundOrderWxExample example = new RefundOrderWxExample();
        example.createCriteria().andOutTradeNoEqualTo(outTradeNo);
        List<RefundOrderWx> rList = this.refundOrderWxMapperExt.selectByExample(example);
        if (rList != null && !rList.isEmpty()) {
            return rList.get(0);
        }
        return null;
    }

    @Transactional
    public RefundOrderWx insertForRefund(PayOrderWx payOrderWx, String outRefundNo) {
        RefundOrderWx record = new RefundOrderWx();
        record.setOutRefundNo(outRefundNo);
        record.setOutTradeNo(payOrderWx.getPayOrderWxId());
        record.setAppid(payOrderWx.getAppid());
        record.setTotalFee(payOrderWx.getTotalFee() != null ? payOrderWx.getTotalFee().intValue() : 0l);
        record.setRefundFee(payOrderWx.getCashFee() != null ? payOrderWx.getCashFee().intValue() : 0l);
        record.setRefundFeeType(payOrderWx.getFeeType());
        record.setCashFee(payOrderWx.getCashFee() != null ? payOrderWx.getCashFee().intValue(): 0l);
        record.setCashFeeType(payOrderWx.getCashFeeType());
        record.setQueryCount(0);
        record.setCreatetime(new Date());

        this.refundOrderWxMapperExt.insertSelective(record);
        RefundOrderWx refundOrderWx = this.refundOrderWxMapperExt.selectByPrimaryKey(outRefundNo);
        return refundOrderWx;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public void updateForApplyRefund(WxPayRefundOrderResponse response, String outRefundNo, long payInfoId) {
        //先上锁基本支付信息，更新退款状态
        PayInfo payInfo = payInfoMapperExt.selectByPrimaryKeyAndLock(payInfoId);
        payInfo.setRefundStatus(PayInfoRefundStatus.PROCESSING.getPersistKey());
        payInfoMapperExt.updateByPrimaryKey(payInfo);
        logger.info("上报商户退款单号{}，退款成功后，更新基本支付信息的退款状态为 {}", outRefundNo, PayInfoRefundStatus.PROCESSING.getPersistKey());

        logger.debug("上报退款微信响应的对象：{}", response);
        RefundOrderWx record = new RefundOrderWx();
        record.setOutRefundNo(outRefundNo);
        record.setRefundId(response.getRefundId());
        record.setTransactionId(response.getTransactionId());
        record.setSettlementRefundFee(response.getSettlementRefundFee() != null ? response.getSettlementRefundFee().intValue() : 0L);
        record.setSettlementTotalFee(response.getSettlementTotalFee() != null ? response.getSettlementTotalFee().intValue() : 0L);
        record.setCashRefundFee(response.getCashRefundFee() != null ? response.getCashRefundFee().intValue() : 0L);
        record.setCouponRefundFee(response.getCouponRefundFee() != null ? response.getCouponRefundFee().intValue() : 0L);
        record.setCouponRefundCount(response.getCouponRefundCount());
        record.setCouponInfo(JSON.toJSONString(response.getDetailList()));
        record.setResultCode(response.getResultCode());
        record.setQueryCount(0);

        record.setRefundStatus(PayInfoRefundStatus.PROCESSING.getPersistKey());
        Calendar calendar = Calendar.getInstance();
        record.setCreatetime(calendar.getTime());
        calendar.add(Calendar.MINUTE, 30);
        record.setQueryTime(calendar.getTime());
        logger.debug("准备更新退款上报成功后的信息：{}", record);
        this.refundOrderWxMapperExt.updateByPrimaryKeySelective(record);
    }

    public void updateForQueryRefundOrder(RefundOrderWx refund, WxPayRefundQueryResponse rsp) {
        try {
            int queryCount = refund.getQueryCount() != null ? refund.getQueryCount() + 1 : 1;
            refund.setResultCode(rsp.getResultCode());
            refund.setQueryCount(queryCount);

            // 因该方法采用商户退款单号查询退款信息，因此返回的退款笔数应有且只有一条
            if ("SUCCESS".equalsIgnoreCase(rsp.getResultCode())) {
                if (rsp.getRefundCount() != null && rsp.getRefundCount() == 1) {
                    switch (rsp.getRefundStatus()[0]) {
                        case "SUCCESS"://退款成功
                            this.buildProperties(refund, rsp);
                            refund.setQueryTime(null);
                            break;
                        case "REFUNDCLOSE" :// 退款关闭
                            this.buildProperties(refund, rsp);
                            refund.setQueryTime(null);
                            break;
                        case "NOTSURE" : //未确定，需要商户用原退款单号重新发起退款申请。
                            this.buildNextQuery(refund);
                            break;
                        case "PROCESSING" : // 退款处理中
                            this.buildNextQuery(refund);
                            break;
                        case "CHANGE":  //退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，商户可以发起异常退款处理的申请，可以退款到用户的新卡或者退款商户，商户自行处理。
                            this.buildProperties(refund, rsp);
                            refund.setQueryTime(null);
                            break;
                        default:
                            logger.error("根据商户退款单号查询退款信息错误：退款状态数据异常:{}", refund.getRefundStatus());
                            break;
                    }
                }
                else {
                    this.buildNextQuery(refund);
                    logger.error("根据商户退款单号查询退款信息错误：退款笔数，预期值:1，实际值:{}", rsp.getRefundCount());
                }
            }
            else {
                this.buildNextQuery(refund);
            }

            this.refundOrderWxMapperExt.updateByPrimaryKey(refund);

            // 更新订单信息
            PayInfoExample payInfoExample = new PayInfoExample();
            payInfoExample.createCriteria().andChannelEqualTo("wx").andPayDetailIdEqualTo(refund.getOutTradeNo());
            List<PayInfo> list = this.payInfoMapperExt.selectByExample(payInfoExample);
            if (list != null && !list.isEmpty()) {
                PayInfo payInfo = list.get(0);
                payInfo.setRefundStatus(refund.getRefundStatus());
                payInfo.setRefundedPrice(refund.getRefundFee());
                this.payInfoMapperExt.updateByPrimaryKeySelective(payInfo);
            }
        }
        catch (Exception ex) {
            logger.error("微信查询退款信息失败，商户退款单号：" + refund.getOutRefundNo(), ex);
        }
    }

    private void buildNextQuery(RefundOrderWx refund) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        refund.setQueryTime(calendar.getTime());
    }

    private void buildProperties(RefundOrderWx refund, WxPayRefundQueryResponse rsp) {
        refund.setTransactionId(rsp.getTransactionId());
        refund.setSettlementRefundFee(rsp.getSettlementRefundFee()[0] != null ? rsp.getSettlementRefundFee()[0].intValue() : 0L);
        refund.setSettlementTotalFee(rsp.getSettlementTotalFee() != null ? rsp.getSettlementTotalFee().intValue() : 0L);
        refund.setCashFee(rsp.getCashFee() != null ? rsp.getCashFee().intValue() : 0L);
        refund.setCashFeeType(rsp.getFeeType());
        refund.setCouponRefundFee(rsp.getCouponRefundFees()[0] != null ? rsp.getCouponRefundFees()[0].intValue() : 0L);
        refund.setCashRefundFee(refund.getSettlementRefundFee() - refund.getCouponRefundFee());
        refund.setCouponRefundCount(rsp.getCouponRefundCount()[0] != null ? rsp.getCouponRefundCount()[0] : 0);
        refund.setCouponInfo(JSON.toJSONString(rsp.getCouponRefundDetails().get(0)));
        refund.setRefundStatus(rsp.getRefundStatus()[0]);
        refund.setQueryTime(null);
    }
}
