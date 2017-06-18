package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TRepairRecord;
import cn.datai.gift.persist.po.TRepairRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRepairRecordMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int countByExample(TRepairRecordExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int deleteByExample(TRepairRecordExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long repairRecordId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int insert(TRepairRecord record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int insertSelective(TRepairRecord record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    List<TRepairRecord> selectByExample(TRepairRecordExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    TRepairRecord selectByPrimaryKey(Long repairRecordId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TRepairRecord record, @Param("example") TRepairRecordExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TRepairRecord record, @Param("example") TRepairRecordExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TRepairRecord record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_repair_record
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TRepairRecord record);
}