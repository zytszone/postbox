package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TBoxInfo implements Serializable {
    public static final long serialVersionUID = -1235147442L;

    /**
     * ID号: T_BOX_INFO.ID
     * @author MyBatis Generator
     */
    private Long id;

    /**
     * 密钥: T_BOX_INFO.SKEY
     * @author MyBatis Generator
     */
    private String skey;

    /**
     * 装货状态,E空,F满: T_BOX_INFO.MSTATUS
     * @author MyBatis Generator
     */
    private String mstatus;

    /**
     * 属主手机号: T_BOX_INFO.MOBILENO
     * @author MyBatis Generator
     */
    private String mobileno;

    /**
     * 上次开启时间: T_BOX_INFO.OPENTIME
     * @author MyBatis Generator
     */
    private Date opentime;

    /**
     * 创建时间: T_BOX_INFO.CREATETIME
     * @author MyBatis Generator
     */
    private Date createtime;

    /**
     * 获取ID号: T_BOX_INFO.ID
     * @return ID号: T_BOX_INFO.ID
     * @author MyBatis Generator
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID号: T_BOX_INFO.ID
     * @param id 映射数据库字段: T_BOX_INFO.ID
     * @author MyBatis Generator
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取密钥: T_BOX_INFO.SKEY
     * @return 密钥: T_BOX_INFO.SKEY
     * @author MyBatis Generator
     */
    public String getSkey() {
        return skey;
    }

    /**
     * 设置密钥: T_BOX_INFO.SKEY
     * @param skey 映射数据库字段: T_BOX_INFO.SKEY
     * @author MyBatis Generator
     */
    public void setSkey(String skey) {
        this.skey = skey == null ? null : skey.trim();
    }

    /**
     * 获取装货状态,E空,F满: T_BOX_INFO.MSTATUS
     * @return 装货状态,E空,F满: T_BOX_INFO.MSTATUS
     * @author MyBatis Generator
     */
    public String getMstatus() {
        return mstatus;
    }

    /**
     * 设置装货状态,E空,F满: T_BOX_INFO.MSTATUS
     * @param mstatus 映射数据库字段: T_BOX_INFO.MSTATUS
     * @author MyBatis Generator
     */
    public void setMstatus(String mstatus) {
        this.mstatus = mstatus == null ? null : mstatus.trim();
    }

    /**
     * 获取属主手机号: T_BOX_INFO.MOBILENO
     * @return 属主手机号: T_BOX_INFO.MOBILENO
     * @author MyBatis Generator
     */
    public String getMobileno() {
        return mobileno;
    }

    /**
     * 设置属主手机号: T_BOX_INFO.MOBILENO
     * @param mobileno 映射数据库字段: T_BOX_INFO.MOBILENO
     * @author MyBatis Generator
     */
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    /**
     * 获取上次开启时间: T_BOX_INFO.OPENTIME
     * @return 上次开启时间: T_BOX_INFO.OPENTIME
     * @author MyBatis Generator
     */
    public Date getOpentime() {
        return opentime;
    }

    /**
     * 设置上次开启时间: T_BOX_INFO.OPENTIME
     * @param opentime 映射数据库字段: T_BOX_INFO.OPENTIME
     * @author MyBatis Generator
     */
    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    /**
     * 获取创建时间: T_BOX_INFO.CREATETIME
     * @return 创建时间: T_BOX_INFO.CREATETIME
     * @author MyBatis Generator
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间: T_BOX_INFO.CREATETIME
     * @param createtime 映射数据库字段: T_BOX_INFO.CREATETIME
     * @author MyBatis Generator
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
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
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSkey() == null ? other.getSkey() == null : this.getSkey().equals(other.getSkey()))
            && (this.getMstatus() == null ? other.getMstatus() == null : this.getMstatus().equals(other.getMstatus()))
            && (this.getMobileno() == null ? other.getMobileno() == null : this.getMobileno().equals(other.getMobileno()))
            && (this.getOpentime() == null ? other.getOpentime() == null : this.getOpentime().equals(other.getOpentime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSkey() == null) ? 0 : getSkey().hashCode());
        result = prime * result + ((getMstatus() == null) ? 0 : getMstatus().hashCode());
        result = prime * result + ((getMobileno() == null) ? 0 : getMobileno().hashCode());
        result = prime * result + ((getOpentime() == null) ? 0 : getOpentime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", id=").append(id);
        sb.append(", skey=").append(skey);
        sb.append(", mstatus=").append(mstatus);
        sb.append(", mobileno=").append(mobileno);
        sb.append(", opentime=").append(opentime);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}