package com.gk.rpc.api.intf;

import com.gk.rpc.api.model.User;

/**
 * @author gaot
 * @date 2021/7/24
 */
public interface UserService {
    /**
     * 查询  根据id
     *
     * @param id id
     * @return {@link User}
     */
    User queryById(Integer id);
}
