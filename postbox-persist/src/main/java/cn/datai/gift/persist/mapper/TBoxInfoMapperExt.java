package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TBoxInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TBoxInfoMapperExt extends TBoxInfoMapper {
    /**
     * 查询并锁住箱子信息
     * @param boxCode
     * @return
     */
    TBoxInfo findAndLockTboxInfo(@Param("boxCode") String boxCode);

    /**
     * 查询待领列表
     * @param map
     * @return
     */
    List<TBoxInfo> queryTBoxInfoByMobileOrproxyCustomerInfoId(Map<String,Object> map);
}