package com.lesson.user.dao;

import org.hibernate.Session;

/**
 * @author Olexandr
 */
public interface Dao {
    Session getSession();

    void flushSession();

    void clearSession();
}
