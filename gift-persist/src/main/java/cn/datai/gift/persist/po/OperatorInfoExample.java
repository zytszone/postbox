package cn.datai.gift.persist.po;

import cn.datai.gift.persist.vo.Page;
import java.util.ArrayList;
import java.util.List;

public class OperatorInfoExample {
    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    protected String orderByClause;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    protected Page page;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    protected boolean distinct;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    protected List<Criteria> oredCriteria;

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public OperatorInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public Page getPage() {
        return page;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
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
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 本段代码由Mybatis Generator自动生成.
     * 映射数据库: OPERATOR_INFO
     * @author MyBatis Generator
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 本类由Mybatis Generator自动生成
     * 映射数据库: OPERATOR_INFO
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

        public Criteria andOperatorInfoIdIsNull() {
            addCriterion("OPERATOR_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdIsNotNull() {
            addCriterion("OPERATOR_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdEqualTo(Long value) {
            addCriterion("OPERATOR_INFO_ID =", value, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdNotEqualTo(Long value) {
            addCriterion("OPERATOR_INFO_ID <>", value, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdGreaterThan(Long value) {
            addCriterion("OPERATOR_INFO_ID >", value, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OPERATOR_INFO_ID >=", value, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdLessThan(Long value) {
            addCriterion("OPERATOR_INFO_ID <", value, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("OPERATOR_INFO_ID <=", value, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdIn(List<Long> values) {
            addCriterion("OPERATOR_INFO_ID in", values, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdNotIn(List<Long> values) {
            addCriterion("OPERATOR_INFO_ID not in", values, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdBetween(Long value1, Long value2) {
            addCriterion("OPERATOR_INFO_ID between", value1, value2, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andOperatorInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("OPERATOR_INFO_ID not between", value1, value2, "operatorInfoId");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNull() {
            addCriterion("MOBILEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNotNull() {
            addCriterion("MOBILEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneEqualTo(String value) {
            addCriterion("MOBILEPHONE =", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotEqualTo(String value) {
            addCriterion("MOBILEPHONE <>", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThan(String value) {
            addCriterion("MOBILEPHONE >", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILEPHONE >=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThan(String value) {
            addCriterion("MOBILEPHONE <", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILEPHONE <=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLike(String value) {
            addCriterion("MOBILEPHONE like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotLike(String value) {
            addCriterion("MOBILEPHONE not like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIn(List<String> values) {
            addCriterion("MOBILEPHONE in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotIn(List<String> values) {
            addCriterion("MOBILEPHONE not in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneBetween(String value1, String value2) {
            addCriterion("MOBILEPHONE between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotBetween(String value1, String value2) {
            addCriterion("MOBILEPHONE not between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("POSTCODE =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("POSTCODE <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("POSTCODE >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("POSTCODE >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("POSTCODE <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("POSTCODE <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("POSTCODE like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("POSTCODE not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("POSTCODE in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("POSTCODE not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("POSTCODE between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("POSTCODE not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("CONTACT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("CONTACT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("CONTACT_NAME =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("CONTACT_NAME <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("CONTACT_NAME >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("CONTACT_NAME <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("CONTACT_NAME like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("CONTACT_NAME not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("CONTACT_NAME in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("CONTACT_NAME not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andBindAccountIsNull() {
            addCriterion("BIND_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBindAccountIsNotNull() {
            addCriterion("BIND_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBindAccountEqualTo(String value) {
            addCriterion("BIND_ACCOUNT =", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotEqualTo(String value) {
            addCriterion("BIND_ACCOUNT <>", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountGreaterThan(String value) {
            addCriterion("BIND_ACCOUNT >", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountGreaterThanOrEqualTo(String value) {
            addCriterion("BIND_ACCOUNT >=", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountLessThan(String value) {
            addCriterion("BIND_ACCOUNT <", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountLessThanOrEqualTo(String value) {
            addCriterion("BIND_ACCOUNT <=", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountLike(String value) {
            addCriterion("BIND_ACCOUNT like", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotLike(String value) {
            addCriterion("BIND_ACCOUNT not like", value, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountIn(List<String> values) {
            addCriterion("BIND_ACCOUNT in", values, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotIn(List<String> values) {
            addCriterion("BIND_ACCOUNT not in", values, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountBetween(String value1, String value2) {
            addCriterion("BIND_ACCOUNT between", value1, value2, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andBindAccountNotBetween(String value1, String value2) {
            addCriterion("BIND_ACCOUNT not between", value1, value2, "bindAccount");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeIsNull() {
            addCriterion("WX_MERCHANT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeIsNotNull() {
            addCriterion("WX_MERCHANT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeEqualTo(String value) {
            addCriterion("WX_MERCHANT_CODE =", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeNotEqualTo(String value) {
            addCriterion("WX_MERCHANT_CODE <>", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeGreaterThan(String value) {
            addCriterion("WX_MERCHANT_CODE >", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WX_MERCHANT_CODE >=", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeLessThan(String value) {
            addCriterion("WX_MERCHANT_CODE <", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeLessThanOrEqualTo(String value) {
            addCriterion("WX_MERCHANT_CODE <=", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeLike(String value) {
            addCriterion("WX_MERCHANT_CODE like", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeNotLike(String value) {
            addCriterion("WX_MERCHANT_CODE not like", value, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeIn(List<String> values) {
            addCriterion("WX_MERCHANT_CODE in", values, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeNotIn(List<String> values) {
            addCriterion("WX_MERCHANT_CODE not in", values, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeBetween(String value1, String value2) {
            addCriterion("WX_MERCHANT_CODE between", value1, value2, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeNotBetween(String value1, String value2) {
            addCriterion("WX_MERCHANT_CODE not between", value1, value2, "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneIsNull() {
            addCriterion("WX_CONTACT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneIsNotNull() {
            addCriterion("WX_CONTACT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneEqualTo(String value) {
            addCriterion("WX_CONTACT_PHONE =", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneNotEqualTo(String value) {
            addCriterion("WX_CONTACT_PHONE <>", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneGreaterThan(String value) {
            addCriterion("WX_CONTACT_PHONE >", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("WX_CONTACT_PHONE >=", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneLessThan(String value) {
            addCriterion("WX_CONTACT_PHONE <", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("WX_CONTACT_PHONE <=", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneLike(String value) {
            addCriterion("WX_CONTACT_PHONE like", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneNotLike(String value) {
            addCriterion("WX_CONTACT_PHONE not like", value, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneIn(List<String> values) {
            addCriterion("WX_CONTACT_PHONE in", values, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneNotIn(List<String> values) {
            addCriterion("WX_CONTACT_PHONE not in", values, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneBetween(String value1, String value2) {
            addCriterion("WX_CONTACT_PHONE between", value1, value2, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneNotBetween(String value1, String value2) {
            addCriterion("WX_CONTACT_PHONE not between", value1, value2, "wxContactPhone");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(CODE) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andTelephoneLikeInsensitive(String value) {
            addCriterion("upper(TELEPHONE) like", value.toUpperCase(), "telephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLikeInsensitive(String value) {
            addCriterion("upper(MOBILEPHONE) like", value.toUpperCase(), "mobilephone");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(ADDRESS) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andPostcodeLikeInsensitive(String value) {
            addCriterion("upper(POSTCODE) like", value.toUpperCase(), "postcode");
            return (Criteria) this;
        }

        public Criteria andContactNameLikeInsensitive(String value) {
            addCriterion("upper(CONTACT_NAME) like", value.toUpperCase(), "contactName");
            return (Criteria) this;
        }

        public Criteria andBindAccountLikeInsensitive(String value) {
            addCriterion("upper(BIND_ACCOUNT) like", value.toUpperCase(), "bindAccount");
            return (Criteria) this;
        }

        public Criteria andWxMerchantCodeLikeInsensitive(String value) {
            addCriterion("upper(WX_MERCHANT_CODE) like", value.toUpperCase(), "wxMerchantCode");
            return (Criteria) this;
        }

        public Criteria andWxContactPhoneLikeInsensitive(String value) {
            addCriterion("upper(WX_CONTACT_PHONE) like", value.toUpperCase(), "wxContactPhone");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OPERATOR_INFO
     * @author do_not_delete_during_merge MyBatis Generator
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * 本类由Mybatis Generator自动生成
     * 映射数据库: OPERATOR_INFO
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