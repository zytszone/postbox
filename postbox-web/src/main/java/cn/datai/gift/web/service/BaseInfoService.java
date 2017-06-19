package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.persist.po.UserWxInfo;
import cn.datai.gift.persist.po.UserWxRelt;

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
     * @param tCustomerInfo
     */
    void insertTCustomerInfo(TCustomerInfo tCustomerInfo);

    /**
     *通过openId查询 用户微信信息
     * @param openId
     * @param unionId
     * @return
     */
    UserWxInfo queryUserWxInfo(String openId,String unionId);

    /**
     * 通过用户Id查询   用户基本信息
     * @param customerInfoId
     * @return
     */
    TCustomerInfo queryTCustomerInfo(Long customerInfoId);

    /**
     * 更新 用户微信信息
     * @param userWxInfo
     */
    void updateUserWxInfo(UserWxInfo userWxInfo);

    /**
     * 更新  用户基本信息
     * @param tCustomerInfo
     */
    void updateTCustomerInfo(TCustomerInfo tCustomerInfo);

    /**
     * 通过用户unionId查询 用户基本信息
     * @param unionId
     * @return
     */
    TCustomerInfo queryTCustomerInfoIdByUnionId(String unionId);

    /**
     * 通过用户userId查询 用户基本信息
     * @param customerInfoId
     * @return
     */
    TCustomerInfo queryTCustomerInfoById(Long customerInfoId);

    /**
     * 数据库插入（或更新）用户微信信息,插入微信用户与基本用户关联信息，插入用户基本信息
     * @param userWxInfo
     */
    void insertOrUpDate(UserWxInfo userWxInfo) throws Exception;

    /**
     * 通过unionId查询 微信用户与基本用户关联关系
     * @param unionId
     * @return
     */
    UserWxRelt queryUserWxReltByUnionId(String unionId);

    /**
     * 插入 微信用户与基本用户关联信息
     * @param userWxRelt
     */
    void insertUserWxRelt(UserWxRelt userWxRelt);

    /**
     * 通过手机号查询用户是否注册
     * @param phone
     * @return
     */
    TCustomerInfo queryTCustomerInfoIdByPhone(String phone);

    /**
     * 根据用户Id查询该用户是不是快递员
     * @param customerInfoId
     * @return
     */
    TExpressmanInfo queryTExpressmanInfoByCustomerInfoId(Long customerInfoId);


}
