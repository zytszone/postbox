package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.CommodityGiftInfo;
import cn.datai.gift.persist.po.GiftAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftAccountMapperExt extends GiftAccountMapper {

    /**
     * 通过主键查询交易账户信息并上锁
     * @param giftAccountId
     * @return
     */
    GiftAccount selectByPrimaryKeyAndLock(@Param("id") long giftAccountId);
}