package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.UserInfo;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface PasswordService {
    /**
     * 校验密码
     * @param oldPwd
     * @return
     */
    boolean checkPassword(Long userInfoId,String oldPwd) throws NoSuchAlgorithmException;

    /**
     * 查询用户信息
     * @param userInfoId
     * @return
     */
    UserInfo queryUserInfo(Long userInfoId);

    void savePwd(Long userInfoId,String pwd) throws NoSuchAlgorithmException;
}
