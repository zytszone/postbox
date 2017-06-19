package cn.datai.gift.web.enums;

/**
 * 箱子的快件状态
 * Created by H.CAAHN on 2017/6/19.
 */
public enum BoxExpressStatus {
    EMPTY("无包裹"),

    FULL("有包裹");

    private String remark;

    BoxExpressStatus(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getName() {
        return this.name();
    }
}
