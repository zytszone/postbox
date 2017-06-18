package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TExpressTakeInfo;
import cn.datai.gift.persist.po.TExpressTakeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TExpressTakeInfoMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int countByExample(TExpressTakeInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int deleteByExample(TExpressTakeInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long expressTakeInfo);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int insert(TExpressTakeInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int insertSelective(TExpressTakeInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    List<TExpressTakeInfo> selectByExample(TExpressTakeInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    TExpressTakeInfo selectByPrimaryKey(Long expressTakeInfo);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TExpressTakeInfo record, @Param("example") TExpressTakeInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TExpressTakeInfo record, @Param("example") TExpressTakeInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TExpressTakeInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TExpressTakeInfo record);
}