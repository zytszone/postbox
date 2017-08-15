package cn.datai.gift.web.service;

import cn.datai.gift.persist.po.TExpressTakeInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
public interface ExpressTakeInfoService {

    /**
     * 根据用户Id查询快递领取历史
     * @param customerInfoId
     * @return
     */
    List<TExpressTakeInfo> queryTExpressTakeInfo(Long customerInfoId);
}
