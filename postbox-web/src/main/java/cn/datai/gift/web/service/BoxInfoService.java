package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.utils.RespResult;

import java.util.List;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
public interface BoxInfoService {
    /**
     * 根据ID查找箱子
     * @param id
     * @return
     */
    TBoxInfo queryById(Long id);

    /**
     * 根据客户手机号码和代领人id查询当前客户待领的快递箱子信息
     * @param mobilePhone
     * @return
     */
    List<TBoxInfo> queryTBoxInfoByMobileOrproxyCustomerInfoId(String mobilePhone,String proxyCustomerInfoId);

    /**
     * 普通用户打开箱子<br/>
     * 程序逻辑：<br/>
     * 1、如果箱子是空的，则判断箱子手机号是否与当前用户匹配，且上次开箱时间在10分钟之内，则允许打开箱子；<br/>
     * 2、如果箱子有货物，且当前操作人是普通用户，则判断箱子手机号是否与当前用户匹配，如匹配，则允许打开箱子
     * @param boxInfo
     * @param mkey
     * @param customerInfo
     * @return
     */
    String updateAsNormalUserForDecode(TBoxInfo boxInfo, String mkey, TCustomerInfo customerInfo);

    /**
     * 快递员打开箱子<br/>
     * 程序逻辑：<br/>
     * 1、如果箱子是空的，则允许打开箱子<br/>
     * 2、如果箱子非空，则不允许打开(暂时使用该逻辑)
     * @param boxInfo
     * @param mkey
     * @param expressman
     * @return
     */
    String updateAsExpressmanForDecode(TBoxInfo boxInfo, String mkey, TExpressmanInfo expressman);

    /**
     * 更新箱子的收件人手机号
     * @param boxId
     * @param mobile
     */
    void updateBoxMobilePhone(Long boxId, String mobile);

    /**
     * 更新代理人
     * @param customerInfoId
     * @param boxIds
     * @return
     */
    RespResult updateForMeLead(Long customerInfoId,String boxIds);
}
