package cn.datai.gift.persist.po;

import java.io.Serializable;

public class SystemParam implements Serializable {
    public static final long serialVersionUID = 295881653L;

    /**
     * : SYSTEM_PARAM.SYSTEM_PARAM_ID
     * @author MyBatis Generator
     */
    private Integer systemParamId;

    /**
     * 名称: SYSTEM_PARAM.NAME
     * @author MyBatis Generator
     */
    private String name;

    /**
     * 值: SYSTEM_PARAM.VALUE
     * @author MyBatis Generator
     */
    private String value;

    /**
     * 注释: SYSTEM_PARAM.REMARK
     * @author MyBatis Generator
     */
    private String remark;

    /**
     * 获取: SYSTEM_PARAM.SYSTEM_PARAM_ID
     * @return : SYSTEM_PARAM.SYSTEM_PARAM_ID
     * @author MyBatis Generator
     */
    public Integer getSystemParamId() {
        return systemParamId;
    }

    /**
     * 设置: SYSTEM_PARAM.SYSTEM_PARAM_ID
     * @param systemParamId 映射数据库字段: SYSTEM_PARAM.SYSTEM_PARAM_ID
     * @author MyBatis Generator
     */
    public void setSystemParamId(Integer systemParamId) {
        this.systemParamId = systemParamId;
    }

    /**
     * 获取名称: SYSTEM_PARAM.NAME
     * @return 名称: SYSTEM_PARAM.NAME
     * @author MyBatis Generator
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称: SYSTEM_PARAM.NAME
     * @param name 映射数据库字段: SYSTEM_PARAM.NAME
     * @author MyBatis Generator
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取值: SYSTEM_PARAM.VALUE
     * @return 值: SYSTEM_PARAM.VALUE
     * @author MyBatis Generator
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置值: SYSTEM_PARAM.VALUE
     * @param value 映射数据库字段: SYSTEM_PARAM.VALUE
     * @author MyBatis Generator
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取注释: SYSTEM_PARAM.REMARK
     * @return 注释: SYSTEM_PARAM.REMARK
     * @author MyBatis Generator
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置注释: SYSTEM_PARAM.REMARK
     * @param remark 映射数据库字段: SYSTEM_PARAM.REMARK
     * @author MyBatis Generator
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: SYSTEM_PARAM
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
        SystemParam other = (SystemParam) that;
        return (this.getSystemParamId() == null ? other.getSystemParamId() == null : this.getSystemParamId().equals(other.getSystemParamId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: SYSTEM_PARAM
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSystemParamId() == null) ? 0 : getSystemParamId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: SYSTEM_PARAM
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", systemParamId=").append(systemParamId);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}