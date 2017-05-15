package cn.datai.gift.web.service;

import cn.datai.gift.persist.mapper.PayOrderWxMapperExt;
import cn.datai.gift.persist.po.PayOrderWx;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.call.weixin.pay.WeixinPayService;
import cn.datai.gift.web.call.weixin.payssl.WeixinPaySSLService;
import cn.datai.gift.wxpay.bean.common.WxError;
import cn.datai.gift.wxpay.bean.common.WxException;
import cn.datai.gift.wxpay.bean.request.WxPayOrderQueryRequest;
import cn.datai.gift.wxpay.bean.request.WxPayRefundOrderRequest;
import cn.datai.gift.wxpay.bean.request.WxPayRefundQueryRequest;
import cn.datai.gift.wxpay.bean.request.WxPayUnifiedOrderRequest;
import cn.datai.gift.wxpay.bean.response.WxPayOrderQueryResponse;
import cn.datai.gift.wxpay.bean.response.WxPayRefundOrderResponse;
import cn.datai.gift.wxpay.bean.response.WxPayRefundQueryResponse;
import cn.datai.gift.wxpay.bean.response.WxPayUnifiedOrderResponse;
import cn.datai.gift.wxpay.util.SignUtil;
import cn.datai.gift.wxpay.util.WxPayResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangyutao on 2017/4/12.
 */
@Service
public abstract class BasePayService implements PayService.WXPayService{

    private static Logger logger = LoggerFactory.getLogger(BasePayService.class);

    /**
     * 微信AppID
     */
    @Value("${weixin.appID}")
    protected String wxAppID;

    /**
     * 微信支付商户号
     */
    @Value("${weixin.pay.mchID}")
    protected String wxMchID;

    @Value("${weixin.pay.authurl")
    protected String wxPayAuthUrl;

    /**
     * 通知地址,异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    @Value("${weixin.pay.notifyUrl}")
    protected String wxNotifyUrl;

    @Value("${wexin.pay.apikey}")
    protected String wxApiKey;

    @Autowired
    private PayOrderWxMapperExt payOrderWxMapperExt;




    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private WeixinPaySSLService weixinPaySSLService;

    public WxPayOrderQueryResponse wxJsapiQuery(String transactionId, String outTradeNo) throws WxException {
        WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
        if (StringUtils.isNotBlank(transactionId)) {
            request.setTransactionId(transactionId);
        }
        else {
            request.setOutTradeNo(outTradeNo);
        }

        request.setAppid(this.wxAppID);
        request.setMchId(this.wxMchID);
        request.setNonceStr(UUID.randomUUID().toString().replace("-", ""));

        //签名
        String sign = SignUtil.createSign(request, wxApiKey);//准备签名的字符串
        logger.debug("签名：" + sign);
        request.setSign(sign);
        WxPayOrderQueryResponse response = null;
        try {
            logger.info("微信支付查询订单请求：{}", request);
            Map map = weixinPayService.payOrderQuery(request).execute().body();

            response = WxPayResponseUtils.getWxPayOrderQueryResponse(map);
            logger.debug("微信支付查询订单响应：{}", response);

        } catch (IOException e) {
            logger.error("调用微信支付查询订单接口异常，{}", e.getMessage());
            throw new BizException(RespCode.PAY_NO_CHANNEL);
        } catch (Exception e) {
            logger.error("调用微信支付查询订单接口异常，{}", e);
            throw new BizException(RespCode.SERVER_ERROR);
        }

        String returnCode = response.getReturnCode();//返回状态码
        if (response == null || returnCode == null ) {
            throw new BizException(RespCode.WX_PAY_CALL_ERROR);
        }

        if ("SUCCESS".equals(returnCode)) {
            logger.info("请求公众号查询订单接口参数正确");

            String resultCode = response.getResultCode();
            if ("SUCCESS".equals(resultCode)) {//查询订单成功
                logger.info("调用公众号支付下单接口成功返回订单信息");

                return response;
            }
            throw new WxException(WxError.fromResponse(response));
        }
        logger.error("微信查询订单请求错误：{}, 请求参数：{}", response.getReturnMsg(), request);
        throw new BizException(RespCode.WX_PAY_REQUEST_ERROR);
    }

    public WxPayRefundQueryResponse wxJsapiQuery(String outRefundNo) throws WxException {
        WxPayRefundQueryRequest wxPayRefundQueryRequest = new WxPayRefundQueryRequest();
        wxPayRefundQueryRequest.setOutRefundNo(outRefundNo);
        wxPayRefundQueryRequest.setAppid(this.wxAppID);
        wxPayRefundQueryRequest.setMchId(this.wxMchID);
        wxPayRefundQueryRequest.setNonceStr(UUID.randomUUID().toString().replace("-", ""));

        //签名
        String sign = SignUtil.createSign(wxPayRefundQueryRequest, wxApiKey);//准备签名的字符串
        logger.debug("签名：" + sign);
        wxPayRefundQueryRequest.setSign(sign);
        WxPayRefundQueryResponse response = null;
        try {
            logger.info("微信退款查询请求：{}", wxPayRefundQueryRequest);
            Map map = weixinPayService.payRefundQuery(wxPayRefundQueryRequest).execute().body();

            response = WxPayResponseUtils.getWxPayRefundQueryResponse(map);
            logger.debug("微信退款查询响应：{}", response);
        } catch (IOException e) {
            logger.error("调用微信退款查询接口异常，{}", e.getMessage());
            throw new BizException(RespCode.PAY_NO_CHANNEL);
        } catch (Exception e) {
            logger.error("调用微信退款查询接口异常，{}", e);
            throw new BizException(RespCode.SERVER_ERROR);
        }

        String returnCode = response.getReturnCode();//返回状态码
        if (response == null || returnCode == null ) {
            throw new BizException(RespCode.WX_PAY_CALL_ERROR);
        }

        if ("SUCCESS".equals(returnCode)) {
            logger.info("请求微信退款查询返回成功");

            String resultCode = response.getResultCode();
            if ("SUCCESS".equals(resultCode)) {//查询退款订单成功
                logger.info("调用微信退款查询接口成功返回退款订单信息");

                return response;
            }
            throw new WxException(WxError.fromResponse(response));
        }
        logger.error("微信查询退款请求错误：{}, 请求参数：{}", response.getReturnMsg(), wxPayRefundQueryRequest);
        throw new BizException(RespCode.WX_PAY_REQUEST_ERROR);
    }

    /**
     * 上报微信支付订单信息，将返回的预付订单信息存入微信支付订单中，并修改对应的状态
     * @param payOrderWx
     */
    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
    public PayOrderWx callAndBindWxPrepayInfo(PayOrderWx payOrderWx){
        PayOrderWx payOrderWx1 = payOrderWxMapperExt.selectByPrimaryKeyAndLock(payOrderWx.getPayOrderWxId());
        if (!payOrderWx.equals(payOrderWx1)) {
            throw new BizException(RespCode.SERVER_BUSY);
        }
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setAppid(payOrderWx.getAppid());
        wxPayUnifiedOrderRequest.setMchId(payOrderWx.getMchId());
        wxPayUnifiedOrderRequest.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
        wxPayUnifiedOrderRequest.setDeviceInfo(payOrderWx.getDeviceInfo());
        wxPayUnifiedOrderRequest.setBody(payOrderWx.getBody());
        wxPayUnifiedOrderRequest.setDetail(payOrderWx.getDetail());
        wxPayUnifiedOrderRequest.setAttach(payOrderWx.getAttach());
        wxPayUnifiedOrderRequest.setOutTradeNo(payOrderWx.getPayOrderWxId());
        wxPayUnifiedOrderRequest.setFeeType(payOrderWx.getFeeType());
        wxPayUnifiedOrderRequest.setTotalFee(payOrderWx.getTotalFee());
        wxPayUnifiedOrderRequest.setSpbillCreateIp(payOrderWx.getSpbillCreateIp());
        wxPayUnifiedOrderRequest.setTimeStart(payOrderWx.getTimeStart());
        wxPayUnifiedOrderRequest.setTimeExpire(payOrderWx.getTimeExpire());
        wxPayUnifiedOrderRequest.setGoodsTag(payOrderWx.getGoodsTag());
        wxPayUnifiedOrderRequest.setNotifyUrl(this.wxNotifyUrl);
        wxPayUnifiedOrderRequest.setTradeType(payOrderWx.getTradeType());
        wxPayUnifiedOrderRequest.setProductId(payOrderWx.getProductId());
        wxPayUnifiedOrderRequest.setLimitPay(payOrderWx.getLimitPay());
        wxPayUnifiedOrderRequest.setOpenid(payOrderWx.getOpenid());
        wxPayUnifiedOrderRequest.setSignType("MD5");
        wxPayUnifiedOrderRequest.setSign(SignUtil.createSign(wxPayUnifiedOrderRequest, this.wxApiKey));

        WxPayUnifiedOrderResponse wxPayUnifiedOrderResponse = null;
        try {
            wxPayUnifiedOrderResponse = this.weixinPayService.payUnifiedOrder(wxPayUnifiedOrderRequest).execute().body();
        } catch (IOException e) {
            throw new BizException(RespCode.WX_PAY_CALL_ERROR);
        }
        boolean checkSign = SignUtil.checkSign(wxPayUnifiedOrderResponse, this.wxApiKey);
        if (!checkSign) {
            throw new BizException(RespCode.WX_PAY_CHECK_SIGN_ERROR);
        }
        if ("SUCCESS".equals(wxPayUnifiedOrderResponse.getReturnCode())) {
            //检查响应内容是否与请求匹配
            boolean checkResult = this.checkWxUnifiedOrderResponse(wxPayUnifiedOrderRequest, wxPayUnifiedOrderResponse);
            if (!checkResult) {
                throw new BizException(RespCode.WX_PAY_RESP_NOT_MATCH);
            }
            //不管结果如何，都要保存结果
            payOrderWx.setPrepayId(wxPayUnifiedOrderResponse.getPrepayId());
            payOrderWx.setCodeUrl(wxPayUnifiedOrderResponse.getCodeUrl());
            payOrderWx.setPrepayResultCode(wxPayUnifiedOrderResponse.getResultCode());
            payOrderWxMapperExt.updateByPrimaryKeySelective(payOrderWx);
        }
        return payOrderWx;
    }

    public WxPayRefundOrderResponse wxJsapiRefundOrder(PayOrderWx payOrderWx, String outRefundNo) throws WxException {
        WxPayRefundOrderRequest wxPayRefundOrderRequest = new WxPayRefundOrderRequest();
        wxPayRefundOrderRequest.setOutTradeNo(payOrderWx.getPayOrderWxId());
        wxPayRefundOrderRequest.setOutRefundNo(outRefundNo);
        wxPayRefundOrderRequest.setTotalFee(payOrderWx.getTotalFee());
        wxPayRefundOrderRequest.setRefundFee(payOrderWx.getCashFee());
        wxPayRefundOrderRequest.setRefundFeeType("CNY");
        wxPayRefundOrderRequest.setOpUserId(this.wxMchID);
        wxPayRefundOrderRequest.setAppid(this.wxAppID);
        wxPayRefundOrderRequest.setMchId(this.wxMchID);
        wxPayRefundOrderRequest.setNonceStr(UUID.randomUUID().toString().replace("-", ""));

        //签名
        String sign = SignUtil.createSign(wxPayRefundOrderRequest, wxApiKey);//准备签名的字符串
        logger.debug("签名：" + sign);
        wxPayRefundOrderRequest.setSign(sign);
        WxPayRefundOrderResponse response = null;
        try {
            logger.info("微信申请退款请求：{}", wxPayRefundOrderRequest);
            Map map = weixinPaySSLService.payRefundOrder(wxPayRefundOrderRequest).execute().body();

            response = WxPayResponseUtils.getWxPayRefundOrderResponse(map);
            logger.debug("微信申请退款响应：{}", response);
        } catch (IOException e) {
            logger.error("调用微信申请退款接口异常，{}", e);
            throw new BizException(RespCode.PAY_NO_CHANNEL);
        } catch (Exception e) {
            logger.error("调用微信申请退款接口异常，{}", e);
            throw new BizException(RespCode.SERVER_ERROR);
        }

        String returnCode = response.getReturnCode();//返回状态码
        if (response == null || returnCode == null ) {
            throw new BizException(RespCode.WX_PAY_CALL_ERROR);
        }

        if ("SUCCESS".equals(returnCode)) {
            logger.info("请求微信申请退款返回成功");

            String resultCode = response.getResultCode();
            if ("SUCCESS".equals(resultCode)) {//申请退款成功
                logger.info("调用微信申请退款接口成功返回退款订单信息");

                return response;
            }
            throw new WxException(WxError.fromResponse(response));
        }
        logger.error("微信申请退款请求错误：{}, 请求参数：{}", response.getReturnMsg(), wxPayRefundOrderRequest);
        throw new BizException(RespCode.WX_PAY_REQUEST_ERROR);
    }

    /**
     * 检查微信统一下单响应内容是否与请求匹配
     * @param wxPayUnifiedOrderRequest
     * @param wxPayUnifiedOrderResponse
     * @return
     */
    private boolean checkWxUnifiedOrderResponse(WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest, WxPayUnifiedOrderResponse wxPayUnifiedOrderResponse) {
        return (
                StringUtils.equals(wxPayUnifiedOrderResponse.getAppid(), wxPayUnifiedOrderRequest.getAppid())
                && StringUtils.equals(wxPayUnifiedOrderResponse.getMchId(), wxPayUnifiedOrderRequest.getMchId())
                && StringUtils.equals(wxPayUnifiedOrderResponse.getDeviceInfo(), wxPayUnifiedOrderRequest.getDeviceInfo())
        );
    }


}
