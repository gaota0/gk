package com.gk.rpc.provider.service;

import com.gk.rpc.api.intf.UserService;
import com.gk.rpc.api.model.User;

/**
 * @author gaot
 * @date 2021/7/24
 */
public class UserServiceImpl implements UserService {
    @Override
    public User queryById(Integer id) {
        final User user = new User();
        user.setId(1);
        user.setName("小明");
        return user;
    }
}
