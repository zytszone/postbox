package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TCustomerInfoMapperExt;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.web.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户Service
 * Created by H.CAAHN on 2017/6/19.
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private TCustomerInfoMapperExt tCustomerInfoMapperExt;

    public TCustomerInfo queryById(Long customerId) {
        return tCustomerInfoMapperExt.selectByPrimaryKey(customerId);
    }
}
