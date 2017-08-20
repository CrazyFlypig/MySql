package com.xiyou.ConnectionPool;


import java.sql.*;

/**
 * @author cc
 * @create 2017-08-20-15:17
 */

public class APP {
    public static void main(String[] args) throws Exception {
        MyDataSource myDataSource = new MyDataSource();
        System.out.println(myDataSource.Size());
        Connection conn = myDataSource.getConnection();
        Statement st = conn.createStatement();
        String sql = "select id,name,age from test";
        ResultSet result = st.executeQuery(sql);
        while (result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            int age = result.getInt("age");
            System.out.println("id:" + id + " name:" + name + " age:" + age);
        }
        result.close();
        st.close();
        conn.close();
        System.out.println(myDataSource.Size());
    }
}
