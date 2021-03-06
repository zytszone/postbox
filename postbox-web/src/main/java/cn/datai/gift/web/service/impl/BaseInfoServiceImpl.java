package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TCustomerInfoMapperExt;
import cn.datai.gift.persist.mapper.TExpressmanInfoMapperExt;
import cn.datai.gift.persist.mapper.UserWxInfoMapperExt;
import cn.datai.gift.persist.mapper.UserWxReltMapperExt;
import cn.datai.gift.persist.po.*;
import cn.datai.gift.web.contants.PhotoContants;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.utils.PhotoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
public class BaseInfoServiceImpl implements BaseInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInfoServiceImpl.class);

    @Autowired
    private TCustomerInfoMapperExt tCustomerInfoMapperExt;

    @Autowired
    private UserWxInfoMapperExt userWxInfoMapperExt;

    @Autowired
    private UserWxReltMapperExt userWxReltMapperExt;

    @Autowired
    private TExpressmanInfoMapperExt tExpressmanInfoMapperExt;


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
     * @param
     */
    @Override
    public void insertTCustomerInfo(TCustomerInfo tCustomerInfo) {
        tCustomerInfoMapperExt.insertSelective(tCustomerInfo);
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
     * @param cusotmerInfoId
     * @return
     */
    @Override
    public TCustomerInfo queryTCustomerInfo(Long cusotmerInfoId) {
        return tCustomerInfoMapperExt.selectByPrimaryKey(cusotmerInfoId);
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
     * @param tCustomerInfo
     */
    @Override
    public void updateTCustomerInfo(TCustomerInfo tCustomerInfo) {
        tCustomerInfoMapperExt.updateByPrimaryKeySelective(tCustomerInfo);
    }

    /**
     * 通过用户unionId查询 用户基本信息
     *
     * @param unionId
     * @return
     */
    @Override
    public TCustomerInfo queryTCustomerInfoIdByUnionId(String unionId) {
        return null;
    }

    /**
     * 通过用户userId查询 用户基本信息
     *
     * @param customerInfoId
     * @return
     */
    @Override
    public TCustomerInfo queryTCustomerInfoById(Long customerInfoId) {
        return tCustomerInfoMapperExt.selectByPrimaryKey(customerInfoId);
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
    public TCustomerInfo queryTCustomerInfoIdByPhone(String phone) {
        TCustomerInfoExample example = new TCustomerInfoExample();
        example.createCriteria().andMobilePhoneEqualTo(phone);
        List<TCustomerInfo> tCustomerInfoList = this.tCustomerInfoMapperExt.selectByExample(example);
        if(null == tCustomerInfoList || tCustomerInfoList.isEmpty()){
            return null;
        }
        return tCustomerInfoList.get(0);
    }

    /**
     * 根据用户Id查询该用户是不是快递员
     *
     * @param customerInfoId
     * @return
     */
    @Override
    public TExpressmanInfo queryTExpressmanInfoByCustomerInfoId(Long customerInfoId) {

        TExpressmanInfoExample example = new TExpressmanInfoExample();
        example.createCriteria().andCustomerInfoIdEqualTo(customerInfoId);
        List<TExpressmanInfo> list = tExpressmanInfoMapperExt.selectByExample(example);
        if(null != list && !list.isEmpty() && list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    /**
     * 插入快递员信息
     *
     * @param tExpressmanInfo
     */
    @Override
    public void insertTExpressmanInfo(TExpressmanInfo tExpressmanInfo) {
        tExpressmanInfoMapperExt.insertSelective(tExpressmanInfo);
    }

    /**
     * 上传图片
     * @param fileName
     * @param url
     */
    @Override
    public void uploadPhoto(String fileName, String url) throws Exception {
        PhotoUtil.uploadFile(fileName + PhotoContants.FILENAME_SUFFIX,url);
    }

}
