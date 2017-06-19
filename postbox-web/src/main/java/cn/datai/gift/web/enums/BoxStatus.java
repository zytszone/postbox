package cn.datai.gift.web.enums;

/**
 * 箱子状态
 * Created by H.CAAHN on 2017/6/19.
 */
public enum BoxStatus {
    NOACTIVE("未激活"),

    NORMAL("正常"),

    REPAIR("维修"),

    DEMISE("注销");

    private String remark;

    BoxStatus(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getName() {
        return this.name();
    }
}
