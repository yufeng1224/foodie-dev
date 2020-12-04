package com.yufeng.service;

import com.yufeng.pojo.Users;
import com.yufeng.pojo.bo.UserBO;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-19
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     *    1. 业务类型是 BO 对象
     *
     * 延伸知识点: 阿里巴巴Java开发手册中的DO、DTO、BO、AO、VO、POJO定义
     *    1. DO(Data Object): 与数据库表结构一一对应, 通过DAO层向上传输数据源对象;
     *    2. DTO(Data Transfer Object): 数据传输对象, service或Manager向外传输的对象;
     *    3. BO(Business Object): 业务对象。由Service层输出的封装业务逻辑的对象;
     *    4. AO(Application Object): 应用对象。在Web层与Service层之间抽象的复用对象模型
     *    5. VO(View Object): 显示层对象, 通常是Web向模板渲染引擎层传输的对象
     *    6. POJO（ Plain Ordinary Java Object）：在本手册中， POJO专指只有setter/getter/toString的简单类，包括DO/DTO/BO/VO等。
     *    7. Query：数据查询对象，各层接收上层的查询请求。 注意超过2个参数的查询封装，禁止使用Map类来传输。
     *
     * 领域模型命名规约：
     *    1. 数据对象：xxxDO，xxx即为数据表名。
     *    2. 数据传输对象：xxxDTO，xxx为业务领域相关的名称。
     *    3. 展示对象：xxxVO，xxx一般为网页名称。
     *    4. POJO是DO/DTO/BO/VO的统称，禁止命名成xxxPOJO。
     */
    public Users createUser(UserBO userBO);

    /**
     * 检锁用户名和密码是否匹配, 用于登录
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);

}
