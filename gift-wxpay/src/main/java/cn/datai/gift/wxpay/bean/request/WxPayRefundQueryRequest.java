package cn.datai.gift.wxpay.bean.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by H.CAAHN on 2017/4/20.
 */
@Root(name = "xml")
public class WxPayRefundQueryRequest extends WxPayBaseRequest {
    /** 微信支付分配的公众账号ID（企业号corpid即为此appId） */
    @Element(name = "appid")
    private String appid;

    /** 微信支付分配的商户号 */
    @Element(name = "mch_id")
    private String mchId;

    /** 随机字符串，不长于32位。推荐随机数生成算法 */
    @Element(name = "nonce_str")
    private String nonceStr;

    /** 通过签名算法计算得出的签名值，详见签名生成算法 */
    @Element(name = "sign")
    private String sign;

    /** 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5 */
    @Element(name = "sign_type")
    private String signType;

    /** 微信订单号,transactionId、outTradeNo、outRefundNo、refundId四选一 */
    @Element(name = "transaction_id")
    private String transactionId;

    /** 商户系统内部的订单号,transactionId、outTradeNo、outRefundNo、refundId四选一 */
    @Element(name = "out_trade_no")
    private String outTradeNo;

    /** 商户侧传给微信的退款单号,transactionId、outTradeNo、outRefundNo、refundId四选一 */
    @Element(name = "out_refund_no")
    private String outRefundNo;

    /** 微信生成的退款单号，在申请退款接口有返回,transactionId、outTradeNo、outRefundNo、refundId四选一 */
    @Element(name = "refund_id")
    private String refundId;

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
}
