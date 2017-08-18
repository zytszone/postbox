package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.vo.UserInfoVo;

import java.util.Map;

public interface TCustomerInfoMapperExt extends TCustomerInfoMapper {

    /**
     * 通过用户ID查询用户OpenId
     * @param params
     * @return
     */
    UserInfoVo queryUserWxInfoByCons(Map<String,Object> params);
}