package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.UserInfoMapperExt;
import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.utils.crypto.bcrypt.BCryptPasswordEncoder;
import cn.datai.gift.web.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Administrator on 2017/4/26.
 */
@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private UserInfoMapperExt userInfoMapperExt;

    /**
     * 校验密码
     * @param oldPwd
     * @return
     */
    @Override
    public boolean checkPassword(Long userInfoId,String oldPwd) throws NoSuchAlgorithmException {
        UserInfo userInfo = this.queryUserInfo(userInfoId);
        String pwdStr = this.getEncodePwd(oldPwd);
        if(userInfo.getPassword().equals(pwdStr)){
            return true;
        }
        return false;
    }

    /**
     * 查询用户信息
     * @param userInfoId
     * @return
     */
    @Override
    public UserInfo queryUserInfo(Long userInfoId) {
        return this.userInfoMapperExt.selectByPrimaryKey(userInfoId);
    }

    /**
     * 保存或者更新密码
     * @param userInfoId
     * @param pwd
     */
    @Transactional
    @Override
    public void savePwd(Long userInfoId, String pwd) throws NoSuchAlgorithmException {
        UserInfo userInfo = this.queryUserInfo(userInfoId);
        userInfo.setPassword(this.getEncodePwd(pwd));
        this.userInfoMapperExt.updateByPrimaryKeySelective(userInfo);
    }


    /**
     * 加密
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String getEncodePwd(String oldPwd) throws NoSuchAlgorithmException {
        SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(5,sha1PRNG);
        String code = bCryptPasswordEncoder.encode(oldPwd);
        return org.apache.commons.codec.binary.Base64.encodeBase64String(code.getBytes());
    }

}
