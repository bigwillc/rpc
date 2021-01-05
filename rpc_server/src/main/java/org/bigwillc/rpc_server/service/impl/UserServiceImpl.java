package org.bigwillc.rpc_server.service.impl;

import model.User;
import service.UserService;

/**
 * UserService implementation.
 *
 * @author bigwillc
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return new User(id, "RPC");
    }
}
