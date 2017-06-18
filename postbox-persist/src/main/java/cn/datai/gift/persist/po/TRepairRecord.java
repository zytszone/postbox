package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TRepairRecord implements Serializable {
    public static final long serialVersionUID = -57112791L;

    /**
     * 报修申请ID: t_repair_record.REPAIR_RECORD_ID
     * @author MyBatis Generator
     */
    private Long repairRecordId;

    /**
     * 报修客户ID: t_repair_record.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    private Long customerInfoId;

    /**
     * 维修人员ID: t_repair_record.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    private Long repairerInfoId;

    /**
     * 箱子ID: t_repair_record.BOX_INFO_ID
     * @author MyBatis Generator
     */
    private Long boxInfoId;

    /**
     * 箱子唯一编码: t_repair_record.BOX_UNIQUE_CODE
     * @author MyBatis Generator
     */
    private String boxUniqueCode;

    /**
     * 拍照截图路径: t_repair_record.REPAIR_IMG_PATH
     * @author MyBatis Generator
     */
    private String repairImgPath;

    /**
     * 备注: t_repair_record.REMARK
     * @author MyBatis Generator
     */
    private String remark;

    /**
     * 报销状态,CHECKING待审核,PENDING待处理,REPAIRING修理中,COMPLETE修理完成,CANCEL取消: t_repair_record.STATUS
     * @author MyBatis Generator
     */
    private String status;

    /**
     * 报修时间: t_repair_record.APPLYTIME
     * @author MyBatis Generator
     */
    private Date applytime;

    /**
     * 系统自动备注,可用于自动录入GPS信息、报错信息来源（如待领包裹列表）、审批信息等: t_repair_record.AUTO_REMARK
     * @author MyBatis Generator
     */
    private String autoRemark;

    /**
     * 审核时间: t_repair_record.AUDITTIME
     * @author MyBatis Generator
     */
    private Date audittime;

    /**
     * 修理开始时间: t_repair_record.REPAIRTIME
     * @author MyBatis Generator
     */
    private Date repairtime;

    /**
     * 修理完成时间: t_repair_record.COMPLETETIME
     * @author MyBatis Generator
     */
    private Date completetime;

    /**
     * 获取报修申请ID: t_repair_record.REPAIR_RECORD_ID
     * @return 报修申请ID: t_repair_record.REPAIR_RECORD_ID
     * @author MyBatis Generator
     */
    public Long getRepairRecordId() {
        return repairRecordId;
    }

    /**
     * 设置报修申请ID: t_repair_record.REPAIR_RECORD_ID
     * @param repairRecordId 映射数据库字段: t_repair_record.REPAIR_RECORD_ID
     * @author MyBatis Generator
     */
    public void setRepairRecordId(Long repairRecordId) {
        this.repairRecordId = repairRecordId;
    }

    /**
     * 获取报修客户ID: t_repair_record.CUSTOMER_INFO_ID
     * @return 报修客户ID: t_repair_record.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    /**
     * 设置报修客户ID: t_repair_record.CUSTOMER_INFO_ID
     * @param customerInfoId 映射数据库字段: t_repair_record.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    /**
     * 获取维修人员ID: t_repair_record.REPAIRER_INFO_ID
     * @return 维修人员ID: t_repair_record.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getRepairerInfoId() {
        return repairerInfoId;
    }

    /**
     * 设置维修人员ID: t_repair_record.REPAIRER_INFO_ID
     * @param repairerInfoId 映射数据库字段: t_repair_record.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public void setRepairerInfoId(Long repairerInfoId) {
        this.repairerInfoId = repairerInfoId;
    }

    /**
     * 获取箱子ID: t_repair_record.BOX_INFO_ID
     * @return 箱子ID: t_repair_record.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public Long getBoxInfoId() {
        return boxInfoId;
    }

    /**
     * 设置箱子ID: t_repair_record.BOX_INFO_ID
     * @param boxInfoId 映射数据库字段: t_repair_record.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public void setBoxInfoId(Long boxInfoId) {
        this.boxInfoId = boxInfoId;
    }

    /**
     * 获取箱子唯一编码: t_repair_record.BOX_UNIQUE_CODE
     * @return 箱子唯一编码: t_repair_record.BOX_UNIQUE_CODE
     * @author MyBatis Generator
     */
    public String getBoxUniqueCode() {
        return boxUniqueCode;
    }

    /**
     * 设置箱子唯一编码: t_repair_record.BOX_UNIQUE_CODE
     * @param boxUniqueCode 映射数据库字段: t_repair_record.BOX_UNIQUE_CODE
     * @author MyBatis Generator
     */
    public void setBoxUniqueCode(String boxUniqueCode) {
        this.boxUniqueCode = boxUniqueCode == null ? null : boxUniqueCode.trim();
    }

    /**
     * 获取拍照截图路径: t_repair_record.REPAIR_IMG_PATH
     * @return 拍照截图路径: t_repair_record.REPAIR_IMG_PATH
     * @author MyBatis Generator
     */
    public String getRepairImgPath() {
        return repairImgPath;
    }

    /**
     * 设置拍照截图路径: t_repair_record.REPAIR_IMG_PATH
     * @param repairImgPath 映射数据库字段: t_repair_record.REPAIR_IMG_PATH
     * @author MyBatis Generator
     */
    public void setRepairImgPath(String repairImgPath) {
        this.repairImgPath = repairImgPath == null ? null : repairImgPath.trim();
    }

    /**
     * 获取备注: t_repair_record.REMARK
     * @return 备注: t_repair_record.REMARK
     * @author MyBatis Generator
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注: t_repair_record.REMARK
     * @param remark 映射数据库字段: t_repair_record.REMARK
     * @author MyBatis Generator
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取报销状态,CHECKING待审核,PENDING待处理,REPAIRING修理中,COMPLETE修理完成,CANCEL取消: t_repair_record.STATUS
     * @return 报销状态,CHECKING待审核,PENDING待处理,REPAIRING修理中,COMPLETE修理完成,CANCEL取消: t_repair_record.STATUS
     * @author MyBatis Generator
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置报销状态,CHECKING待审核,PENDING待处理,REPAIRING修理中,COMPLETE修理完成,CANCEL取消: t_repair_record.STATUS
     * @param status 映射数据库字段: t_repair_record.STATUS
     * @author MyBatis Generator
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取报修时间: t_repair_record.APPLYTIME
     * @return 报修时间: t_repair_record.APPLYTIME
     * @author MyBatis Generator
     */
    public Date getApplytime() {
        return applytime;
    }

    /**
     * 设置报修时间: t_repair_record.APPLYTIME
     * @param applytime 映射数据库字段: t_repair_record.APPLYTIME
     * @author MyBatis Generator
     */
    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    /**
     * 获取系统自动备注,可用于自动录入GPS信息、报错信息来源（如待领包裹列表）、审批信息等: t_repair_record.AUTO_REMARK
     * @return 系统自动备注,可用于自动录入GPS信息、报错信息来源（如待领包裹列表）、审批信息等: t_repair_record.AUTO_REMARK
     * @author MyBatis Generator
     */
    public String getAutoRemark() {
        return autoRemark;
    }

    /**
     * 设置系统自动备注,可用于自动录入GPS信息、报错信息来源（如待领包裹列表）、审批信息等: t_repair_record.AUTO_REMARK
     * @param autoRemark 映射数据库字段: t_repair_record.AUTO_REMARK
     * @author MyBatis Generator
     */
    public void setAutoRemark(String autoRemark) {
        this.autoRemark = autoRemark == null ? null : autoRemark.trim();
    }

    /**
     * 获取审核时间: t_repair_record.AUDITTIME
     * @return 审核时间: t_repair_record.AUDITTIME
     * @author MyBatis Generator
     */
    public Date getAudittime() {
        return audittime;
    }

    /**
     * 设置审核时间: t_repair_record.AUDITTIME
     * @param audittime 映射数据库字段: t_repair_record.AUDITTIME
     * @author MyBatis Generator
     */
    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    /**
     * 获取修理开始时间: t_repair_record.REPAIRTIME
     * @return 修理开始时间: t_repair_record.REPAIRTIME
     * @author MyBatis Generator
     */
    public Date getRepairtime() {
        return repairtime;
    }

    /**
     * 设置修理开始时间: t_repair_record.REPAIRTIME
     * @param repairtime 映射数据库字段: t_repair_record.REPAIRTIME
     * @author MyBatis Generator
     */
    public void setRepairtime(Date repairtime) {
        this.repairtime = repairtime;
    }

    /**
     * 获取修理完成时间: t_repair_record.COMPLETETIME
     * @return 修理完成时间: t_repair_record.COMPLETETIME
     * @author MyBatis Generator
     */
    public Date getCompletetime() {
        return completetime;
    }

    /**
     * 设置修理完成时间: t_repair_record.COMPLETETIME
     * @param completetime 映射数据库字段: t_repair_record.COMPLETETIME
     * @author MyBatis Generator
     */
    public void setCompletetime(Date completetime) {
        this.completetime = completetime;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
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
        TRepairRecord other = (TRepairRecord) that;
        return (this.getRepairRecordId() == null ? other.getRepairRecordId() == null : this.getRepairRecordId().equals(other.getRepairRecordId()))
            && (this.getCustomerInfoId() == null ? other.getCustomerInfoId() == null : this.getCustomerInfoId().equals(other.getCustomerInfoId()))
            && (this.getRepairerInfoId() == null ? other.getRepairerInfoId() == null : this.getRepairerInfoId().equals(other.getRepairerInfoId()))
            && (this.getBoxInfoId() == null ? other.getBoxInfoId() == null : this.getBoxInfoId().equals(other.getBoxInfoId()))
            && (this.getBoxUniqueCode() == null ? other.getBoxUniqueCode() == null : this.getBoxUniqueCode().equals(other.getBoxUniqueCode()))
            && (this.getRepairImgPath() == null ? other.getRepairImgPath() == null : this.getRepairImgPath().equals(other.getRepairImgPath()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getApplytime() == null ? other.getApplytime() == null : this.getApplytime().equals(other.getApplytime()))
            && (this.getAutoRemark() == null ? other.getAutoRemark() == null : this.getAutoRemark().equals(other.getAutoRemark()))
            && (this.getAudittime() == null ? other.getAudittime() == null : this.getAudittime().equals(other.getAudittime()))
            && (this.getRepairtime() == null ? other.getRepairtime() == null : this.getRepairtime().equals(other.getRepairtime()))
            && (this.getCompletetime() == null ? other.getCompletetime() == null : this.getCompletetime().equals(other.getCompletetime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRepairRecordId() == null) ? 0 : getRepairRecordId().hashCode());
        result = prime * result + ((getCustomerInfoId() == null) ? 0 : getCustomerInfoId().hashCode());
        result = prime * result + ((getRepairerInfoId() == null) ? 0 : getRepairerInfoId().hashCode());
        result = prime * result + ((getBoxInfoId() == null) ? 0 : getBoxInfoId().hashCode());
        result = prime * result + ((getBoxUniqueCode() == null) ? 0 : getBoxUniqueCode().hashCode());
        result = prime * result + ((getRepairImgPath() == null) ? 0 : getRepairImgPath().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getApplytime() == null) ? 0 : getApplytime().hashCode());
        result = prime * result + ((getAutoRemark() == null) ? 0 : getAutoRemark().hashCode());
        result = prime * result + ((getAudittime() == null) ? 0 : getAudittime().hashCode());
        result = prime * result + ((getRepairtime() == null) ? 0 : getRepairtime().hashCode());
        result = prime * result + ((getCompletetime() == null) ? 0 : getCompletetime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", repairRecordId=").append(repairRecordId);
        sb.append(", customerInfoId=").append(customerInfoId);
        sb.append(", repairerInfoId=").append(repairerInfoId);
        sb.append(", boxInfoId=").append(boxInfoId);
        sb.append(", boxUniqueCode=").append(boxUniqueCode);
        sb.append(", repairImgPath=").append(repairImgPath);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", applytime=").append(applytime);
        sb.append(", autoRemark=").append(autoRemark);
        sb.append(", audittime=").append(audittime);
        sb.append(", repairtime=").append(repairtime);
        sb.append(", completetime=").append(completetime);
        sb.append("]");
        return sb.toString();
    }
}