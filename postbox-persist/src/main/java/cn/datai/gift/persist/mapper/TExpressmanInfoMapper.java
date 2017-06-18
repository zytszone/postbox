package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.persist.po.TExpressmanInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TExpressmanInfoMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int countByExample(TExpressmanInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int deleteByExample(TExpressmanInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long expressmanInfoId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int insert(TExpressmanInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int insertSelective(TExpressmanInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    List<TExpressmanInfo> selectByExample(TExpressmanInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    TExpressmanInfo selectByPrimaryKey(Long expressmanInfoId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TExpressmanInfo record, @Param("example") TExpressmanInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TExpressmanInfo record, @Param("example") TExpressmanInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TExpressmanInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_expressman_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TExpressmanInfo record);
}