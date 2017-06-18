package cn.datai.gift.persist.po;

import cn.datai.gift.persist.vo.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TExpressTakeInfoExample {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    protected String orderByClause;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    protected Page page;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    protected boolean distinct;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    protected List<Criteria> oredCriteria;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public TExpressTakeInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public Page getPage() {
        return page;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 本类由Mybatis Generator自动生成
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andExpressTakeInfoIsNull() {
            addCriterion("EXPRESS_TAKE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoIsNotNull() {
            addCriterion("EXPRESS_TAKE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoEqualTo(Long value) {
            addCriterion("EXPRESS_TAKE_INFO =", value, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoNotEqualTo(Long value) {
            addCriterion("EXPRESS_TAKE_INFO <>", value, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoGreaterThan(Long value) {
            addCriterion("EXPRESS_TAKE_INFO >", value, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPRESS_TAKE_INFO >=", value, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoLessThan(Long value) {
            addCriterion("EXPRESS_TAKE_INFO <", value, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoLessThanOrEqualTo(Long value) {
            addCriterion("EXPRESS_TAKE_INFO <=", value, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoIn(List<Long> values) {
            addCriterion("EXPRESS_TAKE_INFO in", values, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoNotIn(List<Long> values) {
            addCriterion("EXPRESS_TAKE_INFO not in", values, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoBetween(Long value1, Long value2) {
            addCriterion("EXPRESS_TAKE_INFO between", value1, value2, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andExpressTakeInfoNotBetween(Long value1, Long value2) {
            addCriterion("EXPRESS_TAKE_INFO not between", value1, value2, "expressTakeInfo");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdIsNull() {
            addCriterion("BOX_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdIsNotNull() {
            addCriterion("BOX_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdEqualTo(Long value) {
            addCriterion("BOX_INFO_ID =", value, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdNotEqualTo(Long value) {
            addCriterion("BOX_INFO_ID <>", value, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdGreaterThan(Long value) {
            addCriterion("BOX_INFO_ID >", value, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BOX_INFO_ID >=", value, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdLessThan(Long value) {
            addCriterion("BOX_INFO_ID <", value, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("BOX_INFO_ID <=", value, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdIn(List<Long> values) {
            addCriterion("BOX_INFO_ID in", values, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdNotIn(List<Long> values) {
            addCriterion("BOX_INFO_ID not in", values, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdBetween(Long value1, Long value2) {
            addCriterion("BOX_INFO_ID between", value1, value2, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("BOX_INFO_ID not between", value1, value2, "boxInfoId");
            return (Criteria) this;
        }

        public Criteria andBoxNameIsNull() {
            addCriterion("BOX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBoxNameIsNotNull() {
            addCriterion("BOX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBoxNameEqualTo(String value) {
            addCriterion("BOX_NAME =", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotEqualTo(String value) {
            addCriterion("BOX_NAME <>", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameGreaterThan(String value) {
            addCriterion("BOX_NAME >", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameGreaterThanOrEqualTo(String value) {
            addCriterion("BOX_NAME >=", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLessThan(String value) {
            addCriterion("BOX_NAME <", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLessThanOrEqualTo(String value) {
            addCriterion("BOX_NAME <=", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameLike(String value) {
            addCriterion("BOX_NAME like", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotLike(String value) {
            addCriterion("BOX_NAME not like", value, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameIn(List<String> values) {
            addCriterion("BOX_NAME in", values, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotIn(List<String> values) {
            addCriterion("BOX_NAME not in", values, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameBetween(String value1, String value2) {
            addCriterion("BOX_NAME between", value1, value2, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxNameNotBetween(String value1, String value2) {
            addCriterion("BOX_NAME not between", value1, value2, "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxCodeIsNull() {
            addCriterion("BOX_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBoxCodeIsNotNull() {
            addCriterion("BOX_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBoxCodeEqualTo(String value) {
            addCriterion("BOX_CODE =", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeNotEqualTo(String value) {
            addCriterion("BOX_CODE <>", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeGreaterThan(String value) {
            addCriterion("BOX_CODE >", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BOX_CODE >=", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeLessThan(String value) {
            addCriterion("BOX_CODE <", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeLessThanOrEqualTo(String value) {
            addCriterion("BOX_CODE <=", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeLike(String value) {
            addCriterion("BOX_CODE like", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeNotLike(String value) {
            addCriterion("BOX_CODE not like", value, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeIn(List<String> values) {
            addCriterion("BOX_CODE in", values, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeNotIn(List<String> values) {
            addCriterion("BOX_CODE not in", values, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeBetween(String value1, String value2) {
            addCriterion("BOX_CODE between", value1, value2, "boxCode");
            return (Criteria) this;
        }

        public Criteria andBoxCodeNotBetween(String value1, String value2) {
            addCriterion("BOX_CODE not between", value1, value2, "boxCode");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdIsNull() {
            addCriterion("CUSTOMER_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdIsNotNull() {
            addCriterion("CUSTOMER_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdEqualTo(Long value) {
            addCriterion("CUSTOMER_INFO_ID =", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdNotEqualTo(Long value) {
            addCriterion("CUSTOMER_INFO_ID <>", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdGreaterThan(Long value) {
            addCriterion("CUSTOMER_INFO_ID >", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUSTOMER_INFO_ID >=", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdLessThan(Long value) {
            addCriterion("CUSTOMER_INFO_ID <", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("CUSTOMER_INFO_ID <=", value, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdIn(List<Long> values) {
            addCriterion("CUSTOMER_INFO_ID in", values, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdNotIn(List<Long> values) {
            addCriterion("CUSTOMER_INFO_ID not in", values, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdBetween(Long value1, Long value2) {
            addCriterion("CUSTOMER_INFO_ID between", value1, value2, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andCustomerInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("CUSTOMER_INFO_ID not between", value1, value2, "customerInfoId");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("MOBILE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("MOBILE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("MOBILE_PHONE =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("MOBILE_PHONE <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("MOBILE_PHONE >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("MOBILE_PHONE <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("MOBILE_PHONE like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("MOBILE_PHONE not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("MOBILE_PHONE in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("MOBILE_PHONE not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerIsNull() {
            addCriterion("PROXY_CUSTOMER is null");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerIsNotNull() {
            addCriterion("PROXY_CUSTOMER is not null");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerEqualTo(String value) {
            addCriterion("PROXY_CUSTOMER =", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerNotEqualTo(String value) {
            addCriterion("PROXY_CUSTOMER <>", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerGreaterThan(String value) {
            addCriterion("PROXY_CUSTOMER >", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerGreaterThanOrEqualTo(String value) {
            addCriterion("PROXY_CUSTOMER >=", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerLessThan(String value) {
            addCriterion("PROXY_CUSTOMER <", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerLessThanOrEqualTo(String value) {
            addCriterion("PROXY_CUSTOMER <=", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerLike(String value) {
            addCriterion("PROXY_CUSTOMER like", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerNotLike(String value) {
            addCriterion("PROXY_CUSTOMER not like", value, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerIn(List<String> values) {
            addCriterion("PROXY_CUSTOMER in", values, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerNotIn(List<String> values) {
            addCriterion("PROXY_CUSTOMER not in", values, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerBetween(String value1, String value2) {
            addCriterion("PROXY_CUSTOMER between", value1, value2, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerNotBetween(String value1, String value2) {
            addCriterion("PROXY_CUSTOMER not between", value1, value2, "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNull() {
            addCriterion("SENDTIME is null");
            return (Criteria) this;
        }

        public Criteria andSendtimeIsNotNull() {
            addCriterion("SENDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendtimeEqualTo(Date value) {
            addCriterion("SENDTIME =", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotEqualTo(Date value) {
            addCriterion("SENDTIME <>", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThan(Date value) {
            addCriterion("SENDTIME >", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SENDTIME >=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThan(Date value) {
            addCriterion("SENDTIME <", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("SENDTIME <=", value, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeIn(List<Date> values) {
            addCriterion("SENDTIME in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotIn(List<Date> values) {
            addCriterion("SENDTIME not in", values, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeBetween(Date value1, Date value2) {
            addCriterion("SENDTIME between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("SENDTIME not between", value1, value2, "sendtime");
            return (Criteria) this;
        }

        public Criteria andTaketimeIsNull() {
            addCriterion("TAKETIME is null");
            return (Criteria) this;
        }

        public Criteria andTaketimeIsNotNull() {
            addCriterion("TAKETIME is not null");
            return (Criteria) this;
        }

        public Criteria andTaketimeEqualTo(Date value) {
            addCriterion("TAKETIME =", value, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeNotEqualTo(Date value) {
            addCriterion("TAKETIME <>", value, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeGreaterThan(Date value) {
            addCriterion("TAKETIME >", value, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TAKETIME >=", value, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeLessThan(Date value) {
            addCriterion("TAKETIME <", value, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeLessThanOrEqualTo(Date value) {
            addCriterion("TAKETIME <=", value, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeIn(List<Date> values) {
            addCriterion("TAKETIME in", values, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeNotIn(List<Date> values) {
            addCriterion("TAKETIME not in", values, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeBetween(Date value1, Date value2) {
            addCriterion("TAKETIME between", value1, value2, "taketime");
            return (Criteria) this;
        }

        public Criteria andTaketimeNotBetween(Date value1, Date value2) {
            addCriterion("TAKETIME not between", value1, value2, "taketime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andBoxNameLikeInsensitive(String value) {
            addCriterion("upper(BOX_NAME) like", value.toUpperCase(), "boxName");
            return (Criteria) this;
        }

        public Criteria andBoxCodeLikeInsensitive(String value) {
            addCriterion("upper(BOX_CODE) like", value.toUpperCase(), "boxCode");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLikeInsensitive(String value) {
            addCriterion("upper(MOBILE_PHONE) like", value.toUpperCase(), "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andProxyCustomerLikeInsensitive(String value) {
            addCriterion("upper(PROXY_CUSTOMER) like", value.toUpperCase(), "proxyCustomer");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(REMARK) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_express_take_info
     * @author do_not_delete_during_merge MyBatis Generator
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 本类由Mybatis Generator自动生成
     * 映射数据库: t_express_take_info
     * @author MyBatis Generator
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}