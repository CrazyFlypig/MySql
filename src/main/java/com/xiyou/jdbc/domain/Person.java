package com.xiyou.jdbc.domain;

/**
 * 表数据
 *
 * @author cc
 * @create 2017-08-18-0:44
 */

public class Person {
    private int id ;
    private String name;
    private int age;
    public Person(int id, String name , int age ){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Person(){
        this.id = -1;
        this.name = "";
        this.age = -1;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
