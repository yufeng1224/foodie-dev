package com.yufeng.enums;

import lombok.Getter;

/**
 * 描述:
 *      性别 枚举
 * @author yufeng
 * @create 2020-10-20
 */
@Getter
public enum Sex {

    woman(0, "女"),

    man(1, "男"),

    secret(2, "保密");

    public final Integer type;

    public final String value;

    Sex (Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
