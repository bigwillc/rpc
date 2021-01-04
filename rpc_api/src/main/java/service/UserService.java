package service;

import model.User;

/**
 * @author bigwillc
 */
public interface UserService {

    /**
     * find by id
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
