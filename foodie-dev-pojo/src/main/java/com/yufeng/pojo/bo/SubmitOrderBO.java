package com.yufeng.pojo.bo;

import lombok.Data;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-02
 */
@Data
public class SubmitOrderBO {

    private String userId;

    private String itemSpecIds;

    private String addressId;

    private Integer payMethod;

    private String leftMsg;

}
