package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TBoxLog;
import cn.datai.gift.persist.po.TBoxLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBoxLogMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int countByExample(TBoxLogExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int deleteByExample(TBoxLogExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long boxLogId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int insert(TBoxLog record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int insertSelective(TBoxLog record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    List<TBoxLog> selectByExample(TBoxLogExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    TBoxLog selectByPrimaryKey(Long boxLogId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TBoxLog record, @Param("example") TBoxLogExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TBoxLog record, @Param("example") TBoxLogExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TBoxLog record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_box_log
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TBoxLog record);
}