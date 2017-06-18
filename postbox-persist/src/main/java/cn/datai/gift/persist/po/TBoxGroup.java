package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TBoxGroup implements Serializable {
    public static final long serialVersionUID = 363416031L;

    /**
     * 箱子组ID: t_box_group.BOX_GROUP_ID
     * @author MyBatis Generator
     */
    private Long boxGroupId;

    /**
     * 名称: t_box_group.BOX_NAME
     * @author MyBatis Generator
     */
    private String boxName;

    /**
     * 详细地址: t_box_group.ADDRESS
     * @author MyBatis Generator
     */
    private String address;

    /**
     * 维修人员ID: t_box_group.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    private Long repairerInfoId;

    /**
     * 状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_box_group.STATUS
     * @author MyBatis Generator
     */
    private String status;

    /**
     * 创建时间: t_box_group.CREATETIME
     * @author MyBatis Generator
     */
    private Date createtime;

    /**
     * 获取箱子组ID: t_box_group.BOX_GROUP_ID
     * @return 箱子组ID: t_box_group.BOX_GROUP_ID
     * @author MyBatis Generator
     */
    public Long getBoxGroupId() {
        return boxGroupId;
    }

    /**
     * 设置箱子组ID: t_box_group.BOX_GROUP_ID
     * @param boxGroupId 映射数据库字段: t_box_group.BOX_GROUP_ID
     * @author MyBatis Generator
     */
    public void setBoxGroupId(Long boxGroupId) {
        this.boxGroupId = boxGroupId;
    }

    /**
     * 获取名称: t_box_group.BOX_NAME
     * @return 名称: t_box_group.BOX_NAME
     * @author MyBatis Generator
     */
    public String getBoxName() {
        return boxName;
    }

    /**
     * 设置名称: t_box_group.BOX_NAME
     * @param boxName 映射数据库字段: t_box_group.BOX_NAME
     * @author MyBatis Generator
     */
    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }

    /**
     * 获取详细地址: t_box_group.ADDRESS
     * @return 详细地址: t_box_group.ADDRESS
     * @author MyBatis Generator
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址: t_box_group.ADDRESS
     * @param address 映射数据库字段: t_box_group.ADDRESS
     * @author MyBatis Generator
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取维修人员ID: t_box_group.REPAIRER_INFO_ID
     * @return 维修人员ID: t_box_group.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getRepairerInfoId() {
        return repairerInfoId;
    }

    /**
     * 设置维修人员ID: t_box_group.REPAIRER_INFO_ID
     * @param repairerInfoId 映射数据库字段: t_box_group.REPAIRER_INFO_ID
     * @author MyBatis Generator
     */
    public void setRepairerInfoId(Long repairerInfoId) {
        this.repairerInfoId = repairerInfoId;
    }

    /**
     * 获取状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_box_group.STATUS
     * @return 状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_box_group.STATUS
     * @author MyBatis Generator
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态,NOACTIVE未激活,NORMAL正常,FROZEN冻结,DEMISE销户: t_box_group.STATUS
     * @param status 映射数据库字段: t_box_group.STATUS
     * @author MyBatis Generator
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取创建时间: t_box_group.CREATETIME
     * @return 创建时间: t_box_group.CREATETIME
     * @author MyBatis Generator
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间: t_box_group.CREATETIME
     * @param createtime 映射数据库字段: t_box_group.CREATETIME
     * @author MyBatis Generator
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_group
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
        TBoxGroup other = (TBoxGroup) that;
        return (this.getBoxGroupId() == null ? other.getBoxGroupId() == null : this.getBoxGroupId().equals(other.getBoxGroupId()))
            && (this.getBoxName() == null ? other.getBoxName() == null : this.getBoxName().equals(other.getBoxName()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getRepairerInfoId() == null ? other.getRepairerInfoId() == null : this.getRepairerInfoId().equals(other.getRepairerInfoId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_group
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoxGroupId() == null) ? 0 : getBoxGroupId().hashCode());
        result = prime * result + ((getBoxName() == null) ? 0 : getBoxName().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getRepairerInfoId() == null) ? 0 : getRepairerInfoId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_group
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", boxGroupId=").append(boxGroupId);
        sb.append(", boxName=").append(boxName);
        sb.append(", address=").append(address);
        sb.append(", repairerInfoId=").append(repairerInfoId);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}