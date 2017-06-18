package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class UserWxRelt implements Serializable {
    public static final long serialVersionUID = -1002034640L;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。: user_wx_relt.unionid
     * @author MyBatis Generator
     */
    private String unionid;

    /**
     * 客户ID号: user_wx_relt.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    private Long customerInfoId;

    /**
     * 创建时间: user_wx_relt.create_time
     * @author MyBatis Generator
     */
    private Date createTime;

    /**
     * 注释: user_wx_relt.remark
     * @author MyBatis Generator
     */
    private String remark;

    /**
     * 获取只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。: user_wx_relt.unionid
     * @return 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。: user_wx_relt.unionid
     * @author MyBatis Generator
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。: user_wx_relt.unionid
     * @param unionid 映射数据库字段: user_wx_relt.unionid
     * @author MyBatis Generator
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    /**
     * 获取客户ID号: user_wx_relt.CUSTOMER_INFO_ID
     * @return 客户ID号: user_wx_relt.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    /**
     * 设置客户ID号: user_wx_relt.CUSTOMER_INFO_ID
     * @param customerInfoId 映射数据库字段: user_wx_relt.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    /**
     * 获取创建时间: user_wx_relt.create_time
     * @return 创建时间: user_wx_relt.create_time
     * @author MyBatis Generator
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间: user_wx_relt.create_time
     * @param createTime 映射数据库字段: user_wx_relt.create_time
     * @author MyBatis Generator
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取注释: user_wx_relt.remark
     * @return 注释: user_wx_relt.remark
     * @author MyBatis Generator
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置注释: user_wx_relt.remark
     * @param remark 映射数据库字段: user_wx_relt.remark
     * @author MyBatis Generator
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: user_wx_relt
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
        UserWxRelt other = (UserWxRelt) that;
        return (this.getUnionid() == null ? other.getUnionid() == null : this.getUnionid().equals(other.getUnionid()))
            && (this.getCustomerInfoId() == null ? other.getCustomerInfoId() == null : this.getCustomerInfoId().equals(other.getCustomerInfoId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: user_wx_relt
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUnionid() == null) ? 0 : getUnionid().hashCode());
        result = prime * result + ((getCustomerInfoId() == null) ? 0 : getCustomerInfoId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: user_wx_relt
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", unionid=").append(unionid);
        sb.append(", customerInfoId=").append(customerInfoId);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}