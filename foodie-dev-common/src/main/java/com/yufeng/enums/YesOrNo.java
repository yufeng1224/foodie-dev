package com.yufeng.enums;

import lombok.Getter;

/**
 * 描述:
 *      是否 枚举
 * @author yufeng
 * @create 2020-10-23
 */
@Getter
public enum YesOrNo {

    NO(0, "否"),

    YES(1, "是");

    public final Integer type;

    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }


}
