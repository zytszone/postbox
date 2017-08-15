package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TExpressTakeInfoMapperExt;
import cn.datai.gift.persist.po.TExpressTakeInfo;
import cn.datai.gift.persist.po.TExpressTakeInfoExample;
import cn.datai.gift.web.service.ExpressTakeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
@Service
public class ExpressTakeInfoImpl implements ExpressTakeInfoService {

    private static final Logger logger = LoggerFactory.getLogger(ExpressTakeInfoImpl.class);

    @Autowired
    private TExpressTakeInfoMapperExt tExpressTakeInfoMapperExt;

    /**
     * 根据用户Id查询快递领取历史
     *
     * @param customerInfoId
     * @return
     */
    @Override
    public List<TExpressTakeInfo> queryTExpressTakeInfo(Long customerInfoId) {
        TExpressTakeInfoExample example = new TExpressTakeInfoExample();
        example.createCriteria().andCustomerInfoIdEqualTo(customerInfoId);
        return this.tExpressTakeInfoMapperExt.selectByExample(example);
    }
}
