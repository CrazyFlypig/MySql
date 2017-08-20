package com.xiyou.jdbc.dao;

import com.xiyou.jdbc.domain.Person;
import com.xiyou.jdbc.util.JDBCUtilClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cc
 * @create 2017-08-18-0:48
 */

public class PersonDao {
    public void insert(Person p){
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtilClass.openConnection();
            st = conn.createStatement();
            String sql = null;
            if (p.getId() != -1){
                sql = "insert into persons(id,name,age) values("+p.getId()+",'"+p.getName()+"',"+p.getAge()+")";
            }else {
                sql = "insert into persons(name,age) values('"+p.getName()+"',"+p.getAge()+")";
            }
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilClass.closeStatement(st);
            JDBCUtilClass.closeConnection(conn);
        }
    }
    public void update(Person p){
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtilClass.openConnection();
            st = conn.createStatement();
            String sql = "update persons set name = '"+p.getName()+"',age="+p.getAge()+" where id = " + p.getId();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilClass.closeStatement(st);
            JDBCUtilClass.closeConnection(conn);
        }
    }
    public void delete(Integer id){
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtilClass.openConnection();
            st = conn.createStatement();
            String sql = "DELETE  * FROM persons WHENEVER id = "+id;
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilClass.closeStatement(st);
            JDBCUtilClass.closeConnection(conn);
        }
    }
    public void FindID(Integer id){
        Connection conn = null;
        Statement st = null;
        try {
            conn = JDBCUtilClass.openConnection();
            st = conn.createStatement();
            String sql = "SELECT * FROM persons WHENEVER id = "+id;
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilClass.closeStatement(st);
            JDBCUtilClass.closeConnection(conn);
        }
    }
}
