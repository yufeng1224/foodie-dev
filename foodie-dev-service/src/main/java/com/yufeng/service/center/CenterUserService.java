package com.yufeng.service.center;

import com.yufeng.pojo.Users;
import com.yufeng.pojo.bo.center.CenterUserBO;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-07
 */
public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);


    /**
     * 修改用户信息
     * @param userId
     * @param centerUserBO
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);


}
