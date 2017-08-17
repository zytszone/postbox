package cn.datai.gift.persist.mapper;

import java.util.Map;

public interface TCustomerInfoMapperExt extends TCustomerInfoMapper {

    /**
     * 通过用户ID查询用户OpenId
     * @param params
     * @return
     */
    String queryUserOpenId(Map<String,Object> params);
}