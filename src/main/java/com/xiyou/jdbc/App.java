package com.xiyou.jdbc;

import com.xiyou.jdbc.dao.PersonDao;
import com.xiyou.jdbc.domain.Person;

/**
 * @author cc
 * @create 2017-08-18-1:04
 */

public class App {
    public static void main(String[] args) {
        PersonDao dao = new PersonDao();
        Person p = new Person();
        p.setId(10);
        p.setName("cc");
        p.setAge(20);
        dao.insert(p);
    }
}
