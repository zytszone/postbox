package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TBoxInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBoxInfoMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int countByExample(TBoxInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int deleteByExample(TBoxInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int insert(TBoxInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int insertSelective(TBoxInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    List<TBoxInfo> selectByExample(TBoxInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    TBoxInfo selectByPrimaryKey(Long id);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TBoxInfo record, @Param("example") TBoxInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TBoxInfo record, @Param("example") TBoxInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TBoxInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: T_BOX_INFO
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TBoxInfo record);
}