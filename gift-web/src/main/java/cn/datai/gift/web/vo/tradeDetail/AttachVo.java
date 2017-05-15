package cn.datai.gift.web.vo.tradeDetail;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/28.
 */
public class AttachVo implements Serializable {
    private int haveAcceptGiftNum;//发红包中已被抢走的数量

    private int giftTotalNum;//总共发放红包的数量

    private String acceptGift;//收红包提示语

    private String payStatus;//付款状态

    private String pickUpStatus;//提货状态

    private String remark;//备注

    public int getHaveAcceptGiftNum() {
        return haveAcceptGiftNum;
    }

    public void setHaveAcceptGiftNum(int haveAcceptGiftNum) {
        this.haveAcceptGiftNum = haveAcceptGiftNum;
    }

    public int getGiftTotalNum() {
        return giftTotalNum;
    }

    public void setGiftTotalNum(int giftTotalNum) {
        this.giftTotalNum = giftTotalNum;
    }

    public String getAcceptGift() {
        return acceptGift;
    }

    public void setAcceptGift(String acceptGift) {
        this.acceptGift = acceptGift;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPickUpStatus() {
        return pickUpStatus;
    }

    public void setPickUpStatus(String pickUpStatus) {
        this.pickUpStatus = pickUpStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
