package cn.datai.gift.web.model.vo;

import java.io.Serializable;

/**
 *  商品价格（实时）
 */
public class CommodityPriceVo implements Serializable{

//    {"head":{"code":"0","msg":"成功."},
//        "body":[
        //        {"upPx":"38.980",
                // "tradeStatus":"TRADE",
                // "lastPx":"35.440",
                // "upCount":"",
                // "businessBalance":"5205156",
                // "downCount":"",
                // "openPx":"34.700",
                // "prodCode":"601001",
                // "pxChange":"0.000",
                // "evenCount":"",
                // "lowPx":"34.700",
                // "offerGrp":[["35.440","86","0"],["35.460","135","0"],["35.480","325","0"],["35.500","200","0"],["35.520","501","0"]],
                // "bidGrp":[["35.360","125","0"],["35.340","10","0"],["35.330","73","0"],["35.320","586","0"],["35.310","10","0"]],
                // "prodName":"中茶大树2015",
                // "downPx":"31.900",
                // "preclosePx":"35.440",
                // "highPx":"35.810",
                // "businessAmount":"146729",
                // "pxChangeRate":"0.000",
                // "dataTime":"1462933045005",
                // "amplitude":"3.130"},
//        {"upPx":"20.080","tradeStatus":"TRADE","lastPx":"18.250","upCount":"","businessBalance":"0","downCount":"","openPx":"0.000","prodCode":"601002","pxChange":"0.000","evenCount":"","lowPx":"0.000","offerGrp":[["0.000","0","0"],["0.000","0","0"],["0.000","0","0"],["0.000","0","0"],["0.000","0","0"]],"bidGrp":[["0.000","0","0"],["0.000","0","0"],["0.000","0","0"],["0.000","0","0"],["0.000","0","0"]],"prodName":"中茶曼糯2016","downPx":"16.430","preclosePx":"18.250","highPx":"0.000","businessAmount":"0","pxChangeRate":"0.000","dataTime":"1462933024002","amplitude":"0.000"},
//        {"upPx":"126.690","tradeStatus":"TRADE","lastPx":"114.920","upCount":"","businessBalance":"6518017","downCount":"","openPx":"115.170","prodCode":"602001","pxChange":"-0.250","evenCount":"","lowPx":"114.600","offerGrp":[["115.120","6","0"],["115.300","10","0"],["115.400","6","0"],["115.700","110","0"],["115.920","200","0"]],"bidGrp":[["114.930","130","0"],["114.920","157","0"],["114.900","20","0"],["114.840","77","0"],["114.810","67","0"]],"prodName":"下关景迈2015","downPx":"103.650","preclosePx":"115.170","highPx":"118.000","businessAmount":"56385","pxChangeRate":"-0.220","dataTime":"1462933045006","amplitude":"2.950"}
//     ]}

    //涨停价
    private String upPx;

    //交易状态
    private String tradeStatus;

    //最新价
    private String lastPx;

    //涨家数
    private String upCount;

    //成交额
    private String businessBalance;

    //跌家数
    private String downCount;

    //开盘价
    private String openPx;

    //产品代码
    private String prodCode;

    //涨跌额
    private String pxChange;

    //平家数
    private String evenCount;

    //最低价
    private String lowPx;

    //卖档位（5档）
    private String[][] offerGrp;

    //买档位（5档）
    private String[][] bidGrp;

    //产品名称
    private String prodName;

    //跌停价
    private String downPx;

    //昨收价
    private String preclosePx;

    //最高价
    private String highPx;

    //成交量
    private String businessAmount;

    // 涨跌幅
    private String pxChangeRate;

    //时间戳
    private String dataTime;

    //振幅
    private String amplitude;

    public String getUpPx() {
        return upPx;
    }

    public void setUpPx(String upPx) {
        this.upPx = upPx;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getLastPx() {
        return lastPx;
    }

    public void setLastPx(String lastPx) {
        this.lastPx = lastPx;
    }

    public String getUpCount() {
        return upCount;
    }

    public void setUpCount(String upCount) {
        this.upCount = upCount;
    }

    public String getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(String businessBalance) {
        this.businessBalance = businessBalance;
    }

    public String getDownCount() {
        return downCount;
    }

    public void setDownCount(String downCount) {
        this.downCount = downCount;
    }

    public String getOpenPx() {
        return openPx;
    }

    public void setOpenPx(String openPx) {
        this.openPx = openPx;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getPxChange() {
        return pxChange;
    }

    public void setPxChange(String pxChange) {
        this.pxChange = pxChange;
    }

    public String getEvenCount() {
        return evenCount;
    }

    public void setEvenCount(String evenCount) {
        this.evenCount = evenCount;
    }

    public String getLowPx() {
        return lowPx;
    }

    public void setLowPx(String lowPx) {
        this.lowPx = lowPx;
    }

    public String[][] getOfferGrp() {
        return offerGrp;
    }

    public void setOfferGrp(String[][] offerGrp) {
        this.offerGrp = offerGrp;
    }

    public String[][] getBidGrp() {
        return bidGrp;
    }

    public void setBidGrp(String[][] bidGrp) {
        this.bidGrp = bidGrp;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDownPx() {
        return downPx;
    }

    public void setDownPx(String downPx) {
        this.downPx = downPx;
    }

    public String getPreclosePx() {
        return preclosePx;
    }

    public void setPreclosePx(String preclosePx) {
        this.preclosePx = preclosePx;
    }

    public String getHighPx() {
        return highPx;
    }

    public void setHighPx(String highPx) {
        this.highPx = highPx;
    }

    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getPxChangeRate() {
        return pxChangeRate;
    }

    public void setPxChangeRate(String pxChangeRate) {
        this.pxChangeRate = pxChangeRate;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }
}
