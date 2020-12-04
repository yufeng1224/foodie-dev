package com.yufeng.service;

import com.yufeng.pojo.UserAddress;
import com.yufeng.pojo.bo.AddressBO;

import java.util.List;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-01
 */
public interface AddressService {

    /**
     * 根据用户id查询用户的收货地址列表
     * @param userId
     * @return
     */
    public List<UserAddress> queryAll(String userId);


    /**
     * 用户新增地址
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);


    /**
     * 用户修改地址
     * @param addressBO
     */
    public void updateUserAddress(AddressBO addressBO);


    /**
     * 根据用户id和地址id，删除对应的用户地址信息
     * @param addressId
     * @param userId
     */
    public void deleteUserAddress(String userId, String addressId);


    /**
     * 修改默认地址
     * @param addressId
     * @param userId
     */
    public void updateUserAddressToBeDefault(String userId, String addressId);


    /**
     * 根据用户id和地址id, 查询具体的用户地址信息
     * @param userId
     * @param userId
     * @return
     */
    public UserAddress queryUserAddress(String userId, String addressId);


}
