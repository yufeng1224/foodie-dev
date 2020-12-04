package com.yufeng.pojo.bo;

import lombok.Data;

/**
 * 描述:
 *      用于新增或修改地址的BO
 * @author yufeng
 * @create 2020-12-01
 */
@Data
public class AddressBO {

    private String addressId;

    private String userId;

    private String receiver;

    private String mobile;

    private String province;

    private String city;

    private String district;

    private String detail;

}
