package com.imooc.passbook.service;

import com.imooc.passbook.vo.Response;
import com.imooc.passbook.vo.User;

/**
 * <h1>用户服务：创建 User 服务</h1>
 */
public interface IUserService {
    /**
     * <h2>创建用户</h2>
     * @param user
     * @return  {@link User}
     * @throws Exception
     */
    Response  createUser(User user) throws Exception;
}
