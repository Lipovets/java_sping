package com.lesson.user.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Olexandr
 */
public class AbstractDao implements Dao {

    private SessionFactory sessionFactory;

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void flushSession() {
        getSession().flush();
    }

    @Override
    public void clearSession() {
        getSession().clear();
    }

    @Autowired
    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
