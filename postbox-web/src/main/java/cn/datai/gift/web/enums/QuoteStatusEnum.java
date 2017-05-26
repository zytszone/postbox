package cn.datai.gift.web.enums;

/**
 * Created by Administrator on 2017/4/6.
 */
public enum QuoteStatusEnum {

    START_STATUS("市场启动","START"),
    PRETR_STATUS("盘前","PRETR"),
    OCALL_STATUS("集合竞价","OCALL"),
    TRADE_STATUS("交易中","TRADE"),
    HALF_STATUS("暂停","HALF"),
    SUSP_STATUS("停盘","SUSP"),
    BREAK_STATUS("休市","BREAK"),
    POSIT_STATUS("盘后","POSIT"),
    ENDTR_STATUS("已收盘","ENDTR"),
    STOPT_STATUS("长期停盘","STOPT");

    private String name;

    private String code;

    private QuoteStatusEnum(String name, String code){
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String getStatusName(String code){
        for (QuoteStatusEnum quoteStatusEnum : QuoteStatusEnum.values()) {
            if (quoteStatusEnum.getCode().equals(code)) {
                return quoteStatusEnum.name;
            }
        }
        return "";
    }

}
