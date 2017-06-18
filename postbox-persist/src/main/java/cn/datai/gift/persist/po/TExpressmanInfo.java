package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TExpressmanInfo implements Serializable {
    public static final long serialVersionUID = 570855907L;

    /**
     * 快递员ID号: t_expressman_info.EXPRESSMAN_INFO_ID
     * @author MyBatis Generator
     */
    private Long expressmanInfoId;

    /**
     * 映射客户ID: t_expressman_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    private Long customerInfoId;

    /**
     * 所属快递公司: t_expressman_info.EXPRESS_COMPANY
     * @author MyBatis Generator
     */
    private String expressCompany;

    /**
     * 身份证图片存储路径: t_expressman_info.IDCARD_IMG_PATH
     * @author MyBatis Generator
     */
    private String idcardImgPath;

    /**
     * 工作证件照存储路径: t_expressman_info.WORKCARD_IMG_PATH
     * @author MyBatis Generator
     */
    private String workcardImgPath;

    /**
     * 状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_expressman_info.STATUS
     * @author MyBatis Generator
     */
    private String status;

    /**
     * 申请时间: t_expressman_info.APPLYTIME
     * @author MyBatis Generator
     */
    private Date applytime;

    /**
     * 激活时间: t_expressman_info.ACTIVETIME
     * @author MyBatis Generator
     */
    private Date activetime;

    /**
     * 获取快递员ID号: t_expressman_info.EXPRESSMAN_INFO_ID
     * @return 快递员ID号: t_expressman_info.EXPRESSMAN_INFO_ID
     * @author MyBatis Generator
     */
    public Long getExpressmanInfoId() {
        return expressmanInfoId;
    }

    /**
     * 设置快递员ID号: t_expressman_info.EXPRESSMAN_INFO_ID
     * @param expressmanInfoId 映射数据库字段: t_expressman_info.EXPRESSMAN_INFO_ID
     * @author MyBatis Generator
     */
    public void setExpressmanInfoId(Long expressmanInfoId) {
        this.expressmanInfoId = expressmanInfoId;
    }

    /**
     * 获取映射客户ID: t_expressman_info.CUSTOMER_INFO_ID
     * @return 映射客户ID: t_expressman_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    /**
     * 设置映射客户ID: t_expressman_info.CUSTOMER_INFO_ID
     * @param customerInfoId 映射数据库字段: t_expressman_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    /**
     * 获取所属快递公司: t_expressman_info.EXPRESS_COMPANY
     * @return 所属快递公司: t_expressman_info.EXPRESS_COMPANY
     * @author MyBatis Generator
     */
    public String getExpressCompany() {
        return expressCompany;
    }

    /**
     * 设置所属快递公司: t_expressman_info.EXPRESS_COMPANY
     * @param expressCompany 映射数据库字段: t_expressman_info.EXPRESS_COMPANY
     * @author MyBatis Generator
     */
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }

    /**
     * 获取身份证图片存储路径: t_expressman_info.IDCARD_IMG_PATH
     * @return 身份证图片存储路径: t_expressman_info.IDCARD_IMG_PATH
     * @author MyBatis Generator
     */
    public String getIdcardImgPath() {
        return idcardImgPath;
    }

    /**
     * 设置身份证图片存储路径: t_expressman_info.IDCARD_IMG_PATH
     * @param idcardImgPath 映射数据库字段: t_expressman_info.IDCARD_IMG_PATH
     * @author MyBatis Generator
     */
    public void setIdcardImgPath(String idcardImgPath) {
        this.idcardImgPath = idcardImgPath == null ? null : idcardImgPath.trim();
    }

    /**
     * 获取工作证件照存储路径: t_expressman_info.WORKCARD_IMG_PATH
     * @return 工作证件照存储路径: t_expressman_info.WORKCARD_IMG_PATH
     * @author MyBatis Generator
     */
    public String getWorkcardImgPath() {
        return workcardImgPath;
    }

    /**
     * 设置工作证件照存储路径: t_expressman_info.WORKCARD_IMG_PATH
     * @param workcardImgPath 映射数据库字段: t_expressman_info.WORKCARD_IMG_PATH
     * @author MyBatis Generator
     */
    public void setWorkcardImgPath(String workcardImgPath) {
        this.workcardImgPath = workcardImgPath == null ? null : workcardImgPath.trim();
    }

    /**
     * 获取状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_expressman_info.STATUS
     * @return 状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_expressman_info.STATUS
     * @author MyBatis Generator
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_expressman_info.STATUS
     * @param status 映射数据库字段: t_expressman_info.STATUS
     * @author MyBatis Generator
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取申请时间: t_expressman_info.APPLYTIME
     * @return 申请时间: t_expressman_info.APPLYTIME
     * @author MyBatis Generator
     */
    public Date getApplytime() {
        return applytime;
    }

    /**
     * 设置申请时间: t_expressman_info.APPLYTIME
     * @param applytime 映射数据库字段: t_expressman_info.APPLYTIME
     * @author MyBatis Generator
     */
    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    /**
     * 获取激活时间: t_expressman_info.ACTIVETIME
     * @return 激活时间: t_expressman_info.ACTIVETIME
     * @author MyBatis Generator
     */
    public Date getActivetime() {
        return activetime;
    }

    /**
     * 设置激活时间: t_expressman_info.ACTIVETIME
     * @param activetime 映射数据库字段: t_expressman_info.ACTIVETIME
     * @author MyBatis Generator
     */
    public void setActivetime(Date activetime) {
        this.activetime = activetime;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
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
        TExpressmanInfo other = (TExpressmanInfo) that;
        return (this.getExpressmanInfoId() == null ? other.getExpressmanInfoId() == null : this.getExpressmanInfoId().equals(other.getExpressmanInfoId()))
            && (this.getCustomerInfoId() == null ? other.getCustomerInfoId() == null : this.getCustomerInfoId().equals(other.getCustomerInfoId()))
            && (this.getExpressCompany() == null ? other.getExpressCompany() == null : this.getExpressCompany().equals(other.getExpressCompany()))
            && (this.getIdcardImgPath() == null ? other.getIdcardImgPath() == null : this.getIdcardImgPath().equals(other.getIdcardImgPath()))
            && (this.getWorkcardImgPath() == null ? other.getWorkcardImgPath() == null : this.getWorkcardImgPath().equals(other.getWorkcardImgPath()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getApplytime() == null ? other.getApplytime() == null : this.getApplytime().equals(other.getApplytime()))
            && (this.getActivetime() == null ? other.getActivetime() == null : this.getActivetime().equals(other.getActivetime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExpressmanInfoId() == null) ? 0 : getExpressmanInfoId().hashCode());
        result = prime * result + ((getCustomerInfoId() == null) ? 0 : getCustomerInfoId().hashCode());
        result = prime * result + ((getExpressCompany() == null) ? 0 : getExpressCompany().hashCode());
        result = prime * result + ((getIdcardImgPath() == null) ? 0 : getIdcardImgPath().hashCode());
        result = prime * result + ((getWorkcardImgPath() == null) ? 0 : getWorkcardImgPath().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getApplytime() == null) ? 0 : getApplytime().hashCode());
        result = prime * result + ((getActivetime() == null) ? 0 : getActivetime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", expressmanInfoId=").append(expressmanInfoId);
        sb.append(", customerInfoId=").append(customerInfoId);
        sb.append(", expressCompany=").append(expressCompany);
        sb.append(", idcardImgPath=").append(idcardImgPath);
        sb.append(", workcardImgPath=").append(workcardImgPath);
        sb.append(", status=").append(status);
        sb.append(", applytime=").append(applytime);
        sb.append(", activetime=").append(activetime);
        sb.append("]");
        return sb.toString();
    }
}