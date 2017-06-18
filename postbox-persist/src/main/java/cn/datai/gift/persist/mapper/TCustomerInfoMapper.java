package cn.datai.gift.persist.mapper;

import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.TCustomerInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCustomerInfoMapper {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int countByExample(TCustomerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int deleteByExample(TCustomerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int deleteByPrimaryKey(Long customerInfoId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int insert(TCustomerInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int insertSelective(TCustomerInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    List<TCustomerInfo> selectByExample(TCustomerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    TCustomerInfo selectByPrimaryKey(Long customerInfoId);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int updateByExampleSelective(@Param("record") TCustomerInfo record, @Param("example") TCustomerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int updateByExample(@Param("record") TCustomerInfo record, @Param("example") TCustomerInfoExample example);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKeySelective(TCustomerInfo record);

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_customer_info
     * @author MyBatis Generator
     */
    int updateByPrimaryKey(TCustomerInfo record);
}