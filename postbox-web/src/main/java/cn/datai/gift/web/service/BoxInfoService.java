package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TCustomerInfo;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
public interface BoxInfoService {
    /**
     * 程序逻辑：<br/>
     * 1、如果箱子是空的，且当前操作人是快递员，则允许打开箱子；<br/>
     * 2、如果箱子是空的，且当前操作人是普通用户，则判断箱子手机号是否与当前用户匹配，且上次开箱时间在10分钟之内，则允许打开箱子；<br/>
     * 3、如果箱子有货物，且当前操作人是快递员，则让操作人按照普通用户来操作（快递员也可以拿自己的快递）；<br/>
     * 4、如果箱子有货物，且当前操作人是普通用户，则判断箱子手机号是否与当前用户匹配，如匹配，则允许打开箱子
     * @param boxId
     * @param mkey
     * @param tCustomerInfo
     * @return
     */
    String updateForDecode(Long boxId, String mkey, TCustomerInfo tCustomerInfo) throws Exception;

    /**
     * 更新箱子的属主手机号
     * @param boxId
     * @param mobileno
     */
    void updateBoxMobile(Long boxId, String mobileno);
}
