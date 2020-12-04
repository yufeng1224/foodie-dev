package com.yufeng.enums;

import lombok.Getter;

/**
 * 描述:
 *      支付方式枚举
 * @author yufeng
 * @create 2020-12-02
 */
@Getter
public enum PayMethod {

    WEIXIN(1, "微信"),

    ALIPAY(2, "支付宝");

    public final Integer type;

    public final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
