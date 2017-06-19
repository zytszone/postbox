package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TCustomerInfo;

/**
 * Created by H.CAAHN on 2017/6/19.
 */
public interface CustomerInfoService {
    TCustomerInfo queryById(Long customerId);
}
