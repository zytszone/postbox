package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TBoxInfo;
import org.apache.ibatis.annotations.Param;

public interface TBoxInfoMapperExt extends TBoxInfoMapper {
    /**
     * 查询并锁住箱子信息
     * @param boxCode
     * @return
     */
    TBoxInfo findAndLockTboxInfo(@Param("boxCode") String boxCode);
}