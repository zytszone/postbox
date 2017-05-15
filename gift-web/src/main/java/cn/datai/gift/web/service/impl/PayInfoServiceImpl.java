package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.PayInfoMapperExt;
import cn.datai.gift.persist.mapper.PayRefundOrderWxMapperExt;
import cn.datai.gift.persist.mapper.RefundOrderWxMapperExt;
import cn.datai.gift.persist.po.*;
import cn.datai.gift.persist.vo.Page;
import cn.datai.gift.web.contants.enums.PayInfoStatus;
import cn.datai.gift.web.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * pay_info数据库表相关操作
 * Created by H.CAAHN on 2017/4/20.
 */
@Service
public class PayInfoServiceImpl implements PayInfoService {
    @Autowired
    private PayInfoMapperExt payInfoMapperExt;

    public int countUnPayInfo(String channel) {
        PayInfoExample example = new PayInfoExample();
        example.createCriteria().andChannelEqualTo(channel).andStatusEqualTo(PayInfoStatus.UNPAYING.getPersistKey());
        return payInfoMapperExt.countByExample(example);
    }

    public List<PayInfo> pageUnPayInfo(String channel, int pageNo, int size, int total) {
        PayInfoExample example = new PayInfoExample();
        example.createCriteria().andChannelEqualTo(channel).andStatusEqualTo(PayInfoStatus.UNPAYING.getPersistKey());
        Page page = new Page(true, pageNo, size, total);
        example.setPage(page);
        return payInfoMapperExt.selectByExample(example);
    }
}
