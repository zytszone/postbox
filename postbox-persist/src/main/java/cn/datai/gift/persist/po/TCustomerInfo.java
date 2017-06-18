package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class TCustomerInfo implements Serializable {
    public static final long serialVersionUID = 1028318871L;

    /**
     * 客户ID号: t_customer_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    private Long customerInfoId;

    /**
     * 绑定的外部帐号: t_customer_info.BIND_ACCOUNT
     * @author MyBatis Generator
     */
    private String bindAccount;

    /**
     * 登录名,唯一,自动生成,用户只能修改一次: t_customer_info.LOGIN_NAME
     * @author MyBatis Generator
     */
    private String loginName;

    /**
     * 登录密码: t_customer_info.PASSWORD
     * @author MyBatis Generator
     */
    private String password;

    /**
     * 国家: t_customer_info.COUNTRY
     * @author MyBatis Generator
     */
    private String country;

    /**
     * 省份: t_customer_info.PROVINCE
     * @author MyBatis Generator
     */
    private String province;

    /**
     * 城市: t_customer_info.CITY
     * @author MyBatis Generator
     */
    private String city;

    /**
     * 真实姓名: t_customer_info.REALNAME
     * @author MyBatis Generator
     */
    private String realname;

    /**
     * 性别: t_customer_info.SEX
     * @author MyBatis Generator
     */
    private String sex;

    /**
     * 客户手机号: t_customer_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    private String mobilePhone;

    /**
     * 状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_customer_info.STATUS
     * @author MyBatis Generator
     */
    private String status;

    /**
     * 注册IP地址: t_customer_info.REGISTER_IP_ADDRESS
     * @author MyBatis Generator
     */
    private String registerIpAddress;

    /**
     * 注册时间: t_customer_info.REGISTER_TIME
     * @author MyBatis Generator
     */
    private Date registerTime;

    /**
     * 上次登录IP: t_customer_info.LAST_LOGIN_IP
     * @author MyBatis Generator
     */
    private String lastLoginIp;

    /**
     * 上次登录时间: t_customer_info.LAST_LOGIN_TIME
     * @author MyBatis Generator
     */
    private Date lastLoginTime;

    /**
     * 注册来源: t_customer_info.SOURCE
     * @author MyBatis Generator
     */
    private String source;

    /**
     * 客户头像存储路径: t_customer_info.HEAD_IMG_PATH
     * @author MyBatis Generator
     */
    private String headImgPath;

    /**
     * 获取客户ID号: t_customer_info.CUSTOMER_INFO_ID
     * @return 客户ID号: t_customer_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getCustomerInfoId() {
        return customerInfoId;
    }

    /**
     * 设置客户ID号: t_customer_info.CUSTOMER_INFO_ID
     * @param customerInfoId 映射数据库字段: t_customer_info.CUSTOMER_INFO_ID
     * @author MyBatis Generator
     */
    public void setCustomerInfoId(Long customerInfoId) {
        this.customerInfoId = customerInfoId;
    }

    /**
     * 获取绑定的外部帐号: t_customer_info.BIND_ACCOUNT
     * @return 绑定的外部帐号: t_customer_info.BIND_ACCOUNT
     * @author MyBatis Generator
     */
    public String getBindAccount() {
        return bindAccount;
    }

    /**
     * 设置绑定的外部帐号: t_customer_info.BIND_ACCOUNT
     * @param bindAccount 映射数据库字段: t_customer_info.BIND_ACCOUNT
     * @author MyBatis Generator
     */
    public void setBindAccount(String bindAccount) {
        this.bindAccount = bindAccount == null ? null : bindAccount.trim();
    }

    /**
     * 获取登录名,唯一,自动生成,用户只能修改一次: t_customer_info.LOGIN_NAME
     * @return 登录名,唯一,自动生成,用户只能修改一次: t_customer_info.LOGIN_NAME
     * @author MyBatis Generator
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名,唯一,自动生成,用户只能修改一次: t_customer_info.LOGIN_NAME
     * @param loginName 映射数据库字段: t_customer_info.LOGIN_NAME
     * @author MyBatis Generator
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取登录密码: t_customer_info.PASSWORD
     * @return 登录密码: t_customer_info.PASSWORD
     * @author MyBatis Generator
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码: t_customer_info.PASSWORD
     * @param password 映射数据库字段: t_customer_info.PASSWORD
     * @author MyBatis Generator
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取国家: t_customer_info.COUNTRY
     * @return 国家: t_customer_info.COUNTRY
     * @author MyBatis Generator
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家: t_customer_info.COUNTRY
     * @param country 映射数据库字段: t_customer_info.COUNTRY
     * @author MyBatis Generator
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取省份: t_customer_info.PROVINCE
     * @return 省份: t_customer_info.PROVINCE
     * @author MyBatis Generator
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份: t_customer_info.PROVINCE
     * @param province 映射数据库字段: t_customer_info.PROVINCE
     * @author MyBatis Generator
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取城市: t_customer_info.CITY
     * @return 城市: t_customer_info.CITY
     * @author MyBatis Generator
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市: t_customer_info.CITY
     * @param city 映射数据库字段: t_customer_info.CITY
     * @author MyBatis Generator
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取真实姓名: t_customer_info.REALNAME
     * @return 真实姓名: t_customer_info.REALNAME
     * @author MyBatis Generator
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置真实姓名: t_customer_info.REALNAME
     * @param realname 映射数据库字段: t_customer_info.REALNAME
     * @author MyBatis Generator
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取性别: t_customer_info.SEX
     * @return 性别: t_customer_info.SEX
     * @author MyBatis Generator
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别: t_customer_info.SEX
     * @param sex 映射数据库字段: t_customer_info.SEX
     * @author MyBatis Generator
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取客户手机号: t_customer_info.MOBILE_PHONE
     * @return 客户手机号: t_customer_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置客户手机号: t_customer_info.MOBILE_PHONE
     * @param mobilePhone 映射数据库字段: t_customer_info.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_customer_info.STATUS
     * @return 状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_customer_info.STATUS
     * @author MyBatis Generator
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态,NORMAL正常,FROZEN冻结,DEMISE销户: t_customer_info.STATUS
     * @param status 映射数据库字段: t_customer_info.STATUS
     * @author MyBatis Generator
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取注册IP地址: t_customer_info.REGISTER_IP_ADDRESS
     * @return 注册IP地址: t_customer_info.REGISTER_IP_ADDRESS
     * @author MyBatis Generator
     */
    public String getRegisterIpAddress() {
        return registerIpAddress;
    }

    /**
     * 设置注册IP地址: t_customer_info.REGISTER_IP_ADDRESS
     * @param registerIpAddress 映射数据库字段: t_customer_info.REGISTER_IP_ADDRESS
     * @author MyBatis Generator
     */
    public void setRegisterIpAddress(String registerIpAddress) {
        this.registerIpAddress = registerIpAddress == null ? null : registerIpAddress.trim();
    }

    /**
     * 获取注册时间: t_customer_info.REGISTER_TIME
     * @return 注册时间: t_customer_info.REGISTER_TIME
     * @author MyBatis Generator
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间: t_customer_info.REGISTER_TIME
     * @param registerTime 映射数据库字段: t_customer_info.REGISTER_TIME
     * @author MyBatis Generator
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取上次登录IP: t_customer_info.LAST_LOGIN_IP
     * @return 上次登录IP: t_customer_info.LAST_LOGIN_IP
     * @author MyBatis Generator
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置上次登录IP: t_customer_info.LAST_LOGIN_IP
     * @param lastLoginIp 映射数据库字段: t_customer_info.LAST_LOGIN_IP
     * @author MyBatis Generator
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * 获取上次登录时间: t_customer_info.LAST_LOGIN_TIME
     * @return 上次登录时间: t_customer_info.LAST_LOGIN_TIME
     * @author MyBatis Generator
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登录时间: t_customer_info.LAST_LOGIN_TIME
     * @param lastLoginTime 映射数据库字段: t_customer_info.LAST_LOGIN_TIME
     * @author MyBatis Generator
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取注册来源: t_customer_info.SOURCE
     * @return 注册来源: t_customer_info.SOURCE
     * @author MyBatis Generator
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置注册来源: t_customer_info.SOURCE
     * @param source 映射数据库字段: t_customer_info.SOURCE
     * @author MyBatis Generator
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取客户头像存储路径: t_customer_info.HEAD_IMG_PATH
     * @return 客户头像存储路径: t_customer_info.HEAD_IMG_PATH
     * @author MyBatis Generator
     */
    public String getHeadImgPath() {
        return headImgPath;
    }

    /**
     * 设置客户头像存储路径: t_customer_info.HEAD_IMG_PATH
     * @param headImgPath 映射数据库字段: t_customer_info.HEAD_IMG_PATH
     * @author MyBatis Generator
     */
    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath == null ? null : headImgPath.trim();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
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
        TCustomerInfo other = (TCustomerInfo) that;
        return (this.getCustomerInfoId() == null ? other.getCustomerInfoId() == null : this.getCustomerInfoId().equals(other.getCustomerInfoId()))
            && (this.getBindAccount() == null ? other.getBindAccount() == null : this.getBindAccount().equals(other.getBindAccount()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRegisterIpAddress() == null ? other.getRegisterIpAddress() == null : this.getRegisterIpAddress().equals(other.getRegisterIpAddress()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getLastLoginIp() == null ? other.getLastLoginIp() == null : this.getLastLoginIp().equals(other.getLastLoginIp()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getHeadImgPath() == null ? other.getHeadImgPath() == null : this.getHeadImgPath().equals(other.getHeadImgPath()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCustomerInfoId() == null) ? 0 : getCustomerInfoId().hashCode());
        result = prime * result + ((getBindAccount() == null) ? 0 : getBindAccount().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRegisterIpAddress() == null) ? 0 : getRegisterIpAddress().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getLastLoginIp() == null) ? 0 : getLastLoginIp().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getHeadImgPath() == null) ? 0 : getHeadImgPath().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", customerInfoId=").append(customerInfoId);
        sb.append(", bindAccount=").append(bindAccount);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", realname=").append(realname);
        sb.append(", sex=").append(sex);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", status=").append(status);
        sb.append(", registerIpAddress=").append(registerIpAddress);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", source=").append(source);
        sb.append(", headImgPath=").append(headImgPath);
        sb.append("]");
        return sb.toString();
    }
}