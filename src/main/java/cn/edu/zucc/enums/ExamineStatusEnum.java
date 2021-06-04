package cn.edu.zucc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author wangyangkai
 */

public enum ExamineStatusEnum {

    REVIEWED(0, "审核通过"),
    UNDER_REVIEW(1, "审核中"),
    REJECTED(2, "审核未通过");

    @EnumValue
    private Integer status;

    @JsonValue
    private String value;

    ExamineStatusEnum(Integer status, String value) {
        this.status = status;
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ExamineStatusEnum match(int key) {
        for (ExamineStatusEnum s : values()) {
            if (s.getStatus() == key) {
                return s;
            }
        }

        return null;
    }

    public static ExamineStatusEnum catchValue(String value) {
        for (ExamineStatusEnum s : values()) {
            if (s.getValue().equals(value)) {
                return s;
            }
        }
        return null;
    }
}
