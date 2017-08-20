package com.xiyou.jdbc;

import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.util.Collections;

/**
 * @author cc
 * @create 2017-08-20-13:56
 */

public class B {
    static {
        Object obj = null;
        try {
            Class<A> aClass = A.class;
            Constructor cons = aClass.getDeclaredConstructor();
            cons.setAccessible(true);
            obj = cons.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public B (){
//       super();
//
//    }
    public static void main(String[] args) {
        System.out.println("over");
    }
}
