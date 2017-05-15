package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.PayInfo;
import cn.datai.gift.persist.po.RefundOrderWx;

import java.util.Date;
import java.util.List;

/**
 * pay_info数据库表相关操作
 * Created by H.CAAHN on 2017/4/20.
 */
public interface PayInfoService {
    /**
     * 查询指定渠道待支付总数
     * @param channel
     * @return
     */
    int countUnPayInfo(String channel);

    /**
     * 分页查询指定渠道待支付数据
     * @param channel
     * @param pageNo
     * @param size
     * @param total
     * @return
     */
    List<PayInfo> pageUnPayInfo(String channel, int pageNo, int size, int total);
}
