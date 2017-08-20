package com.xiyou.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 测试第三方数据源——C3P0
 *
 * @author cc
 * @create 2017-08-20-17:38
 */

public class TestC3P0 {
    private ComboPooledDataSource ds;
    @Before
    public void initDataSource(){
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/mybase");
            ds.setUser("root");
            ds.setPassword("root");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void FindAll() throws PropertyVetoException, SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM test");
        ResultSet result = pst.executeQuery();
        while (result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            int age = result.getInt("age");
            System.out.println("id:" + id + " name:" + name + " age:" + age);
        }
    }
    @Test
    public void insert() throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO test(id,name,age) VALUES (?,?,?)");
        pst.setInt(1,40);
        pst.setString(2,"zz");
        pst.setInt(3,100);
        pst.execute();
    }
}
