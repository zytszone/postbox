package cn.datai.gift.persist.po;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    public static final long serialVersionUID = 753837666L;

    /**
     * 用户id: USER_INFO.USER_INFO_ID
     * @author MyBatis Generator
     */
    private Long userInfoId;

    /**
     * 绑定的外部账户，现对应通行证账号: USER_INFO.BIND_ACCOUNT
     * @author MyBatis Generator
     */
    private String bindAccount;

    /**
     * 唯一，自动生成，用户只能修改一次: USER_INFO.LOGIN_NAME
     * @author MyBatis Generator
     */
    private String loginName;

    /**
     * 密码: USER_INFO.PASSWORD
     * @author MyBatis Generator
     */
    private String password;

    /**
     * 国家: USER_INFO.COUNTRY
     * @author MyBatis Generator
     */
    private String country;

    /**
     * 省份: USER_INFO.PROVINCE
     * @author MyBatis Generator
     */
    private String province;

    /**
     * 城市: USER_INFO.CITY
     * @author MyBatis Generator
     */
    private String city;

    /**
     * 昵称: USER_INFO.NICKNAME
     * @author MyBatis Generator
     */
    private String nickname;

    /**
     * 性别: USER_INFO.SEX
     * @author MyBatis Generator
     */
    private String sex;

    /**
     * 手机号: USER_INFO.MOBILE_PHONE
     * @author MyBatis Generator
     */
    private String mobilePhone;

    /**
     * 是否为特别用户: USER_INFO.IS_SPECIAL
     * @author MyBatis Generator
     */
    private String isSpecial;

    /**
     * IP地址: USER_INFO.REGISTER_IP_ADDRESS
     * @author MyBatis Generator
     */
    private String registerIpAddress;

    /**
     * 注册时间: USER_INFO.REGISTER_TIME
     * @author MyBatis Generator
     */
    private Date registerTime;

    /**
     * 最后登录时间: USER_INFO.LAST_LOGIN_TIME
     * @author MyBatis Generator
     */
    private String lastLoginTime;

    /**
     * 账号来源: USER_INFO.SOURCE
     * @author MyBatis Generator
     */
    private String source;

    /**
     * 推荐人: USER_INFO.RECOMMEND_ID
     * @author MyBatis Generator
     */
    private String recommendId;

    /**
     * 等级: USER_INFO.RANK
     * @author MyBatis Generator
     */
    private String rank;

    /**
     * 头像文件存储路径: USER_INFO.HEAD_IMG_PATH
     * @author MyBatis Generator
     */
    private String headImgPath;

    /**
     * 获取用户id: USER_INFO.USER_INFO_ID
     * @return 用户id: USER_INFO.USER_INFO_ID
     * @author MyBatis Generator
     */
    public Long getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置用户id: USER_INFO.USER_INFO_ID
     * @param userInfoId 映射数据库字段: USER_INFO.USER_INFO_ID
     * @author MyBatis Generator
     */
    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    /**
     * 获取绑定的外部账户，现对应通行证账号: USER_INFO.BIND_ACCOUNT
     * @return 绑定的外部账户，现对应通行证账号: USER_INFO.BIND_ACCOUNT
     * @author MyBatis Generator
     */
    public String getBindAccount() {
        return bindAccount;
    }

    /**
     * 设置绑定的外部账户，现对应通行证账号: USER_INFO.BIND_ACCOUNT
     * @param bindAccount 映射数据库字段: USER_INFO.BIND_ACCOUNT
     * @author MyBatis Generator
     */
    public void setBindAccount(String bindAccount) {
        this.bindAccount = bindAccount == null ? null : bindAccount.trim();
    }

    /**
     * 获取唯一，自动生成，用户只能修改一次: USER_INFO.LOGIN_NAME
     * @return 唯一，自动生成，用户只能修改一次: USER_INFO.LOGIN_NAME
     * @author MyBatis Generator
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置唯一，自动生成，用户只能修改一次: USER_INFO.LOGIN_NAME
     * @param loginName 映射数据库字段: USER_INFO.LOGIN_NAME
     * @author MyBatis Generator
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取密码: USER_INFO.PASSWORD
     * @return 密码: USER_INFO.PASSWORD
     * @author MyBatis Generator
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码: USER_INFO.PASSWORD
     * @param password 映射数据库字段: USER_INFO.PASSWORD
     * @author MyBatis Generator
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取国家: USER_INFO.COUNTRY
     * @return 国家: USER_INFO.COUNTRY
     * @author MyBatis Generator
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家: USER_INFO.COUNTRY
     * @param country 映射数据库字段: USER_INFO.COUNTRY
     * @author MyBatis Generator
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取省份: USER_INFO.PROVINCE
     * @return 省份: USER_INFO.PROVINCE
     * @author MyBatis Generator
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份: USER_INFO.PROVINCE
     * @param province 映射数据库字段: USER_INFO.PROVINCE
     * @author MyBatis Generator
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取城市: USER_INFO.CITY
     * @return 城市: USER_INFO.CITY
     * @author MyBatis Generator
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市: USER_INFO.CITY
     * @param city 映射数据库字段: USER_INFO.CITY
     * @author MyBatis Generator
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取昵称: USER_INFO.NICKNAME
     * @return 昵称: USER_INFO.NICKNAME
     * @author MyBatis Generator
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称: USER_INFO.NICKNAME
     * @param nickname 映射数据库字段: USER_INFO.NICKNAME
     * @author MyBatis Generator
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取性别: USER_INFO.SEX
     * @return 性别: USER_INFO.SEX
     * @author MyBatis Generator
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别: USER_INFO.SEX
     * @param sex 映射数据库字段: USER_INFO.SEX
     * @author MyBatis Generator
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取手机号: USER_INFO.MOBILE_PHONE
     * @return 手机号: USER_INFO.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号: USER_INFO.MOBILE_PHONE
     * @param mobilePhone 映射数据库字段: USER_INFO.MOBILE_PHONE
     * @author MyBatis Generator
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 获取是否为特别用户: USER_INFO.IS_SPECIAL
     * @return 是否为特别用户: USER_INFO.IS_SPECIAL
     * @author MyBatis Generator
     */
    public String getIsSpecial() {
        return isSpecial;
    }

    /**
     * 设置是否为特别用户: USER_INFO.IS_SPECIAL
     * @param isSpecial 映射数据库字段: USER_INFO.IS_SPECIAL
     * @author MyBatis Generator
     */
    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial == null ? null : isSpecial.trim();
    }

    /**
     * 获取IP地址: USER_INFO.REGISTER_IP_ADDRESS
     * @return IP地址: USER_INFO.REGISTER_IP_ADDRESS
     * @author MyBatis Generator
     */
    public String getRegisterIpAddress() {
        return registerIpAddress;
    }

    /**
     * 设置IP地址: USER_INFO.REGISTER_IP_ADDRESS
     * @param registerIpAddress 映射数据库字段: USER_INFO.REGISTER_IP_ADDRESS
     * @author MyBatis Generator
     */
    public void setRegisterIpAddress(String registerIpAddress) {
        this.registerIpAddress = registerIpAddress == null ? null : registerIpAddress.trim();
    }

    /**
     * 获取注册时间: USER_INFO.REGISTER_TIME
     * @return 注册时间: USER_INFO.REGISTER_TIME
     * @author MyBatis Generator
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间: USER_INFO.REGISTER_TIME
     * @param registerTime 映射数据库字段: USER_INFO.REGISTER_TIME
     * @author MyBatis Generator
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取最后登录时间: USER_INFO.LAST_LOGIN_TIME
     * @return 最后登录时间: USER_INFO.LAST_LOGIN_TIME
     * @author MyBatis Generator
     */
    public String getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间: USER_INFO.LAST_LOGIN_TIME
     * @param lastLoginTime 映射数据库字段: USER_INFO.LAST_LOGIN_TIME
     * @author MyBatis Generator
     */
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime == null ? null : lastLoginTime.trim();
    }

    /**
     * 获取账号来源: USER_INFO.SOURCE
     * @return 账号来源: USER_INFO.SOURCE
     * @author MyBatis Generator
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置账号来源: USER_INFO.SOURCE
     * @param source 映射数据库字段: USER_INFO.SOURCE
     * @author MyBatis Generator
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取推荐人: USER_INFO.RECOMMEND_ID
     * @return 推荐人: USER_INFO.RECOMMEND_ID
     * @author MyBatis Generator
     */
    public String getRecommendId() {
        return recommendId;
    }

    /**
     * 设置推荐人: USER_INFO.RECOMMEND_ID
     * @param recommendId 映射数据库字段: USER_INFO.RECOMMEND_ID
     * @author MyBatis Generator
     */
    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId == null ? null : recommendId.trim();
    }

    /**
     * 获取等级: USER_INFO.RANK
     * @return 等级: USER_INFO.RANK
     * @author MyBatis Generator
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置等级: USER_INFO.RANK
     * @param rank 映射数据库字段: USER_INFO.RANK
     * @author MyBatis Generator
     */
    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    /**
     * 获取头像文件存储路径: USER_INFO.HEAD_IMG_PATH
     * @return 头像文件存储路径: USER_INFO.HEAD_IMG_PATH
     * @author MyBatis Generator
     */
    public String getHeadImgPath() {
        return headImgPath;
    }

    /**
     * 设置头像文件存储路径: USER_INFO.HEAD_IMG_PATH
     * @param headImgPath 映射数据库字段: USER_INFO.HEAD_IMG_PATH
     * @author MyBatis Generator
     */
    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath == null ? null : headImgPath.trim();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: USER_INFO
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
        UserInfo other = (UserInfo) that;
        return (this.getUserInfoId() == null ? other.getUserInfoId() == null : this.getUserInfoId().equals(other.getUserInfoId()))
            && (this.getBindAccount() == null ? other.getBindAccount() == null : this.getBindAccount().equals(other.getBindAccount()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()))
            && (this.getIsSpecial() == null ? other.getIsSpecial() == null : this.getIsSpecial().equals(other.getIsSpecial()))
            && (this.getRegisterIpAddress() == null ? other.getRegisterIpAddress() == null : this.getRegisterIpAddress().equals(other.getRegisterIpAddress()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getRecommendId() == null ? other.getRecommendId() == null : this.getRecommendId().equals(other.getRecommendId()))
            && (this.getRank() == null ? other.getRank() == null : this.getRank().equals(other.getRank()))
            && (this.getHeadImgPath() == null ? other.getHeadImgPath() == null : this.getHeadImgPath().equals(other.getHeadImgPath()));
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: USER_INFO
     * @author MyBatis Generator
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserInfoId() == null) ? 0 : getUserInfoId().hashCode());
        result = prime * result + ((getBindAccount() == null) ? 0 : getBindAccount().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        result = prime * result + ((getIsSpecial() == null) ? 0 : getIsSpecial().hashCode());
        result = prime * result + ((getRegisterIpAddress() == null) ? 0 : getRegisterIpAddress().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getRecommendId() == null) ? 0 : getRecommendId().hashCode());
        result = prime * result + ((getRank() == null) ? 0 : getRank().hashCode());
        result = prime * result + ((getHeadImgPath() == null) ? 0 : getHeadImgPath().hashCode());
        return result;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: USER_INFO
     * @author MyBatis Generator
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", bindAccount=").append(bindAccount);
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", nickname=").append(nickname);
        sb.append(", sex=").append(sex);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", isSpecial=").append(isSpecial);
        sb.append(", registerIpAddress=").append(registerIpAddress);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", source=").append(source);
        sb.append(", recommendId=").append(recommendId);
        sb.append(", rank=").append(rank);
        sb.append(", headImgPath=").append(headImgPath);
        sb.append("]");
        return sb.toString();
    }
}