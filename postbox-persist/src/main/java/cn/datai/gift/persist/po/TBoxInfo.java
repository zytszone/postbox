package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TBoxInfo implements Serializable {
    public static final long serialVersionUID = -1235147442L;

    /**
     * 箱子ID: t_box_info.BOX_INFO_ID
     * @author MyBatis Generator
     */
    private Long boxInfoId;

    /**
     * 箱子名称,箱子组名+编号: t_box_info.BOX_NAME
     * @author MyBatis Generator
     */
    private String boxName;

    /**
     * 箱子编号: t_box_info.BOX_CODE
     * @author MyBatis Generator
     */
    private String boxCode;

    /**
     * 内部唯一代码: t_box_info.BOX_UNIQUE_CODE
     * @author MyBatis Generator
     */
    private String boxUniqueCode;

    /**
     * 箱子组ID: t_box_info.BOX_GROUP_ID
     * @author MyBatis Generator
     */
    private Long boxGroupId;

    /**
     * 维修人员ID: t_box_info.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    private Long repairerInfoId;

    /**
     * 加密密钥: t_box_info.SEC_KEY
     * @author MyBatis Generator
     */
    private String secKey;

    /**
     * 基本状态,NOACTIVE未激活,NORMAL正常,REPAIR维修,DEMISE注销: t_box_info.STATUS
     * @author MyBatis Generator
     */
    private String status;

    /**
     * 快件状态,EMPTY空,FULL有货物: t_box_info.EXPRESS_STATUS
     * @author MyBatis Generator
     */
    private String expressStatus;

    /**
     * 快件隶属手机号: t_box_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    private String mobilePhone;

    /**
     * 快件代领人ID号: t_box_info.PROXY_CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    private String proxyCustomerInfoId;

    /**
     * 上次开启时间: t_box_info.OPENTIME
     * @author MyBatis Generator
     */
    private Date opentime;

    /**
     * 校验数值,用于客户端传递的值必须大于服务器保存的值,默认值为0: t_box_info.CHECKNUM
     * @author MyBatis Generator
     */
    private Integer checknum;

    /**
     * 创建时间: t_box_info.CREATETIME
     * @author MyBatis Generator
     */
    private Date createtime;

    /**
     * 获取箱子ID: t_box_info.BOX_INFO_ID
     * @return 箱子ID: t_box_info.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public Long getBoxInfoId() {
        return boxInfoId;
    }

    /**
     * 设置箱子ID: t_box_info.BOX_INFO_ID
     * @param boxInfoId 映射数据库字段: t_box_info.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public void setBoxInfoId(Long boxInfoId) {
        this.boxInfoId = boxInfoId;
    }

    /**
     * 获取箱子名称,箱子组名+编号: t_box_info.BOX_NAME
     * @return 箱子名称,箱子组名+编号: t_box_info.BOX_NAME
     * @author MyBatis Generator
     */
    public String getBoxName() {
        return boxName;
    }

    /**
     * 设置箱子名称,箱子组名+编号: t_box_info.BOX_NAME
     * @param boxName 映射数据库字段: t_box_info.BOX_NAME
     * @author MyBatis Generator
     */
    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }

    /**
     * 获取箱子编号: t_box_info.BOX_CODE
     * @return 箱子编号: t_box_info.BOX_CODE
     * @author MyBatis Generator
     */
    public String getBoxCode() {
        return boxCode;
    }

    /**
     * 设置箱子编号: t_box_info.BOX_CODE
     * @param boxCode 映射数据库字段: t_box_info.BOX_CODE
     * @author MyBatis Generator
     */
    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode == null ? null : boxCode.trim();
    }

    /**
     * 获取内部唯一代码: t_box_info.BOX_UNIQUE_CODE
     * @return 内部唯一代码: t_box_info.BOX_UNIQUE_CODE
     * @author MyBatis Generator
     */
    public String getBoxUniqueCode() {
        return boxUniqueCode;
    }

    /**
     * 设置内部唯一代码: t_box_info.BOX_UNIQUE_CODE
     * @param boxUniqueCode 映射数据库字段: t_box_info.BOX_UNIQUE_CODE
     * @author MyBatis Generator
     */
    public void setBoxUniqueCode(String boxUniqueCode) {
        this.boxUniqueCode = boxUniqueCode == null ? null : boxUniqueCode.trim();
    }

    /**
     * 获取箱子组ID: t_box_info.BOX_GROUP_ID
     * @return 箱子组ID: t_box_info.BOX_GROUP_ID
     * @author MyBatis Generator
     */
    public Long getBoxGroupId() {
        return boxGroupId;
    }

    /**
     * 设置箱子组ID: t_box_info.BOX_GROUP_ID
     * @param boxGroupId 映射数据库字段: t_box_info.BOX_GROUP_ID
     * @author MyBatis Generator
     */
    public void setBoxGroupId(Long boxGroupId) {
        this.boxGroupId = boxGroupId;
    }

    /**
     * 获取维修人员ID: t_box_info.REPAIRER_INFO_ID
     * @return 维修人员ID: t_box_info.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getRepairerInfoId() {
        return repairerInfoId;
    }

    /**
     * 设置维修人员ID: t_box_info.REPAIRER_INFO_ID
     * @param repairerInfoId 映射数据库字段: t_box_info.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public void setRepairerInfoId(Long repairerInfoId) {
        this.repairerInfoId = repairerInfoId;
    }

    /**
     * 获取加密密钥: t_box_info.SEC_KEY
     * @return 加密密钥: t_box_info.SEC_KEY
     * @author MyBatis Generator
     */
    public String getSecKey() {
        return secKey;
    }

    /**
     * 设置加密密钥: t_box_info.SEC_KEY
     * @param secKey 映射数据库字段: t_box_info.SEC_KEY
     * @author MyBatis Generator
     */
    public void setSecKey(String secKey) {
        this.secKey = secKey == null ? null : secKey.trim();
    }

    /**
     * 获取基本状态,NOACTIVE未激活,NORMAL正常,REPAIR维修,DEMISE注销: t_box_info.STATUS
     * @return 基本状态,NOACTIVE未激活,NORMAL正常,REPAIR维修,DEMISE注销: t_box_info.STATUS
     * @author MyBatis Generator
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置基本状态,NOACTIVE未激活,NORMAL正常,REPAIR维修,DEMISE注销: t_box_info.STATUS
     * @param status 映射数据库字段: t_box_info.STATUS
     * @author MyBatis Generator
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取快件状态,EMPTY空,FULL有货物: t_box_info.EXPRESS_STATUS
     * @return 快件状态,EMPTY空,FULL有货物: t_box_info.EXPRESS_STATUS
     * @author MyBatis Generator
     */
    public String getExpressStatus() {
        return expressStatus;
    }

    /**
     * 设置快件状态,EMPTY空,FULL有货物: t_box_info.EXPRESS_STATUS
     * @param expressStatus 映射数据库字段: t_box_info.EXPRESS_STATUS
     * @author MyBatis Generator
     */
    public void setExpressStatus(String expressStatus) {
        this.expressStatus = expressStatus == null ? null : expressStatus.trim();
    }

    /**
     * 获取快件隶属手机号: t_box_info.MOBILE_PHONE
     * @return 快件隶属手机号: t_box_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置快件隶属手机号: t_box_info.MOBILE_PHONE
     * @param mobilePhone 映射数据库字段: t_box_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取快件代领人ID号: t_box_info.PROXY_CUSTOMER_INFO_ID
     * @return 快件代领人ID号: t_box_info.PROXY_CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public String getProxyCustomerInfoId() {
        return proxyCustomerInfoId;
    }

    /**
     * 设置快件代领人ID号: t_box_info.PROXY_CUSTOMER_INFO_ID
     * @param proxyCustomerInfoId 映射数据库字段: t_box_info.PROXY_CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public void setProxyCustomerInfoId(String proxyCustomerInfoId) {
        this.proxyCustomerInfoId = proxyCustomerInfoId == null ? null : proxyCustomerInfoId.trim();
    }

    /**
     * 获取上次开启时间: t_box_info.OPENTIME
     * @return 上次开启时间: t_box_info.OPENTIME
     * @author MyBatis Generator
     */
    public Date getOpentime() {
        return opentime;
    }

    /**
     * 设置上次开启时间: t_box_info.OPENTIME
     * @param opentime 映射数据库字段: t_box_info.OPENTIME
     * @author MyBatis Generator
     */
    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    /**
     * 获取校验数值,用于客户端传递的值必须大于服务器保存的值,默认值为0: t_box_info.CHECKNUM
     * @return 校验数值,用于客户端传递的值必须大于服务器保存的值,默认值为0: t_box_info.CHECKNUM
     * @author MyBatis Generator
     */
    public Integer getChecknum() {
        return checknum;
    }

    /**
     * 设置校验数值,用于客户端传递的值必须大于服务器保存的值,默认值为0: t_box_info.CHECKNUM
     * @param checknum 映射数据库字段: t_box_info.CHECKNUM
     * @author MyBatis Generator
     */
    public void setChecknum(Integer checknum) {
        this.checknum = checknum;
    }

    /**
     * 获取创建时间: t_box_info.CREATETIME
     * @return 创建时间: t_box_info.CREATETIME
     * @author MyBatis Generator
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间: t_box_info.CREATETIME
     * @param createtime 映射数据库字段: t_box_info.CREATETIME
     * @author MyBatis Generator
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_info
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
        TBoxInfo other = (TBoxInfo) that;
        return (this.getBoxInfoId() == null ? other.getBoxInfoId() == null : this.getBoxInfoId().equals(other.getBoxInfoId()))
            && (this.getBoxName() == null ? other.getBoxName() == null : this.getBoxName().equals(other.getBoxName()))
            && (this.getBoxCode() == null ? other.getBoxCode() == null : this.getBoxCode().equals(other.getBoxCode()))
            && (this.getBoxUniqueCode() == null ? other.getBoxUniqueCode() == null : this.getBoxUniqueCode().equals(other.getBoxUniqueCode()))
            && (this.getBoxGroupId() == null ? other.getBoxGroupId() == null : this.getBoxGroupId().equals(other.getBoxGroupId()))
            && (this.getRepairerInfoId() == null ? other.getRepairerInfoId() == null : this.getRepairerInfoId().equals(other.getRepairerInfoId()))
            && (this.getSecKey() == null ? other.getSecKey() == null : this.getSecKey().equals(other.getSecKey()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExpressStatus() == null ? other.getExpressStatus() == null : this.getExpressStatus().equals(other.getExpressStatus()))
            && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()))
            && (this.getProxyCustomerInfoId() == null ? other.getProxyCustomerInfoId() == null : this.getProxyCustomerInfoId().equals(other.getProxyCustomerInfoId()))
            && (this.getOpentime() == null ? other.getOpentime() == null : this.getOpentime().equals(other.getOpentime()))
            && (this.getChecknum() == null ? other.getChecknum() == null : this.getChecknum().equals(other.getChecknum()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_info
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoxInfoId() == null) ? 0 : getBoxInfoId().hashCode());
        result = prime * result + ((getBoxName() == null) ? 0 : getBoxName().hashCode());
        result = prime * result + ((getBoxCode() == null) ? 0 : getBoxCode().hashCode());
        result = prime * result + ((getBoxUniqueCode() == null) ? 0 : getBoxUniqueCode().hashCode());
        result = prime * result + ((getBoxGroupId() == null) ? 0 : getBoxGroupId().hashCode());
        result = prime * result + ((getRepairerInfoId() == null) ? 0 : getRepairerInfoId().hashCode());
        result = prime * result + ((getSecKey() == null) ? 0 : getSecKey().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExpressStatus() == null) ? 0 : getExpressStatus().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        result = prime * result + ((getProxyCustomerInfoId() == null) ? 0 : getProxyCustomerInfoId().hashCode());
        result = prime * result + ((getOpentime() == null) ? 0 : getOpentime().hashCode());
        result = prime * result + ((getChecknum() == null) ? 0 : getChecknum().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_info
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", boxInfoId=").append(boxInfoId);
        sb.append(", boxName=").append(boxName);
        sb.append(", boxCode=").append(boxCode);
        sb.append(", boxUniqueCode=").append(boxUniqueCode);
        sb.append(", boxGroupId=").append(boxGroupId);
        sb.append(", repairerInfoId=").append(repairerInfoId);
        sb.append(", secKey=").append(secKey);
        sb.append(", status=").append(status);
        sb.append(", expressStatus=").append(expressStatus);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", proxyCustomerInfoId=").append(proxyCustomerInfoId);
        sb.append(", opentime=").append(opentime);
        sb.append(", checknum=").append(checknum);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}