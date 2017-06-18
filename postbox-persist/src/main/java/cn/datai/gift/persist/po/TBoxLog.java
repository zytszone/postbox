package cn.datai.gift.persist.po;

import java.io.Serializable;

public class TBoxLog implements Serializable {
    public static final long serialVersionUID = -1702408540L;

    /**
     * 开启记录ID: t_box_log.BOX_LOG_ID
     * @author MyBatis Generator
     */
    private Long boxLogId;

    /**
     * 箱子侧记录的ID号: t_box_log.RECORD_ID
     * @author MyBatis Generator
     */
    private Long recordId;

    /**
     * 箱子ID: t_box_log.BOX_INFO_ID
     * @author MyBatis Generator
     */
    private Long boxInfoId;

    /**
     * 开启前密码输入错误次数,默认0: t_box_log.ERROR_COUNT
     * @author MyBatis Generator
     */
    private Integer errorCount;

    /**
     * 记录登记时间: t_box_log.ENTERTIME
     * @author MyBatis Generator
     */
    private String entertime;

    /**
     * 获取开启记录ID: t_box_log.BOX_LOG_ID
     * @return 开启记录ID: t_box_log.BOX_LOG_ID
     * @author MyBatis Generator
     */
    public Long getBoxLogId() {
        return boxLogId;
    }

    /**
     * 设置开启记录ID: t_box_log.BOX_LOG_ID
     * @param boxLogId 映射数据库字段: t_box_log.BOX_LOG_ID
     * @author MyBatis Generator
     */
    public void setBoxLogId(Long boxLogId) {
        this.boxLogId = boxLogId;
    }

    /**
     * 获取箱子侧记录的ID号: t_box_log.RECORD_ID
     * @return 箱子侧记录的ID号: t_box_log.RECORD_ID
     * @author MyBatis Generator
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * 设置箱子侧记录的ID号: t_box_log.RECORD_ID
     * @param recordId 映射数据库字段: t_box_log.RECORD_ID
     * @author MyBatis Generator
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取箱子ID: t_box_log.BOX_INFO_ID
     * @return 箱子ID: t_box_log.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public Long getBoxInfoId() {
        return boxInfoId;
    }

    /**
     * 设置箱子ID: t_box_log.BOX_INFO_ID
     * @param boxInfoId 映射数据库字段: t_box_log.BOX_INFO_ID
     * @author MyBatis Generator
     */
    public void setBoxInfoId(Long boxInfoId) {
        this.boxInfoId = boxInfoId;
    }

    /**
     * 获取开启前密码输入错误次数,默认0: t_box_log.ERROR_COUNT
     * @return 开启前密码输入错误次数,默认0: t_box_log.ERROR_COUNT
     * @author MyBatis Generator
     */
    public Integer getErrorCount() {
        return errorCount;
    }

    /**
     * 设置开启前密码输入错误次数,默认0: t_box_log.ERROR_COUNT
     * @param errorCount 映射数据库字段: t_box_log.ERROR_COUNT
     * @author MyBatis Generator
     */
    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * 获取记录登记时间: t_box_log.ENTERTIME
     * @return 记录登记时间: t_box_log.ENTERTIME
     * @author MyBatis Generator
     */
    public String getEntertime() {
        return entertime;
    }

    /**
     * 设置记录登记时间: t_box_log.ENTERTIME
     * @param entertime 映射数据库字段: t_box_log.ENTERTIME
     * @author MyBatis Generator
     */
    public void setEntertime(String entertime) {
        this.entertime = entertime == null ? null : entertime.trim();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
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
        TBoxLog other = (TBoxLog) that;
        return (this.getBoxLogId() == null ? other.getBoxLogId() == null : this.getBoxLogId().equals(other.getBoxLogId()))
            && (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getBoxInfoId() == null ? other.getBoxInfoId() == null : this.getBoxInfoId().equals(other.getBoxInfoId()))
            && (this.getErrorCount() == null ? other.getErrorCount() == null : this.getErrorCount().equals(other.getErrorCount()))
            && (this.getEntertime() == null ? other.getEntertime() == null : this.getEntertime().equals(other.getEntertime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoxLogId() == null) ? 0 : getBoxLogId().hashCode());
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getBoxInfoId() == null) ? 0 : getBoxInfoId().hashCode());
        result = prime * result + ((getErrorCount() == null) ? 0 : getErrorCount().hashCode());
        result = prime * result + ((getEntertime() == null) ? 0 : getEntertime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", boxLogId=").append(boxLogId);
        sb.append(", recordId=").append(recordId);
        sb.append(", boxInfoId=").append(boxInfoId);
        sb.append(", errorCount=").append(errorCount);
        sb.append(", entertime=").append(entertime);
        sb.append("]");
        return sb.toString();
    }
}