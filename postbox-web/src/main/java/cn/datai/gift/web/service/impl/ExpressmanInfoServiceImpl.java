package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TExpressmanInfoMapperExt;
import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.persist.po.TExpressmanInfoExample;
import cn.datai.gift.web.service.ExpressmanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 快递员Service
 * Created by H.CAAHN on 2017/6/19.
 */
@Service
public class ExpressmanInfoServiceImpl implements ExpressmanInfoService {
    @Autowired
    private TExpressmanInfoMapperExt tExpressmanInfoMapperExt;

    public TExpressmanInfo queryByCustomerInfoId(Long customerId) {
        TExpressmanInfoExample example = new TExpressmanInfoExample();
        example.createCriteria().andCustomerInfoIdEqualTo(customerId);

        List<TExpressmanInfo> eList = this.tExpressmanInfoMapperExt.selectByExample(example);
        if (eList != null && !eList.isEmpty()) {
            return eList.get(0);
        }
        return null;
    }
}
