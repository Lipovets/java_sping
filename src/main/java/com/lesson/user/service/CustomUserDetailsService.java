package com.lesson.user.service;

import com.lesson.user.dao.UserDao;
import com.lesson.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alex
 */
@Service
public class CustomUserDetailsService implements UserDetailsService, UserService {

    @Autowired
    private UserDao dao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    @Transactional(readOnly = true)
    public User getUser(String name) {
        return dao.findByUserName(name);
    }


    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return dao.getUserDetails();
    }

    @Transactional
    public void saveUser(User user) {
        dao.save(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser(){
        return dao.getCurrentUser();
    }
}
