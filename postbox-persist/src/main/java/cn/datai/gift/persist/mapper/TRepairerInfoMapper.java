package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TRepairerInfo;
import cn.datai.gift.persist.po.TRepairerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRepairerInfoMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int countByExample(TRepairerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int deleteByExample(TRepairerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long repairerInfoId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int insert(TRepairerInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int insertSelective(TRepairerInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    List<TRepairerInfo> selectByExample(TRepairerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    TRepairerInfo selectByPrimaryKey(Long repairerInfoId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TRepairerInfo record, @Param("example") TRepairerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TRepairerInfo record, @Param("example") TRepairerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TRepairerInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repairer_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TRepairerInfo record);
}