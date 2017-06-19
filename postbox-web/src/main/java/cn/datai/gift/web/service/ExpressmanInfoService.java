package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TExpressmanInfo;

/**
 * 快递员Service
 * Created by H.CAAHN on 2017/6/19.
 */
public interface ExpressmanInfoService {
    /**
     * 根据客户ID查询快递员信息
     * @param customerId
     * @return
     */
    TExpressmanInfo queryByCustomerInfoId(Long customerId);
}
