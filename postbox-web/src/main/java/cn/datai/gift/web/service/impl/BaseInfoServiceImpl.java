package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.UserInfoMapperExt;
import cn.datai.gift.persist.mapper.UserWxInfoMapperExt;
import cn.datai.gift.persist.mapper.UserWxReltMapperExt;
import cn.datai.gift.persist.po.*;
import cn.datai.gift.web.contants.PhotoContants;
import cn.datai.gift.web.service.BaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
public class BaseInfoServiceImpl implements BaseInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInfoServiceImpl.class);

    @Autowired
    private UserInfoMapperExt userInfoMapperExt;

    @Autowired
    private UserWxInfoMapperExt userWxInfoMapperExt;

    @Autowired
    private UserWxReltMapperExt userWxReltMapperExt;


    /**
     * 插入 用户微信信息
     *
     * @param userWxInfo
     */
    @Override
    public void insertUserWxInfo(UserWxInfo userWxInfo) {
        userWxInfoMapperExt.insertSelective(userWxInfo);
    }

    /**
     * 插入 用户基本信息
     *
     * @param userInfo
     */
    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userInfoMapperExt.insertSelective(userInfo);
    }

    /**
     * 通过openId查询 用户微信信息
     *
     * @param openId
     * @param unionId
     * @return
     */
    @Override
    public UserWxInfo queryUserWxInfo(String openId, String unionId) {
        return userWxInfoMapperExt.selectByPrimaryKey(openId,unionId);
    }

    /**
     * 通过用户Id查询   用户基本信息
     *
     * @param userInfoId
     * @return
     */
    @Override
    public UserInfo queryUserInfo(Long userInfoId) {
        return userInfoMapperExt.selectByPrimaryKey(userInfoId);
    }

    /**
     * 更新 用户微信信息
     *
     * @param userWxInfo
     */
    @Override
    public void updateUserWxInfo(UserWxInfo userWxInfo) {
        userWxInfoMapperExt.updateByPrimaryKeySelective(userWxInfo);
    }

    /**
     * 更新  用户基本信息
     *
     * @param userInfo
     */
    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userInfoMapperExt.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 通过用户unionId查询 用户基本信息
     *
     * @param unionId
     * @return
     */
    @Override
    public UserInfo queryUserInfoByUnionId(String unionId) {
        return null;
    }

    /**
     * 通过用户userId查询 用户基本信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfo queryUserInfoByUserId(Long userId) {
        return userInfoMapperExt.selectByPrimaryKey(userId);
    }

    /**
     * 数据库插入（或更新）用户微信信息,插入微信用户与基本用户关联信息，插入用户基本信息
     *
     * @param userWxInfo
     */
    @Override
    public void insertOrUpDate(UserWxInfo userWxInfo) throws Exception {
        UserWxInfo queryUserWxInfo = this.queryUserWxInfo(userWxInfo.getOpenid(),userWxInfo.getUnionid());
        if(null != queryUserWxInfo){
            this.updateUserWxInfo(userWxInfo);
        }else{
            this.insertUserWxInfo(userWxInfo);//插入用户微信信息

        }
    }

    /**
     * 通过unionId查询 微信用户与基本用户关联关系
     *
     * @param unionId
     * @return
     */
    @Override
    public UserWxRelt queryUserWxReltByUnionId(String unionId) {
        UserWxReltExample userWxReltExample = new UserWxReltExample();
        userWxReltExample.createCriteria().andUnionidEqualTo(unionId);
        List<UserWxRelt> list = userWxReltMapperExt.selectByExample(userWxReltExample);

        if(null != list && !list.isEmpty()){
            return list.get(0);
        }

        return null;

    }

    /**
     * 插入 微信用户与基本用户关联信息
     *
     * @param userWxRelt
     */
    @Override
    public void insertUserWxRelt(UserWxRelt userWxRelt) {
        this.userWxReltMapperExt.insertSelective(userWxRelt);
    }

    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     */
    @Override
    public UserInfo queryUserInfoByPhone(String phone) {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andMobilePhoneEqualTo(phone);
        List<UserInfo> userInfoList = this.userInfoMapperExt.selectByExample(userInfoExample);
        if(null == userInfoList || userInfoList.isEmpty()){
            return null;
        }
        return userInfoList.get(0);
    }

}
