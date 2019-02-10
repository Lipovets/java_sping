package com.lesson.user.dao;

import com.lesson.user.model.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 */
@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public User getCurrentUser() {
        SecurityContext sc = SecurityContextHolder.getContext();
        User currentUser = null;
        if(sc != null && sc.getAuthentication() != null && sc.getAuthentication().getPrincipal() != null) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) sc.getAuthentication().getPrincipal();
            currentUser = findByUserName(user.getUsername());
        }
        return currentUser;
    }

    @Override
    public User findByUserName(String username) {
        List<User> users;
        users = (List<User>) getSession()
                .createCriteria(User.class, "user")
                .add(Restrictions.eq("username", username))
                .list();
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUserDetails() {
        return (List<User>) getSession().createCriteria(User.class).list();
    }

    @Override
    public void save(User user) {
        getSession().save(user);
    }
}
