package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.PayInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PayInfoMapperExt extends PayInfoMapper {

    /**
     * 查询并锁记录
     * @param id
     * @return
     */
    PayInfo selectByPrimaryKeyAndLock(@Param("id") long id);
}