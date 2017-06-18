package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TRepairerInfo implements Serializable {
    public static final long serialVersionUID = 498070963L;

    /**
     * 维修人员ID: t_repairer_info.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    private Long repairerInfoId;

    /**
     * 维修人员姓名: t_repairer_info.REALNAME
     * @author MyBatis Generator
     */
    private String realname;

    /**
     * 维修人员手机号: t_repairer_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    private String mobilePhone;

    /**
     * 维修人员图片: t_repairer_info.REPAIRER_IMG_PATH
     * @author MyBatis Generator
     */
    private String repairerImgPath;

    /**
     * 状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_repairer_info.STATUS
     * @author MyBatis Generator
     */
    private String status;

    /**
     * 创建时间: t_repairer_info.CREATETIME
     * @author MyBatis Generator
     */
    private Date createtime;

    /**
     * 获取维修人员ID: t_repairer_info.REPAIRER_INFO_ID
     * @return 维修人员ID: t_repairer_info.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getRepairerInfoId() {
        return repairerInfoId;
    }

    /**
     * 设置维修人员ID: t_repairer_info.REPAIRER_INFO_ID
     * @param repairerInfoId 映射数据库字段: t_repairer_info.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public void setRepairerInfoId(Long repairerInfoId) {
        this.repairerInfoId = repairerInfoId;
    }

    /**
     * 获取维修人员姓名: t_repairer_info.REALNAME
     * @return 维修人员姓名: t_repairer_info.REALNAME
     * @author MyBatis Generator
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置维修人员姓名: t_repairer_info.REALNAME
     * @param realname 映射数据库字段: t_repairer_info.REALNAME
     * @author MyBatis Generator
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取维修人员手机号: t_repairer_info.MOBILE_PHONE
     * @return 维修人员手机号: t_repairer_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置维修人员手机号: t_repairer_info.MOBILE_PHONE
     * @param mobilePhone 映射数据库字段: t_repairer_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取维修人员图片: t_repairer_info.REPAIRER_IMG_PATH
     * @return 维修人员图片: t_repairer_info.REPAIRER_IMG_PATH
     * @author MyBatis Generator
     */
    public String getRepairerImgPath() {
        return repairerImgPath;
    }

    /**
     * 设置维修人员图片: t_repairer_info.REPAIRER_IMG_PATH
     * @param repairerImgPath 映射数据库字段: t_repairer_info.REPAIRER_IMG_PATH
     * @author MyBatis Generator
     */
    public void setRepairerImgPath(String repairerImgPath) {
        this.repairerImgPath = repairerImgPath == null ? null : repairerImgPath.trim();
    }

    /**
     * 获取状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_repairer_info.STATUS
     * @return 状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_repairer_info.STATUS
     * @author MyBatis Generator
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_repairer_info.STATUS
     * @param status 映射数据库字段: t_repairer_info.STATUS
     * @author MyBatis Generator
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取创建时间: t_repairer_info.CREATETIME
     * @return 创建时间: t_repairer_info.CREATETIME
     * @author MyBatis Generator
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间: t_repairer_info.CREATETIME
     * @param createtime 映射数据库字段: t_repairer_info.CREATETIME
     * @author MyBatis Generator
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
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
        TRepairerInfo other = (TRepairerInfo) that;
        return (this.getRepairerInfoId() == null ? other.getRepairerInfoId() == null : this.getRepairerInfoId().equals(other.getRepairerInfoId()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()))
            && (this.getRepairerImgPath() == null ? other.getRepairerImgPath() == null : this.getRepairerImgPath().equals(other.getRepairerImgPath()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRepairerInfoId() == null) ? 0 : getRepairerInfoId().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        result = prime * result + ((getRepairerImgPath() == null) ? 0 : getRepairerImgPath().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", repairerInfoId=").append(repairerInfoId);
        sb.append(", realname=").append(realname);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", repairerImgPath=").append(repairerImgPath);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}