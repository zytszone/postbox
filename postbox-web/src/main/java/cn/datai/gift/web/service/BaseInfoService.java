package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.UserInfo;
import cn.datai.gift.persist.po.UserWxInfo;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface BaseInfoService {

    /**
     * 插入 用户微信信息
     * @param userWxInfo
     */
    void insertUserWxInfo(UserWxInfo userWxInfo);

    /**
     * 插入 用户基本信息
     * @param userInfo
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     *通过openId查询 用户微信信息
     * @param openId
     * @param unionId
     * @return
     */
    UserWxInfo queryUserWxInfo(String openId,String unionId);

    /**
     * 通过用户Id查询   用户基本信息
     * @param userInfoId
     * @return
     */
    UserInfo queryUserInfo(Long userInfoId);

    /**
     * 更新 用户微信信息
     * @param userWxInfo
     */
    void updateUserWxInfo(UserWxInfo userWxInfo);

    /**
     * 更新  用户基本信息
     * @param userInfo
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 通过用户unionId查询 用户基本信息
     * @param unionId
     * @return
     */
    UserInfo queryUserInfoByUnionId(String unionId);

}
