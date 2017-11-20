package com.future.membership.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.future.membership.bean.page.Page;

public class CardDtoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public CardDtoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMembership_idIsNull() {
            addCriterion("membership_id is null");
            return (Criteria) this;
        }

        public Criteria andMembership_idIsNotNull() {
            addCriterion("membership_id is not null");
            return (Criteria) this;
        }

        public Criteria andMembership_idEqualTo(Integer value) {
            addCriterion("membership_id =", value, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idNotEqualTo(Integer value) {
            addCriterion("membership_id <>", value, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idGreaterThan(Integer value) {
            addCriterion("membership_id >", value, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("membership_id >=", value, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idLessThan(Integer value) {
            addCriterion("membership_id <", value, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idLessThanOrEqualTo(Integer value) {
            addCriterion("membership_id <=", value, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idIn(List<Integer> values) {
            addCriterion("membership_id in", values, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idNotIn(List<Integer> values) {
            addCriterion("membership_id not in", values, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idBetween(Integer value1, Integer value2) {
            addCriterion("membership_id between", value1, value2, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_idNotBetween(Integer value1, Integer value2) {
            addCriterion("membership_id not between", value1, value2, "membership_id");
            return (Criteria) this;
        }

        public Criteria andMembership_nameIsNull() {
            addCriterion("membership_name is null");
            return (Criteria) this;
        }

        public Criteria andMembership_nameIsNotNull() {
            addCriterion("membership_name is not null");
            return (Criteria) this;
        }

        public Criteria andMembership_nameEqualTo(String value) {
            addCriterion("membership_name =", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameNotEqualTo(String value) {
            addCriterion("membership_name <>", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameGreaterThan(String value) {
            addCriterion("membership_name >", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameGreaterThanOrEqualTo(String value) {
            addCriterion("membership_name >=", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameLessThan(String value) {
            addCriterion("membership_name <", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameLessThanOrEqualTo(String value) {
            addCriterion("membership_name <=", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameLike(String value) {
            addCriterion("membership_name like", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameNotLike(String value) {
            addCriterion("membership_name not like", value, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameIn(List<String> values) {
            addCriterion("membership_name in", values, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameNotIn(List<String> values) {
            addCriterion("membership_name not in", values, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameBetween(String value1, String value2) {
            addCriterion("membership_name between", value1, value2, "membership_name");
            return (Criteria) this;
        }

        public Criteria andMembership_nameNotBetween(String value1, String value2) {
            addCriterion("membership_name not between", value1, value2, "membership_name");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysIsNull() {
            addCriterion("remaining_days is null");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysIsNotNull() {
            addCriterion("remaining_days is not null");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysEqualTo(Integer value) {
            addCriterion("remaining_days =", value, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysNotEqualTo(Integer value) {
            addCriterion("remaining_days <>", value, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysGreaterThan(Integer value) {
            addCriterion("remaining_days >", value, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysGreaterThanOrEqualTo(Integer value) {
            addCriterion("remaining_days >=", value, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysLessThan(Integer value) {
            addCriterion("remaining_days <", value, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysLessThanOrEqualTo(Integer value) {
            addCriterion("remaining_days <=", value, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysIn(List<Integer> values) {
            addCriterion("remaining_days in", values, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysNotIn(List<Integer> values) {
            addCriterion("remaining_days not in", values, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysBetween(Integer value1, Integer value2) {
            addCriterion("remaining_days between", value1, value2, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andRemaining_daysNotBetween(Integer value1, Integer value2) {
            addCriterion("remaining_days not between", value1, value2, "remaining_days");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Integer value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Integer value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Integer value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Integer value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Integer value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Integer value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Integer> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Integer> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Integer value1, Integer value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Integer value1, Integer value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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