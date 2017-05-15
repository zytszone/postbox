package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class CommodityPrice implements Serializable {
    public static final long serialVersionUID = 300057253L;

    /**
     * 商品合约信息id: COMMODITY_PRICE.COMMODITY_CONTRACT_ID
     * @author MyBatis Generator
     */
    private Long commodityContractId;

    /**
     * 价格， 单位分: COMMODITY_PRICE.UNIT_PRICE
     * @author MyBatis Generator
     */
    private Long unitPrice;

    /**
     * 时间: COMMODITY_PRICE.TIME
     * @author MyBatis Generator
     */
    private Date time;

    /**
     * 获取商品合约信息id: COMMODITY_PRICE.COMMODITY_CONTRACT_ID
     * @return 商品合约信息id: COMMODITY_PRICE.COMMODITY_CONTRACT_ID
     * @author MyBatis Generator
     */
    public Long getCommodityContractId() {
        return commodityContractId;
    }

    /**
     * 设置商品合约信息id: COMMODITY_PRICE.COMMODITY_CONTRACT_ID
     * @param commodityContractId 映射数据库字段: COMMODITY_PRICE.COMMODITY_CONTRACT_ID
     * @author MyBatis Generator
     */
    public void setCommodityContractId(Long commodityContractId) {
        this.commodityContractId = commodityContractId;
    }

    /**
     * 获取价格， 单位分: COMMODITY_PRICE.UNIT_PRICE
     * @return 价格， 单位分: COMMODITY_PRICE.UNIT_PRICE
     * @author MyBatis Generator
     */
    public Long getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置价格， 单位分: COMMODITY_PRICE.UNIT_PRICE
     * @param unitPrice 映射数据库字段: COMMODITY_PRICE.UNIT_PRICE
     * @author MyBatis Generator
     */
    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 获取时间: COMMODITY_PRICE.TIME
     * @return 时间: COMMODITY_PRICE.TIME
     * @author MyBatis Generator
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置时间: COMMODITY_PRICE.TIME
     * @param time 映射数据库字段: COMMODITY_PRICE.TIME
     * @author MyBatis Generator
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: COMMODITY_PRICE
     * @author MyBatis Generator
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CommodityPrice other = (CommodityPrice) that;
        return (this.getCommodityContractId() == null ? other.getCommodityContractId() == null : this.getCommodityContractId().equals(other.getCommodityContractId()))
            && (this.getUnitPrice() == null ? other.getUnitPrice() == null : this.getUnitPrice().equals(other.getUnitPrice()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: COMMODITY_PRICE
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommodityContractId() == null) ? 0 : getCommodityContractId().hashCode());
        result = prime * result + ((getUnitPrice() == null) ? 0 : getUnitPrice().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: COMMODITY_PRICE
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", commodityContractId=").append(commodityContractId);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", time=").append(time);
        sb.append("]");
        return sb.toString();
    }
}