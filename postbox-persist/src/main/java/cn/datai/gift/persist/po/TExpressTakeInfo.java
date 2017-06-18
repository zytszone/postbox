package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TExpressTakeInfo implements Serializable {
    public static final long serialVersionUID = -165045094L;

    /**
     * 领取历史ID: t_express_take_info.EXPRESS_TAKE_INFO
     * @author MyBatis Generator
     */
    private Long expressTakeInfo;

    /**
     * 箱子ID: t_express_take_info.BOX_INFO_ID
     * @author MyBatis Generator
     */
    private Long boxInfoId;

    /**
     * 箱子名称（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_NAME
     * @author MyBatis Generator
     */
    private String boxName;

    /**
     * 箱子编号（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_CODE
     * @author MyBatis Generator
     */
    private String boxCode;

    /**
     * 客户ID: t_express_take_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    private Long customerInfoId;

    /**
     * 手机号: t_express_take_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    private String mobilePhone;

    /**
     * 代领人信息,JSON数据,包含name,mobile,openid: t_express_take_info.PROXY_CUSTOMER
     * @author MyBatis Generator
     */
    private String proxyCustomer;

    /**
     * 投递时间,该值为箱子上次开启的时间: t_express_take_info.SENDTIME
     * @author MyBatis Generator
     */
    private Date sendtime;

    /**
     * 领取时间: t_express_take_info.TAKETIME
     * @author MyBatis Generator
     */
    private Date taketime;

    /**
     * 自动备注信息,包含箱子组/箱子的位置记录、编号,代领人手机号（如果有）等: t_express_take_info.REMARK
     * @author MyBatis Generator
     */
    private String remark;

    /**
     * 获取领取历史ID: t_express_take_info.EXPRESS_TAKE_INFO
     * @return 领取历史ID: t_express_take_info.EXPRESS_TAKE_INFO
     * @author MyBatis Generator
     */
    public Long getExpressTakeInfo() {
        return expressTakeInfo;
    }

    /**
     * 设置领取历史ID: t_express_take_info.EXPRESS_TAKE_INFO
     * @param expressTakeInfo 映射数据库字段: t_express_take_info.EXPRESS_TAKE_INFO
     * @author MyBatis Generator
     */
    public void setExpressTakeInfo(Long expressTakeInfo) {
        this.expressTakeInfo = expressTakeInfo;
    }

    /**
     * 获取箱子ID: t_express_take_info.BOX_INFO_ID
     * @return 箱子ID: t_express_take_info.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public Long getBoxInfoId() {
        return boxInfoId;
    }

    /**
     * 设置箱子ID: t_express_take_info.BOX_INFO_ID
     * @param boxInfoId 映射数据库字段: t_express_take_info.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public void setBoxInfoId(Long boxInfoId) {
        this.boxInfoId = boxInfoId;
    }

    /**
     * 获取箱子名称（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_NAME
     * @return 箱子名称（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_NAME
     * @author MyBatis Generator
     */
    public String getBoxName() {
        return boxName;
    }

    /**
     * 设置箱子名称（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_NAME
     * @param boxName 映射数据库字段: t_express_take_info.BOX_NAME
     * @author MyBatis Generator
     */
    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }

    /**
     * 获取箱子编号（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_CODE
     * @return 箱子编号（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_CODE
     * @author MyBatis Generator
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * 设置箱子编号（冗余，防止箱子信息产生变动影响历史记录）: t_express_take_info.BOX_CODE
     * @param boxCode 映射数据库字段: t_express_take_info.BOX_CODE
     * @author MyBatis Generator
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode == null ? null : boxCode.trim();
    }

    /**
     * 获取客户ID: t_express_take_info.CUSTOMER_INFO_ID
     * @return 客户ID: t_express_take_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    /**
     * 设置客户ID: t_express_take_info.CUSTOMER_INFO_ID
     * @param customerInfoId 映射数据库字段: t_express_take_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    /**
     * 获取手机号: t_express_take_info.MOBILE_PHONE
     * @return 手机号: t_express_take_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号: t_express_take_info.MOBILE_PHONE
     * @param mobilePhone 映射数据库字段: t_express_take_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取代领人信息,JSON数据,包含name,mobile,openid: t_express_take_info.PROXY_CUSTOMER
     * @return 代领人信息,JSON数据,包含name,mobile,openid: t_express_take_info.PROXY_CUSTOMER
     * @author MyBatis Generator
     */
    public String getProxyCustomer() {
        return proxyCustomer;
    }

    /**
     * 设置代领人信息,JSON数据,包含name,mobile,openid: t_express_take_info.PROXY_CUSTOMER
     * @param proxyCustomer 映射数据库字段: t_express_take_info.PROXY_CUSTOMER
     * @author MyBatis Generator
     */
    public void setProxyCustomer(String proxyCustomer) {
        this.proxyCustomer = proxyCustomer == null ? null : proxyCustomer.trim();
    }

    /**
     * 获取投递时间,该值为箱子上次开启的时间: t_express_take_info.SENDTIME
     * @return 投递时间,该值为箱子上次开启的时间: t_express_take_info.SENDTIME
     * @author MyBatis Generator
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * 设置投递时间,该值为箱子上次开启的时间: t_express_take_info.SENDTIME
     * @param sendtime 映射数据库字段: t_express_take_info.SENDTIME
     * @author MyBatis Generator
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * 获取领取时间: t_express_take_info.TAKETIME
     * @return 领取时间: t_express_take_info.TAKETIME
     * @author MyBatis Generator
     */
    public Date getTaketime() {
        return taketime;
    }

    /**
     * 设置领取时间: t_express_take_info.TAKETIME
     * @param taketime 映射数据库字段: t_express_take_info.TAKETIME
     * @author MyBatis Generator
     */
    public void setTaketime(Date taketime) {
        this.taketime = taketime;
    }

    /**
     * 获取自动备注信息,包含箱子组/箱子的位置记录、编号,代领人手机号（如果有）等: t_express_take_info.REMARK
     * @return 自动备注信息,包含箱子组/箱子的位置记录、编号,代领人手机号（如果有）等: t_express_take_info.REMARK
     * @author MyBatis Generator
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置自动备注信息,包含箱子组/箱子的位置记录、编号,代领人手机号（如果有）等: t_express_take_info.REMARK
     * @param remark 映射数据库字段: t_express_take_info.REMARK
     * @author MyBatis Generator
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
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
        TExpressTakeInfo other = (TExpressTakeInfo) that;
        return (this.getExpressTakeInfo() == null ? other.getExpressTakeInfo() == null : this.getExpressTakeInfo().equals(other.getExpressTakeInfo()))
            && (this.getBoxInfoId() == null ? other.getBoxInfoId() == null : this.getBoxInfoId().equals(other.getBoxInfoId()))
            && (this.getBoxName() == null ? other.getBoxName() == null : this.getBoxName().equals(other.getBoxName()))
            && (this.getBoxCode() == null ? other.getBoxCode() == null : this.getBoxCode().equals(other.getBoxCode()))
            && (this.getCustomerInfoId() == null ? other.getCustomerInfoId() == null : this.getCustomerInfoId().equals(other.getCustomerInfoId()))
            && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()))
            && (this.getProxyCustomer() == null ? other.getProxyCustomer() == null : this.getProxyCustomer().equals(other.getProxyCustomer()))
            && (this.getSendtime() == null ? other.getSendtime() == null : this.getSendtime().equals(other.getSendtime()))
            && (this.getTaketime() == null ? other.getTaketime() == null : this.getTaketime().equals(other.getTaketime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExpressTakeInfo() == null) ? 0 : getExpressTakeInfo().hashCode());
        result = prime * result + ((getBoxInfoId() == null) ? 0 : getBoxInfoId().hashCode());
        result = prime * result + ((getBoxName() == null) ? 0 : getBoxName().hashCode());
        result = prime * result + ((getBoxCode() == null) ? 0 : getBoxCode().hashCode());
        result = prime * result + ((getCustomerInfoId() == null) ? 0 : getCustomerInfoId().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        result = prime * result + ((getProxyCustomer() == null) ? 0 : getProxyCustomer().hashCode());
        result = prime * result + ((getSendtime() == null) ? 0 : getSendtime().hashCode());
        result = prime * result + ((getTaketime() == null) ? 0 : getTaketime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", expressTakeInfo=").append(expressTakeInfo);
        sb.append(", boxInfoId=").append(boxInfoId);
        sb.append(", boxName=").append(boxName);
        sb.append(", boxCode=").append(boxCode);
        sb.append(", customerInfoId=").append(customerInfoId);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", proxyCustomer=").append(proxyCustomer);
        sb.append(", sendtime=").append(sendtime);
        sb.append(", taketime=").append(taketime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}