package com.lesson.user.dao;

import com.lesson.user.model.User;

import java.util.List;

/**
 * @author Alex
 */
public interface UserDao {

    /**
     * Returns current authorized user
     * @return
     */
    User getCurrentUser();

    /** TODO
     * Returns first user by UserName
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * List All user
     * @return
     */
    List<User> getUserDetails();

    /**
     * TODO move to GeneralDao
     * @param user
     */
    void save(User user);
}
